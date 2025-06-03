package net.tarlan.echoes.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.EchoesBlocks;

public class EchoesConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LLERAE_FEATURE = registerKey("llerae");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_SHIMMERDOWN_FEATURE = registerKey("cobalt_shimmerdown");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_TUFT_PATCH_FEATURE = registerKey("cobalt_tuft_patch");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> VOID_ISLAND_KEY = registerKey("void_island");
    //public static Feature<NoneFeatureConfiguration> VOID_ISLAND;
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, LLERAE_FEATURE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(EchoesBlocks.LLERAE_LOG.get()),
                new FancyTrunkPlacer(5, 15, 0),
                BlockStateProvider.simple(EchoesBlocks.LLERAE_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                new TwoLayersFeatureSize(2, 0, 8)).dirt(BlockStateProvider.simple(EchoesBlocks.LLERAE_LOG.get())).build());

        //register(context, COBALT_SHIMMERDOWN_FEATURE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
        //        BlockStateProvider.simple(EchoesBlocks.SHIMMERDOWN_LOG.get()),
        //        new CherryTrunkPlacer(6, 3, 2, UniformInt.of(0,1),UniformInt.of(0,1),UniformInt.of(0,1),UniformInt.of(0,1)),
        //        BlockStateProvider.simple(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get()),
        //        new CherryFoliagePlacer(ConstantInt.of(6), ConstantInt.of(0), ConstantInt.of(4), 0.5f, 0.8f, 4f, 4f),
        //        new TwoLayersFeatureSize(2, 0, 8)).dirt(BlockStateProvider.simple(EchoesBlocks.SHIMMERDOWN_LOG.get())).build());

        register(context, COBALT_SHIMMERDOWN_FEATURE, EchoesFeatures.SHIMMERDOWN_TREE.get(), NoneFeatureConfiguration.INSTANCE);


        //register(context, VOID_ISLAND_KEY, VOID_ISLAND, NoneFeatureConfiguration.INSTANCE);
        //register(context, VOID_ISLAND_KEY, VOID_ISLAND,new VoidIslandFeature(NoneFeatureConfiguration.CODEC));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Echoes.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
