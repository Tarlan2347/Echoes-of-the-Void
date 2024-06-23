package net.tarlan.echoes.worldgen.dimension;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.tarlan.echoes.Echoes;

import java.util.OptionalLong;

public class EchoesDimensionTypes extends DimensionTypes {
    public static final ResourceKey<LevelStem> THE_VOID = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Echoes.MOD_ID, "the_void"));
    public static final ResourceKey<Level> THE_VOID_LEVEL = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Echoes.MOD_ID, "the_void"));
    public static final ResourceKey<DimensionType> THE_VOID_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Echoes.MOD_ID, "the_void"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(EchoesDimensionTypes.THE_VOID_TYPE, new DimensionType(OptionalLong.of(18000), false, false, false, false, 32.0D, true, true, -1280, 2560, 1280, BlockTags.INFINIBURN_END, BuiltinDimensionTypes.OVERWORLD_EFFECTS, 0.0F, new DimensionType.MonsterSettings(true, false, UniformInt.of(0, 7), 0)));
        }
}
