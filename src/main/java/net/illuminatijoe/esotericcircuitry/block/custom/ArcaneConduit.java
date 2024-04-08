package net.illuminatijoe.esotericcircuitry.block.custom;

import net.illuminatijoe.esotericcircuitry.block.ModBlockEntities;
import net.illuminatijoe.esotericcircuitry.block.ModBlocks;
import net.illuminatijoe.esotericcircuitry.block.entity.ArcaneConduitEntity;
import net.illuminatijoe.esotericcircuitry.block.entity.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArcaneConduit extends Block implements EntityBlock {
    // constructor
    public ArcaneConduit(Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
//        if (!pLevel.isClientSide() && !pMovedByPiston){
//            BlockState block = pLevel.getBlockState(pPos.below());
//
//            if (isMachine(block)){
//                //testing
//                pLevel.playLocalSound(pPos, SoundEvents.NOTE_BLOCK_BELL.get(), SoundSource.BLOCKS, 1f, 1f, false);
//            } else {
//                pLevel.playLocalSound(pPos, SoundEvents.NOTE_BLOCK_BANJO.get(), SoundSource.BLOCKS, 1f, 1f, false);
//
//            }
//        }
//
//        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
//    }

    // check if the block is a machine
    private boolean isMachine(BlockState block) {
        return block.is(ModBlocks.ARCANE_CONDUIT.get());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.esoteric_circuitry.arcane_conduit.tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

//    @Override
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlockEntities.ARCANE_CONDUIT_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return TickableBlockEntity.getTickerHelper(pLevel);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND){
            pPlayer.sendSystemMessage(Component.literal("Hello"));
            return InteractionResult.sidedSuccess(pLevel.isClientSide());
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

}
