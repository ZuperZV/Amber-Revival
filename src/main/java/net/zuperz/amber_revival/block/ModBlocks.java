package net.zuperz.amber_revival.block;

import net.minecraft.client.resources.model.Material;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.custom.AmberDisplayBlock;
import net.zuperz.amber_revival.block.custom.FallingDropExperienceBlock;
import net.zuperz.amber_revival.block.custom.FossilBreakerBlock;
import net.zuperz.amber_revival.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AmberRevival.MOD_ID);

    public static final DeferredBlock<Block> AMBER_DISPLAY = registerBlock("amber_display",
            () -> new AmberDisplayBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<Block> FOSSIL_BREAKER = registerBlock("fossil_breaker",
            () -> new FossilBreakerBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<Block> AMBER_SAND_ORE = registerBlock("amber_sand_ore",
            () -> new FallingDropExperienceBlock(
                    UniformInt.of(1, 5),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(1.0F).destroyTime(3.5f).sound(SoundType.SAND).requiresCorrectToolForDrops()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}