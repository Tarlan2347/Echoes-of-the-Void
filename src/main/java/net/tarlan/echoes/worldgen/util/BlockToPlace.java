package net.tarlan.echoes.worldgen.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public record BlockToPlace(BlockPos pos, BlockState state) {}
