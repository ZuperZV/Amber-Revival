package net.zuperz.amber_revival.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmberRevival.MOD_ID);

    public static final Supplier<CreativeModeTab> AMBER_REVIVAL_TAB =
            CREATIVE_MODE_TABS.register("amber_revival_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.amber_revival.amber_revival_tab"))
                    .icon(() -> new ItemStack(ModItems.AMBER.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AMBER.get());
                        pOutput.accept(ModItems.AMBER_INGOT.get());
                        pOutput.accept(ModItems.RESIN.get());

                        pOutput.accept(ModBlocks.AMBER_SAND_ORE.get());
                        pOutput.accept(ModBlocks.AMBER_DISPLAY.get());
                        //pOutput.accept(ModBlocks.FOSSIL_BREAKER.get());

                        pOutput.accept(ModItems.RAPTOR_SPAWN_EGG.get());
                        pOutput.accept(ModItems.COOKED_RAPTOR_CHOPS.get());
                        pOutput.accept(ModItems.RAPTOR_CHOPS.get());

                        pOutput.accept(ModItems.WARD_CUT_MUSIC_DISC.get());

                        pOutput.accept(ModItems.SLIME_AMBER.get());
                        pOutput.accept(ModItems.SPIDER_AMBER.get());
                        pOutput.accept(ModItems.AMBER_AXOLOTL.get());
                        pOutput.accept(ModItems.AMBER_RAPTOR_SKULL.get());

                        pOutput.accept(ModBlocks.AMBER_LOG.get());
                        pOutput.accept(ModBlocks.RESIN_AMBER_LOG.get());
                        pOutput.accept(ModBlocks.AMBER_WOOD.get());
                        pOutput.accept(ModBlocks.AMBER_PLANKS.get());
                        pOutput.accept(ModBlocks.STRIPPED_AMBER_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_AMBER_WOOD.get());

                        pOutput.accept(ModBlocks.AMBER_STAIRS.get());
                        pOutput.accept(ModBlocks.AMBER_SLAB.get());
                        pOutput.accept(ModBlocks.AMBER_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.AMBER_BUTTON.get());
                        pOutput.accept(ModBlocks.AMBER_FENCE.get());
                        pOutput.accept(ModBlocks.AMBER_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.AMBER_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.AMBER_DOOR.get());
                        //pOutput.accept(ModItems.AMBER_SIGN.get());
                        //pOutput.accept(ModItems.AMBER_HANGING_SIGN.get());

                        pOutput.accept(ModBlocks.AMBER_LEAVES.get());
                        //pOutput.accept(ModBlocks.AMBER_SAPLING.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
