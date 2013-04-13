/**
 * HeliostatPower
 *
 * @file Crafting.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.rakosmanjr.heliostatpower.items.ModItems;

public class Crafting
{
	/**
	 * Add all the recipes used by the mod
	 */
	public static void AddAllRecipes()
	{
		ItemStack ironStack = new ItemStack(Item.ingotIron);
		ItemStack thinGlassStack = new ItemStack(Block.thinGlass);
		ItemStack ironWaferStack = new ItemStack(ModItems.ironWafer);
		ItemStack copperWaferStack = new ItemStack(ModItems.copperWafer);
		ItemStack silverWaferStack = new ItemStack(ModItems.silverWafer);
		
		ItemStack angleIronStack = new ItemStack(ModItems.angleIron);
		ItemStack angleIronCrossStack = new ItemStack(ModItems.angleIronCross);
		ItemStack mirrorStack = new ItemStack(ModItems.mirror);
		ItemStack highPressureIronWaferStack = new ItemStack(
				ModItems.highPressureIronWafer);
		ItemStack temperedGlassStack = new ItemStack(ModItems.temperedGlass);
		
		// CraftingIonicCompressor.Instance().AddRecipe(
		// angleIronStack,
		// 20 * 5,
		// 20,
		// 1,
		// new ItemStack[] { ironStack, null, null, null, ironStack, null,
		// ironStack, null, ironStack, null, null, null,
		// ironStack, null, null });
		
		CraftingIonicCompressor.Instance().AddRecipe(
				mirrorStack,
				20 * 5,
				20,
				1,
				new ItemStack[] { thinGlassStack, thinGlassStack,
						thinGlassStack, thinGlassStack, thinGlassStack,
						copperWaferStack, silverWaferStack, silverWaferStack,
						silverWaferStack, copperWaferStack, copperWaferStack,
						copperWaferStack, copperWaferStack, copperWaferStack,
						copperWaferStack });
		CraftingIonicCompressor.Instance().AddRecipe(
				highPressureIronWaferStack,
				20 * 5,
				20,
				1,
				new ItemStack[] { ironWaferStack, ironWaferStack,
						ironWaferStack, ironWaferStack, ironWaferStack,
						copperWaferStack, copperWaferStack, copperWaferStack,
						copperWaferStack, copperWaferStack, ironWaferStack,
						ironWaferStack, ironWaferStack, ironWaferStack,
						ironWaferStack });
		CraftingIonicCompressor.Instance().AddRecipe(
				temperedGlassStack,
				20 * 5,
				20,
				1,
				new ItemStack[] { ironWaferStack, thinGlassStack,
						thinGlassStack, thinGlassStack, ironWaferStack,
						ironWaferStack, thinGlassStack, thinGlassStack,
						thinGlassStack, ironWaferStack, ironWaferStack,
						thinGlassStack, thinGlassStack, thinGlassStack,
						ironWaferStack });
		
		CraftingMiller.Instance().AddRecipe(
				angleIronStack,
				20 * 5,
				new ItemStack[] { null, null, ironStack, null, null, ironStack,
						ironStack, ironStack, ironStack });
		CraftingMiller.Instance().AddRecipe(
				angleIronCrossStack,
				20 * 5,
				new ItemStack[] { angleIronStack, null, angleIronStack, null,
						angleIronStack, null, angleIronStack, null,
						angleIronStack });
		
		for (ItemStack item : new ItemStack[] { ironStack })// OreDictionary.getOres("ingotIron"))
		{
			if (item.stackSize != 1)
			{
				item.stackSize = 1;
			}
			
			CraftingMiller.Instance().AddRecipe(
					ironWaferStack,
					20 * 5,
					new ItemStack[] { item, item, item, item, item, item, item,
							item, item });
			// CraftingDrawer.Instance().AddRecipe(
			// new ItemStack(ModItems.ironWafer), 20 * 5,
			// new ItemStack[] { item, item, item });
		}
		
		for (ItemStack item : OreDictionary.getOres("ingotSilver"))
		{
			if (item.stackSize != 1)
			{
				item.stackSize = 1;
			}
			
			CraftingMiller.Instance().AddRecipe(
					silverWaferStack,
					20 * 5,
					new ItemStack[] { item, item, item, item, item, item, item,
							item, item });
			CraftingDrawer.Instance().AddRecipe(
					new ItemStack(ModItems.silverSpool), 20 * 5,
					new ItemStack[] { item, item, item });
		}
		
		for (ItemStack item : OreDictionary.getOres("ingotCopper"))
		{
			CraftingMiller.Instance().AddRecipe(
					copperWaferStack,
					20 * 5,
					new ItemStack[] { item, item, item, item, item, item, item,
							item, item });
			CraftingDrawer.Instance().AddRecipe(
					new ItemStack(ModItems.copperSpool), 20 * 5,
					new ItemStack[] { item, item, item });
		}
	}
	
	/**
	 * Add all the fuels used by the mod
	 */
	public static void AddAllFuels()
	{
		// ---------------------- //
		// Ionic Compressor Fuels //
		// ---------------------- //
		CraftingIonicCompressor.Instance().AddFuel(ModItems.sodiumNitrate, 1);
	}
}
