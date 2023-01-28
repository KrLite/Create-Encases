package com.oasis.ic.create_encases.mixin;

import static com.simibubi.create.content.contraptions.base.RotatedPillarKineticBlock.AXIS;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.oasis.ic.create_encases.EncasesAllBlocks;
import com.oasis.ic.create_encases.content.contraptions.relays.encased.EncasesEncasedCogwheelBlock;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.CogWheelBlock;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.utility.Iterate;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(CogWheelBlock.class)
public class CogWheelBlockMixin {
    @Shadow(remap = false)
    boolean isLarge;

    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (player.isSneaking() || !player.canModifyBlocks())
            cir.setReturnValue(ActionResult.PASS);

        ItemStack heldItem = player.getStackInHand(hand);

        //Cogwheels
        EncasesEncasedCogwheelBlock[] encasesEncasedCogwheelBlocks = isLarge
                ? new EncasesEncasedCogwheelBlock[] {
                        //Large
                        EncasesAllBlocks.ZINC_ENCASED_LARGE_COGWHEEL.get(),
                        EncasesAllBlocks.SHADOW_STEEL_ENCASED_LARGE_COGWHEEL.get(),
                        EncasesAllBlocks.REFINED_RADIANCE_ENCASED_LARGE_COGWHEEL.get(),
                        EncasesAllBlocks.RAILWAY_ENCASED_LARGE_COGWHEEL.get(),
                        EncasesAllBlocks.COPPER_ENCASED_LARGE_COGWHEEL.get()
        }
                : new EncasesEncasedCogwheelBlock[] {
                        //Small
                        EncasesAllBlocks.ZINC_ENCASED_COGWHEEL.get(),
                        EncasesAllBlocks.SHADOW_STEEL_ENCASED_COGWHEEL.get(),
                        EncasesAllBlocks.REFINED_RADIANCE_ENCASED_COGWHEEL.get(),
                        EncasesAllBlocks.RAILWAY_ENCASED_COGWHEEL.get(),
                        EncasesAllBlocks.COPPER_ENCASED_COGWHEEL.get()
        };

        for ( EncasesEncasedCogwheelBlock encasesEncasedCogwheelBlock : encasesEncasedCogwheelBlocks) {
            if ( !encasesEncasedCogwheelBlock.getCasing().isIn(heldItem) )
                continue;

            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);

            BlockState cdEncasedState = encasesEncasedCogwheelBlock.getDefaultState()
                    .with(AXIS, state.get(AXIS));

            for ( Direction d : Iterate.directionsInAxis(state.get(AXIS)) ) {
                BlockState adjacentState = world.getBlockState(pos.offset(d));
                if ( !(adjacentState.getBlock() instanceof IRotate def) )
                    continue;
                if ( !def.hasShaftTowards(world, pos.offset(d), adjacentState, d.getOpposite()) )
                    continue;
                cdEncasedState =
                        cdEncasedState.cycle(d.getDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                : EncasedCogwheelBlock.BOTTOM_SHAFT);
            }

            KineticTileEntity.switchToBlockState(world, pos, cdEncasedState);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
