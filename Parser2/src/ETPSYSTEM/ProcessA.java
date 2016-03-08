package ETPSYSTEM;
import org.jcsp.lang.*;

public class ProcessA implements CSProcess  {
	public ChannelValue spotcheck;
	public ChannelValue topa;
		
	public ProcessA(ChannelValue spotcheck, ChannelValue topa){
		this.spotcheck=spotcheck;
		this.topa=topa;
	}
	
	public void run(){
		Sum1A sum1 = new Sum1A(spotcheck, topa);
		Sum2A sum2 = new Sum2A(spotcheck, topa);

		CSProcess[] Asums = new CSProcess[]{sum1, sum2};
		Parallel par = new Parallel(Asums);
		par.run();
		par.addProcess(sum1);
	}
	
}
