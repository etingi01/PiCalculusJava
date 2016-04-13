package ETPSYSTEM;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.*;

public class Sum1L implements CSProcess  {
	public ChannelValue spotcheck;
	public ChannelValue topa;
	public ChannelValue read;
	ChannelValue newl;

	public Sum1L(ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue newl){
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=read;
		this.newl=newl;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.read.channel.out().write(newl);
		System.out.println("Process L - sum1l: send newl channel of type " + newl.type);
		
	}

}
