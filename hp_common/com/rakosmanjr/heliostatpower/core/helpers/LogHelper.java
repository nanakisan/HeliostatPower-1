/**
 * HeliostatPower
 *
 * @file LogHelper.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.rakosmanjr.heliostatpower.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class LogHelper
{
	private static Logger hpLogger = Logger.getLogger(Reference.MOD_ID);
	
	public static void Init()
	{
		hpLogger.setParent(FMLLog.getLogger());
	}
	
	public static void Log(Level logLevel, String message)
	{
		hpLogger.log(logLevel, message);
	}
}
