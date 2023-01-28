package net.krlite.create_encases.mixin;

import java.util.Random;
import java.util.function.Supplier;

import net.krlite.create_encases.content.contraptions.relays.belt.EncasesBeltTileEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.krlite.create_encases.EncasesAllSpriteShifts;

import com.simibubi.create.content.contraptions.relays.belt.BeltModel;
import com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity;
import com.simibubi.create.foundation.block.render.SpriteShiftEntry;

import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachedBlockView;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

@Mixin(BeltModel.class)
public class BeltModelMixin extends ForwardingBakedModel {
    @Shadow(remap = false)
    @Final
    @Mutable
    private static SpriteShiftEntry SPRITE_SHIFT;

    @Inject(method = "emitBlockQuads", at = @At("HEAD"), cancellable = true)
    public void updateBeltCasingType(
            BlockRenderView blockView, BlockState state, BlockPos pos,
            Supplier<Random> randomSupplier, RenderContext context, CallbackInfo ci
    ) {
        if ( blockView instanceof RenderAttachedBlockView attachmentView ) {
            if ( attachmentView.getBlockEntityRenderAttachment(pos) instanceof BeltTileEntity.CasingType type && !type.equals(BeltTileEntity.CasingType.NONE) ) {
                SPRITE_SHIFT =
                        // Create Casings
                        type.name().equals(EncasesBeltTileEntity.CasingTypeExpanded.SHADOW_STEEL.name())
                                ? EncasesAllSpriteShifts.SHADOW_STEEL_BELT_CASING
                                : type.name().equals(EncasesBeltTileEntity.CasingTypeExpanded.REFINED_RADIANCE.name())
                                ? EncasesAllSpriteShifts.REFINED_RADIANCE_BELT_CASING
                                : type.name().equals(EncasesBeltTileEntity.CasingTypeExpanded.RAILWAY.name())
                                ? EncasesAllSpriteShifts.RAILWAY_BELT_CASING
                                : type.name().equals(EncasesBeltTileEntity.CasingTypeExpanded.COPPER.name())
                                ? EncasesAllSpriteShifts.COPPER_BELT_CASING
                                // Encases Casings
                                : type.name().equals(EncasesBeltTileEntity.CasingTypeExpanded.ZINC.name())
                                ? EncasesAllSpriteShifts.ZINC_BELT_CASING
                                // Original Casings
                                : type.equals(BeltTileEntity.CasingType.ANDESITE)
                                ? EncasesAllSpriteShifts.ANDESITE_BELT_CASING
                                : EncasesAllSpriteShifts.BRASS_BELT_CASING;
            }
        }

        SpriteFinder spriteFinder = SpriteFinder.get(MinecraftClient.getInstance().getBakedModelManager().getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE));
        context.pushTransform(quad -> {
            Sprite sprite = spriteFinder.find(quad, 0);
            if (sprite == SPRITE_SHIFT.getOriginal()) {
                for (int vertex = 0; vertex < 4; vertex++) {
                    float u = quad.spriteU(vertex, 0);
                    float v = quad.spriteV(vertex, 0);
                    quad.sprite(vertex, 0,
                            SPRITE_SHIFT.getTargetU(u),
                            SPRITE_SHIFT.getTargetV(v)
                    );
                }
            }
            sprite.close();
            return true;
        });

        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
        context.popTransform();

        ci.cancel();
    }
}
