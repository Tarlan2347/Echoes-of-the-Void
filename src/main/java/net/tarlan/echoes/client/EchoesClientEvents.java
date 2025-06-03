package net.tarlan.echoes.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.block.custom.EchoesSlabBlock;

@Mod.EventBusSubscriber(modid = Echoes.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EchoesClientEvents {

    @SubscribeEvent
    public static void onRenderWorld(RenderLevelStageEvent event) {
        // Hook into world rendering stages for custom rendering
        EchoesSlabRenderer.renderSlabOutline(event);
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        // Hook into keypresses (for custom keybinds)
    }
}
