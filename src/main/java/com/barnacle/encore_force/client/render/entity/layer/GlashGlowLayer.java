package com.barnacle.encore_force.client.render.entity.layer;

import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.client.model.entity.GlashModel;
import com.barnacle.encore_force.entities.Glash;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.platypushasnohat.sinew.client.render.SinewRenderTypes;
import com.platypushasnohat.sinew.utils.SinewColorUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class GlashGlowLayer extends RenderLayer<Glash, GlashModel> {

    private static final RenderType TEXTURE_LOCATION = SinewRenderTypes.getEyesAlphaEnabled(EncoreForce.location("textures/entity/glash/glash_eyes.png"));

    public GlashGlowLayer(RenderLayerParent<Glash, GlashModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Glash glash, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (glash.isInvisible()) {
            return;
        }
        VertexConsumer consumer = buffer.getBuffer(TEXTURE_LOCATION);
        this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(glash, 0.0F), SinewColorUtils.packColor(1.0F, 1.0F, 1.0F, 0.7F));
    }
}
