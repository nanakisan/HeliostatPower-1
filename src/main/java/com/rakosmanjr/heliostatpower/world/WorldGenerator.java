/**
 * HeliostatPower
 *
 * @file WorldGenerator.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.world;

import com.rakosmanjr.heliostatpower.block.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class WorldGenerator implements IWorldGenerator
{
	public WorldGenerator()
	{
		
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case -1: // Nether
				break;
			case 0: // Overworld
				GenerateOverworld(world, random, chunkX * 16, chunkZ * 16);
				break;
			case 1: // End
				break;
		}
	}
	
	private void GenerateOverworld(World world, Random random, int blockX, int blockZ)
	{
		int currentBiomeId = world.getWorldChunkManager().getBiomeGenAt(blockX, blockZ).biomeID;
		
		// Generate Sodium Nitrate Crystals
		if (currentBiomeId == BiomeGenBase.desert.biomeID || currentBiomeId == BiomeGenBase.desertHills.biomeID)
		{
			// 6.68% chance of generating
			// Calculation at http://easycalculation.com/statistics/normal-distribution.php
			if (random.nextGaussian() > 1.5)
			{
				blockX = blockX + random.nextInt(16);
				blockZ = blockZ + random.nextInt(16);
				int blockY = world.getHeightValue(blockX, blockZ);
				WorldGenMinable generator = new WorldGenMinable(
						ModBlocks.sodiumNitrateCrystal,
						(int)(random.nextGaussian() * 11) + 90,
						Blocks.sand);
				generator.generate(world, random, blockX, blockY, blockZ);
			}
		}
	}
}
