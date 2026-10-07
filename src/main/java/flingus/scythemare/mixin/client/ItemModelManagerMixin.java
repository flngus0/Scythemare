package flingus.scythemare.mixin.client;

import flingus.scythemare.Scythemare;
import flingus.scythemare.index.ModDataComponentTypes;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemModelManager.class)
public class ItemModelManagerMixin {
    @ModifyVariable(method = "update", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private Identifier scythemare$changeSkinModel(Identifier identifier, ItemRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, @Nullable World world, @Nullable HeldItemContext heldItemContext, int seed) {
        if (identifier != null) {
            Identifier skin = stack.get(ModDataComponentTypes.SKIN_COMPONENT);

            if (skin != null) {
                return Scythemare.id("scythe_skin/" + skin.getPath());
            }
        }

        return identifier;
    }
}