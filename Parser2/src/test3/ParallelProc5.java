package test3;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc5 implements CSProcess {
	 public String nameProcess = "ParallelProc5";
	 public ChannelValue d4 ;
	 public ChannelValue up2 ;
	 public ChannelValue up1 ;
	 public ChannelValue up4 ;
	 public ChannelValue up3 ;
	 public ChannelValue d1 ;
	 public ChannelValue d2 ;
	 public ChannelValue d3 ;
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
	 public ParallelProc5( ChannelValue d4, ChannelValue up2, ChannelValue up1, ChannelValue up4, ChannelValue up3, ChannelValue d1, ChannelValue d2, ChannelValue d3, ChannelValue fee, ChannelValue f1, ChannelValue u1, ChannelValue f2, ChannelValue send, ChannelValue u2, ChannelValue f3, ChannelValue u3, ChannelValue f4, ChannelValue u4){
			 this.d4 = d4;
			 this.up2 = up2;
			 this.up1 = up1;
			 this.up4 = up4;
			 this.up3 = up3;
			 this.d1 = d1;
			 this.d2 = d2;
			 this.d3 = d3;
			 this.fee = fee;
			 this.f1 = f1;
			 this.u1 = u1;
			 this.f2 = f2;
			 this.send = send;
			 this.u2 = u2;
			 this.f3 = f3;
			 this.u3 = u3;
			 this.f4 = f4;
			 this.u4 = u4;
	 }
	 public void run() {
		 while(true){
		 if(!(up1.channel.in().pending()&&up2.channel.in().pending())){
			 ChannelValue value = new ChannelValue();
			 value.type = "Integer";
			 System.out.println("Phil 1 is thinking");

			 //String namevalue =  JOptionPane.showInputDialog(null, "Communication with an external system, throu channel think - Give the value of value: ");
		 }else{
			 ChannelValue fork = new ChannelValue();
			 fork.type = "Integer";
			 System.out.println("Phil 1 will take the up1");

			 fork = (ChannelValue) up1.channel.in().read();
			 System.out.println("Phil 1 took the up1");
			 ChannelValue fork2 = new ChannelValue();
			 fork2.type = "Integer";
			 fork2 = (ChannelValue) up2.channel.in().read();
			 ChannelValue value2 = new ChannelValue();
			 value2.type = "Integer";
			 String namevalue2 =  JOptionPane.showInputDialog(null, "Phil 1 is eating ");
			 //value2 = namevalue2 ; 
			 ChannelValue fork1 = new ChannelValue();
			 fork1.type = "Integer";
			 fork1 = (ChannelValue) d1.channel.in().read();
			 ChannelValue fork3 = new ChannelValue();
			 fork3.type = "Integer";
			 fork3 = (ChannelValue) d2.channel.in().read();
			 try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                 
		 }
	 }
	 }
}
