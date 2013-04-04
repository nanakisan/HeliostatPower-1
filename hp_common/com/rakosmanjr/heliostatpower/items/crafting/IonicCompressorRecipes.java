/**
 * HeliostatPower
 *
 * @file IonicCompressorRecipes.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import com.rakosmanjr.heliostatpower.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IonicCompressorRecipes
{
	private static final IonicCompressorRecipes instance = new IonicCompressorRecipes();
	
	private Map<ItemStack, ItemStack> recipes;
	
	private IonicCompressorRecipes()
	{
		recipes = new HashMap<ItemStack, ItemStack>();
		
		AddRecipe(new ItemStack(Item.ingotIron, 3), new ItemStack(ModItems.angleIron, 1));
		AddRecipe(new ItemStack(Item.ingotIron, 3), new ItemStack(ModItems.angleIron, 1));
		AddRecipe(new ItemStack(Item.ingotIron, 3), new ItemStack(ModItems.angleIron, 1));
		AddRecipe(new ItemStack(Item.ingotIron, 3), new ItemStack(ModItems.angleIron, 1));
	}
	
	public static IonicCompressorRecipes Instance()
	{
		return instance;
	}
	
	public void AddRecipe(ItemStack inputStack, ItemStack resultStack)
	{
		recipes.put(inputStack, resultStack);
	}
	
	public ItemStack GetResultFor(ItemStack inputStack, boolean adjustInput)
	{
		if (inputStack == null)
		{
			return null;
		}
		
		for (Map.Entry<ItemStack, ItemStack> entry : recipes.entrySet())
		{
			ItemStack recipeInput = entry.getKey();
			
			if (recipeInput.isItemEqual(inputStack)
					&& inputStack.stackSize >= recipeInput.stackSize)
			{
				LogHelper.Log(Level.INFO, String.format("Recipe uses - item: %s id: %s amount: %s", entry.getValue().getItem().getUnlocalizedName(), entry.getValue().itemID, entry.getValue().stackSize));
				LogHelper.Log(Level.INFO, String.format("Recipe creates - item: %s id: %s amount: %s", recipeInput.getItem().getUnlocalizedName(), recipeInput.itemID, recipeInput.stackSize));
				
				if (adjustInput)
				{ 
					inputStack.stackSize -= recipeInput.stackSize;
				}
				
				return entry.getValue().copy();
			}
		}
		
		return null;
	}
	
	public boolean HasRecipeFor(ItemStack inputStack)
	{
		if (inputStack == null)
		{
			return false;
		}
		
		for (Map.Entry<ItemStack, ItemStack> entry : recipes.entrySet())
		{
			if (entry.getKey().isItemEqual(inputStack))
			{
				return true;
			}
		}
		
		return false;
	}
}
