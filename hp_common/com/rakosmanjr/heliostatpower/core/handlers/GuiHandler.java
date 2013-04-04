/**
 * HeliostatPower
 *
 * @file GuiHandler.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.handlers;

import com.rakosmanjr.heliostatpower.client.gui.machine.GuiBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.gui.machine.ContainerBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.lib.GuiIds;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int Id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		switch (Id)
		{
			case GuiIds.BASIC_IONIC_COMPRESSOR_ID:
				TileBasicIonicCompressor tileEntity = (TileBasicIonicCompressor)world.getBlockTileEntity(x, y, z);
				return new ContainerBasicIonicCompressor(player.inventory, tileEntity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int Id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		switch (Id)
		{
			case GuiIds.BASIC_IONIC_COMPRESSOR_ID:
				TileBasicIonicCompressor tileEntity = (TileBasicIonicCompressor)world.getBlockTileEntity(x, y, z);
				return new GuiBasicIonicCompressor(player.inventory, tileEntity);
		}
		
		return null;
	}
}
