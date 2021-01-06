package io.github.kydzombie.modnotfound.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ShovelBase extends ShovelItem {
    public ShovelBase(ToolMaterial material) {
        super(material, 1.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
