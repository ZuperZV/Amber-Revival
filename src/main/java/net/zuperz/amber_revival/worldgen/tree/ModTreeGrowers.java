package net.zuperz.amber_revival.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower AMBER = new TreeGrower(AmberRevival.MOD_ID + ":amber",
            Optional.empty(), Optional.of(ModConfiguredFeatures.AMBER_KEY), Optional.empty());
}