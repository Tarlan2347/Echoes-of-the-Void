package net.tarlan.echoes.worldgen.feature.voidIslands;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.tarlan.echoes.block.EchoesBlocks;

import java.util.*;

public class VoidIslandBiomes {

    // --- SurfaceRule: defines top block and optional secondary layer ---
    public static class SurfaceRule {
        public final BlockState topBlock;
        public final int topDepth;
        public final Optional<BlockState> secondaryBlock;
        public final Optional<Integer> secondaryDepth;

        public SurfaceRule(BlockState topBlock, int topDepth) {
            this.topBlock = topBlock;
            this.topDepth = topDepth;
            this.secondaryBlock = Optional.empty();
            this.secondaryDepth = Optional.empty();
        }

        public SurfaceRule(BlockState topBlock, int topDepth, BlockState secondaryBlock, int secondaryDepth) {
            this.topBlock = topBlock;
            this.topDepth = topDepth;
            this.secondaryBlock = Optional.of(secondaryBlock);
            this.secondaryDepth = Optional.of(secondaryDepth);
        }
    }

    // --- Biome Preset ---
    public static class VoidBiome {
        public final String id;
        public final SurfaceRule surfaceRule;

        public VoidBiome(String id, SurfaceRule surfaceRule) {
            this.id = id;
            this.surfaceRule = surfaceRule;
        }
    }

    // --- Registry ---
    private static final Map<String, VoidBiome> BIOME_MAP = new HashMap<>();
    private static final List<VoidBiome> BIOME_LIST = new ArrayList<>();

    // --- Static Init ---
    static {
        register(new VoidBiome(
                "shimmer_forest",
                new SurfaceRule(
                        EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState(),
                        1 // depth
                )
        ));
        register(new VoidBiome(
                "llerae",
                new SurfaceRule(
                        EchoesBlocks.UMBRELITH_LLERAE_MOSS.get().defaultBlockState(),
                        1 // depth
                )
        ));
        register(new VoidBiome(
                "barren",
                new SurfaceRule(
                        EchoesBlocks.UMBRELITH.get().defaultBlockState(),
                        1 // depth
                )
        ));

        // Future: shimmer_jungle, shimmer_plains, llerae variants, etc.
    }

    private static void register(VoidBiome biome) {
        BIOME_MAP.put(biome.id, biome);
        BIOME_LIST.add(biome);
    }

    // --- Access Methods ---

    public static Optional<VoidBiome> get(String id) {
        return Optional.ofNullable(BIOME_MAP.get(id));
    }

    public static VoidBiome getRandom(RandomSource random) {
        return BIOME_LIST.get(random.nextInt(BIOME_LIST.size()));
    }

    // --- Used by /voidIsland command ---
    public static VoidBiome resolve(String idOrNull, RandomSource random) {
        if (idOrNull == null || idOrNull.isEmpty()) {
            return getRandom(random);
        }
        return get(idOrNull).orElseGet(() -> getRandom(random));
    }

    public static Collection<String> getBiomeIds() {
        return List.copyOf(BIOME_MAP.keySet());
    }

}
