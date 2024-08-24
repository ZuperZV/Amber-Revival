package net.zuperz.amber_revival.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.entity.ModEntities;
import net.zuperz.amber_revival.item.custom.ResinItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AmberRevival.MOD_ID);

    public static final DeferredItem<Item> AMBER = ITEMS.register("amber",
            () -> new Item(new Item.Properties())
    );

    public static final DeferredItem<Item> RESIN = ITEMS.register("resin",
            () -> new ResinItem(new Item.Properties())
    );

    public static final DeferredItem<Item> SLIME_AMBER = ITEMS.register("slime_amber",
            () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> AMBER_RAPTOR_SKULL = ITEMS.register("amber_raptor_skull",
            () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> RAPTOR_SKULL = ITEMS.register("raptor_skull",
            () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> RAPTOR_SPAWN_EGG = ITEMS.register("raptor_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RAPTOR, 0x282828, 0xac9781, new Item.Properties())
    );


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}