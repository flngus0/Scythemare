package flingus.scythemare.entity;

import flingus.scythemare.index.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class SoulSpiritEntity extends PathfinderMob implements TraceableEntity {
    private static final EntityDataAccessor<Boolean> DISAPPEARING = SynchedEntityData.defineId(SoulSpiritEntity.class, EntityDataSerializers.BOOLEAN);

    protected @Nullable EntityReference<Entity> owner;
    public final AnimationState idleState = new AnimationState();
    public final AnimationState disappearState = new AnimationState();
    private int disappearTicks = 8;

    public SoulSpiritEntity(EntityType<? extends SoulSpiritEntity> entityType, Level world) {
        super(entityType, world);
        this.xpReward = 4;
        this.setNoGravity(true);

        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DISAPPEARING, false);
    }

    public boolean isDisappearing() {
        return this.entityData.get(DISAPPEARING);
    }

    public void setDisappearing(boolean value) {
        this.entityData.set(DISAPPEARING, value);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 0.9));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;

        if (this.level().isClientSide()) {
            if (this.isDisappearing()) {
                this.idleState.stop();
                this.disappearState.startIfStopped(this.tickCount);
            }
            else {
                this.idleState.startIfStopped(this.tickCount);
                this.disappearState.stop();
            }
            return;
        }

        LivingEntity target = this.getTarget();
        if (target != null && !target.isAlive() && !this.isDisappearing()) {
            this.setDisappearing(true);
            this.setTarget(null);
        }

        if (this.isDisappearing()) {
            this.disappearTicks--;
            if (this.disappearTicks <= 0 && this.level() instanceof ServerLevel serverWorld) {
                serverWorld.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, this.getX(), this.getY(0.5), this.getZ(), 15, 0.5, 0.5, 0.5, 0.02);
                serverWorld.sendParticles(ParticleTypes.SOUL, this.getX(), this.getY(0.5), this.getZ(), 4, 0.5, 0.5, 0.5, 0.02);
                this.playSound(ModSounds.SOUL_SPIRIT_DISAPPEAR, 0.4f, 1.064f);

                this.discard();
            }
        }
    }

    protected void setOwner(@Nullable EntityReference<Entity> owner) {
        this.owner = owner;
    }

    public void setOwner(@Nullable Entity owner) {
        this.setOwner(EntityReference.of(owner));
    }

    protected boolean isOwner(Entity entity) {
        return this.owner != null && this.owner.matches(entity);
    }

    @Override
    public @Nullable Entity getOwner() {
        return EntityReference.getEntity(this.owner, this.level());
    }

    @Override
    public void travel(Vec3 movementInput) {
        if (this.isDisappearing()) return;
        this.travelFlying(movementInput, this.getSpeed());

        this.processFlappingMovement();

        this.calculateEntityAnimation(true);
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public boolean canStandOnFluid(FluidState state) {
        return true;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.FLYING_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        EntityReference.store(this.owner, view, "Owner");
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        this.setOwner(EntityReference.read(view, "Owner"));
    }

}