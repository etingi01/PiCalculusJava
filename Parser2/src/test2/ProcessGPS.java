package test2;
import org.jcsp.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ProcessGPS implements CSProcess {
	 public String nameProcess = "ProcessGPS";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue read ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ProcessGPS( ChannelValue spotcheck, ChannelValue topa, ChannelValue read, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.read = read;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 while(true){ 
			 ChannelValue newl = new ChannelValue();
			 newl.type = "Tl";
			 read.channel.out().write( newl );
			 System.out.println("ProcessGPS: newl was sent through read");
			 /*ChannelValue newl2 = new ChannelValue();
			 newl2.type = "Tl";
			 read.channel.out().write( newl2 );
			 System.out.println("ProcessGPS: newl2 was sent through read");*/

			 Repl5 rep = new Repl5(topa, read, spotcheck, newl, x, send, fee);
			 ProcessManager manager = new ProcessManager(rep);
			 manager.start();
			 }
	 }
}
