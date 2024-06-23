package net.tarlan.echoes.item.custom;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class EchoesFoods {
    public static final FoodProperties LLERAE_FRUIT = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).fast().build();
    public static final FoodProperties COBALT_TUFT_CONE = new FoodProperties.Builder().nutrition(4).saturationMod(0.1f).build();
    public static final FoodProperties MOSSBERRY = new FoodProperties.Builder().nutrition(3).saturationMod(0.5f).build();
    public static final FoodProperties BAKED_LLERAE_FRUIT = new FoodProperties.Builder().nutrition(6).saturationMod(0.3f).fast().build();
    public static final FoodProperties COBALT_SHIMMER_FLOWER = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).alwaysEat().fast().effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 60,1), 1).build();
    public static final FoodProperties SHIMMER_STUFFED_LLERAE = new FoodProperties.Builder().nutrition(4).saturationMod(0.6f).alwaysEat().effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600,1), 1).build();
}
