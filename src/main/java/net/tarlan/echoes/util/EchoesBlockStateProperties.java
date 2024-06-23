package net.tarlan.echoes.util;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.*;
import net.tarlan.echoes.block.custom.EchoesSlabType;

public class EchoesBlockStateProperties extends BlockStateProperties {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final EnumProperty<EchoesSlabType> ECHOES_SLAB_TYPE = EnumProperty.create("type", EchoesSlabType.class);

    public static final int MAX_TUFT_SIZE = 5;
    public static final IntegerProperty TUFT_SIZE = IntegerProperty.create("tuft_size", 0, 5);

    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");
    public static final BooleanProperty CONNECTED_EAST = BooleanProperty.create("connected_east");
    public static final BooleanProperty CONNECTED_WEST = BooleanProperty.create("connected_west");
    public static final BooleanProperty CONNECTED_NORTH = BooleanProperty.create("connected_north");
    public static final BooleanProperty CONNECTED_SOUTH = BooleanProperty.create("connected_south");
}
