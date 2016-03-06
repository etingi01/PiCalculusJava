package PiCalculusWithProcesses;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.Crew;



public class Summation2 implements CSProcess  {
	ChannelValue b= new ChannelValue();
	ChannelValue c= new ChannelValue();
	Integer p;
	
	public Summation2(ChannelValue bCentric, ChannelValue cCentric){
		this.b = bCentric;
		this.c=cCentric;
		this.c.aValue=10;
	}
	@SuppressWarnings("unchecked")
	public void run(){
		this.c= (ChannelValue) b.channel.in().read();
		System.out.println("Summation2: I read from Channel b" + this.c.aValue.toString());
		c.channel.out().write(this.c.aValue);
		System.out.println("Summation2: Wrote in the new channel of C");

	}
	

}
