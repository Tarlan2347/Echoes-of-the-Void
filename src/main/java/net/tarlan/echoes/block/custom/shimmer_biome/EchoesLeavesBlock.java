package net.tarlan.echoes.block.custom.shimmer_biome;

import java.util.OptionalInt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.item.EchoesItems;
import net.tarlan.echoes.worldgen.EchoesConfiguredFeatures;

public class EchoesLeavesBlock extends Block implements SimpleWaterloggedBlock, net.minecraftforge.common.IForgeShearable, BonemealableBlock {
    public static final IntegerProperty ECHOES_DISTANCE = IntegerProperty.create("echoes_distance", 1, 21);
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public EchoesLeavesBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ECHOES_DISTANCE, 1).setValue(PERSISTENT, Boolean.FALSE).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public VoxelShape getBlockSupportShape(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return Shapes.empty();
    }

    /**
     * @return whether this block needs random ticking.
     */
    public boolean isRandomlyTicking(BlockState pState) {
        return !pState.getValue(PERSISTENT);
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.hasNearbyAlivePlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 64)) return;
        if (this.decaying(pState)) {
            dropResources(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }
        handleFlowering(pState, pLevel, pPos, pRandom);
    }
    public void handleFlowering(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.defaultBlockState().is(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get())) {
            if (pRandom.nextInt(64) == 0) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get().withPropertiesOf(pState));
                updateDistance(pState, pLevel, pPos);
            }
        }
        if (this.defaultBlockState().is(EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get())) {
            if (pRandom.nextInt(8) == 0) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get().withPropertiesOf(pState));
                updateDistance(pState, pLevel, pPos);
            }
        }
        if (this.defaultBlockState().is(EchoesBlocks.LLERAE_LEAVES.get())) {
            if (pRandom.nextInt(64) == 0) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.BUDDING_LLERAE_LEAVES.get().withPropertiesOf(pState));
                updateDistance(pState, pLevel, pPos);
            }
        }
        if (this.defaultBlockState().is(EchoesBlocks.BUDDING_LLERAE_LEAVES.get())) {
            if (pRandom.nextInt(8) == 0) {
                pLevel.setBlockAndUpdate(pPos, EchoesBlocks.LLERAE_LEAVES.get().withPropertiesOf(pState));
                updateDistance(pState, pLevel, pPos);
            }
        }
    }
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    protected boolean decaying(BlockState pState) {
        return !pState.getValue(PERSISTENT) && pState.getValue(ECHOES_DISTANCE) == 21;
    }
    public int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 1;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        int distance = getDistanceAt(pFacingState) + 1;
        if (distance != 1 || pState.getValue(ECHOES_DISTANCE) != distance) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }

    private static BlockState updateDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = 21;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            mutableBlockPos.setWithOffset(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(mutableBlockPos)) + 1);
            if (i == 1) {
                break;
            }
        }
        return pState.setValue(ECHOES_DISTANCE, i);
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        return getOptionalDistanceAt(pNeighbor).orElse(21);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState pState) {
        if (pState.is(EchoesBlocks.SHIMMERDOWN_LOG.get())) {
            return OptionalInt.of(0);
        } else if (pState.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return pState.hasProperty(ECHOES_DISTANCE) ? OptionalInt.of(pState.getValue(ECHOES_DISTANCE)) : OptionalInt.empty();
        }
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(8) == 1) {
            BlockPos blockpos = pPos.below();
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (blockstate.is(EchoesBlocks.UMBRELITH_COBALT_MOSS.get())) {
                ParticleUtils.spawnParticleBelow(pLevel, pPos, pRandom, ParticleTypes.WARPED_SPORE);
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ECHOES_DISTANCE, PERSISTENT, WATERLOGGED);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidstate = level.getFluidState(pos);

        BlockState defaultState = this.defaultBlockState()
                .setValue(PERSISTENT, Boolean.TRUE)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);

        return updateDistance(defaultState, level, pos);
    }



    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        placeFeaturesAndBlocks(pLevel, pRandom, pPos, pState);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (this.defaultBlockState().is(EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get())) {
            int count = 1 + pLevel.random.nextInt(2);
            ItemStack stack = new ItemStack(EchoesItems.COBALT_SHIMMER_FLOWER.get(), count);
            handlePicking(pLevel, pPos, pPlayer, stack);

            pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.6F + pLevel.random.nextFloat() * 0.6F);
            pLevel.setBlockAndUpdate(pPos, EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get().withPropertiesOf(pState));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        if (this.defaultBlockState().is(EchoesBlocks.BUDDING_LLERAE_LEAVES.get())) {
            int count = 1 + pLevel.random.nextInt(2);
            ItemStack stack = new ItemStack(EchoesItems.LLERAE_FRUIT.get(), count);
            handlePicking(pLevel, pPos, pPlayer, stack);

            pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.6F + pLevel.random.nextFloat() * 0.6F);
            pLevel.setBlockAndUpdate(pPos, EchoesBlocks.LLERAE_LEAVES.get().withPropertiesOf(pState));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        return InteractionResult.PASS;
    }
    private void handlePicking(Level level, BlockPos pos, Player player, ItemStack stack) {
        ItemStack copyStack = stack.copy();
        Inventory inventory = player.getInventory();
        // Add directly to inventory, drop remainder if any.
        inventory.add(copyStack);
        if (!copyStack.isEmpty()) {
            popResource(level, pos.above(), copyStack);
        }
    }


    // Set up registry link to configured features classes and call the place() function, then update nearby leaves.
    public void placeFeaturesAndBlocks(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        Registry<ConfiguredFeature<?, ?>> registry = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        if (pRandom.nextInt(24) == 0) {
            // Randomly spawn tufts if there is space.
            if (this.defaultBlockState().is(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get()) || this.defaultBlockState().is(EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get())) {
                if (pLevel.getBlockState(pPos.above()).isAir()) {
                    this.place(registry, EchoesConfiguredFeatures.COBALT_TUFT_PATCH_FEATURE, pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos.above());
                }
            }
            // Randomly change surrounding leaf blocks to flowering.
            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pPos.offset(pRandom.nextInt(5) - 1, pRandom.nextInt(5) - 1, pRandom.nextInt(5) - 1);
                if (pLevel.getBlockState(blockpos).is(EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.FLOWERING_COBALT_SHIMMERDOWN_LEAVES.get().withPropertiesOf(pState));
                    updateDistance(pState, pLevel, pPos);
                }
                if (pLevel.getBlockState(blockpos).is(EchoesBlocks.LLERAE_LEAVES.get())) {
                    pLevel.setBlockAndUpdate(blockpos, EchoesBlocks.BUDDING_LLERAE_LEAVES.get().withPropertiesOf(pState));
                    updateDistance(pState, pLevel, pPos);
                }
            }
        }
    }
    // Place a configured feature as called in the performBonemeal() function.
    private void place(Registry<ConfiguredFeature<?, ?>> pFeatureRegistry, ResourceKey<ConfiguredFeature<?, ?>> pFeatureKey, ServerLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos) {
        pFeatureRegistry.getHolder(pFeatureKey).ifPresent((featureKey) -> {
            featureKey.value().place(pLevel, pChunkGenerator, pRandom, pPos);
        });
    }
}
