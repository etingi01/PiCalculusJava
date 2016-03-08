package PiCalculusWithProcesses;
import org.jcsp.lang.*;


import java.util.Timer;
import java.util.TimerTask;
public class Summation1 implements CSProcess  {

	ChannelValue b = new ChannelValue();
	ChannelValue a = new ChannelValue();
	Integer p;
	public Summation1(ChannelValue aCentric, ChannelValue bCentric){
		this.b = bCentric;
		this.a=aCentric;
		this.a.aValue=(Integer)15;
	}
	public void run(){
		
		while(true){
		this.b.aValue=a;
		this.b.channel.out().write(this.b.aValue);
		manager m = new manager(a);
		ProcessManager managerP = new ProcessManager(m);
		System.out.println("Summation 1: Wrote to channel b: " + "Channel a");
	    managerP.start ();

		}
	/*	p=(Integer) this.a.channel.in().read();
		System.out.println("Summation 1: Read from channel a: " + p);*/
		//}
		//this.b.out().write(a);
	}
	public void repeatedSequence(){

	}
	
}
