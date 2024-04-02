package net.illuminatijoe.esotericcircuitry.item;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EsotericCircuitry.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ESOTERIC_CIRCUITRY_TAB = CREATIVE_MODE_TABS.register("esoteric_circuitry_tab",
            () -> CreativeModeTab
                    .builder()
                    .icon(() -> new ItemStack(Items.GOLD_BLOCK))
                    .title(Component.translatable("creative_tab.esoteric_circuitry_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                            pOutput.accept(item.get());
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
 }
