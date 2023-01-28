package net.krlite.create_encases;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.elementary.SimpleKineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogInstance;
import com.simibubi.create.content.contraptions.relays.encased.EncasedCogRenderer;
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance;
import com.simibubi.create.content.contraptions.relays.encased.ShaftRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class EncasesAllTileEntities {
	// Encased Cogwheel
	public static final BlockEntityEntry<SimpleKineticTileEntity> CD_ENCASED_COGWHEEl = CreateEncases.registrate()
			.tileEntity("encased_cogwheel", SimpleKineticTileEntity::new)
			.instance(()-> EncasedCogInstance::small, false)
			.validBlocks(

					EncasesAllBlocks.ZINC_ENCASED_COGWHEEL,
					EncasesAllBlocks.SHADOW_STEEL_ENCASED_COGWHEEL,
					EncasesAllBlocks.REFINED_RADIANCE_ENCASED_COGWHEEL,
					EncasesAllBlocks.RAILWAY_ENCASED_COGWHEEL,
					EncasesAllBlocks.COPPER_ENCASED_COGWHEEL

			)
			.renderer(()-> EncasedCogRenderer::small)
			.register();

	// Encased Large Cogwheel
	public static final BlockEntityEntry<SimpleKineticTileEntity> CD_ENCASED_LARGE_COGWHEEl = CreateEncases.registrate()
			.tileEntity("encased_large_cogwheel", SimpleKineticTileEntity::new)
			.instance(()-> EncasedCogInstance::large, false)
			.validBlocks(

					EncasesAllBlocks.ZINC_ENCASED_LARGE_COGWHEEL,
					EncasesAllBlocks.SHADOW_STEEL_ENCASED_LARGE_COGWHEEL,
					EncasesAllBlocks.REFINED_RADIANCE_ENCASED_LARGE_COGWHEEL,
					EncasesAllBlocks.RAILWAY_ENCASED_LARGE_COGWHEEL,
					EncasesAllBlocks.COPPER_ENCASED_LARGE_COGWHEEL

			)
			.renderer(()-> EncasedCogRenderer::large)
			.register();

	// Encased Shaft
	public static final BlockEntityEntry<KineticTileEntity> CD_ENCASED_SHAFT = CreateEncases.registrate()
			.tileEntity("encased_shaft", KineticTileEntity::new)
			.instance(()-> ShaftInstance::new, false)
			.validBlocks(

					EncasesAllBlocks.ZINC_ENCASED_SHAFT,
					EncasesAllBlocks.SHADOW_STEEL_ENCASED_SHAFT,
					EncasesAllBlocks.REFINED_RADIANCE_ENCASED_SHAFT,
					EncasesAllBlocks.RAILWAY_ENCASED_SHAFT,
					EncasesAllBlocks.COPPER_ENCASED_SHAFT

			)
			.renderer(()-> ShaftRenderer::new)
			.register();



	public static void register() {
	}
}
