package github.chorman0773.gac14.economy.core.player;

import github.chorman0773.gac14.economy.core.EcoCoreModule;
import github.chorman0773.gac14.player.PlayerInfoTag;
import net.minecraft.nbt.NBTTagDouble;

public final class PlayerInfoTagBalance
		extends PlayerInfoTag<EcoCoreModule, Double, NBTTagDouble, PlayerInfoTagBalance> {

	public PlayerInfoTagBalance(EcoCoreModule mod, double initial) {
		super(mod, "money", initial, Double.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public NBTTagDouble writeToNbt() {
		// TODO Auto-generated method stub
		return new NBTTagDouble(this.get());
	}

	@Override
	protected void readFromNbt(NBTTagDouble tag) {
		// TODO Auto-generated method stub
		this.set(tag.getDouble());
	}

}
