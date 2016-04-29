package test2;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc2 implements CSProcess {
	 public String nameProcess = "ParallelProc2";
	 public ChannelValue spotcheck ;
	 public ChannelValue topa ;
	 public ChannelValue x ;
	 public ChannelValue send ;
	 public ChannelValue fee ;
	 public ParallelProc2( ChannelValue spotcheck, ChannelValue topa, ChannelValue x, ChannelValue send, ChannelValue fee){
			 this.spotcheck = spotcheck;
			 this.topa = topa;
			 this.x = x;
			 this.send = send;
			 this.fee = fee;
	 }
	 public void run() {
JOptionPane.showMessageDialog(null, "Communication with an external system, through channel send object sent - fee : " + fee);
	 }
}
