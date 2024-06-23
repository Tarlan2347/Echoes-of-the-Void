package net.tarlan.echoes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.util.EchoesTags;
import net.tarlan.echoes.worldgen.EchoesConfiguredFeatures;

public class EchoesCobaltMossBlock extends Block implements BonemealableBlock {

    public EchoesCobaltMossBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        // Update on random tick, replacing this block with its blank variant if unable to support moss.
        if (!canBeCobaltMoss(pLevel, pPos, pState)) {
            if (this.defaultBlockState().is(EchoesBlocks.UMBRELITH_COBALT_MOSS.get())) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.UMBRELITH.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.VERLITH_COBALT_MOSS.get())) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.VERLITH.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.TEALITE_COBALT_MOSS.get())) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.TEALITE.get().defaultBlockState());
            } else if (this.defaultBlockState().is(EchoesBlocks.PALESTONE_COBALT_MOSS.get())) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.PALESTONE.get().defaultBlockState());
            } else return;
        }
        // Prevent loading unloaded chunks when spreading.
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        // Update on random tick, replacing nearby blocks with its mossy variant if able to support moss.
        if (pLevel.getRawBrightness(pPos.above(), 0) >= 2) {
            for (int i = 0; i < 8; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(4) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(4) - 1);
                if (canBeCobaltMoss(pLevel, blockpos, this.defaultBlockState())) {
                    if (pLevel.getBlockState(blockpos).is(EchoesBlocks.UMBRELITH.get())) {
                        pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState());
                    } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.VERLITH.get())) {
                        pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.VERLITH_COBALT_MOSS.get().defaultBlockState());
                    } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.TEALITE.get())) {
                        pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.TEALITE_COBALT_MOSS.get().defaultBlockState());
                    } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.PALESTONE.get())) {
                        pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.PALESTONE_COBALT_MOSS.get().defaultBlockState());
                    } else return;
                }
            }
        }
    }

    // If the position above the moss block is air, can support moss below it, or does not suffocate, this moss can survive.
    public static boolean canBeCobaltMoss(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        BlockPos blockpos = pPos.above();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (pLevel.getBlockState(pPos.above()).isAir()) {
            return true;
        } else if (blockstate.getBlock().defaultBlockState().is(EchoesTags.Blocks.MOSS_CAN_GROW_UNDER)) {
            return true;
        } else if (!blockstate.getBlock().defaultBlockState().isSuffocating(pLevel, pPos)) {
            return true;
        } else return false;
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
        placeMoss(pLevel, pRandom, pPos);
        // Place features using the placeFeature() function.
        placeFeature (pLevel, pRandom, pPos);
    }

    // When called by the performBonemeal() function, randomly replace nearby blocks with its mossy variant if able to support moss.
    public void placeMoss(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos) {
        for(int i = 0; i < 2; ++i) {
            BlockPos blockpos = pPos.offset(pRandom.nextInt(2) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(2) - 1);
            if (canBeCobaltMoss(pLevel, blockpos, this.defaultBlockState())) {
                if (pLevel.getBlockState(blockpos).is(EchoesBlocks.UMBRELITH.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState());
                } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.VERLITH.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.VERLITH_COBALT_MOSS.get().defaultBlockState());
                } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.TEALITE.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.TEALITE_COBALT_MOSS.get().defaultBlockState());
                } else if (pLevel.getBlockState(blockpos).is(EchoesBlocks.PALESTONE.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.PALESTONE_COBALT_MOSS.get().defaultBlockState());
                } else return;
            }
        }
    }
    // Set up registry link to configured features classes and call the place() function.
    public void placeFeature(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos) {
        Registry<ConfiguredFeature<?, ?>> registry = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        this.place(registry, EchoesConfiguredFeatures.COBALT_TUFT_PATCH_FEATURE, pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
        if (pRandom.nextInt(32) == 0) {
            this.place(registry, EchoesConfiguredFeatures.COBALT_SHIMMERDOWN_BUSH_FEATURE, pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
        }
    }
    // Place a configured feature as called in the performBonemeal() function.
    private void place(Registry<ConfiguredFeature<?, ?>> pFeatureRegistry, ResourceKey<ConfiguredFeature<?, ?>> pFeatureKey, ServerLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos) {
        pFeatureRegistry.getHolder(pFeatureKey).ifPresent((featureKey) -> {
            featureKey.value().place(pLevel, pChunkGenerator, pRandom, pPos);
        });
    }
}
