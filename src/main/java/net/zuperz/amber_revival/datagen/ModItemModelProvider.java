package net.zuperz.amber_revival.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AmberRevival.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.AMBER.get());
        basicItem(ModItems.AMBER_INGOT.get());
        basicItem(ModItems.RESIN.get());
        basicItem(ModItems.RESIN_PICKAXE.get());

        basicItem(ModItems.RAPTOR_CHOPS.get());
        basicItem(ModItems.COOKED_RAPTOR_CHOPS.get());
        basicItem(ModItems.RAPTOR_FEAHER.get());

        smallAmberItem(ModItems.SLIME_AMBER);
        amberItem(ModItems.SPIDER_AMBER);
        amberItem(ModItems.AMBER_AXOLOTL);
        bigAmberItem(ModItems.AMBER_RAPTOR_SKULL);

        basicItem(ModItems.WARD_CUT_MUSIC_DISC.get());

        basicItem(ModItems.RAPTOR_SKULL.get());
        withExistingParent(ModItems.RAPTOR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        
        buttonItem(ModBlocks.AMBER_BUTTON, ModBlocks.AMBER_PLANKS);
        fenceItem(ModBlocks.AMBER_FENCE, ModBlocks.AMBER_PLANKS);

        basicItem(ModBlocks.AMBER_DOOR.asItem());
    }

    private ItemModelBuilder handheldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder amberItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/amber"))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder smallAmberItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/small_amber"))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder bigAmberItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/big_amber"))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "item/" + item.getId().getPath()));
    }


    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID,"block/" + item.getId().getPath()));
    }
    
    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

}