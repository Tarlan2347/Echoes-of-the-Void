package net.tarlan.echoes.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.item.EchoesItems;
import net.tarlan.echoes.item.custom.FuelBlockItem;

public class WoodBlockSet {
    public final RegistryObject<Block> planks;
    public final RegistryObject<SlabBlock> slab;
    public final RegistryObject<StairBlock> stairs;
    public final RegistryObject<FenceBlock> fence;
    public final RegistryObject<FenceGateBlock> fenceGate;
    public final RegistryObject<DoorBlock> door;
    public final RegistryObject<TrapDoorBlock> trapdoor;
    public final RegistryObject<ButtonBlock> button;
    public final RegistryObject<PressurePlateBlock> pressurePlate;

    public WoodBlockSet(
            RegistryObject<Block> planks,
            RegistryObject<SlabBlock> slab,
            RegistryObject<StairBlock> stairs,
            RegistryObject<FenceBlock> fence,
            RegistryObject<FenceGateBlock> fenceGate,
            RegistryObject<DoorBlock> door,
            RegistryObject<TrapDoorBlock> trapdoor,
            RegistryObject<ButtonBlock> button,
            RegistryObject<PressurePlateBlock> pressurePlate
    ) {
        this.planks = planks;
        this.slab = slab;
        this.stairs = stairs;
        this.fence = fence;
        this.fenceGate = fenceGate;
        this.door = door;
        this.trapdoor = trapdoor;
        this.button = button;
        this.pressurePlate = pressurePlate;
    }

    public void registerItems(DeferredRegister<Item> ITEMS, String name) {
        ITEMS.register(name + "_planks", () ->
                new FuelBlockItem(planks.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_slab", () ->
                new FuelBlockItem(slab.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_stairs", () ->
                new FuelBlockItem(stairs.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_fence", () ->
                new FuelBlockItem(fence.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_fence_gate", () ->
                new FuelBlockItem(fenceGate.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_door", () ->
                new FuelBlockItem(door.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_trapdoor", () ->
                new FuelBlockItem(trapdoor.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_button", () ->
                new FuelBlockItem(button.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
        ITEMS.register(name + "_pressure_plate", () ->
                new FuelBlockItem(pressurePlate.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    }


}
