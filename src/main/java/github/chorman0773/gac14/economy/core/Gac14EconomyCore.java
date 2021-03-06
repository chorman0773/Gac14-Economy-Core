package github.chorman0773.gac14.economy.core;


import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

import github.chorman0773.gac14.Gac14Core;
import github.chorman0773.gac14.Gac14Module;
import github.chorman0773.gac14.economy.core.player.CapabilityBalance;



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
        MinecraftForge.EVENT_BUS.addListener(this::setup);
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
    
    private void checkConfig() {
    	try {
    		if(config==null) 
    			config = Gac14Core.getInstance().getConfig("gac14:economy/core");
    	}catch(IOException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    private JsonObject config;
    public double getInitialBalance() {
    	checkConfig();
    	return config.get("init-bal").getAsDouble();
    }
    
    private void setup(FMLCommonSetupEvent event) {
    	CapabilityManager.INSTANCE.register(CapabilityBalance.class, new IStorage<CapabilityBalance>() {

			@Override
			public INBT writeNBT(Capability<CapabilityBalance> capability, CapabilityBalance instance, Direction side) {
				// TODO Auto-generated method stub
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<CapabilityBalance> capability, CapabilityBalance instance, Direction side, INBT nbt) {
				instance.deserializeNBT((DoubleNBT)nbt);
			}
    		
    	}, ()->new CapabilityBalance(getInitialBalance()));
    }
    
}
