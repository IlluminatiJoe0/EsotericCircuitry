package net.illuminatijoe.esotericcircuitry.block.entity;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.block.ModBlockEntities;
import net.illuminatijoe.esotericcircuitry.block.entity.util.FunctionalBlockEntity;
import net.illuminatijoe.esotericcircuitry.block.entity.util.TickableBlockEntity;
import net.illuminatijoe.esotericcircuitry.gui.MaterializerMenu;
import net.illuminatijoe.esotericcircuitry.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
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

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 400;
    private static final int OUTPUT_SLOT = 0;
    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);


    public MaterializerEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MATERIALIZER_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> MaterializerEntity.this.progress;
                    case 1 -> MaterializerEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MaterializerEntity.this.progress = pValue;
                    case 1 -> MaterializerEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
        return inventory.getStackInSlot(OUTPUT_SLOT);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag data = nbt.getCompound(EsotericCircuitry.MOD_ID);
        this.inventory.deserializeNBT(data.getCompound("inventory"));
        this.turnedOn = data.getBoolean("turned_on");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        var data = new CompoundTag();
        data.put("inventory", this.inventory.serializeNBT());
        data.putBoolean("turned_on", this.turnedOn);
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


    private int ticks = 0;
    @Override
    public void tick() {
        if (this.turnedOn){
            increaseProgress();
            if(ticks == 399) {
                getLevel().playSound(null, getBlockPos(), SoundEvents.LAVA_EXTINGUISH,
                        SoundSource.BLOCKS, 0.3f, 1f);
            }
            if(hasFinished()){
                outputItem();
                setChanged();
            }
        } else {
            resetProgress();
        }

    }

    private void resetProgress() {
        progress = 0;
        ticks = 0;
    }

    private void outputItem() {
        ItemStack result = ModItems.DIVINUM_INGOT.get().getDefaultInstance();

        this.inventory.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.inventory.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));

        resetProgress();
    }

    private boolean hasFinished() {
        return progress >= maxProgress;
    }

    private void increaseProgress() {
        progress++;
        ticks++;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.esoteric_circuitry.materializer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MaterializerMenu(i, inventory, this);
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return this.optional;
    }
}
