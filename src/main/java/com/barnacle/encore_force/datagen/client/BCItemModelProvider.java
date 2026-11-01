package com.barnacle.encore_force.datagen.client;

import com.barnacle.encore_force.EncoreForce;
import com.platypushasnohat.sinew.datagen.client.SinewItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BCItemModelProvider extends SinewItemModelProvider {

    public BCItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, EncoreForce.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem(
        );

        this.handheldItem(
        );

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof DeferredSpawnEggItem && BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(EncoreForce.MOD_ID)) {
                this.withExistingParent(name(item), "item/template_spawn_egg");
            }
        }
    }
}