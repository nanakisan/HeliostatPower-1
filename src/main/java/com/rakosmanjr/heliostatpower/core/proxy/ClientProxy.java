/**
 * HeliostatPower
 *
 * @file ClientProxy.java
 *
 * @author rakosmanjr
 * @License Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
package com.rakosmanjr.heliostatpower.core.proxy;

import com.rakosmanjr.heliostatpower.lib.RenderIds;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void RegisterRenderers()
	{
		RenderIds.basicIonicCompressorRenderId = RenderingRegistry.getNextAvailableRenderId();
		
		//MinecraftForgeClient.registerItemRenderer(BlockIds.BASIC_IONIC_COMPRESSOR_DEFAULT, )
	}
	
	@Override
	public void InitTileEntities()
	{
		super.InitTileEntities();
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileBasicIonicCompressor.class, new TileEntityBasicIonicCompressorRenderer());
	}
}
