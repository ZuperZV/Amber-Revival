package net.zuperz.amber_revival.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.item.ModItems;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmberRevival.MOD_ID);

    public static final Supplier<CreativeModeTab> AMBER_REIVAL_TAB =
            CREATIVE_MODE_TABS.register("amber_revival_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.amber_revival.amber_revival_tab"))
                    .icon(() -> new ItemStack(ModItems.AMBER.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AMBER);
                        pOutput.accept(ModItems.RESIN);

                        pOutput.accept(ModBlocks.AMBER_SAND_ORE);
                        pOutput.accept(ModBlocks.AMBER_DISPLAY);
                        pOutput.accept(ModBlocks.FOSSIL_BREAKER);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
