package net.tarlan.echoes.screen;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.block.entity.UmbrelithFurnaceBlockEntity;
import net.tarlan.echoes.recipe.UmbrelithFurnaceRecipe;

public class UmbrelithFurnaceMenu extends AbstractContainerMenu {
    public final UmbrelithFurnaceBlockEntity umbrelithFurnaceBlockEntity;
    private final Level level;
    private final ContainerData data;

    public UmbrelithFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(6));
    }

    public UmbrelithFurnaceMenu(int pContainerId, Inventory inv, BlockEntity pEntity, ContainerData containerData) {
        super(EchoesMenuTypes.UMBRELITH_FURNACE_MENU.get(), pContainerId);
        checkContainerSize(inv, 6);
        umbrelithFurnaceBlockEntity = (((UmbrelithFurnaceBlockEntity) pEntity));
        this.level = inv.player.level();
        this.data = containerData;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.umbrelithFurnaceBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new UmbrelithFurnaceFuelSlot(iItemHandler, 0, 16, 32));
            this.addSlot(new SlotItemHandler(iItemHandler, 1, 48, 32));
            this.addSlot(new UmbrelithFurnaceOutputSlot(iItemHandler, 2, 118, 23));
            this.addSlot(new UmbrelithFurnaceOutputSlot(iItemHandler, 3, 136, 23));
            this.addSlot(new UmbrelithFurnaceOutputSlot(iItemHandler, 4, 118, 41));
            this.addSlot(new UmbrelithFurnaceOutputSlot(iItemHandler, 5, 136, 41));
        });
        addDataSlots(containerData);
    }

    public int getSmeltingProgress() {
        int progress = this.data.get(0);  // Current Progress
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowLength = 45; // Arrow Size

        return maxProgress != 0 && progress != 0 ? progress * progressArrowLength / maxProgress : 0;
    }
    public int getFuelProgress() {
        int i = this.data.get(3);
        if (i == 0) {
            i = 200;
        }

        return this.data.get(2) * 16 / i;
    }
    public int getTotalFueltime() {
        return this.data.get(3);
    }public int getRemainingFueltime() {
        return this.data.get(2);
    }
    public int getTotalFuelInSlot() {
        return umbrelithFurnaceBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .map(handler -> {
                    ItemStack stack = handler.getStackInSlot(0); // Slot 0 = fuel
                    int burnTimePerItem = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
                    return burnTimePerItem * stack.getCount();
                })
                .orElse(0);
    }

    public boolean isBurning() {
        return this.data.get(2) > 0;
    }

    /** CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
     * must assign a slot number to each of the slots used by the GUI.
     * For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
     * Each time we add a Slot to the container, it automatically increases the slotIndex, which means
     * 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
     * 9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
     * 36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
     */
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 6;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, umbrelithFurnaceBlockEntity.getBlockPos()), pPlayer, EchoesBlocks.UMBRELITH_FURNACE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for(int i = 0; i < 3; ++i) {
            for(int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
