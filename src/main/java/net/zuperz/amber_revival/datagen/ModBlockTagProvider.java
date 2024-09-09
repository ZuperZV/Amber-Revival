package net.zuperz.amber_revival.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AmberRevival.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AMBER_DISPLAY.get())
                .add(ModBlocks.AMBER_SAND_ORE.get())
                .add(ModBlocks.FOSSIL_BREAKER.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.AMBER_SLAB.get())
                .add(ModBlocks.AMBER_STAIRS.get())
                .add(ModBlocks.AMBER_PLANKS.get())
                .add(ModBlocks.AMBER_LOG.get())
                .add(ModBlocks.RESIN_AMBER_LOG.get())
                .add(ModBlocks.AMBER_WOOD.get())
                .add(ModBlocks.STRIPPED_AMBER_LOG.get())
                .add(ModBlocks.STRIPPED_AMBER_WOOD.get())
                .add(ModBlocks.AMBER_PRESSURE_PLATE.get())
                .add(ModBlocks.AMBER_BUTTON.get())
                .add(ModBlocks.AMBER_FENCE.get())
                .add(ModBlocks.AMBER_FENCE_GATE.get());
        
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.AMBER_DISPLAY.get())
                .add(ModBlocks.RESIN_AMBER_LOG.get());
        

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AMBER_SAND_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.AMBER_SAND_ORE.get());

        tag(BlockTags.FENCES).add(ModBlocks.AMBER_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.AMBER_FENCE_GATE.get());
    }
}