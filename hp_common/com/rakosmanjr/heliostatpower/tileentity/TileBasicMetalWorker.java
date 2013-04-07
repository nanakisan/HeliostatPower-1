/**
 * HeliostatPower
 *
 * @file TileBasicMetalWorker.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.tileentity;

import com.rakosmanjr.heliostatpower.items.crafting.CraftingIonicCompressor;
import com.rakosmanjr.heliostatpower.items.crafting.CraftingMetalWorker;
import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileBasicMetalWorker extends TileHeliostat implements
		ISidedInventory
{
	private ItemStack[] inventory;
	
	private boolean craftingGridChanged;
	private boolean validRecipe;
	private boolean inCycle;
	
	private final int gridWidth = CraftingMetalWorker.GRID_WIDTH;
	private final int gridHeight = CraftingMetalWorker.GRID_HEIGHT;
	private final int gridTotal = CraftingMetalWorker.GRID_TOTAL;
	private final int totalSlots = gridTotal + 1;
	
	private final int OUTPUT_SLOT = 9;
	
	// Cycle stuff
	// Applies to the current cycle
	private int recipeId; // recipeId for info lookup
	private int totalTickCount; // total ticks passed during cycle
	private int maxTickCount; // max ticks needed for cycle
	private ItemStack result; // result of the recipe
	private Status status; // status of the machine
	
	public TileBasicMetalWorker()
	{
		inventory = new ItemStack[totalSlots];
		
		craftingGridChanged = true;
		validRecipe = false;
		inCycle = false;
		
		SetCustomName(Strings.TE_METAL_WORKER);
	}
	
	public void updateEntity()
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
	
	private boolean CanProcess()
	{
		recipeId = CraftingMetalWorker.Instance().GetRecipeId(inventory);
		
		return recipeId < 0 ? false : true;
	}
	
	private void StartProcessingCycle()
	{
		if (recipeId < 0)
		{
			validRecipe = false;
			return;
		}
		
		totalTickCount = 0;
		maxTickCount = CraftingMetalWorker.Instance().GetMaxTick(recipeId);
		result = CraftingMetalWorker.Instance().GetResult(recipeId);
		
		inCycle = true;
		
		for (int x = 0; x < gridWidth; x++)
		{
			for (int y = 0; y < gridHeight; y++)
			{
				int slot = x * gridHeight + y;
				int count = CraftingMetalWorker.Instance()
						.ComponentsUsedInSlot(recipeId, slot);
				
				if (count == -1)
				{
					continue;
				}
				
				decrStackSize(slot, count);
			}
		}
	}
	
	private void ContinueProcessingCycle()
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
	
	private void EndProcessingCycle()
	{
		if (inventory[OUTPUT_SLOT] == null)
		{
			inventory[OUTPUT_SLOT] = result.copy();
		}
		else if (inventory[OUTPUT_SLOT].isItemEqual(result)
				&& inventory[OUTPUT_SLOT].stackSize + result.stackSize <= inventory[OUTPUT_SLOT]
						.getMaxStackSize())
		{
			inventory[OUTPUT_SLOT].stackSize += result.stackSize;
		}
		else
		{
			status = Status.OutputFull;
			return;
		}
		
		ForceEndProcessingCycle();
	}
	
	private void ForceEndProcessingCycle()
	{
		inCycle = false;
		totalTickCount = -1;
		maxTickCount = -1;
		result = null;
	}
	
	public Status GetStatus()
	{
		return status;
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i)
	{	
		if (i >= 0 && i < inventory.length)
		{
			return inventory[i];
		}
		
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int i, int amount)
	{
		ItemStack itemStack = getStackInSlot(i);
		
		if (itemStack != null)
		{
			if (itemStack.stackSize <= amount)
			{
				setInventorySlotContents(i, null);
			}
			else
			{
				itemStack = itemStack.splitStack(amount);
				
				if (itemStack.stackSize == 0)
				{
					setInventorySlotContents(i, null);
				}
			}
		}
		
		return itemStack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		ItemStack itemStack = getStackInSlot(i);
		
		if (itemStack != null)
		{
			setInventorySlotContents(i, null);
		}
		
		return itemStack;
	}
	
	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack)
	{
		inventory[i] = itemStack;
		
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
		{
			itemStack.stackSize = getInventoryStackLimit();
		}
		
		if (i < gridTotal)
		{
			craftingGridChanged = true;
		}
	}
	
	@Override
	public String getInvName()
	{
		return GetCustomName();
	}
	
	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
				&& entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;
	}
	
	@Override
	public void openChest()
	{
		
	}
	
	@Override
	public void closeChest()
	{
		
	}
	
	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		if (i == OUTPUT_SLOT)
		{
			if (Reference.DEBUG)
			{
				return true;
			}
		}
		else
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public int[] getSizeInventorySide(int var1)
	{
		return null;
	}
	
	@Override
	public boolean func_102007_a(int i, ItemStack itemstack, int j)
	{
		return false;
	}
	
	@Override
	public boolean func_102008_b(int i, ItemStack itemstack, int j)
	{
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
	}
}
