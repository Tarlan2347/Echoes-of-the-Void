package net.tarlan.echoes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.tarlan.echoes.block.entity.EchoesBlockEntities;
import net.tarlan.echoes.block.entity.UmbrelithFurnaceBlockEntity;
import org.jetbrains.annotations.Nullable;

public class UmbrelithFurnaceBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    /** Set constructor. */
    public UmbrelithFurnaceBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
    }
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }
    /** Define collision shape, then set collision shape. */
    public static final VoxelShape UMBRELITH_FURNACE_VOXEL_SHAPE = Block.box(0,0,0,16,16,16);
    @Override public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return UMBRELITH_FURNACE_VOXEL_SHAPE;
    }
    /** Render block model. */
    @Override public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    /** Get and drop inventory contents when removed from world or broken. */
    @Override public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof UmbrelithFurnaceBlockEntity) {
                ((UmbrelithFurnaceBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
    /** Open menu when interacted with. */
    @Override public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof UmbrelithFurnaceBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (UmbrelithFurnaceBlockEntity)entity, pPos);
                return InteractionResult.CONSUME;
            } else {
                throw new IllegalStateException("Umbrelith Furnace Container Provider Is Missing!");
            }
        }
        return  InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, EchoesBlockEntities.UMBRELITH_FURNACE_BLOCK_ENTITY.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            double d0 = (double)pPos.getX() + 0.5D;
            double d1 = (double)pPos.getY();
            double d2 = (double)pPos.getZ() + 0.5D;
            if (pRandom.nextDouble() < 0.1D) {
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 0.5F, false);
            }

            Direction direction = pState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = pRandom.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = pRandom.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            pLevel.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.ASH, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }

    /** Initialize block entity. */
    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new UmbrelithFurnaceBlockEntity(pPos, pState);
    }
}
