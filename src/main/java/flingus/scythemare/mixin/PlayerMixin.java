package flingus.scythemare.mixin;

import flingus.scythemare.index.ModSounds;
import flingus.scythemare.index.ModTags;
import flingus.scythemare.item.ScytheItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin {
    @Shadow
    protected abstract void playAttackSound(SoundEvent sound);

    @Redirect(method = "canUseSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean scythemare$sweepAttackable(ItemStack stack, TagKey<?> tag) {
        if (stack.isIn(ModTags.SWEEP_ATTACKABLE)) {
            return true;
        }

        return stack.isIn(ItemTags.SWORDS);
    }

    @Redirect(method = "doSweepingAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;playAttackSound(Lnet/minecraft/sound/SoundEvent;)V"))
    private void scythemare$doSweepingAttack(PlayerEntity player, SoundEvent sound) {
        if (player.getMainHandStack().getItem() instanceof ScytheItem) {
            this.playAttackSound(ModSounds.SCYTHE_SWEEP);
        }
        else {
            this.playAttackSound(sound);
        }
    }
}