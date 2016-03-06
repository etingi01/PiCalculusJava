package ETPSYSTEM;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;

public class ProcessO implements CSProcess  {
	public ChannelValue spotcheck;
	public ChannelValue topa;
	public ChannelValue read;

	public ProcessO(ChannelValue spotcheck, ChannelValue topa, ChannelValue read){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
	/*	this.s=new ChannelValue();
		this.s.type="Tx";
		this.ls=new ChannelValue();
		this.ls.type="Tl";*/
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Sum1O sum1 = new Sum1O(spotcheck, topa, read);
		Sum2O sum2 = new Sum2O(spotcheck, topa, read);

		CSProcess[] Asums = new CSProcess[]{sum1, sum2};
		Parallel par = new Parallel(Asums);
		par.run();

	}

}
