package net.tarlan.echoes.block.custom;

import net.minecraft.util.StringRepresentable;

public enum EchoesSlabType implements StringRepresentable {
    HALF("half"),
    DOUBLE("double");

    private final String name;

    private EchoesSlabType(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}