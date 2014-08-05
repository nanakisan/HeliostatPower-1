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

import net.minecraft.block.Block;

public class ModBlocks
{
	public static Block sodiumNitrateCrystal;
	public static Block basicIonicCompressor;
	public static Block basicMetalWorker;
	
	public static void Init()
	{
		sodiumNitrateCrystal = new BlockSodiumNitrateCrystal();
		basicIonicCompressor = new BlockBasicIonicCompressor();
		basicMetalWorker = new BlockBasicMetalWorker();
	}
}
