package net.zuperz.amber_revival.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AMBER_PLACED_KEY = registerKey("amber_placed");

    public static final ResourceKey<PlacedFeature> AMBER_SAND_ORE_PLACED_KEY = registerKey("amber_sand_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, AMBER_SAND_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AMBER_SAND_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));


        List<PlacementModifier> amberPlacementModifiers = new ArrayList<>(VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(600)));
        amberPlacementModifiers.add(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK)));
        amberPlacementModifiers.add(HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES));

        register(context, AMBER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AMBER_KEY),
                amberPlacementModifiers);
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}