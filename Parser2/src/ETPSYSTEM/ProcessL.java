package ETPSYSTEM;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.ProcessManager;

public class ProcessL implements CSProcess  {
	public ChannelValue spotcheck;
	public ChannelValue topa;
	public ChannelValue read;
	
	public ProcessL(ChannelValue spotcheck, ChannelValue topa, ChannelValue read){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			ChannelValue newl = new ChannelValue();
			newl.type="Tl";
			Sum1L sum1 = new Sum1L(spotcheck, topa, read, newl);
			Sum2L sum2 = new Sum2L(spotcheck, topa, read, newl);
			CSProcess[] Lsums = new CSProcess[]{sum1, sum2};
			Parallel par = new Parallel(Lsums);
			ProcessManager manager = new ProcessManager(par);
			manager.start();

		/*	this.read.channel.out().write(newl);
			System.out.println("Process L send newl channel of type " + newl.type);*/
		}
		
	}

}
