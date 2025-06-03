package net.tarlan.echoes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tarlan.echoes.Echoes;
import org.openjdk.nashorn.internal.runtime.Debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (isHovering(34, 32, 12, 14, pMouseX, pMouseY)) {
            int burnTimeLeft = this.menu.getRemainingFueltime();
            int fuelSlotBurnTime = this.menu.getTotalFuelInSlot(); // Ensure this method is implemented
            double coalsLeft = burnTimeLeft / 1600.0;
            double coalsInSlot = fuelSlotBurnTime / 1600.0;

            // Format values: one decimal max, strip trailing .0
            String coalsLeftStr = String.format("%.1f", coalsLeft).replaceAll("\\.0$", "");
            String coalsInSlotStr = String.format("%.1f", coalsInSlot).replaceAll("\\.0$", "");

            List<Component> tooltips = new ArrayList<>();
            tooltips.add(Component.literal("Active Time Left: " + coalsLeftStr + " coal"));
            tooltips.add(Component.literal("(" + coalsInSlotStr + " stored)").withStyle(ChatFormatting.DARK_GRAY));

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), pMouseX, pMouseY);
        }

        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int leftPos = this.leftPos;
        int topPos = this.topPos;
        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);

        int smeltingProgress = this.menu.getSmeltingProgress();
        pGuiGraphics.blit(TEXTURE, leftPos + 68, topPos + 31, 176, 16, smeltingProgress + 1, 18);


        int fuelProgress = this.menu.getFuelProgress();
        if (this.menu.isBurning()) {
            pGuiGraphics.blit(TEXTURE, leftPos + 34, topPos + 32 + 15 - fuelProgress, 176, 15 - fuelProgress, 12, fuelProgress + 1);
        }
    }
    private boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= leftPos + x && mouseX <= leftPos + x + width &&
                mouseY >= topPos + y && mouseY <= topPos + y + height;
    }
}
