package net.illuminatijoe.esotericcircuitry.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.block.entity.MaterializerEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MaterializerScreen extends AbstractContainerScreen<MaterializerMenu> {
    public static final Component TITLE = Component.translatable("gui.esoteric_circuitry.materializer_screen");

    private final BlockPos position;
    private final int imageWidth, imageHeight;
    private int leftPos, topPos;
    private MaterializerEntity materializerEntity;
    private static final ResourceLocation TEXTURE = new ResourceLocation(EsotericCircuitry.MOD_ID,
            "textures/gui/materializer_screen.png");

    public MaterializerScreen(BlockPos pos, MaterializerMenu menu, Inventory inventory) {
        super(menu, inventory, TITLE);

        this.position = pos;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        if(this.minecraft == null) return;

        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity BE = level.getBlockEntity(this.position);
        if (BE instanceof MaterializerEntity ME) {
            this.materializerEntity = ME;
        } else {
            EsotericCircuitry.LOGGER.info("BlockEntity at " + this.position + "is not of type MaterializerEntity!\n");
            return;
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.drawString(this.font, TITLE, this.leftPos+8, this.topPos+8, 0x404040, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
