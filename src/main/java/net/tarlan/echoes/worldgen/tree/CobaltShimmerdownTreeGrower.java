package net.tarlan.echoes.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.tarlan.echoes.worldgen.EchoesConfiguredFeatures;

import javax.annotation.Nullable;

public class CobaltShimmerdownTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return EchoesConfiguredFeatures.COBALT_SHIMMERDOWN_FEATURE;
    }
}
