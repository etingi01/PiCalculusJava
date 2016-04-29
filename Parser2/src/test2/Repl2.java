package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Repl2 implements CSProcess {
	 public String nameProcess = "Repl2";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public Repl2( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 ChannelValue y = new ChannelValue();
			 y.type = "Tl";
			 y = (ChannelValue) x.channel.in().read();
			 System.out.println("ParallelProc3 - repl2 - y received channel through x");

			 loc  ls = new loc();
			 String namels =  JOptionPane.showInputDialog(null, "Communication with an external system, throu channel y - Give the value of ls: ");
			 ls.setnametmimatos(namels);// = namels ; 
	 }
}
