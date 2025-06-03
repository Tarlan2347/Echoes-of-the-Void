package net.tarlan.echoes.block.custom.shimmer_biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.util.EchoesBlockStateProperties;
import net.tarlan.echoes.util.EchoesTags;

import javax.annotation.Nullable;
import java.util.*;

public class CobaltMossLayer extends Block {
    public CobaltMossLayer(BlockBehaviour.Properties p_152915_) {
        super(p_152915_);
        registerDefaultState();
    }
    protected void registerDefaultState() {
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(MOSS_LAYERS, 1));
    }

    /**
     * Define Layer Values, Layer Methods, and Per-Layer Voxel Shapes:
     * */
    public static final int MAX_MOSS_LAYERS = 8;
    public static final IntegerProperty MOSS_LAYERS = EchoesBlockStateProperties.MOSS_LAYERS;
    private static final VoxelShape[] SHAPE_BY_LAYER = new VoxelShape[] {
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
    };
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_LAYER[pState.getValue(MOSS_LAYERS) - 1];
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_LAYER[pState.getValue(MOSS_LAYERS) - 1];
    }

    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return SHAPE_BY_LAYER[pState.getValue(MOSS_LAYERS) - 1];
    }

    public VoxelShape getVisualShape(BlockState pState, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_LAYER[pState.getValue(MOSS_LAYERS) - 1];
    }

    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.getValue(MOSS_LAYERS) == 8 ? 0.2F : 1.0F;
    }


    /**
     * Update Shape:
     * */
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        updateMossLayerAfterChange(pState, pLevel, pCurrentPos);
        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, blockIn, fromPos, isMoving);
        updateMossLayerAfterChange(state, level, pos);
    }
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        updateMossLayerAfterChange(state, level, pos);
    }
    private void updateMossLayerAfterChange(BlockState state, LevelAccessor level, BlockPos pos) {
        ConvertToMossyStone(state, level, pos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        mossSlide(state, level, pos, random);
        convertUnderlayer(level, pos);
        ConvertToMossyStone(state, level, pos);
    }

    private void convertUnderlayer(ServerLevel level, BlockPos pos) {
        BlockPos belowLayer = pos.below();
        BlockState belowLayerState = level.getBlockState(belowLayer);
        if (belowLayerState.is(EchoesBlocks.UMBRELITH.get())) {
            level.setBlockAndUpdate(belowLayer, EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState());
        }
        if (belowLayerState.is(EchoesBlocks.VERLITH.get())) {
            level.setBlockAndUpdate(belowLayer, EchoesBlocks.VERLITH_COBALT_MOSS.get().defaultBlockState());
        }
        if (belowLayerState.is(EchoesBlocks.PALESTONE.get())) {
            level.setBlockAndUpdate(belowLayer, EchoesBlocks.PALESTONE_COBALT_MOSS.get().defaultBlockState());
        }
        if (belowLayerState.is(EchoesBlocks.TEALITE.get())) {
            level.setBlockAndUpdate(belowLayer, EchoesBlocks.TEALITE_COBALT_MOSS.get().defaultBlockState());
        }
    }
    private void ConvertToMossyStone(BlockState state, LevelAccessor level, BlockPos pos) {
        if (state.getValue(MOSS_LAYERS) >= MAX_MOSS_LAYERS) {
            BlockState below = level.getBlockState(pos.below());

            Map<Block, Block> mossyStoneMap = Map.of(
                    EchoesBlocks.UMBRELITH.get(), EchoesBlocks.UMBRELITH_COBALT_MOSS.get(),
                    EchoesBlocks.VERLITH.get(), EchoesBlocks.VERLITH_COBALT_MOSS.get(),
                    EchoesBlocks.PALESTONE.get(), EchoesBlocks.PALESTONE_COBALT_MOSS.get(),
                    EchoesBlocks.TEALITE.get(), EchoesBlocks.TEALITE_COBALT_MOSS.get(),
                    EchoesBlocks.UMBRELITH_COBALT_MOSS.get(), EchoesBlocks.UMBRELITH_COBALT_MOSS.get(),
                    EchoesBlocks.VERLITH_COBALT_MOSS.get(), EchoesBlocks.VERLITH_COBALT_MOSS.get(),
                    EchoesBlocks.PALESTONE_COBALT_MOSS.get(), EchoesBlocks.PALESTONE_COBALT_MOSS.get(),
                    EchoesBlocks.TEALITE_COBALT_MOSS.get(), EchoesBlocks.TEALITE_COBALT_MOSS.get()
            );

            for (Map.Entry<Block, Block> entry : mossyStoneMap.entrySet()) {
                if (below.is(entry.getKey())) {
                    level.setBlock(pos, entry.getValue().defaultBlockState(), 2);
                    break;
                }
            }
        }
    }
    private void mossSlide(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int currentLayers = state.getValue(MOSS_LAYERS);
        if (currentLayers <= 0) return;

        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        if (currentLayers >= MAX_MOSS_LAYERS && aboveState.is(this)) return;

        // generateOres radius-3 circular area offsets (for fall behavior)
        List<BlockPos> offsets = getCircleOffsets(pos, 2);

        if (currentLayers == 1) {
            for (BlockPos offset : offsets) {
                BlockPos targetPos = offset.below();
                BlockState targetState = level.getBlockState(targetPos);
                if (handleFallTarget(level, pos, state, currentLayers, targetPos, targetState, MAX_MOSS_LAYERS, 1)) return;
            }
        }

        // Pass 1: check y - 1 (below)
        for (BlockPos offset : offsets) {
            BlockPos targetPos = offset.below();
            BlockState targetState = level.getBlockState(targetPos);
            if (handleFallTarget(level, pos, state, currentLayers, targetPos, targetState, MAX_MOSS_LAYERS, 1)) return;
        }

        // Pass 2: check same y
        for (BlockPos offset : offsets) {
            BlockState targetState = level.getBlockState(offset);
            if (handleFallTarget(level, pos, state, currentLayers, offset, targetState, currentLayers - 2, 2)) return;
        }
    }
    private List<BlockPos> getCircleOffsets(BlockPos center, int radius) {
        List<BlockPos> result = new ArrayList<>();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if ((dx * dx + dz * dz) <= radius * radius) {
                    BlockPos offset = center.offset(dx, 0, dz);
                    if (!offset.equals(center)) result.add(offset);
                }
            }
        }

        // Sort by distance to center (the closest first)
        result.sort(Comparator.comparingDouble(offset -> offset.distSqr(center)));
        return result;
    }

    private boolean handleFallTarget(ServerLevel level, BlockPos sourcePos, BlockState sourceState, int currentLayers, BlockPos targetPos, BlockState targetState, int targetLayerThreshold, int sourceLayerThreshold) {
        if (currentLayers < sourceLayerThreshold) {
            return false;
        }
        if (targetState.is(this)) {
            int targetLayers = targetState.getValue(MOSS_LAYERS);
            if (targetLayers < MAX_MOSS_LAYERS && targetLayers <= targetLayerThreshold) {
                BlockState newState = targetState
                        .setValue(MOSS_LAYERS, targetLayers + 1);

                level.setBlockAndUpdate(targetPos, newState);
                consumeMossLayer(level, sourcePos, sourceState, currentLayers);
                return true;
            }
        }
        else if (targetState.isAir()) {
            if (canSurvive(targetState, level, targetPos)) {
                BlockState newState = this.defaultBlockState().setValue(MOSS_LAYERS, 1);

                level.setBlockAndUpdate(targetPos, newState);
                consumeMossLayer(level, sourcePos, sourceState, currentLayers);
                return true;
            }
        }
        else if (targetState.canBeReplaced() || targetState.is(EchoesBlocks.COBALT_TUFT_SMALL.get())) {
            if (Math.random() == 0) { // Very low chance of replacing replaceable.
                if (canSurvive(targetState, level, targetPos)) {
                    BlockState newState = this.defaultBlockState().setValue(MOSS_LAYERS, 1);

                    level.setBlockAndUpdate(targetPos, newState);
                    consumeMossLayer(level, sourcePos, sourceState, currentLayers);
                    return true;
                }
            } else return false;
        }

        return false;
    }



    private void consumeMossLayer(ServerLevel level, BlockPos pos, BlockState state, int currentLayers) {
        BlockPos targetPos = pos.above();
        BlockState targetState = level.getBlockState(targetPos);
        if (currentLayers == 8 && targetState.is(this)) {
            if (targetState.getValue(MOSS_LAYERS) == 1) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
            else {
                level.setBlockAndUpdate(pos, state.setValue(MOSS_LAYERS, currentLayers - 1));
            }
        } else if (currentLayers == 1) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        } else {
            level.setBlockAndUpdate(pos, state.setValue(MOSS_LAYERS, currentLayers - 1));
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    /**
     * Check for Survivability and Fetch Blockstates for Placement:
     * */
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        if (pState.is(this) && pState.getValue(MOSS_LAYERS) == 8) {
            return true;
        }
        BlockState belowState = pLevel.getBlockState(pPos.below());
        if (belowState.is(this) && belowState.getValue(MOSS_LAYERS) == 8) {
            return true;
        }
        if (belowState.is(EchoesTags.Blocks.MOSS_LAYER_CAN_SURVIVE_ON)) {
            return true;
        }
        return Block.isFaceFull(belowState.getCollisionShape(pLevel, pPos.below()), Direction.UP);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        RandomSource random = pContext.getLevel().getRandom();
        boolean placeWithTuft = random.nextFloat() < 0.3F; // 30% chance
        int tuftAge = placeWithTuft ? random.nextInt(6) : 0;

        if (blockstate.is(this)) {
            int i = blockstate.getValue(MOSS_LAYERS);
            return blockstate.setValue(MOSS_LAYERS, Math.min(8, i + 1));
        } else {
            return this.defaultBlockState()
                    .setValue(MOSS_LAYERS, 1);
        }
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(MOSS_LAYERS);
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        int i = pState.getValue(MOSS_LAYERS);
        if (pUseContext.getItemInHand().is(this.asItem()) && i < 8) {
            if (pUseContext.replacingClickedOnBlock()) {
                return pUseContext.getClickedFace() == Direction.UP;
            } else {
                return true;
            }
        }
        return !pUseContext.getItemInHand().is(this.asItem()) || i != 8;
    }

}
