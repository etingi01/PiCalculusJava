package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc1 implements CSProcess {
	 public String nameProcess = "ParallelProc1";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ParallelProc1( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 while(true){ 
			 ChannelValue z = new ChannelValue();
			 z.type = "Tl";
			 z = (ChannelValue) topa.channel.in().read();
			 System.out.println("ParallelProc1 - z received channel through topa");
			 Repl1 rep = new Repl1(z, topa, spotcheck, x, send, fee);
			 ProcessManager manager = new ProcessManager(rep);
			 manager.start();
			 }
	 }
}
