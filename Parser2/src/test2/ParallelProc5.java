package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc5 implements CSProcess {
	 public String nameProcess = "ParallelProc5";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue read ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ParallelProc5( ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.read = read;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 while(true){ 
			 ChannelValue s = new ChannelValue();
			 s.type = "Tx";
			 s = (ChannelValue) spotcheck.channel.in().read();
			 System.out.println("ParallelProc5: s received channel through spotcheck");
			 Repl4 rep = new Repl4(s, read, topa, spotcheck, x, send, fee);
			 ProcessManager manager = new ProcessManager(rep);
			 manager.start();
			 }
	 }
}
