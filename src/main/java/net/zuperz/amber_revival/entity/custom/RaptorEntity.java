package net.zuperz.amber_revival.entity.custom;

import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.zuperz.amber_revival.entity.ModEntities;
import net.zuperz.amber_revival.entity.client.RaptorVariant;
import net.zuperz.amber_revival.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class RaptorEntity extends Animal {
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(RaptorEntity.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    static final Predicate<ItemEntity> ALLOWED_ITEMS = ItemEntity -> !ItemEntity.hasPickUpDelay() && ItemEntity.isAlive();
    private float interestedAngle;
    private float interestedAngleO;
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(RaptorEntity.class, EntityDataSerializers.BYTE);


    public RaptorEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setPathfindingMalus(PathType.DANGER_OTHER, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_OTHER, 0.0F);

        this.setCanPickUpLoot(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.RESIN), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new RaptorEntity.RaptorEntitySearchForItemsGoal());
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 40D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.RESIN.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.RAPTOR.get().create(pLevel);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 60;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        this.interestedAngleO = this.interestedAngle;
        if (this.isInterested()) {
            this.interestedAngle = this.interestedAngle + (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle = this.interestedAngle + (0.0F - this.interestedAngle) * 0.4F;
        }
    }

    public boolean isInterested() {
        return this.getFlag(8);
    }

    private boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_FLAGS_ID) & pFlagId) != 0;
    }

    /* VARIANT */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, 0);
        pBuilder.define(DATA_FLAGS_ID, (byte)0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public RaptorVariant getVariant() {
        return RaptorVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(RaptorVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType,
                                        @Nullable SpawnGroupData pSpawnGroupData) {
        RaptorVariant variant = Util.getRandom(RaptorVariant.values(), this.random);
        this.setVariant(variant);
        
        this.populateDefaultEquipmentSlots(pLevel.getRandom(), pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    /* SOUNDS */

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.FOX_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }
    
    @Override
    public SoundEvent getEatingSound(ItemStack pItemStack) {
        return SoundEvents.FOX_EAT;
    }
    
    /* ITEM */

    public float getHeadRollAngle(float pPartialTick) {
        return Mth.lerp(pPartialTick, this.interestedAngleO, this.interestedAngle) * 0.11F * (float) Math.PI;
    }

    class RaptorEntitySearchForItemsGoal extends Goal {
        public RaptorEntitySearchForItemsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (!RaptorEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (RaptorEntity.this.getTarget() != null || RaptorEntity.this.getLastHurtByMob() != null) {
                return false;
            } else if (RaptorEntity.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                return false;
            } else {
                List<ItemEntity> list = RaptorEntity.this.level()
                        .getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(8.0, 8.0, 8.0), RaptorEntity.ALLOWED_ITEMS);
                return !list.isEmpty() && RaptorEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        @Override
        public void tick() {
            List<ItemEntity> list = RaptorEntity.this.level().getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(8.0, 8.0, 8.0), RaptorEntity.ALLOWED_ITEMS);
            ItemStack itemstack = RaptorEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                RaptorEntity.this.getNavigation().moveTo(list.get(0), 1.2F);
            }


        }

        @Override
        public void start() {
            List<ItemEntity> list = RaptorEntity.this.level().getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(8.0, 8.0, 8.0), RaptorEntity.ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                RaptorEntity.this.getNavigation().moveTo(list.get(0), 1.2F);
            }
        }
    }
    
    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = this.getEquipmentSlotForItem(pItemstack);
        return !this.getItemBySlot(equipmentslot).isEmpty() ? false : equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
    }

    @Override
    public boolean canHoldItem(ItemStack pStack) {
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);

        boolean isEmpty = itemstack.isEmpty();

        boolean hasFoodData = pStack.has(DataComponents.FOOD);

        boolean isAmber = pStack.getItem() == ModItems.AMBER.get();

        boolean isResin = pStack.getItem() == ModItems.RESIN.get();

        return isEmpty && (hasFoodData || isAmber || isResin);
    }


    private void dropItemStack(ItemStack pStack) {
        ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), pStack);
        this.level().addFreshEntity(itementity);
    }

    @Override
    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        if (this.isFood(pStack)) {
            this.playSound(this.getEatingSound(pStack), 1.0F, 1.0F);
        }

        super.usePlayerItem(pPlayer, pHand, pStack);
    }

    /**
     * Tests if this entity should pick up a weapon or an armor piece. Entity drops current weapon or armor if the new one is better.
     */
    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (this.canHoldItem(itemstack)) {
            int i = itemstack.getCount();
            if (i > 1) {
                this.dropItemStack(itemstack.split(i - 1));
            }

            this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
            this.onItemPickup(pItemEntity);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            this.take(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
        }
    }
    
    private void spitOutItem(ItemStack pStack) {
        if (!pStack.isEmpty() && !this.level().isClientSide) {
            ItemEntity itementity = new ItemEntity(
                    this.level(), this.getX() + this.getLookAngle().x, this.getY() + 1.0, this.getZ() + this.getLookAngle().z, pStack
            );
            itementity.setPickUpDelay(40);
            itementity.setThrower(this);
            this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
            this.level().addFreshEntity(itementity);
        }
    }

    private boolean canEat(ItemStack pStack) {
        return pStack.has(DataComponents.FOOD) && this.getTarget() == null && this.onGround() && !this.isSleeping();
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        if (pRandom.nextFloat() < 0.2F) {
            float f = pRandom.nextFloat();
            ItemStack itemstack;
            if (f < 0.05F) {
                itemstack = new ItemStack(Items.DIAMOND);
            } else if (f < 0.2F) {
                itemstack = new ItemStack(Items.EGG);
            } else if (f < 0.4F) {
                itemstack = pRandom.nextBoolean() ? new ItemStack(Items.RABBIT_FOOT) : new ItemStack(Items.RABBIT_HIDE);
            } else if (f < 0.6F) {
                itemstack = new ItemStack(Items.WHEAT);
            } else if (f < 0.8F) {
                itemstack = new ItemStack(Items.LEATHER);
            } else {
                itemstack = new ItemStack(Items.FEATHER);
            }

            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 45) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                for (int i = 0; i < 8; i++) {
                    Vec3 vec3 = new Vec3(((double)this.random.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0)
                            .xRot(-this.getXRot() * (float) (Math.PI / 180.0))
                            .yRot(-this.getYRot() * (float) (Math.PI / 180.0));
                    this.level()
                            .addParticle(
                                    new ItemParticleOption(ParticleTypes.ITEM, itemstack),
                                    this.getX() + this.getLookAngle().x / 2.0,
                                    this.getY(),
                                    this.getZ() + this.getLookAngle().z / 2.0,
                                    vec3.x,
                                    vec3.y + 0.05,
                                    vec3.z
                            );
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }
    }

    protected void dropEquipment() { // Forge: move extra drops to dropEquipment to allow them to be captured by LivingDropsEvent
        super.dropEquipment();
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!itemstack.isEmpty()) {
            this.spawnAtLocation(itemstack);
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }

    }
}