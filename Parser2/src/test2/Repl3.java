package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Repl3 implements CSProcess {
	 public String nameProcess = "Repl3";
	 public ChannelValue loc ;
	 public ChannelValue read ;
	 public ChannelValue topa ;
	 public ChannelValue spotcheck ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public Repl3( ChannelValue loc, ChannelValue read, ChannelValue topa, ChannelValue spotcheck, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.loc = loc;
			 this.read = read;
			 this.topa = topa;
			 this.spotcheck = spotcheck;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 @SuppressWarnings("unchecked")
	public void run() {
		 	System.out.println("ParallelProc4-repl3: - loc will be sent through topa");
			 topa.channel.out().write( loc );
			 System.out.println("ParallelProc4-repl3: - loc was sent through topa");

	 }
}
