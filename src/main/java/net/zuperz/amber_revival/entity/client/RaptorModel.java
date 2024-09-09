package net.zuperz.amber_revival.entity.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Animal;
import net.zuperz.amber_revival.entity.custom.RaptorEntity;

public class RaptorModel<R extends Animal> extends HierarchicalModel<RaptorEntity> {
    private final ModelPart raptor;
    public final ModelPart head;

    public RaptorModel(ModelPart root) {
        this.raptor = root.getChild("raptor");
        this.head = raptor.getChild("Body").getChild("neak").getChild("head");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition raptor = partdefinition.addOrReplaceChild("raptor", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition Body = raptor.addOrReplaceChild("Body", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.0F, -3.0F, -9.0F, 6.0F, 6.0F, 12.0F, deformation)
                        .texOffs(18, 7)
                        .addBox(0.0F, -5.0F, -8.0F, 0.0F, 2.0F, 11.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create()
                        .texOffs(18, 7)
                        .addBox(0.0F, -2.0F, -6.0F, 0.0F, 2.0F, 11.0F, deformation),
                PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition cube_r2 = Body.addOrReplaceChild("cube_r2", CubeListBuilder.create()
                        .texOffs(18, 7)
                        .addBox(0.0F, -2.0F, -6.0F, 0.0F, 2.0F, 11.0F, deformation),
                PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition Tall = Body.addOrReplaceChild("Tall", CubeListBuilder.create()
                        .texOffs(17, 23)
                        .addBox(-4.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, deformation)
                        .texOffs(24, 3)
                        .addBox(-2.0F, -4.0F, 0.0F, 0.0F, 2.0F, 7.0F, deformation),
                PartPose.offset(2.0F, -1.0F, 3.0F));

        PartDefinition Tall2 = Tall.addOrReplaceChild("Tall2", CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 7.0F, deformation)
                        .texOffs(18, 13)
                        .addBox(1.0F, -3.5F, 0.0F, 0.0F, 2.0F, 7.0F, deformation),
                PartPose.offset(-3.0F, -0.5F, 7.0F));

        PartDefinition Tall3 = Tall2.addOrReplaceChild("Tall3", CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 6.0F, deformation),
                PartPose.offset(1.0F, 0.0F, 7.0F));

        PartDefinition LArm = Body.addOrReplaceChild("LArm", CubeListBuilder.create()
                        .texOffs(38, 32)
                        .addBox(0.0F, -1.0F, -1.0F, 1.0F, 6.0F, 2.0F, deformation),
                PartPose.offset(3.0F, 1.0F, -7.0F));

        PartDefinition neak = Body.addOrReplaceChild("neak", CubeListBuilder.create(), PartPose.offset(1.5F, -2.0F, -8.0F));

        PartDefinition cube_r3 = neak.addOrReplaceChild("cube_r3", CubeListBuilder.create()
                        .texOffs(46, 46)
                        .addBox(-2.0F, -5.0F, -2.0F, 0.0F, 9.0F, 9.0F, deformation),
                PartPose.offsetAndRotation(0.5F, -4.0F, 1.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r4 = neak.addOrReplaceChild("cube_r4", CubeListBuilder.create()
                        .texOffs(0, 30)
                        .addBox(-3.0F, -4.0F, -2.0F, 3.0F, 7.0F, 5.0F, deformation),
                PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition head = neak.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-4.5F, -4.0F, -5.0F, 6.0F, 6.0F, 6.0F, deformation)
                        .texOffs(0, 48)
                        .addBox(-1.5F, -12.0F, -5.0F, 0.0F, 8.0F, 8.0F, deformation),
                PartPose.offset(0.0F, -3.0F, -2.0F));

        PartDefinition nese = head.addOrReplaceChild("nese", CubeListBuilder.create()
                        .texOffs(35, 0)
                        .addBox(-4.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, deformation),
                PartPose.offset(0.5F, -1.0F, -5.0F));

        PartDefinition mund = head.addOrReplaceChild("mund", CubeListBuilder.create()
                        .texOffs(36, 10)
                        .addBox(-4.0F, -1.0F, -4.0F, 5.0F, 2.0F, 3.0F, deformation),
                PartPose.offset(0.0F, 1.0F, -4.0F));

        PartDefinition RArm = Body.addOrReplaceChild("RArm", CubeListBuilder.create()
                .texOffs(38, 32)
                .mirror()
                .addBox(-1.0F, -1.0F, -1.0F, 1.0F, 6.0F, 2.0F, deformation)
                .mirror(false), PartPose.offset(-3.0F, 1.0F, -7.0F));

        PartDefinition LLeg = raptor.addOrReplaceChild("LLeg", CubeListBuilder.create()
                        .texOffs(32, 20)
                        .addBox(0.0F, -1.5F, -1.75F, 3.0F, 6.0F, 4.0F, deformation),
                PartPose.offset(3.0F, -0.5F, 1.75F));

        PartDefinition leg_2 = LLeg.addOrReplaceChild("leg_2", CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, deformation),
                PartPose.offset(1.0F, 4.5F, 1.25F));

        PartDefinition leg_3 = leg_2.addOrReplaceChild("leg_3", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, 0.0F, -2.0F, 3.0F, 2.0F, 3.0F, deformation),
                PartPose.offset(0.0F, 1.25F, 1.0F));

        PartDefinition RLeg = raptor.addOrReplaceChild("RLeg", CubeListBuilder.create()
                        .texOffs(32, 20)
                        .mirror()
                        .addBox(-3.0F, -1.5F, -1.75F, 3.0F, 6.0F, 4.0F, deformation)
                        .mirror(false),
                PartPose.offset(-3.0F, -0.5F, 1.75F));

        PartDefinition leg_2_r1 = RLeg.addOrReplaceChild("leg_2_r1", CubeListBuilder.create()
                        .texOffs(0, 18)
                        .addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, deformation),
                PartPose.offsetAndRotation(-1.0F, 4.5F, 1.25F, 0.0F, 0.0F, 0.0F));

        PartDefinition leg_3_r1 = leg_2_r1.addOrReplaceChild("leg_3_r1", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, 0.0F, -2.0F, 3.0F, 2.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(0.0F, 1.25F, 1.0F, 0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(RaptorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(RaptorAnimations.raptor_walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, RaptorAnimations.raptor_idle, ageInTicks, 1f);
        this.animate(entity.sitAnimationState, RaptorAnimations.raptor_sit, ageInTicks, 1f);
    }

    public void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        raptor.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return raptor;
    }
}
