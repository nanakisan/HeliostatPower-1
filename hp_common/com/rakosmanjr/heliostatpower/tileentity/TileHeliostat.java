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

import com.rakosmanjr.heliostatpower.lib.NBTTags;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileHeliostat extends TileEntity
{
	private ForgeDirection orientation;
	private short state;
	private String owner;
	private String customName;
	
	public TileHeliostat()
	{
		orientation = ForgeDirection.SOUTH;
		state = 0;
		owner = "";
		customName = "";
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
	
	public short GetState()
	{
		return state;
	}
	
	public void SetState(short state)
	{
		this.state = state;
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
		
		if (nbtTagCompound.hasKey(NBTTags.NBT_TE_STATE_KEY))
		{
			state = nbtTagCompound.getShort(NBTTags.NBT_TE_STATE_KEY);
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
		nbtTagCompound.setShort(NBTTags.NBT_TE_STATE_KEY, state);
		
		if (HasOwner())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_OWNER_KEY, owner);
		}
		
		if (HasCustomName())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_CUSTOM_NAME, customName);
		}
	}
}
