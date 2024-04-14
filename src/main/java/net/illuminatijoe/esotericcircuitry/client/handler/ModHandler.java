package net.illuminatijoe.esotericcircuitry.client.handler;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.gui.MaterializerScreen;
import net.illuminatijoe.esotericcircuitry.gui.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EsotericCircuitry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModHandler {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.MATERIALIZER_MENU.get(), MaterializerScreen::new);
            // TODO: Add more later
        });
    }


}
