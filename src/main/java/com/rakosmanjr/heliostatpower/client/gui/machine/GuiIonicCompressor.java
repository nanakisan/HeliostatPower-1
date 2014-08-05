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

import com.rakosmanjr.heliostatpower.gui.machine.ContainerIonicCompressor;
import com.rakosmanjr.heliostatpower.lib.NameMaps;
import com.rakosmanjr.heliostatpower.lib.Textures;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiIonicCompressor extends GuiContainer
{
	private TileBasicIonicCompressor basicIonicCompressor;

	public GuiIonicCompressor(InventoryPlayer inventoryPlayer, TileBasicIonicCompressor basicIonicCompressor)
	{
		super(new ContainerIonicCompressor(inventoryPlayer, basicIonicCompressor));

		this.basicIonicCompressor = basicIonicCompressor;

		ySize = 166;
		xSize = 176;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		// draw name at (11, 6)
		String containerName = basicIonicCompressor.getInventoryName();
		this.fontRendererObj.drawString(containerName, 11, 6, 4210752);

		// draw status at (104, 64)
		String currentStatus = LanguageRegistry.instance().getStringLocalization(NameMaps.STATUS_NAMEMAP.get(basicIonicCompressor.GetStatus()));
		this.fontRendererObj.drawString(currentStatus, 104, 64, 4210752);
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
