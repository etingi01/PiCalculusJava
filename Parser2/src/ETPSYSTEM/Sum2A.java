package ETPSYSTEM;

import org.jcsp.lang.CSProcess;

public class Sum2A implements CSProcess  {
	ChannelValue spotcheck;
	ChannelValue topa;
	ChannelValue y;
	Loc ls;

	public Sum2A(ChannelValue spotcheck, ChannelValue topa){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.y=new ChannelValue();
		this.y.type="Tl";

		ls = new Loc();

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			ChannelValue x=new ChannelValue();
			x.type="Tx";
			
			this.spotcheck.channel.out().write(x);
			System.out.println("process A - sum2: Send x channel through spotcheck");
			y=(ChannelValue) x.channel.in().read();
			System.out.println("process A - sum2: Received y channel through x");
			ls=(Loc)y.channel.in().read();
			System.out.println("process A - sum2: Received ls Loc base type through y channel");

		}
	}
	

}
