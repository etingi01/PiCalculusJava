import org.jcsp.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemCar {
	 public String nameSystem = "SystemCar";
	 public ChannelValue read ;
		 public ChannelValue spotcheck ;
		 public ChannelValue topa ;
		 public SystemCar( ChannelValue spotcheck, ChannelValue topa){
			 read = new ChannelValue(); 
			 read.type = "Tr" ;
			 this.spotcheck = spotcheck;
			 this.topa = topa;
		 }
		 public void run() {
			 ProcessO O = new ProcessO(spotcheck, topa, read);
			 CSProcess[] OBEprocesses = new CSProcess[]{O};
			 atomicGroup OBE = new atomicGroup(OBEprocesses, "OBE");
			 ProcessL L = new ProcessL(spotcheck, topa, read);
			 CSProcess[] GPSprocesses = new CSProcess[]{L};
			 atomicGroup GPS = new atomicGroup(GPSprocesses, "GPS");
			 CSProcess[] parParts = new CSProcess[]{OBE,GPS};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
	 }
