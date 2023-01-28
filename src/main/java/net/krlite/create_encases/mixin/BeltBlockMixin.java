package net.krlite.create_encases.mixin;

import static com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity.CasingType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.krlite.create_encases.EncasesAllBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTileEntities;
import com.simibubi.create.content.contraptions.relays.belt.BeltBlock;
import com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity;
import com.simibubi.create.foundation.block.ITE;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(BeltBlock.class)
public class BeltBlockMixin implements ITE<BeltTileEntity> {
    @Inject(method = "onUse", at = @At("TAIL"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack heldItem = player.getStackInHand(hand);

        // Create Casings
        if ( AllBlocks.SHADOW_STEEL_CASING.isIn(heldItem) ) {
            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);
            withTileEntityDo(world, pos, te -> te.setCasingType(CasingType.valueOf("SHADOW_STEEL")));
            cir.setReturnValue(ActionResult.SUCCESS);
        }

        if ( AllBlocks.REFINED_RADIANCE_CASING.isIn(heldItem) ) {
            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);
            withTileEntityDo(world, pos, te -> te.setCasingType(CasingType.valueOf("REFINED_RADIANCE")));
            cir.setReturnValue(ActionResult.SUCCESS);
        }

        if ( AllBlocks.RAILWAY_CASING.isIn(heldItem) ) {
            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);
            withTileEntityDo(world, pos, te -> te.setCasingType(CasingType.valueOf("RAILWAY")));
            cir.setReturnValue(ActionResult.SUCCESS);
        }

        if ( AllBlocks.COPPER_CASING.isIn(heldItem) ) {
            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);
            withTileEntityDo(world, pos, te -> te.setCasingType(CasingType.valueOf("COPPER")));
            cir.setReturnValue(ActionResult.SUCCESS);
        }

        // CD Casings
        if ( EncasesAllBlocks.ZINC_CASING.isIn(heldItem) ) {
            if ( world.isClient )
                cir.setReturnValue(ActionResult.SUCCESS);
            withTileEntityDo(world, pos, te -> te.setCasingType(CasingType.valueOf("ZINC")));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }

    @Override
    public Class<BeltTileEntity> getTileEntityClass() {
        return BeltTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends BeltTileEntity> getTileEntityType() {
        return AllTileEntities.BELT.get();
    }
}
