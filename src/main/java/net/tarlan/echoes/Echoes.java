package net.tarlan.echoes;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tarlan.echoes.block.EchoesBlocks;
import net.tarlan.echoes.block.entity.EchoesBlockEntities;
import net.tarlan.echoes.item.EchoesCreativeModeTabs;
import net.tarlan.echoes.item.EchoesItems;
import net.tarlan.echoes.recipe.EchoesRecipeTypes;
import net.tarlan.echoes.screen.EchoesMenuTypes;
import net.tarlan.echoes.screen.UmbrelithFurnaceScreen;
import net.tarlan.echoes.sounds.EchoesSounds;
import net.tarlan.echoes.world.inventory.EchoesBlockMenuTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Echoes.MOD_ID)
public class Echoes
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "echoes";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Echoes()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EchoesCreativeModeTabs.register(modEventBus);
        EchoesItems.register(modEventBus);
        EchoesBlocks.register(modEventBus);
        EchoesSounds.register(modEventBus);
        EchoesBlockMenuTypes.register(modEventBus);
        EchoesMenuTypes.register(modEventBus);
        EchoesBlockEntities.register(modEventBus);
        EchoesRecipeTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }
    protected void clientSetup(final FMLClientSetupEvent event) {
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {}}
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
    public static ResourceLocation createLocation(String path) {return new ResourceLocation(MOD_ID, path);}
    public static ResourceLocation createLocation(ResourceKey<?> path) {return path.location();}
    public static ResourceLocation createLocation(Holder<?> holder) {return createLocation(holder.unwrapKey().orElseThrow());}
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(EchoesMenuTypes.UMBRELITH_FURNACE_MENU.get(), UmbrelithFurnaceScreen::new);
        }
    }
}
