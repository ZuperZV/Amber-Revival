package net.zuperz.amber_revival.block.entity.custom;

import cpw.mods.util.Lazy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.zuperz.amber_revival.block.entity.ModBlockEntities;
import net.zuperz.amber_revival.recipe.FossilBreakerRecipe;
import net.zuperz.amber_revival.recipe.ItemHandlerRecipeInput;
import net.zuperz.amber_revival.screen.FossilBreakerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class FossilBreakerBlockEntity extends BlockEntity {
    private final ItemStackHandler inputItems = createItemHandler(1);
    private final ItemStackHandler outputItems = createItemHandler(1);

    // MAKE SURE TO USE net.neoforged.neoforge.common.util.Lazy and not the CPW package, your game WILL CRASH otherwise
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> new CombinedInvWrapper(inputItems, outputItems));
    private final Lazy<IItemHandler> inputItemHandler = Lazy.of(() -> new CustomItemHandler(inputItems));
    private final Lazy<IItemHandler> outputItemHandler = Lazy.of(() -> new CustomItemHandler(outputItems));


    private int myInt = 0;
    public FossilBreakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FOSSIL_BREAKER_BE.get(), pos, state);
    }

    public void tickServer() {
        // Ticking logic

        Random r = new Random();
        myInt = r.nextInt(0, 10); // Just as an example
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 0); // IMPORTANT
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.get().getSlots());
        for (int i = 0; i < itemHandler.get().getSlots(); i++) {
            inventory.setItem(i, itemHandler.get().getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        // you can also do stuff like tag.putInt("my_key", int)
        tag.putInt("fossil_block_entity.my_int", myInt);
        tag.put("fossil_block_entity.inputs", inputItems.serializeNBT(registries));
        tag.put("fossil_block_entity.outputs", outputItems.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        // you can also do stuff like myInt = tag.gettInt("my_key");
        myInt = tag.getInt("fossil_block_entity.my_int");
        if (tag.contains("fossil_block_entity.inputs"))
            inputItems.deserializeNBT(registries, tag.getCompound("fossil_block_entity.inputs"));

        if (tag.contains("fossil_block_entity.inputs"))
            outputItems.deserializeNBT(registries, tag.getCompound("fossil_block_entity.outputs"));
    }

    private ItemStackHandler createItemHandler(int slots) {
        return new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        loadAdditional(tag, lookupProvider);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    public Lazy<IItemHandler> getItemHandler() {
        return itemHandler;
    }

    public ItemStackHandler getInputItems() {
        return inputItems;
    }

    public ItemStackHandler getOutputItems() {
        return outputItems;
    }

    public Lazy<IItemHandler> getInputItemHandler() {
        return inputItemHandler;
    }
    public Lazy<IItemHandler> getOutputItemHandler() {
        return outputItemHandler;
    }

    public int getMyInt() {
        return myInt;
    }
}