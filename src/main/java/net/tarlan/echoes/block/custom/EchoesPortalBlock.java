package net.tarlan.echoes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.ITeleporter;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.worldgen.dimension.EchoesDimensionTypes;
import net.tarlan.echoes.worldgen.portal.EchoesTeleporter;
import org.jetbrains.annotations.NotNull;

public class EchoesPortalBlock extends Block {
    public EchoesPortalBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            MinecraftServer minecraftServer = serverLevel.getServer();
            ResourceKey<Level> resourceKey = player.level().dimension() == EchoesDimensionTypes.THE_VOID_LEVEL ? Level.OVERWORLD : EchoesDimensionTypes.THE_VOID_LEVEL;
            ServerLevel portalDimension = minecraftServer.getLevel(resourceKey);
            if (portalDimension != null && !player.isPassenger()) {
                // We are going to the overworld. Upscale current position by 32.
                BlockPos overworldDestinationPos = new BlockPos(pPos.getX()*32, pPos.getY(), pPos.getZ()*32);
                // We are going to the overworld. Downscale current position by 32.
                BlockPos theVoidDestinationPos = new BlockPos(pPos.getX()/32, pPos.getY(), pPos.getZ()/32);
                if (resourceKey == EchoesDimensionTypes.THE_VOID_LEVEL) {
                    player.changeDimension(portalDimension, new EchoesTeleporter(overworldDestinationPos, true));
                } else {
                    player.changeDimension(portalDimension, new EchoesTeleporter(theVoidDestinationPos, false));
                    pLevel.setBlock(theVoidDestinationPos.below(1), EchoesBlocks.UMBRELITH.get().defaultBlockState(), 2);
                }
            }
        }
    }
}