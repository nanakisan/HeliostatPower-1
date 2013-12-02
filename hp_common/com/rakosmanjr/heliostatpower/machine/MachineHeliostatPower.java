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

import com.rakosmanjr.heliostatpower.items.crafting.ICrafting;
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
	
	protected int outputSlot;
	
	protected int recipeId;
	protected int totalTickCount;
	protected int maxTickCount;
	protected ItemStack result;
	protected Status status;
	
	protected int maxEnergy;
	protected double storedEnergy;
	
	protected List<ItemStack> inventory;
	
	protected ICrafting crafter;
	
	/**
	 * @param gridWidth
	 *            Width of the crafting grid
	 * @param gridHeight
	 *            Height of the crafting grid
	 * @param additionalSlots
	 *            Amount of slots in addition to the crafting grid
	 */
	public MachineHeliostatPower(int gridWidth, int gridHeight,
			int additionalSlots, List<ItemStack> inventory, ICrafting crafter)
	{
		this.GRID_WIDTH = gridWidth;
		this.GRID_HEIGHT = gridHeight;
		this.GRID_TOTAL = gridHeight * gridHeight;
		this.TOTAL_SLOTS = GRID_TOTAL + additionalSlots;
		this.inventory = inventory;
		this.crafter = crafter;
	}
	
	/**
	 * Call to update the machine More than one call a tick can throw the
	 * machine off
	 */
	public void UpdateMachine()
	{
		if (craftingGridChanged)
		{
			validRecipe = CanProcess();
		}
		
		if (validRecipe || inCycle)
		{
			if (inCycle)
			{
				ContinueProcessingCycle();
			}
			else
			{
				StartProcessingCycle();
			}
		}
		else
		{
			status = Status.WaitingForRecipe;
		}
	}
	
	/**
	 * Checks if the machine can process what's in the crafting grid
	 * 
	 * @return True if it can process
	 */
	protected boolean CanProcess()
	{
		recipeId = crafter.GetRecipeId(inventory);
		
		return !(recipeId < 0);
	}
	
	/**
	 * Starts a processing job If called during a job, that job could be
	 * canceled
	 */
	protected void StartProcessingCycle()
	{
		if (recipeId < 0)
		{
			validRecipe = false;
			return;
		}
		
		totalTickCount = 0;
		maxTickCount = crafter.GetMaxTick(recipeId);
		result = crafter.GetResult(recipeId);
		
		inCycle = true;
		
		for (int x = 0; x < GRID_WIDTH; x++)
		{
			for (int y = 0; y < GRID_HEIGHT; y++)
			{
				int slot = x * GRID_HEIGHT + y;
				int count = crafter.ComponentsUsedInSlot(recipeId, slot);
				
				if (count == -1)
				{
					continue;
				}
				
				DecrementStackAt(slot, count);
			}
		}
	}
	
	/**
	 * Continues a processing job
	 */
	protected void ContinueProcessingCycle()
	{
		if (totalTickCount >= maxTickCount)
		{
			EndProcessingCycle();
		}
		else
		{
			status = Status.Processing;
			totalTickCount++;
		}
	}
	
	/**
	 * Ends a processing job nicely
	 */
	protected void EndProcessingCycle()
	{
		if (inventory.get(outputSlot) == null)
		{
			inventory.set(outputSlot, result.copy());
		}
		else if (inventory.get(outputSlot).isItemEqual(result)
				&& inventory.get(outputSlot).stackSize + result.stackSize <= inventory
						.get(outputSlot).getMaxStackSize())
		{
			inventory.get(outputSlot).stackSize += result.stackSize;
		}
		else
		{
			status = Status.OutputFull;
			return;
		}
		
		ForceEndProcessingCycle();
	}
	
	/**
	 * Forces the current job to end
	 */
	protected void ForceEndProcessingCycle()
	{
		inCycle = false;
		totalTickCount = -1;
		maxTickCount = -1;
		result = null;
	}
	
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
	 * Gives energy to the machine
	 * 
	 * @param tophalfEnergy
	 *            Amount of energy to give to the machine
	 * @return Amount of energy not used
	 */
	public double GiveEnergy(double tophalfEnergy)
	{
		double need = (maxEnergy - storedEnergy) - tophalfEnergy;
		
		if (need < 0)
		{
			storedEnergy = maxEnergy;
			return -need;
		}
		
		storedEnergy = maxEnergy - need;
		return 0;
	}
	
	/**
	 * Returns the max stored energy
	 */
	public int GetMaxEnergy()
	{
		return maxEnergy;
	}
	
	/**
	 * Returns the stored energy between 0 and total
	 * 
	 * @param total
	 *            What number to normalize the stored energy too
	 */
	public double GetNormalizedStoredEnergy(int total)
	{
		return (storedEnergy * total) / maxEnergy;
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
