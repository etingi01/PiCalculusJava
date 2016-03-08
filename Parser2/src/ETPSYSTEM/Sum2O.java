package ETPSYSTEM;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ProcessManager;

public class Sum2O implements CSProcess  {
	ChannelValue spotcheck;
	ChannelValue topa;
	ChannelValue read;
	public ChannelValue s;
	public ChannelValue ls;

	public Sum2O(ChannelValue spotcheck, ChannelValue topa, ChannelValue read){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
		this.s=new ChannelValue();
		this.s.type="Tx";
		this.ls=new ChannelValue();
		this.ls.type="Tl";

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while(true){
			this.s=(ChannelValue)this.spotcheck.channel.in().read();
			System.out.println("ProcessO - sum2: Received s through spotcheck");
			repl2O rep = new repl2O(this.spotcheck, this.topa, this.read, this.s, this.ls);
			ProcessManager manager = new ProcessManager(rep);
			manager.start();
			/*this.ls=(ChannelValue)this.read.channel.in().read();
			System.out.println("ProcessO - sum2: Received ls through read channel");
			this.s.channel.out().write(ls);
			System.out.println("ProcessO - sum2: Sent ls through s channel");*/
		//}
	}
	
	public class repl2O implements CSProcess{
		ChannelValue spotcheck;
		ChannelValue topa;
		ChannelValue read;
		public ChannelValue s;
		public ChannelValue ls;

		public repl2O(ChannelValue spotcheck, ChannelValue topa, ChannelValue read,ChannelValue s, ChannelValue ls ){
			this.spotcheck=spotcheck;
			this.topa=topa;
			this.read=read;
			this.s=s;
			this.ls=ls;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Process O- sum2-repl: Came here");
			this.ls=(ChannelValue)this.read.channel.in().read();
			System.out.println("ProcessO - sum2: Received ls through read channel");
			this.s.channel.out().write(ls);
			System.out.println("ProcessO - sum2: Sent ls through s channel");

		}
		
	}
}
