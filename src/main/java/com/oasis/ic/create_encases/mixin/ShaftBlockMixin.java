package com.oasis.ic.create_encases.mixin;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oasis.ic.create_encases.EncasesAllBlocks;
import com.oasis.ic.create_encases.content.contraptions.relays.encased.EncasesEncasedShaftBlock;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ShaftBlock.class)
public class ShaftBlockMixin {
	@Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
	private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
		if (player.isSneaking() || !player.canModifyBlocks())
			cir.setReturnValue(ActionResult.PASS);

		ItemStack heldItem = player.getStackInHand(hand);

		//Shafts
		List<EncasesEncasedShaftBlock> encasesEncasedShaftBlocks = List.of(
				EncasesAllBlocks.ZINC_ENCASED_SHAFT.get(),
				EncasesAllBlocks.SHADOW_STEEL_ENCASED_SHAFT.get(),
				EncasesAllBlocks.REFINED_RADIANCE_ENCASED_SHAFT.get(),
				EncasesAllBlocks.RAILWAY_ENCASED_SHAFT.get(),
				EncasesAllBlocks.COPPER_ENCASED_SHAFT.get()
		);

		for ( EncasesEncasedShaftBlock encasesEncasedShaftBlock : encasesEncasedShaftBlocks) {
			if ( !encasesEncasedShaftBlock.getCasing().isIn(heldItem) ) {
				continue;
			}

			if ( world.isClient ) {
				cir.setReturnValue(ActionResult.SUCCESS);
			}

			KineticTileEntity.switchToBlockState(world, pos, encasesEncasedShaftBlock.getDefaultState()
					.with(AXIS, state.get(AXIS)));
			cir.setReturnValue(ActionResult.SUCCESS);
		}
	}
}
