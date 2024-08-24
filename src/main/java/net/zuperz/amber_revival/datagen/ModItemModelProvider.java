package net.zuperz.amber_revival.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AmberRevival.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.AMBER.get());
        basicItem(ModItems.RESIN.get());

        smallAmberItem(ModItems.SLIME_AMBER);
        bigAmberItem(ModItems.AMBER_RAPTOR_SKULL);

        basicItem(ModItems.RAPTOR_SKULL.get());
        withExistingParent(ModItems.RAPTOR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
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

}