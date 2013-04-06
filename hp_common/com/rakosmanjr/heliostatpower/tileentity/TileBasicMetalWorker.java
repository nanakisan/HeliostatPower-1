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
import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Status;
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
	
	private final int gridWidth = CraftingIonicCompressor.GRID_WIDTH;
	private final int gridHeight = CraftingIonicCompressor.GRID_HEIGHT;
	private final int gridTotal = CraftingIonicCompressor.GRID_TOTAL;
	private final int totalSlots = gridTotal + 1;
	
	private final int OUTPUT_SLOT = 16;
	
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
		
		SetCustomName(Strings.TE_BASIC_METAL_WORKER);
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
		return false;
	}
	
	private void StartProcessingCycle()
	{
		
	}
	
	private void ContinueProcessingCycle()
	{
		
	}
	
	private void EndProcessingCycle()
	{
		
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
			return inventory[0];
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
