package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc3 implements CSProcess {
	 public String nameProcess = "ParallelProc3";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ParallelProc3( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 while(true){ 
			 spotcheck.channel.out().write( x );
			 System.out.println("ParallelProc: x was sent through spotcheck");
			 
			 Repl2 rep = new Repl2(spotcheck, topa, x, send, fee);
			 
			 ProcessManager manager = new ProcessManager(rep);
			 manager.start();
			 }
	 }
}
