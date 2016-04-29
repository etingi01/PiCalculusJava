package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Repl4 implements CSProcess {
	 public String nameProcess = "Repl4";
	 public ChannelValue s ;
	 public ChannelValue read ;
	 public ChannelValue topa ;
	 public ChannelValue spotcheck ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public Repl4( ChannelValue s, ChannelValue read, ChannelValue topa, ChannelValue spotcheck, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.s = s;
			 this.read = read;
			 this.topa = topa;
			 this.spotcheck = spotcheck;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 ChannelValue ls = new ChannelValue();
			 ls.type = "Tl";
			 ls = (ChannelValue) read.channel.in().read();
			 System.out.println("ParallelProc5 - repl4:  ls received channel through read");

			 s.channel.out().write( ls );
			 System.out.println("ParallelProc5 - repl4: - ls has been sent through s");

	 }
}
