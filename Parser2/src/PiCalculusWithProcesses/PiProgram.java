package PiCalculusWithProcesses;

import org.jcsp.lang.*;
public class PiProgram {

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		
		Any2AnyChannel b = Channel.createAny2Any();
		Any2AnyChannel k = Channel.createAny2Any();
		
		
		Summation1 sum1 = new Summation1(b.in(), k.out());
		Summation2 sum2 = new Summation2(k.in(), b.out());
		Summation3 sum3 = new Summation3(k.in(), b.out());
		FinalOutput screen = new FinalOutput(b.in());

		CSProcess[] parArray = new CSProcess[] {sum1, sum2, sum3, screen};
		Parallel par = new Parallel(parArray);
		par.run();
	}
	
}
