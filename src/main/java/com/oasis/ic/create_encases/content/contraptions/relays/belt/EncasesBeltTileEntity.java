package com.oasis.ic.create_encases.content.contraptions.relays.belt;

import com.simibubi.create.content.contraptions.relays.belt.BeltTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class EncasesBeltTileEntity extends BeltTileEntity {
    public EncasesBeltTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public enum CasingTypeExpanded {
        SHADOW_STEEL, REFINED_RADIANCE, RAILWAY, COPPER, ZINC;
    }
}
