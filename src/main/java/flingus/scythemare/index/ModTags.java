package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ModTags {
    TagKey<Block> HARVEST_TOUCH_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, Scythemare.id("harvest_touch_replaceable"));
    TagKey<Item> SCYTHES = TagKey.of(RegistryKeys.ITEM, Scythemare.id("scythes"));
    TagKey<Item> SWEEP_ATTACKABLE = TagKey.of(RegistryKeys.ITEM, Scythemare.id("sweep_attackable"));

    TagKey<EntityType<?>> SOUL_REAP_IGNORE = TagKey.of(RegistryKeys.ENTITY_TYPE, Scythemare.id("soul_reap_ignore"));
    TagKey<Enchantment> SCYTHE_ENCHANTMENTS = TagKey.of(RegistryKeys.ENCHANTMENT, Scythemare.id("scythe_enchantments"));
}
