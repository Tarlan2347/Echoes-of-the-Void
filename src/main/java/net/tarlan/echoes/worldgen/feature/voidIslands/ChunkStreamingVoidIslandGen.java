package net.tarlan.echoes.worldgen.feature.voidIslands;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.tarlan.echoes.block.EchoesBlocks;

public class ChunkStreamingVoidIslandGen {
    private static final int CHUNK_WIDTH = 16;
    private static final int CHUNK_HEIGHT = 64;

    // Generation
    public static void generate(ServerLevel level, BlockPos origin, int radius, RandomSource random) {
        System.out.println("[StreamGen] Generating void island at: " + origin + " with radius " + radius);

        PerlinNoise noise = PerlinNoise.create(random, -6, 1, 1, 1, 2, 2, 2);
        BlockState islandBlock = EchoesBlocks.UMBRELITH.get().defaultBlockState();

        int maxHeight = origin.getY() + (CHUNK_HEIGHT / 2);
        int minHeight = origin.getY() - (CHUNK_HEIGHT / 2);

        int chunkRange = (radius * 3) / 16;

        for (int chunkX = -chunkRange; chunkX <= chunkRange; chunkX++) {
            for (int chunkZ = -chunkRange; chunkZ <= chunkRange; chunkZ++) {
                boolean wroteChunk = generateChunk(level, origin, radius, chunkX, chunkZ, noise, islandBlock, minHeight, maxHeight);

                // Optional optimization: stop when no chunks generate any content
                if (!wroteChunk) {
                    // Optionally: check if entire ring is empty before breaking
                }
            }
        }

        System.out.println("[StreamGen] Generation complete.");
    }

    // Core: generate one chunk
    private static boolean generateChunk(ServerLevel level, BlockPos origin, int radius, int chunkOffsetX, int chunkOffsetZ,
                                         PerlinNoise noise, BlockState state, int minY, int maxY) {
        int baseX = origin.getX() + chunkOffsetX * CHUNK_WIDTH;
        int baseZ = origin.getZ() + chunkOffsetZ * CHUNK_WIDTH;
        int centerX = origin.getX();
        int centerZ = origin.getZ();
        int originY = origin.getY();

        boolean wroteAnything = false;

        for (int dx = 0; dx < CHUNK_WIDTH; dx++) {
            for (int dz = 0; dz < CHUNK_WIDTH; dz++) {
                int wx = baseX + dx;
                int wz = baseZ + dz;

                float hDist = (float) Math.hypot(wx - centerX, wz - centerZ);
                if (hDist > radius) continue;

                for (int wy = minY; wy <= maxY; wy++) {
                    float yOffset = wy - originY;
                    float density = 5 + (float) noise.getValue(wx * 0.01, wy * 0.01, wz * 0.01);

                    density -= (Math.abs(yOffset) * (yOffset > 0 ? 0.08f : 0.16f));
                    density -= hDist * 0.04f;

                    if (density > 0f) {
                        BlockPos pos = new BlockPos(wx, wy, wz);
                        level.setBlock(pos, state, 3); // Flags = update + no neighbor notify
                        wroteAnything = true;
                    }
                }
            }
        }

        return wroteAnything;
    }
}
