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

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.lib.Reference;
import com.rakosmanjr.heliostatpower.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IIconRegister;

import java.util.Random;

public class BlockSodiumNitrateCrystal extends BlockSand
{
	public BlockSodiumNitrateCrystal()
	{
		this.setBlockName(Strings.SODIUM_NITRATE_CRYSTAL_NAME);
		setCreativeTab(HeliostatPower.tabsHP);
		setHardness(1f);
		setResistance(5f);
		this.setStepSound(Block.soundTypeSand);
		this.setHarvestLevel("shovel", 3);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	public int quantityDropped(Random random)
	{
		double g = random.nextGaussian();
		double l = 1.0; // lower weight
		double u = 3.0; // upper weight

		return (int) (g > 0 ? (g * u) + 3 : (g * l) + 3);
	}
}
