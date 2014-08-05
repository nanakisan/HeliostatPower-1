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

import org.w3c.dom.NodeList;

import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIonicCompressor extends Container
{
	private TileBasicIonicCompressor basicIonicCompressor;
	
	//private static NodeList slotNodes = XMLLocations.IC_READER.GetNodes("slots");
	
	public ContainerIonicCompressor(InventoryPlayer inventoryPlayer,
			TileBasicIonicCompressor basicIonicCompressor)
	{
		this.basicIonicCompressor = basicIonicCompressor;
		
		// Add crafting grid
		// Start: (10, 20)
		int gridX = 10;
		int gridY = 20;
		
		for (int craftingRowIndex = 0; craftingRowIndex < 3; ++craftingRowIndex)
		{
			for (int craftingColumnIndex = 0; craftingColumnIndex < 5; ++craftingColumnIndex)
			{
				addSlotToContainer(new Slot(basicIonicCompressor,
						craftingColumnIndex + craftingRowIndex * 5, gridX
								+ craftingColumnIndex * 18, gridY
								+ craftingRowIndex * 18));
			}
		}
		
		// Add sodium nitrate slot
		addSlotToContainer(new Slot(basicIonicCompressor, 15, 109, 11));
		// Add output slot
		addSlotToContainer(new Slot(basicIonicCompressor, 16, 142, 38 ));
		
		AddPlayerInventory(inventoryPlayer, 8, 84);
	}
	
	private void AddPlayerInventory(InventoryPlayer inventoryPlayer, int x,
			int y)
	{
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
		{
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
			{
				addSlotToContainer(new Slot(inventoryPlayer,
						inventoryColumnIndex + inventoryRowIndex * 9 + 9, x
								+ inventoryColumnIndex * 18, y
								+ inventoryRowIndex * 18));
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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer,
			int slotIndex)
	{
		ItemStack newItemStack = null;
		Slot slot = (Slot)inventorySlots.get(slotIndex);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemStack = slot.getStack();
			newItemStack = itemStack.copy();
			
			if (slotIndex < 17)
			{
				if (!this.mergeItemStack(itemStack, 17, inventorySlots.size(),
						true))
					return null;
			}
			else if (basicIonicCompressor.isItemValidForSlot(15, itemStack))
			{
				if (!mergeItemStack(itemStack, 0, 16, true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemStack, 0, 17, false))
				return null;
			
			if (itemStack.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}
		
		return newItemStack;
	}
}
