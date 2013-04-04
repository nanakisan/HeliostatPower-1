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

import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.tileentity.TileHeliostat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockHeliostat extends BlockContainer
{
	public BlockHeliostat(int id, Material material)
	{
		super(id, material);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
	{
		int direction = 0;
		int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
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
		
		((TileHeliostat)world.getBlockTileEntity(x, y, z)).SetOwner(entityLiving.getEntityName());
		((TileHeliostat)world.getBlockTileEntity(x, y, z)).SetOrientation(direction);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}
}
