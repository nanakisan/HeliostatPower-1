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

import com.rakosmanjr.heliostatpower.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Crafting
{
	/**
	 * Add all the recipes used by the mod
	 */
	public static void AddAllRecipes()
	{
		ItemStack ironStack = new ItemStack(Items.iron_ingot);
		ItemStack thinGlassStack = new ItemStack(Blocks.glass_pane);
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
				new RecipeItem(angleIronStack, new ItemStack[] { null, null,
						ironStack, null, null, ironStack, ironStack, ironStack,
						ironStack }, 20 * 5, 0, 0, 1, 20));
		CraftingMiller.Instance().AddRecipe(
				new RecipeItem(angleIronCrossStack, new ItemStack[] {
						angleIronStack, null, angleIronStack, null,
						angleIronStack, null, angleIronStack, null,
						angleIronStack }, 20 * 5, 0, 0, 1, 20));
		
		for (ItemStack item : new ItemStack[] { ironStack })// OreDictionary.getOres("ingotIron"))
		{
			if (item.stackSize != 1)
			{
				item.stackSize = 1;
			}
			
			CraftingMiller.Instance().AddRecipe(
					new RecipeItem(ironWaferStack, new ItemStack[] { item,
							item, item, item, item, item, item, item, item },
							20 * 5, 0, 0, 1, 20));
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
					new RecipeItem(silverWaferStack, new ItemStack[] { item,
							item, item, item, item, item, item, item, item },
							20 * 5, 0, 0, 1, 20));
			CraftingDrawer.Instance().AddRecipe(
					new RecipeItem(new ItemStack(ModItems.silverSpool),
							new ItemStack[] { item, item, item }, 20 * 5, 0, 0,
							1, 20));
		}
		
		for (ItemStack item : OreDictionary.getOres("ingotCopper"))
		{
			CraftingMiller.Instance().AddRecipe(
					new RecipeItem(copperWaferStack, new ItemStack[] { item,
							item, item, item, item, item, item, item, item },
							20 * 5, 0, 0, 1, 20));
			CraftingDrawer.Instance().AddRecipe(
					new RecipeItem(new ItemStack(ModItems.copperSpool),
							new ItemStack[] { item, item, item }, 20 * 5, 0, 0,
							1, 20));
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
