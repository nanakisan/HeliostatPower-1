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

import java.util.Hashtable;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.rakosmanjr.heliostatpower.gui.machine.ContainerBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.tileentity.Status;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiBasicIonicCompressor extends GuiContainer
{
	private TileBasicIonicCompressor basicIonicCompressor;
	private static Map<Status, String> statusNameMap;
	
	public GuiBasicIonicCompressor(InventoryPlayer inventoryPlayer,
			TileBasicIonicCompressor basicIonicCompressor)
	{
		super(new ContainerBasicIonicCompressor(inventoryPlayer, basicIonicCompressor));
		ySize = 176;
		this.basicIonicCompressor = basicIonicCompressor;
		
		statusNameMap = new Hashtable<Status, String>();
		statusNameMap.put(Status.WaitingForRecipe, "status.waitingForRecipe");
		statusNameMap.put(Status.WaitingForFuel, "status.waitingForFuel");
		statusNameMap.put(Status.Processing, "status.processing");
		statusNameMap.put(Status.OutputFull, "status.outputFull");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		// draw name at (11, 6)
		String containerName = basicIonicCompressor.isInvNameLocalized() ? basicIonicCompressor.getInvName() : LanguageRegistry.instance().getStringLocalization(basicIonicCompressor.getInvName());
		fontRenderer.drawString(containerName, 11, 6, 4210752);
		
		// draw status at (108, 64)
		String currentStatus = LanguageRegistry.instance().getStringLocalization(statusNameMap.get(basicIonicCompressor.GetStatus()));
		fontRenderer.drawString(currentStatus, 108, 64, 4210752);
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
