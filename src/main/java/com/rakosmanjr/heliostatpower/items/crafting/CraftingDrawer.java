/**
 * HeliostatPower
 *
 * @file CraftingDrawer.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CraftingDrawer implements ICrafting
{
	private static CraftingDrawer instance = new CraftingDrawer();

	private static int nextId = 0;
	private static Map<Integer, RecipeItem> recipes;

	public static final int GRID_WIDTH = 3;
	public static final int GRID_HEIGHT = 1;
	public static final int GRID_TOTAL = GRID_WIDTH * GRID_HEIGHT;

	public CraftingDrawer()
	{
		recipes = new Hashtable<Integer, RecipeItem>();
	}

	/**
	 * Returns the singleton instance
	 */
	public static CraftingDrawer Instance()
	{
		return instance;
	}

	/**
	 * Add a new recipe to the Drawer
	 */
	@Override
	public void AddRecipe(RecipeItem recipeItem)// ItemStack result, int
	// maxTick, ItemStack[] recipe)
	{
		if (recipeItem.recipe.length != GRID_TOTAL)
		{
			LogHelper.log(Level.WARN, String.format("Invalid recipe added to Drawer! Wrong size!\nRecipeId: %s Result: %s", nextId, LanguageRegistry.instance().getStringLocalization(recipeItem.result.getItem().getUnlocalizedName())));
		}

		// RecipeItem recipeItem = new RecipeItem();
		// recipeItem.maxTick = maxTick;
		// recipeItem.recipe = recipe;
		recipeItem.recipeId = nextId;
		// recipeItem.result = result;

		recipes.put(nextId, recipeItem);
		nextId++;
	}

	/**
	 * Returns the recipeId for the given recipe
	 *
	 * @param input Array of ItemStacks representing the crafting grid
	 *              horizontally. Must have the proper stack size for each stack!
	 */
	@Override
	public int GetRecipeId(List<ItemStack> input)
	{
		boolean itemFound = true;

		for (RecipeItem recipe : recipes.values())
		{
			for (int i = 0; i < GRID_TOTAL; i++)
			{
				if (input.get(i) == null && recipe.recipe[i] == null)
				{
					itemFound = true;
					continue;
				}
				else if ((input.get(i) != null && recipe.recipe[i] != null) && (input.get(i).isItemEqual(recipe.recipe[i])))
				{
					if (input.get(i).stackSize >= recipe.recipe[i].stackSize)
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
	 * Gets the stack size for the given crafting slot, for the given recipeId
	 *
	 * @param recipeId Recipe Id gotten from GetRecipeId()
	 * @param slot     Crafting slot number [0, GRID_TOTAL]
	 */
	@Override
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
