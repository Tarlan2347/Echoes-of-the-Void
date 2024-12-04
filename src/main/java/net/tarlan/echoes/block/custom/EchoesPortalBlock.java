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
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.level() instanceof ServerLevel serverLevel) {
            ServerLevel portalDimension = serverLevel.getServer().getLevel(pEntity.level().dimension() ==
                    EchoesDimensionTypes.THE_VOID_LEVEL ? Level.OVERWORLD : EchoesDimensionTypes.THE_VOID_LEVEL);
            pEntity.changeDimension(portalDimension);
        }
    }
}