package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModEnchantments {
    RegistryKey<Enchantment> HARVEST_TOUCH = RegistryKey.of(RegistryKeys.ENCHANTMENT, Scythemare.id("harvest_touch"));
    RegistryKey<Enchantment> SOUL_REAP = RegistryKey.of(RegistryKeys.ENCHANTMENT, Scythemare.id("soul_reap"));
    RegistryKey<Enchantment> REINFORCING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Scythemare.id("reinforcing"));

    static void init() {}
}
