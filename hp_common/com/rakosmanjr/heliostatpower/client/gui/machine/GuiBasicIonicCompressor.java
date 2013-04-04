/**
 * HeliostatPower
 *
 * @file GuiBasicIonicCompressor.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.client.gui.machine;

import org.lwjgl.opengl.GL11;

import com.rakosmanjr.heliostatpower.gui.machine.ContainerBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiBasicIonicCompressor extends GuiContainer
{
	private TileBasicIonicCompressor basicIonicCompressor;
	
	public GuiBasicIonicCompressor(InventoryPlayer inventoryPlayer,
			TileBasicIonicCompressor basicIonicCompressor)
	{
		super(new ContainerBasicIonicCompressor(inventoryPlayer, basicIonicCompressor));
		ySize = 176;
		this.basicIonicCompressor = basicIonicCompressor;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		String containerName = basicIonicCompressor.isInvNameLocalized() ? basicIonicCompressor.getInvName() : StatCollector.translateToLocal(basicIonicCompressor.getInvName());
		fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_BASIC_IONIC_COMPRESSOR);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
