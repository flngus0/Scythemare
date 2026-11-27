package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.item.ScytheItem;
import moriyashiine.strawberrylib.api.module.SLibRegistries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ModItems {
    public static final Item WOODEN_SCYTHE = scythe("wooden_scythe", ToolMaterial.WOOD, 6.5F, -3.3F, 15, false);

    public static final Item STONE_SCYTHE = scythe("stone_scythe", ToolMaterial.STONE, 6.5F, -3.3F, 5, false);
    public static final Item COPPER_SCYTHE = scythe("copper_scythe", ToolMaterial.COPPER, 6.5F, -3.3F, 13, false);

    public static final Item GOLDEN_SCYTHE = scythe("golden_scythe", ToolMaterial.GOLD, 6.5F, -3.3F, 22, false);

    public static final Item IRON_SCYTHE = scythe("iron_scythe", ToolMaterial.IRON, 7.5F, -3.3F, 14, false);

    public static final Item DIAMOND_SCYTHE = scythe("diamond_scythe", ToolMaterial.DIAMOND, 8.5F, -3.3F, 10, false);

    public static final Item NETHERITE_SCYTHE = scythe("netherite_scythe", ToolMaterial.NETHERITE, 9.5F, -3.3F, 15, true);
    public static final Item SCYTHE_SKIN_SMITHING_TEMPLATE = SLibRegistries.registerItem("scythe_skin_smithing_template", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addBefore(Items.WOODEN_SPEAR, WOODEN_SCYTHE, STONE_SCYTHE, COPPER_SCYTHE, IRON_SCYTHE, GOLDEN_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, SCYTHE_SKIN_SMITHING_TEMPLATE);
        });
    }

    private static Item scythe(String name, ToolMaterial material, float attackDamage, float attackSpeed, int enchantability, boolean isFireproof) {
        Item.Properties settings = new Item.Properties()
                .stacksTo(1)
                .enchantable(enchantability)
                .attributes(ItemAttributeModifiers.builder()
                        .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Scythemare.id("scythe_reach"), 1.5D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(Scythemare.id("scythe_block_reach"), 1.5D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                        .build())
                .durability(material.durability());

        if (isFireproof) {
            settings.fireResistant();
        }

        return SLibRegistries.registerItem(name, ScytheItem::new, settings);
    }
}