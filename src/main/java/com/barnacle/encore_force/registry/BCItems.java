package com.barnacle.encore_force.registry;

import com.barnacle.encore_force.EncoreForce;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BCItems {

    public static final DeferredRegister.Items ITEM = DeferredRegister.createItems(EncoreForce.MOD_ID);

    public static List<DeferredItem<? extends Item>> ITEM_TRANSLATIONS = new ArrayList<>();

    public static final DeferredItem<Item> Glash_SPAWN_EGG = registerSpawnEggItem("glash", BCEntities.GLASH, 0x1d1f1c, 0x375e5d);

    private static <I extends Item> DeferredItem<I> registerItem(String name, Supplier<? extends I> supplier) {
        DeferredItem<I> item = ITEM.register(name, supplier);
        ITEM_TRANSLATIONS.add(item);
        return item;
    }

    private static <I extends Item> DeferredItem<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        return ITEM.register(name, supplier);
    }

    private static DeferredItem<Item> registerSpawnEggItem(String name, Supplier<? extends EntityType<? extends Mob>> type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new DeferredSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }
}
