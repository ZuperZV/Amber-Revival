package net.zuperz.amber_revival.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.items.IItemHandler;

public class ItemHandlerRecipeInput implements RecipeInput {
    private final IItemHandler itemHandler;

    public ItemHandlerRecipeInput(IItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.itemHandler.getStackInSlot(slot);
    }

    @Override
    public int size() {
        return this.itemHandler.getSlots();
    }

    // Implement other required methods
}
