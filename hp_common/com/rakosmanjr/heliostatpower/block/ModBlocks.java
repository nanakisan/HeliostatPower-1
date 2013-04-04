/**
 * HeliostatPower
 *
 * @file ModBlocks.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.block;

import com.rakosmanjr.heliostatpower.lib.BlockIds;

import net.minecraft.block.Block;

public class ModBlocks
{
	public static Block sodiumNitrateCrystal;
	public static Block basicIonicCompressor;
	
	public static void Init()
	{
		sodiumNitrateCrystal = new BlockSodiumNitrateCrystal(BlockIds.SODIUM_NITRATE_CRYSTAL_DEFAULT);
		basicIonicCompressor = new BlockBasicIonicCompressor(BlockIds.BASIC_IONIC_COMPRESSOR_DEFAULT);
	}
}
