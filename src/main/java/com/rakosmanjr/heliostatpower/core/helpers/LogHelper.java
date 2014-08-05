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

import com.rakosmanjr.heliostatpower.lib.Reference;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogHelper
{
	private static Logger hpLogger = LogManager.getLogger(Reference.MOD_ID);

	public static void log(Level logLevel, String message)
	{
		hpLogger.log(logLevel, message);
	}
}
