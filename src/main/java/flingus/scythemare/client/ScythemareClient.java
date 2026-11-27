package flingus.scythemare.client;

import flingus.scythemare.client.index.ModEntityModelLayers;
import flingus.scythemare.client.render.entity.SoulSpiritEntityRenderer;
import flingus.scythemare.index.ModDataComponentTypes;
import flingus.scythemare.index.ModEntities;
import flingus.scythemare.index.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;

public class ScythemareClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityModelLayers.init();

        EntityRenderers.register(ModEntities.SOUL_SPIRIT, SoulSpiritEntityRenderer::new);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.has(ModDataComponentTypes.SKIN_COMPONENT)) {
                Component skin = Component.literal(" (").append(Component.translatable(ModDataComponentTypes.getTranslatedSkinName(itemStack.get(ModDataComponentTypes.SKIN_COMPONENT)))).append(")").withStyle(ChatFormatting.DARK_GRAY);

                list.set(0, Component.empty().append(list.getFirst()).append(skin));
            }

            if(itemStack.is(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE)) {
                list.add(1, Component.translatable("item.minecraft.smithing_template").withStyle(ChatFormatting.GRAY));
                list.add(2, Component.empty());
                list.add(3, Component.translatable("item.minecraft.smithing_template.applies_to").withStyle(ChatFormatting.GRAY));
                list.add(4, Component.literal(" ").append(Component.translatable("item.scythemare.smithing_template.netherite_scythes").withStyle(ChatFormatting.BLUE)));
            }
        });
    }
}
