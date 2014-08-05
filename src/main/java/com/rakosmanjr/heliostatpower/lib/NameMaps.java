/**
 * HeliostatPower
 *
 * @file NameMaps.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.lib;

import java.util.Hashtable;
import java.util.Map;

import com.rakosmanjr.heliostatpower.tileentity.Status;

public class NameMaps
{
	public static Map<Status, String> STATUS_NAMEMAP;
	
	static
	{
		STATUS_NAMEMAP = new Hashtable<Status, String>();
		STATUS_NAMEMAP.put(Status.WaitingForRecipe, "status.waitingForRecipe");
		STATUS_NAMEMAP.put(Status.WaitingForFuel, "status.waitingForFuel");
		STATUS_NAMEMAP.put(Status.Processing, "status.processing");
		STATUS_NAMEMAP.put(Status.OutputFull, "status.outputFull");
	}
}
