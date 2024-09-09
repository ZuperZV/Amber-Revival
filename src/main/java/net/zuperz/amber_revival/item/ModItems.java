package net.zuperz.amber_revival.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.entity.ModEntities;
import net.zuperz.amber_revival.item.custom.ModFoodProoerties;
import net.zuperz.amber_revival.item.custom.ModToolTiers;
import net.zuperz.amber_revival.item.custom.ResinItem;
import net.zuperz.amber_revival.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AmberRevival.MOD_ID);

    public static final DeferredItem<Item> AMBER = ITEMS.register("amber",
            () -> new Item(new Item.Properties())
    );

    public static final DeferredItem<Item> AMBER_INGOT = ITEMS.register("amber_ingot",
            () -> new Item(new Item.Properties())
    );

    public static final DeferredItem<Item> RESIN = ITEMS.register("resin",
            () -> new ResinItem(new Item.Properties())
    );

    public static final DeferredItem<Item> RESIN_PICKAXE = ITEMS.register("resin_pickaxe",
            () -> new PickaxeItem(ModToolTiers.AMBER,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.AMBER, 3, -2.4f))));

    public static final DeferredItem<Item> SLIME_AMBER = ITEMS.register("slime_amber",
            () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> SPIDER_AMBER = ITEMS.register("spider_amber",
            () -> new Item(new Item.Properties().stacksTo(1))
    );

    public static final DeferredItem<Item> AMBER_AXOLOTL = ITEMS.register("amber_axolotl",
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

    public static final DeferredItem<Item> WARD_CUT_MUSIC_DISC = ITEMS.registerItem("ward_cut_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModSounds.WARD_CUT_KEY).stacksTo(1)));


    public static final DeferredItem<Item> COOKED_RAPTOR_CHOPS = ITEMS.register("cooked_raptor_chops",
            () -> new Item(new Item.Properties().food(ModFoodProoerties.COOKED_RAPTOR_CHOPS))
    );

    public static final DeferredItem<Item> RAPTOR_CHOPS = ITEMS.register("raptor_chops",
            () -> new Item(new Item.Properties().food(ModFoodProoerties.RAPTOR_CHOPS))
    );

    public static final DeferredItem<Item> RAPTOR_FEAHER = ITEMS.register("raptor_feaher",
            () -> new Item(new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}