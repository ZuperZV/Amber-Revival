package net.zuperz.amber_revival.screen;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zuperz.amber_revival.AmberRevival;
import net.zuperz.amber_revival.block.ModBlocks;
import net.zuperz.amber_revival.block.entity.custom.FossilBreakerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class FossilBreakerMenu extends AbstractContainerMenu {
    private final FossilBreakerBlockEntity blockEntity;
    private final ContainerData data;

    // Constructor used on the client
    public FossilBreakerMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, buf != null ? inv.player.level().getBlockEntity(buf.readBlockPos()) : null, new SimpleContainerData(3));
    }

    // Constructor used on the server
    public FossilBreakerMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.FOSSIL_BREAKER_MENU.get(), id);
        FossilBreakerBlockEntity blockEntity1;
        checkContainerSize(inv, 3); // Assuming 3 slots
        blockEntity1 = (FossilBreakerBlockEntity) entity;
        this.data = data;

        if (entity instanceof FossilBreakerBlockEntity) {
        } else {
            throw new IllegalStateException("Unexpected BlockEntity");
        }

        // Add the custom slots (2 inputs, 1 output)
        this.blockEntity = blockEntity1;
        ItemStackHandler itemHandler = (ItemStackHandler) blockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(itemHandler, 0, 56, 17)); // Input Slot 1
        this.addSlot(new SlotItemHandler(itemHandler, 1, 56, 53)); // Input Slot 2
        this.addSlot(new SlotItemHandler(itemHandler, 2, 116, 35)); // Output Slot

        // Add the player's inventory slots
        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        // Add data synchronization
        this.addDataSlots(data);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), player, ModBlocks.FOSSIL_BREAKER.get());
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < 3) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}

