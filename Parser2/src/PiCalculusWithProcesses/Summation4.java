package PiCalculusWithProcesses;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.Crew;

public class Summation4 implements CSProcess  {
	ChannelInput k;
	ChannelOutput b;
	ChannelInput data;
	private final Crew crew = new Crew ();

	public Summation4(ChannelInput kCentric, ChannelOutput bCentric){
		this.b = bCentric;
		this.k=kCentric;
	}
	@SuppressWarnings("unchecked")
	public void run(){

		crew.startWrite();
		b.write(new Integer(200));
		crew.endWrite();
		System.out.println("\nSummation 2: Sent b to sum 1." );
		Integer d = (Integer) k.read();		
		System.out.println("Summation 2: Read from channel k: " + d);

	}
	

}

