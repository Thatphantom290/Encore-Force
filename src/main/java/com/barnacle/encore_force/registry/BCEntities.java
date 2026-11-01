package com.barnacle.encore_force.registry;

import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.entities.Glash;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BCEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, EncoreForce.MOD_ID);

    public static List<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> ENTITY_TRANSLATIONS = new ArrayList<>();

    public static final DeferredHolder<EntityType<?>, EntityType<Glash>> GLASH = registerEntity("glash", Glash::new, MobCategory.MONSTER, builder -> builder.sized(0.8F, 1.7F).eyeHeight(1.4F).clientTrackingRange(8));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        DeferredHolder<EntityType<?>, EntityType<E>> entity = registerEntityNoLang(name, factory, entityClassification, builderConsumer);
        ENTITY_TRANSLATIONS.add(entity);
        return entity;
    }

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntityNoLang(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        return ENTITY_TYPE.register(name, () -> {
            var builder = EntityType.Builder.of(factory, entityClassification);
            builderConsumer.accept(builder);
            return builder.build(EncoreForce.MOD_ID + ":" + name);
        });
    }
}
