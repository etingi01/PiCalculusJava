package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Repl5 implements CSProcess {
	 public String nameProcess = "Repl5";
	 public ChannelValue topa ;
	 public ChannelValue read ;
	 public ChannelValue spotcheck ;
	 public ChannelValue newl ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public Repl5( ChannelValue topa, ChannelValue read, ChannelValue spotcheck, ChannelValue newl, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.topa = topa;
			 this.read = read;
			 this.spotcheck = spotcheck;
			 this.newl = newl;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
	 }
}
