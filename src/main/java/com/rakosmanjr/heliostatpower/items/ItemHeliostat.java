/**
 * HeliostatPower
 *
 * @file HPItem.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.lib.Reference;
import net.minecraft.item.Item;

public class ItemHeliostat extends Item
{
	public ItemHeliostat(String unlocalizedName)
	{
		maxStackSize = 64;
		setNoRepair();
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(HeliostatPower.tabsHP);
		setTextureName(Reference.MOD_ID.toLowerCase() + ":" + unlocalizedName.substring(unlocalizedName.indexOf(".") + 1));
	}
}
