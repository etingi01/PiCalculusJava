package PiCalculusWithProcesses;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelOutput;

public class FinalOutput implements CSProcess  {
	ChannelInput k;
	public FinalOutput(ChannelInput temp){
		this.k=temp;
	}
	@Override
	public void run() {
		k.read();
		k.read();
			
	}
	

}

