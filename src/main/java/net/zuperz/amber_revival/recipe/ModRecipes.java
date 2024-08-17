package net.zuperz.amber_revival.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;

import static net.zuperz.amber_revival.AmberRevival.MOD_ID;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, AmberRevival.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, FossilBreakerRecipe.Serializer> FOSSIL_BREAKER_SERIALIZER =
            SERIALIZERS.register("fossil_breaker", () -> FossilBreakerRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}