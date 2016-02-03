package PiCalculusWithProcesses;
import org.jcsp.lang.*;
public class Summation1 implements CSProcess  {
	ChannelInput b;
	ChannelOutput k;
	
	public Summation1(ChannelInput bCentric, ChannelOutput kCentric){
		this.b = bCentric;
		this.k=kCentric;
	}
	public void run(){
	   Integer d = (Integer)b.read();
		System.out.println("Summation 1: Read from channel b: " + d);
		k.write(new Integer(5));	
		System.out.println("I wrote in the k channel. Summation 2 must consume");
	}
	
	
}
