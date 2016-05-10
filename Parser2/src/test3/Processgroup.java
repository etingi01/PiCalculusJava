package test3;
import org.jcsp.lang.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Processgroup implements CSProcess {
	 public String nameProcess = "Processgroup";
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
	 public Processgroup( ChannelValue fee, ChannelValue f1, ChannelValue u1, ChannelValue f2, ChannelValue send, ChannelValue u2, ChannelValue f3, ChannelValue u3, ChannelValue f4, ChannelValue u4){
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
			 ChannelValue d4 = new ChannelValue();
			 d4.type = "fdown";
			 ChannelValue up2 = new ChannelValue();
			 up2.type = "fup";
			 ChannelValue up1 = new ChannelValue();
			 up1.type = "fup";
			 ChannelValue up4 = new ChannelValue();
			 up4.type = "fup";
			 ChannelValue up3 = new ChannelValue();
			 up3.type = "fup";
			 ChannelValue d1 = new ChannelValue();
			 d1.type = "fdown";
			 ChannelValue d2 = new ChannelValue();
			 d2.type = "fdown";
			 ChannelValue d3 = new ChannelValue();
			 d3.type = "fdown";
			 ParallelProc1 ParP0 = new ParallelProc1(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 ParallelProc12 ParP1 = new ParallelProc12(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 CSProcess[] parParts = new CSProcess[]{ParP0,ParP1};
			 Parallel par = new Parallel(parParts);
			 par.run();
	 }
}
