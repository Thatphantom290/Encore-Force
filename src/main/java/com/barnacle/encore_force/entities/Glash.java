package com.barnacle.encore_force.entities;

import com.platypushasnohat.sinew.client.animation.SmoothAnimationState;
import com.platypushasnohat.sinew.entity.base.AnimatedMonster;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class Glash extends AnimatedMonster {

    public final SmoothAnimationState attackAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState punchAnimationState = new SmoothAnimationState();

    public Glash(EntityType<? extends Glash> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 8;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GlashAttackGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true, true));
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.getAnimationState() == 0, this.tickCount);
        this.walkAnimationState.animateWhen(this.getAnimationState() == 0, this.tickCount);
        this.attackAnimationState.animateWhen(this.getAnimationState() == 1, this.tickCount);
        this.punchAnimationState.animateWhen(this.hurtTime > 0, this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float length = (float) Mth.length(this.getX() - this.xo, 0.0F, this.getZ() - this.zo);
        float speed = Math.min(length * 10.0F, 1.0F);
        this.walkAnimation.update(speed, 0.5F);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setSprinting(this.isAggressive() && this.getDeltaMovement().horizontalDistance() > 0.05D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.15F, 0.5F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.5F;
    }

    class GlashAttackGoal extends Goal {
        private final Glash glash;
        private int attackTickDelay = 0;
        private int cooldown = 0;
        private boolean isAttacking = false;

        public GlashAttackGoal(Glash glash) {
            this.glash = glash;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.glash.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.glash.setAggressive(true);
            this.cooldown = 0;
            this.isAttacking = false;
            this.glash.setAnimationState(0);
        }

        @Override
        public void stop() {
            this.glash.setAggressive(false);
            this.glash.setAnimationState(0);
            this.glash.getNavigation().stop();
            this.isAttacking = false;
        }

        @Override
        public void tick() {
            LivingEntity target = this.glash.getTarget();
            if (target == null) return;

            this.glash.getLookControl().setLookAt(target, 30.0F, 30.0F);
            double distSqr = this.glash.distanceToSqr(target);

            double reachSqr = this.glash.getBbWidth() * 2.0F * this.glash.getBbWidth() * 2.0F + target.getBbWidth();

            if (this.cooldown > 0) {
                this.cooldown--;
            }

            if (this.isAttacking) {
                this.glash.getNavigation().stop();
                this.attackTickDelay--;

                if (this.attackTickDelay == 6) {
                    if (distSqr <= reachSqr) {
                        this.glash.doHurtTarget(target);
                    }
                }

                if (this.attackTickDelay <= 0) {
                    this.isAttacking = false;
                    this.glash.setAnimationState(0);
                    this.cooldown = 8;
                }
            } else {
                this.glash.getNavigation().moveTo(target, 1.2D);

                if (distSqr <= reachSqr && this.cooldown <= 0) {
                    this.isAttacking = true;
                    this.attackTickDelay = 8;
                    this.glash.setAnimationState(1);
                }
            }
        }
    }
}
