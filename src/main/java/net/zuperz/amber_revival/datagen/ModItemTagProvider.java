package net.zuperz.amber_revival.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.item.ModItems;
import net.zuperz.amber_revival.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, AmberRevival.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.AMBER_LOG.get().asItem())
                .add(ModBlocks.AMBER_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_AMBER_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_AMBER_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.AMBER_PLANKS.get().asItem());
    }
}