package ETPSYSTEM;

import org.jcsp.lang.CSProcess;

public class Sum1O implements CSProcess  {
	ChannelValue spotcheck;
	ChannelValue topa;
	ChannelValue read;
	ChannelValue loc;
	
	public Sum1O(ChannelValue spotcheck, ChannelValue topa, ChannelValue read){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
		this.loc=new ChannelValue();
		this.loc.type="Tl";

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			loc = (ChannelValue)read.channel.in().read();
			System.out.println("processO - sum1: Received loc through read");
			topa.channel.out().write(loc);
			System.out.println("processO - sum1: Sent loc through topa channel");
		}
	}

}
