package net.zuperz.amber_revival.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zuperz.amber_revival.AmberRevival;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_AMBER_TOOL = createTag("incorrect_for_smber_tool");
        public static final TagKey<Block> NEEDS_AMBER_TOOL = createTag("needs_amber_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HAMMER_ITEM = createTag("hammer_item");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, name));
        }
    }
}