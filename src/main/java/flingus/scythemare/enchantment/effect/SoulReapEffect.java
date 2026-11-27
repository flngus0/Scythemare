package flingus.scythemare.enchantment.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import flingus.scythemare.index.ModCriteria;
import flingus.scythemare.index.ModTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SoulReapEffect(float heal) implements EnchantmentEntityEffect {
    public static final MapCodec<SoulReapEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.FLOAT.fieldOf("heal").forGetter(SoulReapEffect::heal)).apply(instance, SoulReapEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (context.owner() instanceof LivingEntity attacker && target instanceof LivingEntity victim) {
            if (canReap(victim)) {
                float maxHealth = victim.getMaxHealth();
                float healAmount = maxHealth * (this.heal * level);

                attacker.heal(healAmount);

                world.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.2F);
                world.sendParticles(ParticleTypes.SOUL, victim.getX(), victim.getY(0.5), victim.getZ(), 12, 0.25, 0.5, 0.25, 0.05);
                if (attacker instanceof ServerPlayer player) {
                    ModCriteria.SOUL_REAP.trigger(player);
                }
            }
        }
    }

    public static boolean canReap(LivingEntity entity) {
        return !(entity.getType().is(ModTags.SOUL_REAP_IGNORE)) || entity.isDeadOrDying();
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}