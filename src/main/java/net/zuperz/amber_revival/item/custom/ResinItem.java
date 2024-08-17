package net.zuperz.amber_revival.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.zuperz.amber_revival.item.ModItems;

import java.util.List;

import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;

public class ResinItem extends Item {
    public ResinItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (pLevel.isClientSide) {
            return InteractionResultHolder.pass(itemstack);
        }

        BlockHitResult blockhitresult = getPlayerPOVHitResult(
                pLevel, pPlayer, ClipContext.Fluid.NONE
        );

        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        }

        BlockPos blockpos = blockhitresult.getBlockPos();
        BlockState blockstate = pLevel.getBlockState(blockpos);

        if (blockstate.getBlock() == Blocks.SLIME_BLOCK) {
            pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 3);
            ItemStack newItemStack = new ItemStack(Items.DIAMOND); // Change to desired item
            pPlayer.getInventory().add(newItemStack);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            itemstack.shrink(1);
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        }

        // Check for nearby slimes
        List<Slime> slimes = pLevel.getEntitiesOfClass(Slime.class, new AABB(pPlayer.blockPosition()).inflate(5.0));
        boolean slimeProcessed = false;

        for (Slime slime : slimes) {
            if (slime.getBoundingBox().inflate(1.0).contains(pPlayer.getEyePosition(1.0f))) {
                slime.discard();
                slimeProcessed = true;

                // Play a sound effect
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SLIME_SQUISH, SoundSource.PLAYERS, 1.0F, 1.0F);

                // Add one new item
                ItemStack newItemStack = new ItemStack(ModItems.SLIME_AMBER.get());
                pPlayer.getInventory().add(newItemStack);

                // Remove one resin item
                itemstack.shrink(1);

                pPlayer.awardStat(Stats.ITEM_USED.get(this));
                break; // Stop processing after handling the first slime
            }
        }

        if (slimeProcessed) {
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        }

        return InteractionResultHolder.pass(itemstack);
    }
}