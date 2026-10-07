package flingus.scythemare;

import flingus.scythemare.index.*;
import moriyashiine.strawberrylib.api.SLib;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scythemare implements ModInitializer {
	public static final String MOD_ID = "scythemare";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        SLib.init(MOD_ID);
		LOGGER.info("scythemare....");
        ModEntities.init();
        ModItems.init();
        ModSounds.init();

        ModEnchantments.init();
        ModRegistryKeys.init();
        ModLootEvents.init();
        ModTrades.init();

        ModDataComponentTypes.init();
        ModEnchantmentEffects.init();
        ModCriteria.init();
        ScytheSkins.init();
    }

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
