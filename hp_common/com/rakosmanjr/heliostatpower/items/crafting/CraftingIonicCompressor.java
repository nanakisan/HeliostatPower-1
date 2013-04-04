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

import java.util.Hashtable;
import java.util.Map;

import com.rakosmanjr.heliostatpower.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingIonicCompressor
{
	private static CraftingIonicCompressor instance = new CraftingIonicCompressor();
	
	private static int nextId = 0;
	private static Map<Integer, RecipeItem> recipes;
	
	public static final int GRID_WIDTH = 5;
	public static final int GRID_HEIGHT = 3;
	public static final int GRID_TOTAL = GRID_WIDTH * GRID_HEIGHT;
	
	private CraftingIonicCompressor()
	{
		recipes = new Hashtable<Integer, RecipeItem>();
		
		AddRecipe(new ItemStack(ModItems.angleIron, 1), 20 * 5, 20, 1, new ItemStack[] { 
			new ItemStack(Item.ingotIron), null, null, null, new ItemStack(Item.ingotIron),
			null, new ItemStack(Item.ingotIron), null, new ItemStack(Item.ingotIron), null,
			null, null, new ItemStack(Item.ingotIron), null, null });
	}
	
	public static CraftingIonicCompressor Instance()
	{
		return instance;
	}
	
	public void AddRecipe(ItemStack result, int maxTick, int fuelConsumptionRate, int fuelConsumed, ItemStack[] recipe)
	{
		if (recipe.length != GRID_TOTAL)
		{
			// Housten, we have a problem...
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
	
	public int GetMaxTick(int recipeId)
	{
		return recipes.get(recipeId).maxTick;
	}
	
	public int GetFuelConsumptionRate(int recipeId)
	{
		return recipes.get(recipeId).fuelConsumptionRate;
	}
	
	public int GetFuelConsumend(int recipeId)
	{
		return recipes.get(recipeId).fuelConsumed;
	}
	
	public int ComponentsUsedInSlot(int recipeId, int slot)
	{
		if (recipes.get(recipeId).recipe[slot] == null)
		{
			return -1;
		}
		
		return recipes.get(recipeId).recipe[slot].stackSize;
	}
	
	public ItemStack GetResult(int recipeId)
	{
		return recipes.get(recipeId).result;
	}
	
	private class RecipeItem
	{
		public int recipeId;
		public ItemStack result;
		public ItemStack[] recipe;
		public int maxTick;
		public int fuelConsumptionRate;
		public int fuelConsumed;
	}
}
