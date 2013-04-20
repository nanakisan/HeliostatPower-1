/**
 * HeliostatPower
 *
 * @file RecipeItem.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items.crafting;

import net.minecraft.item.ItemStack;

public class RecipeItem
{
	public int recipeId;
	public ItemStack result;
	public ItemStack[] recipe;
	public int maxTick;
	public int fuelConsumptionRate;
	public int fuelConsumed;
	public int energyConsumptionRate;
	public int energyConsumed;
	
	public RecipeItem(ItemStack result, ItemStack[] recipe, int maxTick,
			int fuelConsumptionRate, int fuelConsumed,
			int energyConsumptionRate, int energyConsumed)
	{
		this.result = result;
		this.recipe = recipe;
		this.maxTick = maxTick;
		this.fuelConsumptionRate = fuelConsumptionRate;
		this.fuelConsumed = fuelConsumed;
		this.energyConsumptionRate = energyConsumptionRate;
		this.energyConsumed = energyConsumed;
	}
	
	public RecipeItem()
	{
		
	}
}
