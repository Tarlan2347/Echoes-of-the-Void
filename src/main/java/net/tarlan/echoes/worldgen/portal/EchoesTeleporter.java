package net.tarlan.echoes.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.DebugLoggedPrintStream;
import net.minecraft.server.commands.SayCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.util.ITeleporter;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.block.custom.EchoesPortalBlock;
import net.tarlan.echoes.util.EchoesTags;
import org.jline.utils.Log;

import java.io.Console;
import java.io.PrintStream;
import java.util.function.Function;

public class EchoesTeleporter implements ITeleporter {
    public static BlockPos thisPos;
    public static boolean isInsideDimension = true;

    public EchoesTeleporter(BlockPos pos, boolean insideDim) {
        thisPos = pos;
        isInsideDimension = insideDim;
    }

    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        int y = 61;

        if (!isInsideDimension) {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        destinationWorld.setBlock(destinationPos.below(1), EchoesBlocks.UMBRELITH.get().defaultBlockState(), 2);
        Log.debug("Place Block Attempt At" + destinationPos);
        entity.setPos(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());
        return entity;
    }
}
