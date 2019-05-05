package github.chorman0773.gac14.economy.core.transaction;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class TransactionEvent extends Event {

	public TransactionEvent(Transaction transaction) {
		
		this.transaction = transaction;
	}

	public final Transaction transaction;
	
	@Cancelable
	public static final class Initialize extends TransactionEvent{

		public Initialize(Transaction transaction) {
			super(transaction);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	@HasResult
	public static final class Start extends TransactionEvent{

		public Start(Transaction transaction) {
			super(transaction);
			// TODO Auto-generated constructor stub
		}
		
		
	}
	
	@HasResult
	public static final class Complete extends TransactionEvent{

		public Complete(Transaction transaction) {
			super(transaction);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static final class Finish extends TransactionEvent{

		public Finish(Transaction transaction) {
			super(transaction);
			// TODO Auto-generated constructor stub
		}
		
		
	}
	
}
