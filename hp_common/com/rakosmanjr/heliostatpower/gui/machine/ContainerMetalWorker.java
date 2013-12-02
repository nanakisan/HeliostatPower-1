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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import org.lwjgl.util.Point;
import org.w3c.dom.NodeList;

import com.rakosmanjr.heliostatpower.lib.XMLLocations;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

public class ContainerMetalWorker extends Container
{
	private final TileBasicMetalWorker tileMetalWorker;
	
	private int nextSlot;
	
	public ContainerMetalWorker(InventoryPlayer inventoryPlayer,
			TileBasicMetalWorker basicMetalWorker)
	{
		this.tileMetalWorker = basicMetalWorker;
		
		AddMillerSlots();
		AddDrawerSlots();
		AddPlayerInventory(inventoryPlayer, 8, 139);
	}
	
	private void AddMillerSlots()
	{
		AddCraftingGrid(3, 3, 26, 14);
		addSlotToContainer(new Slot(tileMetalWorker, nextSlot, 130, 32));
		nextSlot++;
	}
	
	private void AddDrawerSlots()
	{
		AddCraftingGrid(3, 1, 26, 95);
		addSlotToContainer(new Slot(tileMetalWorker, nextSlot,
				130, 95));
		nextSlot++;
	}
	
	private void AddCraftingGrid(int width, int height, int x, int y)
	{
		for (int craftingRowIndex = 0; craftingRowIndex < height; ++craftingRowIndex)
		{
			for (int craftingColumnIndex = 0; craftingColumnIndex < width; ++craftingColumnIndex)
			{
				addSlotToContainer(new Slot(tileMetalWorker, nextSlot, x
						+ craftingColumnIndex * 18, y + craftingRowIndex * 18));
				nextSlot++;
			}
		}
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
					actionBarSlotIndex, 8 + actionBarSlotIndex * 18, y + 58));
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
			else if (tileMetalWorker.isItemValidForSlot(15, itemStack))
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
