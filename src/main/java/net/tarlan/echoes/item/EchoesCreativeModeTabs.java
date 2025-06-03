package net.tarlan.echoes.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.EchoesBlocks;

public class EchoesCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Echoes.MOD_ID);
    public static final RegistryObject<CreativeModeTab> ECHOES_INGREDIENTS = CREATIVE_MODE_TABS.register("echoes_ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EchoesItems.VIOLUM_INGOT.get()))
                    .title(Component.translatable("creativetab.echoes_ingredients"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(EchoesItems.RAW_VERDANTINE.get());
                        pOutput.accept(EchoesItems.VERDANTINE_INGOT.get());
                        pOutput.accept(EchoesItems.VERDANTINE_NUGGET.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_INGOT.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_NUGGET.get());
                        pOutput.accept(EchoesItems.RAW_AZURETINE.get());
                        pOutput.accept(EchoesItems.AZURETINE_INGOT.get());
                        pOutput.accept(EchoesItems.AZURETINE_NUGGET.get());
                        pOutput.accept(EchoesItems.RAW_NERON.get());
                        pOutput.accept(EchoesItems.NERON_INGOT.get());
                        pOutput.accept(EchoesItems.NERON_NUGGET.get());
                        pOutput.accept(EchoesItems.RAW_GLARIUM.get());
                        pOutput.accept(EchoesItems.GLARIUM_INGOT.get());
                        pOutput.accept(EchoesItems.GLARIUM_NUGGET.get());
                        pOutput.accept(EchoesItems.VIOLUM_REMNANT.get());
                        pOutput.accept(EchoesItems.VIOLUM_NODULE.get());
                        pOutput.accept(EchoesItems.VIOLUM_INGOT.get());
                        pOutput.accept(EchoesItems.VIOLUM_NUGGET.get());

                        pOutput.accept(EchoesItems.CHLORIUM.get());
                        pOutput.accept(EchoesItems.CINDRITE.get());
                        //pOutput.accept(EchoesItems.MAGMAR.get());
                        pOutput.accept(EchoesItems.PEARLUM.get());
                        pOutput.accept(EchoesItems.PERIALIGHT.get());
                        //pOutput.accept(EchoesItems.REGITE.get());
                        pOutput.accept(EchoesItems.XIRIUM.get());

                        pOutput.accept(EchoesItems.BRIMNITE.get());
                        //pOutput.accept(EchoesItems.FLARE_DUST.get());
                        pOutput.accept(EchoesItems.RESONANE.get());
                        pOutput.accept(EchoesItems.UMBRITE.get());

                        //pOutput.accept(EchoesItems.COMPACTED_CHORUS.get());
                        pOutput.accept(EchoesItems.VIOLUM_SMITHING_UPGRADE.get());
                        pOutput.accept(EchoesItems.SHIVA_SCALE.get());

                        pOutput.accept(EchoesItems.LLERAE_FRUIT.get());
                        pOutput.accept(EchoesItems.BAKED_LLERAE_FRUIT.get());
                        pOutput.accept(EchoesItems.COBALT_SHIMMER_FLOWER.get());
                        pOutput.accept(EchoesItems.SHIMMER_STUFFED_LLERAE.get());
                        //pOutput.accept(EchoesItems.COBALT_TUFT_CONE.get());
                        //pOutput.accept(EchoesItems.MOSSBERRY.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> ECHOES_TOOLS_AND_ARMOR = CREATIVE_MODE_TABS.register("echoes_tools_and_armor",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EchoesItems.VIOLUM_PICKAXE.get()))
                    .title(Component.translatable("creativetab.echoes_tools_and_armor"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(EchoesItems.VERDANTINE_SWORD.get());
                        pOutput.accept(EchoesItems.VERDANTINE_SHOVEL.get());
                        pOutput.accept(EchoesItems.VERDANTINE_PICKAXE.get());
                        pOutput.accept(EchoesItems.VERDANTINE_AXE.get());
                        pOutput.accept(EchoesItems.VERDANTINE_HOE.get());
                        pOutput.accept(EchoesItems.CHLORIUM_SWORD.get());
                        pOutput.accept(EchoesItems.CHLORIUM_SHOVEL.get());
                        pOutput.accept(EchoesItems.CHLORIUM_PICKAXE.get());
                        pOutput.accept(EchoesItems.CHLORIUM_AXE.get());
                        pOutput.accept(EchoesItems.CHLORIUM_HOE.get());
                        pOutput.accept(EchoesItems.CINDRITE_SWORD.get());
                        pOutput.accept(EchoesItems.CINDRITE_SHOVEL.get());
                        pOutput.accept(EchoesItems.CINDRITE_PICKAXE.get());
                        pOutput.accept(EchoesItems.CINDRITE_AXE.get());
                        pOutput.accept(EchoesItems.CINDRITE_HOE.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_SWORD.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_SHOVEL.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_PICKAXE.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_AXE.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_HOE.get());
                        pOutput.accept(EchoesItems.NERON_SWORD.get());
                        pOutput.accept(EchoesItems.NERON_SHOVEL.get());
                        pOutput.accept(EchoesItems.NERON_PICKAXE.get());
                        pOutput.accept(EchoesItems.NERON_AXE.get());
                        pOutput.accept(EchoesItems.NERON_HOE.get());
                        pOutput.accept(EchoesItems.AZURETINE_SWORD.get());
                        pOutput.accept(EchoesItems.AZURETINE_SHOVEL.get());
                        pOutput.accept(EchoesItems.AZURETINE_PICKAXE.get());
                        pOutput.accept(EchoesItems.AZURETINE_AXE.get());
                        pOutput.accept(EchoesItems.AZURETINE_HOE.get());
                        pOutput.accept(EchoesItems.XIRIUM_SWORD.get());
                        pOutput.accept(EchoesItems.XIRIUM_SHOVEL.get());
                        pOutput.accept(EchoesItems.XIRIUM_PICKAXE.get());
                        pOutput.accept(EchoesItems.XIRIUM_AXE.get());
                        pOutput.accept(EchoesItems.XIRIUM_HOE.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_SWORD.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_SHOVEL.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_PICKAXE.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_AXE.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_HOE.get());
                        pOutput.accept(EchoesItems.VIOLUM_SWORD.get());
                        pOutput.accept(EchoesItems.VIOLUM_SHOVEL.get());
                        pOutput.accept(EchoesItems.VIOLUM_PICKAXE.get());
                        pOutput.accept(EchoesItems.VIOLUM_AXE.get());
                        pOutput.accept(EchoesItems.VIOLUM_HOE.get());

                        pOutput.accept(EchoesItems.VERDANTINE_HELMET.get());
                        pOutput.accept(EchoesItems.VERDANTINE_CHESTPLATE.get());
                        pOutput.accept(EchoesItems.VERDANTINE_LEGGINGS.get());
                        pOutput.accept(EchoesItems.VERDANTINE_BOOTS.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_HELMET.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_CHESTPLATE.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_LEGGINGS.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_BOOTS.get());
                        pOutput.accept(EchoesItems.NERON_HELMET.get());
                        pOutput.accept(EchoesItems.NERON_CHESTPLATE.get());
                        pOutput.accept(EchoesItems.NERON_LEGGINGS.get());
                        pOutput.accept(EchoesItems.NERON_BOOTS.get());
                        pOutput.accept(EchoesItems.AZURETINE_HELMET.get());
                        pOutput.accept(EchoesItems.AZURETINE_CHESTPLATE.get());
                        pOutput.accept(EchoesItems.AZURETINE_LEGGINGS.get());
                        pOutput.accept(EchoesItems.AZURETINE_BOOTS.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_HELMET.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_CHESTPLATE.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_LEGGINGS.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_BOOTS.get());
                        pOutput.accept(EchoesItems.VIOLUM_HELMET.get());
                        pOutput.accept(EchoesItems.VIOLUM_CHESTPLATE.get());
                        pOutput.accept(EchoesItems.VIOLUM_LEGGINGS.get());
                        pOutput.accept(EchoesItems.VIOLUM_BOOTS.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> ECHOES_BLOCKS = CREATIVE_MODE_TABS.register("echoes_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EchoesBlocks.VIOLUM_BLOCK.get()))
                    .title(Component.translatable("creativetab.echoes_blocks"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(EchoesItems.RAW_VERDANTINE_BLOCK.get());
                        pOutput.accept(EchoesItems.VERDANTINE_BLOCK.get());
                        //pOutput.accept(EchoesItems.CHORUSITE_BLOCK.get());
                        pOutput.accept(EchoesItems.RAW_AZURETINE_BLOCK.get());
                        pOutput.accept(EchoesItems.AZURETINE_BLOCK.get());
                        pOutput.accept(EchoesItems.RAW_NERON_BLOCK.get());
                        pOutput.accept(EchoesItems.NERON_BLOCK.get());
                        pOutput.accept(EchoesItems.VIOLUM_NODULE_BLOCK.get());
                        pOutput.accept(EchoesItems.VIOLUM_BLOCK.get());

                        pOutput.accept(EchoesItems.CHLORIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.CINDRITE_BLOCK.get());
                        //pOutput.accept(EchoesItems.MAGMAR_BLOCK.get());
                        pOutput.accept(EchoesItems.PEARLUM_BLOCK.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_BLOCK.get());
                        //pOutput.accept(EchoesItems.REGITE_BLOCK.get());
                        pOutput.accept(EchoesItems.XIRIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.GLOWING_XIRIUM_BLOCK.get());

                        pOutput.accept(EchoesItems.BRIMNITE_BLOCK.get());
                        pOutput.accept(EchoesItems.RESONANE_BLOCK.get());
                        pOutput.accept(EchoesItems.UMBRITE_BLOCK.get());

                        pOutput.accept(EchoesItems.VERLITH.get());
                        pOutput.accept(EchoesItems.ROUGH_VERLITH_SLAB.get());
                        pOutput.accept(EchoesItems.ROUGH_VERLITH_STAIRS.get());
                        pOutput.accept(EchoesItems.ROUGH_VERLITH_WALL.get());
                        pOutput.accept(EchoesItems.VERLITH_BLOCK.get());
                        pOutput.accept(EchoesItems.VERLITH_SLAB.get());
                        pOutput.accept(EchoesItems.VERLITH_STAIRS.get());
                        pOutput.accept(EchoesItems.VERLITH_WALL.get());
                        pOutput.accept(EchoesItems.VERLITH_TILES.get());
                        pOutput.accept(EchoesItems.VERLITH_PILLAR.get());
                        pOutput.accept(EchoesItems.VERLITH_PILLAR_CROSS.get());
                        pOutput.accept(EchoesItems.CHISELED_VERLITH.get());

                        pOutput.accept(EchoesItems.TEALITE.get());
                        pOutput.accept(EchoesItems.ROUGH_TEALITE_SLAB.get());
                        pOutput.accept(EchoesItems.ROUGH_TEALITE_STAIRS.get());
                        pOutput.accept(EchoesItems.ROUGH_TEALITE_WALL.get());
                        pOutput.accept(EchoesItems.TEALITE_BLOCK.get());
                        pOutput.accept(EchoesItems.TEALITE_SLAB.get());
                        pOutput.accept(EchoesItems.TEALITE_STAIRS.get());
                        pOutput.accept(EchoesItems.TEALITE_WALL.get());
                        pOutput.accept(EchoesItems.TEALITE_TILES.get());
                        pOutput.accept(EchoesItems.TEALITE_PILLAR.get());
                        pOutput.accept(EchoesItems.TEALITE_PILLAR_CROSS.get());
                        pOutput.accept(EchoesItems.CHISELED_TEALITE.get());

                        pOutput.accept(EchoesItems.PALESTONE.get());
                        pOutput.accept(EchoesItems.ROUGH_PALESTONE_SLAB.get());
                        pOutput.accept(EchoesItems.ROUGH_PALESTONE_STAIRS.get());
                        pOutput.accept(EchoesItems.ROUGH_PALESTONE_WALL.get());
                        pOutput.accept(EchoesItems.PALESTONE_BLOCK.get());
                        pOutput.accept(EchoesItems.PALESTONE_SLAB.get());
                        pOutput.accept(EchoesItems.PALESTONE_STAIRS.get());
                        pOutput.accept(EchoesItems.PALESTONE_WALL.get());
                        pOutput.accept(EchoesItems.PALESTONE_TILES.get());
                        pOutput.accept(EchoesItems.PALESTONE_PILLAR.get());
                        pOutput.accept(EchoesItems.PALESTONE_PILLAR_CROSS.get());
                        pOutput.accept(EchoesItems.CHISELED_PALESTONE.get());

                        //pOutput.accept(EchoesItems.BLUESTONE.get());
                        //pOutput.accept(EchoesItems.SMOOTH_BLUESTONE.get());
                        //pOutput.accept(EchoesItems.POLISHED_BLUESTONE.get());
                        //pOutput.accept(EchoesItems.BLUESTONE_BRICKS.get());
                        //pOutput.accept(EchoesItems.QUARTZ_STYLE_BLUESTONE_BRICKS.get());
                        //pOutput.accept(EchoesItems.BLUESTONE_STAIRS.get());
                        //pOutput.accept(EchoesItems.BLUESTONE_SLAB.get());
                        //pOutput.accept(EchoesItems.BLUESTONE_WALL.get());
                        //pOutput.accept(EchoesItems.BLUESTONE_PILLAR.get());
                        //pOutput.accept(EchoesItems.CHISELED_BLUESTONE.get());
                        //
                        //pOutput.accept(EchoesItems.ODER.get());
                        //pOutput.accept(EchoesItems.POLISHED_ODER.get());
                        //pOutput.accept(EchoesItems.ODER_BRICKS.get());
                        //pOutput.accept(EchoesItems.ODER_TILES.get());
                        //pOutput.accept(EchoesItems.ODER_PILLAR.get());
                        //pOutput.accept(EchoesItems.ODER_STAIRS.get());
                        //pOutput.accept(EchoesItems.ODER_SLAB.get());
                        //pOutput.accept(EchoesItems.ODER_WALL.get());

                        pOutput.accept(EchoesItems.UMBRELITH.get());
                        pOutput.accept(EchoesItems.COBBLED_UMBRELITH.get());
                        pOutput.accept(EchoesItems.FRAMED_UMBRELITH.get());
                        pOutput.accept(EchoesItems.FRAMED_COBBLED_UMBRELITH.get());
                        pOutput.accept(EchoesItems.ROUGH_UMBRELITH_SLAB.get());
                        pOutput.accept(EchoesItems.ROUGH_UMBRELITH_STAIRS.get());
                        pOutput.accept(EchoesItems.ROUGH_UMBRELITH_WALL.get());
                        pOutput.accept(EchoesItems.SMOOTH_UMBRELITH.get());
                        pOutput.accept(EchoesItems.SMOOTH_UMBRELITH_SLAB.get());
                        pOutput.accept(EchoesItems.SMOOTH_UMBRELITH_STAIRS.get());
                        pOutput.accept(EchoesItems.SMOOTH_UMBRELITH_WALL.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BLOCK.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BLOCK_DAMAGED.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BLOCK_CRACKED.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BLOCK_FRACTURED.get());
                        pOutput.accept(EchoesItems.UMBRELITH_SLAB.get());
                        pOutput.accept(EchoesItems.UMBRELITH_STAIRS.get());
                        pOutput.accept(EchoesItems.UMBRELITH_WALL.get());
                        pOutput.accept(EchoesItems.CHISELED_UMBRELITH.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BRICKS.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BRICK_SLAB.get());
                        pOutput.accept(EchoesItems.UMBRELITH_BRICK_STAIRS.get());
                        pOutput.accept(EchoesItems.UMBRELITH_TILES.get());
                        pOutput.accept(EchoesItems.UMBRELITH_TILE_SLAB.get());
                        pOutput.accept(EchoesItems.UMBRELITH_TILE_STAIRS.get());
                        pOutput.accept(EchoesItems.THREE_BY_THREE_UMBRELITH_TILES.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PANEL.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PANEL_INSET.get());
                        pOutput.accept(EchoesItems.REINFORCED_UMBRELITH_PANEL.get());
                        pOutput.accept(EchoesItems.REINFORCED_UMBRELITH_PANEL_INSET.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_TILES.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_TILE_SLAB.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_TILE_STAIRS.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_BRICKS.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_BRICK_SLAB.get());
                        pOutput.accept(EchoesItems.FINE_UMBRELITH_BRICK_STAIRS.get());
                        pOutput.accept(EchoesItems.PATTERNED_UMBRELITH_TILES.get());
                        pOutput.accept(EchoesItems.CUT_UMBRELITH.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PILLAR.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PILLAR_SLAB.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PILLAR_STAIRS.get());
                        pOutput.accept(EchoesItems.UMBRELITH_PILLAR_CROSS.get());
                        pOutput.accept(EchoesItems.UMBRELITH_OMNI_SLAB.get());
                        //pOutput.accept(EchoesItems.UMBRELITH_BLOCK_CONNECTING.get());

                        pOutput.accept(EchoesItems.MONOLITH.get());
                        pOutput.accept(EchoesItems.VOID_PORTAL.get());

                        pOutput.accept(EchoesItems.VERDANTINE_ORE.get());
                        pOutput.accept(EchoesItems.AZURETINE_ORE.get());
                        pOutput.accept(EchoesItems.NERON_ORE.get());
                        pOutput.accept(EchoesItems.VIOLUM_DEPOSIT.get());

                        pOutput.accept(EchoesItems.CHLORIUM_ORE.get());
                        pOutput.accept(EchoesItems.CINDRITE_ORE.get());
                        pOutput.accept(EchoesItems.DEEPSLATE_CINDRITE_ORE.get());
                        pOutput.accept(EchoesItems.NETHER_CINDRITE_ORE.get());
                        //pOutput.accept(EchoesItems.MAGMAR_ORE.get());
                        pOutput.accept(EchoesItems.PEARLUM_ORE.get());
                        pOutput.accept(EchoesItems.PERIALIGHT_ORE.get());
                        pOutput.accept(EchoesItems.END_PERIALIGHT_ORE.get());
                        //pOutput.accept(EchoesItems.REGITE_ORE.get());
                        pOutput.accept(EchoesItems.XIRIUM_ORE.get());

                        pOutput.accept(EchoesItems.BRIMNITE_ORE.get());
                        pOutput.accept(EchoesItems.UMBRITE_ORE.get());
                        pOutput.accept(EchoesItems.DEEPSLATE_UMBRITE_ORE.get());
                        pOutput.accept(EchoesItems.END_UMBRITE_ORE.get());

                        //pOutput.accept(EchoesItems.CHORUS_BLOCK.get());
                        //pOutput.accept(EchoesItems.CHORUS_PLANKS.get());

                        pOutput.accept(EchoesItems.GLARIUM_ORE.get());
                        pOutput.accept(EchoesItems.RAW_GLARIUM_BLOCK.get());

                        pOutput.accept(EchoesItems.GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.MONOCHROME_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.MONOCHROME_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_MONOCHROME_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.RED_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.RED_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_RED_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.ORANGE_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.ORANGE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_ORANGE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.YELLOW_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.YELLOW_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_YELLOW_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LIME_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.LIME_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_LIME_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.GREEN_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.GREEN_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_GREEN_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LIGHT_BLUE_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.LIGHT_BLUE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_LIGHT_BLUE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.BLUE_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.BLUE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_BLUE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.PURPLE_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.PURPLE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_PURPLE_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.MAGENTA_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.MAGENTA_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_MAGENTA_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.PINK_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.PINK_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_PINK_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.BROWN_GLARIUM_BLOCK.get());
                        pOutput.accept(EchoesItems.BROWN_GLARIUM_LAMP.get());
                        pOutput.accept(EchoesItems.LARGE_BROWN_GLARIUM_LAMP.get());

                        pOutput.accept(EchoesItems.SHIMMER_TORCH.get());

                        pOutput.accept(EchoesItems.LLERAE_LOG.get());
                        pOutput.accept(EchoesItems.STRIPPED_LLERAE_LOG.get());
                        pOutput.accept(EchoesItems.LLERAE_PLANKS.get());
                        pOutput.accept(EchoesItems.LLERAE_SLAB.get());
                        pOutput.accept(EchoesItems.LLERAE_STAIRS.get());
                        pOutput.accept(EchoesItems.LLERAE_FENCE.get());
                        pOutput.accept(EchoesItems.LLERAE_FENCE_GATE.get());
                        pOutput.accept(EchoesItems.LLERAE_DOOR.get());
                        pOutput.accept(EchoesItems.LLERAE_TRAPDOOR.get());
                        pOutput.accept(EchoesItems.LLERAE_BUTTON.get());
                        pOutput.accept(EchoesItems.LLERAE_PRESSURE_PLATE.get());
                        pOutput.accept(EchoesItems.LLERAE_LEAVES.get());
                        pOutput.accept(EchoesItems.BUDDING_LLERAE_LEAVES.get());
                        pOutput.accept(EchoesItems.LLERAE_SAPLING.get());
                        pOutput.accept(EchoesItems.UMBRELITH_LLERAE_MOSS.get());
                        pOutput.accept(EchoesItems.PALESTONE_LLERAE_MOSS.get());
                        pOutput.accept(EchoesItems.TEALITE_LLERAE_MOSS.get());
                        pOutput.accept(EchoesItems.VERLITH_LLERAE_MOSS.get());

                        pOutput.accept(EchoesItems.SHIMMERDOWN_LOG.get());
                        pOutput.accept(EchoesItems.STRIPPED_SHIMMERDOWN_LOG.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_PLANKS.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_SLAB.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_STAIRS.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_FENCE.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_FENCE_GATE.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_DOOR.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_TRAPDOOR.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_BUTTON.get());
                        pOutput.accept(EchoesItems.SHIMMERDOWN_PRESSURE_PLATE.get());
                        pOutput.accept(EchoesItems.COBALT_SHIMMERDOWN_LEAVES.get());
                        pOutput.accept(EchoesItems.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get());
                        pOutput.accept(EchoesItems.COBALT_SHIMMERDOWN_SAPLING.get());
                        pOutput.accept(EchoesItems.UMBRELITH_COBALT_MOSS.get());
                        pOutput.accept(EchoesItems.PALESTONE_COBALT_MOSS.get());
                        pOutput.accept(EchoesItems.TEALITE_COBALT_MOSS.get());
                        pOutput.accept(EchoesItems.VERLITH_COBALT_MOSS.get());

                        pOutput.accept(EchoesItems.COBALT_TUFT_SMALL.get());

                        pOutput.accept(EchoesItems.COBALT_MOSS_LAYER.get());

                        pOutput.accept(EchoesItems.INK_TENDRIL.get());
                        pOutput.accept(EchoesItems.RED_DUSTER.get());
                        pOutput.accept(EchoesItems.GREEN_DUSTER.get());
                        pOutput.accept(EchoesItems.BLUE_DUSTER.get());
                        pOutput.accept(EchoesItems.SPARKBUD.get());

                        pOutput.accept(EchoesItems.BANDSAW.get());
                        pOutput.accept(EchoesItems.UMBRELITH_FURNACE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
