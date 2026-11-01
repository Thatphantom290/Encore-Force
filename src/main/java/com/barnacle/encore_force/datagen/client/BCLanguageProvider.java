package com.barnacle.encore_force.datagen.client;

import com.barnacle.encore_force.EncoreForce;
import com.barnacle.encore_force.registry.BCCreativeTabs;
import com.barnacle.encore_force.registry.BCEntities;
import com.barnacle.encore_force.registry.BCItems;
import com.platypushasnohat.sinew.datagen.client.SinewLanguageProvider;
import net.minecraft.data.PackOutput;

public class BCLanguageProvider extends SinewLanguageProvider {

    public BCLanguageProvider(PackOutput output) {
        super(output, EncoreForce.MOD_ID);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeTab(BCCreativeTabs.BARN_CORE_TAB.get(), "Barn Core");
        BCItems.ITEM_TRANSLATIONS.forEach(this::forItem);
        BCEntities.ENTITY_TRANSLATIONS.forEach(this::forEntity);
    }
}