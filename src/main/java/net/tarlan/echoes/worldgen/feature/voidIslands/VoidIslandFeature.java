package net.tarlan.echoes.worldgen.feature.voidIslands;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VoidIslandFeature extends Feature<NoneFeatureConfiguration> {
    public VoidIslandFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
        WorldGenLevel level = placeContext.level();
        RandomSource random = placeContext.random();
        BlockPos origin = placeContext.origin();

        // You can hardcode a specific biome ID here,
        // or pass null to let the generator pick a random one.
        String biomeId = "shimmer_forest"; // or null

        //VoidIslandGenerator.generateVoidIsland(level, origin, random, null, null);
        VoidIslandGenerator.generateVoidIsland(level, origin, random, null, null);
        return true;
    }
}
