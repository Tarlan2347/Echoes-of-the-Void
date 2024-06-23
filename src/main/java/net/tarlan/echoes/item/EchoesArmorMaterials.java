package net.tarlan.echoes.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tarlan.echoes.Echoes;

import java.util.function.Supplier;

public enum EchoesArmorMaterials implements ArmorMaterial {

    VERDANTINE("verdantine", 10,new int[]{2,4,5,2},
            14, SoundEvents.ARMOR_EQUIP_IRON, 0, 0, ()->
            Ingredient.of(EchoesItems.VERDANTINE_INGOT.get())),

    CHORUSITE("chorusite", 20,new int[]{2,5,6,2},
            9, SoundEvents.ARMOR_EQUIP_IRON, 0, 0, ()->
            Ingredient.of(EchoesItems.CHORUSITE_INGOT.get())),

    NERON("neron", 20,new int[]{3,5,5,4},
            13, SoundEvents.ARMOR_EQUIP_IRON, 1, 0, ()->
            Ingredient.of(EchoesItems.NERON_INGOT.get())),

    AZURETINE("azuretine", 40,new int[]{4,5,6,5},
            13, SoundEvents.ARMOR_EQUIP_NETHERITE, 2, 0.05f, ()->
            Ingredient.of(EchoesItems.AZURETINE_INGOT.get())),

    PERIALIGHT("perialight", 60,new int[]{5,5,5,5},
            13, SoundEvents.ARMOR_EQUIP_DIAMOND, 3, 0.1f, ()->
            Ingredient.of(EchoesItems.PERIALIGHT.get())),

    VIOLUM("violum", 80,new int[]{7,7,7,7},
            22, SoundEvents.ARMOR_EQUIP_NETHERITE, 4, 0.15f, ()->
            Ingredient.of(EchoesItems.VIOLUM_INGOT.get()));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    EchoesArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Echoes.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
