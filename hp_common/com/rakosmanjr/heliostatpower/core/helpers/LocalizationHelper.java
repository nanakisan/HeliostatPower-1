/**
 * HeliostatPower
 *
 * @file LocalizationHelper.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.helpers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper
{
	public static boolean IsXMLLanguageFile(String filename)
	{
		return filename.endsWith(".xml");
	}
	
	public static String GetLocaleFromFileName(String filename)
	{
		return filename.substring(filename.lastIndexOf('/') + 1, filename.lastIndexOf('.'));
	}
	
	public static String GetLocalizedString(String key)
	{
		return LanguageRegistry.instance().getStringLocalization(key);
	}
}
