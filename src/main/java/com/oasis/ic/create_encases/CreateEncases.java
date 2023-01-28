package com.oasis.ic.create_encases;

import com.oasis.ic.create_encases.util.EncasesItemGroups;
import com.simibubi.create.foundation.data.CreateRegistrate;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simibubi.create.Create;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateEncases implements ModInitializer {
	public static final String ID = "create_encases";
	public static final String NAME = "Create: Encases";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);

	public static CreateRegistrate registrate() {
		return REGISTRATE.creativeModeTab(() -> EncasesItemGroups.CREATORS_DECORS);
	}

	public static Identifier identifier(String... paths) {
		return new Identifier(Arrays.stream(paths).filter(Objects::nonNull).filter(p -> !p.isEmpty()).collect(Collectors.joining("/")));
	}

	@Override
	public void onInitialize() {
		LOGGER.info("[{}] is loading alongside Create [{}]! ⚙️", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), NAME);
	}
}
