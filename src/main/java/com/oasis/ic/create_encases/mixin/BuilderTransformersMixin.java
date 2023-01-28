package com.oasis.ic.create_encases.mixin;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;

@Mixin(BuilderTransformers.class)
public abstract class BuilderTransformersMixin {
    // casing()
    @Inject(method = "lambda$casing$50", at = @At("RETURN"), remap = false)
    private static <B extends CasingBlock> void casingLambda50(
            Supplier<CTSpriteShiftEntry> ct, BlockBuilder<B, CreateRegistrate> b,
            CallbackInfoReturnable<BlockBuilder<B, CreateRegistrate>> cir
    ) {
        if ( b.getName().equals("shadow_steel_casing") ) {
            b
                    .properties(
                            settings -> settings
                                    .sounds(BlockSoundGroup.DEEPSLATE)
                                    .strength(50.0F, 1200.0F)
                    )
                    .item()
                    .properties(i -> i.rarity(Rarity.UNCOMMON))
                    .tag(AllTags.AllItemTags.CASING.tag)
                    .build();
        }

        if ( b.getName().equals("refined_radiance_casing") ) {
            b
                    .properties(
                            settings -> settings
                            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                            .luminance($ -> 12)
                    )
                    .item()
                    .properties(i -> i.rarity(Rarity.UNCOMMON))
                    .tag(AllTags.AllItemTags.CASING.tag)
                    .build();
        }
    }

    /*
    // layeredCasing()
    @Inject(method = "lambda$layeredCasing$54", at = @At("RETURN"))
    private static <B extends CasingBlock> void layeredCasingLambda54(
        Supplier<CTSpriteShiftEntry> ct, Supplier<CTSpriteShiftEntry> ct2, BlockBuilder<B, CreateRegistrate> b,
        CallbackInfoReturnable<BlockBuilder<B, CreateRegistrate>> cir
    ) {
        if ( b.getName().equals("railway_casing") ) {
        }
    }

     */
}
