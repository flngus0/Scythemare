package flingus.scythemare.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class ScythemareUtils {
    public static boolean hasEnchantment(ResourceKey<Enchantment> enchantment, Level world, ItemStack stack) {
        return EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment), stack) > 0;
    }
}
