package net.tarlan.echoes.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.worldgen.EchoesPlacedFeatures;

public class EchoesBiomes {
    public static final ResourceKey<Biome> BARREN_EXPANSE = ResourceKey.create(Registries.BIOME,new ResourceLocation(Echoes.MOD_ID, "barren_expanse"));
    public static final ResourceKey<Biome> COBALT_SHIMMER_FOREST = ResourceKey.create(Registries.BIOME,new ResourceLocation(Echoes.MOD_ID, "cobalt_shimmer_forest"));
    public static final ResourceKey<Biome> LLERAE_FOREST = ResourceKey.create(Registries.BIOME,new ResourceLocation(Echoes.MOD_ID, "llerae_forest"));

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(BARREN_EXPANSE, barrenExpanse(context));
        context.register(COBALT_SHIMMER_FOREST, cobaltShimmerdownForest(context));
        context.register(LLERAE_FOREST, lleraeForest(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome barrenExpanse(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 1, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.COBALT_SHIMMERDOWN_PLACED_FEATURE);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.LLERAE_PLACED_FEATURE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x000000)
                        .waterFogColor(0x000000)
                        .skyColor(0x000000)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .fogColor(0x000000).build())
                .build();
    }
    public static Biome cobaltShimmerdownForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 1, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.COBALT_SHIMMERDOWN_PLACED_FEATURE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.LLERAE_PLACED_FEATURE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x000000)
                        .waterFogColor(0x000000)
                        .skyColor(0x000000)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .fogColor(0x000000).build())
                .build();
    }
    public static Biome lleraeForest(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 1, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.COBALT_SHIMMERDOWN_PLACED_FEATURE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EchoesPlacedFeatures.LLERAE_PLACED_FEATURE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x000000)
                        .waterFogColor(0x000000)
                        .skyColor(0x000000)
                        .grassColorOverride(0x000000)
                        .foliageColorOverride(0x000000)
                        .fogColor(0x000000).build())
                .build();
    }
}