package flingus.scythemare.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class ScythemareUtils {
    public static boolean hasEnchantment(RegistryKey<Enchantment> enchantment, World world, ItemStack stack) {
        return EnchantmentHelper.getLevel(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(enchantment), stack) > 0;
    }
}
