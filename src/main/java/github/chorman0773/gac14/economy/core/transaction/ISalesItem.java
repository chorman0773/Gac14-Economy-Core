package github.chorman0773.gac14.economy.core.transaction;

import java.util.UUID;

import net.minecraft.util.ResourceLocation;

public interface ISalesItem {
	
	
	UUID getSeller();
	
	double getCost(int amount);
	
	Transaction createTransaction(UUID buyer, int amount);
	
	ResourceLocation getName();
	
	public static interface Equivalence{ 
		
		/**
		 * @return the name of the source item
		 */
		ResourceLocation getFromName();
		
		/**
		 * @return the name of the resultant item
		 */
		ResourceLocation getToName();
		
		/**
		 * @return the quantity of the resultant item represented by the source item
		 */
		double getQuantity();
		
		
	};
	
	int getMinQuantity();
	
	int getMaxQuantity();
	
	
	
	
}

