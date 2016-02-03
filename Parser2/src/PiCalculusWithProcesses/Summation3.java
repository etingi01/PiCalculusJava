package PiCalculusWithProcesses;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.Crew;
import org.jcsp.lang.One2AnyChannel;
import org.jcsp.lang.SharedChannelInput;

public class Summation3 implements CSProcess  {
	ChannelInput k;
	ChannelOutput b;
	private final Crew crew = new Crew ();

	public Summation3(ChannelInput kCentric, ChannelOutput bCentric){
		this.b = bCentric;
		this.k=kCentric;
	}
	public void run(){

		crew.startWrite();
		b.write(new Integer(100));
		crew.endWrite();
		System.out.println("I wrote in the channel");

	}
}