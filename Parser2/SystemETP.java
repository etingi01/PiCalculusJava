import org.jcsp.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemETP {
	 public String nameSystem = "SystemETP";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
		 public SystemETP(){
			 spotcheck = new ChannelValue(); 
			 spotcheck.type = "Tsc" ;
			 topa = new ChannelValue(); 
			 topa.type = "Tpa" ;
		 }
		 public void run() {
			 ProcessA A = new ProcessA(spotcheck, topa);
			 CSProcess[] PAprocesses = new CSProcess[]{A};
			 atomicGroup PA = new atomicGroup(PAprocesses, "PA");
			 SystemCar Car = new SystemCar(spotcheck, topa);
			 CSProcess[] parParts = new CSProcess[]{PA,Car};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
	 }
