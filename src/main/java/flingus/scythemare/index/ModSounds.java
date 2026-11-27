package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent SCYTHE_SWEEP = registerSound("item.scythe.sweep");
    public static final SoundEvent SOUL_SPIRIT_DISAPPEAR = registerSound("entity.soul_spirit.disappear");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Scythemare.id(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void init() {
        Scythemare.LOGGER.info("Registering " + Scythemare.MOD_ID + " Sounds");
    }
}
