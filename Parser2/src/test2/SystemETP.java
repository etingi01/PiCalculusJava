package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemETP implements CSProcess {
	 public String nameSystem = "SystemETP";
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
		 public SystemETP(){
			 x = new ChannelValue(); 
			 x.type = "unknown" ;
			 send = new ChannelValue(); 
			 send.type = "ETP[ETP[Fee]]" ;
			 fee = new ChannelValue(); 
			 fee.type = "ETP[Fee]" ;
		 }
		 public void run() {
			 ChannelValue topa = new ChannelValue();
			 topa.type = "Tpa";
			 ChannelValue spotcheck = new ChannelValue();
			 spotcheck.type = "Tsc";
			 ProcessPA PAGrPr = new ProcessPA(spotcheck, topa, x, send, fee);
			 CSProcess[] PAprocesses = new CSProcess[]{PAGrPr};
			 atomicGroup PA = new atomicGroup(PAprocesses, "PA");
			 SystemCar Car = new SystemCar(spotcheck, topa, x, send, fee);
			 CSProcess[] parParts = new CSProcess[]{PA,Car};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
		 public static void main(String[] args){
			 SystemETP system = new SystemETP();
			 system.run();
		 }
	 }
