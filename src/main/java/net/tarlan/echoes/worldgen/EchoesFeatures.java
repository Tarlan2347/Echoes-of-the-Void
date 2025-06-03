package net.tarlan.echoes.worldgen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.worldgen.feature.voidIslands.VoidIslandFeature;
import net.tarlan.echoes.worldgen.tree.EchoesShimmerdownTreeFeature;

public class EchoesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Echoes.MOD_ID);
    private static final RegistryObject<VoidIslandFeature> VOID_ISLAND_FEATURE = FEATURES.register("void_island", () -> new VoidIslandFeature(NoneFeatureConfiguration.CODEC));
    //private static final RegistryObject<EchoesShimmerdownTreeFeature> SHIMMERDOWN_TREE = FEATURES.register("shimmerdown_tree", () -> new EchoesShimmerdownTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<EchoesShimmerdownTreeFeature> SHIMMERDOWN_TREE = FEATURES.register("shimmerdown_tree", () -> new EchoesShimmerdownTreeFeature(NoneFeatureConfiguration.CODEC)
    );
}
