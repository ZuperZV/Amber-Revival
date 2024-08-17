package net.zuperz.amber_revival.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.block.entity.custom.AmberDisplayBlockEntity;
import net.zuperz.amber_revival.block.entity.custom.FossilBreakerBlockEntity;

import java.util.function.Supplier;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AmberRevival.MOD_ID);

    public static final Supplier<BlockEntityType<AmberDisplayBlockEntity>> AMBER_DISPLAY_BE =
            BLOCK_ENTITIES.register("amber_display_be", () -> BlockEntityType.Builder.of(
                    AmberDisplayBlockEntity::new, ModBlocks.AMBER_DISPLAY.get()).build(null));

    public static final Supplier<BlockEntityType<FossilBreakerBlockEntity>> FOSSIL_BREAKER_BE =
            BLOCK_ENTITIES.register("fossil_breaker_be", () -> BlockEntityType.Builder.of(
                    FossilBreakerBlockEntity::new, ModBlocks.FOSSIL_BREAKER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}