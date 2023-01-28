package com.oasis.ic.create_encases.util;

import com.oasis.ic.create_encases.CreateEncases;
import com.oasis.ic.create_encases.content.contraptions.relays.encased.EncasesEncasedCogwheelBlock;
import com.oasis.ic.create_encases.content.contraptions.relays.encased.EncasesEncasedShaftBlock;
import com.oasis.ic.create_encases.mixin.AllSpriteShiftsInvoker;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;

import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;

public class CreateBlockBuilders {
    /*
     * Entries
     */

    // Encased Shaft
    public static BlockEntry<EncasesEncasedShaftBlock> createEncasedShaft(
            String casing, boolean axe, boolean pickaxe,
            NonNullFunction<Settings, EncasesEncasedShaftBlock> function,
            Rarity rarity
    ) {
        return CreateEncases.registrate()
                .block(casing + "_encased_shaft", function)
                .initialProperties(SharedProperties::stone)
                .transform(createEncasedShaftBase(
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing")
                        , axe, pickaxe, rarity
                ))
                .register();

                /*
                !layered
                // Unlayered
                ? CreatorsDecors.registrate()
                .block(casing + "_encased_shaft", function)
                .initialProperties(SharedProperties::stone)
                .transform(createEncasedShaftBase(
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing")
                        , axe, pickaxe
                ))
                .register()

                // Layered
                : CreatorsDecors.registrate()
                .block(casing + "_encased_shaft", function)
                .initialProperties(SharedProperties::stone)
                .transform(createEncasedLayeredShaftBase(
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side")
                        , axe, pickaxe
                ))
                .register();

                 */
    }

    // Encased Cogwheel
    public static BlockEntry<EncasesEncasedCogwheelBlock> createEncasedCogwheel(
            String casing, boolean large,
            boolean axe, boolean pickaxe,
            NonNullFunction<Settings, EncasesEncasedCogwheelBlock> function,
            Rarity rarity
    ) {
        return ! large
                ? CreateEncases.registrate()
                // Small
                .block(casing + "_encased_cogwheel", function)
                .transform(b -> createEncasedCogwheelBase(b,
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"), AllBlocks.COGWHEEL::get,
                        axe, pickaxe, rarity
                ))
                .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                        AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                        Couple.create(AllSpriteShiftsInvoker.invokeVertical(casing + "_encased_cogwheel_side"),
                                AllSpriteShiftsInvoker.invokeHorizontal(casing + "_encased_cogwheel_side"))
                )))
                .register()

                // Large
                        : CreateEncases.registrate()
                .block(casing + "_encased_large_cogwheel", function)
                .transform(b -> createEncasedCogwheelBase(b,
                        () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"), AllBlocks.LARGE_COGWHEEL::get,
                        axe, pickaxe, rarity
                ))
                .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                        AllSpriteShiftsInvoker.invokeOmni(casing + "_casing")
                )))
                .register();


                /*
                !layered
                ? !large
                    // Unlayered
                    ? CreatorsDecors.registrate()
                        // Small
                        .block(casing + "_encased_cogwheel", function)
                        .transform(b -> createEncasedCogwheelBase(b,
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"), AllBlocks.COGWHEEL::get,
                                axe, pickaxe
                        ))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_side"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_side")))))
                        .register()

                        // Large
                        : CreatorsDecors.registrate()
                        .block(casing + "_encased_large_cogwheel", function)
                        .transform(b -> createEncasedCogwheelBase(b,
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"), AllBlocks.LARGE_COGWHEEL::get,
                                axe, pickaxe
                        ))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"))))
                        .register()

                    // Layered
                    : !large ? CreatorsDecors.registrate()
                        // Small
                        .block(casing + "_encased_cogwheel", function)
                        .transform(b -> createEncasedLayeredCogwheelBase(b,
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"),
                                () -> CDAllSpriteShifts.omni(casing + "_casing_side_alt"),
                                AllBlocks.COGWHEEL::get,
                                axe, pickaxe))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                                AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_top"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_top"))
                        )))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                                AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_side"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_side"))
                        )))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                                CDAllSpriteShifts.omni(casing + "_casing_side_alt"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_side_alt"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_side_alt"))
                        )))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                                AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_layered"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_layered"))
                        )))
                        .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                                CDAllSpriteShifts.omni(casing + "_casing_side_alt"),
                                Couple.create(CDAllSpriteShifts.vertical(casing + "_encased_cogwheel_layered_alt"),
                                        CDAllSpriteShifts.horizontal(casing + "_encased_cogwheel_layered_alt"))
                        )))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"))))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"))))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(CDAllSpriteShifts.omni(casing + "_casing_side_alt"))))
                        .register()

                        // Large
                        : CreatorsDecors.registrate()
                        .block(casing + "_encased_large_cogwheel", function)
                        .transform(b -> createEncasedLayeredCogwheelBase(b,
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"),
                                () -> AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"),
                                () -> CDAllSpriteShifts.omni(casing + "_casing_side_alt"),
                                AllBlocks.LARGE_COGWHEEL::get,
                                 axe, pickaxe))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing"))))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShiftsInvoker.invokeOmni(casing + "_casing_side"))))
                        .onRegister(connectedTextures(() -> new EncasedCTBehaviour(CDAllSpriteShifts.omni(casing + "_casing_side_alt"))))
                        .register();

                 */
    }


    /*
     * Builders
     */

    // Encased Shaft
    public static <B extends EncasesEncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> createEncasedShaftBase(
            Supplier<CTSpriteShiftEntry> casingShift,
            boolean axe, boolean pickaxe, Rarity rarity
    ) {
        return b -> createEncasedBlock(b, AllBlocks.SHAFT::get, axe, pickaxe)
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.get(EncasedShaftBlock.AXIS))))
                .item()
                .properties(i -> i.rarity(rarity))
                .build();
    }

    /*
    public static <B extends CDEncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> createEncasedLayeredShaftBase(
            Supplier<CTSpriteShiftEntry> casingShift, Supplier<CTSpriteShiftEntry> casingShiftSide,
            boolean axe, boolean pickaxe
    ) {
        return b -> createEncasedBlock(b, AllBlocks.SHAFT::get, axe, pickaxe)
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(casingShiftSide.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.get(EncasedShaftBlock.AXIS))))
                .onRegister(casingConnectivity((block, cc) -> cc.make(block, casingShiftSide.get(),
                        (s, f) -> f.getAxis() != s.get(EncasedShaftBlock.AXIS))))
                .item()
                .build();
    }

     */

    // Encased Cogwheel
    private static <B extends EncasesEncasedCogwheelBlock, P> BlockBuilder<B, P> createEncasedCogwheelBase(
            BlockBuilder<B, P> b, Supplier<CTSpriteShiftEntry> casingShift,
            Supplier<ItemConvertible> drop,
            boolean axe, boolean pickaxe, Rarity rarity
    ) {
        return createEncasedBlock(b, drop, axe, pickaxe)
                .addLayer(() -> RenderLayer::getCutoutMipped)
                .onRegister(casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.get(EncasedCogwheelBlock.AXIS)
                                && !s.get(f.getDirection() == Direction.AxisDirection.POSITIVE
                                ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))))
                .item()
                .properties(i -> i.rarity(rarity))
                .build();
    }

    /*
    private static <B extends CDEncasedCogwheelBlock, P> BlockBuilder<B, P> createEncasedLayeredCogwheelBase(
            BlockBuilder<B, P> b,
            Supplier<CTSpriteShiftEntry> casingShift, Supplier<CTSpriteShiftEntry> casingShiftSide,
            Supplier<CTSpriteShiftEntry> casingShiftSideAlt,
            Supplier<ItemConvertible> drop,
            boolean axe, boolean pickaxe
    ) {
        return createEncasedBlock(b, drop, axe, pickaxe)
                .addLayer(() -> RenderLayer::getCutoutMipped)
                .onRegister(casingConnectivity(
                        (block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() == s.get(EncasedCogwheelBlock.AXIS)
                                && !s.get(f.getDirection() == Direction.AxisDirection.POSITIVE
                                ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))
                ))
                .onRegister(casingConnectivity(
                        (block, cc) -> cc.make(block, casingShiftSide.get(),
                        (s, f) -> f.getAxis() == s.get(EncasedCogwheelBlock.AXIS)
                                && !s.get(f.getDirection() == Direction.AxisDirection.POSITIVE
                                ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))
                ))
                .onRegister(casingConnectivity(
                        (block, cc) -> cc.make(block, casingShiftSideAlt.get(),
                        (s, f) -> f.getAxis() == s.get(EncasedCogwheelBlock.AXIS)
                                && !s.get(f.getDirection() == Direction.AxisDirection.POSITIVE
                                ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT))
                ))
                .item()
                .build();
    }

     */

    // Encased Block
    public static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> createEncasedBlock(
            BlockBuilder<B, P> b, Supplier<ItemConvertible> drop,
            boolean axe, boolean pickaxe
    ) {
        return b.properties(Settings::nonOpaque)
                .transform(BlockStressDefaults.setNoImpact())
                .transform(axe && pickaxe
                        ? TagGen.axeOrPickaxe()
                        : !axe && !pickaxe ? null
                        : axe ? TagGen.axeOnly()
                        : TagGen.pickaxeOnly())
                .loot((p, lb) -> p.addDrop(lb, drop.get()));
    }
}
