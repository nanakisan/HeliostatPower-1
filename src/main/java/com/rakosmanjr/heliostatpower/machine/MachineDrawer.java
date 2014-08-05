/**
 * HeliostatPower
 *
 * @file MachineDrawer.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.machine;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.rakosmanjr.heliostatpower.items.crafting.CraftingDrawer;

public class MachineDrawer extends MachineHeliostatPower
{
	public MachineDrawer(List<ItemStack> inventory)
	{
		super(3, 1, 1, inventory, CraftingDrawer.Instance());
		
		outputSlot = TOTAL_SLOTS - 1;
		craftingGridChanged = true;
		validRecipe = false;
		inCycle = false;
	}
}
