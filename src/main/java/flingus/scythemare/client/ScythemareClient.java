package flingus.scythemare.client;

import flingus.scythemare.client.index.ModEntityModelLayers;
import flingus.scythemare.client.render.entity.SoulSpiritEntityRenderer;
import flingus.scythemare.index.ModDataComponentTypes;
import flingus.scythemare.index.ModEntities;
import flingus.scythemare.index.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ScythemareClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityModelLayers.init();

        EntityRendererFactories.register(ModEntities.SOUL_SPIRIT, SoulSpiritEntityRenderer::new);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.contains(ModDataComponentTypes.SKIN_COMPONENT)) {
                Text skin = Text.literal(" (").append(Text.translatable(ModDataComponentTypes.getTranslatedSkinName(itemStack.get(ModDataComponentTypes.SKIN_COMPONENT)))).append(")").formatted(Formatting.DARK_GRAY);

                list.set(0, Text.empty().append(list.getFirst()).append(skin));
            }

            if(itemStack.isOf(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE)) {
                list.add(1, Text.translatable("item.minecraft.smithing_template").formatted(Formatting.GRAY));
                list.add(2, Text.empty());
                list.add(3, Text.translatable("item.minecraft.smithing_template.applies_to").formatted(Formatting.GRAY));
                list.add(4, Text.literal(" ").append(Text.translatable("item.scythemare.smithing_template.netherite_scythes").formatted(Formatting.BLUE)));
            }
        });
    }
}
