package net.tarlan.echoes.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.tarlan.echoes.event.EventManager;
import net.tarlan.echoes.event.RegistryEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RegistryHelper implements Consumer<RegistryEvent>
{
    private final Multimap<ResourceKey<? extends Registry<?>>, Registrar<?>> registrars = ArrayListMultimap.create();

    private RegistryHelper()
    {
        // Register self as an event handler
        EventManager.addListener(this);
    }

    public static RegistryHelper create()
    {
        return new RegistryHelper();
    }

    public <T> void addRegistrar(ResourceKey<? extends Registry<T>> registry, Registrar<T> registrar)
    {
        this.registrars.put(registry, registrar);
    }

    @Override
    public void accept(RegistryEvent registryEvent)
    {
        this.registrars.get(registryEvent.getRegistryKey()).forEach(registrar ->
        {
            ((Registrar<?>)registrar).registerAll(registryEvent::register);
        });
    }

    public interface Registrar<T>
    {
        void registerAll(BiConsumer<ResourceLocation, T> register);
    }
}