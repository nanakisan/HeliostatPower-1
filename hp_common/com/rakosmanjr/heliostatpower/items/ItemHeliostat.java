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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemHeliostat extends Item
{
	public ItemHeliostat(int id)
	{
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 64;
		setNoRepair();
		setCreativeTab(HeliostatPower.tabsHP);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister iconRegister)
	{
		iconIndex = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}
}
