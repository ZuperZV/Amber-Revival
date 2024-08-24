package net.zuperz.amber_revival.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

@OnlyIn(Dist.CLIENT)
public class RaptorHeldItemLayer extends RenderLayer<RaptorEntity, RaptorModel<RaptorEntity>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public RaptorHeldItemLayer(RenderLayerParent<RaptorEntity, RaptorModel<RaptorEntity>> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer);
        this.itemInHandRenderer = pItemInHandRenderer;
    }

    @Override
    public void render(
            PoseStack pPoseStack,
            MultiBufferSource pBuffer,
            int pPackedLight,
            RaptorEntity pLivingEntity,
            float pLimbSwing,
            float pLimbSwingAmount,
            float pPartialTicks,
            float pAgeInTicks,
            float pNetHeadYaw,
            float pHeadPitch
    ) {
        pPoseStack.pushPose();

        if (pLivingEntity.isBaby()) {
            pPoseStack.scale(0.75F, 0.75F, 0.75F);
            pPoseStack.translate(0.0F, 0.5F, 0.209375F);
        }

        pPoseStack.translate(0.0F, 0.56F, -0.58F);

        RaptorModel<RaptorEntity> model = this.getParentModel();
        model.head.translateAndRotate(pPoseStack);

        if (pLivingEntity.getLookControl().isLookingAtTarget()) {
            pPoseStack.mulPose(Axis.YP.rotationDegrees(pNetHeadYaw));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(pHeadPitch));
        }

        if (pLivingEntity.isBaby()) {
            pPoseStack.translate(0.0F, -0.22F, -0.67F);
        } else {
            pPoseStack.translate(0.0F, 0.1F, -0.2F);
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        if (pLivingEntity.isSleeping()) {
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

        this.itemInHandRenderer.renderItem(pLivingEntity, pLivingEntity.getItemBySlot(EquipmentSlot.MAINHAND), ItemDisplayContext.GROUND, false, pPoseStack, pBuffer, pPackedLight);

        pPoseStack.popPose();
    }
}