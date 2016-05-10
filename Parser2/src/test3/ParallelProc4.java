package test3;
import org.jcsp.lang.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class ParallelProc4 implements CSProcess {
	 public String nameProcess = "ParallelProc4";
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
	 public ParallelProc4( ChannelValue d4, ChannelValue up2, ChannelValue up1, ChannelValue up4, ChannelValue up3, ChannelValue d1, ChannelValue d2, ChannelValue d3, ChannelValue fee, ChannelValue f1, ChannelValue u1, ChannelValue f2, ChannelValue send, ChannelValue u2, ChannelValue f3, ChannelValue u3, ChannelValue f4, ChannelValue u4){
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
			 ParallelProc5 ParP0 = new ParallelProc5(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 ParallelProc6 ParP1 = new ParallelProc6(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 ParallelProc7 ParP2 = new ParallelProc7(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 ParallelProc8 ParP3 = new ParallelProc8(d4, up2, up1, up4, up3, d1, d2, d3, fee, f1, u1, f2, send, u2, f3, u3, f4, u4);
			 CSProcess[] parParts = new CSProcess[]{ParP0,ParP1,ParP2,ParP3};
			 Parallel par = new Parallel(parParts);
			 par.run();
	 }
}
