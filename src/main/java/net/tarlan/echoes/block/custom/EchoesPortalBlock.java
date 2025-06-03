package net.tarlan.echoes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.commands.ExecuteCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.tarlan.echoes.worldgen.dimension.EchoesDimensionTypes;

public class EchoesPortalBlock extends Block {
    public EchoesPortalBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (!pLevel.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) pLevel;
            ResourceKey<Level> currentDimension = serverLevel.dimension();

            // Check if the entity is in the Overworld, Nether, or End
            if (currentDimension.equals(Level.OVERWORLD) || currentDimension.equals(Level.NETHER) || currentDimension.equals(Level.END)) {
                // Get the target level (THE_VOID_LEVEL)
                ServerLevel targetDimension = serverLevel.getServer().getLevel(EchoesDimensionTypes.THE_VOID_LEVEL);
                if (targetDimension != null) {
                    pEntity.changeDimension(targetDimension);
                }
            }
            else {
                ServerLevel targetDimension = serverLevel.getServer().getLevel(Level.OVERWORLD);
                if (targetDimension != null) {
                    pEntity.changeDimension(targetDimension);
                }
            }
        }
    }
}