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

import java.util.logging.Level;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import com.rakosmanjr.heliostatpower.lib.GuiIds;
import com.rakosmanjr.heliostatpower.lib.RenderIds;
import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockBasicIonicCompressor extends BlockHeliostat
{
	public BlockBasicIonicCompressor(int id)
	{
		super(id, Material.iron);
		
		setUnlocalizedName(Strings.BASIC_IONIC_COMPRESSOR_NAME);
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(5F);
		this.setBlockBounds(0, 0, 0, 1, 1, 1);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		//LogHelper.Log(Level.INFO, String.format("Tile entity (%s) created", getUnlocalizedName()));
		return new TileBasicIonicCompressor();
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
	
	//@Override
	//public int getRenderType()
	//{
	//	return RenderIds.basicIonicCompressorRenderId;
	//}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta)
	{
		//LogHelper.Log(Level.INFO, String.format("Tile entity (%s) at (%s, %s, %s) broken", getUnlocalizedName(), x, y, z));
		super.breakBlock(world, x, y, z, id, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		//LogHelper.Log(Level.INFO, String.format("Tile entity (%s) at (%s, %s, %s) activated", getUnlocalizedName(), x, y, z));
		
		if (player.isSneaking())
		{
			return true;
		}
		else
		{
			TileBasicIonicCompressor tileCompressor = (TileBasicIonicCompressor)world.getBlockTileEntity(x, y, z);
				
			if (tileCompressor != null)
			{
				player.openGui(HeliostatPower.instance, GuiIds.BASIC_IONIC_COMPRESSOR_ID, world, x, y, z);
			}
			
			return true;
		}
	}
}
