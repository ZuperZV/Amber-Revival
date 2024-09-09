package net.zuperz.amber_revival.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AmberRevival.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AMBER_SAND_ORE);

        horizontalBlock(ModBlocks.AMBER_DISPLAY.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/amber_display")));


        blockWithItem(ModBlocks.AMBER_PLANKS);

        logBlock(((RotatedPillarBlock) ModBlocks.AMBER_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.AMBER_WOOD.get()), blockTexture(ModBlocks.AMBER_LOG.get()), blockTexture(ModBlocks.AMBER_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.RESIN_AMBER_LOG.get()));

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_AMBER_LOG.get(), ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "block/stripped_amber_log"),
                ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "block/stripped_amber_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_AMBER_WOOD.get(), ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "block/stripped_amber_log"),
                ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "block/stripped_amber_log"));

        stairsBlock(((StairBlock) ModBlocks.AMBER_STAIRS.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.AMBER_SLAB.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.AMBER_PRESSURE_PLATE.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.AMBER_BUTTON.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBlocks.AMBER_FENCE.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.AMBER_FENCE_GATE.get()), blockTexture(ModBlocks.AMBER_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.AMBER_DOOR.get()), modLoc("block/amber_door_bottom"), modLoc("block/amber_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.AMBER_TRAPDOOR.get()), modLoc("block/amber_trapdoor"), true, "cutout");
        
        blockItem(ModBlocks.AMBER_LOG);
        blockItem(ModBlocks.RESIN_AMBER_LOG);

        blockItem(ModBlocks.AMBER_WOOD);
        blockItem(ModBlocks.STRIPPED_AMBER_LOG);
        blockItem(ModBlocks.STRIPPED_AMBER_WOOD);

        leavesBlock(ModBlocks.AMBER_LEAVES);
        saplingBlock(ModBlocks.AMBER_SAPLING);

        blockItem(ModBlocks.AMBER_STAIRS);
        blockItem(ModBlocks.AMBER_SLAB);
        blockItem(ModBlocks.AMBER_PRESSURE_PLATE);
        blockItem(ModBlocks.AMBER_FENCE_GATE);

        blockItem(ModBlocks.AMBER_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("amber_revival:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("amber_revival:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }
}