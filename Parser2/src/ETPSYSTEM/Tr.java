package ETPSYSTEM;
import org.jcsp.lang.*;

public class Tr {
	public Any2AnyChannel Car;
	public Tl TlChannel;
	public Tr(){
		Car = Channel.createAny2Any();
		TlChannel = new Tl();
	}
}
