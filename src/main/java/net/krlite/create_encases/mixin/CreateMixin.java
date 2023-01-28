package net.krlite.create_encases.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.krlite.create_encases.CreateEncases;
import net.krlite.create_encases.EncasesAllBlocks;
import net.krlite.create_encases.EncasesAllSpriteShifts;
import net.krlite.create_encases.EncasesAllTileEntities;
import com.simibubi.create.Create;

@Mixin(Create.class)
public class CreateMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void init(CallbackInfo ci) {
        EncasesAllBlocks.register();
        EncasesAllTileEntities.register();
        EncasesAllSpriteShifts.register();

        CreateEncases.REGISTRATE.register();

        CreateEncases.LOGGER.info("Splendid Mechanic Aesthetics! ⚙️");
    }
}
