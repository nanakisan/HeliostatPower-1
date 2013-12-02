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

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.core.helpers.LocalizationHelper;
import com.rakosmanjr.heliostatpower.lib.Reference;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHeliostat extends Item
{
	
	public Icon icon;
	
	public ItemHeliostat(int id, String unlocalizedName)
	{
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 64;
		setNoRepair();
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(HeliostatPower.tabsHP);
		setTextureName(Reference.MOD_ID.toLowerCase() + ":" + unlocalizedName.substring(unlocalizedName.indexOf(".") +1));
	}
}
