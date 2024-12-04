package net.tarlan.echoes.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tarlan.echoes.Echoes;

public class EchoesSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Echoes.MOD_ID);

    public static final RegistryObject<SoundEvent> VIOLUM_BLOCK_BREAK = registerSoundEvents("violum_block_break");
    public static final RegistryObject<SoundEvent> VIOLUM_BLOCK_STEP = registerSoundEvents("violum_block_step");
    public static final RegistryObject<SoundEvent> VIOLUM_BLOCK_PLACE = registerSoundEvents("violum_block_place");
    public static final RegistryObject<SoundEvent> VIOLUM_BLOCK_HIT = registerSoundEvents("violum_block_hit");
    public static final RegistryObject<SoundEvent> VIOLUM_BLOCK_FALL = registerSoundEvents("violum_block_fall");

    public static final ForgeSoundType VIOLUM_SOUNDS = new ForgeSoundType(1f, 1f,
            VIOLUM_BLOCK_BREAK, VIOLUM_BLOCK_STEP, VIOLUM_BLOCK_PLACE, VIOLUM_BLOCK_HIT,VIOLUM_BLOCK_FALL);

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, ()-> SoundEvent.createVariableRangeEvent(new ResourceLocation(Echoes.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}