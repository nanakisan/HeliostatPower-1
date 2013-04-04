/**
 * HeliostatPower
 *
 * @file ItemMirrorSupport.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items;

import com.rakosmanjr.heliostatpower.lib.Strings;

public class ItemMirrorSupport extends ItemHeliostat
{
	public ItemMirrorSupport(int id)
	{
		super(id);
		setUnlocalizedName(Strings.MIRROR_SUPPORT_NAME);
	}
}
