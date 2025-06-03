package net.tarlan.echoes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class EchoesFlammable {
    public static BlockBehaviour.Properties woodProps(MapColor color, float strength) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .sound(SoundType.WOOD)
                .strength(strength)
                .ignitedByLava();
    }

    public static boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    public static int getFlammability(int id) {
        return 5;
    }

    public static int getSpreadSpeed(int id) {
        return 20;
    }
}
