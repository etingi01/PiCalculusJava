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
			 bed = (ChannelValue) door.channel.in().read();
			 window.channel.out().write( play );
	 }
}
