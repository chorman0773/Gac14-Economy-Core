package github.chorman0773.gac14.economy.core.player;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityBalance implements ICapabilitySerializable<DoubleNBT> {

	
	@CapabilityInject(CapabilityBalance.class)
	public static final Capability<CapabilityBalance> CAP = null;
	
	private double value;
	
	public CapabilityBalance(double initial) {
		this.value = initial;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return CAP.orEmpty(cap, LazyOptional.of(()->this));
	}

	@Override
	public DoubleNBT serializeNBT() {
		// TODO Auto-generated method stub
		return new DoubleNBT(value);
	}

	@Override
	public void deserializeNBT(DoubleNBT nbt) {
		value = nbt.getDouble();
	}

}
