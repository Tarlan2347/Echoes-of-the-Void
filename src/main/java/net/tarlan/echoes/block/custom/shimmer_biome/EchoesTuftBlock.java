package net.tarlan.echoes.block.custom.shimmer_biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.util.EchoesBlockStateProperties;
import net.tarlan.echoes.util.EchoesTags;

public class EchoesTuftBlock extends Block implements BonemealableBlock, IPlantable {
    public EchoesTuftBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        registerDefaultState();
    }
    protected void registerDefaultState() {
        this.registerDefaultState(this.stateDefinition.any().setValue(TUFT_SIZE, 0));
    }
    @Override public float getMaxHorizontalOffset() {
        return 0.3f;
    }
    @Override public float getMaxVerticalOffset() {
        return -0.005f;
    }
    public static final int MAX_TUFT_SIZE = 5;
    public static final IntegerProperty TUFT_SIZE = EchoesBlockStateProperties.TUFT_SIZE;
    private static final VoxelShape[] SHAPES_BY_SIZE = new VoxelShape[] {
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D),
    };
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPES_BY_SIZE[this.getTuftSize(pState)];
    }
    protected IntegerProperty getSizeProperty() {
        return TUFT_SIZE;
    }


    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.hasNearbyAlivePlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 64)) return;

        if (pRandom.nextInt((50 / (1+(this.getTuftSize(pState)) * 2))) == 0) { // 1/50 - 10/50 chance
            // Try to grow the plant if it's not already at max size
            growAndDie(pLevel, pRandom, pPos, pState);
        }
    }

    public void growAndDie (ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        int currentSize = this.getTuftSize(pState);
        int maxSize = this.getMaxTuftSize();

        if (currentSize < maxSize) {
            // If not max size, simply grow
            pLevel.setBlock(pPos, this.getStateForAge(currentSize + 1), 2);
            return;
        }

        // We are at max size.
        // Select whether we will survive or not.
        boolean survives = !(pRandom.nextInt(10) == 0); // 1/10 chance to die, changed from previous 1/5.

        if (survives) {
            // Reset to minimum size and survive, but draw life from nearby tufts
            pLevel.setBlock(pPos, this.getStateForAge(0), 2);
            changeNearbyTuftSize(pLevel, pPos, -1);
        } else {
            // Remove self but add to nearby tufts
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 2);
            changeNearbyTuftSize(pLevel, pPos, 1);
        }
        // Attempt to spread after our fate is finished
        this.trySpreading(pLevel, pRandom, pPos);
    }

    private void changeNearbyTuftSize(ServerLevel pLevel, BlockPos pPos, int sizeMod) {
        for (Direction direction : Direction.values()) {
            BlockPos nearbyPos = pPos.relative(direction);
            BlockState nearbyState = pLevel.getBlockState(nearbyPos);

            // === Regular tuft block ===
            if (nearbyState.getBlock() instanceof EchoesTuftBlock) {
                int newSize = Math.max(0, nearbyState.getValue(TUFT_SIZE) + sizeMod);

                if (newSize == 0 || newSize > getMaxTuftSize()) {
                    pLevel.setBlock(nearbyPos, Blocks.AIR.defaultBlockState(), 2);
                } else {
                    pLevel.setBlock(nearbyPos, nearbyState.setValue(TUFT_SIZE, newSize), 2);
                }
            }
        }
    }


    private void trySpreading(ServerLevel level, RandomSource random, BlockPos pos) {
        int placed = 0;
        int attempts = 0;

        int thisSize = this.getTuftSize(defaultBlockState()); // Current tuft size

        while (placed < 5 && attempts < 20) {
            BlockPos targetPos = pos.offset(random.nextInt(5) - 3, random.nextInt(5) - 3, random.nextInt(5) - 3);
            BlockPos belowPos = targetPos.below();
            BlockState targetState = level.getBlockState(targetPos);
            BlockState belowState = level.getBlockState(belowPos);

            //
            if (targetState.canBeReplaced() && !targetState.is(EchoesBlocks.COBALT_MOSS_LAYER.get())) {
                if (this.mayPlaceOn(belowState, level, belowPos)) {
                    level.setBlock(targetPos, this.defaultBlockState().setValue(TUFT_SIZE, thisSize), 2);
                    placed++;
                }
            }
            if (targetState.is(EchoesBlocks.COBALT_MOSS_LAYER.get())) {
                if (random.nextIntBetweenInclusive(0, 100) < 5) {
                    if (this.mayPlaceOn(belowState, level, belowPos)) {
                        level.setBlock(targetPos, this.defaultBlockState().setValue(TUFT_SIZE, thisSize), 2);
                        placed++;
                    }
                }
            }

            attempts++;
        }
    }



    public int getMaxTuftSize() {
        return 5;
    }
    public int getTuftSize(BlockState pState) {
        return pState.getValue(this.getSizeProperty());
    }
    public BlockState getStateForAge(int pSize) {
        return this.defaultBlockState().setValue(this.getSizeProperty(), pSize);
    }
    public final boolean isMaxSize(BlockState pState) {
        return this.getTuftSize(pState) >= this.getMaxTuftSize();
    }
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        //if (pState.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        //    return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this);
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }
    public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(EchoesTags.Blocks.SUPPORTS_VOID_VEGETATION) || pState.is(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get()) || pState.is(EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get());
    }
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return (int) Math.round((this.getTuftSize(state) + 1) * 1.5);
    }
    @Override public BlockState getPlant(BlockGetter world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return defaultBlockState();
        return state;
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TUFT_SIZE);
    }

    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }


    // if max size is met, replace with minimum size and randomly select an air block nearby and place a second minimum size plant there.
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        growAndDie(pLevel, pRandom, pPos, pState);
    }
}