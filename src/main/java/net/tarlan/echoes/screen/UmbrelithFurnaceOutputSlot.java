package net.tarlan.echoes.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.tarlan.echoes.recipe.UmbrelithFurnaceRecipe;

public class UmbrelithFurnaceOutputSlot extends SlotItemHandler {
    private static Container emptyInventory = new SimpleContainer(0);
    private final IItemHandler itemHandler;
    private final int index;

    public UmbrelithFurnaceOutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }
}
