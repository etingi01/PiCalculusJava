package test3;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemBoot implements CSProcess {
	 public String nameSystem = "SystemBoot" ;
	 public ChannelValue fee ;
	 public ChannelValue f1 ;
	 public ChannelValue u1 ;
	 public ChannelValue f2 ;
	 public ChannelValue send ;
	 public ChannelValue u2 ;
	 public ChannelValue f3 ;
	 public ChannelValue u3 ;
	 public ChannelValue f4 ;
	 public ChannelValue u4 ;
		 public SystemBoot (){
			 fee = new ChannelValue(); 
			 fee.type = "ETP[Fee]" ;
			 f1 = new ChannelValue(); 
			 f1.type = "unknown" ;
			 u1 = new ChannelValue(); 
			 u1.type = "unknown" ;
			 f2 = new ChannelValue(); 
			 f2.type = "unknown" ;
			 send = new ChannelValue(); 
			 send.type = "ETP[ETP[Fee]]" ;
			 u2 = new ChannelValue(); 
			 u2.type = "unknown" ;
			 f3 = new ChannelValue(); 
			 f3.type = "unknown" ;
			 u3 = new ChannelValue(); 
			 u3.type = "unknown" ;
			 f4 = new ChannelValue(); 
			 f4.type = "unknown" ;
			 u4 = new ChannelValue(); 
			 u4.type = "unknown" ;
		}
		 public void run() {
			 Processgroup groupGrPr = new Processgroup(fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 CSProcess[] groupprocesses = new CSProcess[]{groupGrPr};
			 atomicGroup group = new atomicGroup(groupprocesses, "group");
			 group.run();
		 }
		 public static void main(String[] args){
			  SystemBoot system = new SystemBoot ();
			 system.run();
		 }
}
