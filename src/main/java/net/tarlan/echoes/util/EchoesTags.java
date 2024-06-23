package net.tarlan.echoes.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tarlan.echoes.Echoes;

public class EchoesTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_VIOLUM_TOOL = tag("needs_violum_tool");
        public static final TagKey<Block> SUPPORTS_VOID_VEGETATION = tag("supports_void_vegetation");
        public static final TagKey<Block> MOSS_CAN_GROW_UNDER = tag("moss_can_grow_under");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Echoes.MOD_ID, name));
        }
    }

    public static final TagKey<Block> GREY_DYES = Blocks.tag("grey_dyes");
    public static final TagKey<Block> SHIMMER_FLOWERS = Blocks.tag("shimmer_flowers");
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Echoes.MOD_ID, name));
        }
    }
}
