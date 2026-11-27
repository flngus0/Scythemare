package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public interface ModTags {
    TagKey<Block> HARVEST_TOUCH_REPLACEABLE = TagKey.create(Registries.BLOCK, Scythemare.id("harvest_touch_replaceable"));
    TagKey<Item> SCYTHES = TagKey.create(Registries.ITEM, Scythemare.id("scythes"));
    TagKey<Item> SWEEP_ATTACKABLE = TagKey.create(Registries.ITEM, Scythemare.id("sweep_attackable"));

    TagKey<EntityType<?>> SOUL_REAP_IGNORE = TagKey.create(Registries.ENTITY_TYPE, Scythemare.id("soul_reap_ignore"));
    TagKey<Enchantment> SCYTHE_ENCHANTMENTS = TagKey.create(Registries.ENCHANTMENT, Scythemare.id("scythe_enchantments"));
}
