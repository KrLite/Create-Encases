package net.krlite.create_encases;

import net.krlite.create_encases.mixin.AllSpriteShiftsInvoker;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import com.simibubi.create.foundation.block.render.SpriteShifter;

public class EncasesAllSpriteShifts {
    // Original Casings
    public static final SpriteShiftEntry
            ANDESITE_BELT_CASING = getOriginalCasing("andesite"),
            BRASS_BELT_CASING = getOriginalCasing("brass");

    // Create Casings
    public static final SpriteShiftEntry
            SHADOW_STEEL_BELT_CASING = getOriginalCasing("shadow_steel"),
            REFINED_RADIANCE_BELT_CASING = getOriginalCasing("refined_radiance"),
            RAILWAY_BELT_CASING = getOriginalCasing("railway"),
            COPPER_BELT_CASING = getOriginalCasing("copper");

    // Encases Casings
    public static final SpriteShiftEntry
            ZINC_BELT_CASING = getCustomCasing("zinc");



    public static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    public static CTSpriteShiftEntry horizontal(String name) {
        return getCT(AllCTTypes.HORIZONTAL, name);
    }

    public static CTSpriteShiftEntry vertical(String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }



    public static SpriteShiftEntry get(String originalLocation, String targetLocation) {
        return SpriteShifter.get(CreateEncases.identifier(originalLocation), CreateEncases.identifier(targetLocation));
    }

    public static SpriteShiftEntry getMixed(String originalLocation, String targetLocation) {
        return SpriteShifter.get(Create.asResource(originalLocation), CreateEncases.identifier(targetLocation));
    }



    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, CreateEncases.identifier("block",  blockTextureName), CreateEncases.identifier("block",  connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    private static SpriteShiftEntry getOriginalCasing(String casing) {
        return AllSpriteShiftsInvoker.invokeGet("block/brass_casing_belt", "block/" + casing + "_casing_belt");
    }

    private static SpriteShiftEntry getCustomCasing(String casing) {
        return EncasesAllSpriteShifts.getMixed("block/brass_casing_belt", "block/" + casing + "_casing_belt");
    }



    public static void register() {

    }
}
