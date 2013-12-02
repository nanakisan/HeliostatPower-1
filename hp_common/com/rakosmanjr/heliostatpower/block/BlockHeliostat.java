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

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.tileentity.TileHeliostat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHeliostat extends BlockContainer
{
	public BlockHeliostat(int id, Material material, String name)
	{
		super(id, material);
		setUnlocalizedName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase par5EntityLivingBase, ItemStack itemStack) {
	
		int direction = 0;
		int facing = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
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
			((TileHeliostat)world.getBlockTileEntity(x, y, z)).SetCustomName(itemStack.getDisplayName());
		}
		
		((TileHeliostat)world.getBlockTileEntity(x, y, z)).SetOwner(par5EntityLivingBase.getEntityName());
		((TileHeliostat)world.getBlockTileEntity(x, y, z)).SetOrientation(direction);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}
}
