package flingus.scythemare.mixin;

import flingus.scythemare.index.ModItems;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Piglin.class)
public class PiglinEntityMixin {
    @Inject(method = "createSpawnWeapon", at = @At("HEAD"), cancellable = true)
    private void scythemare$makeInitialWeapon(CallbackInfoReturnable<ItemStack> cir) {
        Piglin piglin = (Piglin) (Object) this;

        if (piglin.getRandom().nextFloat() < 0.1F) {
            cir.setReturnValue(new ItemStack(ModItems.GOLDEN_SCYTHE));
        }
    }
}