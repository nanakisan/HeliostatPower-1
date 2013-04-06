/**
 * HeliostatPower
 *
 * @file ContainerBasicMetalWorker.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.gui.machine;

import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerBasicMetalWorker extends Container
{
	public ContainerBasicMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return false;
	}
}
