/**
 * HeliostatPower
 *
 * @file HeliostatPower.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower;

import com.rakosmanjr.heliostatpower.block.ModBlocks;
import com.rakosmanjr.heliostatpower.core.handlers.GuiHandler;
import com.rakosmanjr.heliostatpower.core.proxy.CommonProxy;
import com.rakosmanjr.heliostatpower.creativetab.CreativeTabHeliostatPower;
import com.rakosmanjr.heliostatpower.items.ModItems;
import com.rakosmanjr.heliostatpower.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION)
public class HeliostatPower
{
	@Instance(Reference.MOD_ID)
	public static HeliostatPower instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs tabsHP = new CreativeTabHeliostatPower(Reference.MOD_NAME);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModBlocks.Init();
		ModItems.Init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		proxy.RegisterItemsAndBlocks();
		proxy.RegisterRenderers();
		proxy.InitTileEntities();
		proxy.RegisterWorldGen();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
