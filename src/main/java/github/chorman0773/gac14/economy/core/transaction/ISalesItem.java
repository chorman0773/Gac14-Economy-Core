package github.chorman0773.gac14.economy.core.transaction;

import java.util.UUID;

import net.minecraft.util.ResourceLocation;

/**
 * An ISalesItem refers to something that is being sold.
 * It is possible to create a transaction 
 * @author chorm
 */
public interface ISalesItem {
	
	/**
	 * @return the id of the seller, usually the NIL ID for the Server.
	 */
	UUID getSeller();
	
	/**
	 * Obtains the cost of some amount of an item
	 * @param amount the amount being requested
	 * @return returns the cost for that amount, or Double.NEGATIVE_INFINITY if the requested amount is unavailable.
	 */
	double getCost(int amount);
	
	/**
	 * Creates a transaction for a particular buyer, where the Seller of the Transaction is getSeller(), and the buyer is buyer.
	 * @param buyer The buyer for the transaction
	 * @param amount the quantity of the item being purchased
	 * @return A new transaction, which causes amount of the item to be transfered to the buyer, in exchange for getCost(amount) dollars, or null if the requested amount is not available.  
	 */
	Transaction createTransaction(UUID buyer, int amount);
	
	/**
	 * Obtains the name of Item being sold.
	 * If the item is in the minecraft domain, then it must be a vanilla item, or a loot table.
	 * If the item is in the gac14 domain, then it must be a loot table, or an extension item.
	 * Otherwise the type of item being sold is implementation defined.
	 * @return The name of the item being sold
	 */
	ResourceLocation getItemName();
	
	/**
	 * Obtains the name of the provider for the transaction.
	 * @see Transaction#getProviderName()
	 */
	ResourceLocation getProviderName();
	
	/**
	 * An Equivalence is mapping between a Source Item, which does not have a corresponding ISalesItem and a Resultant Item, which does.
	 * Despite the names, this mapping is reflexive, that is, either item may be used in-place of the other.
	 * There is also a quantity modifier, which defines the quantity
	 *   of the resultant item which corresponds to 1 of the source item.
	 * @author chorm
	 */
	public static interface Equivalence{ 
		
		/**
		 * Obtains the name of Item which does not have a corresponding ISalesItem
		 * If the item is in the minecraft domain, then it must be a vanilla item, or a loot table.
		 * If the item is in the gac14 domain, then it must be a loot table, or an extension item.
		 * Otherwise the type of item is implementation defined.
		 * @return the name of the source item
		 */
		ResourceLocation getSourceName();
		
		/**
		 * Obtains the name of Item which does have a corresponding ISalesItem
		 * If the item is in the minecraft domain, then it must be a vanilla item, or a loot table.
		 * If the item is in the gac14 domain, then it must be a loot table, or an extension item.
		 * Otherwise the type of item is implementation defined.
		 * @return the name of the resultant item
		 */
		ResourceLocation getResultName();
		
		/**
		 * @return the quantity of the resultant item represented by the source item
		 */
		double getQuantity();
		
		
	};
	
	/**
	 * Gets the minimum advertised quantity. 
	 * This value must be less-than or equal-to the maximum advertised quantity.
	 * Any attempts to request a quantity that is less-than this value MAY or MAY NOT be honored (this is implementation-defined).
	 * If the request is not honored, then the appropriate error value SHOULD be returned.
	 * @return The minimum quantity
	 */
	int getMinQuantity();
	
	/**
	 * Gets the maximum advertised quantity. 
	 * Any attempts to request a quantity that is greater-than this value SHOULD NOT be honored.
	 * If the request is not honored, then the appropriate error value SHOULD be returned.
	 * @return The maximum quantity
	 */
	int getMaxQuantity();
	
	
	
	
}

