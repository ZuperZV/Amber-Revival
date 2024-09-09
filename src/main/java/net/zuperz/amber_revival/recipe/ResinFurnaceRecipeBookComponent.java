package net.zuperz.amber_revival.recipe;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.zuperz.amber_revival.item.ModItems;

import java.util.Set;

public class ResinFurnaceRecipeBookComponent extends AbstractFurnaceRecipeBookComponent {
    @Override
    protected Set<Item> getFuelItems() {
        return Set.of(ModItems.RESIN.get(), Items.SNOWBALL, Items.SNOW_BLOCK);
    }
}