package PiCalculusWithProcesses;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;

public class repSum2 implements CSProcess  {
	ChannelValue c= new ChannelValue();

	public repSum2(ChannelValue cC){
		this.c=cC;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	/*     final CSTimer tim = new CSTimer ();
	     long timeout = tim.read ();
	     for (int i = 0; i < 10; i++) {
	         timeout += 1000;
	         tim.after (timeout);         // every second ...
	       }*/
	    
		this.c.aValue=(Integer)5;
		c.channel.out().write(this.c.aValue);
		System.out.println("Summation2 - repl: Wrote in the new channel of C");

	}

}
