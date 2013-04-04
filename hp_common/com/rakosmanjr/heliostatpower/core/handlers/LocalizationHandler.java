/**
 * HeliostatPower
 *
 * @file LocalizationHandler.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.handlers;

import com.rakosmanjr.heliostatpower.core.helpers.LocalizationHelper;
import com.rakosmanjr.heliostatpower.lib.Localizations;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler
{
	public static void LoadLanguages()
	{
		for (String localizationFile : Localizations.localeFiles)
		{
			LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.GetLocaleFromFileName(localizationFile), LocalizationHelper.IsXMLLanguageFile(localizationFile));
		}
	}
}
