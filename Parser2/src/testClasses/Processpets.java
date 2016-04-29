package testClasses;

import org.jcsp.lang.*;

import ETPSYSTEM.ChannelValue;

import org.jcsp.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Processpets implements CSProcess {
	 public String nameProcess = "Processpets";
	 public ChannelValue door ;
	 public ChannelValue window ;
	 public Processpets( ChannelValue door, ChannelValue window){
			 this.door = door;
			 this.window = window;
	 }
	 public void run() {
			 ChannelValue play = new ChannelValue();
			 play.type = "Tp";
			 ChannelValue bed = new ChannelValue();
			 bed.type = "Tb";
			 String name = "ssdfsf";
			 bed = (ChannelValue) door.channel.in().read();
			 System.out.println("ekane in apo to door");
			 window.channel.out().write( play );
			 System.out.println("ekane out apo to window");
	 }
}
