package PiCalculusWithProcesses;

import java.util.ArrayList;

import org.jcsp.lang.*;

public class PiProgram {


	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		
		//Any2AnyChannel b = Channel.createAny2Any();
		ChannelValue b = new ChannelValue();
		ChannelValue a = new ChannelValue();
		ChannelValue c = new ChannelValue();
		
		//Any2AnyChannel c = Channel.createAny2Any();
		ArrayList<String> Prefixes = new ArrayList<String> ();
		Prefixes.add("b");
		
		Summation1 sum1 = new Summation1(a, b);
		Summation2 sum2 = new Summation2(b, c);
		//Summation3 sum3 = new Summation3(a);
		//FinalOutput screen = new FinalOutput(b.in());

		CSProcess[] parArray = new CSProcess[] {sum1, sum2/*, sum3, screen*/};
		Parallel par = new Parallel(parArray);
		par.run();
	}
	
}
