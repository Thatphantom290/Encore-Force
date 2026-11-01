package com.barnacle.encore_force.events;

import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.client.model.entity.GlashModel;
import com.barnacle.encore_force.client.render.entity.GlashRenderer;
import com.barnacle.encore_force.registry.BCEntities;
import com.barnacle.encore_force.registry.BCModelLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = EncoreForce.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BCEntities.GLASH.get(), GlashRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BCModelLayers.GLASH, GlashModel::createBodyLayer);
    }
}
