package test3;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc7 implements CSProcess {
	 public String nameProcess = "ParallelProc7";
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
	 public ParallelProc7( ChannelValue d4, ChannelValue up2, ChannelValue up1, ChannelValue up4, ChannelValue up3, ChannelValue d1, ChannelValue d2, ChannelValue d3, ChannelValue fee, ChannelValue f1, ChannelValue u1, ChannelValue f2, ChannelValue send, ChannelValue u2, ChannelValue f3, ChannelValue u3, ChannelValue f4, ChannelValue u4){
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
		 if(!(up3.channel.in().pending()&&up4.channel.in().pending())){
			 ChannelValue value4 = new ChannelValue();
			 value4.type = "Integer";
			// System.out.println("Phil 3 is thinking");

			// String namevalue4 =  JOptionPane.showInputDialog(null, "Communication with an external system, throu channel think4 - Give the value of value4: ");
		//	 value4 = namevalue4 ; 
		 }else{
			 ChannelValue fork10 = new ChannelValue();
			 fork10.type = "Integer";
			 fork10 = (ChannelValue) up3.channel.in().read();
			 ChannelValue fork11 = new ChannelValue();
			 fork11.type = "Integer";
			 fork11 = (ChannelValue) up4.channel.in().read();
			 ChannelValue value5 = new ChannelValue();
			 value5.type = "Integer";
			 String namevalue5 =  JOptionPane.showInputDialog(null, "Phil 3 is eating ");
		//	 value5 = namevalue5 ; 
			 ChannelValue fork12 = new ChannelValue();
			 fork12.type = "Integer";
			 fork12 = (ChannelValue) d3.channel.in().read();
			 ChannelValue fork13 = new ChannelValue();
			 fork13.type = "Integer";
			 fork13 = (ChannelValue) d4.channel.in().read();
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
