package com.barnacle.encore_force;

import com.barnacle.encore_force.datagen.client.BCItemModelProvider;
import com.barnacle.encore_force.datagen.client.BCLanguageProvider;
import com.barnacle.encore_force.datagen.server.BCDatapackBuiltinEntriesProvider;
import com.barnacle.encore_force.registry.BCCreativeTabs;
import com.barnacle.encore_force.registry.BCEntities;
import com.barnacle.encore_force.registry.BCItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;
import net.minecraft.core.HolderLookup.Provider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import com.mojang.logging.LogUtils;

@Mod(EncoreForce.MOD_ID)
public class EncoreForce {
    public static final String MOD_ID = "encore_force";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path.toLowerCase(Locale.ROOT));
    }

    public EncoreForce(IEventBus modEventBus, ModContainer modContainer) {
        BCEntities.ENTITY_TYPE.register(modEventBus);
        BCItems.ITEM.register(modEventBus);
        BCCreativeTabs.CREATIVE_MODE_TAB.register(modEventBus);
        modEventBus.addListener(this::dataSetup);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        BCDatapackBuiltinEntriesProvider datapackEntries = new BCDatapackBuiltinEntriesProvider(output, provider);
        generator.addProvider(server, datapackEntries);
        provider = datapackEntries.getRegistryProvider();

        generator.addProvider(client, new BCItemModelProvider(output, helper));
        generator.addProvider(client, new BCLanguageProvider(output));
    }
}
