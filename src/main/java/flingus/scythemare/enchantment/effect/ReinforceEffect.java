package flingus.scythemare.enchantment.effect;

import com.mojang.serialization.MapCodec;
import flingus.scythemare.Scythemare;
import flingus.scythemare.entity.SoulSpiritEntity;
import flingus.scythemare.index.ModEntities;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public record ReinforceEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ReinforceEffect> CODEC = MapCodec.unit(ReinforceEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (context.owner() instanceof LivingEntity attacker) {
            if (attacker.getHealth() <= 10.0F) {
                if(!target.isAlive() || target instanceof SoulSpiritEntity) return;

                List<SoulSpiritEntity> spirits = world.getEntitiesByClass(
                        SoulSpiritEntity.class,
                        attacker.getBoundingBox().expand(64.0),
                        spirit -> spirit.isAlive() && attacker.equals(spirit.getOwner())
                );

                if (spirits.size() >= 3 + (level - 1) * 2) {
                    return;
                }

                SoulSpiritEntity spirit = ModEntities.SOUL_SPIRIT.create(world, null, attacker.getBlockPos(), SpawnReason.REINFORCEMENT, true, false);

                if (spirit != null) {
                    spirit.refreshPositionAndAngles(attacker.getX(), attacker.getY() + 2.0, attacker.getZ(), attacker.getYaw(), attacker.getPitch());
                    if (target instanceof LivingEntity livingTarget) {
                        spirit.setTarget(livingTarget);
                    }
                    world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, spirit.getX(), spirit.getBodyY(0.5), spirit.getZ(), 20, 0.5, 0.5, 0.5, 0.02);
                    world.spawnParticles(ParticleTypes.SOUL, spirit.getX(), spirit.getBodyY(0.5), spirit.getZ(), 6, 0.5, 0.5, 0.5, 0.02);

                    spirit.setOwner(attacker);

                    world.spawnEntity(spirit);
                    Scythemare.LOGGER.info(String.valueOf(spirit.getOwner()));
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}