/**
 * HeliostatPower
 *
 * @file XMLLocations.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.lib;

import com.rakosmanjr.heliostatpower.HeliostatPower;
import com.rakosmanjr.heliostatpower.core.helpers.XMLReader;

public class XMLLocations
{
	// GUI XML files
	public static String GUI_IONIC_COMPRESSOR_SHORT_XML = "mods/assets/heliostatpower/textures/gui/" + "ionicCompressor.xml";
	public static String GUI_METAL_WORKER_SHORT_XML = "mods/assets/heliostatpower/textures/gui/" + "metalWorker.xml";
	
	public static String GUI_IONIC_COMPRESSOR_FULL_XML = HeliostatPower.class.getResource(GUI_IONIC_COMPRESSOR_SHORT_XML).getPath().replaceFirst("%20", " ");
	public static String GUI_METAL_WORKER_FULL_XML = HeliostatPower.class.getResource(GUI_METAL_WORKER_SHORT_XML).getPath().replaceFirst("%20", " ");
	
	// XML readers
	public static final XMLReader IC_READER = new XMLReader(GUI_IONIC_COMPRESSOR_FULL_XML);
	public static final XMLReader MW_READER = new XMLReader(GUI_METAL_WORKER_FULL_XML);
}
