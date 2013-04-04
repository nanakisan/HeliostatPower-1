/**
 * HeliostatPower
 *
 * @file ContainerBasicIonicCompressor.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.gui.machine;

import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBasicIonicCompressor extends Container
{
	public ContainerBasicIonicCompressor(InventoryPlayer inventoryPlayer,
			TileBasicIonicCompressor basicIonicCompressor)
	{
		// Add crafting grid
		// Start: (12, 20)
		for (int craftingRowIndex = 0; craftingRowIndex < 3; ++craftingRowIndex)
		{
			for (int craftingColumnIndex = 0; craftingColumnIndex < 5; ++craftingColumnIndex)
			{
				addSlotToContainer(new Slot(basicIonicCompressor, craftingColumnIndex
						+ craftingRowIndex * 5,
						12 + craftingColumnIndex * 18,
						20 + craftingRowIndex * 18));
			}
		}
		
		// Add sodium nitrate slot
		addSlotToContainer(new Slot(basicIonicCompressor, 15, 111, 11));
		// Add output slot
		addSlotToContainer(new Slot(basicIonicCompressor, 16, 144, 38));
		
		AddPlayerInventory(inventoryPlayer);
	}
	
	private void AddPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
		{
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
			{
				addSlotToContainer(new Slot(inventoryPlayer,
						inventoryColumnIndex + inventoryRowIndex * 9 + 9,
						8 + inventoryColumnIndex * 18,
						84 + inventoryRowIndex * 18));
			}
		}
		
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer,
					actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}
