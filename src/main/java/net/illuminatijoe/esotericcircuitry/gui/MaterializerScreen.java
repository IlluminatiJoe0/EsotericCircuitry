package net.illuminatijoe.esotericcircuitry.gui;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.block.entity.MaterializerEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class MaterializerScreen extends AbstractContainerScreen<MaterializerMenu> {
    public static final Component TITLE = Component.translatable("gui.esoteric_circuitry.materializer_screen");

    private final int imageWidth, imageHeight;
    private static final ResourceLocation TEXTURE = new ResourceLocation(EsotericCircuitry.MOD_ID,
            "textures/gui/materializer_menu.png");

    public MaterializerScreen(MaterializerMenu menu, Inventory inventory, Component pTitle) {
        super(menu, inventory, TITLE);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        if(this.minecraft == null) return;

        Level level = this.minecraft.level;
        if(level == null) return;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float v, int i, int i1) {
        renderBackground(guiGraphics);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
