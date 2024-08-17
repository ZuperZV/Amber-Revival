package net.zuperz.amber_revival.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.item.custom.ResinItem;

import java.util.Arrays;

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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}