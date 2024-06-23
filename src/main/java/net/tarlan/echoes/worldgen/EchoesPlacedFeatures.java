package net.tarlan.echoes.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.EchoesBlocks;

import java.util.List;

public class EchoesPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LLERAE_PLACED_FEATURE = createKey("llerae_placed");
    public static final ResourceKey<PlacedFeature> COBALT_SHIMMERDOWN_PLACED_FEATURE = createKey("cobalt_shimmerdown_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LLERAE_PLACED_FEATURE, configuredFeatures.getOrThrow(EchoesConfiguredFeatures.LLERAE_FEATURE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), EchoesBlocks.LLERAE_SAPLING.get()));

        register(context, COBALT_SHIMMERDOWN_PLACED_FEATURE, configuredFeatures.getOrThrow(EchoesConfiguredFeatures.COBALT_SHIMMERDOWN_FEATURE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), EchoesBlocks.COBALT_SHIMMERDOWN_SAPLING.get()));
    }




    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Echoes.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
