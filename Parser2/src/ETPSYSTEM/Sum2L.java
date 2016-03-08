package ETPSYSTEM;

import org.jcsp.lang.CSProcess;

public class Sum2L implements CSProcess  {
	public ChannelValue spotcheck;
	public ChannelValue topa;
	public ChannelValue read;
	ChannelValue newl;
	public Sum2L(ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue newl){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
		this.newl=newl;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Loc value = new Loc();
		value.Location="Nicosia 25-60";
		newl.channel.out().write(value);
		System.out.println("ProcessL - send through newl a loc");
		

	}


}
