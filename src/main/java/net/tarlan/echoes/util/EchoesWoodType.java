package net.tarlan.echoes.util;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tarlan.echoes.block.EchoesBlockSetType;

import java.util.Set;
import java.util.stream.Stream;

public record EchoesWoodType(String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
    private static final Set<net.minecraft.world.level.block.state.properties.WoodType> VALUES = new ObjectArraySet<>();
    public static final WoodType LLERAE = register(new WoodType("llerae", EchoesBlockSetType.LLERAE));
    public static final WoodType SHIMMERDOWN = register(new WoodType("shimmerdown", EchoesBlockSetType.SHIMMERDOWN, SoundType.NETHER_WOOD, SoundType.NETHER_WOOD_HANGING_SIGN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE, SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN));

    public EchoesWoodType(String pName, BlockSetType pSetType) {
        this(pName, pSetType, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    public static net.minecraft.world.level.block.state.properties.WoodType register(net.minecraft.world.level.block.state.properties.WoodType EchoesWoodType) {
        VALUES.add(EchoesWoodType);
        return EchoesWoodType;
    }

    public static Stream<net.minecraft.world.level.block.state.properties.WoodType> values() {
        return VALUES.stream();
    }
}