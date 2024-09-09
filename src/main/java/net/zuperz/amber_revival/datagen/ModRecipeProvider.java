package net.zuperz.amber_revival.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {

        SimpleCookingRecipeBuilder.blasting (Ingredient.of(ModItems.RESIN.get()), RecipeCategory.MISC , ModItems.AMBER.get(), 0.15f , 200)
                .unlockedBy("has_resin",inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RESIN.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "resin"));

        SimpleCookingRecipeBuilder.blasting (Ingredient.of(ModItems.AMBER.get()), RecipeCategory.MISC , ModItems.AMBER_INGOT.get(), 0.15f , 200)
                .unlockedBy("has_amber",inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AMBER.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "amber_ingot"));

        SimpleCookingRecipeBuilder.smelting (Ingredient.of(ModItems.RAPTOR_CHOPS.get()), RecipeCategory.MISC , ModItems.COOKED_RAPTOR_CHOPS.get(), 0.15f, 300)
                .unlockedBy("has_raptor_chops",inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RAPTOR_CHOPS.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "cooked_raptor_chops_from_smelting"));

        SimpleCookingRecipeBuilder.smoking (Ingredient.of(ModItems.RAPTOR_CHOPS.get()), RecipeCategory.MISC , ModItems.COOKED_RAPTOR_CHOPS.get(), 0.15f, 100)
                .unlockedBy("has_raptor_chops",inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RAPTOR_CHOPS.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "cooked_raptor_chops_from_blasting"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RESIN_PICKAXE.get())
                .pattern("BBB")
                .pattern(" A ")
                .pattern(" A ")
                .define('A', Items.STICK)
                .define('B', ModItems.RESIN)
                .unlockedBy("has_resin", has(ModItems.RESIN.get())).save(pWriter);
        
        
        /* WOOD */

        woodFromLogs(pWriter, ModBlocks.AMBER_WOOD.get(), ModBlocks.AMBER_LOG.get());
        woodFromLogs(pWriter, ModBlocks.STRIPPED_AMBER_WOOD.get(), ModBlocks.STRIPPED_AMBER_LOG.get());

        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AMBER_SLAB.get(), Ingredient.of(ModBlocks.AMBER_PLANKS.get()))
                .group("wooden_slab")
                .unlockedBy(getHasName(ModBlocks.AMBER_PLANKS.get()), has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        /*ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.AMBER_SIGN.get(), 3)
                .group("wooden_sign")
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .define('A', ModBlocks.AMBER_PLANKS.get().asItem())
                .define('B', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ModBlocks.AMBER_PLANKS.get()), has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);
         */

        /*ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.AMBER_HANGING_SIGN.get(), 6)
                .group("hanging_sign")
                .pattern("B B")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.STRIPPED_AMBER_LOG.get())
                .define('B', Items.CHAIN)
                .unlockedBy("has_stripped_logs", has(ModBlocks.STRIPPED_AMBER_LOG.get()))
                .save(pWriter);
         */

        stairBuilder(ModBlocks.AMBER_STAIRS.get(), Ingredient.of(ModBlocks.AMBER_PLANKS.get()))
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PRESSURE_PLATE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AMBER_PRESSURE_PLATE.get())
                .pattern("AA")
                .define('A', ModBlocks.AMBER_PLANKS.get())
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.AMBER_BUTTON.get())
                .requires(ModBlocks.AMBER_PLANKS.get())
                .unlockedBy("has_amber_planks", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.AMBER_PLANKS.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AMBER_FENCE.get(), 3)
                .pattern("ABA")
                .pattern("ABA")
                .define('A', ModBlocks.AMBER_PLANKS.get())
                .define('B', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AMBER_FENCE_GATE.get())
                .pattern("BAB")
                .pattern("BAB")
                .define('A', ModBlocks.AMBER_PLANKS.get())
                .define('B', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AMBER_TRAPDOOR.get(), 2)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.AMBER_PLANKS.get())
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.AMBER_DOOR.get(), 3)
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', ModBlocks.AMBER_PLANKS.get())
                .unlockedBy("has_amber_planks", has(ModBlocks.AMBER_PLANKS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.AMBER_PLANKS.get(), 4)
                .requires(ModBlocks.AMBER_LOG.get())
                .unlockedBy("has_amber_planks", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.AMBER_LOG.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "amber_planks_from_log"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.AMBER_PLANKS.get(), 4)
                .requires(ModBlocks.AMBER_WOOD.get())
                .unlockedBy("has_amber_planks", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.AMBER_WOOD.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "amber_planks_from_wood"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.AMBER_PLANKS.get(), 3)
                .requires(ModBlocks.STRIPPED_AMBER_LOG.get())
                .unlockedBy("has_amber_planks", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.STRIPPED_AMBER_LOG.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "stripped_amber_planks_from_log"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.AMBER_PLANKS.get(), 3)
                .requires(ModBlocks.STRIPPED_AMBER_WOOD.get())
                .unlockedBy("has_amber_planks", inventoryTrigger(ItemPredicate.Builder.item().
                        of(ModBlocks.STRIPPED_AMBER_WOOD.get()).build()))
                .save(pWriter, ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "stripped_amber_planks_from_wood"));
    }


    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, AmberRevival.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}