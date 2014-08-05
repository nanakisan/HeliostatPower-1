/**
 * HeliostatPower
 *
 * @file BlockBasicIonicCompressor.java
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
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBasicIonicCompressor extends BlockHeliostat
{
	public BlockBasicIonicCompressor()
	{
		super(Material.iron, Strings.IONIC_COMPRESSOR_NAME);
		
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(5F);
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
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
			TileBasicIonicCompressor tileCompressor = (TileBasicIonicCompressor)world.getTileEntity(x, y, z);

			if (tileCompressor != null)
			{
				player.openGui(HeliostatPower.instance,
						GuiIds.IONIC_COMPRESSOR_ID, world, x, y, z);
			}
		}
		
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileBasicIonicCompressor();
	}
}
