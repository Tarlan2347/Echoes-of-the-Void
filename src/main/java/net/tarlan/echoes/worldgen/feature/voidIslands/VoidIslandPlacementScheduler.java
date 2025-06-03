package net.tarlan.echoes.worldgen.feature.voidIslands;
import com.mojang.datafixers.util.Unit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.tarlan.echoes.worldgen.util.BlockToPlace;

import java.util.*;

public class VoidIslandPlacementScheduler {

    private static final Map<ChunkPos, List<BlockToPlace>> chunkedQueue = new HashMap<>();
    private static ServerLevel currentLevel;
    private static int blocksPerTick = 5000;
    private static boolean active = false;
    private static long placementStartTime = -1;
    private static final Deque<BlockPos> updateQueue = new ArrayDeque<>();
    private static boolean updatingVisuals = false;
    private static final int updatesPerTick = 2000;




    public static void schedule(ServerLevel level, List<BlockToPlace> blocks, int perTick) {
        if (chunkedQueue.isEmpty()) {
            currentLevel = level;
            blocksPerTick = perTick;
            active = true;
            placementStartTime = System.currentTimeMillis(); // Start timing
        }

        for (BlockToPlace block : blocks) {
            ChunkPos chunkPos = new ChunkPos(block.pos());
            chunkedQueue.computeIfAbsent(chunkPos, k -> new ArrayList<>()).add(block);
        }

        System.out.println("[VoidPlacer] Scheduling " + blocks.size() + " blocks to be placed");
    }



    public static void tick() {
        int totalBlocks = 0;
        int skippedBlocks = 0;
        if (!active || currentLevel == null || chunkedQueue.isEmpty()) return;
        int minY = currentLevel.getMinBuildHeight();
        int maxY = currentLevel.getMaxBuildHeight();

        long startTime = System.nanoTime();
        int placed = 0;

        List<ChunkPos> finishedChunks = new ArrayList<>();

        BlockPos playerPos = getAnyPlayerPos(currentLevel);

        List<Map.Entry<ChunkPos, List<BlockToPlace>>> sortedEntries = new ArrayList<>(chunkedQueue.entrySet());

        sortedEntries.sort(Comparator.comparingDouble(entry ->
                distanceSquared(entry.getKey(), playerPos)
        ));

        for (Map.Entry<ChunkPos, List<BlockToPlace>> entry : sortedEntries) {
            if (placed >= blocksPerTick) break;

            ChunkPos chunkPos = entry.getKey();
            List<BlockToPlace> blocks = entry.getValue();

            LevelChunk chunk = currentLevel.getChunk(chunkPos.x, chunkPos.z);

            // Place all blocks

            for (BlockToPlace block : blocks) {
                int y = block.pos().getY();
                if (y < minY || y >= maxY) {
                    skippedBlocks++;
                    continue;
                }
                chunk.setBlockState(block.pos(), block.state(), false);

                updateQueue.add(block.pos());
                placed++;
            }
            currentLevel.getChunkSource().chunkMap.read(chunkPos);
            chunk.setUnsaved(true);
            finishedChunks.add(chunkPos);
        }

        // Clean up
        for (ChunkPos pos : finishedChunks) {
            chunkedQueue.remove(pos);
        }

        // Finish
        if (chunkedQueue.isEmpty()) {
            long placementEndTime = System.currentTimeMillis();
            long totalDuration = placementEndTime - placementStartTime;
            System.out.println("[VoidPlacer] Total Island Placement Time Took: " + totalDuration + " ms (~" + (totalDuration / 1000.0) + " sec)");

            active = false;
            updatingVisuals = true;
        }
    }
    private static BlockPos getAnyPlayerPos(ServerLevel level) {
        return level.players().isEmpty() ? BlockPos.ZERO : level.players().get(0).blockPosition();
    }

    private static double distanceSquared(ChunkPos chunkPos, BlockPos playerPos) {
        int chunkCenterX = chunkPos.getMinBlockX() + 8;
        int chunkCenterZ = chunkPos.getMinBlockZ() + 8;

        int dx = chunkCenterX - playerPos.getX();
        int dz = chunkCenterZ - playerPos.getZ();
        return dx * dx + dz * dz;
    }

    public static void tickVisualUpdates() {
        if (!updatingVisuals || currentLevel == null || updateQueue.isEmpty()) return;
        Map<ChunkPos, List<BlockPos>> byChunk = new HashMap<>();

        for (int i = 0; i < updatesPerTick && !updateQueue.isEmpty(); i++) {
            BlockPos pos = updateQueue.pollLast();
            ChunkPos chunkPos = new ChunkPos(pos);
            byChunk.computeIfAbsent(chunkPos, k -> new ArrayList<>()).add(pos);
        }
        for (Map.Entry<ChunkPos, List<BlockPos>> entry : byChunk.entrySet()) {
            for (BlockPos pos : entry.getValue()) {
                BlockState state = currentLevel.getBlockState(pos);
                currentLevel.sendBlockUpdated(pos, state, state, 2);
            }
        }
        if (updateQueue.isEmpty()) {
            updatingVisuals = false;
            currentLevel = null;
        }
    }





    public static boolean isActive() {
        return active;
    }

    public static int getRemainingBlocks() {
        return chunkedQueue.values().stream().mapToInt(List::size).sum();
    }
}
