package net.krlite.create_encases;

import static net.krlite.create_encases.util.CreateBlockBuilders.createEncasedCogwheel;
import static net.krlite.create_encases.util.CreateBlockBuilders.createEncasedShaft;
import static net.krlite.create_encases.util.EncasesBlockBuilders.*;
import static net.krlite.create_encases.util.EncasesBlockSettings.*;

import net.krlite.create_encases.content.contraptions.relays.encased.EncasesEncasedCogwheelBlock;
import net.krlite.create_encases.content.contraptions.relays.encased.EncasesEncasedShaftBlock;
import net.krlite.create_encases.util.EncasesBlockSettings;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.curiosities.deco.MetalLadderBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.util.Rarity;

public class EncasesAllBlocks {
    // Create Ladders
    public static final BlockEntry<MetalLadderBlock>
            SHADOW_STEEL_LADDER = cdLadder(
                    "shadow_steel",
                    settings -> SHADOW_STEEL(settings)
                            .strength(0.8F, 1200.0F),
                    Rarity.UNCOMMON
            ),
            REFINED_RADIANCE_LADDER = cdLadder(
                    "refined_radiance",
                    settings -> REFINED_RADIANCE(settings)
                            .strength(0.8F),
                    Rarity.UNCOMMON
            ),
            RAILWAY_LADDER = cdLadder(
                    "railway",
                    settings -> RAILWAY(settings)
                            .strength(0.8F),
                    Rarity.COMMON
            );

    // Encases Ladders
    public static final BlockEntry<MetalLadderBlock>
            ZINC_LADDER = cdLadder(
                    "zinc",
                    settings -> ZINC(settings)
                            .strength(0.4F),
                    Rarity.COMMON
            );

    // Encases Casings
    public static final BlockEntry<CasingBlock>
            ZINC_CASING = cdCasing(
                    "zinc", false, true,
                    EncasesBlockSettings::ZINC, Rarity.COMMON
            );

    // Create Encased Cogwheels
    public static final BlockEntry<EncasesEncasedCogwheelBlock>
            // Small
            SHADOW_STEEL_ENCASED_COGWHEEL = createEncasedCogwheel(
                    "shadow_steel", false, false, true,
                    settings -> EncasesEncasedCogwheelBlock.shadowSteel(false, SHADOW_STEEL(settings)),
                    Rarity.UNCOMMON
            ),
            REFINED_RADIANCE_ENCASED_COGWHEEL = createEncasedCogwheel(
                    "refined_radiance", false, false, true,
                    settings -> EncasesEncasedCogwheelBlock.refinedRadiance(false, REFINED_RADIANCE(settings)),
                    Rarity.UNCOMMON
            ),
            RAILWAY_ENCASED_COGWHEEL = createEncasedCogwheel(
                    "railway", false, true, true,
                    settings -> EncasesEncasedCogwheelBlock.railway(false, RAILWAY(settings)),
                    Rarity.COMMON
            ),
            COPPER_ENCASED_COGWHEEL = createEncasedCogwheel(
                    "copper", false, true, true,
                    settings -> EncasesEncasedCogwheelBlock.copper(false, COPPER(settings)),
                    Rarity.COMMON
            ),

            // Large
            SHADOW_STEEL_ENCASED_LARGE_COGWHEEL = createEncasedCogwheel(
                    "shadow_steel", true, false, true,
                    settings -> EncasesEncasedCogwheelBlock.shadowSteel(true, SHADOW_STEEL(settings)),
                    Rarity.UNCOMMON
            ),
            REFINED_RADIANCE_ENCASED_LARGE_COGWHEEL = createEncasedCogwheel(
                    "refined_radiance", true, false, true,
                    settings -> EncasesEncasedCogwheelBlock.refinedRadiance(true, REFINED_RADIANCE(settings)),
                    Rarity.UNCOMMON
            ),
            RAILWAY_ENCASED_LARGE_COGWHEEL = createEncasedCogwheel(
                    "railway", true, true, true,
                    settings -> EncasesEncasedCogwheelBlock.railway(true, RAILWAY(settings)),
                    Rarity.COMMON
            ),
            COPPER_ENCASED_LARGE_COGWHEEL = createEncasedCogwheel(
                    "copper", true, true, true,
                    settings -> EncasesEncasedCogwheelBlock.copper(true, COPPER(settings)),
                    Rarity.COMMON
            );

    // Encases Encased Cogwheels
    public static final BlockEntry<EncasesEncasedCogwheelBlock>
            // Small
            ZINC_ENCASED_COGWHEEL = cdEncasedCogwheel(
                    "zinc", false, false, true,
                    settings -> EncasesEncasedCogwheelBlock.zinc(false, ZINC(settings)),
                    Rarity.COMMON
            ),

            // Large
            ZINC_ENCASED_LARGE_COGWHEEL = cdEncasedCogwheel(
                    "zinc", true, false, true,
                    settings -> EncasesEncasedCogwheelBlock.zinc(true, ZINC(settings)),
                    Rarity.COMMON
            );

    // Create Encased Shaft Blocks
    public static final BlockEntry<EncasesEncasedShaftBlock>
            SHADOW_STEEL_ENCASED_SHAFT = createEncasedShaft(
                    "shadow_steel",
                    false, true,
                    settings -> EncasesEncasedShaftBlock.shadowSteel(SHADOW_STEEL(settings)),
                    Rarity.UNCOMMON
            ),
            REFINED_RADIANCE_ENCASED_SHAFT = createEncasedShaft(
                    "refined_radiance",
                    false, true,
                    settings -> EncasesEncasedShaftBlock.refinedRadiance(REFINED_RADIANCE(settings)),
                    Rarity.UNCOMMON
            ),
            RAILWAY_ENCASED_SHAFT = createEncasedShaft(
                    "railway",
                    true, true,
                    settings -> EncasesEncasedShaftBlock.railway(RAILWAY(settings)),
                    Rarity.COMMON
            ),
            COPPER_ENCASED_SHAFT = createEncasedShaft(
                    "copper",
                    true, true,
                    settings -> EncasesEncasedShaftBlock.copper(COPPER(settings)),
                    Rarity.COMMON
            );

    // Encases Encased Shaft Blocks
    public static final BlockEntry<EncasesEncasedShaftBlock>
            ZINC_ENCASED_SHAFT = cdEncasedShaft(
                    "zinc",
                    false, true,
                    settings -> EncasesEncasedShaftBlock.zinc(ZINC(settings)),
                    Rarity.COMMON
            );



    public static void register() {

    }
}
