/**
 * HeliostatPower
 *
 * @file ModItems.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.items;

//import ic2.api.Items;

import com.rakosmanjr.heliostatpower.lib.ItemIds;

//import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
//import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModItems
{
	public static Item angleIron;
	public static Item angleIronCross;
	public static Item mirrorSupport;
	public static Item mirror;
	public static Item lens;
	public static Item highPressureIronWafer;
	public static Item ironWafer;
	public static Item silverWafer;
	public static Item copperWafer;
	public static Item temperedGlass;
	public static Item copperSpool;
	public static Item silverSpool;
	public static Item adhesive;
	public static Item sodiumNitrate;
	
	public static void Init()
	{
		// Create items
		angleIron = new ItemAngleIron(ItemIds.ANGLE_IRON_DEFAULT);
		angleIronCross = new ItemAngleIronCross(ItemIds.ANGLE_IRON_CROSS_DEFAULT);
		mirrorSupport = new ItemMirrorSupport(ItemIds.MIRROR_SUPPORT_DEFAULT);
		mirror = new ItemMirror(ItemIds.MIRROR_DEFAULT);
		lens = new ItemLens(ItemIds.LENS_DEFAULT);
		highPressureIronWafer = new ItemHighPressureIronWafer(ItemIds.HIGH_PRESSURE_IRON_WAFER_DEFAULT);
		ironWafer = new ItemIronWafer(ItemIds.IRON_WAFER_DEFAULT);
		silverWafer = new ItemSilverWafer(ItemIds.SILVER_WAFER_DEFAULT);
		copperWafer = new ItemCopperWafer(ItemIds.COPPER_WAFER_DEFAULT);
		temperedGlass = new ItemTemperedGlass(ItemIds.TEMPERED_GLASS_DEFAULT);
		copperSpool = new ItemCopperSpool(ItemIds.COPPER_SPOOL_DEFAULT);
		silverSpool = new ItemSilverSpool(ItemIds.SILVER_SPOOL_DEFAULT);
		adhesive = new ItemAdhesive(ItemIds.ADHESIVE_DEFAULT);
		sodiumNitrate = new ItemSodiumNitrate(ItemIds.SODIUM_NITRATE_DEFAULT);
		
		// Setup recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(angleIronCross, "a a", " a ", "a a", 'a', angleIron));
		GameRegistry.addRecipe(new ShapedOreRecipe(mirrorSupport, "aaa", "www", " i ", 'a', adhesive, 'w', ironWafer, 'i', angleIronCross));
		GameRegistry.addRecipe(new ShapedOreRecipe(lens, "tst", "t t", "tst", 't', temperedGlass, 's', silverWafer));
		
		//if (Loader.isModLoaded("IC2"))
		//{
		//	GameRegistry.addRecipe(new ShapedOreRecipe(adhesive, "rrr", "rwr", "rrr", 's', "itemRubber", 'w', OreDictionary.getOreName(Items.getItem("waterCell").itemID)));
		//}
	}
}
