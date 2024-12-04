package net.tarlan.echoes.recipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.screen.UmbrelithFurnaceMenu;

public class EchoesRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Echoes.MOD_ID);

    public static final RegistryObject<RecipeSerializer<UmbrelithFurnaceRecipe>> UMBRELITH_FURNACE_SERIALIZER =
            SERIALIZERS.register("umbrelith_furnace", () -> UmbrelithFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}