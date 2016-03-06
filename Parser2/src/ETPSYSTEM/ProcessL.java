package ETPSYSTEM;

import org.jcsp.lang.CSProcess;

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
			this.read.channel.out().write(newl);
			System.out.println("Process L send newl channel of type " + newl.type);
		}
		
	}

}
