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

import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.machine.MachineDrawer;
import com.rakosmanjr.heliostatpower.machine.MachineMiller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;

public class TileBasicMetalWorker extends RFPoweredHelioStatTile implements
		ISidedInventory
{
	public final MachineMiller miller;
	public final MachineDrawer drawer;
	
	private final ArrayList<ItemStack> inventory;
	
	public TileBasicMetalWorker()
	{
		super(32000);
		inventory = new ArrayList<ItemStack>(Arrays.asList(new ItemStack[14]));
		miller = new MachineMiller(inventory.subList(0, 10));
		drawer = new MachineDrawer(inventory.subList(10, 14));

		this.setCustomName(Strings.TE_METAL_WORKER);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		double tophalfEnergy = storedEnergy / 2;
		storedEnergy -= tophalfEnergy;
		double extra = miller.GiveEnergy(tophalfEnergy);
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
	public String getInventoryName()
	{
		return this.getCustomName();
	}

	@Override
	public boolean hasCustomInventoryName()
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
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
				&& entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

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

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}
}
