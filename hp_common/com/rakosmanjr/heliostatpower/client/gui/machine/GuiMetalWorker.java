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
import org.lwjgl.util.Point;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import com.rakosmanjr.heliostatpower.gui.machine.ContainerMetalWorker;
import com.rakosmanjr.heliostatpower.lib.NameMaps;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiMetalWorker extends GuiContainer
{
	private TileBasicMetalWorker basicMetalWorker;
	private Point nameDrawPoint;
	private Point statusDrawPoint;
	
	public GuiMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		super(new ContainerMetalWorker(inventoryPlayer, basicMetalWorker));
		
		this.basicMetalWorker = basicMetalWorker;
		
		ySize = XMLLocations.MW_READER.GetAttributeFromNodeInt("gui", "height");
		xSize = XMLLocations.MW_READER.GetAttributeFromNodeInt("gui", "width");
		
		nameDrawPoint = new Point(
				XMLLocations.MW_READER.GetAttributeFromNodeInt("name", "x"),
				XMLLocations.MW_READER.GetAttributeFromNodeInt("name", "y"));
		statusDrawPoint = new Point(
				XMLLocations.MW_READER.GetAttributeFromNodeInt("status", "x"),
				XMLLocations.MW_READER.GetAttributeFromNodeInt("status", "y"));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		String containerName = basicMetalWorker.isInvNameLocalized() ? basicMetalWorker
				.getInvName() : LanguageRegistry.instance()
				.getStringLocalization(basicMetalWorker.getInvName());
		fontRenderer.drawString(containerName, nameDrawPoint.getX(),
				nameDrawPoint.getY(), 4210752);
		
		String currentStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicMetalWorker
								.GetStatus()));
		fontRenderer.drawString(currentStatus, statusDrawPoint.getX(),
				statusDrawPoint.getY(), 4210752);
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
