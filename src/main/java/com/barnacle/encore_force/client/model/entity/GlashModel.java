package com.barnacle.encore_force.client.model.entity;

import com.barnacle.encore_force.client.model.entity.animation.GlashAnimations;
import com.barnacle.encore_force.entities.Glash;
import com.platypushasnohat.sinew.client.model.entity.SinewEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GlashModel extends SinewEntityModel<Glash> {

    private final ModelPart all;
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;

    public GlashModel(ModelPart root) {
        this.all = root.getChild("all");
        this.root = this.all.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
    }


    @Override
    public ModelPart root() {
        return this.all;
    }

    @Override
    protected void setupAnimations(Glash entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.walkAnimationState, GlashAnimations.GLASH_WALK, limbSwing, limbSwingAmount, 1.4F, 0.92F, partialTicks);
        this.animateIdleSmooth(entity.idleAnimationState, GlashAnimations.GLASH_IDLE, ageInTicks, partialTicks, limbSwingAmount);
        this.animateSmooth(entity.attackAnimationState, GlashAnimations.GLASH_ATTACK, ageInTicks, partialTicks);
        this.animateSmooth(entity.punchAnimationState, GlashAnimations.GLASH_PUNCH, ageInTicks, partialTicks);
        this.head.yRot += netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot += headPitch * Mth.DEG_TO_RAD;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition root = all.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, -8.0F, -1.5F, 6.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition rightface = head.addOrReplaceChild("rightface", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, -5.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-2.0F, -7.0F, -5.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9628F, 0.2537F, 1.5444F, 0.0F, -0.1745F, 0.0F));

        PartDefinition leftface = head.addOrReplaceChild("leftface", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -8.0F, -5.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(2, 29).addBox(1.0F, -7.0F, -5.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0372F, 0.2537F, 1.5444F, 0.0F, 0.1745F, 0.0F));

        PartDefinition leftleg = root.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(33, 12).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition lefttoe = leftleg.addOrReplaceChild("lefttoe", CubeListBuilder.create().texOffs(25, 11).addBox(-2.0F, -0.2F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition rightleg = root.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(39, 12).mirror().addBox(-1.0F, 0.0F, 0.0F, 3.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition righttoe = rightleg.addOrReplaceChild("righttoe", CubeListBuilder.create().texOffs(25, 11).mirror().addBox(-1.0F, -0.2F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 45, 36);
    }
}
