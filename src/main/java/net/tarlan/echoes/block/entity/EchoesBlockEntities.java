package net.tarlan.echoes.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.EchoesBlocks;

public class EchoesBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Echoes.MOD_ID);

    public static final RegistryObject<BlockEntityType<UmbrelithFurnaceBlockEntity>> UMBRELITH_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("umbrelith_furnace_block_entity", () ->
                    BlockEntityType.Builder.of(UmbrelithFurnaceBlockEntity::new,
                            EchoesBlocks.UMBRELITH_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
