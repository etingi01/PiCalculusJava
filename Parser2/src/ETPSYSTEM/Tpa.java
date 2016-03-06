package ETPSYSTEM;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.Channel;

public class Tpa {
	public Any2AnyChannel ETP;
	public Tl TlChannel;
	
	public Tpa(){
		ETP = Channel.createAny2Any();
		TlChannel = new Tl();
	}
}
