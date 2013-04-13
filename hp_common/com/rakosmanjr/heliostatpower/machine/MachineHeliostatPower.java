/**
 * HeliostatPower
 *
 * @file MachineHeliostatPower.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.machine;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.rakosmanjr.heliostatpower.tileentity.Status;

/**
 * Base class for a machine that does processing
 */
public abstract class MachineHeliostatPower
{
	protected boolean craftingGridChanged;
	protected boolean validRecipe;
	protected boolean inCycle;
	
	protected final int GRID_WIDTH;
	protected final int GRID_HEIGHT;
	protected final int GRID_TOTAL;
	protected final int TOTAL_SLOTS;
	
	protected int recipeId;
	protected int totalTickCount;
	protected int maxTickCount;
	protected ItemStack result;
	protected Status status;
	
	protected List<ItemStack> inventory;
	
	/**
	 * @param gridWidth
	 *            Width of the crafting grid
	 * @param gridHeight
	 *            Height of the crafting grid
	 * @param additionalSlots
	 *            Amount of slots in addition to the crafting grid
	 */
	public MachineHeliostatPower(int gridWidth, int gridHeight,
			int additionalSlots, List<ItemStack> inventory)
	{
		this.GRID_WIDTH = gridWidth;
		this.GRID_HEIGHT = gridHeight;
		this.GRID_TOTAL = gridHeight * gridHeight;
		this.TOTAL_SLOTS = GRID_TOTAL + additionalSlots;
		this.inventory = inventory;
		// this.inventory = new ItemStack[TOTAL_SLOTS];
	}
	
	/**
	 * Call to update the machine More than one call a tick can throw the
	 * machine off
	 */
	public abstract void UpdateMachine();
	
	/**
	 * Checks if the machine can process what's in the crafting grid
	 * 
	 * @return True if it can process
	 */
	protected abstract boolean CanProcess();
	
	/**
	 * Starts a processing job If called during a job, that job could be
	 * canceled
	 */
	protected abstract void StartProcessingCycle();
	
	/**
	 * Continues a processing job
	 */
	protected abstract void ContinueProcessingCycle();
	
	/**
	 * Ends a processing job nicely
	 */
	protected abstract void EndProcessingCycle();
	
	/**
	 * Forces the current job to end
	 */
	protected abstract void ForceEndProcessingCycle();
	
	/**
	 * @return Returns the status of the machine
	 */
	public Status GetStatus()
	{
		return status;
	}
	
	/**
	 * @return True if the machine is processing
	 */
	public boolean IsMachineProcessing()
	{
		return inCycle;
	}
	
	/**
	 * Returns the progress between 0 and total
	 * 
	 * @param total
	 *            What number to normalize the progress too
	 */
	public int GetNormalizedProgress(int total)
	{
		if (!IsMachineProcessing())
		{
			return 0;
		}
		
		return (totalTickCount * total) / maxTickCount;
	}
	
	/**
	 * Gets the size of the internal inventory
	 */
	public int GetInventorySize()
	{
		return inventory.size();
	}
	
	/**
	 * Gets the stack at the specified slot
	 * 
	 * @param slot
	 *            Slot number for the machine's inventory
	 * @return Returns the ItemStack at (slot + OFFSET) in the containers
	 *         inventory
	 */
	public ItemStack GetStackAt(int slot)
	{
		if (slot >= 0 && slot < inventory.size())
		{
			return inventory.get(slot);
		}
		
		return null;
	}
	
	/**
	 * Adds or removes items from an itemStack in the inventory
	 * 
	 * @param slot
	 *            Slot number for the machine's inventory
	 * @param amount
	 *            Positive or negative value to inc/dec the stack by
	 */
	public void DecrementStackAt(int slot, int amount)
	{
		if (slot >= 0 && slot < inventory.size())
		{
			if (inventory.get(slot) != null)
			{
				inventory.get(slot).stackSize -= amount;
				
				if (inventory.get(slot).stackSize > inventory.get(slot)
						.getMaxStackSize())
				{
					inventory.get(slot).stackSize = inventory.get(slot)
							.getMaxStackSize();
				}
				else if (inventory.get(slot).stackSize <= 0)
				{
					inventory.set(slot, null);
				}
			}
		}
		
		if (slot < GRID_TOTAL)
		{
			craftingGridChanged = true;
		}
	}
	
	/**
	 * Sets the specified stack to the given itemStack
	 * 
	 * @param slot
	 *            Slot number for the machine's inventory
	 * @param itemstack
	 *            The itemStack to set
	 * @param copy
	 *            True if itemStack should be copied by value, false if by
	 *            reference
	 */
	public void SetStackAt(int slot, ItemStack itemstack, boolean copy)
	{
		if (slot >= 0 && slot < inventory.size())
		{
			if (copy && itemstack != null)
			{
				inventory.set(slot, itemstack.copy());
			}
			else
			{
				inventory.set(slot, itemstack);
			}
		}
		
		if (slot < GRID_TOTAL)
		{
			craftingGridChanged = true;
		}
		
		if (inventory.get(slot) == null)
		{
			
		}
		else if (inventory.get(slot).stackSize < 1)
		{
			inventory.set(slot, null);
		}
		else if (inventory.get(slot).stackSize > inventory.get(slot)
				.getMaxStackSize())
		{
			inventory.get(slot).stackSize = inventory.get(slot)
					.getMaxStackSize();
		}
	}
}
