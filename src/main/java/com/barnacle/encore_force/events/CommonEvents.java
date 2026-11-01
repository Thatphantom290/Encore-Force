package com.barnacle.encore_force.events;


import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.entities.Glash;
import com.barnacle.encore_force.registry.BCEntities;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = EncoreForce.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(BCEntities.GLASH.get(), Glash.registerAttributes().build());
    }

    @SubscribeEvent
    private static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(BCEntities.GLASH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}