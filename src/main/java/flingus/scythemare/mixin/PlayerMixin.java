package flingus.scythemare.mixin;

import flingus.scythemare.index.ModSounds;
import flingus.scythemare.index.ModTags;
import flingus.scythemare.item.ScytheItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Shadow
    protected abstract void playServerSideSound(SoundEvent sound);

    @Redirect(method = "isSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean scythemare$sweepAttackable(ItemStack stack, TagKey<?> tag) {
        if (stack.is(ModTags.SWEEP_ATTACKABLE)) {
            return true;
        }

        return stack.is(ItemTags.SWORDS);
    }

    @Redirect(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;playServerSideSound(Lnet/minecraft/sounds/SoundEvent;)V"))
    private void scythemare$doSweepingAttack(Player player, SoundEvent sound) {
        if (player.getMainHandItem().getItem() instanceof ScytheItem) {
            this.playServerSideSound(ModSounds.SCYTHE_SWEEP);
        }
        else {
            this.playServerSideSound(sound);
        }
    }
}