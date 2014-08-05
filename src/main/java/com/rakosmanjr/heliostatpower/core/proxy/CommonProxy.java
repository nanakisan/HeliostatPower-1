/**
 * HeliostatPower
 *
 * @file CommonProxy.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.proxy;

import com.rakosmanjr.heliostatpower.block.ModBlocks;
import com.rakosmanjr.heliostatpower.items.ModItems;
import com.rakosmanjr.heliostatpower.items.crafting.Crafting;
import com.rakosmanjr.heliostatpower.lib.Strings;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicIonicCompressor;
import com.rakosmanjr.heliostatpower.tileentity.TileBasicMetalWorker;
import com.rakosmanjr.heliostatpower.world.WorldGenerator;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	// public static String itemsPng = "/rakosmanjr/solarfarm/items.png";
	// public static String blocksPng = "/rakosmanjr/solarfarm/blocks.png";
	
	public void RegisterRenderers()
	{
		
	}
	
	public void InitTileEntities()
	{
		GameRegistry.registerTileEntity(TileBasicIonicCompressor.class,
				Strings.TE_IONIC_COMPRESSOR);
		GameRegistry.registerTileEntity(TileBasicMetalWorker.class,
				Strings.TE_METAL_WORKER);
	}
	
	public void RegisterItemsAndBlocks()
	{
		// Items
		GameRegistry.registerItem(ModItems.angleIron, Strings.ANGLE_IRON_NAME);
		GameRegistry.registerItem(ModItems.angleIronCross,
				Strings.ANGLE_IRON_CROSS_NAME);
		GameRegistry.registerItem(ModItems.mirrorSupport,
				Strings.MIRROR_SUPPORT_NAME);
		GameRegistry.registerItem(ModItems.mirror, Strings.MIRROR_NAME);
		GameRegistry.registerItem(ModItems.lens, Strings.LENS_NAME);
		GameRegistry.registerItem(ModItems.highPressureIronWafer,
				Strings.HIGH_PRESSURE_IRON_WAFER_NAME);
		GameRegistry.registerItem(ModItems.ironWafer, Strings.IRON_WAFER_NAME);
		GameRegistry.registerItem(ModItems.silverWafer,
				Strings.SILVER_WAFER_NAME);
		GameRegistry.registerItem(ModItems.copperWafer,
				Strings.COPPER_WAFER_NAME);
		GameRegistry.registerItem(ModItems.temperedGlass,
				Strings.TEMPERED_GLASS_NAME);
		GameRegistry.registerItem(ModItems.copperSpool,
				Strings.COPPER_SPOOL_NAME);
		GameRegistry.registerItem(ModItems.silverSpool,
				Strings.SILVER_SPOOL_NAME);
		GameRegistry.registerItem(ModItems.adhesive, Strings.ADHESIVE_NAME);
		GameRegistry.registerItem(ModItems.sodiumNitrate,
				Strings.SODIUM_NITRATE_NAME);
		
		// Blocks
		GameRegistry.registerBlock(ModBlocks.sodiumNitrateCrystal,
				Strings.SODIUM_NITRATE_CRYSTAL_NAME);
		GameRegistry.registerBlock(ModBlocks.basicIonicCompressor,
				Strings.IONIC_COMPRESSOR_NAME);
		GameRegistry.registerBlock(ModBlocks.basicMetalWorker,
				Strings.METAL_WORKER_NAME);
		
		// Add Recipes and Fuels
		Crafting.AddAllRecipes();
		Crafting.AddAllFuels();
	}
	
	public void RegisterWorldGen()
	{
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 1);
	}
}
