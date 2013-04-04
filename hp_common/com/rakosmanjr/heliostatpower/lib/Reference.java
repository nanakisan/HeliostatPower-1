/**
 * HeliostatPower
 *
 * @file Reference.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.lib;

public class Reference
{
	// Debugging?
	public static final boolean DEBUG_MODE = false;
	
	// General mod stuff
	public static final String MOD_ID = "heliostatpower";
	public static final String MOD_NAME = "HeliostatPower";
	public static final String VERSION = "0.0.3";
	public static final String MC_VERSION = "1.5.1";
	
	public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
	
	public static final String SERVER_PROXY_CLASS = "com.rakosmanjr.heliostatpower.core.proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "com.rakosmanjr.heliostatpower.core.proxy.ClientProxy";
	
	// 2 MJ = 5 EU
	// Should make this a config setting at some point
	public static final float POWER_RATIO_EU = 2f;
	public static final float POWER_RATIO_MJ = 5f;
	public static final float POWER_RATIO_EU_MJ = POWER_RATIO_EU / POWER_RATIO_MJ;
	public static final float POWER_RATIO_MJ_EU = POWER_RATIO_MJ / POWER_RATIO_EU;
}
