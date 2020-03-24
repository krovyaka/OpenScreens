package krovyaka.openutils.client.gui;

import krovyaka.openutils.OpenUtils;
import krovyaka.openutils.common.container.ContainerSecureChest;
import krovyaka.openutils.common.tileentity.TileEntitySecureChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

import java.awt.*;

/**
 * Created by Toby on 27/06/2017.
 */
public class GuiTutorialContainer extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(OpenUtils.MODID, "textures/gui/container.png");

    public GuiTutorialContainer(InventoryPlayer player, TileEntitySecureChest tileEntitySecureChest) {
        super(new ContainerSecureChest(player, tileEntitySecureChest));
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(new TextComponentTranslation("tile.secure_chest.name").getFormattedText(), 5, 5, Color.darkGray.getRGB());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
