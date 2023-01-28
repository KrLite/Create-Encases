package com.oasis.ic.create_encases.mixin;

import static com.oasis.ic.create_encases.content.contraptions.relays.belt.EncasesBeltTileEntity.CasingType;
import static com.oasis.ic.create_encases.content.contraptions.relays.belt.EncasesBeltTileEntity.CasingTypeExpanded;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.oasis.ic.create_encases.EncasesAllBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity;

import net.minecraft.block.BlockState;

@Mixin(BeltTileEntity.class)
public class BeltTileEntityMixin {
    @Shadow(remap = false)
	public CasingType casing;

    @ModifyArg(
            method = "setCasingType",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getRawIdFromState(Lnet/minecraft/block/BlockState;)I"
            ),
            index = 0
    )
    public BlockState setCasingType(BlockState state) {
        return
                // Create Casings
                casing.name().equals(CasingTypeExpanded.SHADOW_STEEL.name())
                ? AllBlocks.SHADOW_STEEL_CASING.getDefaultState()
                : casing.name().equals(CasingTypeExpanded.REFINED_RADIANCE.name())
                ? AllBlocks.REFINED_RADIANCE_CASING.getDefaultState()
                : casing.name().equals(CasingTypeExpanded.RAILWAY.name())
                ? AllBlocks.RAILWAY_CASING.getDefaultState()
                : casing.name().equals(CasingTypeExpanded.COPPER.name())
                ? AllBlocks.COPPER_CASING.getDefaultState()

                // CD Casings
                : casing.name().equals(CasingTypeExpanded.ZINC.name())
                ? EncasesAllBlocks.ZINC_CASING.getDefaultState()

                // Default Casings
                : casing == BeltTileEntity.CasingType.ANDESITE
                ? AllBlocks.ANDESITE_CASING.getDefaultState()
                : AllBlocks.BRASS_CASING.getDefaultState();
    }
}
