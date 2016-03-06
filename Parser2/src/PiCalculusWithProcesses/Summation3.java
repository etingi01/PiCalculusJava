package PiCalculusWithProcesses;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.Crew;
import org.jcsp.lang.One2AnyChannel;
import org.jcsp.lang.SharedChannelInput;

public class Summation3 implements CSProcess  {
	Any2AnyChannel a;
	Integer k;
	public Summation3(Any2AnyChannel aCentric){
		this.a = aCentric;
	}
	public void run(){
		this.k=(Integer) this.a.in().read();
		System.out.println("Summation 3: I read from the channel A: " + k);

	}
}