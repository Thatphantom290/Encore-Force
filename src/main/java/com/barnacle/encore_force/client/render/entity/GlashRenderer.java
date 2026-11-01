package com.barnacle.encore_force.client.render.entity;

import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.client.model.entity.GlashModel;
import com.barnacle.encore_force.client.render.entity.layer.GlashGlowLayer;
import com.barnacle.encore_force.entities.Glash;
import com.barnacle.encore_force.registry.BCModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlashRenderer extends MobRenderer<Glash, GlashModel> {
    private static final ResourceLocation TEXTURE_LOCATION = EncoreForce.location("textures/entity/glash/glash.png");

    public GlashRenderer(EntityRendererProvider.Context context) {
        super(context, new GlashModel(context.bakeLayer(BCModelLayers.GLASH)), 0.5F);
        this.addLayer(new GlashGlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Glash dicer) {
        return TEXTURE_LOCATION;
    }
}
