package net.tarlan.echoes.block.custom.shimmer_biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.util.EchoesTags;
import net.tarlan.echoes.worldgen.EchoesConfiguredFeatures;

public class CobaltJungleMoss extends Block implements BonemealableBlock {

    public CobaltJungleMoss(Properties pProperties) {
        super(pProperties);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Prevent loading unloaded chunks when spreading.
        if (!level.isAreaLoaded(pos, 1)) return;
        growIsland(level, pos, random);
        suffocate(level, pos, random, state);
        spreadMoss(level, pos, random, state,8);
        if (random.nextInt(20) == 0) { // 1/50 chance to grow new stuff
            placeFeatures(level, random, pos);
        }
        growLayers(level, pos, random, state);
    }
    private void growLayers(Level level, BlockPos pos, RandomSource random, BlockState state) {
        if (!canBeCobaltMoss(level, pos, state) || random.nextInt(150) != 0) return;

        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        Block cobaltMossLayer = EchoesBlocks.COBALT_MOSS_LAYER.get();

        if (aboveState.is(cobaltMossLayer)) {
            int layers = aboveState.getValue(CobaltMossLayer.MOSS_LAYERS);

            if (layers < CobaltMossLayer.MAX_MOSS_LAYERS && random.nextInt(100) < 10) {
                BlockState updated = aboveState.setValue(CobaltMossLayer.MOSS_LAYERS, layers + 1);
                level.setBlockAndUpdate(abovePos, updated);
            }

        } else if (aboveState.isAir()) {
            BlockState newLayer = EchoesBlocks.COBALT_MOSS_LAYER.get().defaultBlockState().setValue(CobaltMossLayer.MOSS_LAYERS, 1);
            level.setBlockAndUpdate(abovePos, newLayer);
        }
    }


    private void suffocate(Level level, BlockPos pos, RandomSource random, BlockState state) {
        // Update on random tick, replacing this block with its blank variant if unable to support moss.
        if (!canBeCobaltMoss(level, pos, state)) {
            if (this.defaultBlockState().is(EchoesBlocks.UMBRELITH_COBALT_MOSS.get())) {
                level.setBlockAndUpdate(pos, EchoesBlocks.UMBRELITH.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.VERLITH_COBALT_MOSS.get())) {
                level.setBlockAndUpdate(pos, EchoesBlocks.VERLITH.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.TEALITE_COBALT_MOSS.get())) {
                level.setBlockAndUpdate(pos, EchoesBlocks.TEALITE.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.PALESTONE_COBALT_MOSS.get())) {
                level.setBlockAndUpdate(pos, EchoesBlocks.PALESTONE.get().defaultBlockState());
            }
        }
    }
    private void spreadMoss(Level level, BlockPos pos, RandomSource random, BlockState state, int reach) {
        BlockPos bestTarget = null;
        double bestDistanceSq = reach * reach;

        for (int i = 0; i < 10; ++i) {
            int dx = random.nextInt(reach * 2 + 1) - reach;
            int dy = random.nextInt(reach * 2 + 1) - reach;
            int dz = random.nextInt(reach * 2 + 1) - reach;
            BlockPos candidate = pos.offset(dx, dy, dz);

            BlockState targetState = level.getBlockState(candidate);
            if (canBeMoss(targetState) && canBeCobaltMoss(level, candidate, this.defaultBlockState())) {
                double distanceSq = candidate.distSqr(pos);
                if (distanceSq < bestDistanceSq) {
                    bestTarget = candidate;
                    bestDistanceSq = distanceSq;
                }
            }
        }

        if (bestTarget != null) {
            BlockState targetState = level.getBlockState(bestTarget);
            level.setBlockAndUpdate(bestTarget, beMoss(targetState));
        }
    }

    private boolean canBeMoss(BlockState state) {
        return state.is(EchoesBlocks.UMBRELITH.get()) ||
                state.is(EchoesBlocks.VERLITH.get()) ||
                state.is(EchoesBlocks.TEALITE.get()) ||
                state.is(EchoesBlocks.PALESTONE.get());
    }
    private BlockState beMoss(BlockState state) {
        if (state.is(EchoesBlocks.UMBRELITH.get())) {
            return EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState();
        } else if (state.is(EchoesBlocks.VERLITH.get())) {
            return EchoesBlocks.VERLITH_COBALT_MOSS.get().defaultBlockState();
        } else if (state.is(EchoesBlocks.TEALITE.get())) {
            return EchoesBlocks.TEALITE_COBALT_MOSS.get().defaultBlockState();
        } else if (state.is(EchoesBlocks.PALESTONE.get())) {
            return EchoesBlocks.PALESTONE_COBALT_MOSS.get().defaultBlockState();
        } else {
            return state; // fallback (shouldn’t ever hit)
        }
    }



    private void growIsland(Level level, BlockPos pos, RandomSource random) {
        if (!isSupportedByUmbrelith(level, pos.below(), 2)) return;
        if ((random.nextFloat() > 0.1f)) return;

        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos targetPos = pos.relative(dir);
        BlockState targetState = level.getBlockState(targetPos);

        if (targetState.isAir() || targetState.canBeReplaced()) {
            // Count umbrelith blocks in 3x3 area around target
            int umbrelithCount = 0;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos scanPos = targetPos.offset(dx, 0, dz);
                    if (level.getBlockState(scanPos).is(EchoesBlocks.UMBRELITH.get())) {
                        umbrelithCount++;
                    }
                }
            }

            float spreadChance = umbrelithCount / 9.0f; // scale from 0.0 to 1.0
            if (random.nextFloat() <= spreadChance) {
                BlockPos finalPos = targetPos;
                for (int dy = 1; dy <= 2; dy++) {
                    BlockPos scanPos = targetPos.below(dy);
                    BlockState scanState = level.getBlockState(scanPos);
                    if (scanState.is(EchoesBlocks.UMBRELITH.get()) || scanState.is(EchoesBlocks.UMBRELITH_COBALT_MOSS.get())) {
                        BlockPos candidateAbove = scanPos.above();
                        BlockState above = level.getBlockState(candidateAbove);
                        if (above.isAir() || above.canBeReplaced() || above.is(EchoesBlocks.COBALT_MOSS_LAYER.get())) {
                            finalPos = candidateAbove; // Translate downward
                            break;
                        }
                    }
                }
                level.setBlockAndUpdate(finalPos, EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState());
            }
        }
    }
    private boolean isSupportedByUmbrelith(LevelAccessor level, BlockPos origin, int radius) {
        BlockPos.MutableBlockPos scanPos = new BlockPos.MutableBlockPos();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                scanPos.set(origin.getX() + dx, origin.getY(), origin.getZ() + dz);
                if (level.getBlockState(scanPos).is(EchoesBlocks.UMBRELITH.get())) {
                    return true;
                }
            }
        }
        return false;
    }


    // If the position above the moss block is air, can support moss below it, or does not suffocate, this moss can survive.
    public static boolean canBeCobaltMoss(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (pLevel.getBlockState(pPos.above()).isAir()) {
            return true;
        } else if (blockstate.getBlock().defaultBlockState().is(EchoesTags.Blocks.MOSS_CAN_GROW_UNDER)) {
            return true;
        } else return !blockstate.getBlock().defaultBlockState().isSuffocating(pLevel, pPos);
    }

    // Make sure this block knows it can random tick, even though it might be redundant.
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    // If the block above the moss is free, return true so bonemeal can work.
    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pLevel.getBlockState(pPos.above()).isAir();
    }
    // Bonemeal always succeeds if bonemeal can be used.
    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        // Place moss using the placeMoss() function.
        spreadMoss(pLevel, pPos, pRandom, pState, 4);
        // Place features using the placeFeature() function.
        placeFeatures(pLevel, pRandom, pPos);
    }
    public void placeFeatures(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos) {
        Registry<ConfiguredFeature<?, ?>> registry = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        BlockPos abovePos = pPos.above();
        ChunkGenerator generator = pLevel.getChunkSource().getGenerator();
        if (pLevel.getBlockState(abovePos).canBeReplaced()) {
            placeShimmerForestFLoorCover(pLevel, pRandom, pPos, registry);
            if (FewerThanXBlocks(pLevel, abovePos, 8, 8, 0, 1, EchoesBlocks.SHIMMERDOWN_LOG.get())) {
                tryPlaceFeature(registry, EchoesConfiguredFeatures.COBALT_SHIMMERDOWN_FEATURE, pLevel, generator, pRandom, abovePos, 1f/20f);
            }
        }
    }
    private void placeShimmerForestFLoorCover(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, Registry<ConfiguredFeature<?, ?>> registry) {
        // Don't dynamically place a new tuft population if there is already one.
        if (FewerThanXBlocks(pLevel, pPos, 10, 6, 0, 1, EchoesBlocks.COBALT_TUFT_SMALL.get())) {
            this.place(registry, EchoesConfiguredFeatures.COBALT_TUFT_PATCH_FEATURE, pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
        }
    }

    private boolean FewerThanXBlocks(ServerLevel level, BlockPos pos, int radius, int lessThan, int minY, int maxY, Block block) {
        int count = 0;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx * dx + dz * dz <= radius * radius) {
                    for (int dy = minY; dy <= maxY; dy++) {
                        BlockPos scanPos = pos.offset(dx, dy, dz);
                        BlockState state = level.getBlockState(scanPos);
                        if (state.is(block)) {
                            count++;
                            if (count >= lessThan) {
                                return false; // Too many blocks found
                            }
                        }
                    }
                }
            }
        }
        return true; // Less than the limit
    }


    public void tryPlaceFeature(Registry<ConfiguredFeature<?, ?>> registry, ResourceKey<ConfiguredFeature<?, ?>> key, ServerLevel level, ChunkGenerator gen, RandomSource random, BlockPos pos, float chance) {
        if (random.nextFloat() < chance) {
            place(registry, key, level, gen, random, pos);
        }
    }
    private void place(Registry<ConfiguredFeature<?, ?>> pFeatureRegistry, ResourceKey<ConfiguredFeature<?, ?>> pFeatureKey, ServerLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos) {
        pFeatureRegistry.getHolder(pFeatureKey).ifPresent((featureKey) -> {
            featureKey.value().place(pLevel, pChunkGenerator, pRandom, pPos);
        });
    }
}
