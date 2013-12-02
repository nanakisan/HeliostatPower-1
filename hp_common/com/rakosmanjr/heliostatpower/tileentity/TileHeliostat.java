/**
 * HeliostatPower
 *
 * @file TileHeliostat.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.tileentity;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

import com.rakosmanjr.heliostatpower.lib.NBTTags;

public class TileHeliostat extends TileEntity implements IEnergySink
{
	private ForgeDirection orientation;
	private String owner;
	private String customName;
	private boolean initiated;
	
	protected double storedEnergy;
	protected double maxEnergy;
	
	public TileHeliostat()
	{
		orientation = ForgeDirection.SOUTH;
		owner = "";
		customName = "";
	}
	
	protected void Initiate()
	{
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
		
		initiated = true;
	}
	
	@Override
	public void updateEntity()
	{
		if (!initiated)
		{
			Initiate();
		}
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
	}
	
	@Override
	public void onChunkUnload()
	{
		super.onChunkUnload();
	}
	
	public ForgeDirection GetOrientation()
	{
		return orientation;
	}
	
	public void SetOrientation(ForgeDirection orientation)
	{
		this.orientation = orientation;
	}
	
	public void SetOrientation(int orientation)
	{
		this.orientation = ForgeDirection.getOrientation(orientation);
	}
	
	public String GetOwner()
	{
		return owner;
	}
	
	public boolean HasOwner()
	{
		return owner != null && owner.length() > 0;
	}
	
	public void SetOwner(String owner)
	{
		this.owner = owner;
	}
	
	public boolean HasCustomName()
	{
		return customName != null && customName.length() > 0;
	}
	
	public String GetCustomName()
	{
		return customName;
	}
	
	public void SetCustomName(String customName)
	{
		this.customName = customName;
	}
	
	public boolean IsUseableByPlayer(EntityPlayer player)
	{
		return owner.equals(player.username);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		
		if (nbtTagCompound.hasKey(NBTTags.NBT_TE_DIRECTION_KEY))
		{
			orientation = ForgeDirection.getOrientation(nbtTagCompound
					.getByte(NBTTags.NBT_TE_DIRECTION_KEY));
		}
		
		if (nbtTagCompound.hasKey(NBTTags.NBT_TE_OWNER_KEY))
		{
			owner = nbtTagCompound.getString(NBTTags.NBT_TE_OWNER_KEY);
		}
		
		if (nbtTagCompound.hasKey(NBTTags.NBT_TE_CUSTOM_NAME))
		{
			customName = nbtTagCompound.getString(NBTTags.NBT_TE_CUSTOM_NAME);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setByte(NBTTags.NBT_TE_DIRECTION_KEY,
				(byte)orientation.ordinal());
		
		if (HasOwner())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_OWNER_KEY, owner);
		}
		
		if (HasCustomName())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_CUSTOM_NAME, customName);
		}
	}
	
	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction)
	{
		return true;
	}
	
	@Override
	public double demandedEnergyUnits() {
		return maxEnergy - storedEnergy;
	}
	
	@Override
	public double injectEnergyUnits(ForgeDirection directionFrom, double amount)
	{
		double need = (double) ((maxEnergy - storedEnergy) - amount);
		
		if (need < 0)
		{
			storedEnergy = maxEnergy;
			return -need;
		}
		
		storedEnergy = maxEnergy - need;
		return 0;
	}
	
	@Override
	public int getMaxSafeInput()
	{
		return Integer.MAX_VALUE;
	}
}
