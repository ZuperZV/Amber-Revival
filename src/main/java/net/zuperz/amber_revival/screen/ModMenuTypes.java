package net.zuperz.amber_revival.screen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;

import java.util.function.Supplier;


public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, AmberRevival.MOD_ID);

    public static final Supplier<MenuType<FossilBreakerMenu>> FOSSIL_BREAKER_MENU = MENUS.register("fossil_breaker_menu",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> new FossilBreakerMenu(windowId, inv.player, data.readBlockPos())));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}