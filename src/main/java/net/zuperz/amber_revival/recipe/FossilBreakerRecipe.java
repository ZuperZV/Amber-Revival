package net.zuperz.amber_revival.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.zuperz.amber_revival.AmberRevival;
import org.jetbrains.annotations.NotNull;

public class FossilBreakerRecipe implements Recipe<RecipeInput> {
    // An in-code representation of our recipe data. This can be basically anything you want.
    // Common things to have here is a processing time integer of some kind, or an experience reward.
    // Note that we now use an ingredient instead of an item stack for the input.
    private final Ingredient inputItem;
    private final Ingredient inputItem2;
    private final ItemStack result;

    // Add a constructor that sets all properties. 
    public FossilBreakerRecipe(Ingredient inputItem, Ingredient inputItem2, ItemStack result) {
        this.inputItem = inputItem;
        this.inputItem2 = inputItem2;
        this.result = result;
    }

    // A list of our ingredients. Does not need to be overridden if you have no ingredients
    // (the default implementation returns an empty list here). It makes sense to cache larger lists in a field.
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(RecipeInput recipeInput, Level level) {
        return this.inputItem.test(recipeInput.getItem(0)) && this.inputItem2.test(recipeInput.getItem(1));
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    // Grid-based recipes should return whether their recipe can fit in the given dimensions.
    // We don't have a grid, so we just return if any item can be placed in there.
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    // Return an UNMODIFIABLE version of your result here. The result of this method is mainly intended
    // for the recipe book, and commonly used by JEI and other recipe viewers as well.
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public Ingredient getInput() {
        return inputItem;
    }

    public Ingredient getInput2() {
        return inputItem2;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static final class Type implements RecipeType<FossilBreakerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "fossil_breaker";
    }

    public static class Serializer implements RecipeSerializer<FossilBreakerRecipe> {
        private Serializer() {}
        public static final Serializer INSTANCE = new Serializer();

        public static final ResourceLocation ID =
                ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "fossil_breaker");


        public static final MapCodec<FossilBreakerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("inputItem").forGetter(FossilBreakerRecipe::getInput),
                Ingredient.CODEC.fieldOf("inputItem2").forGetter(FossilBreakerRecipe::getInput2),
                ItemStack.CODEC.fieldOf("outputItem").forGetter(recipe -> recipe.getResultItem(null))
        ).apply(instance, FossilBreakerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FossilBreakerRecipe> STREAM_CODEC = StreamCodec.of(FossilBreakerRecipe.Serializer::toNetwork, FossilBreakerRecipe.Serializer::fromNetwork);

        public static FossilBreakerRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            Ingredient input2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            return new FossilBreakerRecipe(input, input2, output);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, FossilBreakerRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.inputItem);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.inputItem2);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
        }

        @Override
        public @NotNull MapCodec<FossilBreakerRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FossilBreakerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}

// PowerUpL NotLikeThis PowerUpR