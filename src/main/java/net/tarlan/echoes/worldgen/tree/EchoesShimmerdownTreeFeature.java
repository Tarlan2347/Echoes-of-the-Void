package net.tarlan.echoes.worldgen.tree;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.tarlan.echoes.block.EchoesBlocks;

public class EchoesShimmerdownTreeFeature extends Feature<NoneFeatureConfiguration> {

    int tier0 = 4;
    int tier1 = 5;
    int tier2 = 10;
    int tier3 = 20;
    int tier4 = 40;

    public EchoesShimmerdownTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }
    // /place feature echoes:shimmerdown_tree

    private int getWeightedTreeHeight(RandomSource random) {
        float roll = random.nextFloat(); // Generates a float [0.0, 1.0)
        if (roll < 0.40) return random.nextIntBetweenInclusive(tier0, tier1);  // 40% chance (tiny trees 4-5)
        if (roll < 0.95) return random.nextIntBetweenInclusive(tier1+1, tier2); // 55% chance (small trees 6-10)
        if (roll < 0.9975) return random.nextIntBetweenInclusive(tier2+1, tier3); // 4.75% chance (medium trees 11-20)
        return random.nextIntBetweenInclusive(tier3+1, tier4); // 0.25% chance (large trees 21-40)
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos position = context.origin();
        RandomSource random = context.random();
        BlockState logMaterial = EchoesBlocks.SHIMMERDOWN_LOG.get().defaultBlockState();

        int totalHeight = getWeightedTreeHeight(random); // Chance to be just a smudge taller than normal.

        // generateOres a random angle between 0 and 2π
        double angle = random.nextDouble() * 2 * Math.PI;

        // Calculate displacement using circle edge formula
        int radius = totalHeight / 3;
        int xDisplacement = (int) Math.round(radius * Math.cos(angle));
        int zDisplacement = (int) Math.round(radius * Math.sin(angle));

        int lastBranchPoint = 0;
        BlockPos lastPos = position;  // Store the last placed position

        for (int y = 0; y <= totalHeight; y++) {
            double t = (double) y / totalHeight;
            double Func = Math.sin(t * Math.PI - Math.PI / 2);
            double xOffset = xDisplacement * Func * 0.5 + xDisplacement * 0.5;
            double zOffset = zDisplacement * Func * 0.5 + zDisplacement * 0.5;

            int x = position.getX() + (int) Math.round(xOffset);
            int z = position.getZ() + (int) Math.round(zOffset);
            int currentY = position.getY() + y;

            BlockPos newPos = new BlockPos(x, currentY, z);
            placeTrunkBlock(world, lastPos, newPos, logMaterial);

            int dx = 0;

            double normalized = angle % (2 * Math.PI);
            if (normalized < 0) normalized += 2 * Math.PI;

            if (normalized <= Math.PI / 4 || normalized > 7 * Math.PI / 4) {
                dx = 1; // East (+X)
            } else if (normalized <= 3 * Math.PI / 4) {
                dx = 2; // South (+Z)
            } else if (normalized <= 5 * Math.PI / 4) {
                dx = 3; // West (-X)
            } else {
                dx = 4; // North (-Z)
            }

            // If tree is tier 2:
            if ((y == 0) && (totalHeight > 5) && (totalHeight <= 10)) {
                if (dx == 1) { // Pos X
                    placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 0), logMaterial);
                }
                if (dx == 2) { // Pos Z
                    placeTrunkBlock(world, lastPos, newPos.offset(0, 0, 1), logMaterial);
                }
                if (dx == 3) { // Neg X
                    placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, 0), logMaterial);
                }
                if (dx == 4) { // Neg Z
                    placeTrunkBlock(world, lastPos, newPos.offset(0, 0, -1), logMaterial);
                }
            }

            // If tree is tier 3-4:
            if ((totalHeight > 10)) {
                if (dx == 1) { // Pos X
                    if ((y < (totalHeight / 1.5))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 4))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, 1), logMaterial);
                    }
                    if ((y < (totalHeight / 6))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 10))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 1), logMaterial);
                    }
                }
                if (dx == 2) { // Pos Z (rotate)
                    if ((y < (totalHeight / 1.5))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, 1), logMaterial);
                    }
                    if ((y < (totalHeight / 4))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 6))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, 1), logMaterial);
                    }
                    if ((y < (totalHeight / 10))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(1, 0, 1), logMaterial);
                    }
                }
                if (dx == 3) { // Neg X (rotate)
                    if ((y < (totalHeight / 1.5))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 4))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, -1), logMaterial);
                    }
                    if ((y < (totalHeight / 6))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 10))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, -1), logMaterial);
                    }
                }
                if (dx == 4) { // Neg Z (rotate)
                    if ((y < (totalHeight / 1.5))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, -1), logMaterial);
                    }
                    if ((y < (totalHeight / 4))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, 0), logMaterial);
                    }
                    if ((y < (totalHeight / 6))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(0, 0, -1), logMaterial);
                    }
                    if ((y < (totalHeight / 10))) {
                        placeTrunkBlock(world, lastPos, newPos.offset(-1, 0, -1), logMaterial);
                    }
                }
            }

            // If tree is tier 4:
            // if ((totalHeight > 20) && (totalHeight <= 40)) {
            //     if (dx == 1) {

            //     }
            //     if (dx == 2) {

            //     }
            //     if (dx == 3) {

            //     }
            //     if (dx == 4) {

            //     }
            // }

            lastPos = newPos; // Update last position

            if (y >= totalHeight / 4 && (y - lastBranchPoint) >= Math.max(5, totalHeight / 8) && (totalHeight - y) > 4) {
                if (!(random.nextIntBetweenInclusive(0, 10) == 0)) {
                    // Give a random variety based on tree size.
                    placeStem(random.nextIntBetweenInclusive(2, Math.max(2, (int) ((totalHeight - y) * 1.5))), lastPos, logMaterial, world, random, totalHeight, angle);
                    lastBranchPoint = y;
                }
            }
            if (y == totalHeight) {
                placeFoliage(totalHeight, lastPos, world);
            }
            if (y == 0 && random.nextInt(8)==1 && totalHeight > tier2) {
                placeStem(random.nextIntBetweenInclusive((int)(totalHeight * 0.2), Math.max(2, (int) ((totalHeight - y) * 1.5))), lastPos.below(1), logMaterial, world, random, totalHeight, angle);
            }
        }

        return true;
    }

    private void placeStem(int height, BlockPos position, BlockState logMaterial, WorldGenLevel world, RandomSource random, int treeHeight, double parentAngle) {

        double angle = random.nextDouble() * 2 * Math.PI; // generateOres random angle (0 to 2π)
        int radius = (int) (height);
        int xDisplacement = (int) Math.round(radius * Math.cos(angle));
        int zDisplacement = (int) Math.round(radius * Math.sin(angle));

        int lastBranchPoint = 0;
        BlockPos lastPos = position;
        for (int y = 0; y <= height; y++) {
            double t = (double) y / height;
            double Func = Math.sin(t * Math.PI - Math.PI / 2);
            double xOffset = xDisplacement * Func * 0.5 + xDisplacement * 0.5;
            double zOffset = zDisplacement * Func * 0.5 + zDisplacement * 0.5;

            int x = position.getX() + (int) Math.round(xOffset);
            int z = position.getZ() + (int) Math.round(zOffset);
            int currentY = position.getY() + y;

            BlockPos newPos = new BlockPos(x, currentY, z);
            placeTrunkBlock(world, lastPos, newPos, logMaterial);
            lastPos = newPos;

            // Sub-branches.
            if (y >= height / 3 && (y - lastBranchPoint) >= 3 && (height - y) > 6) {
                if (!(random.nextIntBetweenInclusive(0, 10) == 0)) {
                    placeStem(random.nextIntBetweenInclusive(2, Math.max(2, (int) ((height - y) * 1.5))), lastPos, logMaterial, world, random, treeHeight, angle);
                    lastBranchPoint = y;
                }
            }

            // Finish with foliage
            if (y == height) {
                placeFoliage(treeHeight, lastPos, world);
            }
        }
    }

    private void placeFoliage (int treeHeight, BlockPos position, WorldGenLevel world) {
        // generateOres a number of disks for foliage.
        if (treeHeight <= tier1) {
            generateDisk(world, new BlockPos(position.getX(), position.getY(), position.getZ()), 3);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 1, position.getZ()), 4);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 2, position.getZ()), 1);
        } else if (treeHeight <= tier2) {
            generateDisk(world, new BlockPos(position.getX(), position.getY(), position.getZ()), 4);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 1, position.getZ()), 5);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 2, position.getZ()), 2);
        } else if (treeHeight <= tier3) {
            generateDisk(world, new BlockPos(position.getX(), position.getY(), position.getZ()), 5);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 1, position.getZ()), 6);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 2, position.getZ()), 2);
            generateDisk(world, new BlockPos(position.getX(), position.getY() - 1, position.getZ()), 1);
        } else if (treeHeight <= tier4) {
            generateDisk(world, new BlockPos(position.getX(), position.getY(), position.getZ()), 8);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 1, position.getZ()), 9);
            generateDisk(world, new BlockPos(position.getX(), position.getY() + 2, position.getZ()), 4);
            generateDisk(world, new BlockPos(position.getX(), position.getY() - 1, position.getZ()), 6);
            generateDisk(world, new BlockPos(position.getX(), position.getY() - 2, position.getZ()), 1);
        }
    }
    private void generateDisk(WorldGenLevel world, BlockPos center, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) { // Ensures circular shape
                    BlockPos pos = center.offset(x, 0, z);
                    if (world.getBlockState(pos) == Blocks.AIR.defaultBlockState() || world.getBlockState(pos).canBeReplaced()) {
                        world.setBlock(pos, EchoesBlocks.COBALT_SHIMMERDOWN_LEAVES.get().defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    // Using a line algorithm to avoid floating blocks
    private void placeTrunkBlock(WorldGenLevel world, BlockPos pos1, BlockPos pos2, BlockState state) {
        int x1 = pos1.getX(), y1 = pos1.getY(), z1 = pos1.getZ();
        int x2 = pos2.getX(), y2 = pos2.getY(), z2 = pos2.getZ();

        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1), dz = Math.abs(z2 - z1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1, sz = z1 < z2 ? 1 : -1;

        if (dx >= dy && dx >= dz) {  // X is the driving axis
            int err1 = 2 * dy - dx;
            int err2 = 2 * dz - dx;
            while (x1 != x2) {
                placeBlock(world, new BlockPos(x1, y1, z1), state);
                if (err1 > 0) { y1 += sy; err1 -= 2 * dx; }
                if (err2 > 0) { z1 += sz; err2 -= 2 * dx; }
                err1 += 2 * dy;
                err2 += 2 * dz;
                x1 += sx;
            }
        } else if (dy >= dx && dy >= dz) {  // Y is the driving axis
            int err1 = 2 * dx - dy;
            int err2 = 2 * dz - dy;
            while (y1 != y2) {
                placeBlock(world, new BlockPos(x1, y1, z1), state);
                if (err1 > 0) { x1 += sx; err1 -= 2 * dy; }
                if (err2 > 0) { z1 += sz; err2 -= 2 * dy; }
                err1 += 2 * dx;
                err2 += 2 * dz;
                y1 += sy;
            }
        } else {  // Z is the driving axis
            int err1 = 2 * dx - dz;
            int err2 = 2 * dy - dz;
            while (z1 != z2) {
                placeBlock(world, new BlockPos(x1, y1, z1), state);
                if (err1 > 0) { x1 += sx; err1 -= 2 * dz; }
                if (err2 > 0) { y1 += sy; err2 -= 2 * dz; }
                err1 += 2 * dx;
                err2 += 2 * dz;
                z1 += sz;
            }
        }

        // Ensure the final position is also set
        placeBlock(world, new BlockPos(x2, y2, z2), state);
    }


    private void placeBlock(WorldGenLevel world, BlockPos pos, BlockState state) {
        world.setBlock(pos, state, 3);
    }
}
