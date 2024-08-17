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

        amberItem(ModItems.SLIME_AMBER);
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

}