package io.github.kydzombie.modnotfound.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class AxeBase extends AxeItem {
    public AxeBase(ToolMaterial material) {
        super(material, 6.0F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
