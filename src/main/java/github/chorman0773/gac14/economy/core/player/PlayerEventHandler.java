package github.chorman0773.gac14.economy.core.player;

import java.io.IOException;

import github.chorman0773.gac14.Gac14Core;
import github.chorman0773.gac14.economy.core.Gac14EconomyCore;
import github.chorman0773.gac14.player.PlayerProfileEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class PlayerEventHandler {
	private static final Gac14EconomyCore EcoCore = Gac14EconomyCore.getInstance();
	
	private static double balance;
	private static boolean hasInitBalance;
	
	private PlayerEventHandler() {
		// TODO Auto-generated constructor stub
	}

	@SubscribeEvent
	public static void attach(AttachCapabilitiesEvent<PlayerEntity> caps ){
		if(!hasInitBalance) {
			balance = EcoCore.getInitialBalance();
			hasInitBalance = true;
		}
		caps.addCapability(new ResourceLocation("gac14:eco/balance"), new CapabilityBalance(balance));
	}
	
}
