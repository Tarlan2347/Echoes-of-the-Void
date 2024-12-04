package net.tarlan.echoes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tarlan.echoes.Echoes;
import org.openjdk.nashorn.internal.runtime.Debug;

public class UmbrelithFurnaceScreen extends AbstractContainerScreen<UmbrelithFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Echoes.MOD_ID, "textures/gui/umbrelith_furnace.png");

    public UmbrelithFurnaceScreen(UmbrelithFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override protected void init() {
        super.init();
        this.titleLabelY = 10000;
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        this.renderBg(pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
    @Override protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        //RenderSystem.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
        //RenderSystem.setShaderTexture(0, TEXTURE);
        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

        int smeltingProgress = this.menu.getSmeltingProgress();
        pGuiGraphics.blit(TEXTURE, leftPos + 68, topPos + 31, 176, 16, smeltingProgress + 1, 18);


        int fuelProgress = this.menu.getFuelProgress();
        if (this.menu.isBurning()) {
            pGuiGraphics.blit(TEXTURE, leftPos + 34, topPos + 32 + 15 - fuelProgress, 176, 15 - fuelProgress, 12, fuelProgress + 1);
        }
    }
}
