package PiCalculusWithProcesses;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInput;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.Crew;
import org.jcsp.lang.ProcessManager;



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
		//while(true){
		this.c= (ChannelValue) b.channel.in().read();
		repSum2 rep = new repSum2(this.c);
		ProcessManager managerP = new ProcessManager(rep);
		System.out.println("Summation2: I read from Channel b " + this.c.aValue.toString());
		managerP.start();
		System.out.println("Summation2: Produced new parallel process and I am still running");
		
		this.c= (ChannelValue) b.channel.in().read();
		repSum2 rep2 = new repSum2(this.c);
		ProcessManager managerP2 = new ProcessManager(rep2);
		System.out.println("Summation2: I read from Channel b " + this.c.aValue.toString());
		managerP2.start();

		//}
	}
	public void repeatedSequence(){

	}
	

}
