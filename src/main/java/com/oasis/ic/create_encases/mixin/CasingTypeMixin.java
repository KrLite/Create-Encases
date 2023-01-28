package com.oasis.ic.create_encases.mixin;

import static com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity.CasingType;

import java.util.Arrays;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CasingType.class)
@Unique
public class CasingTypeMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private static CasingType[] $VALUES;

    private static final CasingType SHADOW_STEEL = casingTypeExpansion$addVariant("SHADOW_STEEL");
    private static final CasingType REFINED_RADIANCE = casingTypeExpansion$addVariant("REFINED_RADIANCE");
    private static final CasingType TRAIN = casingTypeExpansion$addVariant("RAILWAY");
    private static final CasingType COPPER = casingTypeExpansion$addVariant("COPPER");
    private static final CasingType ZINC = casingTypeExpansion$addVariant("ZINC");

    @Invoker("<init>")
    public static CasingType casingTypeExpansion$invokeInit(String internalName, int internalId) {
        throw new AssertionError();
    }

    private static CasingType casingTypeExpansion$addVariant(String name) {
        assert $VALUES != null;

        CasingType casingType = casingTypeExpansion$invokeInit(name, $VALUES[$VALUES.length - 1].ordinal() + 1);
        $VALUES = Stream.concat(Arrays.stream($VALUES), Stream.of(casingType)).toArray(CasingType[]::new);

        return casingType;
    }
}
