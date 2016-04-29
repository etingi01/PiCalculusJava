package test2;
import org.jcsp.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ProcessOBE implements CSProcess {
	 public String nameProcess = "ProcessOBE";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue read ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ProcessOBE( ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.read = read;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 ParallelProc4 ParP0 = new ParallelProc4(spotcheck, topa, read, x, send, fee);
			 ParallelProc5 ParP1 = new ParallelProc5(spotcheck, topa, read, x, send, fee);
			 CSProcess[] parParts = new CSProcess[]{ParP0,ParP1};
			 PriParallel par = new PriParallel(parParts);
			 par.setPriority(1);
			 par.run();
	 }
}
