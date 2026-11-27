package flingus.scythemare.index;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModLootEvents {
    public static void init() {
        LootTableEvents.MODIFY_DROPS.register((entry, context, drops) -> {
            if (entry.is(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_UNIQUE)) {
                drops.add(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE.getDefaultInstance());
            }
        });

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && BuiltInLootTables.BASTION_OTHER.equals(key)) {
                var enchantmentRegistry = registries.lookupOrThrow(Registries.ENCHANTMENT);
                var tagEntries = enchantmentRegistry.getOrThrow(ModTags.SCYTHE_ENCHANTMENTS);

                EnchantRandomlyFunction.Builder enchantBuilder = EnchantRandomlyFunction.randomApplicableEnchantment(registries);

                enchantBuilder.withOneOf(tagEntries);

                LootPool.Builder poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BOOK).apply(enchantBuilder));

                tableBuilder.withPool(poolBuilder);
            }
        });
    }
}
