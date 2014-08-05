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

import com.rakosmanjr.heliostatpower.client.gui.machine.GuiIonicCompressor;
import com.rakosmanjr.heliostatpower.client.gui.machine.GuiMetalWorker;
import com.rakosmanjr.heliostatpower.gui.machine.ContainerIonicCompressor;
import com.rakosmanjr.heliostatpower.gui.machine.ContainerMetalWorker;
import com.rakosmanjr.heliostatpower.lib.GuiIds;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

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
			case GuiIds.IONIC_COMPRESSOR_ID:
				TileBasicIonicCompressor tileBasicIonicCompressor = (TileBasicIonicCompressor)world.getTileEntity(x, y, z);
				return new ContainerIonicCompressor(player.inventory, tileBasicIonicCompressor);
			
			case GuiIds.METAL_WORKER_ID:
				TileBasicMetalWorker tileBasicMetalWorker = (TileBasicMetalWorker)world.getTileEntity(x, y, z);
				return new ContainerMetalWorker(player.inventory, tileBasicMetalWorker);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int Id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		switch (Id)
		{
			case GuiIds.IONIC_COMPRESSOR_ID:
				TileBasicIonicCompressor tileEntity = (TileBasicIonicCompressor)world.getTileEntity(x, y, z);
				return new GuiIonicCompressor(player.inventory, tileEntity);
				
			case GuiIds.METAL_WORKER_ID:
				TileBasicMetalWorker tileBasicMetalWorker = (TileBasicMetalWorker)world.getTileEntity(x, y, z);
				return new GuiMetalWorker(player.inventory, tileBasicMetalWorker);
		}
		
		return null;
	}
}
