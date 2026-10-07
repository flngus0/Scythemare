package flingus.scythemare.mixin;

import flingus.scythemare.index.ModItems;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {
    @Inject(method = "makeInitialWeapon", at = @At("HEAD"), cancellable = true)
    private void scythemare$makeInitialWeapon(CallbackInfoReturnable<ItemStack> cir) {
        PiglinEntity piglin = (PiglinEntity) (Object) this;

        if (piglin.getRandom().nextFloat() < 0.1F) {
            cir.setReturnValue(new ItemStack(ModItems.GOLDEN_SCYTHE));
        }
    }
}