package net.tarlan.echoes.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.tarlan.echoes.recipe.UmbrelithFurnaceRecipe;

public class UmbrelithFurnaceFuelSlot extends SlotItemHandler {
    private static Container emptyInventory = new SimpleContainer(0);
    private final IItemHandler itemHandler;
    private final int index;

    public UmbrelithFurnaceFuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }
    protected boolean isFuel(ItemStack pStack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, UmbrelithFurnaceRecipe.Type.INSTANCE) > 0;
    }
    public boolean mayPlace(ItemStack pStack) {
        return this.isFuel(pStack) || isBucket(pStack);
    }

    public int getMaxStackSize(ItemStack pStack) {
        return isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack pStack) {
        return pStack.is(Items.BUCKET);
    }
}
