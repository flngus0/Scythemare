package flingus.scythemare.enchantment.effect;

import com.mojang.serialization.MapCodec;
import flingus.scythemare.entity.SoulSpiritEntity;
import flingus.scythemare.index.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record ReinforceEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ReinforceEffect> CODEC = MapCodec.unit(ReinforceEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (context.owner() instanceof LivingEntity attacker) {
            if (attacker.getHealth() <= 10.0F) {
                if(!target.isAlive() || target instanceof SoulSpiritEntity) return;

                List<SoulSpiritEntity> spirits = world.getEntitiesOfClass(
                        SoulSpiritEntity.class,
                        attacker.getBoundingBox().inflate(64.0),
                        spirit -> spirit.isAlive() && attacker.equals(spirit.getOwner())
                );

                if (spirits.size() >= 3 + (level - 1) * 2) {
                    return;
                }

                SoulSpiritEntity spirit = ModEntities.SOUL_SPIRIT.create(world, null, attacker.blockPosition(), EntitySpawnReason.REINFORCEMENT, true, false);

                if (spirit != null) {
                    spirit.snapTo(attacker.getX(), attacker.getY() + 2.0, attacker.getZ(), attacker.getYRot(), attacker.getXRot());
                    if (target instanceof LivingEntity livingTarget) {
                        spirit.setTarget(livingTarget);
                    }
                    world.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, spirit.getX(), spirit.getY(0.5), spirit.getZ(), 20, 0.5, 0.5, 0.5, 0.02);
                    world.sendParticles(ParticleTypes.SOUL, spirit.getX(), spirit.getY(0.5), spirit.getZ(), 6, 0.5, 0.5, 0.5, 0.02);

                    spirit.setOwner(attacker);

                    world.addFreshEntity(spirit);
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}