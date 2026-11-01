package com.barnacle.encore_force.registry;

import com.barnacle.encore_force.EncoreForce;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BCCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EncoreForce.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BARN_CORE_TAB = CREATIVE_MODE_TAB.register("barn_core_creative_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BCItems.Glash_SPAWN_EGG.get()))
                    .title(Component.translatable("creative_tab.barn_core"))
                    .displayItems((parameters, output) -> {
                        output.accept(BCItems.Glash_SPAWN_EGG);
                    })
                    .build());
}