package com.oasis.ic.create_encases.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;

@Mixin(Create.class)
public interface CreateAccessor {
    @Accessor(value = "REGISTRATE", remap = false)
    static CreateRegistrate getRegistrate() {
        throw new AssertionError();
    }
}
