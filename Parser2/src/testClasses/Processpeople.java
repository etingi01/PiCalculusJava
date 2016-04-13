package testClasses;

import org.jcsp.lang.*;

import ETPSYSTEM.ChannelValue;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Processpeople implements CSProcess {
	 public String nameProcess = "Processpeople";
	 public ChannelValue door ;
	 public ChannelValue window ;
	 public Processpeople( ChannelValue door, ChannelValue window){
			 this.door = door;
			 this.window = window;
	 }
	 public void run() {
			 ChannelValue bed = new ChannelValue();
			 bed.type = "Tb";
			 door.channel.out().write( bed );
			 System.out.println("Ekane out apo to door");
			 ChannelValue play = new ChannelValue();
			 play.type = "Tp";
			 play = (ChannelValue) window.channel.in().read();
			 System.out.println("ekane in apo to window");
	 }
	 
}
