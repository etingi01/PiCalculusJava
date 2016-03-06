package ETPSYSTEM;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.Channel;

public class Tx {
	public Any2AnyChannel ETP;
	public Tl TlChannel;
	
	public Tx(){
		ETP = Channel.createAny2Any();
		TlChannel = new Tl();
	}
}
