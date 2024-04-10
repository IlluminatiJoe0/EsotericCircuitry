package net.illuminatijoe.esotericcircuitry.block.entity;

import net.illuminatijoe.esotericcircuitry.block.ModBlockEntities;
import net.illuminatijoe.esotericcircuitry.block.entity.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class ArcaneConduitEntity extends BlockEntity implements TickableBlockEntity {
    public ArcaneConduitEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ARCANE_CONDUIT_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }


    public int ticks = 0;
    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide()){
            return;
        }

        if (ticks++ % 20 == 0){
            getLevel().playSound(null, getBlockPos(), SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 0.1f, 5f);
        }
    }
}
