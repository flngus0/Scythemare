package flingus.scythemare.entity;

import flingus.scythemare.index.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class SoulSpiritEntity extends PathAwareEntity implements Ownable {
    private static final TrackedData<Boolean> DISAPPEARING = DataTracker.registerData(SoulSpiritEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected @Nullable LazyEntityReference<Entity> owner;
    public final AnimationState idleState = new AnimationState();
    public final AnimationState disappearState = new AnimationState();
    private int disappearTicks = 8;

    public SoulSpiritEntity(EntityType<? extends SoulSpiritEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 4;
        this.setNoGravity(true);

        this.moveControl = new FlightMoveControl(this, 10, false);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new BirdNavigation(this, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DISAPPEARING, false);
    }

    public boolean isDisappearing() {
        return this.dataTracker.get(DISAPPEARING);
    }

    public void setDisappearing(boolean value) {
        this.dataTracker.set(DISAPPEARING, value);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(1, new FlyGoal(this, 0.9));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;

        if (this.getEntityWorld().isClient()) {
            if (this.isDisappearing()) {
                this.idleState.stop();
                this.disappearState.startIfNotRunning(this.age);
            }
            else {
                this.idleState.startIfNotRunning(this.age);
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
            if (this.disappearTicks <= 0 && this.getEntityWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, this.getX(), this.getBodyY(0.5), this.getZ(), 15, 0.5, 0.5, 0.5, 0.02);
                serverWorld.spawnParticles(ParticleTypes.SOUL, this.getX(), this.getBodyY(0.5), this.getZ(), 4, 0.5, 0.5, 0.5, 0.02);
                this.playSound(ModSounds.SOUL_SPIRIT_DISAPPEAR, 0.4f, 1.064f);

                this.discard();
            }
        }
    }

    protected void setOwner(@Nullable LazyEntityReference<Entity> owner) {
        this.owner = owner;
    }

    public void setOwner(@Nullable Entity owner) {
        this.setOwner(LazyEntityReference.of(owner));
    }

    protected boolean isOwner(Entity entity) {
        return this.owner != null && this.owner.uuidEquals(entity);
    }

    @Override
    public @Nullable Entity getOwner() {
        return LazyEntityReference.getEntity(this.owner, this.getEntityWorld());
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isDisappearing()) return;
        this.travelFlying(movementInput, this.getMovementSpeed());

        this.addAirTravelEffects();

        this.updateLimbs(true);
    }

    @Override
    public boolean shouldRenderName() {
        return false;
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    public boolean canWalkOnFluid(FluidState state) {
        return true;
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        return !this.isOwner(target);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 8.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.15)
                .add(EntityAttributes.FLYING_SPEED, 0.15)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        LazyEntityReference.writeData(this.owner, view, "Owner");
    }

    @Override
    protected void readCustomData(ReadView view) {
        this.setOwner(LazyEntityReference.fromData(view, "Owner"));
    }

}