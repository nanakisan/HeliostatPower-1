/**
 * HeliostatPower
 *
 * @file MachineDrawer.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.machine;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.rakosmanjr.heliostatpower.items.crafting.CraftingDrawer;
import com.rakosmanjr.heliostatpower.tileentity.Status;

public class MachineDrawer extends MachineHeliostatPower
{
	// Last slot in the inventory
	private final int OUTPUT_SLOT;
	
	public MachineDrawer(List<ItemStack> inventory)
	{
		super(3, 1, 1, inventory);
		
		OUTPUT_SLOT = TOTAL_SLOTS - 1;
		craftingGridChanged = true;
		validRecipe = false;
		inCycle = false;
	}
	
	@Override
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
	
	@Override
	protected boolean CanProcess()
	{
		recipeId = CraftingDrawer.Instance().GetRecipeId(inventory);
		
		return !(recipeId < 0);
	}
	
	@Override
	protected void StartProcessingCycle()
	{
		if (recipeId < 0)
		{
			validRecipe = false;
			return;
		}
		
		totalTickCount = 0;
		maxTickCount = CraftingDrawer.Instance().GetMaxTick(recipeId);
		result = CraftingDrawer.Instance().GetResult(recipeId);
		
		inCycle = true;
		
		for (int x = 0; x < GRID_WIDTH; x++)
		{
			for (int y = 0; y < GRID_HEIGHT; y++)
			{
				int slot = x * GRID_HEIGHT + y;
				int count = CraftingDrawer.Instance().ComponentsUsedInSlot(
						recipeId, slot);
				
				if (count == -1)
				{
					continue;
				}
				
				DecrementStackAt(slot, count);
			}
		}
	}
	
	@Override
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
	
	@Override
	protected void EndProcessingCycle()
	{
		if (inventory.get(OUTPUT_SLOT) == null)
		{
			inventory.set(OUTPUT_SLOT, result.copy());
		}
		else if (inventory.get(OUTPUT_SLOT).isItemEqual(result)
				&& inventory.get(OUTPUT_SLOT).stackSize + result.stackSize <= inventory
						.get(OUTPUT_SLOT).getMaxStackSize())
		{
			inventory.get(OUTPUT_SLOT).stackSize += result.stackSize;
		}
		else
		{
			status = Status.OutputFull;
			return;
		}
		
		ForceEndProcessingCycle();
	}
	
	@Override
	protected void ForceEndProcessingCycle()
	{
		inCycle = false;
		totalTickCount = -1;
		maxTickCount = -1;
		result = null;
	}
	
	@Override
	public Status GetStatus()
	{
		return status;
	}
}
