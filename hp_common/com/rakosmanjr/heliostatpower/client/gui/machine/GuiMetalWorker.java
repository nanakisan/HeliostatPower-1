/**
 * HeliostatPower
 *
 * @file ContainerBasicMetalWorker.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.client.gui.machine;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import com.rakosmanjr.heliostatpower.gui.machine.ContainerBasicMetalWorker;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

public class GuiMetalWorker extends GuiContainer
{
	private TileBasicMetalWorker basicMetalWorker;
	
	public GuiMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		super(new ContainerBasicMetalWorker(inventoryPlayer, basicMetalWorker));
		ySize = 176;
		this.basicMetalWorker = basicMetalWorker;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(Textures.GUI_METAL_WORKER_TEXTURE);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
