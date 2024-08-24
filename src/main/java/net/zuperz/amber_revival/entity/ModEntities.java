package net.zuperz.amber_revival.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AmberRevival.MOD_ID);

    public static final Supplier<EntityType<RaptorEntity>> RAPTOR =
            ENTITY_TYPES.register("raptor", () -> EntityType.Builder.of(RaptorEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("raptor"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
