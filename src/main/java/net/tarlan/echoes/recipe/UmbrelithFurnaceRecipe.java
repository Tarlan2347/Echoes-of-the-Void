package net.tarlan.echoes.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class UmbrelithFurnaceRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int smeltTime;

    public UmbrelithFurnaceRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id, int smeltTime) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.smeltTime = smeltTime;
    }
    public int getSmeltTime() {
        return this.smeltTime;
    }

    @Override public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        } else {
            return inputItems.get(0).test(pContainer.getItem(1));
        }
    }
    @Override public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }
    @Override public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }
    @Override public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }
    @Override public ResourceLocation getId() {
        return id;
    }
    @Override public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }
    public static class Serializer implements RecipeSerializer<UmbrelithFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        /** ** ** **/
        @Override public UmbrelithFurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            int smeltTime = GsonHelper.getAsInt(pSerializedRecipe, "smeltTime", 200);
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new UmbrelithFurnaceRecipe(inputs, output, pRecipeId, smeltTime);
        }
        /** ** ** **/

        @Override public @Nullable UmbrelithFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int _i = pBuffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(_i, Ingredient.EMPTY);
            int smeltTime = pBuffer.readInt();

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output = pBuffer.readItem();
            return new UmbrelithFurnaceRecipe(inputs, output, pRecipeId, smeltTime);
        }
        @Override public void toNetwork(FriendlyByteBuf pBuffer, UmbrelithFurnaceRecipe pRecipe) {
            pBuffer.writeVarInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeItem(pRecipe.getResultItem(null));
        }
    }
    @Override public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    public static class Type implements RecipeType<UmbrelithFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "umbrelith_furnace";
    }
}
