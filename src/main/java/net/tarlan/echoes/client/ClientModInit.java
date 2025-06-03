package net.tarlan.echoes.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tarlan.echoes.Echoes;

@Mod.EventBusSubscriber(modid = Echoes.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModInit {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

}
