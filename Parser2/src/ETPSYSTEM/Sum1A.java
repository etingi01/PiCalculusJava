package ETPSYSTEM;

import org.jcsp.lang.CSProcess;

public class Sum1A implements CSProcess  {
	ChannelValue spotcheck;
	ChannelValue topa;
	ChannelValue z;
	Loc l;
	
	
	public Sum1A(ChannelValue spotcheck, ChannelValue topa){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.z=new ChannelValue();
		this.z.type="Tl";
		l = new Loc();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			this.z=(ChannelValue) this.topa.channel.in().read();
			System.out.println("process A - sum1: Received z channel through topa");
			this.l=(Loc)this.z.channel.in().read();
			System.out.println("process A - sum1: Received l Loc through z channel");
		}
	}

}
