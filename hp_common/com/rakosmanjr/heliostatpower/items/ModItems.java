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

import ic2.api.item.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.rakosmanjr.heliostatpower.lib.ItemIds;
import com.rakosmanjr.heliostatpower.lib.Strings;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
//import cpw.mods.fml.common.Loader;
//import net.minecraftforge.oredict.OreDictionary;

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
		angleIron = new ItemAngleIron(ItemIds.ANGLE_IRON_DEFAULT, Strings.ANGLE_IRON_NAME);
		angleIronCross = new ItemAngleIronCross(ItemIds.ANGLE_IRON_CROSS_DEFAULT, Strings.ANGLE_IRON_CROSS_NAME);
		mirrorSupport = new ItemMirrorSupport(ItemIds.MIRROR_SUPPORT_DEFAULT, Strings.MIRROR_SUPPORT_NAME);
		mirror = new ItemMirror(ItemIds.MIRROR_DEFAULT, Strings.MIRROR_NAME);
		lens = new ItemLens(ItemIds.LENS_DEFAULT, Strings.LENS_NAME);
		highPressureIronWafer = new ItemHighPressureIronWafer(ItemIds.HIGH_PRESSURE_IRON_WAFER_DEFAULT, Strings.HIGH_PRESSURE_IRON_WAFER_NAME);
		ironWafer = new ItemIronWafer(ItemIds.IRON_WAFER_DEFAULT, Strings.IRON_WAFER_NAME);
		silverWafer = new ItemSilverWafer(ItemIds.SILVER_WAFER_DEFAULT, Strings.SILVER_WAFER_NAME);
		copperWafer = new ItemCopperWafer(ItemIds.COPPER_WAFER_DEFAULT, Strings.COPPER_WAFER_NAME);
		temperedGlass = new ItemTemperedGlass(ItemIds.TEMPERED_GLASS_DEFAULT, Strings.TEMPERED_GLASS_NAME);
		copperSpool = new ItemCopperSpool(ItemIds.COPPER_SPOOL_DEFAULT, Strings.COPPER_SPOOL_NAME);
		silverSpool = new ItemSilverSpool(ItemIds.SILVER_SPOOL_DEFAULT, Strings.SILVER_SPOOL_NAME);
		adhesive = new ItemAdhesive(ItemIds.ADHESIVE_DEFAULT, Strings.ADHESIVE_NAME);
		sodiumNitrate = new ItemSodiumNitrate(ItemIds.SODIUM_NITRATE_DEFAULT, Strings.SODIUM_NITRATE_NAME);
	
		LanguageRegistry.addName(adhesive, "Adhesive");
		LanguageRegistry.addName(angleIron, "Angle Iron");
		LanguageRegistry.addName(angleIronCross, "Angle Iron Cross");
		LanguageRegistry.addName(copperSpool, "Copper Spool");
		LanguageRegistry.addName(copperWafer, "Copper Wafer");
		LanguageRegistry.addName(highPressureIronWafer, "High Pressure Iron Wafer");
		LanguageRegistry.addName(ironWafer, "Iron Wafer");
		LanguageRegistry.addName(lens, "Lens");
		LanguageRegistry.addName(mirror, "Mirror");
		LanguageRegistry.addName(mirrorSupport, "Mirror Support");
		LanguageRegistry.addName(silverSpool, "Silver Spool");
		LanguageRegistry.addName(silverWafer, "Silver Wafer");
		LanguageRegistry.addName(sodiumNitrate, "Sodium Nitrate");
		LanguageRegistry.addName(temperedGlass, "Tempered Glass");
		
		// Setup recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(angleIronCross, "a a", " a ", "a a", 'a', angleIron));
		GameRegistry.addRecipe(new ShapedOreRecipe(mirrorSupport, "aaa", "www", " i ", 'a', adhesive, 'w', ironWafer, 'i', angleIronCross));
		GameRegistry.addRecipe(new ShapedOreRecipe(lens, "tst", "t t", "tst", 't', temperedGlass, 's', silverWafer));
		
		if (Loader.isModLoaded("IC2"))
		{
			System.out.println("Loaded ");
			//GameRegistry.addRecipe(new ShapedOreRecipe(adhesive, "rrr", "rwr", "rrr", 'r', "itemRubber", 'w', "waterCell"));
			GameRegistry.addRecipe(new ShapedOreRecipe(adhesive, "rrr", "rrr", "rrr", 'r', "itemRubber", 'w', OreDictionary.getOreName(Item.bucketWater.itemID)));
		}
	}
}
