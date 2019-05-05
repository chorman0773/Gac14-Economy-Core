package github.chorman0773.gac14.economy.core.player;

import github.chorman0773.gac14.economy.core.Gac14EconomyCore;
import github.chorman0773.gac14.player.PlayerProfileEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class PlayerEventHandler {
	private static final Gac14EconomyCore EcoCore = Gac14EconomyCore.getInstance();
	
	private PlayerEventHandler() {
		// TODO Auto-generated constructor stub
	}

	@SubscribeEvent
	public static void CreatePlayerProfile(PlayerProfileEvent.Create ev) {
		
		ev.player.registerKey(new PlayerInfoTagBalance(EcoCore.getModule(),EcoCore.getInitialBalance()) );
		
	}
	
}
