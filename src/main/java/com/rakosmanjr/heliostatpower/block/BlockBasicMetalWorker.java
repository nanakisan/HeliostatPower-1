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
import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBasicMetalWorker extends BlockHeliostat
{
	public BlockBasicMetalWorker()
	{
		super(Material.iron, Strings.METAL_WORKER_NAME);
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(5F);
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
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
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (!world.isRemote && !player.isSneaking())
		{
			TileBasicMetalWorker tileWorker = (TileBasicMetalWorker)world
					.getTileEntity(x, y, z);
			
			if (tileWorker != null)
			{
				player.openGui(HeliostatPower.instance, GuiIds.METAL_WORKER_ID,
						world, x, y, z);
			}
		}
		
		return true;
	}
}
