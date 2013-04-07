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

import java.util.ArrayList;

import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMetalWorker extends Container
{
	TileBasicMetalWorker tileMetalWorker;
	
	public ContainerMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		this.tileMetalWorker = basicMetalWorker;
		
		// Add crafting grid
		int gridX = XMLLocations.MW_READER.GetAttributeFromNodeInt("craftgrid",
				"x");
		int gridY = XMLLocations.MW_READER.GetAttributeFromNodeInt("craftgrid",
				"y");
		
		for (int craftingRowIndex = 0; craftingRowIndex < 3; ++craftingRowIndex)
		{
			for (int craftingColumnIndex = 0; craftingColumnIndex < 3; ++craftingColumnIndex)
			{
				addSlotToContainer(new Slot(tileMetalWorker,
						craftingColumnIndex + craftingRowIndex * 3, gridX
								+ craftingColumnIndex * 18, gridY
								+ craftingRowIndex * 18));
			}
		}
		
		// Add output slot
		addSlotToContainer(new Slot(tileMetalWorker, 9,
				XMLLocations.MW_READER.GetAttributeFromNodeInt("output", "x"),
				XMLLocations.MW_READER.GetAttributeFromNodeInt("output", "y")));
		
		AddPlayerInventory(inventoryPlayer,
				XMLLocations.MW_READER
						.GetAttributeFromNodeInt("playerinv", "x"),
				XMLLocations.MW_READER
						.GetAttributeFromNodeInt("playerinv", "y"));
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
			
			if (slotIndex < 10)
			{
				if (!this.mergeItemStack(itemStack, 10, inventorySlots.size(),
						true))
					return null;
			}
			else if (tileMetalWorker.isStackValidForSlot(15, itemStack))
			{
				if (!mergeItemStack(itemStack, 0, 9, true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemStack, 0, 10, false))
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
