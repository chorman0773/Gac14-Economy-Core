package github.chorman0773.gac14.economy.core.transaction;

import java.util.UUID;

import org.apache.logging.log4j.core.util.UuidUtil;

import net.minecraftforge.common.MinecraftForge;

public abstract class Transaction {

	
	public final void init() {
		if(MinecraftForge.EVENT_BUS.post(new TransactionEvent.Initialize(this))) 
			doInit();
		
	}
	
	public abstract UUID getSeller();
	
	public abstract UUID getReciever();
	
	public final UUID getTransId() {
		return TransactionId;
	};
	
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
	
	 public final void finish() {
		MinecraftForge.EVENT_BUS.post(new TransactionEvent.Finish(this));
		
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
