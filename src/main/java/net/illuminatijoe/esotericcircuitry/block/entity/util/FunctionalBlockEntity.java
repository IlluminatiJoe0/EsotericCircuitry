package net.illuminatijoe.esotericcircuitry.block.entity.util;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FunctionalBlockEntity extends BlockEntity {
    public boolean turnedOn = false;
    public FunctionalBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public void turnOn(){
        if (!this.turnedOn){
            this.turnedOn = true;
            getLevel().playSound(null, getBlockPos(), SoundEvents.NOTE_BLOCK_BELL.get(), SoundSource.BLOCKS, 1f, 1f);
        }
    }
}
