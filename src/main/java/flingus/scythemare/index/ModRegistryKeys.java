package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModRegistryKeys {
    RegistryKey<DamageType> SLICE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Scythemare.id("slice"));

    static void init(){}
}
