package test3;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;

public class atomicGroup implements CSProcess  {
	public CSProcess[] parArray;
	public String nameGroup;
	
	public atomicGroup(CSProcess[] parArray, String group){
		this.parArray=parArray;
		nameGroup=group;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Parallel par = new Parallel(parArray);
		par.run();

	}
}
