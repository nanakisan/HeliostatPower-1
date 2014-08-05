/**
 * HeliostatPower
 *
 * @file CraftingIonicCompressor.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;

import java.util.Hashtable;
import java.util.Map;

/**
 * Handles all the crafting recipe stuff for the Ionic Compressor
 */
public class CraftingIonicCompressor
{
	private static CraftingIonicCompressor instance = new CraftingIonicCompressor();

	private static int nextId = 0;
	private static Map<Integer, RecipeItem> recipes;
	private static Map<Item, Integer> fuels;

	public static final int GRID_WIDTH = 5;
	public static final int GRID_HEIGHT = 3;
	public static final int GRID_TOTAL = GRID_WIDTH * GRID_HEIGHT;

	private CraftingIonicCompressor()
	{
		recipes = new Hashtable<Integer, RecipeItem>();
		fuels = new Hashtable<Item, Integer>();
	}

	/**
	 * Returns the singleton instance
	 */
	public static CraftingIonicCompressor Instance()
	{
		return instance;
	}

	/**
	 * Add a new fuel to the Ionic Compressor
	 *
	 * @param item          The item to use for fuel
	 * @param effectiveness The effectiveness of the fuel, lower numbers will cause more
	 *                      fuel to be used per tick
	 */
	public void AddFuel(Item item, int effectiveness)
	{
		fuels.put(item, effectiveness);
	}

	/**
	 * Returns a map of the fuels added to the compressor
	 *
	 * @return <Item, effectiveness> map
	 */
	public Map<Item, Integer> GetFuels()
	{
		return fuels;
	}

	/**
	 * Add a new recipe to the Miller
	 *
	 * @param result  ItemStack for what the recipe gives
	 * @param maxTick Number of ticks it takes to complete the crafting (Note: 20
	 *                ticks = 1 second)
	 * @param recipe  Array of ItemStacks representing the crafting grid
	 *                horizontally, works with stack size!
	 */
	public void AddRecipe(ItemStack result, int maxTick, int fuelConsumptionRate, int fuelConsumed, ItemStack[] recipe)
	{
		if (recipe.length != GRID_TOTAL)
		{
			LogHelper.log(Level.WARN, String.format("Invalid recipe added! Wrong size!\nRecipeId: %s Result: %s", nextId, LanguageRegistry.instance().getStringLocalization(result.getItem().getUnlocalizedName())));
			return;
		}

		RecipeItem recipeItem = new RecipeItem();
		recipeItem.fuelConsumed = fuelConsumed;
		recipeItem.fuelConsumptionRate = fuelConsumptionRate;
		recipeItem.maxTick = maxTick;
		recipeItem.recipe = recipe;
		recipeItem.recipeId = nextId;
		recipeItem.result = result;

		recipes.put(nextId, recipeItem);
		nextId++;
	}

	/**
	 * Returns the recipeId for the given recipe
	 *
	 * @param input Array of ItemStacks representing the crafting grid
	 *              horizontally. Must have the proper stack size for each stack!
	 */
	public int GetRecipeId(ItemStack[] input)
	{
		boolean itemFound = true;

		for (RecipeItem recipe : recipes.values())
		{
			for (int i = 0; i < GRID_TOTAL; i++)
			{
				if (input[i] == null && recipe.recipe[i] == null)
				{
					itemFound = true;
					continue;
				}
				else if ((input[i] != null && recipe.recipe[i] != null) && (input[i].isItemEqual(recipe.recipe[i])))
				{
					if (input[i].stackSize >= recipe.recipe[i].stackSize)
					{
						itemFound = true;
						continue;
					}
				}

				itemFound = false;
				break;
			}

			if (itemFound)
			{
				return recipe.recipeId;
			}
		}

		return -1;
	}

	/**
	 * Gets the max tick for the given recipeId
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 */
	public int GetMaxTick(int recipeId)
	{
		return recipes.get(recipeId).maxTick;
	}

	/**
	 * Gets the fuel consumption rate for the given recipeId
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 */
	public int GetFuelConsumptionRate(int recipeId)
	{
		return recipes.get(recipeId).fuelConsumptionRate;
	}

	/**
	 * Gets the amount of fuel consumed for the given recipeId
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 */
	public int GetFuelConsumend(int recipeId)
	{
		return recipes.get(recipeId).fuelConsumed;
	}

	/**
	 * Gets the stack size for the given crafting slot, for the given recipeId
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 * @param slot     Crafting slot number [0, GRID_TOTAL]
	 */
	public int ComponentsUsedInSlot(int recipeId, int slot)
	{
		if (recipes.get(recipeId).recipe[slot] == null)
		{
			return -1;
		}

		return recipes.get(recipeId).recipe[slot].stackSize;
	}

	/**
	 * Gets the result itemStack for the given recipeId Note: the returned
	 * itemStack is a reference!
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 */
	public ItemStack GetResult(int recipeId)
	{
		return recipes.get(recipeId).result;
	}
}
