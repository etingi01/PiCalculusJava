package PiCalculusWithProcesses;

import org.jcsp.lang.CSProcess;

public class manager implements CSProcess  {
	ChannelValue a = new ChannelValue();
	Integer p;

	public manager(ChannelValue aC){
		this.a=aC;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
 		p=(Integer) this.a.channel.in().read();
		System.out.println("Summation 1 - repl: Read from channel a: " + p);

	}

}
