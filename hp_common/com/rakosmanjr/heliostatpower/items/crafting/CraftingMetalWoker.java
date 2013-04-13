/**
 * HeliostatPower
 *
 * @file CraftingMetalWoker.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import com.rakosmanjr.heliostatpower.items.ModItems;

import cpw.mods.fml.common.registry.LanguageRegistry;

@Deprecated
public class CraftingMetalWoker
{
	private static CraftingMetalWoker instance = new CraftingMetalWoker();
	
	private static int nextId = 0;
	private static Map<Integer, RecipeItem> recipes;
	
	public static final int GRID_WIDTH = 3;
	public static final int GRID_HEIGHT = 3;
	public static final int GRID_TOTAL = GRID_WIDTH * GRID_HEIGHT;
	
	public CraftingMetalWoker()
	{
		recipes = new Hashtable<Integer, RecipeItem>();
		
		AddRecipe(new ItemStack(ModItems.ironWafer), 20 * 5, new ItemStack[] {
				null, null, null, new ItemStack(Item.ingotIron, 1),
				new ItemStack(Item.ingotIron, 1),
				new ItemStack(Item.ingotIron, 1),
				new ItemStack(Item.ingotIron, 1),
				new ItemStack(Item.ingotIron, 1),
				new ItemStack(Item.ingotIron, 1) });
		
		for (ItemStack item : OreDictionary.getOres("ingotSilver"))
		{
			if (item.stackSize != 1)
			{
				item.stackSize = 1;
			}
			
			AddRecipe(new ItemStack(ModItems.silverWafer), 20 * 5,
					new ItemStack[] { null, null, null, item, item, item, item,
							item, item });
			AddRecipe(new ItemStack(ModItems.silverSpool), 20 * 5,
					new ItemStack[] { item, item, item, item, null, item, item,
							item, item });
		}
		
		for (ItemStack item : OreDictionary.getOres("ingotCopper"))
		{
			AddRecipe(new ItemStack(ModItems.copperWafer), 20 * 5,
					new ItemStack[] { null, null, null, item, item, item, item,
							item, item });
			AddRecipe(new ItemStack(ModItems.copperSpool), 20 * 5,
					new ItemStack[] { item, item, item, item, null, item, item,
							item, item });
		}
	}
	
	public static CraftingMetalWoker Instance()
	{
		return instance;
	}
	
	public void AddRecipe(ItemStack result, int maxTick, ItemStack[] recipe)
	{
		if (recipe.length != GRID_TOTAL)
		{
			LogHelper
					.Log(Level.WARNING,
							String.format(
									"Invalid recipe added! Wrong size!\nRecipeId: %s Result: %s",
									nextId,
									LanguageRegistry
											.instance()
											.getStringLocalization(
													result.getItem()
															.getUnlocalizedName())));
		}
		
		RecipeItem recipeItem = new RecipeItem();
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
				else if ((input[i] != null && recipe.recipe[i] != null)
						&& (input[i].isItemEqual(recipe.recipe[i])))
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
	}
}
