package net.illuminatijoe.esotericcircuitry.item;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EsotericCircuitry.MOD_ID);

    public static final RegistryObject<Item> COMPONENT_CONTAINER = ITEMS.register("component_container",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.component_container.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            }
    );
    public static final RegistryObject<Item> CONCEIVER_COMPONENT = ITEMS.register("conceiver_component",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.conceiver_component.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    public static final RegistryObject<Item> CONDUCTION_COMPONENT = ITEMS.register("conduction_component",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.conduction_component.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    public static final RegistryObject<Item> DIVINUM_CONTAINER = ITEMS.register("divinum_container",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIVINUM_INGOT = ITEMS.register("divinum_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CIRCUIT_T1 = ITEMS.register("esoteric_circuit_tier_1",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.esoteric_circuit_tier_1.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    public static final RegistryObject<Item> EXCITATION_NODE = ITEMS.register("excitation_node",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERCEIVER_COMPONENT = ITEMS.register("perceiver_component",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.perceiver_component.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    public static final RegistryObject<Item> RECEIVER_COMPONENT = ITEMS.register("receiver_component",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.esoteric_circuitry.receiver_component.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
