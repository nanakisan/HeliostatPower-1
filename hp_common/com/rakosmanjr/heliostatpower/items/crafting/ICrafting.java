/**
 * HeliostatPower
 *
 * @file ICrafting.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ICrafting
{
	public void AddRecipe(RecipeItem recipeItem);
	
	public int GetRecipeId(List<ItemStack> input);
	
	public int ComponentsUsedInSlot(int recipeId, int slot);
	
	public int GetMaxTick(int recipeId);
	
	public ItemStack GetResult(int recipeId);
}
