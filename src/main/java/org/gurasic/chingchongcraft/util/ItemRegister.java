package org.gurasic.chingchongcraft.util;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.gurasic.chingchongcraft.items.Basic_Manual;

public class ItemRegister {
    public static final Basic_Manual BASIC_MANUAL = register("basic_manual", new Basic_Manual(new Item.Settings()));
    public static <T extends Item> T register(String path, T item) {
        return Registry.register(Registries.ITEM, new Identifier("chingchongcraft", path), item);
    }
    public static void initialize() {

    }
}
