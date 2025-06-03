package net.tarlan.echoes.worldgen.util;

import net.minecraft.world.level.block.state.BlockState;

public class BlockVolume {
    private final BlockState[][][] data;
    private final int width, height, depth;

    public BlockVolume(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.data = new BlockState[width][height][depth];
    }

    public void set(int x, int y, int z, BlockState state) {
        data[x][y][z] = state;
    }

    public BlockState get(int x, int y, int z) {
        return data[x][y][z];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getDepth() { return depth; }
}
