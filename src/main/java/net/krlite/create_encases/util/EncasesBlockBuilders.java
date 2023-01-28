package net.krlite.create_encases.util;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;

import java.util.function.Supplier;

import net.krlite.create_encases.CreateEncases;
import net.krlite.create_encases.EncasesAllSpriteShifts;
import net.krlite.create_encases.content.contraptions.relays.encased.EncasesEncasedCogwheelBlock;
import net.krlite.create_encases.content.contraptions.relays.encased.EncasesEncasedShaftBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.simibubi.create.content.curiosities.deco.MetalLadderBlock;
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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

public class EncasesBlockBuilders {
    /*
     * Entries
     */

    // Casing
    public static BlockEntry<CasingBlock> cdCasing(
            String name, boolean axe, boolean pickaxe,
            NonNullUnaryOperator<Settings> settings,
            Rarity rarity
    ) {
        String id = name + "_casing";
        return CreateEncases.registrate()
                .block(id, CasingBlock::deprecated)
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(EncasesAllSpriteShifts.omni(id))))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, EncasesAllSpriteShifts.omni(id))))
                .addLayer(() -> RenderLayer::getSolid)
                .initialProperties(SharedProperties::stone)
                .properties(settings)
                .transform(axe && pickaxe
                        ? TagGen.axeOrPickaxe()
                        : !axe && !pickaxe ? null
                        : axe ? TagGen.axeOnly()
                        : TagGen.pickaxeOnly())
                .loot(BlockLootTableGenerator::addDrop)
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .tag(AllTags.AllBlockTags.CASING.tag)
                .item()
                .properties(i -> i.rarity(rarity))
                .tag(AllTags.AllItemTags.CASING.tag)
                .build()
                .register();
    }

    // Ladder
    public static BlockEntry<MetalLadderBlock> cdLadder(
            String name, NonNullUnaryOperator<Settings> settings,
            Rarity rarity
    ) {
        return CreateEncases.registrate()
                .block(name + "_ladder", MetalLadderBlock::new)
                .transform(cdLadderBase(rarity))
                .properties(settings)
                .register();
    }

    // Encased Shaft
    public static BlockEntry<EncasesEncasedShaftBlock> cdEncasedShaft(
            String casing, boolean axe, boolean pickaxe,
            NonNullFunction<Settings, EncasesEncasedShaftBlock> function,
            Rarity rarity
    ) {
        return CreateEncases.registrate()
                .block(casing + "_encased_shaft", function)
                .initialProperties(SharedProperties::stone)
                .transform(cdEncasedShaftBase(
                        () -> EncasesAllSpriteShifts.omni(casing + "_casing")
                        , axe, pickaxe, rarity
                ))
                .register();
    }

    // Encased Cogwheel
    public static BlockEntry<EncasesEncasedCogwheelBlock> cdEncasedCogwheel(
            String casing, boolean large,
            boolean axe, boolean pickaxe,
            NonNullFunction<Settings, EncasesEncasedCogwheelBlock> function,
            Rarity rarity
    ) {
        return !large
                // Small
                ? CreateEncases.registrate()
                .block(casing + "_encased_cogwheel", function)
                .transform(b -> cdEncasedCogwheelBase(b,
                        () -> EncasesAllSpriteShifts.omni(casing + "_casing"), AllBlocks.COGWHEEL::get,
                        axe, pickaxe, rarity
                ))
                .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                        EncasesAllSpriteShifts.omni(casing + "_casing"),
                        Couple.create(EncasesAllSpriteShifts.vertical(casing + "_encased_cogwheel_side"),
                                EncasesAllSpriteShifts.horizontal(casing + "_encased_cogwheel_side"))
                )))
                .register()

                // Large
                : CreateEncases.registrate()
                .block(casing + "_encased_large_cogwheel", function)
                .transform(b -> cdEncasedCogwheelBase(b,
                        () -> EncasesAllSpriteShifts.omni(casing + "_casing"), AllBlocks.LARGE_COGWHEEL::get,
                        axe, pickaxe, rarity
                ))
                .onRegister(connectedTextures(() -> new EncasedCogCTBehaviour(
                        EncasesAllSpriteShifts.omni(casing + "_casing")
                )))
                .register();
    }



    /*
     * Builders
     */

    // Ladder
    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> cdLadderBase(Rarity rarity) {
        return b -> b.initialProperties(() -> Blocks.LADDER)
                .addLayer(() -> RenderLayer::getCutout)
                .transform(TagGen.pickaxeOnly())
                .tag(BlockTags.CLIMBABLE)
                .item()
                .properties(i -> i.rarity(rarity))
                .build();
    }

    // Encased Shaft
    public static <B extends EncasesEncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> cdEncasedShaftBase(
            Supplier<CTSpriteShiftEntry> casingShift,
            boolean axe, boolean pickaxe,
            Rarity rarity
    ) {
        return b -> cdEncasedBlock(b, AllBlocks.SHAFT::get, axe, pickaxe)
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
                        (s, f) -> f.getAxis() != s.get(EncasedShaftBlock.AXIS))))
                .item()
                .properties(i -> i.rarity(rarity))
                .build();
    }

    // Encased Cogwheel
    private static <B extends EncasesEncasedCogwheelBlock, P> BlockBuilder<B, P> cdEncasedCogwheelBase(
            BlockBuilder<B, P> b, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemConvertible> drop,
            boolean axe, boolean pickaxe, Rarity rarity
    ) {
        return cdEncasedBlock(b, drop, axe, pickaxe)
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

    // Encased Block
    public static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> cdEncasedBlock(
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
