/*package net.zuperz.amber_revival.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zuperz.amber_revival.AmberRevival;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AmberRevival.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.RAW_STEEL_BLOCK);

        blockWithItem(ModBlocks.STEEL_ORE);
        blockWithItem(ModBlocks.STEEL_DEEPSLATE_ORE);

        horizontalBlock(ModBlocks.HARD_ANVIL.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/hard_anvil")));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neo_tech:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neo_tech:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
 */