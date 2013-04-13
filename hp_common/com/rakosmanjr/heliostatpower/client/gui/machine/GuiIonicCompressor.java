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
import org.lwjgl.util.Point;
import org.w3c.dom.NodeList;

import com.rakosmanjr.heliostatpower.core.helpers.XMLReader;
import com.rakosmanjr.heliostatpower.gui.machine.ContainerIonicCompressor;
import com.rakosmanjr.heliostatpower.lib.NameMaps;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiIonicCompressor extends GuiContainer
{
	private TileBasicIonicCompressor basicIonicCompressor;
	private Point nameDrawPoint;
	private Point statusDrawPoint;
	
	public GuiIonicCompressor(InventoryPlayer inventoryPlayer,
			TileBasicIonicCompressor basicIonicCompressor)
	{
		super(new ContainerIonicCompressor(inventoryPlayer,
				basicIonicCompressor));
		
		this.basicIonicCompressor = basicIonicCompressor;
		
		NodeList textNodes = XMLLocations.IC_READER.GetNodes("text");
		
		ySize = XMLLocations.IC_READER.GetBaseAttributeInt("height");
		xSize = XMLLocations.IC_READER.GetBaseAttributeInt("width");
		
		nameDrawPoint = new Point(
				XMLLocations.IC_READER.GetNodeAttributeInt("name", "x", textNodes),
				XMLLocations.IC_READER.GetNodeAttributeInt("name", "y", textNodes));
		statusDrawPoint = new Point(
				XMLLocations.IC_READER.GetNodeAttributeInt("status", "x", textNodes),
				XMLLocations.IC_READER.GetNodeAttributeInt("status", "y", textNodes));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		// draw name at (11, 6)
		String containerName = basicIonicCompressor.isInvNameLocalized() ? basicIonicCompressor
				.getInvName() : LanguageRegistry.instance()
				.getStringLocalization(basicIonicCompressor.getInvName());
		fontRenderer.drawString(containerName, nameDrawPoint.getX(),
				nameDrawPoint.getY(), 4210752);
		
		// draw status at (104, 64)
		String currentStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicIonicCompressor
								.GetStatus()));
		fontRenderer.drawString(currentStatus, statusDrawPoint.getX(),
				statusDrawPoint.getY(), 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(Textures.GUI_IONIC_COMPRESSOR_TEXTURE);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
