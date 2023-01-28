package com.oasis.ic.create_encases.mixin;

import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AllSpriteShifts.class)
public interface AllSpriteShiftsInvoker {
    @Invoker(value = "omni", remap = false)
    static CTSpriteShiftEntry invokeOmni(String name) {
        throw new AssertionError();
    }

    @Invoker(value = "horizontal", remap = false)
    static CTSpriteShiftEntry invokeHorizontal(String name) {
        throw new AssertionError();
    }

    @Invoker(value = "vertical", remap = false)
    static CTSpriteShiftEntry invokeVertical(String name) {
        throw new AssertionError();
    }

    @Invoker(value = "get", remap = false)
    static SpriteShiftEntry invokeGet(String originalLocation, String targetLocation) {
        throw new AssertionError();
    }
}
