package github.chorman0773.gac14.economy.core.transaction;

import java.util.UUID;

import org.apache.logging.log4j.core.util.UuidUtil;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

/**
 * A Transaction refers to an exchange between 2 players or a player and the server.
 * A Transaction has 4 phases: Initialization, Start, Completion, and Finalization.
 * The Exchange occurs during Completion. 
 * @author chorm
 */
public abstract class Transaction {

	/**
	 * Called During Phase 1 of the Transaction
	 * Any resources should be acquired during this phase.
	 */
	public final void init() {
		if(MinecraftForge.EVENT_BUS.post(new TransactionEvent.Initialize(this))) 
			doInit();
		
	}
	
	/**
	 * Obtains the name of the Provider which created the Transaction.
	 * The Provider name is used to identify the kind of transaction without knowing the source of the transaction.
	 * <br/><br/>
	 * There is a list of Standard Providers in the gac14 domain, no other providers may be registered in this domain
	 * <ul>
	 * <li>gac14:eco/exchange -&gt; The Admin Shop using the exchange</li>
	 * <li>gac14:eco/auction -&gt; The Auction House</li>
	 * <li>gac14:eco/admin_shop -&gt; The Admin Shop using fixed prices</li>
	 * <li>gac14:eco/player_shop -&gt; A Player Shop</li>
	 * <li>gac14:eco/trade -&gt; The Trade System</li>
	 * </ul>
	 * <br/>
	 * See Gac14 Specification for details about these providers
	 * @return The name of the Provider
	 */
	public abstract ResourceLocation getProviderName();
	
	/**
	 * Obtains the Principal ID of the Seller, or the originator of the transaction.
	 * The Server is identified by the NIL UUID
	 * Who is the seller in a transaction is defined by the provider.
	 * @return The UUID of the Seller
	 */
	public abstract UUID getSeller();
	
	/**
	 * Obtains the Principal ID of the Reciever.
	 * Who the Reciever is in a transaction is defined by the provider.
	 * @return
	 */
	public abstract UUID getReciever();
	
	/**
	 * Obtains the ID of the Transaction.
	 * @return The Transaction ID.
	 */
	public final UUID getTransId() {
		return TransactionId;
	};
	
	/**
	 * Starts the Transaction, and checks if the participants (Seller and Reciever) have the resources available to complete.
	 */
	public final void start() {
		
		TransactionEvent.Start evStart = new TransactionEvent.Start(this);
		MinecraftForge.EVENT_BUS.post(evStart);
		switch(evStart.getResult()) {
		case ALLOW:
			doStart(true);
			break;
		case DEFAULT:
			doStart(false);
			break;
		case DENY:
			break;
		default:
			throw new Error();		
		}
	};
	
	/**
	 * Completes the Transaction and transfers the resources between the participants.
	 */
	public final void complete() {
		
		TransactionEvent.Complete evComplete = new TransactionEvent.Complete(this);
		MinecraftForge.EVENT_BUS.post(evComplete);
		switch(evComplete.getResult()) {
			case ALLOW:
				doComplete(true);
				break;
			case DEFAULT:
				doComplete(false);
				break;
			case DENY:
				break;
			default:
				throw new Error();		
		}
	};
	
	/**
	 * Finalizes the Transaction between 2 participants.
	 */
	 public final void finish() {
		MinecraftForge.EVENT_BUS.post(new TransactionEvent.Finish(this));
		doFinish();
	}
	
	private final UUID TransactionId;
	
	protected Transaction() {
		TransactionId = UuidUtil.getTimeBasedUuid();
	}
	
	protected void doInit() {
		
	}
	
	protected void doFinish() {
		
	}
	
	protected abstract void doStart(boolean force);
	
	protected abstract void doComplete(boolean force);
}
