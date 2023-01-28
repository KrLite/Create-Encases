package net.krlite.create_encases.util;

import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class EncasesBlockSettings {
    public static Settings SHADOW_STEEL(Settings settings) {
        return settings
                .mapColor(MapColor.BLACK)
                .sounds(BlockSoundGroup.DEEPSLATE)
                .strength(50.0F, 1200.0F);
    }

    public static Settings REFINED_RADIANCE(Settings settings) {
        return settings
                .mapColor(MapColor.WHITE)
                .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                .strength(1.5F)
                .luminance($ -> 12);
    }

    public static Settings RAILWAY(Settings settings) {
        return settings
                .mapColor(MapColor.TERRACOTTA_CYAN)
                .sounds(BlockSoundGroup.NETHERITE)
                .strength(5.0F, 6.0F);
    }

    public static Settings COPPER(Settings settings) {
        return settings
                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                .sounds(BlockSoundGroup.COPPER)
                .strength(3.0F, 6.0F);
    }

    public static Settings ZINC(Settings settings) {
        return settings
                .mapColor(MapColor.LICHEN_GREEN)
                .sounds(BlockSoundGroup.METAL)
                .strength(5.0F, 6.0F);
    }
}
