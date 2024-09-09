package net.zuperz.amber_revival.item.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.zuperz.amber_revival.item.ModItems;
import net.zuperz.amber_revival.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier AMBER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_AMBER_TOOL,
            600, 4f, 1.5f, 20,
            () -> Ingredient.of(ModItems.RESIN_PICKAXE));
}