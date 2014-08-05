/**
 * HeliostatPower
 *
 * @file HPBlock.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.block;

import com.rakosmanjr.heliostatpower.tileentity.BaseHelioStatTile;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockHeliostat extends BlockContainer
{
	public BlockHeliostat(Material material, String name)
	{
		super(material);
		this.setBlockName(name);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemStack)
	{

		int direction = 0;
		int facing = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		switch (facing)
		{
			case 0:
				direction = ForgeDirection.NORTH.ordinal();
				break;
			case 1:
				direction = ForgeDirection.EAST.ordinal();
				break;
			case 2:
				direction = ForgeDirection.SOUTH.ordinal();
				break;
			case 3:
				direction = ForgeDirection.WEST.ordinal();
				break;
		}

		world.setBlockMetadataWithNotify(x, y, z, direction, 3);

		if (itemStack.hasDisplayName())
		{
			((BaseHelioStatTile) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
		}

		((BaseHelioStatTile) world.getTileEntity(x, y, z)).setOwner(placer.getCommandSenderName());
		((BaseHelioStatTile) world.getTileEntity(x, y, z)).setOrientation(direction);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return null;
	}
}
