package ETPSYSTEM;
import org.jcsp.lang.*;

public class Tl {
	public Any2AnyChannel ETP;
	public Loc LocBase;
	
	public Tl(){
		ETP = Channel.createAny2Any();
		LocBase = new Loc();
	}
}
