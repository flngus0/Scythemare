package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.item.ScytheItem;
import flingus.scythemare.item.ScytheSkinSmithingTemplateItem;
import moriyashiine.strawberrylib.api.module.SLibRegistries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item WOODEN_SCYTHE = scythe("wooden_scythe", ToolMaterial.WOOD, 6.5F, -3.3F, 15, false);

    public static final Item STONE_SCYTHE = scythe("stone_scythe", ToolMaterial.STONE, 6.5F, -3.3F, 5, false);
    public static final Item COPPER_SCYTHE = scythe("copper_scythe", ToolMaterial.COPPER, 6.5F, -3.3F, 13, false);

    public static final Item GOLDEN_SCYTHE = scythe("golden_scythe", ToolMaterial.GOLD, 6.5F, -3.3F, 22, false);

    public static final Item IRON_SCYTHE = scythe("iron_scythe", ToolMaterial.IRON, 7.5F, -3.3F, 14, false);

    public static final Item DIAMOND_SCYTHE = scythe("diamond_scythe", ToolMaterial.DIAMOND, 8.5F, -3.3F, 10, false);

    public static final Item NETHERITE_SCYTHE = scythe("netherite_scythe", ToolMaterial.NETHERITE, 9.5F, -3.3F, 15, true);
    public static final Item SCYTHE_SKIN_SMITHING_TEMPLATE = SLibRegistries.registerItem("scythe_skin_smithing_template", ScytheSkinSmithingTemplateItem::new, new Item.Settings().rarity(Rarity.UNCOMMON));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addBefore(Items.WOODEN_SPEAR, WOODEN_SCYTHE, STONE_SCYTHE, COPPER_SCYTHE, IRON_SCYTHE, GOLDEN_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, SCYTHE_SKIN_SMITHING_TEMPLATE);
        });
    }

    private static Item scythe(String name, ToolMaterial material, float attackDamage, float attackSpeed, int enchantability, boolean isFireproof) {
        Item.Settings settings = new Item.Settings()
                .maxCount(1)
                .hoe(material, attackDamage, attackSpeed)
                .enchantable(enchantability)
                .attributeModifiers(AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .add(EntityAttributes.ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(Scythemare.id("scythe_reach"), 1.5D, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .add(EntityAttributes.BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(Scythemare.id("scythe_block_reach"), 1.5D, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .build())
                .maxDamage(material.durability());

        if (isFireproof) {
            settings.fireproof();
        }

        return SLibRegistries.registerItem(name, ScytheItem::new, settings);
    }
}