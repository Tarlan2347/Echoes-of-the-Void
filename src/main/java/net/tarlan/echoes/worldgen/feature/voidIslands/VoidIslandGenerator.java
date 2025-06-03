package net.tarlan.echoes.worldgen.feature.voidIslands;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.worldgen.util.BlockToPlace;

import javax.annotation.Nullable;
import java.util.*;

public class VoidIslandGenerator {
    public static void generateVoidIsland(WorldGenLevel level, BlockPos origin, RandomSource random, @Nullable String biomeId, Integer optionalRadius) {
        long totalStart = System.nanoTime();
        System.out.println("[VoidIsland] Starting island generation at: " + origin);

        // --- Resolve Biome ---
        VoidIslandBiomes.VoidBiome biome = VoidIslandBiomes.resolve(biomeId, random);
        System.out.println("[VoidIsland] Biome: " + biome.id);

        // --- Volume Setup ---
        int mainRadius = optionalRadius != null ? optionalRadius : getRandomRadius(random);
        System.out.println("[VoidIsland] Radius: " + mainRadius);
        int mainHeight = (mainRadius * 2) / 4;
        int boundV = mainHeight * 2;
        int boundH = mainRadius * 3;
        System.out.println("[VoidIsland] Bounds: " + boundH + " " + boundV + " " + boundH);

        float[][] baseShapeMap = new float[boundH][boundH];
        float[][] topShapeMap = new float[boundH][boundH];
        BlockState[][][] blockVolume = new BlockState[boundH][boundV][boundH];
        PerlinNoise sharedNoise = PerlinNoise.create(random, -7, 1, 1, 1, 2, 2, 2);
        System.out.println("[VoidIsland] Noise generated...");

        generateBaseHeightMap(baseShapeMap, sharedNoise, boundH, mainRadius);
        System.out.println("[VoidIsland] Base heightmap created...");
        blurFilterXZMatrix(baseShapeMap, boundH, mainRadius, 1f, 0.1f);
        System.out.println("[VoidIsland] Base heightmap blurred...");
        generateTopHeightMap(topShapeMap, sharedNoise, boundH, mainRadius);
        System.out.println("[VoidIsland] Top heightmap created...");
        blurFilterXZMatrix(topShapeMap, boundH, mainRadius, 2.0f, 0.5f);
        System.out.println("[VoidIsland] Top heightmap blurred...");

        // --- Write Volume ---
        writeMassToVolume(blockVolume, baseShapeMap, EchoesBlocks.UMBRELITH.get().defaultBlockState(), boundV, boundH, mainHeight, true);
        System.out.println("[VoidIsland] Wrote base mass to volume...");
        writeMassToVolume(blockVolume, baseShapeMap, EchoesBlocks.UMBRELITH.get().defaultBlockState(), boundV, boundH, mainHeight, false);
        System.out.println("[VoidIsland] Wrote top mass to volume...");
        // --- Apply Surface Rules ---
        BlockState[][][] postSurfaceVolume = applySurfaceRules(blockVolume, biome.surfaceRule);
        System.out.println("[VoidIsland] Applied surface rules...");
        // --- Trim Volume with Margin ---
        int margin = mainRadius / 4;
        postSurfaceVolume = trimVolumeWithMargin(postSurfaceVolume, margin);
        System.out.println("[VoidIsland] Trimmed volume...");

        // --- Apply Ores ---
        oreGen(postSurfaceVolume, random, STONE_VEINS);
        System.out.println("[VoidIsland] Generated stone veins...");
        oreGen(postSurfaceVolume, random, ORE_VEINS);
        System.out.println("[VoidIsland] Generated ore veins...");

        // --- Export ---
        BlockState[][][] finalVolumeToExport = postSurfaceVolume;
        System.out.println("[VoidIsland] Mapped final volume...");
        List<BlockToPlace> blockList = exportBlockVolume(
                finalVolumeToExport,
                origin,
                finalVolumeToExport[0].length,
                finalVolumeToExport.length
        );
        System.out.println("[VoidIsland] Scheduling " + blockList.size() + " blocks");
        VoidIslandPlacementScheduler.schedule((ServerLevel) level, blockList, 100000);

        // --- Logging ---
        long totalEnd = System.nanoTime();
        logTime(totalStart, totalEnd);
    }
    private static void logTime(long start, long end) {
        long durationMs = (end - start) / 1_000_000;
        System.out.println("[VoidIsland] " + "TOTAL Calculation Time" + " took " + durationMs + " ms");
    }
    private static int getRandomRadius(RandomSource random) {
        return switch (random.nextIntBetweenInclusive(0, 7)) {
            case 0 -> 16;
            case 1 -> 32;
            case 2 -> 64;
            case 3 -> 72;
            case 4 -> 80;
            case 5 -> 96;
            case 6 -> 128;
            case 7 -> 160;
            default -> 8;
        };
    }
    private static void generateBaseHeightMap(float[][] baseShapeMap, PerlinNoise noise, int boundH, int mainRadius) {
        int center = boundH / 2;
        float fractionOfRadius = 1f / mainRadius;
        for (int x = 0; x < boundH; x++) {
            for (int z = 0; z < boundH; z++) {
                float noiseValue = (float) ((noise.getValue(x, 1, z) + 1));
                int dx = x - center;
                int dz = z - center;
                float distance = (float) Math.sqrt(dx * dx + dz * dz);
                baseShapeMap[x][z] = noiseValue - (distance * fractionOfRadius);
            }
        }
    }
    private static void generateTopHeightMap(float[][] topShapeMap, PerlinNoise noise, int boundH, int mainRadius) {
        int center = boundH / 2;
        float fractionOfRadius = 1f / mainRadius;
        for (int x = 0; x < boundH; x++) {
            for (int z = 0; z < boundH; z++) {
                float noiseValue = (float) ((noise.getValue(x, 1, z) + 1));
                int dx = x - center;
                int dz = z - center;
                float distance = (float) Math.sqrt(dx * dx + dz * dz);
                topShapeMap[x][z] = noiseValue - (distance * fractionOfRadius);
            }
        }
    }
    private static void blurFilterXZMatrix(float[][] base, int bound, int maxRadius, float centerMultiplier, float edgeMultiplier) {
        float[][] out = new float[bound][bound];
        int center = bound / 2;
        float maxDistance = (float) Math.sqrt(2 * (center * center));

        for (int x = 0; x < bound; x++) {
            for (int z = 0; z < bound; z++) {
                int dx = x - center;
                int dz = z - center;
                float distance = (float) Math.sqrt(dx * dx + dz * dz);
                float normDist = distance / maxDistance;

                float blurFactor = Mth.lerp(normDist, centerMultiplier, edgeMultiplier);
                int r = Mth.clamp((int)(blurFactor * maxRadius / 32), 1, 6);

                float sum = 0f;
                int count = 0;
                for (int fx = -r; fx <= r; fx++) {
                    int nx = x + fx;
                    if (nx < 0 || nx >= bound) continue;

                    for (int fz = -r; fz <= r; fz++) {
                        int nz = z + fz;
                        if (nz < 0 || nz >= bound) continue;

                        sum += base[nx][nz];
                        count++;
                    }
                }

                out[x][z] = count > 0 ? (sum / count) : base[x][z];
            }
        }

        for (int x = 0; x < bound; x++) {
            System.arraycopy(out[x], 0, base[x], 0, bound);
        }
    }
    private static void writeMassToVolume(BlockState[][][] volume, float[][] heightMap, BlockState blockState, int boundV, int boundH, float mainHeight, boolean isBase) {
        float yValStep = isBase ? 1F / mainHeight : 4F / mainHeight;
        float yVal = 0;
        int midY = (int) (boundV / 1.5F);

        if (isBase) {
            for (int y = midY - 1; y >= 0; y--) {
                int placedThisLayer = 0;
                for (int x = 0; x < boundH; x++) {
                    for (int z = 0; z < boundH; z++) {
                        float threshold = heightMap[x][z];
                        if (yVal < threshold) {
                            volume[x][y][z] = blockState;
                            placedThisLayer++;
                        }
                    }
                }
                if (placedThisLayer == 0) break;
                yVal += yValStep;
            }
        } else {
            for (int y = midY; y < boundV; y++) {
                int placedThisLayer = 0;
                for (int x = 0; x < boundH; x++) {
                    for (int z = 0; z < boundH; z++) {
                        float threshold = heightMap[x][z];
                        if (yVal < threshold) {
                            volume[x][y][z] = blockState;
                            placedThisLayer++;
                        }
                    }
                }
                if (placedThisLayer == 0) break;
                yVal += yValStep;
            }
        }

    }
    private static BlockState[][][] applySurfaceRules(BlockState[][][] volume, VoidIslandBiomes.SurfaceRule rule) {
        int sizeX = volume.length;
        int sizeY = volume[0].length;
        int sizeZ = volume[0][0].length;

        BlockState surfaceBlock = rule.topBlock;
        BlockState fillBlock = rule.secondaryBlock.orElse(surfaceBlock);
        int depth = rule.topDepth;
        int modified = 0;

        List<String> traits = new ArrayList<>();
        traits.add("topBlock: " + surfaceBlock);
        if (rule.secondaryBlock.isPresent()) traits.add("secondaryBlock: " + fillBlock);
        traits.add("topDepth: " + depth);

        int totalColumns = sizeX * sizeZ;
        int columnsProcessed = 0;
        int nextLogStep = 10;

        for (int x = 0; x < sizeX; x++) {
            for (int z = 0; z < sizeZ; z++) {
                int remainingDepth = depth;

                for (int y = sizeY - 1; y >= 0; y--) {
                    BlockState current = volume[x][y][z];
                    if (current == null || current.isAir()) {
                        remainingDepth = depth;
                        continue;
                    }

                    if (remainingDepth > 0) {
                        BlockState target = (remainingDepth == depth) ? surfaceBlock : fillBlock;
                        if (current != target) {
                            volume[x][y][z] = target;
                            modified++;
                        }
                        remainingDepth--;
                    } else {
                        break; // Done filling this column
                    }
                }

                // Progress Logging
                columnsProcessed++;
                int progressPercent = (columnsProcessed * 100) / totalColumns;
                if (progressPercent >= nextLogStep) {
                    System.out.println("[VoidIsland] Surface rule progress: " + progressPercent);
                    nextLogStep += 10;
                }
            }
        }

        System.out.println("[VoidIsland] Surface rule modified " + modified + " blocks using traits: " + String.join(", ", traits));
        return volume;
    }

    private static BlockState[][][] trimVolumeWithMargin(BlockState[][][] volume, int margin) {
        int sizeX = volume.length;
        int sizeY = volume[0].length;
        int sizeZ = volume[0][0].length;

        int minX = sizeX, maxX = -1;
        int minY = sizeY, maxY = -1;
        int minZ = sizeZ, maxZ = -1;

        // Scan bounds of non-null blocks
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    if (volume[x][y][z] != null && !volume[x][y][z].isAir()) {
                        if (x < minX) minX = x;
                        if (y < minY) minY = y;
                        if (z < minZ) minZ = z;
                        if (x > maxX) maxX = x;
                        if (y > maxY) maxY = y;
                        if (z > maxZ) maxZ = z;
                    }
                }
            }
        }

        // Add margin, clamp to bounds
        minX = Math.max(0, minX - margin);
        minY = Math.max(0, minY - margin);
        minZ = Math.max(0, minZ - margin);
        maxX = Math.min(sizeX - 1, maxX + margin);
        maxY = Math.min(sizeY - 1, maxY + margin);
        maxZ = Math.min(sizeZ - 1, maxZ + margin);

        int newSizeX = maxX - minX + 1;
        int newSizeY = maxY - minY + 1;
        int newSizeZ = maxZ - minZ + 1;

        BlockState[][][] trimmed = new BlockState[newSizeX][newSizeY][newSizeZ];

        for (int x = 0; x < newSizeX; x++) {
            for (int y = 0; y < newSizeY; y++) {
                System.arraycopy(volume[minX + x][minY + y], minZ, trimmed[x][y], 0, newSizeZ);
            }
        }

        System.out.printf("[VoidIsland] Trimmed volume to [%d, %d, %d]%n", newSizeX, newSizeY, newSizeZ);
        return trimmed;
    }

    // -- SETUP ORES -- //

    private static final List<BlockState> REPLACEABLE_STONE = List.of(
            EchoesBlocks.UMBRELITH.get().defaultBlockState(),
            EchoesBlocks.VERLITH.get().defaultBlockState(),
            EchoesBlocks.TEALITE.get().defaultBlockState(),
            EchoesBlocks.PALESTONE.get().defaultBlockState()
    );
    private static final List<OreVeinConfig> STONE_VEINS = List.of(
            vanilla (EchoesBlocks.UMBRELITH.get().defaultBlockState(), 3.0f, 32,64),
            vanilla (EchoesBlocks.VERLITH  .get().defaultBlockState(), 3.0f, 32,64),
            vanilla (EchoesBlocks.TEALITE  .get().defaultBlockState(), 2.0f, 16,32),
            vanilla (EchoesBlocks.PALESTONE.get().defaultBlockState(), 2.0f, 16,32)
    );

    private static final List<OreVeinConfig> ORE_VEINS = List.of(
             vanilla (EchoesBlocks.UMBRITE_ORE   .get().defaultBlockState(), 6.0f, 10,18),
             vanilla (EchoesBlocks.VERDANTINE_ORE.get().defaultBlockState(), 6.0f, 8, 16),
             vanilla (EchoesBlocks.CHLORIUM_ORE  .get().defaultBlockState(), 5.0f, 8, 12),
             vanilla (EchoesBlocks.NERON_ORE     .get().defaultBlockState(), 4.0f, 6, 10),
             vanilla (EchoesBlocks.GLARIUM_ORE   .get().defaultBlockState(), 5.0f, 8, 14),
             vanilla (EchoesBlocks.AZURETINE_ORE .get().defaultBlockState(), 3.0f, 5, 8),
             vanilla (EchoesBlocks.XIRIUM_ORE    .get().defaultBlockState(), 3.0f, 5, 8),
             vanilla (EchoesBlocks.PEARLUM_ORE   .get().defaultBlockState(), 2.0f, 4, 8),
             vanilla (EchoesBlocks.PERIALIGHT_ORE.get().defaultBlockState(), 1.0f, 2, 6),
            vanilla (EchoesBlocks.VIOLUM_DEPOSIT.get().defaultBlockState(), 0.3f, 1, 4)
    );
    public enum VeinType {
        VANILLA_STYLE {
            @Override
            public VeinParams parseParams(float... args) {
                if (args.length < 2) throw new IllegalArgumentException("VANILLA_STYLE vein requires min and max steps");
                return new BlockCountParams((int) args[0], (int) args[1]);
            }
        };
        public abstract VeinParams parseParams(float... args);
    }
    public static OreVeinConfig vanilla(BlockState ore, float triesPerChunk, int minBlocks, int maxBlocks) {
        return new OreVeinConfig(ore, triesPerChunk, VeinType.VANILLA_STYLE, VeinType.VANILLA_STYLE.parseParams(minBlocks, maxBlocks));
    }
    public sealed interface VeinParams permits BlockCountParams {}
    public record BlockCountParams(int minBlocks, int maxBlocks) implements VeinParams {}
    public record OreVeinConfig(BlockState ore, float triesPerChunk, VeinType type, VeinParams params) {}

    // -- PLACE ORES -- //

    private static final int BASE_CHUNK_VOLUME = 16 * 64 * 16;
    private static void oreGen(BlockState[][][] volume, RandomSource random, @Nullable List<OreVeinConfig> oreList) {
        if (oreList == null || oreList.isEmpty()) return;

        final int sizeX = volume.length;
        final int sizeY = volume[0].length;
        final int sizeZ = volume[0][0].length;
        final int volumeTotal = sizeX * sizeY * sizeZ;

        final Set<BlockState> replaceable = new HashSet<>(REPLACEABLE_STONE);
        final Map<String, int[]> oreStats = new LinkedHashMap<>(); // [0] = placed, [1] = skipped

        for (OreVeinConfig config : oreList) {
            BlockState ore = config.ore();
            String name = ore.getBlock().getName().getString();
            oreStats.putIfAbsent(name, new int[2]);

            float attemptsRaw = (volumeTotal / (float) BASE_CHUNK_VOLUME) * config.triesPerChunk();
            int attempts = (int) attemptsRaw + (random.nextFloat() < (attemptsRaw % 1f) ? 1 : 0);

            boolean variableSize = config.params() instanceof BlockCountParams;

            System.out.println("[OreGen] Placing " + name + " (" + attempts + " times)");
            for (int attempt = 0; attempt < attempts; attempt++) {
                int originX = random.nextInt(sizeX);
                int originY = random.nextInt(sizeY);
                int originZ = random.nextInt(sizeZ);

                BlockState originState = volume[originX][originY][originZ];
                if (originState == null || originState.isAir()) continue;

                int veinSize = 1;
                if (variableSize) {
                    BlockCountParams params = (BlockCountParams) config.params();
                    veinSize = Mth.randomBetweenInclusive(random, params.minBlocks, params.maxBlocks);
                }

                float angle = random.nextFloat() * (float) Math.PI;
                float halfSize = veinSize / 8f;
                double sin = Math.sin(angle), cos = Math.cos(angle);

                double x1 = originX + sin * halfSize;
                double x2 = originX - sin * halfSize;
                double z1 = originZ + cos * halfSize;
                double z2 = originZ - cos * halfSize;
                double y1 = originY + random.nextInt(3) - 1;
                double y2 = originY + random.nextInt(3) - 1;

                for (int i = 0; i < veinSize; i++) {
                    float f = (float) i / veinSize;
                    double px = Mth.lerp(f, x1, x2);
                    double py = Mth.lerp(f, y1, y2);
                    double pz = Mth.lerp(f, z1, z2);

                    double scale = random.nextDouble() * veinSize / 16.0;
                    double radius = (Math.sin(f * Math.PI) + 1.0) * scale + 1.0;
                    double rHalf = radius / 2.0;

                    int minX = Mth.floor(px - rHalf), maxX = Mth.floor(px + rHalf);
                    int minY = Mth.floor(py - rHalf), maxY = Mth.floor(py + rHalf);
                    int minZ = Mth.floor(pz - rHalf), maxZ = Mth.floor(pz + rHalf);

                    for (int x = minX; x <= maxX; x++) {
                        double dx = (x + 0.5 - px) / rHalf;
                        double dx2 = dx * dx;
                        if (dx2 >= 1.0) continue;

                        for (int y = minY; y <= maxY; y++) {
                            double dy = (y + 0.5 - py) / rHalf;
                            double dy2 = dy * dy;
                            if (dx2 + dy2 >= 1.0) continue;

                            for (int z = minZ; z <= maxZ; z++) {
                                double dz = (z + 0.5 - pz) / rHalf;
                                if (dx2 + dy2 + dz * dz >= 1.0) continue;

                                if (x < 0 || x >= sizeX || y < 0 || y >= sizeY || z < 0 || z >= sizeZ) continue;

                                BlockState current = volume[x][y][z];
                                if (current == ore) continue; // already placed
                                if (current != null && replaceable.contains(current)) {
                                    volume[x][y][z] = ore;
                                    oreStats.get(name)[0]++; // placed
                                } else {
                                    oreStats.get(name)[1]++; // skipped
                                }
                            }
                        }
                    }
                }
            }
        }

        // Print final stats
        System.out.println("[OreGen] Final Placement Summary:");
        for (Map.Entry<String, int[]> entry : oreStats.entrySet()) {
            int placed = entry.getValue()[0], skipped = entry.getValue()[1];
            System.out.printf("  %-20s | Placed: %,7d | Skipped: %,7d | Total Attempts: %,7d%n",
                    entry.getKey(), placed, skipped, placed + skipped);
        }
    }

    private static List<BlockToPlace> exportBlockVolume(BlockState[][][] volume, BlockPos origin, int boundV, int boundH) {
        System.out.println("[VoidIsland - exportBlockVolume] export list fetching...");
        List<BlockToPlace> blocks = new ArrayList<>();

        if (volume == null) {
            System.err.println("[VoidIsland - exportBlockVolume] Volume is null!");
            return blocks;
        }

        int sizeX = volume.length;
        int sizeY = volume[0].length;
        int sizeZ = volume[0][0].length;

        int ox = origin.getX() - boundH / 2;
        int oy = origin.getY() - boundV / 2;
        int oz = origin.getZ() - boundH / 2;

        int totalVoxels = sizeX * sizeY * sizeZ;
        int logInterval = totalVoxels / 10;
        int processed = 0;
        int nextLog = logInterval;

        System.out.println("[VoidIsland] exporting...");

        try {
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (int z = 0; z < sizeZ; z++) {
                        processed++;
                        if (processed >= nextLog) {
                            int percent = (int)(((float)(processed * 100)) / (float)(totalVoxels));
                            System.out.println("[VoidIsland] Export progress: " + percent + "% (" + processed + " / " + totalVoxels + ")");
                            nextLog += logInterval;
                        }

                        BlockState state = volume[x][y][z];
                        if (state != null && !state.isAir()) {
                            blocks.add(new BlockToPlace(new BlockPos(ox + x, oy + y, oz + z), state));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[VoidIsland] ERROR during export: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("[VoidIsland] Exported " + blocks.size() + " blocks!");
        return blocks;
    }

}