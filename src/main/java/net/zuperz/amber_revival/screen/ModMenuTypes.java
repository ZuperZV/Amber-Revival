package net.zuperz.amber_revival.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;


public class ModMenuTypes {
    public static DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, AmberRevival.MOD_ID);

    public static final DeferredHolder<MenuType<?>,MenuType<FossilBreakerMenu>> FOSSIL_BREAKER_MENU =
            registerMenuType(FossilBreakerMenu::new, "fossil_breaker_menu");

    private static <T extends AbstractContainerMenu>
    DeferredHolder<MenuType<?>,MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name)
    {
        return MENU_TYPES.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}