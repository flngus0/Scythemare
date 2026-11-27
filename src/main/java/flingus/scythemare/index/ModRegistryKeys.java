package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public interface ModRegistryKeys {
    ResourceKey<DamageType> SLICE = ResourceKey.create(Registries.DAMAGE_TYPE, Scythemare.id("slice"));

    static void init(){}
}
