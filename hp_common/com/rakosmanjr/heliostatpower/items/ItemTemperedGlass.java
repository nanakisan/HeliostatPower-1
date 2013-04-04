/**
 * HeliostatPower
 *
 * @file ItemTemperedGlass.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items;

import com.rakosmanjr.heliostatpower.lib.Strings;

public class ItemTemperedGlass extends ItemHeliostat
{
	public ItemTemperedGlass(int id)
	{
		super(id);
		setUnlocalizedName(Strings.TEMPERED_GLASS_NAME);
	}
}
