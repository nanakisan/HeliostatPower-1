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

import net.minecraft.creativetab.CreativeTabs;

import com.rakosmanjr.heliostatpower.block.ModBlocks;
import com.rakosmanjr.heliostatpower.core.handlers.GuiHandler;
import com.rakosmanjr.heliostatpower.core.handlers.LocalizationHandler;
import com.rakosmanjr.heliostatpower.core.helpers.LogHelper;
import com.rakosmanjr.heliostatpower.core.proxy.CommonProxy;
import com.rakosmanjr.heliostatpower.creativetab.CreativeTabHeliostatPower;
import com.rakosmanjr.heliostatpower.items.ModItems;
import com.rakosmanjr.heliostatpower.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class HeliostatPower
{
	@Instance(Reference.MOD_ID)
	public static HeliostatPower instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static CreativeTabs tabsHP = new CreativeTabHeliostatPower(
			CreativeTabs.getNextID(), Reference.MOD_NAME);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		LogHelper.Init();
		LocalizationHandler.LoadLanguages();
		
		ModBlocks.Init();
		ModItems.Init();
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		NetworkRegistry.instance().registerGuiHandler(instance,
				new GuiHandler());
		
		proxy.RegisterItemsAndBlocks();
		proxy.RegisterRenderers();
		proxy.InitTileEntities();
		proxy.RegisterWorldGen();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
