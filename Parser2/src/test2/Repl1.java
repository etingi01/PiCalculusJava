package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Repl1 implements CSProcess {
	 public String nameProcess = "Repl1";
	 public ChannelValue z ;
	 public ChannelValue topa ;
	 public ChannelValue spotcheck ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public Repl1( ChannelValue z, ChannelValue topa, ChannelValue spotcheck, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.z = z;
			 this.topa = topa;
			 this.spotcheck = spotcheck;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
			 loc  l = new loc();
			 	System.out.println("ParallelProc1-repl1");
			 String namel =  JOptionPane.showInputDialog(null, "Communication with an external system, through channel z - Give the value of l: ");
			 l.setnametmimatos(namel);// = namel ; 
	 }
}
