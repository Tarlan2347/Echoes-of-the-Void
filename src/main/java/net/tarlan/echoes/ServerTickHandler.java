package net.tarlan.echoes;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tarlan.echoes.worldgen.feature.voidIslands.VoidIslandPlacementScheduler;

@Mod.EventBusSubscriber(modid = Echoes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerTickHandler {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            VoidIslandPlacementScheduler.tick();
            VoidIslandPlacementScheduler.tickVisualUpdates();
        }
    }
}