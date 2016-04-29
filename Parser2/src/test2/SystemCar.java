package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemCar implements CSProcess {
	 public String nameSystem = "SystemCar";
		 public ChannelValue spotcheck ;
		 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
		 public SystemCar( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
			 this.spotcheck = spotcheck;
			 this.topa = topa;
		 }
		 public void run() {
			 ChannelValue read = new ChannelValue();
			 read.type = "Tr";
			 ProcessOBE OBEGrPr = new ProcessOBE(spotcheck, topa, read, x, send, fee);
			 CSProcess[] OBEprocesses = new CSProcess[]{OBEGrPr};
			 atomicGroup OBE = new atomicGroup(OBEprocesses, "OBE");
			 ProcessGPS GPSGrPr = new ProcessGPS(spotcheck, topa, read, x, send, fee);
			 CSProcess[] GPSprocesses = new CSProcess[]{GPSGrPr};
			 atomicGroup GPS = new atomicGroup(GPSprocesses, "GPS");
			 CSProcess[] parParts = new CSProcess[]{OBE,GPS};
			 PriParallel par = new PriParallel(parParts);
			 par.setPriority(1);
			 par.run();
		 }
	 }
