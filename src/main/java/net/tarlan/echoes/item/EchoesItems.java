package net.tarlan.echoes.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.item.custom.EchoesFoods;
import net.tarlan.echoes.item.custom.FuelBlockItem;
import net.tarlan.echoes.item.custom.FuelItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.item.Items.registerBlock;
import static net.minecraft.world.item.Items.registerItem;

public class EchoesItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Echoes.MOD_ID);

    //METALS
    public static final RegistryObject<Item> RAW_VERDANTINE = ITEMS.register("raw_verdantine",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_INGOT = ITEMS.register("verdantine_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_NUGGET = ITEMS.register("verdantine_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_INGOT = ITEMS.register("chorusite_ingot",
    //        () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_NUGGET = ITEMS.register("chorusite_nugget",
    //        () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> RAW_AZURETINE = ITEMS.register("raw_azuretine",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_INGOT = ITEMS.register("azuretine_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_NUGGET = ITEMS.register("azuretine_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> RAW_NERON = ITEMS.register("raw_neron",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_INGOT = ITEMS.register("neron_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_NUGGET = ITEMS.register("neron_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> RAW_GLARIUM = ITEMS.register("raw_glarium",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GLARIUM_INGOT = ITEMS.register("glarium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GLARIUM_NUGGET = ITEMS.register("glarium_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VIOLUM_REMNANT = ITEMS.register("violum_remnant",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_NODULE = ITEMS.register("violum_nodule",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_INGOT = ITEMS.register("violum_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_NUGGET = ITEMS.register("violum_nugget",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    //GEMS
    public static final RegistryObject<Item> CHLORIUM = ITEMS.register("chlorium",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CINDRITE = ITEMS.register("cindrite",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> MAGMAR = ITEMS.register("magmar",
    //        () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> PEARLUM = ITEMS.register("pearlum",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> PERIALIGHT = ITEMS.register("perialight",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.UNCOMMON), 8000));
    //public static final RegistryObject<Item> REGITE = ITEMS.register("regite",
    //        () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> XIRIUM = ITEMS.register("xirium",
            () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    //FUELS
    public static final RegistryObject<Item> BRIMNITE = ITEMS.register("brimnite",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.COMMON), 2400));
    //public static final RegistryObject<Item> FLARE_DUST = ITEMS.register("flare_dust",
    //        () -> new FuelItem(new Item.Properties().rarity(Rarity.COMMON), 4800));
    public static final RegistryObject<Item> RESONANE = ITEMS.register("resonane",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.RARE),16000));
    public static final RegistryObject<Item> UMBRITE = ITEMS.register("umbrite",
            () -> new FuelItem(new Item.Properties().rarity(Rarity.COMMON), 2400));

    //public static final RegistryObject<Item> COMPACTED_CHORUS = ITEMS.register("compacted_chorus",
    //        () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> VIOLUM_SMITHING_UPGRADE = ITEMS.register("violum_smithing_upgrade",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> SHIVA_SCALE = ITEMS.register("shiva_scale",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(8)) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.literal("It still hums...").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
                }
            });



    //BLOCKS
    public static final RegistryObject<BlockItem> VERDANTINE_BLOCK = ITEMS.register("raw_verdantine_block", () ->
            new BlockItem(EchoesBlocks.RAW_VERDANTINE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RAW_VERDANTINE_BLOCK = ITEMS.register("verdantine_block", () ->
            new BlockItem(EchoesBlocks.VERDANTINE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<BlockItem> CHORUSITE_BLOCK = ITEMS.register("chorusite_block", () ->
    //        new BlockItem(EchoesBlocks.CHORUSITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RAW_AZURETINE_BLOCK = ITEMS.register("raw_azuretine_block", () ->
            new BlockItem(EchoesBlocks.RAW_AZURETINE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> AZURETINE_BLOCK = ITEMS.register("azuretine_block", () ->
            new BlockItem(EchoesBlocks.AZURETINE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RAW_NERON_BLOCK = ITEMS.register("raw_neron_block", () ->
            new BlockItem(EchoesBlocks.RAW_NERON_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> NERON_BLOCK = ITEMS.register("neron_block", () ->
            new BlockItem(EchoesBlocks.NERON_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VIOLUM_NODULE_BLOCK = ITEMS.register("violum_nodule_block", () ->
            new BlockItem(EchoesBlocks.VIOLUM_NODULE_BLOCK.get(), new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<BlockItem> VIOLUM_BLOCK = ITEMS.register("violum_block", () ->
            new BlockItem(EchoesBlocks.VIOLUM_BLOCK.get(), new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<BlockItem> CHLORIUM_BLOCK = ITEMS.register("chlorium_block", () ->
            new BlockItem(EchoesBlocks.CHLORIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CINDRITE_BLOCK = ITEMS.register("cindrite_block", () ->
            new BlockItem(EchoesBlocks.CINDRITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<BlockItem> MAGMAR_BLOCK = ITEMS.register("magmar_block", () ->
    //        new BlockItem(EchoesBlocks.MAGMAR_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PEARLUM_BLOCK = ITEMS.register("pearlum_block", () ->
            new BlockItem(EchoesBlocks.PEARLUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PERIALIGHT_BLOCK = ITEMS.register("perialight_block", () ->
            new FuelBlockItem(EchoesBlocks.PERIALIGHT_BLOCK.get(), new Item.Properties().rarity(Rarity.UNCOMMON), 72000));
    //public static final RegistryObject<BlockItem> REGITE_BLOCK = ITEMS.register("regite_block", () ->
    //        new BlockItem(EchoesBlocks.REGITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> XIRIUM_BLOCK = ITEMS.register("xirium_block", () ->
            new BlockItem(EchoesBlocks.XIRIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GLOWING_XIRIUM_BLOCK = ITEMS.register("glowing_xirium_block", () ->
            new BlockItem(EchoesBlocks.GLOWING_XIRIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> BRIMNITE_BLOCK = ITEMS.register("brimnite_block", () ->
            new FuelBlockItem(EchoesBlocks.BRIMNITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON), 21600));
    public static final RegistryObject<BlockItem> RESONANE_BLOCK = ITEMS.register("resonane_block", () ->
            new FuelBlockItem(EchoesBlocks.RESONANE_BLOCK.get(), new Item.Properties().rarity(Rarity.RARE), 144000));
    public static final RegistryObject<BlockItem> UMBRITE_BLOCK = ITEMS.register("umbrite_block", () ->
            new FuelBlockItem(EchoesBlocks.UMBRITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON), 21600));

    public static final RegistryObject<BlockItem> VERLITH = ITEMS.register("verlith", () ->
            new BlockItem(EchoesBlocks.VERLITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_VERLITH_SLAB = ITEMS.register("rough_verlith_slab", () ->
            new BlockItem(EchoesBlocks.ROUGH_VERLITH_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_VERLITH_STAIRS = ITEMS.register("rough_verlith_stairs", () ->
            new BlockItem(EchoesBlocks.ROUGH_VERLITH_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_VERLITH_WALL = ITEMS.register("rough_verlith_wall", () ->
            new BlockItem(EchoesBlocks.ROUGH_VERLITH_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_BLOCK = ITEMS.register("verlith_block", () ->
            new BlockItem(EchoesBlocks.VERLITH_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_SLAB = ITEMS.register("verlith_slab", () ->
            new BlockItem(EchoesBlocks.VERLITH_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_STAIRS = ITEMS.register("verlith_stairs", () ->
            new BlockItem(EchoesBlocks.VERLITH_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_WALL = ITEMS.register("verlith_wall", () ->
            new BlockItem(EchoesBlocks.VERLITH_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_TILES = ITEMS.register("verlith_tiles", () ->
            new BlockItem(EchoesBlocks.VERLITH_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_PILLAR = ITEMS.register("verlith_pillar", () ->
            new BlockItem(EchoesBlocks.VERLITH_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_PILLAR_CROSS = ITEMS.register("verlith_pillar_cross", () ->
            new BlockItem(EchoesBlocks.VERLITH_PILLAR_CROSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CHISELED_VERLITH = ITEMS.register("chiseled_verlith", () ->
            new BlockItem(EchoesBlocks.CHISELED_VERLITH.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> TEALITE = ITEMS.register("tealite", () ->
            new BlockItem(EchoesBlocks.TEALITE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_TEALITE_SLAB = ITEMS.register("rough_tealite_slab", () ->
            new BlockItem(EchoesBlocks.ROUGH_TEALITE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_TEALITE_STAIRS = ITEMS.register("rough_tealite_stairs", () ->
            new BlockItem(EchoesBlocks.ROUGH_TEALITE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_TEALITE_WALL = ITEMS.register("rough_tealite_wall", () ->
            new BlockItem(EchoesBlocks.ROUGH_TEALITE_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_BLOCK = ITEMS.register("tealite_block", () ->
            new BlockItem(EchoesBlocks.TEALITE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_SLAB = ITEMS.register("tealite_slab", () ->
            new BlockItem(EchoesBlocks.TEALITE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_STAIRS = ITEMS.register("tealite_stairs", () ->
            new BlockItem(EchoesBlocks.TEALITE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_WALL = ITEMS.register("tealite_wall", () ->
            new BlockItem(EchoesBlocks.TEALITE_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_TILES = ITEMS.register("tealite_tiles", () ->
            new BlockItem(EchoesBlocks.TEALITE_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_PILLAR = ITEMS.register("tealite_pillar", () ->
            new BlockItem(EchoesBlocks.TEALITE_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_PILLAR_CROSS = ITEMS.register("tealite_pillar_cross", () ->
            new BlockItem(EchoesBlocks.TEALITE_PILLAR_CROSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CHISELED_TEALITE = ITEMS.register("chiseled_tealite", () ->
            new BlockItem(EchoesBlocks.CHISELED_TEALITE.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> PALESTONE = ITEMS.register("palestone", () ->
            new BlockItem(EchoesBlocks.PALESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_PALESTONE_SLAB = ITEMS.register("rough_palestone_slab", () ->
            new BlockItem(EchoesBlocks.ROUGH_PALESTONE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_PALESTONE_STAIRS = ITEMS.register("rough_palestone_stairs", () ->
            new BlockItem(EchoesBlocks.ROUGH_PALESTONE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_PALESTONE_WALL = ITEMS.register("rough_palestone_wall", () ->
            new BlockItem(EchoesBlocks.ROUGH_PALESTONE_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_BLOCK = ITEMS.register("palestone_block", () ->
            new BlockItem(EchoesBlocks.PALESTONE_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_SLAB = ITEMS.register("palestone_slab", () ->
            new BlockItem(EchoesBlocks.PALESTONE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_STAIRS = ITEMS.register("palestone_stairs", () ->
            new BlockItem(EchoesBlocks.PALESTONE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_WALL = ITEMS.register("palestone_wall", () ->
            new BlockItem(EchoesBlocks.PALESTONE_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_TILES = ITEMS.register("palestone_tiles", () ->
            new BlockItem(EchoesBlocks.PALESTONE_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_PILLAR = ITEMS.register("palestone_pillar", () ->
            new BlockItem(EchoesBlocks.PALESTONE_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_PILLAR_CROSS = ITEMS.register("palestone_pillar_cross", () ->
            new BlockItem(EchoesBlocks.PALESTONE_PILLAR_CROSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CHISELED_PALESTONE = ITEMS.register("chiseled_palestone", () ->
            new BlockItem(EchoesBlocks.CHISELED_PALESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));

   //public static final RegistryObject<BlockItem> BLUESTONE = ITEMS.register("bluestone", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> SMOOTH_BLUESTONE = ITEMS.register("smooth_bluestone", () ->
   //        new BlockItem(EchoesBlocks.SMOOTH_BLUESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> POLISHED_BLUESTONE = ITEMS.register("polished_bluestone", () ->
   //        new BlockItem(EchoesBlocks.POLISHED_BLUESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> BLUESTONE_BRICKS = ITEMS.register("bluestone_bricks", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE_BRICKS.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> QUARTZ_STYLE_BLUESTONE_BRICKS = ITEMS.register("quartz_style_bluestone_bricks", () ->
   //        new BlockItem(EchoesBlocks.QUARTZ_STYLE_BLUESTONE_BRICKS.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> BLUESTONE_STAIRS = ITEMS.register("bluestone_stairs", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> BLUESTONE_SLAB = ITEMS.register("bluestone_slab", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> BLUESTONE_WALL = ITEMS.register("bluestone_wall", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> BLUESTONE_PILLAR = ITEMS.register("bluestone_pillar", () ->
   //        new BlockItem(EchoesBlocks.BLUESTONE_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> CHISELED_BLUESTONE = ITEMS.register("chiseled_bluestone", () ->
   //        new BlockItem(EchoesBlocks.CHISELED_BLUESTONE.get(), new Item.Properties().rarity(Rarity.COMMON)));

   //public static final RegistryObject<BlockItem> ODER = ITEMS.register("oder", () ->
   //        new BlockItem(EchoesBlocks.ODER.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> POLISHED_ODER = ITEMS.register("polished_oder", () ->
   //        new BlockItem(EchoesBlocks.POLISHED_ODER.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_BRICKS = ITEMS.register("oder_bricks", () ->
   //        new BlockItem(EchoesBlocks.ODER_BRICKS.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_TILES = ITEMS.register("oder_tiles", () ->
   //        new BlockItem(EchoesBlocks.ODER_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_PILLAR = ITEMS.register("oder_pillar", () ->
   //        new BlockItem(EchoesBlocks.ODER_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_STAIRS = ITEMS.register("oder_stairs", () ->
   //        new BlockItem(EchoesBlocks.ODER_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_SLAB = ITEMS.register("oder_slab", () ->
   //        new BlockItem(EchoesBlocks.ODER_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
   //public static final RegistryObject<BlockItem> ODER_WALL = ITEMS.register("oder_wall", () ->
   //        new BlockItem(EchoesBlocks.ODER_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> UMBRELITH = ITEMS.register("umbrelith", () ->
            new BlockItem(EchoesBlocks.UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> COBBLED_UMBRELITH = ITEMS.register("cobbled_umbrelith", () ->
            new BlockItem(EchoesBlocks.COBBLED_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FRAMED_UMBRELITH = ITEMS.register("framed_umbrelith", () ->
            new BlockItem(EchoesBlocks.FRAMED_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FRAMED_COBBLED_UMBRELITH = ITEMS.register("framed_cobbled_umbrelith", () ->
            new BlockItem(EchoesBlocks.FRAMED_COBBLED_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_UMBRELITH_SLAB = ITEMS.register("rough_umbrelith_slab", () ->
            new BlockItem(EchoesBlocks.ROUGH_UMBRELITH_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_UMBRELITH_STAIRS = ITEMS.register("rough_umbrelith_stairs", () ->
            new BlockItem(EchoesBlocks.ROUGH_UMBRELITH_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ROUGH_UMBRELITH_WALL = ITEMS.register("rough_umbrelith_wall", () ->
            new BlockItem(EchoesBlocks.ROUGH_UMBRELITH_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> SMOOTH_UMBRELITH = ITEMS.register("smooth_umbrelith", () ->
            new BlockItem(EchoesBlocks.SMOOTH_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> SMOOTH_UMBRELITH_SLAB = ITEMS.register("smooth_umbrelith_slab", () ->
            new BlockItem(EchoesBlocks.SMOOTH_UMBRELITH_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
     public static final RegistryObject<BlockItem> SMOOTH_UMBRELITH_STAIRS = ITEMS.register("smooth_umbrelith_stairs", () ->
            new BlockItem(EchoesBlocks.SMOOTH_UMBRELITH_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
     public static final RegistryObject<BlockItem> SMOOTH_UMBRELITH_WALL = ITEMS.register("smooth_umbrelith_wall", () ->
            new BlockItem(EchoesBlocks.SMOOTH_UMBRELITH_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BLOCK = ITEMS.register("umbrelith_block", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BLOCK_DAMAGED = ITEMS.register("umbrelith_block_damaged", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BLOCK_DAMAGED.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BLOCK_CRACKED = ITEMS.register("umbrelith_block_cracked", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BLOCK_CRACKED.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BLOCK_FRACTURED = ITEMS.register("umbrelith_block_fractured", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BLOCK_FRACTURED.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_SLAB = ITEMS.register("umbrelith_slab", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_STAIRS = ITEMS.register("umbrelith_stairs", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_WALL = ITEMS.register("umbrelith_wall", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_WALL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CHISELED_UMBRELITH = ITEMS.register("chiseled_umbrelith", () ->
            new BlockItem(EchoesBlocks.CHISELED_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BRICKS = ITEMS.register("umbrelith_bricks", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BRICKS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BRICK_SLAB = ITEMS.register("umbrelith_brick_slab", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BRICK_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_BRICK_STAIRS = ITEMS.register("umbrelith_brick_stairs", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_BRICK_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_TILES = ITEMS.register("umbrelith_tiles", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_TILE_SLAB = ITEMS.register("umbrelith_tile_slab", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_TILE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_TILE_STAIRS = ITEMS.register("umbrelith_tile_stairs", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_TILE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> THREE_BY_THREE_UMBRELITH_TILES = ITEMS.register("three_by_three_umbrelith_tiles", () ->
            new BlockItem(EchoesBlocks.THREE_BY_THREE_UMBRELITH_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PANEL = ITEMS.register("umbrelith_panel", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PANEL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PANEL_INSET = ITEMS.register("umbrelith_panel_inset", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PANEL_INSET.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> REINFORCED_UMBRELITH_PANEL = ITEMS.register("reinforced_umbrelith_panel", () ->
            new BlockItem(EchoesBlocks.REINFORCED_UMBRELITH_PANEL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> REINFORCED_UMBRELITH_PANEL_INSET = ITEMS.register("reinforced_umbrelith_panel_inset", () ->
            new BlockItem(EchoesBlocks.REINFORCED_UMBRELITH_PANEL_INSET.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_TILES = ITEMS.register("fine_umbrelith_tiles", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_TILE_SLAB = ITEMS.register("fine_umbrelith_tile_slab", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_TILE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_TILE_STAIRS = ITEMS.register("fine_umbrelith_tile_stairs", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_TILE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_BRICKS = ITEMS.register("fine_umbrelith_bricks", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_BRICKS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_BRICK_SLAB = ITEMS.register("fine_umbrelith_brick_slab", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_BRICK_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> FINE_UMBRELITH_BRICK_STAIRS = ITEMS.register("fine_umbrelith_brick_stairs", () ->
            new BlockItem(EchoesBlocks.FINE_UMBRELITH_BRICK_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PATTERNED_UMBRELITH_TILES = ITEMS.register("patterned_umbrelith_tiles", () ->
            new BlockItem(EchoesBlocks.PATTERNED_UMBRELITH_TILES.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CUT_UMBRELITH = ITEMS.register("cut_umbrelith", () ->
            new BlockItem(EchoesBlocks.CUT_UMBRELITH.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PILLAR = ITEMS.register("umbrelith_pillar", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PILLAR.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PILLAR_SLAB = ITEMS.register("umbrelith_pillar_slab", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PILLAR_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PILLAR_STAIRS = ITEMS.register("umbrelith_pillar_stairs", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PILLAR_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_PILLAR_CROSS = ITEMS.register("umbrelith_pillar_cross", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_PILLAR_CROSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_OMNI_SLAB = ITEMS.register("umbrelith_omni_slab", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_OMNI_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<BlockItem> UMBRELITH_BLOCK_CONNECTING = ITEMS.register("umbrelith_block_connecting", () ->
    //        new BlockItem(EchoesBlocks.UMBRELITH_BLOCK_CONNECTING.get(), new Item.Properties().rarity(Rarity.COMMON)));


    public static final RegistryObject<BlockItem> MONOLITH = ITEMS.register("monolith", () ->
            new BlockItem(EchoesBlocks.MONOLITH.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> VOID_PORTAL = ITEMS.register("void_portal", () ->
            new BlockItem(EchoesBlocks.VOID_PORTAL.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> VERDANTINE_ORE = ITEMS.register("verdantine_ore", () ->
            new BlockItem(EchoesBlocks.VERDANTINE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> AZURETINE_ORE = ITEMS.register("azuretine_ore", () ->
            new BlockItem(EchoesBlocks.AZURETINE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> NERON_ORE = ITEMS.register("neron_ore", () ->
            new BlockItem(EchoesBlocks.NERON_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GLARIUM_ORE = ITEMS.register("glarium_ore", () ->
            new BlockItem(EchoesBlocks.GLARIUM_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VIOLUM_DEPOSIT = ITEMS.register("violum_deposit", () ->
            new BlockItem(EchoesBlocks.VIOLUM_DEPOSIT.get(), new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<BlockItem> CHLORIUM_ORE = ITEMS.register("chlorium_ore", () ->
            new BlockItem(EchoesBlocks.CHLORIUM_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> CINDRITE_ORE = ITEMS.register("cindrite_ore", () ->
            new BlockItem(EchoesBlocks.CINDRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> DEEPSLATE_CINDRITE_ORE = ITEMS.register("deepslate_cindrite_ore", () ->
            new BlockItem(EchoesBlocks.DEEPSLATE_CINDRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> NETHER_CINDRITE_ORE = ITEMS.register("nether_cindrite_ore", () ->
            new BlockItem(EchoesBlocks.NETHER_CINDRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<BlockItem> MAGMAR_ORE = ITEMS.register("magmar_ore", () ->
    //        new BlockItem(EchoesBlocks.MAGMAR_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PEARLUM_ORE = ITEMS.register("pearlum_ore", () ->
            new BlockItem(EchoesBlocks.PEARLUM_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PERIALIGHT_ORE = ITEMS.register("perialight_ore", () ->
            new BlockItem(EchoesBlocks.PERIALIGHT_ORE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<BlockItem> END_PERIALIGHT_ORE = ITEMS.register("end_perialight_ore", () ->
            new BlockItem(EchoesBlocks.END_PERIALIGHT_ORE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
    //public static final RegistryObject<BlockItem> REGITE_ORE = ITEMS.register("regite_ore", () ->
            //        new BlockItem(EchoesBlocks.REGITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> XIRIUM_ORE = ITEMS.register("xirium_ore", () ->
            new BlockItem(EchoesBlocks.XIRIUM_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> BRIMNITE_ORE = ITEMS.register("brimnite_ore", () ->
            new BlockItem(EchoesBlocks.BRIMNITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRITE_ORE = ITEMS.register("umbrite_ore", () ->
            new BlockItem(EchoesBlocks.UMBRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> DEEPSLATE_UMBRITE_ORE = ITEMS.register("deepslate_umbrite_ore", () ->
            new BlockItem(EchoesBlocks.DEEPSLATE_UMBRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> END_UMBRITE_ORE = ITEMS.register("end_umbrite_ore", () ->
            new BlockItem(EchoesBlocks.END_UMBRITE_ORE.get(), new Item.Properties().rarity(Rarity.COMMON)));

    //public static final RegistryObject<BlockItem> CHORUS_BLOCK = ITEMS.register("chorus_block", () ->
    //        new BlockItem(EchoesBlocks.CHORUS_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<BlockItem> CHORUS_PLANKS = ITEMS.register("chorus_planks", () ->
    //        new BlockItem(EchoesBlocks.CHORUS_PLANKS.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> RAW_GLARIUM_BLOCK = ITEMS.register("raw_glarium_block", () ->
            new BlockItem(EchoesBlocks.RAW_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> GLARIUM_BLOCK = ITEMS.register("glarium_block", () ->
            new BlockItem(EchoesBlocks.GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GLARIUM_LAMP = ITEMS.register("glarium_lamp", () ->
            new BlockItem(EchoesBlocks.GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_GLARIUM_LAMP = ITEMS.register("large_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> MONOCHROME_GLARIUM_BLOCK = ITEMS.register("monochrome_glarium_block", () ->
            new BlockItem(EchoesBlocks.MONOCHROME_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> MONOCHROME_GLARIUM_LAMP = ITEMS.register("monochrome_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.MONOCHROME_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_MONOCHROME_GLARIUM_LAMP = ITEMS.register("large_monochrome_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_MONOCHROME_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RED_GLARIUM_BLOCK = ITEMS.register("red_glarium_block", () ->
            new BlockItem(EchoesBlocks.RED_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RED_GLARIUM_LAMP = ITEMS.register("red_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.RED_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_RED_GLARIUM_LAMP = ITEMS.register("large_red_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_RED_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ORANGE_GLARIUM_BLOCK = ITEMS.register("orange_glarium_block", () ->
            new BlockItem(EchoesBlocks.ORANGE_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> ORANGE_GLARIUM_LAMP = ITEMS.register("orange_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.ORANGE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_ORANGE_GLARIUM_LAMP = ITEMS.register("large_orange_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_ORANGE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> YELLOW_GLARIUM_BLOCK = ITEMS.register("yellow_glarium_block", () ->
            new BlockItem(EchoesBlocks.YELLOW_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> YELLOW_GLARIUM_LAMP = ITEMS.register("yellow_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.YELLOW_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_YELLOW_GLARIUM_LAMP = ITEMS.register("large_yellow_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_YELLOW_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LIME_GLARIUM_BLOCK = ITEMS.register("lime_glarium_block", () ->
            new BlockItem(EchoesBlocks.LIME_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LIME_GLARIUM_LAMP = ITEMS.register("lime_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LIME_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_LIME_GLARIUM_LAMP = ITEMS.register("large_lime_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_LIME_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GREEN_GLARIUM_BLOCK = ITEMS.register("green_glarium_block", () ->
            new BlockItem(EchoesBlocks.GREEN_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GREEN_GLARIUM_LAMP = ITEMS.register("green_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.GREEN_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_GREEN_GLARIUM_LAMP = ITEMS.register("large_green_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_GREEN_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LIGHT_BLUE_GLARIUM_BLOCK = ITEMS.register("light_blue_glarium_block", () ->
            new BlockItem(EchoesBlocks.LIGHT_BLUE_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LIGHT_BLUE_GLARIUM_LAMP = ITEMS.register("light_blue_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LIGHT_BLUE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_LIGHT_BLUE_GLARIUM_LAMP = ITEMS.register("large_light_blue_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_LIGHT_BLUE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> BLUE_GLARIUM_BLOCK = ITEMS.register("blue_glarium_block", () ->
            new BlockItem(EchoesBlocks.BLUE_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> BLUE_GLARIUM_LAMP = ITEMS.register("blue_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.BLUE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_BLUE_GLARIUM_LAMP = ITEMS.register("large_blue_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_BLUE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PURPLE_GLARIUM_BLOCK = ITEMS.register("purple_glarium_block", () ->
            new BlockItem(EchoesBlocks.PURPLE_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PURPLE_GLARIUM_LAMP = ITEMS.register("purple_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.PURPLE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_PURPLE_GLARIUM_LAMP = ITEMS.register("large_purple_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_PURPLE_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> MAGENTA_GLARIUM_BLOCK = ITEMS.register("magenta_glarium_block", () ->
            new BlockItem(EchoesBlocks.MAGENTA_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> MAGENTA_GLARIUM_LAMP = ITEMS.register("magenta_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.MAGENTA_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_MAGENTA_GLARIUM_LAMP = ITEMS.register("large_magenta_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_MAGENTA_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PINK_GLARIUM_BLOCK = ITEMS.register("pink_glarium_block", () ->
            new BlockItem(EchoesBlocks.PINK_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PINK_GLARIUM_LAMP = ITEMS.register("pink_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.PINK_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_PINK_GLARIUM_LAMP = ITEMS.register("large_pink_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_PINK_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> BROWN_GLARIUM_BLOCK = ITEMS.register("brown_glarium_block", () ->
            new BlockItem(EchoesBlocks.BROWN_GLARIUM_BLOCK.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> BROWN_GLARIUM_LAMP = ITEMS.register("brown_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.BROWN_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> LARGE_BROWN_GLARIUM_LAMP = ITEMS.register("large_brown_glarium_lamp", () ->
            new BlockItem(EchoesBlocks.LARGE_BROWN_GLARIUM_LAMP.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<StandingAndWallBlockItem> SHIMMER_TORCH = ITEMS.register("shimmer_torch", () ->
            new StandingAndWallBlockItem(EchoesBlocks.SHIMMER_TORCH.get(),EchoesBlocks.WALL_SHIMMER_TORCH.get(), new Item.Properties().rarity(Rarity.COMMON), Direction.DOWN));

    public static final RegistryObject<BlockItem> LLERAE_LOG = ITEMS.register("llerae_log", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_LOG.get(), new Item.Properties().rarity(Rarity.COMMON),800));
    public static final RegistryObject<BlockItem> STRIPPED_LLERAE_LOG = ITEMS.register("stripped_llerae_log", () ->
            new FuelBlockItem(EchoesBlocks.STRIPPED_LLERAE_LOG.get(), new Item.Properties().rarity(Rarity.COMMON), 800));
    public static final RegistryObject<BlockItem> LLERAE_PLANKS = ITEMS.register("llerae_planks", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_PLANKS.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_SLAB = ITEMS.register("llerae_slab", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_STAIRS = ITEMS.register("llerae_stairs", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_FENCE = ITEMS.register("llerae_fence", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_FENCE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_FENCE_GATE = ITEMS.register("llerae_fence_gate", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_FENCE_GATE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_DOOR = ITEMS.register("llerae_door", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_DOOR.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_TRAPDOOR = ITEMS.register("llerae_trapdoor", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_TRAPDOOR.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_BUTTON = ITEMS.register("llerae_button", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_BUTTON.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_PRESSURE_PLATE = ITEMS.register("llerae_pressure_plate", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_PRESSURE_PLATE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> LLERAE_LEAVES = ITEMS.register("llerae_leaves", () ->
            new FuelBlockItem(EchoesBlocks.LLERAE_LEAVES.get(), new Item.Properties().rarity(Rarity.COMMON), 100));
    public static final RegistryObject<BlockItem> BUDDING_LLERAE_LEAVES = ITEMS.register("budding_llerae_leaves", () ->
            new FuelBlockItem(EchoesBlocks.BUDDING_LLERAE_LEAVES.get(), new Item.Properties().rarity(Rarity.COMMON), 100));
    public static final RegistryObject<BlockItem> LLERAE_SAPLING = ITEMS.register("llerae_sapling", () ->
            new BlockItem(EchoesBlocks.LLERAE_SAPLING.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_LLERAE_MOSS = ITEMS.register("umbrelith_llerae_moss", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_LLERAE_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_LLERAE_MOSS = ITEMS.register("palestone_llerae_moss", () ->
            new BlockItem(EchoesBlocks.PALESTONE_LLERAE_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_LLERAE_MOSS = ITEMS.register("tealite_llerae_moss", () ->
            new BlockItem(EchoesBlocks.TEALITE_LLERAE_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_LLERAE_MOSS = ITEMS.register("verlith_llerae_moss", () ->
            new BlockItem(EchoesBlocks.VERLITH_LLERAE_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> SHIMMERDOWN_LOG = ITEMS.register("shimmerdown_log", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_LOG.get(), new Item.Properties().rarity(Rarity.COMMON),800));
    public static final RegistryObject<BlockItem> STRIPPED_SHIMMERDOWN_LOG = ITEMS.register("stripped_shimmerdown_log", () ->
            new FuelBlockItem(EchoesBlocks.STRIPPED_SHIMMERDOWN_LOG.get(), new Item.Properties().rarity(Rarity.COMMON), 800));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_PLANKS = ITEMS.register("shimmerdown_planks", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_PLANKS.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_SLAB = ITEMS.register("shimmerdown_slab", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_SLAB.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_STAIRS = ITEMS.register("shimmerdown_stairs", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_STAIRS.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_FENCE = ITEMS.register("shimmerdown_fence", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_FENCE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_FENCE_GATE = ITEMS.register("shimmerdown_fence_gate", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_FENCE_GATE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_DOOR = ITEMS.register("shimmerdown_door", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_DOOR.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_TRAPDOOR = ITEMS.register("shimmerdown_trapdoor", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_TRAPDOOR.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_BUTTON = ITEMS.register("shimmerdown_button", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_BUTTON.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> SHIMMERDOWN_PRESSURE_PLATE = ITEMS.register("shimmerdown_pressure_plate", () ->
            new FuelBlockItem(EchoesBlocks.SHIMMERDOWN_PRESSURE_PLATE.get(), new Item.Properties().rarity(Rarity.COMMON), 400));
    public static final RegistryObject<BlockItem> COBALT_SHIMMERDOWN_LEAVES = ITEMS.register("cobalt_shimmerdown_leaves", () ->
            new FuelBlockItem(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get(), new Item.Properties().rarity(Rarity.COMMON), 50));
    public static final RegistryObject<BlockItem> FLOWERING_COBALT_SHIMMERDOWN_LEAVES = ITEMS.register("flowering_cobalt_shimmerdown_leaves", () ->
            new FuelBlockItem(EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get(), new Item.Properties().rarity(Rarity.COMMON), 100));
    public static final RegistryObject<BlockItem> COBALT_SHIMMERDOWN_SAPLING = ITEMS.register("cobalt_shimmerdown_sapling", () ->
            new FuelBlockItem(EchoesBlocks.COBALT_SHIMMERDOWN_SAPLING.get(), new Item.Properties().rarity(Rarity.COMMON), 100));
    public static final RegistryObject<BlockItem> UMBRELITH_COBALT_MOSS = ITEMS.register("umbrelith_cobalt_moss", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_COBALT_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> PALESTONE_COBALT_MOSS = ITEMS.register("palestone_cobalt_moss", () ->
            new BlockItem(EchoesBlocks.PALESTONE_COBALT_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> TEALITE_COBALT_MOSS = ITEMS.register("tealite_cobalt_moss", () ->
            new BlockItem(EchoesBlocks.TEALITE_COBALT_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> VERLITH_COBALT_MOSS = ITEMS.register("verlith_cobalt_moss", () ->
            new BlockItem(EchoesBlocks.VERLITH_COBALT_MOSS.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> COBALT_TUFT_SMALL = ITEMS.register("cobalt_tuft_small", () ->
            new BlockItem(EchoesBlocks.COBALT_TUFT_SMALL.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<BlockItem> COBALT_MOSS_LAYER = ITEMS.register("cobalt_moss_layer", () ->
            new BlockItem(EchoesBlocks.COBALT_MOSS_LAYER.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> INK_TENDRIL = ITEMS.register("ink_tendril", () ->
            new BlockItem(EchoesBlocks.INK_TENDRIL.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> RED_DUSTER = ITEMS.register("red_duster", () ->
            new BlockItem(EchoesBlocks.RED_DUSTER.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> GREEN_DUSTER = ITEMS.register("green_duster", () ->
            new BlockItem(EchoesBlocks.GREEN_DUSTER.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> BLUE_DUSTER = ITEMS.register("blue_duster", () ->
            new BlockItem(EchoesBlocks.BLUE_DUSTER.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> SPARKBUD = ITEMS.register("sparkbud", () ->
            new BlockItem(EchoesBlocks.SPARKBUD.get(), new Item.Properties().rarity(Rarity.COMMON)));


    public static final RegistryObject<BlockItem> BANDSAW = ITEMS.register("bandsaw", () ->
            new BlockItem(EchoesBlocks.BANDSAW.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<BlockItem> UMBRELITH_FURNACE = ITEMS.register("umbrelith_furnace", () ->
            new BlockItem(EchoesBlocks.UMBRELITH_FURNACE.get(), new Item.Properties().rarity(Rarity.COMMON)));



    //TOOLS
    public static final RegistryObject<Item> VERDANTINE_SWORD = ITEMS.register("verdantine_sword", () ->
            new SwordItem(EchoesTiers.VERDANTINE, 3, -2.4f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_SHOVEL = ITEMS.register("verdantine_shovel", () ->
            new ShovelItem(EchoesTiers.VERDANTINE, 1.5f, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_PICKAXE = ITEMS.register("verdantine_pickaxe", () ->
            new PickaxeItem(EchoesTiers.VERDANTINE, 1, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_AXE = ITEMS.register("verdantine_axe", () ->
            new AxeItem(EchoesTiers.VERDANTINE, 5, -3.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_HOE = ITEMS.register("verdantine_hoe", () ->
            new HoeItem(EchoesTiers.VERDANTINE, 0, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> CHLORIUM_SWORD = ITEMS.register("chlorium_sword", () ->
            new SwordItem(EchoesTiers.CHLORIUM, 3, -2.4f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHLORIUM_SHOVEL = ITEMS.register("chlorium_shovel", () ->
            new ShovelItem(EchoesTiers.CHLORIUM, 1.5f, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHLORIUM_PICKAXE = ITEMS.register("chlorium_pickaxe", () ->
            new PickaxeItem(EchoesTiers.CHLORIUM, 1, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHLORIUM_AXE = ITEMS.register("chlorium_axe", () ->
            new AxeItem(EchoesTiers.CHLORIUM, 5, -3.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CHLORIUM_HOE = ITEMS.register("chlorium_hoe", () ->
            new HoeItem(EchoesTiers.CHLORIUM, 0, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> CINDRITE_SWORD = ITEMS.register("cindrite_sword", () ->
            new SwordItem(EchoesTiers.CINDRITE, 3, -2.4f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CINDRITE_SHOVEL = ITEMS.register("cindrite_shovel", () ->
            new ShovelItem(EchoesTiers.CINDRITE, 1.5f, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CINDRITE_PICKAXE = ITEMS.register("cindrite_pickaxe", () ->
            new PickaxeItem(EchoesTiers.CINDRITE, 1, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CINDRITE_AXE = ITEMS.register("cindrite_axe", () ->
            new AxeItem(EchoesTiers.CINDRITE, 5, -3.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CINDRITE_HOE = ITEMS.register("cindrite_hoe", () ->
            new HoeItem(EchoesTiers.CINDRITE, 0, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));

    //public static final RegistryObject<Item> CHORUSITE_SWORD = ITEMS.register("chorusite_sword", () ->
    //        new SwordItem(EchoesTiers.CHORUSITE, 3, -2.4f, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_SHOVEL = ITEMS.register("chorusite_shovel", () ->
    //        new ShovelItem(EchoesTiers.CHORUSITE, 1.5f, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_PICKAXE = ITEMS.register("chorusite_pickaxe", () ->
    //        new PickaxeItem(EchoesTiers.CHORUSITE, 1, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_AXE = ITEMS.register("chorusite_axe", () ->
    //        new AxeItem(EchoesTiers.CHORUSITE, 5, -3.2f, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_HOE = ITEMS.register("chorusite_hoe", () ->
    //        new HoeItem(EchoesTiers.CHORUSITE, 0, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> NERON_SWORD = ITEMS.register("neron_sword", () ->
            new SwordItem(EchoesTiers.NERON, 3, -2.4f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_SHOVEL = ITEMS.register("neron_shovel", () ->
            new ShovelItem(EchoesTiers.NERON, 1.5f, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_PICKAXE = ITEMS.register("neron_pickaxe", () ->
            new PickaxeItem(EchoesTiers.NERON, 1, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_AXE = ITEMS.register("neron_axe", () ->
            new AxeItem(EchoesTiers.NERON, 5, -3.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_HOE = ITEMS.register("neron_hoe", () ->
            new HoeItem(EchoesTiers.NERON, 0, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> AZURETINE_SWORD = ITEMS.register("azuretine_sword", () ->
            new SwordItem(EchoesTiers.AZURETINE, 3, -2.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_SHOVEL = ITEMS.register("azuretine_shovel", () ->
            new ShovelItem(EchoesTiers.AZURETINE, 1.5f, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_PICKAXE = ITEMS.register("azuretine_pickaxe", () ->
            new PickaxeItem(EchoesTiers.AZURETINE, 1, -2.6f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_AXE = ITEMS.register("azuretine_axe", () ->
            new AxeItem(EchoesTiers.AZURETINE, 5, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_HOE = ITEMS.register("azuretine_hoe", () ->
            new HoeItem(EchoesTiers.AZURETINE, 0, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> XIRIUM_SWORD = ITEMS.register("xirium_sword", () ->
            new SwordItem(EchoesTiers.XIRIUM, 3, -2.2f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> XIRIUM_SHOVEL = ITEMS.register("xirium_shovel", () ->
            new ShovelItem(EchoesTiers.XIRIUM, 1.5f, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> XIRIUM_PICKAXE = ITEMS.register("xirium_pickaxe", () ->
            new PickaxeItem(EchoesTiers.XIRIUM, 1, -2.6f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> XIRIUM_AXE = ITEMS.register("xirium_axe", () ->
            new AxeItem(EchoesTiers.XIRIUM, 5, -3.0f, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> XIRIUM_HOE = ITEMS.register("xirium_hoe", () ->
            new HoeItem(EchoesTiers.XIRIUM, 0, -2.8f, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> PERIALIGHT_SWORD = ITEMS.register("perialight_sword", () ->
            new SwordItem(EchoesTiers.PERIALIGHT, 3, -2.0f, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_SHOVEL = ITEMS.register("perialight_shovel", () ->
            new ShovelItem(EchoesTiers.PERIALIGHT, 1.5f, -2.6f, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_PICKAXE = ITEMS.register("perialight_pickaxe", () ->
            new PickaxeItem(EchoesTiers.PERIALIGHT, 1, -2.2f, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_AXE = ITEMS.register("perialight_axe", () ->
            new AxeItem(EchoesTiers.PERIALIGHT, 5, -2.8f, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_HOE = ITEMS.register("perialight_hoe", () ->
            new HoeItem(EchoesTiers.PERIALIGHT, 0, -2.6f, new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> VIOLUM_SWORD = ITEMS.register("violum_sword", () ->
            new SwordItem(EchoesTiers.VIOLUM, 3, -1.8f, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_SHOVEL = ITEMS.register("violum_shovel", () ->
            new ShovelItem(EchoesTiers.VIOLUM, 1.5f, -2.4f, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_PICKAXE = ITEMS.register("violum_pickaxe", () ->
            new PickaxeItem(EchoesTiers.VIOLUM, 1, -2.0f, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_AXE = ITEMS.register("violum_axe", () ->
            new AxeItem(EchoesTiers.VIOLUM, 5, -2.6f, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_HOE = ITEMS.register("violum_hoe", () ->
            new HoeItem(EchoesTiers.VIOLUM, 0, -2.4f, new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> VERDANTINE_HELMET = ITEMS.register("verdantine_helmet",
            () -> new ArmorItem(EchoesArmorMaterials.VERDANTINE, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_CHESTPLATE = ITEMS.register("verdantine_chestplate",
            () -> new ArmorItem(EchoesArmorMaterials.VERDANTINE, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_LEGGINGS = ITEMS.register("verdantine_leggings",
            () -> new ArmorItem(EchoesArmorMaterials.VERDANTINE, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDANTINE_BOOTS = ITEMS.register("verdantine_boots",
            () -> new ArmorItem(EchoesArmorMaterials.VERDANTINE, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.COMMON)));

    //public static final RegistryObject<Item> CHORUSITE_HELMET = ITEMS.register("chorusite_helmet",
    //        () -> new ArmorItem(EchoesArmorMaterials.CHORUSITE, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_CHESTPLATE = ITEMS.register("chorusite_chestplate",
    //        () -> new ArmorItem(EchoesArmorMaterials.CHORUSITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_LEGGINGS = ITEMS.register("chorusite_leggings",
    //        () -> new ArmorItem(EchoesArmorMaterials.CHORUSITE, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> CHORUSITE_BOOTS = ITEMS.register("chorusite_boots",
    //        () -> new ArmorItem(EchoesArmorMaterials.CHORUSITE, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> NERON_HELMET = ITEMS.register("neron_helmet",
            () -> new ArmorItem(EchoesArmorMaterials.NERON, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_CHESTPLATE = ITEMS.register("neron_chestplate",
            () -> new ArmorItem(EchoesArmorMaterials.NERON, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_LEGGINGS = ITEMS.register("neron_leggings",
            () -> new ArmorItem(EchoesArmorMaterials.NERON, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> NERON_BOOTS = ITEMS.register("neron_boots",
            () -> new ArmorItem(EchoesArmorMaterials.NERON, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> AZURETINE_HELMET = ITEMS.register("azuretine_helmet",
            () -> new ArmorItem(EchoesArmorMaterials.AZURETINE, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_CHESTPLATE = ITEMS.register("azuretine_chestplate",
            () -> new ArmorItem(EchoesArmorMaterials.AZURETINE, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_LEGGINGS = ITEMS.register("azuretine_leggings",
            () -> new ArmorItem(EchoesArmorMaterials.AZURETINE, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> AZURETINE_BOOTS = ITEMS.register("azuretine_boots",
            () -> new ArmorItem(EchoesArmorMaterials.AZURETINE, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> PERIALIGHT_HELMET = ITEMS.register("perialight_helmet",
            () -> new ArmorItem(EchoesArmorMaterials.PERIALIGHT, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_CHESTPLATE = ITEMS.register("perialight_chestplate",
            () -> new ArmorItem(EchoesArmorMaterials.PERIALIGHT, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_LEGGINGS = ITEMS.register("perialight_leggings",
            () -> new ArmorItem(EchoesArmorMaterials.PERIALIGHT, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PERIALIGHT_BOOTS = ITEMS.register("perialight_boots",
            () -> new ArmorItem(EchoesArmorMaterials.PERIALIGHT, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> VIOLUM_HELMET = ITEMS.register("violum_helmet",
            () -> new ArmorItem(EchoesArmorMaterials.VIOLUM, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_CHESTPLATE = ITEMS.register("violum_chestplate",
            () -> new ArmorItem(EchoesArmorMaterials.VIOLUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_LEGGINGS = ITEMS.register("violum_leggings",
            () -> new ArmorItem(EchoesArmorMaterials.VIOLUM, ArmorItem.Type.LEGGINGS, new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> VIOLUM_BOOTS = ITEMS.register("violum_boots",
            () -> new ArmorItem(EchoesArmorMaterials.VIOLUM, ArmorItem.Type.BOOTS, new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> LLERAE_FRUIT = ITEMS.register("llerae_fruit",
            () -> new Item(new Item.Properties().food(EchoesFoods.LLERAE_FRUIT).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> BAKED_LLERAE_FRUIT = ITEMS.register("baked_llerae_fruit",
            () -> new Item(new Item.Properties().food(EchoesFoods.BAKED_LLERAE_FRUIT).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> COBALT_SHIMMER_FLOWER = ITEMS.register("cobalt_shimmer_flower",
            () -> new Item(new Item.Properties().food(EchoesFoods.COBALT_SHIMMER_FLOWER).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SHIMMER_STUFFED_LLERAE = ITEMS.register("shimmer_stuffed_llerae",
            () -> new Item(new Item.Properties().food(EchoesFoods.SHIMMER_STUFFED_LLERAE).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> COBALT_TUFT_CONE = ITEMS.register("cobalt_tuft_cone",
            () -> new Item(new Item.Properties().food(EchoesFoods.COBALT_TUFT_CONE).rarity(Rarity.COMMON)));
    //public static final RegistryObject<Item> MOSSBERRY = ITEMS.register("mossberry",
    //        () -> new ItemNameBlockItem(EchoesBlocks.MOSSBERRY.get(),new Item.Properties().food(EchoesFoods.MOSSBERRY).rarity(Rarity.COMMON)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static Item registerBlock(Block pBlock) {
        return registerBlock(new BlockItem(pBlock, new Item.Properties()).getBlock());
    }
}
