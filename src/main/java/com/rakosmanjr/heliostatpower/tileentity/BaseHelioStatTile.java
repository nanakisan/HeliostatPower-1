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
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BaseHelioStatTile extends TileEntity
{
	private ForgeDirection orientation;
	private String owner;
	private String customName;
	private boolean initiated;

	protected double storedEnergy;
	protected double maxEnergy;

	public BaseHelioStatTile()
	{
		orientation = ForgeDirection.SOUTH;
		owner = "";
		customName = "";
	}

	protected void Initiate()
	{
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

	public ForgeDirection getOrientation()
	{
		return orientation;
	}

	public void setOrientation(ForgeDirection orientation)
	{
		this.orientation = orientation;
	}

	public void setOrientation(int orientation)
	{
		this.orientation = ForgeDirection.getOrientation(orientation);
	}

	public String getOwner()
	{
		return owner;
	}

	public boolean hasOwner()
	{
		return owner != null && owner.length() > 0;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public boolean hasCustomName()
	{
		return customName != null && customName.length() > 0;
	}

	public String getCustomName()
	{
		return customName;
	}

	public void setCustomName(String customName)
	{
		this.customName = customName;
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return owner.equals(player.getDisplayName());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);

		if (nbtTagCompound.hasKey(NBTTags.NBT_TE_DIRECTION_KEY))
		{
			orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(NBTTags.NBT_TE_DIRECTION_KEY));
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

		nbtTagCompound.setByte(NBTTags.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());

		if (this.hasOwner())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_OWNER_KEY, owner);
		}

		if (this.hasCustomName())
		{
			nbtTagCompound.setString(NBTTags.NBT_TE_CUSTOM_NAME, customName);
		}
	}
}
