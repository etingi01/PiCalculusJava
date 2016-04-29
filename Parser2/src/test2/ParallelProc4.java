package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc4 implements CSProcess {
	 public String nameProcess = "ParallelProc4";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue read ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ParallelProc4( ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.read = read;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 while(true){ 
			 ChannelValue loc = new ChannelValue();
			 loc.type = "Tl";
			 loc = (ChannelValue) read.channel.in().read();
			 System.out.println("ParallelProc4 - loc received channel through read");
			 Repl3 rep = new Repl3(loc, read, topa, spotcheck, x, send, fee);
			 ProcessManager manager = new ProcessManager(rep);
			 manager.start();
			 }
	 }
}
