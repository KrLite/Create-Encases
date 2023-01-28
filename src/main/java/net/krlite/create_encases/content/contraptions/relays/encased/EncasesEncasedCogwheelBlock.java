package net.krlite.create_encases.content.contraptions.relays.encased;

import net.krlite.create_encases.EncasesAllBlocks;
import net.krlite.create_encases.EncasesAllTileEntities;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogwheelBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.block.entity.BlockEntityType;

public class EncasesEncasedCogwheelBlock extends EncasedCogwheelBlock {
    public static EncasesEncasedCogwheelBlock shadowSteel(boolean large, Settings settings) {
        return new EncasesEncasedCogwheelBlock(large, settings, AllBlocks.SHADOW_STEEL_CASING);
    }

    public static EncasesEncasedCogwheelBlock refinedRadiance(boolean large, Settings settings) {
        return new EncasesEncasedCogwheelBlock(large, settings, AllBlocks.REFINED_RADIANCE_CASING);
    }

    public static EncasesEncasedCogwheelBlock railway(boolean large, Settings settings) {
        return new EncasesEncasedCogwheelBlock(large, settings, AllBlocks.RAILWAY_CASING);
    }

    public static EncasesEncasedCogwheelBlock copper(boolean large, Settings settings) {
        return new EncasesEncasedCogwheelBlock(large, settings, AllBlocks.COPPER_CASING);
    }

    public static EncasesEncasedCogwheelBlock zinc(boolean large, Settings settings) {
        return new EncasesEncasedCogwheelBlock(large, settings, EncasesAllBlocks.ZINC_CASING);
    }



    protected EncasesEncasedCogwheelBlock(boolean large, Settings settings, BlockEntry<CasingBlock> casing) {
        super(large, settings, casing);
    }

    @Override
    public BlockEntityType<? extends SimpleKineticTileEntity> getTileEntityType() {
        return this.isLargeCog()
                ? EncasesAllTileEntities.CD_ENCASED_LARGE_COGWHEEl.get()
                : EncasesAllTileEntities.CD_ENCASED_COGWHEEl.get();
    }
}
