package github.chorman0773.gac14.economy.core;

import github.chorman0773.gac14.Gac14Module;
import github.chorman0773.gac14.Version;
import net.minecraft.util.ResourceLocation;

public final class EcoCoreModule extends Gac14Module<EcoCoreModule> {

	@Override
	public ResourceLocation getModuleName() {
		// TODO Auto-generated method stub
		return ResourceLocation.makeResourceLocation("gac14:economy/core");
	}

	@Override
	public Version getModuleVersion() {
		// TODO Auto-generated method stub
		
		return new Version("1.0");
	}

}
