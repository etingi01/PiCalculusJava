package ETPSYSTEM;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.Channel;

public class Tsc {
	public Any2AnyChannel ETP;
	public Tx TxChannel;
	
	public Tsc(){
		ETP = Channel.createAny2Any();
		TxChannel = new Tx();
	}
}
