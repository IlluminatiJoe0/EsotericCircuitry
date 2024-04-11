package net.illuminatijoe.esotericcircuitry.block.entity;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.block.ModBlockEntities;
import net.illuminatijoe.esotericcircuitry.block.entity.util.FunctionalBlockEntity;
import net.illuminatijoe.esotericcircuitry.block.entity.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaterializerEntity extends FunctionalBlockEntity implements TickableBlockEntity, MenuProvider {
    private final ItemStackHandler inventory = new ItemStackHandler() {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            MaterializerEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    public MaterializerEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MATERIALIZER_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag data = nbt.getCompound(EsotericCircuitry.MOD_ID);
        this.inventory.deserializeNBT(data.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        var data = new CompoundTag();
        data.put("inventory", this.inventory.serializeNBT());
        nbt.put(EsotericCircuitry.MOD_ID, data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.optional.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }
    public ItemStack getStackInSlot(int slot) {
        return this.inventory.getStackInSlot(slot);
    }
    public void setStackInSlot(int slot, ItemStack stack) {
        this.inventory.setStackInSlot(slot, stack);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }



    @Override
    public void tick() {

    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.esoteric_circuitry.materializer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }
}
