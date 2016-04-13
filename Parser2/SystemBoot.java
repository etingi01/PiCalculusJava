import org.jcsp.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class SystemBoot {
	 public String nameSystem = "SystemBoot" ;
	 public ChannelValue channel ;
	 public ChannelValue st ;
	 public ChannelValue read ;
	 public ChannelValue pl ;
		 public SystemBoot (){
			 channel = new ChannelValue(); 
			 channel.type = "Tr" ;
			 st = new ChannelValue(); 
			 st.type = "Tsc" ;
			 read = new ChannelValue(); 
			 read.type = "Tr" ;
			 pl = new ChannelValue(); 
			 pl.type = "Tx" ;
		 }
		 public void run() {
			 ProcessO O = new ProcessO(pl, st, read);
			 ProcessL L = new ProcessL(pl, st, read);
			 CSProcess[] OBEprocesses = new CSProcess[]{O, L};
			 atomicGroup OBE = new atomicGroup(OBEprocesses, "OBE");
			 ProcessM M = new ProcessM(st, read);
			 CSProcess[] MAprocesses = new CSProcess[]{M};
			 atomicGroup MA = new atomicGroup(MAprocesses, "MA");
			 SystemETP ETP = new SystemETP(channel, read);
			 CSProcess[] parParts = new CSProcess[]{OBE,MA,ETP};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
}
