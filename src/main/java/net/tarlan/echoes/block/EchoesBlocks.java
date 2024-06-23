package net.tarlan.echoes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.custom.*;
import net.tarlan.echoes.util.EchoesTags;
import net.tarlan.echoes.util.EchoesWoodType;
import net.tarlan.echoes.worldgen.tree.CobaltShimmerdownTreeGrower;
import net.tarlan.echoes.worldgen.tree.LleraeTreeGrower;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.Blocks.TORCH;

public class EchoesBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Echoes.MOD_ID);

    //METALS
    public static final RegistryObject<Block> RAW_VERDANTINE_BLOCK = registerBlock("raw_verdantine_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANTINE_BLOCK = registerBlock("verdantine_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHORUSITE_BLOCK = registerBlock("chorusite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_AZURETINE_BLOCK = registerBlock("raw_azuretine_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(9,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AZURETINE_BLOCK = registerBlock("azuretine_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK).strength(18,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_NERON_BLOCK = registerBlock("raw_neron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(5,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NERON_BLOCK = registerBlock("neron_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(5,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VIOLUM_NODULE_BLOCK = registerBlock("violum_nodule_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK).strength(50,1200).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VIOLUM_BLOCK = registerBlock("violum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHERITE_BLOCK).strength(100,1200).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHLORIUM_BLOCK = registerBlock("chlorium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CINDRITE_BLOCK = registerBlock("cindrite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MAGMAR_BLOCK = registerBlock("magmar_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE).strength(1,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLUM_BLOCK = registerBlock("pearlum_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PERIALIGHT_BLOCK = registerBlock("perialight_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL).strength(50,6).requiresCorrectToolForDrops().emissiveRendering(EchoesBlocks::always)) {
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 15;}});
    public static final RegistryObject<Block> REGITE_BLOCK = registerBlock("regite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE).strength(1,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> XIRIUM_BLOCK = registerBlock("xirium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BRIMNITE_BLOCK = registerBlock("brimnite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.STONE).strength(1,6).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RESONANE_BLOCK = registerBlock("resonane_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()) {
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 5;}});
    public static final RegistryObject<Block> UMBRITE_BLOCK = registerBlock("umbrite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.STONE).strength(3,6).requiresCorrectToolForDrops()));

    //STONE
    //TODO: Add Red, Orange, Yellow, Lime, Green, Cyan, Light Blue, Blue, Purple, Magenta, Pink, Brown, & Gray Palestone Variants
    public static final RegistryObject<Block> VERLITH = registerBlock("verlith", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> ROUGH_VERLITH_SLAB = registerBlock("rough_verlith_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_VERLITH_STAIRS = registerBlock("rough_verlith_stairs", () -> new StairBlock(() -> EchoesBlocks.VERLITH.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_VERLITH_WALL = registerBlock("rough_verlith_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_BLOCK = registerBlock("verlith_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> VERLITH_SLAB = registerBlock("verlith_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_STAIRS = registerBlock("verlith_stairs", () -> new StairBlock(() -> EchoesBlocks.VERLITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(4,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_WALL = registerBlock("verlith_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_TILES = registerBlock("verlith_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_VERLITH = registerBlock("chiseled_verlith", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> VERLITH_PILLAR = registerBlock("verlith_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_PILLAR_CROSS = registerBlock("verlith_pillar_cross", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> TEALITE = registerBlock("tealite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> ROUGH_TEALITE_SLAB = registerBlock("rough_tealite_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(2, 12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_TEALITE_STAIRS = registerBlock("rough_tealite_stairs", () -> new StairBlock(() -> EchoesBlocks.TEALITE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(2, 12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_TEALITE_WALL = registerBlock("rough_tealite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_BLOCK = registerBlock("tealite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> TEALITE_SLAB = registerBlock("tealite_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_STAIRS = registerBlock("tealite_stairs", () -> new StairBlock(() -> EchoesBlocks.TEALITE_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(4,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_WALL = registerBlock("tealite_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_TILES = registerBlock("tealite_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_TEALITE = registerBlock("chiseled_tealite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> TEALITE_PILLAR = registerBlock("tealite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_PILLAR_CROSS = registerBlock("tealite_pillar_cross", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PALESTONE = registerBlock("palestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> ROUGH_PALESTONE_SLAB = registerBlock("rough_palestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2, 12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_PALESTONE_STAIRS = registerBlock("rough_palestone_stairs", () -> new StairBlock(() -> EchoesBlocks.PALESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2, 12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_PALESTONE_WALL = registerBlock("rough_palestone_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_BLOCK = registerBlock("palestone_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> PALESTONE_SLAB = registerBlock("palestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_STAIRS = registerBlock("palestone_stairs", () -> new StairBlock(() -> EchoesBlocks.PALESTONE_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(4,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_WALL = registerBlock("palestone_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_TILES = registerBlock("palestone_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_PALESTONE = registerBlock("chiseled_palestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> PALESTONE_PILLAR = registerBlock("palestone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_PILLAR_CROSS = registerBlock("palestone_pillar_cross", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(3,14).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> UMBRELITH = registerBlock("umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> COBBLED_UMBRELITH = registerBlock("cobbled_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FRAMED_UMBRELITH = registerBlock("framed_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FRAMED_COBBLED_UMBRELITH = registerBlock("framed_cobbled_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> ROUGH_UMBRELITH_SLAB = registerBlock("rough_umbrelith_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4f,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> ROUGH_UMBRELITH_STAIRS = registerBlock("rough_umbrelith_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4f,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROUGH_UMBRELITH_WALL = registerBlock("rough_umbrelith_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(4,12).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> SMOOTH_UMBRELITH = registerBlock("smooth_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f,14).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> SMOOTH_UMBRELITH_SLAB = registerBlock("smooth_umbrelith_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f,14).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> SMOOTH_UMBRELITH_STAIRS = registerBlock("smooth_umbrelith_stairs", () -> new StairBlock(() -> EchoesBlocks.SMOOTH_UMBRELITH.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5f,14).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> SMOOTH_UMBRELITH_WALL = registerBlock("smooth_umbrelith_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(5,14).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BLOCK = registerBlock("umbrelith_block", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BLOCK_DAMAGED = registerBlock("umbrelith_block_damaged", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BLOCK_CRACKED = registerBlock("umbrelith_block_cracked", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BLOCK_FRACTURED = registerBlock("umbrelith_block_fractured", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> UMBRELITH_SLAB = registerBlock("umbrelith_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_STAIRS = registerBlock("umbrelith_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_WALL = registerBlock("umbrelith_wall", () -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6,14).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> CHISELED_UMBRELITH = registerBlock("chiseled_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BRICKS = registerBlock("umbrelith_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> UMBRELITH_BRICK_SLAB = registerBlock("umbrelith_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_BRICK_STAIRS = registerBlock("umbrelith_brick_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_TILES = registerBlock("umbrelith_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> UMBRELITH_TILE_SLAB = registerBlock("umbrelith_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_TILE_STAIRS = registerBlock("umbrelith_tile_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> THREE_BY_THREE_UMBRELITH_TILES = registerBlock("three_by_three_umbrelith_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_PANEL = registerBlock("umbrelith_panel", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_PANEL_INSET = registerBlock("umbrelith_panel_inset", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> REINFORCED_UMBRELITH_PANEL = registerBlock("reinforced_umbrelith_panel", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> REINFORCED_UMBRELITH_PANEL_INSET = registerBlock("reinforced_umbrelith_panel_inset", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FINE_UMBRELITH_TILES = registerBlock("fine_umbrelith_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> FINE_UMBRELITH_TILE_SLAB = registerBlock("fine_umbrelith_tile_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FINE_UMBRELITH_TILE_STAIRS = registerBlock("fine_umbrelith_tile_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FINE_UMBRELITH_BRICKS = registerBlock("fine_umbrelith_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> FINE_UMBRELITH_BRICK_SLAB = registerBlock("fine_umbrelith_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> FINE_UMBRELITH_BRICK_STAIRS = registerBlock("fine_umbrelith_brick_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> PATTERNED_UMBRELITH_TILES = registerBlock("patterned_umbrelith_tiles", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> CUT_UMBRELITH = registerBlock("cut_umbrelith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> UMBRELITH_PILLAR = registerBlock("umbrelith_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<SlabBlock> UMBRELITH_PILLAR_SLAB = registerBlock("umbrelith_pillar_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_PILLAR_STAIRS = registerBlock("umbrelith_pillar_stairs", () -> new StairBlock(() -> EchoesBlocks.UMBRELITH_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_PILLAR_CROSS = registerBlock("umbrelith_pillar_cross", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().noOcclusion().mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> UMBRELITH_OMNI_SLAB = registerBlock("umbrelith_omni_slab", () -> new EchoesSlabBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    //public static final RegistryObject<Block> UMBRELITH_BLOCK_CONNECTING = registerBlock("umbrelith_block_connecting", () -> new EchoesConnectedBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(6f,16).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GRAY)));
    // Bluestone Blocks
    public static final RegistryObject<Block> BLUESTONE = registerBlock("bluestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SMOOTH_BLUESTONE = registerBlock("smooth_bluestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_BLUESTONE = registerBlock("polished_bluestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUESTONE_BRICKS = registerBlock("bluestone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> QUARTZ_STYLE_BLUESTONE_BRICKS = registerBlock("quartz_style_bluestone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUESTONE_STAIRS = registerBlock("bluestone_stairs", () -> new StairBlock(() -> POLISHED_BLUESTONE.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> BLUESTONE_SLAB = registerBlock("bluestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUESTONE_WALL = registerBlock("bluestone_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> BLUESTONE_PILLAR = registerBlock("bluestone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_BLUESTONE = registerBlock("chiseled_bluestone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    // Oder Blocks
    public static final RegistryObject<Block> ODER = registerBlock("oder", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> POLISHED_ODER = registerBlock("polished_oder", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ODER_BRICKS = registerBlock("oder_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ODER_TILES = registerBlock("oder_tiles", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> ODER_PILLAR = registerBlock("oder_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ODER_STAIRS = registerBlock("oder_stairs", () -> new StairBlock(() -> POLISHED_ODER.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> ODER_SLAB = registerBlock("oder_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ODER_WALL = registerBlock("oder_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.5f,4).requiresCorrectToolForDrops()));



    public static final RegistryObject<Block> MONOLITH = registerBlock("monolith", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(300,12000).requiresCorrectToolForDrops().isRedstoneConductor(EchoesBlocks::never)));

    public static final RegistryObject<Block> VOID_PORTAL = registerBlock("void_portal", () -> new EchoesPortalBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3,12).noLootTable().noOcclusion().noCollission()));
    //public static final Block END_PORTAL = register("end_portal", new EndPortalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).noCollission().lightLevel((p_152690_) -> {return 15;
    //public static final Block NETHER_PORTAL = register("nether_portal", new NetherPortalBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().strength(-1.0F).sound(SoundType.GLASS).lightLevel((p_50872_) -> {return 15;

    //ORES
    public static final RegistryObject<Block> VERDANTINE_ORE = registerBlock("verdantine_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AZURETINE_ORE = registerBlock("azuretine_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(12,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NERON_ORE = registerBlock("neron_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(9,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLARIUM_ORE = registerBlock("glarium_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(9,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VIOLUM_DEPOSIT = registerBlock("violum_deposit", () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(100,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHLORIUM_ORE = registerBlock("chlorium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> CINDRITE_ORE = registerBlock("cindrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(0, 3)));
    public static final RegistryObject<Block> DEEPSLATE_CINDRITE_ORE = registerBlock("deepslate_cindrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE).strength(5,9).requiresCorrectToolForDrops(), UniformInt.of(0, 3)));
    public static final RegistryObject<Block> NETHER_CINDRITE_ORE = registerBlock("nether_cindrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).sound(SoundType.NETHER_ORE).strength(3,6).requiresCorrectToolForDrops(), UniformInt.of(0, 4)));
    public static final RegistryObject<Block> MAGMAR_ORE = registerBlock("magmar_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).sound(SoundType.NETHER_ORE).strength(3,6).requiresCorrectToolForDrops(), UniformInt.of(0, 4)));
    public static final RegistryObject<Block> PEARLUM_ORE = registerBlock("pearlum_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(9,12).requiresCorrectToolForDrops(), UniformInt.of(2, 4)));
    public static final RegistryObject<Block> PERIALIGHT_ORE = registerBlock("perialight_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(50,12).requiresCorrectToolForDrops(), UniformInt.of(5, 12)) {
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 5;}});
    public static final RegistryObject<Block> END_PERIALIGHT_ORE = registerBlock("end_perialight_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(50,12).requiresCorrectToolForDrops(), UniformInt.of(3, 7)) {
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 10;}});
    public static final RegistryObject<Block> REGITE_ORE = registerBlock("regite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(1, 2)));
    public static final RegistryObject<Block> XIRIUM_ORE = registerBlock("xirium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));
    public static final RegistryObject<Block> BRIMNITE_ORE = registerBlock("brimnite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).sound(SoundType.NETHER_ORE).strength(3,6).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> UMBRITE_ORE = registerBlock("umbrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> DEEPSLATE_UMBRITE_ORE = registerBlock("deepslate_umbrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).sound(SoundType.DEEPSLATE).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> END_UMBRITE_ORE = registerBlock("end_umbrite_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).sound(SoundType.METAL).strength(5,12).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));

    public static final RegistryObject<Block> CHORUS_BLOCK = registerBlock("chorus_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STEM).strength(2,6)));
    public static final RegistryObject<Block> CHORUS_PLANKS = registerBlock("chorus_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STEM).strength(2,6)));


    public static final RegistryObject<RedstoneLampBlock> RAW_GLARIUM_BLOCK = registerBlock("raw_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(5)).sound(SoundType.METAL).strength(5,6).requiresCorrectToolForDrops()));


    //TODO: Heighten Contrast For Colored Glarium
    public static final RegistryObject<RedstoneLampBlock> GLARIUM_BLOCK = registerBlock("glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> GLARIUM_LAMP = registerBlock("glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_GLARIUM_LAMP = registerBlock("large_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> MONOCHROME_GLARIUM_BLOCK = registerBlock("monochrome_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> MONOCHROME_GLARIUM_LAMP = registerBlock("monochrome_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_MONOCHROME_GLARIUM_LAMP = registerBlock("large_monochrome_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_GRAY).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> RED_GLARIUM_BLOCK = registerBlock("red_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_RED).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> RED_GLARIUM_LAMP = registerBlock("red_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_RED).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_RED_GLARIUM_LAMP = registerBlock("large_red_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_RED).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> ORANGE_GLARIUM_BLOCK = registerBlock("orange_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> ORANGE_GLARIUM_LAMP = registerBlock("orange_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_ORANGE_GLARIUM_LAMP = registerBlock("large_orange_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> YELLOW_GLARIUM_BLOCK = registerBlock("yellow_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_YELLOW).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> YELLOW_GLARIUM_LAMP = registerBlock("yellow_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_YELLOW).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_YELLOW_GLARIUM_LAMP = registerBlock("large_yellow_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_YELLOW).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LIME_GLARIUM_BLOCK = registerBlock("lime_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LIME_GLARIUM_LAMP = registerBlock("lime_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_LIME_GLARIUM_LAMP = registerBlock("large_lime_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> GREEN_GLARIUM_BLOCK = registerBlock("green_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> GREEN_GLARIUM_LAMP = registerBlock("green_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_GREEN_GLARIUM_LAMP = registerBlock("large_green_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_GREEN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LIGHT_BLUE_GLARIUM_BLOCK = registerBlock("light_blue_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LIGHT_BLUE_GLARIUM_LAMP = registerBlock("light_blue_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_LIGHT_BLUE_GLARIUM_LAMP = registerBlock("large_light_blue_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> BLUE_GLARIUM_BLOCK = registerBlock("blue_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> BLUE_GLARIUM_LAMP = registerBlock("blue_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_BLUE_GLARIUM_LAMP = registerBlock("large_blue_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BLUE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> PURPLE_GLARIUM_BLOCK = registerBlock("purple_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_PURPLE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> PURPLE_GLARIUM_LAMP = registerBlock("purple_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_PURPLE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_PURPLE_GLARIUM_LAMP = registerBlock("large_purple_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_PURPLE).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> MAGENTA_GLARIUM_BLOCK = registerBlock("magenta_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_MAGENTA).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> MAGENTA_GLARIUM_LAMP = registerBlock("magenta_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_MAGENTA).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_MAGENTA_GLARIUM_LAMP = registerBlock("large_magenta_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_MAGENTA).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> PINK_GLARIUM_BLOCK = registerBlock("pink_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> PINK_GLARIUM_LAMP = registerBlock("pink_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_PINK_GLARIUM_LAMP = registerBlock("large_pink_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> BROWN_GLARIUM_BLOCK = registerBlock("brown_glarium_block", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.COLOR_BROWN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> BROWN_GLARIUM_LAMP = registerBlock("brown_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BROWN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));
    public static final RegistryObject<RedstoneLampBlock> LARGE_BROWN_GLARIUM_LAMP = registerBlock("large_brown_glarium_lamp", () -> new EchoesLampBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BROWN).lightLevel(litBlockEmission(15)).sound(SoundType.METAL).strength(6,16).requiresCorrectToolForDrops()));

    public static final RegistryObject<TorchBlock> SHIMMER_TORCH = registerBlock("shimmer_torch", () -> new TorchBlock(BlockBehaviour.Properties.copy(TORCH).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel((p_50755_) -> 7), ParticleTypes.UNDERWATER));
    public static final RegistryObject<WallTorchBlock> WALL_SHIMMER_TORCH = registerBlock("wall_shimmer_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.copy(TORCH).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel((p_50755_) -> 7), ParticleTypes.UNDERWATER));


    public static final RegistryObject<FlammableRotatedPillarBlock> LLERAE_LOG = registerBlock("llerae_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()));
    public static final RegistryObject<FlammableRotatedPillarBlock> STRIPPED_LLERAE_LOG = registerBlock("stripped_llerae_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()));
    public static final RegistryObject<Block> LLERAE_PLANKS = registerBlock("llerae_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_SLAB = registerBlock("llerae_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_STAIRS = registerBlock("llerae_stairs", () -> new StairBlock(() -> EchoesBlocks.LLERAE_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_FENCE = registerBlock("llerae_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_FENCE_GATE = registerBlock("llerae_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2f,2f).ignitedByLava(), EchoesWoodType.LLERAE) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_DOOR = registerBlock("llerae_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava().noOcclusion(), EchoesBlockSetType.LLERAE) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_TRAPDOOR = registerBlock("llerae_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava().noOcclusion(), EchoesBlockSetType.LLERAE) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_BUTTON = registerBlock("llerae_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava(), EchoesBlockSetType.LLERAE, 10, true) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}});
    public static final RegistryObject<Block> LLERAE_PRESSURE_PLATE = registerBlock("llerae_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).sound(SoundType.WOOD).strength(2f,2f).ignitedByLava(), EchoesBlockSetType.LLERAE) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 5;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 20;}
            @Override public int getPressedTime() {
            return 10;
        }});
    public static final RegistryObject<Block> LLERAE_LEAVES = registerBlock("llerae_leaves", () -> new EchoesLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.AZALEA_LEAVES).strength(0.1f,0.1f).ignitedByLava().noOcclusion()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 30;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 60;}
            @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                return 2;
            }});
    public static final RegistryObject<Block> BUDDING_LLERAE_LEAVES = registerBlock("budding_llerae_leaves", () -> new EchoesLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.AZALEA_LEAVES).strength(0.1f,0.1f).ignitedByLava().noOcclusion()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 30;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 60;}
            @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                return 6;
            }});
    public static final RegistryObject<SaplingBlock> LLERAE_SAPLING = registerBlock("llerae_sapling", () -> new SaplingBlock(new LleraeTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_BLACK).sound(SoundType.AZALEA_LEAVES).noOcclusion()) {
            @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
            @Override public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {if (!pLevel.isAreaLoaded(pPos, 1)) return;if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 0 && pLevel.getMaxLocalRawBrightness(pPos.above()) <= 7 && pRandom.nextInt(14) == 0) {this.advanceTree(pLevel, pPos, pState, pRandom);}if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 8 && pRandom.nextInt(7) == 0) {this.advanceTree(pLevel, pPos, pState, pRandom);}}});
    public static final RegistryObject<Block> UMBRELITH_LLERAE_MOSS = registerBlock("umbrelith_llerae_moss", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(4,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_LLERAE_MOSS = registerBlock("palestone_llerae_moss", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_LLERAE_MOSS = registerBlock("tealite_llerae_moss", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_LLERAE_MOSS = registerBlock("verlith_llerae_moss", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));

//.hasPostProcess(Blocks::always).emissiveRendering(Blocks::always)

    public static final RegistryObject<FlammableRotatedPillarBlock> SHIMMERDOWN_LOG = registerBlock("shimmerdown_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(1f,0f).ignitedByLava()));
    public static final RegistryObject<FlammableRotatedPillarBlock> STRIPPED_SHIMMERDOWN_LOG = registerBlock("stripped_shimmerdown_log", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(1f,0f).ignitedByLava()));
    public static final RegistryObject<Block> SHIMMERDOWN_PLANKS = registerBlock("shimmerdown_planks", () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(1f,0f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_SLAB = registerBlock("shimmerdown_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_STAIRS = registerBlock("shimmerdown_stairs", () -> new StairBlock(() -> EchoesBlocks.SHIMMERDOWN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_FENCE = registerBlock("shimmerdown_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_FENCE_GATE = registerBlock("shimmerdown_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava(), EchoesWoodType.SHIMMERDOWN) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_DOOR = registerBlock("shimmerdown_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava().noOcclusion(), EchoesBlockSetType.SHIMMERDOWN) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_TRAPDOOR = registerBlock("shimmerdown_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava().noOcclusion(), EchoesBlockSetType.SHIMMERDOWN) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_BUTTON = registerBlock("shimmerdown_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava(), EchoesBlockSetType.SHIMMERDOWN, 10, true) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}});
    public static final RegistryObject<Block> SHIMMERDOWN_PRESSURE_PLATE = registerBlock("shimmerdown_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STEM).strength(2f,2f).ignitedByLava(), EchoesBlockSetType.SHIMMERDOWN) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 10;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}
            @Override public int getPressedTime() {return 10;}});

    public static final RegistryObject<Block> COBALT_SHIMMERDOWN_LEAVES = registerBlock("cobalt_shimmerdown_leaves", () -> new EchoesLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.AZALEA_LEAVES).strength(0.05f,0.0f).ignitedByLava().noOcclusion()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 240;}
            @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 6;}});
    public static final RegistryObject<Block> FLOWERING_COBALT_SHIMMERDOWN_LEAVES = registerBlock("flowering_cobalt_shimmerdown_leaves", () -> new EchoesLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.AZALEA_LEAVES).strength(0.05f,0.0f).ignitedByLava().noOcclusion()) {
            @Override public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return true;}
            @Override public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 90;}
            @Override public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {return 240;}
            @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
                return 8;
            }});

    public static final RegistryObject<SaplingBlock> COBALT_SHIMMERDOWN_SAPLING = registerBlock("cobalt_shimmerdown_sapling", () -> new SaplingBlock(new CobaltShimmerdownTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).sound(SoundType.AZALEA_LEAVES).noOcclusion()) {
            @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
            @Override public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {if (!pLevel.isAreaLoaded(pPos, 1)) return; if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 0 && pLevel.getMaxLocalRawBrightness(pPos.above()) <= 4 && pRandom.nextInt(4) == 0) {this.advanceTree(pLevel, pPos, pState, pRandom);}if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 5 && pRandom.nextInt(14) == 0) {this.advanceTree(pLevel, pPos, pState, pRandom);}}});
    public static final RegistryObject<Block> UMBRELITH_COBALT_MOSS = registerBlock("umbrelith_cobalt_moss", () -> new EchoesCobaltMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().sound(SoundType.METAL).strength(4,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALESTONE_COBALT_MOSS = registerBlock("palestone_cobalt_moss", () -> new EchoesCobaltMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEALITE_COBALT_MOSS = registerBlock("tealite_cobalt_moss", () -> new EchoesCobaltMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERLITH_COBALT_MOSS = registerBlock("verlith_cobalt_moss", () -> new EchoesCobaltMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).randomTicks().sound(SoundType.METAL).strength(2,12).requiresCorrectToolForDrops()));

    //public static final Block GRASS = register("grass", new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> COBALT_TUFT_SMALL = registerBlock("cobalt_tuft_small", () -> new EchoesTuftBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.COLOR_BLUE).replaceable().noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));

    //public static final Block WHEAT = register("wheat", new CropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<CropBlock> MOSSBERRY = registerBlock("mossberry", () -> new MossberryBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}});

    //public static final Block MOSS_CARPET = register("moss_carpet", new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> COBALT_MOSS_CARPET = registerBlock("cobalt_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).strength(0.5F).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> INK_TENDRIL = registerBlock("ink_tendril", () -> new TallGrassBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(MapColor.TERRACOTTA_BLACK).replaceable().noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
        @Override public float getMaxHorizontalOffset() {return 0.75f;}
        @Override public float getMaxVerticalOffset() {return 0.05f;}});
    public static final RegistryObject<Block> RED_DUSTER = registerBlock("red_duster", () -> new TallGrassBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(MapColor.COLOR_RED).replaceable().noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
        @Override public float getMaxHorizontalOffset() {return 0.75f;}
        @Override public float getMaxVerticalOffset() {return 0.05f;}
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 6;}});
    public static final RegistryObject<Block> GREEN_DUSTER = registerBlock("green_duster", () -> new TallGrassBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(MapColor.COLOR_LIGHT_GREEN).replaceable().noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
        @Override public float getMaxHorizontalOffset() {return 0.75f;}
        @Override public float getMaxVerticalOffset() {return 0.05f;}
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 6;}});
    public static final RegistryObject<Block> BLUE_DUSTER = registerBlock("blue_duster", () -> new TallGrassBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(MapColor.COLOR_BLUE).replaceable().noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
        @Override public float getMaxHorizontalOffset() {return 0.75f;}
        @Override public float getMaxVerticalOffset() {return 0.05f;}
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 6;}});
    public static final RegistryObject<Block> SPARKBUD = registerBlock("sparkbud", () -> new TallGrassBlock(BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XYZ).mapColor(MapColor.TERRACOTTA_RED).replaceable().noCollission().randomTicks().instabreak().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)) {
        @Override public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION);}
        @Override public float getMaxHorizontalOffset() {return 0.75f;}
        @Override public float getMaxVerticalOffset() {return 0.05f;}
        @Override public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {return 6;}});





    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }
    private static Boolean always(BlockState p_50810_, BlockGetter p_50811_, BlockPos p_50812_) {return true;}
    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (lightLevel) -> lightLevel.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn;
        toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
    public static Block register(String pKey, Block pBlock) {
        return Registry.register(BuiltInRegistries.BLOCK, pKey, pBlock);
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
