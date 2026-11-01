package com.barnacle.encore_force.registry;

import com.barnacle.encore_force.EncoreForce;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class BCModelLayers {
    public static final ModelLayerLocation GLASH = register("glash");

    private static ModelLayerLocation register(String id) {
        return new ModelLayerLocation(EncoreForce.location(id), "main");
    }
}
