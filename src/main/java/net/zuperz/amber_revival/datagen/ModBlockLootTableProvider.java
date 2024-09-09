package net.zuperz.amber_revival.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.item.ModItems;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;


import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.AMBER_DISPLAY.get());
        dropSelf(ModBlocks.FOSSIL_BREAKER.get());

        this.add(ModBlocks.AMBER_SAND_ORE.get(),
                block -> createMultipleOreDropsAmber(ModBlocks.AMBER_SAND_ORE.get(), ModItems.AMBER.get(), ModItems.RESIN.get(), ModItems.SLIME_AMBER.get(), ModItems.AMBER_RAPTOR_SKULL.get(), ModItems.WARD_CUT_MUSIC_DISC.get(), 1, 2));

        this.add(ModBlocks.RESIN_AMBER_LOG.get(),
                block -> createMultipleItemLogDrops(ModBlocks.RESIN_AMBER_LOG.get(), Item.byBlock(ModBlocks.AMBER_LOG.get()), ModItems.RESIN.get(), 0, 1));


        this.dropSelf(ModBlocks.AMBER_PLANKS.get());
        this.dropSelf(ModBlocks.AMBER_LOG.get());
        this.dropSelf(ModBlocks.AMBER_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_AMBER_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_AMBER_WOOD.get());

        dropSelf(ModBlocks.AMBER_PLANKS.get());
        dropSelf(ModBlocks.AMBER_LOG.get());
        dropSelf(ModBlocks.AMBER_PLANKS.get());
        dropSelf(ModBlocks.AMBER_STAIRS.get());
        this.add(ModBlocks.AMBER_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.AMBER_SLAB.get()));

        dropSelf(ModBlocks.AMBER_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.AMBER_BUTTON.get());

        dropSelf(ModBlocks.AMBER_FENCE.get());
        dropSelf(ModBlocks.AMBER_FENCE_GATE.get());

        dropSelf(ModBlocks.AMBER_TRAPDOOR.get());
        this.add(ModBlocks.AMBER_DOOR.get(),
                block -> createDoorTable(ModBlocks.AMBER_DOOR.get()));
        
        this.add(ModBlocks.AMBER_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.AMBER_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.AMBER_SAPLING.get());
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder createMultipleItemLogDrops(Block pBlock, Item log, Item item, float minDrops, float maxDrops) {
        LootPool.Builder logPool = LootPool.lootPool()
                .add(LootItem.lootTableItem(log)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));

        LootPool.Builder itemPool = LootPool.lootPool()
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .when(LootItemRandomChanceCondition.randomChance(0.8f)));

        return LootTable.lootTable()
                .withPool(logPool)
                .withPool(itemPool);
    }

    protected LootTable.Builder createMultipleOreDropsAmber(Block block, Item item, Item ekstraItem, Item item2, Item item3, Item item4, float minDrops, float maxDrops) {
        LootPool.Builder amberPool = LootPool.lootPool()
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))));

        LootPool.Builder ekstraItemPool = LootPool.lootPool()
                .add(LootItem.lootTableItem(ekstraItem)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))))
                        .when(LootItemRandomChanceCondition.randomChance(0.8f))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.RESIN_PICKAXE.get())));



        LootPool.Builder item2Pool = LootPool.lootPool()
                .add(LootItem.lootTableItem(item2)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.08f)));

        LootPool.Builder item3Pool = LootPool.lootPool()
                .add(LootItem.lootTableItem(item3)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f)));

        LootPool.Builder item4Pool = LootPool.lootPool()
                .add(LootItem.lootTableItem(item4)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f)));

        return LootTable.lootTable()
                .withPool(amberPool)
                .withPool(ekstraItemPool)
                .withPool(item2Pool)
                .withPool(item3Pool)
                .withPool(item4Pool);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}