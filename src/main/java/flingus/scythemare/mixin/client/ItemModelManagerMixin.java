package flingus.scythemare.mixin.client;

import flingus.scythemare.Scythemare;
import flingus.scythemare.index.ModDataComponentTypes;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemModelResolver.class)
public class ItemModelManagerMixin {
    @ModifyVariable(method = "appendItemLayers", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private Identifier scythemare$changeSkinModel(Identifier identifier, ItemStackRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, @Nullable Level world, @Nullable ItemOwner heldItemContext, int seed) {
        if (identifier != null) {
            Identifier skin = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

            if (skin != null) {
                return Scythemare.id("scythe_skin/" + skin.getPath());
            }
        }

        return identifier;
    }
}