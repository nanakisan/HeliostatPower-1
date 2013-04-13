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

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.w3c.dom.NodeList;

import com.rakosmanjr.heliostatpower.gui.machine.ContainerMetalWorker;
import com.rakosmanjr.heliostatpower.lib.NameMaps;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiMetalWorker extends GuiContainer
{
	private final TileBasicMetalWorker basicMetalWorker;
	private static final Point nameDrawPoint;
	private static final Point millerStatusDrawPoint;
	private static final Point drawerStatusDrawPoint;
	
	private static final Rectangle baseArrow;
	private static final Rectangle millerArrow;
	private static final Rectangle drawerArrow;
	
	public static final NodeList textNodes;
	public static final NodeList arrowNodes;
	
	static
	{
		textNodes = XMLLocations.MW_READER.GetNodes("text");
		arrowNodes = XMLLocations.MW_READER.GetNodes("arrow");
		
		nameDrawPoint = new Point(XMLLocations.MW_READER.GetNodeAttributeInt(
				"name", "x", textNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("name", "y",
						textNodes));
		
		millerStatusDrawPoint = new Point(
				XMLLocations.MW_READER.GetNodeAttributeInt("millerstatus", "x",
						textNodes), XMLLocations.MW_READER.GetNodeAttributeInt(
						"millerstatus", "y", textNodes));
		drawerStatusDrawPoint = new Point(
				XMLLocations.MW_READER.GetNodeAttributeInt("drawerstatus", "x",
						textNodes), XMLLocations.MW_READER.GetNodeAttributeInt(
						"drawerstatus", "y", textNodes));
		
		baseArrow = new Rectangle(XMLLocations.MW_READER.GetNodeAttributeInt(
				"texture", "x", arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("texture", "y",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("texture", "width",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("texture", "height",
						arrowNodes));
		millerArrow = new Rectangle(XMLLocations.MW_READER.GetNodeAttributeInt(
				"miller", "x", arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("miller", "y",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("miller", "width",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("miller", "height",
						arrowNodes));
		drawerArrow = new Rectangle(XMLLocations.MW_READER.GetNodeAttributeInt(
				"drawer", "x", arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("drawer", "y",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("drawer", "width",
						arrowNodes),
				XMLLocations.MW_READER.GetNodeAttributeInt("drawer", "height",
						arrowNodes));
	}
	
	public GuiMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		super(new ContainerMetalWorker(inventoryPlayer, basicMetalWorker));
		
		this.basicMetalWorker = basicMetalWorker;
		
		ySize = XMLLocations.MW_READER.GetBaseAttributeInt("height");
		xSize = XMLLocations.MW_READER.GetBaseAttributeInt("width");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		// Draw container name
		String containerName = basicMetalWorker.isInvNameLocalized() ? basicMetalWorker
				.getInvName() : LanguageRegistry.instance()
				.getStringLocalization(basicMetalWorker.getInvName());
		fontRenderer.drawString(containerName, nameDrawPoint.getX(),
				nameDrawPoint.getY(), 4210752);
		
		// Draw the machines status'
		String millerStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicMetalWorker.miller
								.GetStatus()));
		String drawerStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicMetalWorker.drawer
								.GetStatus()));
		
		fontRenderer.drawString(millerStatus, millerStatusDrawPoint.getX(),
				millerStatusDrawPoint.getY(), 4210752);
		fontRenderer.drawString(drawerStatus, drawerStatusDrawPoint.getX(),
				drawerStatusDrawPoint.getY(), 4210752);
		
		// Draw the arrows
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(Textures.GUI_METAL_WORKER_TEXTURE);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
		if (basicMetalWorker.miller.IsMachineProcessing())
		{
			int progress = basicMetalWorker.miller
					.GetNormalizedProgress(millerArrow.getWidth());
			
			drawTexturedModalRect(millerArrow.getX() + xStart,
					millerArrow.getY() + yStart, baseArrow.getX(),
					baseArrow.getY(), progress, baseArrow.getHeight());
		}
		
		if (basicMetalWorker.drawer.IsMachineProcessing())
		{
			int progress = basicMetalWorker.drawer
					.GetNormalizedProgress(drawerArrow.getWidth());
			
			drawTexturedModalRect(drawerArrow.getX() + xStart,
					drawerArrow.getY() + yStart, baseArrow.getX(),
					baseArrow.getY(), progress, baseArrow.getHeight());
		}
	}
}
