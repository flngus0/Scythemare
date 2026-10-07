package flingus.scythemare.mixin.client.enchancement;

import flingus.scythemare.Scythemare;
import flingus.scythemare.index.ModItems;
import moriyashiine.enchancement.client.gui.tooltip.StoredEnchantmentsTooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Pseudo
@Mixin(StoredEnchantmentsTooltipComponent.class)
public class StoredEnchantmentsTooltipComponentMixin {
    @Shadow
    @Final
    private static Map<ItemStack, Identifier> TEXTURE_MAP;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void scythemare$addIcon(CallbackInfo ci) {
        Map<ItemStack, Identifier> map = TEXTURE_MAP;

        map.put(ModItems.IRON_SCYTHE.getDefaultStack(), Scythemare.id("container/slot/scythe"));
    }
}