package net.tarlan.echoes.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.tarlan.echoes.util.EchoesTags;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.jetbrains.annotations.NotNull;

public enum EchoesTiers implements Tier {
    // Stone-ish,
    VERDANTINE (1, 256,  5.0F,  1.0F, 5,  Ingredient.of(EchoesItems.VERDANTINE_INGOT.get()), BlockTags.NEEDS_STONE_TOOL),
    CHLORIUM   (1, 80,   12.0F, 0.0F, 22, Ingredient.of(EchoesItems.CHLORIUM.get()),         BlockTags.NEEDS_STONE_TOOL),
    // Iron-ish,
    CINDRITE   (2, 256,  6.0F,  2.0F, 15, Ingredient.of(EchoesItems.CINDRITE.get()),         BlockTags.NEEDS_IRON_TOOL),
    NERON      (2, 512,  9.0F,  2.0F, 10, Ingredient.of(EchoesItems.NERON_INGOT.get()),      BlockTags.NEEDS_IRON_TOOL),
    // Diamond-ish,
    AZURETINE  (3, 1028, 18.0F, 3.0F, 5,  Ingredient.of(EchoesItems.AZURETINE_INGOT.get()),  BlockTags.NEEDS_DIAMOND_TOOL),
    XIRIUM     (3, 256,  32.0F, 3.0F, 18, Ingredient.of(EchoesItems.XIRIUM.get()),           BlockTags.NEEDS_DIAMOND_TOOL),
    // Netherite-ish,
    PERIALIGHT (4, 2048, 39.0F, 5.0F, 15, Ingredient.of(EchoesItems.PERIALIGHT.get()),       Tags.Blocks.NEEDS_NETHERITE_TOOL),
    // Final.
    VIOLUM     (5, 4096, 60.0F, 6.0F, 30, Ingredient.of(EchoesItems.VIOLUM_INGOT.get()),     EchoesTags.Blocks.NEEDS_VIOLUM_TOOL);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;
    private final TagKey<Block> tag;

    //CHORUSITE(2, 256, 6.0F, 2.0F, 10, Ingredient.of(EchoesItems.CHORUSITE_INGOT.get()), BlockTags.NEEDS_IRON_TOOL),
    EchoesTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Ingredient pRepairIngredient, TagKey<Block> tag) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = pRepairIngredient;
        this.tag = tag;
    }
    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    public TagKey<Block> getTag()
    {
        return this.tag;
    }
}
