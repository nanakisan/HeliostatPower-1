/**
 * HeliostatPower
 *
 * @file BasicMetalWorker.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.block;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.lib.GuiIds;
import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicMetalWorker extends BlockHeliostat
{
	public BasicMetalWorker(int id, Material material)
	{
		super(id, material);
		
		setUnlocalizedName(Strings.BASIC_METAL_WORKER_NAME);
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(5F);
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileBasicMetalWorker();
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta)
	{
		super.breakBlock(world, x, y, z, id, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (!world.isRemote && player.isSneaking())
		{
			TileBasicMetalWorker tileWorker = (TileBasicMetalWorker)world
					.getBlockTileEntity(x, y, z);
			
			if (tileWorker != null)
			{
				player.openGui(HeliostatPower.instance, GuiIds.METAL_WORKER_ID,
						world, x, y, z);
			}
		}
		
		return true;
	}
}
