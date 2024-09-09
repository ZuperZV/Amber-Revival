package net.zuperz.amber_revival.event;

import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.entity.ModEntities;
import net.zuperz.amber_revival.entity.client.ModModelLayers;
import net.zuperz.amber_revival.entity.client.RaptorModel;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AmberRevival.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(ModModelLayers.RAPTOR, () -> RaptorModel.createBodyLayer(new CubeDeformation(0.0F)));

        event.registerLayerDefinition(ModModelLayers.RAPTOR_ARMOR, () -> RaptorModel.createBodyLayer(new CubeDeformation(0.0F)));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RAPTOR.get(), RaptorEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.RAPTOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}