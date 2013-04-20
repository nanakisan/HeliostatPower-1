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

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.machine.MachineDrawer;
import com.rakosmanjr.heliostatpower.machine.MachineMiller;

public class TileBasicMetalWorker extends TileHeliostat implements
		ISidedInventory
{
	public final MachineMiller miller;
	public final MachineDrawer drawer;
	
	private final ArrayList<ItemStack> inventory;
	
	public TileBasicMetalWorker()
	{
		inventory = new ArrayList<ItemStack>(Arrays.asList(new ItemStack[14]));
		miller = new MachineMiller(inventory.subList(0, 10));
		drawer = new MachineDrawer(inventory.subList(10, 14));
		
		maxEnergy = miller.GetMaxEnergy() + drawer.GetMaxEnergy();
		
		SetCustomName(Strings.TE_METAL_WORKER);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		int tophalfEnergy = storedEnergy / 2;
		storedEnergy -= tophalfEnergy;
		int extra = miller.GiveEnergy(tophalfEnergy);
		storedEnergy = drawer.GiveEnergy(storedEnergy + extra);
		
		miller.UpdateMachine();
		drawer.UpdateMachine();
	}
	
	@Override
	public int getSizeInventory()
	{
		return miller.GetInventorySize() + drawer.GetInventorySize();
	}
	
	@Override
	public ItemStack getStackInSlot(int i)
	{
		if (i >= 0 && i < getSizeInventory())
		{
			return inventory.get(i);
			// return GetInventory(i);
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
		inventory.set(i, itemStack);
		// SetInventory(i, itemStack);
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
