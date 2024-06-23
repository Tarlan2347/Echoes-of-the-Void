package net.tarlan.echoes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tarlan.echoes.util.EchoesBlockStateProperties;

import javax.annotation.Nullable;


public class EchoesSlabBlock extends Block implements SimpleWaterloggedBlock {


    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<EchoesSlabType> TYPE = EchoesBlockStateProperties.ECHOES_SLAB_TYPE;
    protected final VoxelShape halfNorthAabb;
    protected final VoxelShape halfSouthAabb;
    protected final VoxelShape halfEastAabb;
    protected final VoxelShape halfWestAabb;
    protected final VoxelShape halfUpAabb;
    protected final VoxelShape halfDownAabb;
    protected final VoxelShape doubleZ;
    protected final VoxelShape doubleX;
    protected final VoxelShape doubleY;

    public EchoesSlabBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.UP));
        // Z Axis
        this.halfSouthAabb = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        this.halfNorthAabb = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        // X Axis
        this.halfEastAabb  = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
        this.halfWestAabb  = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        // Y Axis
        this.halfUpAabb    = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        this.halfDownAabb  = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        // Full Block
        this.doubleZ = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        this.doubleX = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        this.doubleY = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        EchoesSlabType type = pState.getValue(TYPE);
        if (type == EchoesSlabType.HALF) {
            if (direction == Direction.NORTH) {
                return this.halfNorthAabb;
            } else if (direction == Direction.SOUTH) {
                return this.halfSouthAabb;
            } else if (direction == Direction.EAST) {
                return this.halfEastAabb;
            } else if (direction == Direction.WEST) {
                return this.halfWestAabb;
            } else if (direction == Direction.DOWN) {
                return this.halfDownAabb;
            } else {
                // This covers the default case (UP or any other value)
                return this.halfUpAabb;
            }
        } else if (type == EchoesSlabType.DOUBLE) {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                return this.doubleZ;
            } else if (direction == Direction.EAST || direction == Direction.WEST) {
                return this.doubleX;
            } else {
                return this.doubleY;
            }
        } else {
            return this.halfUpAabb;
        }
    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor levelaccessor = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = pContext.getLevel().getBlockState(blockpos);
        // Double-up this slab, keep all other configurations, and remove water
        if (blockstate.is(this)) { return blockstate.setValue(TYPE, EchoesSlabType.DOUBLE).setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, pContext.getClickedFace()); }
        // Checks on side faces
        else if (pContext.getClickedFace() == Direction.EAST  && (pContext.getClickLocation().y - (double)blockpos.getY() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.DOWN); }
        else if (pContext.getClickedFace() == Direction.EAST  && (pContext.getClickLocation().y - (double)blockpos.getY() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.UP); }
        else if (pContext.getClickedFace() == Direction.WEST  && (pContext.getClickLocation().y - (double)blockpos.getY() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.DOWN); }
        else if (pContext.getClickedFace() == Direction.WEST  && (pContext.getClickLocation().y - (double)blockpos.getY() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.UP); }
        else if (pContext.getClickedFace() == Direction.NORTH && (pContext.getClickLocation().y - (double)blockpos.getY() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.DOWN); }
        else if (pContext.getClickedFace() == Direction.NORTH && (pContext.getClickLocation().y - (double)blockpos.getY() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.UP); }
        else if (pContext.getClickedFace() == Direction.SOUTH && (pContext.getClickLocation().y - (double)blockpos.getY() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.DOWN); }
        else if (pContext.getClickedFace() == Direction.SOUTH && (pContext.getClickLocation().y - (double)blockpos.getY() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos);return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.UP); }
        // Checks on top faces
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.EAST && (pContext.getClickLocation().x - (double)blockpos.getX() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.WEST); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.EAST && (pContext.getClickLocation().x - (double)blockpos.getX() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.EAST); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.WEST && (pContext.getClickLocation().x - (double)blockpos.getX() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.WEST); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.WEST && (pContext.getClickLocation().x - (double)blockpos.getX() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.EAST); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.SOUTH && (pContext.getClickLocation().z - (double)blockpos.getZ() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.NORTH); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.SOUTH && (pContext.getClickLocation().z - (double)blockpos.getZ() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.SOUTH); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.NORTH && (pContext.getClickLocation().z - (double)blockpos.getZ() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.NORTH); }
        else if (pContext.getClickedFace() == Direction.UP && pContext.getHorizontalDirection() == Direction.NORTH && (pContext.getClickLocation().z - (double)blockpos.getZ() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.SOUTH); }
        // Checks on bottom faces
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.EAST && (pContext.getClickLocation().x - (double)blockpos.getX() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.WEST); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.EAST && (pContext.getClickLocation().x - (double)blockpos.getX() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.EAST); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.WEST && (pContext.getClickLocation().x - (double)blockpos.getX() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.WEST); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.WEST && (pContext.getClickLocation().x - (double)blockpos.getX() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.EAST); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.SOUTH && (pContext.getClickLocation().z - (double)blockpos.getZ() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.NORTH); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.SOUTH && (pContext.getClickLocation().z - (double)blockpos.getZ() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.SOUTH); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.NORTH && (pContext.getClickLocation().z - (double)blockpos.getZ() > 0.66D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.NORTH); }
        else if (pContext.getClickedFace() == Direction.DOWN && pContext.getHorizontalDirection() == Direction.NORTH && (pContext.getClickLocation().z - (double)blockpos.getZ() < 0.33D)) {FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, Direction.SOUTH); }
        // Default or neutral placements
        else { FluidState fluidstate = pContext.getLevel().getFluidState(blockpos); return this.defaultBlockState().setValue(TYPE, EchoesSlabType.HALF).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(FACING, pContext.getClickedFace()); }
    }
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        ItemStack itemstack = pUseContext.getItemInHand();
        EchoesSlabType slabtype = pState.getValue(TYPE);
        if (slabtype != EchoesSlabType.DOUBLE && itemstack.is(this.asItem())) {
            Direction direction = pUseContext.getClickedFace();
            if (direction == pState.getValue(FACING)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    //Waterlogging Handlers
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return pState.getValue(TYPE) != EchoesSlabType.DOUBLE ? SimpleWaterloggedBlock.super.placeLiquid(pLevel, pPos, pState, pFluidState) : false;
    }
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return pState.getValue(TYPE) != EchoesSlabType.DOUBLE ? SimpleWaterloggedBlock.super.canPlaceLiquid(pLevel, pPos, pState, pFluid) : false;
    }
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }


    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        switch (pType) {
            case LAND:
                return false;
            case WATER:
                return pLevel.getFluidState(pPos).is(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(WATERLOGGED, FACING, TYPE);
    }
}
