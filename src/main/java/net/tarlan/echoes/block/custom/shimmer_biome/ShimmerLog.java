package net.tarlan.echoes.block.custom.shimmer_biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.block.custom.FlammableRotatedPillarBlock;

public class ShimmerLog extends FlammableRotatedPillarBlock {
    public ShimmerLog(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        convertToMoss(pLevel, pPos);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        convertToMoss((Level) pLevel, pCurrentPos);
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    public void convertToMoss(Level level, BlockPos pos) {
        int count = 0;
        // Check adjacient blocks on the x and z axis and count the matching types
        for (Direction dir : Direction.values()) {
            BlockPos checkPos = pos.relative(dir);
            BlockState neighbor = level.getBlockState(checkPos);
            if (neighbor.is(EchoesBlocks.UMBRELITH_COBALT_MOSS.get())) {
                count++;
            }
        }

        if (count >= 3) {
            level.setBlockAndUpdate(pos, EchoesBlocks.UMBRELITH_COBALT_MOSS.get().defaultBlockState());
        }
    }
}
