package io.github.kydzombie.modnotfound;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModNotFound implements ModInitializer {

    public static final String MOD_ID = "modnotfound";

    public static final Item NANITE_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_ingot"), NANITE_INGOT);
    }
}
