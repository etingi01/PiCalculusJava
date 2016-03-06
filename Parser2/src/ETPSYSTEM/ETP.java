package ETPSYSTEM;

import org.jcsp.lang.*;

public class ETP {
	public String nameSystem = "ETP"; 
	public ChannelValue spotcheck;
	public ChannelValue topa;

	public ETP(){
		spotcheck = new ChannelValue();
		topa=new ChannelValue();
		spotcheck.type="Tsc";
		
		topa.type="Tpa";
		
		//Atomic Group - PA
		ProcessA A = new ProcessA(spotcheck, topa);
		CSProcess[] PAprocesses = new CSProcess[]{A};
		
		atomicGroup PA = new atomicGroup(PAprocesses, "PA");
		
		SystemCar Car = new SystemCar(spotcheck, topa);
		CSProcess[] ETPsystem = new CSProcess[]{PA, Car};
		Parallel par = new Parallel(ETPsystem);
		par.run();
		
	}
	
	public static void main(String[] args){
		ETP mySystem = new ETP();
		System.out.println("ETP");
	}


}
