package flingus.scythemare.enchantment.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import flingus.scythemare.index.ModCriteria;
import flingus.scythemare.index.ModTags;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public record SoulReapEffect(float heal) implements EnchantmentEntityEffect {
    public static final MapCodec<SoulReapEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.FLOAT.fieldOf("heal").forGetter(SoulReapEffect::heal)).apply(instance, SoulReapEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (context.owner() instanceof LivingEntity attacker && target instanceof LivingEntity victim) {
            if (canReap(victim)) {
                float maxHealth = victim.getMaxHealth();
                float healAmount = maxHealth * (this.heal * level);

                attacker.heal(healAmount);

                world.playSound(null, victim.getX(), victim.getY(), victim.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.PLAYERS, 1.0F, 1.2F);
                world.spawnParticles(ParticleTypes.SOUL, victim.getX(), victim.getBodyY(0.5), victim.getZ(), 12, 0.25, 0.5, 0.25, 0.05);
                if (attacker instanceof ServerPlayerEntity player) {
                    ModCriteria.SOUL_REAP.trigger(player);
                }
            }
        }
    }

    public static boolean canReap(LivingEntity entity) {
        return !(entity.getType().isIn(ModTags.SOUL_REAP_IGNORE)) || entity.isDead();
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}