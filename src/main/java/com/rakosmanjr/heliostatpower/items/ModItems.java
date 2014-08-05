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

import com.rakosmanjr.heliostatpower.lib.Strings;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
		angleIron = new ItemAngleIron(Strings.ANGLE_IRON_NAME);
		angleIronCross = new ItemAngleIronCross(Strings.ANGLE_IRON_CROSS_NAME);
		mirrorSupport = new ItemMirrorSupport(Strings.MIRROR_SUPPORT_NAME);
		mirror = new ItemMirror(Strings.MIRROR_NAME);
		lens = new ItemLens(Strings.LENS_NAME);
		highPressureIronWafer = new ItemHighPressureIronWafer(Strings.HIGH_PRESSURE_IRON_WAFER_NAME);
		ironWafer = new ItemIronWafer(Strings.IRON_WAFER_NAME);
		silverWafer = new ItemSilverWafer(Strings.SILVER_WAFER_NAME);
		copperWafer = new ItemCopperWafer(Strings.COPPER_WAFER_NAME);
		temperedGlass = new ItemTemperedGlass(Strings.TEMPERED_GLASS_NAME);
		copperSpool = new ItemCopperSpool(Strings.COPPER_SPOOL_NAME);
		silverSpool = new ItemSilverSpool(Strings.SILVER_SPOOL_NAME);
		adhesive = new ItemAdhesive(Strings.ADHESIVE_NAME);
		sodiumNitrate = new ItemSodiumNitrate(Strings.SODIUM_NITRATE_NAME);

		// Setup recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(angleIronCross, "a a", " a ", "a a", 'a', angleIron));
		GameRegistry.addRecipe(new ShapedOreRecipe(mirrorSupport, "aaa", "www", " i ", 'a', adhesive, 'w', ironWafer, 'i', angleIronCross));
		GameRegistry.addRecipe(new ShapedOreRecipe(lens, "tst", "t t", "tst", 't', temperedGlass, 's', silverWafer));

		if (Loader.isModLoaded("IC2"))
		{
			System.out.println("Loaded ");
			//GameRegistry.addRecipe(new ShapedOreRecipe(adhesive, "rrr", "rwr", "rrr", 'r', "itemRubber", 'w', "waterCell"));
			GameRegistry.addRecipe(new ShapedOreRecipe(adhesive, "rrr", "rrr", "rrr", 'r', "itemRubber", 'w', Items.water_bucket));
		}
	}
}
