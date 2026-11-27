package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public interface ModEnchantments {
    ResourceKey<Enchantment> HARVEST_TOUCH = ResourceKey.create(Registries.ENCHANTMENT, Scythemare.id("harvest_touch"));
    ResourceKey<Enchantment> SOUL_REAP = ResourceKey.create(Registries.ENCHANTMENT, Scythemare.id("soul_reap"));
    ResourceKey<Enchantment> REINFORCING = ResourceKey.create(Registries.ENCHANTMENT, Scythemare.id("reinforcing"));

    static void init() {}
}
