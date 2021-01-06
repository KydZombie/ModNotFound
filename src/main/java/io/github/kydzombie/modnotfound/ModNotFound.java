package io.github.kydzombie.modnotfound;

import io.github.kydzombie.modnotfound.armor.BaseArmor;
import io.github.kydzombie.modnotfound.armor.NaniteArmorMaterial;
import io.github.kydzombie.modnotfound.tools.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModNotFound implements ModInitializer {

    public static final String MOD_ID = "modnotfound";

    // Items
    public static final Item NANITE_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item NANITE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item NAN_PIECES = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    // Blocks
    public static final Block NAN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES, 4).strength(35.0F,1200.0F).sounds(BlockSoundGroup.ANCIENT_DEBRIS));
    public static final Block NANITE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).requiresTool().breakByTool(FabricToolTags.PICKAXES, 4).strength(55.0F,1200.0F).sounds(BlockSoundGroup.NETHERITE));

    // Armor
    public static final ArmorMaterial NANITE_ARMOR = new NaniteArmorMaterial();

    // Ore Gen
    private static ConfiguredFeature<?, ?> NAN_BLOCK_END = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE), // base block is endstone in the end biomes
                    NAN_BLOCK.getDefaultState(),
                    3)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0, // bottom offset
                    0, // min y level
                    64))) // max y level
            .spreadHorizontally()
            .repeat(10); // number oof veins per chunk

    @Override
    public void onInitialize() {
        // Items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_ingot"), NANITE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_nugget"), NANITE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nan_pieces"), NAN_PIECES);

        // Blocks
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "nan_block"), NAN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nan_block"), new BlockItem(NAN_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "nanite_block"), NANITE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_block"), new BlockItem(NANITE_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

        // Tools
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_pickaxe"), new PickaxeBase(new ToolMaterialNanite()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_axe"), new AxeBase(new ToolMaterialNanite()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_shovel"), new ShovelBase(new ToolMaterialNanite()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_sword"), new SwordBase(new ToolMaterialNanite()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_hoe"), new HoeBase(new ToolMaterialNanite()));

        // Armor
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_helmet"), new BaseArmor(NANITE_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_chestplate"), new BaseArmor(NANITE_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_leggings"), new BaseArmor(NANITE_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nanite_boots"), new BaseArmor(NANITE_ARMOR, EquipmentSlot.FEET));

        // Ore Gen
        RegistryKey<ConfiguredFeature<?, ?>> nanBlockEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("modnotfound", "nan_block_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, nanBlockEnd.getValue(), NAN_BLOCK_END);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, nanBlockEnd);

    }
}
