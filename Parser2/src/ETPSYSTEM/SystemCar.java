package ETPSYSTEM;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;

public class SystemCar implements CSProcess  {
	public String nameSystem = "Car";
	public ChannelValue read;
	public ChannelValue spotcheck;
	public ChannelValue topa;
	public SystemCar(ChannelValue spotcheck, ChannelValue topa){
		
		//(v read:Tr)
		read = new ChannelValue();
		read.type="Tr";
		
		this.spotcheck=spotcheck;
		this.topa=topa;
		this.read=new ChannelValue();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//atomic Group (v OBE)O
		ProcessO O = new ProcessO(spotcheck, topa, read);
		CSProcess[] OBEprocesses = new CSProcess[]{O};
		atomicGroup OBE = new atomicGroup(OBEprocesses, "OBE");

		//atomic Group (v GPS)L
		ProcessL L = new ProcessL(spotcheck, topa, read);
		CSProcess[] GPSprocesses = new CSProcess[]{L};
		atomicGroup GPS = new atomicGroup(GPSprocesses, "GPS");
		
		CSProcess[] Carsystem = new CSProcess[]{OBE, GPS};
		Parallel par = new Parallel(Carsystem);
		par.run();

	}
	
	
}
