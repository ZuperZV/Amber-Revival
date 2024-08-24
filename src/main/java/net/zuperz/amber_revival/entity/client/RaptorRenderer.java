package net.zuperz.amber_revival.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

import java.util.Map;

public class RaptorRenderer extends MobRenderer<RaptorEntity, RaptorModel<RaptorEntity>> { // Changed the model type
    private static final Map<RaptorVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RaptorVariant.class), map -> {
                map.put(RaptorVariant.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "textures/entity/raptor/raptor.png"));
                map.put(RaptorVariant.ALBINO,
                        ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "textures/entity/raptor/white_raptor.png"));
                map.put(RaptorVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(AmberRevival.MOD_ID, "textures/entity/raptor/blue_raptor.png"));
            });

    public RaptorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RaptorModel<>(pContext.bakeLayer(ModModelLayers.RAPTOR)), 0.75f); // Changed the model type
        this.addLayer(new RaptorHeldItemLayer(this, pContext.getItemInHandRenderer())); // No change needed here
    }

    @Override
    public ResourceLocation getTextureLocation(RaptorEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(RaptorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
