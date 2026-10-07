package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent SCYTHE_SWEEP = registerSound("item.scythe.sweep");
    public static final SoundEvent SOUL_SPIRIT_DISAPPEAR = registerSound("entity.soul_spirit.disappear");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Scythemare.id(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void init() {
        Scythemare.LOGGER.info("Registering " + Scythemare.MOD_ID + " Sounds");
    }
}
