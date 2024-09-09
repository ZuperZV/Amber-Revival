package net.zuperz.amber_revival.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, AmberRevival.MOD_ID);

    public static final Holder<Potion> HARDEN_POTION = POTIONS.register("harden_potion",
            () -> new Potion(
                    new MobEffectInstance(MobEffects.GLOWING, 200, 4),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 3),
                    new MobEffectInstance(MobEffects.LUCK, 400, 4)
            )
    );


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}