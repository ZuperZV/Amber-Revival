package net.zuperz.amber_revival.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class RaptorArmorLayer extends RenderLayer<RaptorEntity, RaptorModel<RaptorEntity>> {
    private final RaptorModel<RaptorEntity> model;
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("amber_revival", "textures/entity/raptor/raptor_armor/raptor_armor.png");
    private static final Map<Crackiness.Level, ResourceLocation> ARMOR_CRACK_LOCATIONS = Map.of(
            Crackiness.Level.LOW, ResourceLocation.fromNamespaceAndPath("amber_revival", "textures/entity/raptor/raptor_armor/raptor_armor_crackiness_low.png"),
            Crackiness.Level.MEDIUM, ResourceLocation.fromNamespaceAndPath("amber_revival", "textures/entity/raptor/raptor_armor/raptor_armor_crackiness_medium.png"),
            Crackiness.Level.HIGH, ResourceLocation.fromNamespaceAndPath("amber_revival", "textures/entity/raptor/raptor_armor/raptor_armor_crackiness_high.png")
    );

    public RaptorArmorLayer(RenderLayerParent<RaptorEntity, RaptorModel<RaptorEntity>> pRenderer, EntityModelSet pModels) {
        super(pRenderer);
        this.model = new RaptorModel<>(pModels.bakeLayer(ModModelLayers.RAPTOR_ARMOR));
    }

    @Override
    public void render(
            PoseStack pPoseStack,
            MultiBufferSource pBufferSource,
            int pPackedLight,
            RaptorEntity pLivingEntity,
            float pLimbSwing,
            float pLimbSwingAmount,
            float pPartialTick,
            float pAgeInTicks,
            float pNetHeadYaw,
            float pHeadPitch
    ) {
        if (pLivingEntity.hasArmor()) {
            ItemStack itemstack = pLivingEntity.getBodyArmorItem();

            pPoseStack.pushPose();
            pPoseStack.translate(0.0D, -0.02D, 0.016D);
            pPoseStack.scale(1.03f, 1f, 1.03f);


            if (itemstack.getItem() instanceof AnimalArmorItem animalArmorItem &&
                    animalArmorItem.getBodyType() == AnimalArmorItem.BodyType.CANINE) {

                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
                this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

                // Use the static texture
                VertexConsumer vertexConsumer = pBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
                this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);

                this.maybeRenderCracks(pPoseStack, pBufferSource, pPackedLight, itemstack);
            }

            pPoseStack.popPose(); // Restore the pose stack to its original state
        }
    }

    private void maybeRenderCracks(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ItemStack pArmorStack) {
        Crackiness.Level crackinessLevel = Crackiness.WOLF_ARMOR.byDamage(pArmorStack);
        if (crackinessLevel != Crackiness.Level.NONE) {
            ResourceLocation crackTexture = ARMOR_CRACK_LOCATIONS.get(crackinessLevel);
            if (crackTexture != null) {
                VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityTranslucent(crackTexture));
                this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
            }
        }
    }
}
