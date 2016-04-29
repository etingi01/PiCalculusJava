package test2;
import org.jcsp.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ProcessPA implements CSProcess {
	 public String nameProcess = "ProcessPA";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ProcessPA( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 ParallelProc1 ParP0 = new ParallelProc1(spotcheck, topa, x, send, fee);
			 ParallelProc2 ParP1 = new ParallelProc2(spotcheck, topa, x, send, fee);
			 ParallelProc3 ParP2 = new ParallelProc3(spotcheck, topa, x, send, fee);
			 CSProcess[] parParts = new CSProcess[]{ParP0,ParP1,ParP2};
			 PriParallel par = new PriParallel(parParts);
			 par.setPriority(10);
			 par.run();
	 }
}
