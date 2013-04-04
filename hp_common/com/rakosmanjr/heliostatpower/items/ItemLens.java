/**
 * HeliostatPower
 *
 * @file ItemLens.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items;

import com.rakosmanjr.heliostatpower.lib.Strings;

public class ItemLens extends ItemHeliostat
{
	public ItemLens(int id)
	{
		super(id);
		setUnlocalizedName(Strings.LENS_NAME);
	}
}
