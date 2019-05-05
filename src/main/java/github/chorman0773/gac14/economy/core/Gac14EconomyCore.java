package github.chorman0773.gac14.economy.core;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import github.chorman0773.gac14.Gac14Module;



// The value here should match an entry in the META-INF/mods.toml file
@Mod("gac14-economy-core")
public class Gac14EconomyCore
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Gac14EconomyCore() {
    	
    	assert instance == null;
    	instance = this;
    	
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    	@SubscribeEvent
    	public static void registerModule(RegistryEvent.Register<Gac14Module<?>> register) {
    	   register.getRegistry().register(Gac14EconomyCore.getInstance().getModule());
       }
    }
    
    private static Gac14EconomyCore instance;
    
    private EcoCoreModule Eco = new EcoCoreModule();
    
    public static Gac14EconomyCore getInstance(){
    	assert instance != null;
    	return instance;	
    }
    
    public EcoCoreModule getModule() {
    	return Eco;
    }
    
    public double getInitialBalance() {
    	
    	return 0.0;
    }
    
}
