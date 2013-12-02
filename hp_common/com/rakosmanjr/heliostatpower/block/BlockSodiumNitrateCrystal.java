/**
 * HeliostatPower
 *
 * @file BlockSodiumNitrate.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.block;

import java.util.Random;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.items.ModItems;
import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.common.MinecraftForge;

public class BlockSodiumNitrateCrystal extends BlockSand
{
	public BlockSodiumNitrateCrystal(int id)
	{
		super(id);
		setUnlocalizedName(Strings.SODIUM_NITRATE_CRYSTAL_NAME);
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(1f);
		setResistance(5f);
		setStepSound(Block.soundSandFootstep);
		MinecraftForge.setBlockHarvestLevel(this, "shovel", 3);
	}
	
	public int idDropped(int metadata, Random random, int zero)
	{
		return ModItems.sodiumNitrate.itemID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	
	public int quantityDropped(Random random)
	{
		double g = random.nextGaussian();
		double l = 1.0; // lower weight
		double u = 3.0; // upper weight
		
		return (int)(g > 0 ? (g * u) + 3 : (g * l) + 3);
	}
}
