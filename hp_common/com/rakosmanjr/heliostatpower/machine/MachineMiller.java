/**
 * HeliostatPower
 *
 * @file MachineMiller.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.machine;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.rakosmanjr.heliostatpower.items.crafting.CraftingMiller;

public class MachineMiller extends MachineHeliostatPower
{
	public MachineMiller(List<ItemStack> inventory)
	{
		super(3, 3, 1, inventory, CraftingMiller.Instance());
		
		outputSlot = TOTAL_SLOTS - 1;
		craftingGridChanged = true;
		validRecipe = false;
		inCycle = false;
	}
}
