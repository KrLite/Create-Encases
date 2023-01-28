package net.krlite.create_encases.content.contraptions.relays.encased;

import net.krlite.create_encases.EncasesAllBlocks;
import net.krlite.create_encases.EncasesAllTileEntities;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.CasingBlock;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedShaftBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.block.entity.BlockEntityType;

public class EncasesEncasedShaftBlock extends EncasedShaftBlock {
	public static EncasesEncasedShaftBlock shadowSteel(Settings settings) {
		return new EncasesEncasedShaftBlock(settings, AllBlocks.SHADOW_STEEL_CASING);
	}

	public static EncasesEncasedShaftBlock refinedRadiance(Settings settings) {
		return new EncasesEncasedShaftBlock(settings, AllBlocks.REFINED_RADIANCE_CASING);
	}

	public static EncasesEncasedShaftBlock railway(Settings settings) {
		return new EncasesEncasedShaftBlock(settings, AllBlocks.RAILWAY_CASING);
	}

	public static EncasesEncasedShaftBlock copper(Settings settings) {
		return new EncasesEncasedShaftBlock(settings, AllBlocks.COPPER_CASING);
	}

	public static EncasesEncasedShaftBlock zinc(Settings settings) {
		return new EncasesEncasedShaftBlock(settings, EncasesAllBlocks.ZINC_CASING);
	}



	protected EncasesEncasedShaftBlock(Settings settings, BlockEntry<CasingBlock> casing) {
		super(settings, casing);
	}

	@Override
	public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
		return EncasesAllTileEntities.CD_ENCASED_SHAFT.get();
	}
}
