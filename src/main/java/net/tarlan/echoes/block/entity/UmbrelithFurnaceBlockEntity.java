package net.tarlan.echoes.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tarlan.echoes.recipe.UmbrelithFurnaceRecipe;
import net.tarlan.echoes.screen.UmbrelithFurnaceMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UmbrelithFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    /**
     * Set constructor.
     */
    public UmbrelithFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(EchoesBlockEntities.UMBRELITH_FURNACE_BLOCK_ENTITY.get(), pPos, pBlockState);
        //this.quickCheck = RecipeManager.createCheck((RecipeType)pRecipeType);
        //this.recipeType = pRecipeType;
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(6);
    private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT_0 = 2;
    private static final int OUTPUT_SLOT_1 = 3;
    private static final int OUTPUT_SLOT_2 = 4;
    private static final int OUTPUT_SLOT_3 = 5;
    //private final RecipeType<SmeltingRecipe> recipeType;
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private int progress = 0;
    private int maxProgress = 200;
    private int remainingFuelTime = 0;
    private int maxFuelTime = 100;

    protected final ContainerData data = new ContainerData() {
        public int get(int pIndex) {
            switch (pIndex) {
                case 0:
                    return UmbrelithFurnaceBlockEntity.this.progress;
                case 1:
                    return UmbrelithFurnaceBlockEntity.this.maxProgress;
                case 2:
                    return UmbrelithFurnaceBlockEntity.this.remainingFuelTime;
                case 3:
                    return UmbrelithFurnaceBlockEntity.this.maxFuelTime;
                default:
                    return 0;
            }
        }

        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0:
                    UmbrelithFurnaceBlockEntity.this.progress = pValue;
                    break;
                case 1:
                    UmbrelithFurnaceBlockEntity.this.maxProgress = pValue;
                    break;
                case 2:
                    UmbrelithFurnaceBlockEntity.this.remainingFuelTime = pValue;
                    break;
                case 3:
                    UmbrelithFurnaceBlockEntity.this.maxFuelTime = pValue;
                    break;
            }
        }

        public int getCount() {
            return 4;
        }
    };

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    //private final RecipeManager.CachedCheck<Container, ? extends EchoesBaseSmeltingRecipe> quickCheck;

    /**
     * Find and add inventory contents to drop pool.
     */
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        ;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    /**
     * Set & get handler capabilities of this block entity.
     */
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    /**
     * Set display name
     */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.echoes.umbrelith_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new UmbrelithFurnaceMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    /**
     * Save inventory on world save.
     */
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("umbrelith_furnace.progress", progress);
        pTag.putInt("umbrelith_furnace.fuelTimeLeft", remainingFuelTime);
        pTag.putInt("umbrelith_furnace.maxProgress", maxProgress);
        pTag.putInt("umbrelith_furnace.maxFuelTime", maxFuelTime);
        super.saveAdditional(pTag);
    }

    /**
     * Load inventory on world load.
     */
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("umbrelith_furnace.progress");
        remainingFuelTime = pTag.getInt("umbrelith_furnace.fuelTimeLeft");
        maxProgress = pTag.getInt("umbrelith_furnace.maxProgress");
        maxFuelTime = pTag.getInt("umbrelith_furnace.maxFuelTime");
    }
    private boolean isLit() {
        return this.remainingFuelTime > 0;
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        boolean burning = this.isLit();
        boolean hasFuel = !itemHandler.getStackInSlot(FUEL_SLOT).isEmpty();
        boolean hasInputs = !itemHandler.getStackInSlot(INPUT_SLOT).isEmpty();
        boolean stateChanged = false;

        // Decrement remaining fuel time only if the furnace is burning
        if (remainingFuelTime > 0) {
            remainingFuelTime--;
        }

        // If the furnace is not burning, and it has fuel, start burning
        if ((remainingFuelTime == 0) && hasFuel && (canCraft())) {
            ItemStack fuelStack = itemHandler.getStackInSlot(FUEL_SLOT);
            maxFuelTime = getBurnDuration(fuelStack);
            remainingFuelTime = maxFuelTime;

            fuelStack.shrink(1);
            stateChanged = true;
        }
        if (remainingFuelTime > 0) {
            // If the furnace is burning and a recipe is valid, handle progress
            if (canCraft()) {
                int outputSlot = getFirstAvailableOutputSlot();
                maxProgress = getSmeltTime();
                progress++;
                if (progress == maxProgress) {
                    progress = 0;
                    craftToSlot(outputSlot);
                    stateChanged = true;
                }
            } else {
                progress = Mth.clamp(progress - 1, 0, maxProgress);
            }
        } else {
            progress = 0;
        }

        if (burning != this.isLit()) {
            stateChanged = true;
            pState = pState.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isLit()));
            pLevel.setBlock(pPos, pState, 3);
        }
        // If state has changes, update furnace
        if (stateChanged) {
            setChanged(pLevel, pPos, pState);
        }
    }
    private int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty()) {
            return 0;
        } else {
            Item item = pFuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pFuel, new UmbrelithFurnaceRecipe.Type());
        }
    }
    private int getSmeltTime() {
        Optional<UmbrelithFurnaceRecipe> recipe = getCurrentRecipe();
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        return recipe.map(UmbrelithFurnaceRecipe::getSmeltTime).orElse(200);
    }
    //private static int getSmeltTime(Level pLevel, AbstractFurnaceBlockEntity pBlockEntity) {
    //    return pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    //}
    private Optional<UmbrelithFurnaceRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(UmbrelithFurnaceRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canCraft() {
        Optional<UmbrelithFurnaceRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);
        if (((itemHandler.getStackInSlot(OUTPUT_SLOT_0).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_0).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_0).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_0).getMaxStackSize()))) {
            return true;
        } else if (((itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_1).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()))) {
            return true;
        } else if (((itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_2).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()))) {
            return true;
        } else if (((itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_3).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_3).getMaxStackSize()))) {
            return true;
        } else {
            return false;
        }
    }
    private int getFirstAvailableOutputSlot() {
        Optional<UmbrelithFurnaceRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        if ((itemHandler.getStackInSlot(OUTPUT_SLOT_0).isEmpty() || (itemHandler.getStackInSlot(OUTPUT_SLOT_0).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_0).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_0).getMaxStackSize()))) {
            return 2; // Slot 2 is available, return it.
        } else if ((itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty() || (itemHandler.getStackInSlot(OUTPUT_SLOT_1).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()))) {
            return 3; // Slot 3 is available, return it.
        } else if ((itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty() || (itemHandler.getStackInSlot(OUTPUT_SLOT_2).is(result.getItem())) && (itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() + result.getCount() <= itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()))) {
            return 4; // Slot 4 is available, return it.
        } else {
            return 5; // Slot 5 must be available since this was predicated on one of these slots being available, so return it.
        }
    }
    private void craftToSlot(int outputSlot) {
        Optional<UmbrelithFurnaceRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
        this.itemHandler.setStackInSlot(outputSlot, new ItemStack(result.getItem(), this.itemHandler.getStackInSlot(outputSlot).getCount() + result.getCount()));
    }
}