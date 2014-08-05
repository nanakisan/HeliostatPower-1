package com.rakosmanjr.heliostatpower.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author thatsIch
 * @since 05.08.2014.
 */
public abstract class RFPoweredHelioStatTile extends BaseHelioStatTile implements IEnergyHandler
{
	protected final IEnergyStorage storage;

	protected RFPoweredHelioStatTile(int capacity)
	{
		this.storage = new EnergyStorage(capacity);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return this.storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
	}
}
