package flingus.scythemare.index;

import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ModTrades {
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2, factories -> {
            factories.add((world, entity, random) -> {
                if(FabricLoader.getInstance().isModLoaded("enchancement")) {
                    if (!EnchancementUtil.isEnchantmentAllowed(ModEnchantments.HARVEST_TOUCH.getValue())) {
                        return null;
                    }
                }

                ItemStack book = harvestTouchBook(world.getRegistryManager());

                return new TradeOffer(new TradedItem(Items.EMERALD, 12), Optional.of(new TradedItem(Items.BOOK, 3)), book, 5, 2, 0.05f);
            });
        });
    }

    private static ItemStack harvestTouchBook(RegistryWrapper.WrapperLookup registries) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

        RegistryEntry<Enchantment> enchantment = registries.getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(ModEnchantments.HARVEST_TOUCH);

        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
        builder.add(enchantment, 1);

        book.set(DataComponentTypes.STORED_ENCHANTMENTS, builder.build());

        return book;
    }
}