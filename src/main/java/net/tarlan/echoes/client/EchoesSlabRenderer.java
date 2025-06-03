package net.tarlan.echoes.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.tarlan.echoes.block.custom.EchoesSlabBlock;
import net.tarlan.echoes.block.custom.EchoesSlabType;

public class EchoesSlabRenderer {
    public static void renderSlabOutline(RenderLevelStageEvent event) {
        if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS)) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || mc.level == null) return;
        HitResult hitResult = mc.hitResult;
        if (hitResult == null || hitResult.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hitResult;
        BlockPos targetPos = blockHit.getBlockPos();
        ItemStack heldItem = player.getMainHandItem();

        if (!(heldItem.getItem() instanceof BlockItem)) return;
        BlockState heldBlockState = ((BlockItem) heldItem.getItem()).getBlock().defaultBlockState();

        if (!(heldBlockState.getBlock() instanceof EchoesSlabBlock)) return;

        BlockState targetState = mc.level.getBlockState(targetPos);
        BlockState previewState = null;

        // Check if the targeted block is a HALF slab and clicked face matches the slab’s facing
        if (targetState.getBlock() instanceof EchoesSlabBlock && targetState.getValue(EchoesSlabBlock.TYPE) == EchoesSlabType.HALF) {
            DirectionProperty facingProp = EchoesSlabBlock.FACING;
            if (targetState.hasProperty(facingProp) && blockHit.getDirection() == targetState.getValue(facingProp)) {
                // If the clicked face matches, preview a double slab
                previewState = targetState.setValue(EchoesSlabBlock.TYPE, EchoesSlabType.DOUBLE);
            }
        }

        // If not stacking on an existing slab, use standard placement logic
        if (previewState == null) {
            BlockPlaceContext context = new BlockPlaceContext(player, InteractionHand.MAIN_HAND, heldItem, blockHit);
            previewState = ((EchoesSlabBlock) heldBlockState.getBlock()).getStateForPlacement(context);
            if (previewState != null) {
                targetPos = blockHit.getBlockPos().relative(blockHit.getDirection());
            }
        }

        if (previewState == null) return;

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();
        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;
        poseStack.translate(targetPos.getX() - camX, targetPos.getY() - camY, targetPos.getZ() - camZ);


        RenderSystem.lineWidth(0.066F); // Adjust thickness here (default is 1.0F)

        // Render correct shape
        LevelRenderer.renderLineBox(
                poseStack,
                mc.renderBuffers().bufferSource().getBuffer(RenderType.lines()),
                previewState.getShape(mc.level, targetPos).bounds(),
                0.5F, 0.5F, 0.6F, 0.25F
        );

        poseStack.popPose();
    }
}
