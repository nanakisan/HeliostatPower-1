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

import com.rakosmanjr.heliostatpower.gui.machine.ContainerMetalWorker;
import com.rakosmanjr.heliostatpower.lib.NameMaps;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

public class GuiMetalWorker extends GuiContainer
{
	private final TileBasicMetalWorker basicMetalWorker;
	
	private static final Rectangle baseArrow;
	private static final Rectangle millerArrow;
	private static final Rectangle drawerArrow;
	
	static
	{
		
		baseArrow = new Rectangle(176, 0, 32, 15);
		millerArrow = new Rectangle(86, 33, 32, 15);
		drawerArrow = new Rectangle(86, 96, 32, 15);
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
		String containerName = basicMetalWorker.getInventoryName();
		this.fontRendererObj.drawString(containerName, 5, 5, 4210752);
		
		// Draw the machines status'
		String millerStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicMetalWorker.miller
								.GetStatus()));
		String drawerStatus = LanguageRegistry.instance()
				.getStringLocalization(
						NameMaps.STATUS_NAMEMAP.get(basicMetalWorker.drawer
								.GetStatus()));

		this.fontRendererObj.drawString(millerStatus, 95, 58, 4210752);
		this.fontRendererObj.drawString(drawerStatus, 95, 121, 4210752);
		
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
