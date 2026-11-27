package flingus.scythemare.index;

import moriyashiine.enchancement.common.util.EnchancementUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

public class ModTrades {
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2, factories -> {
            factories.add((world, entity, random) -> {
                if(FabricLoader.getInstance().isModLoaded("enchancement")) {
                    if (!EnchancementUtil.isEnchantmentAllowed(ModEnchantments.HARVEST_TOUCH.identifier())) {
                        return null;
                    }
                }

                ItemStack book = harvestTouchBook(world.registryAccess());

                return new MerchantOffer(new ItemCost(Items.EMERALD, 12), Optional.of(new ItemCost(Items.BOOK, 3)), book, 5, 2, 0.05f);
            });
        });
    }

    private static ItemStack harvestTouchBook(HolderLookup.Provider registries) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

        Holder<Enchantment> enchantment = registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchantments.HARVEST_TOUCH);

        ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        builder.upgrade(enchantment, 1);

        book.set(DataComponents.STORED_ENCHANTMENTS, builder.toImmutable());

        return book;
    }
}