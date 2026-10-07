package flingus.scythemare.index;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKeys;

public class ModLootEvents {
    public static void init() {
        LootTableEvents.MODIFY_DROPS.register((entry, context, drops) -> {
            if (entry.matchesKey(LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST)) {
                drops.add(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE.getDefaultStack());
            }
        });

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && LootTables.BASTION_OTHER_CHEST.equals(key)) {
                var enchantmentRegistry = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
                var tagEntries = enchantmentRegistry.getOrThrow(ModTags.SCYTHE_ENCHANTMENTS);

                EnchantRandomlyLootFunction.Builder enchantBuilder = EnchantRandomlyLootFunction.builder(registries);

                enchantBuilder.options(tagEntries);

                LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(Items.BOOK).apply(enchantBuilder));

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
