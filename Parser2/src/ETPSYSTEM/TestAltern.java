package ETPSYSTEM;
import org.jcsp.lang.*;
import org.jcsp.plugNplay.BlackHole;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class TestAltern implements CSProcess{

	public One2OneChannel myh =  Channel.createOne2One();
	public One2OneChannel second = Channel.createOne2One();
	
	
	   
	   
	   public TestAltern (One2OneChannel myh, One2OneChannel second) {
	     this.myh=myh;
	     this.second=second;	     
	   }
	 
	   public void run () {
		   
			class repl2N implements CSProcess{
				One2OneChannel myh;
				One2OneChannel newl;
				One2OneChannel second;
				ChannelValue ins; 
				repl2N(One2OneChannel myh, One2OneChannel newl,One2OneChannel second){
					this.myh=myh;
					this.newl=newl;
					this.second=second;
				}

				@Override
				public void run() {
					// TODO Auto-generated method stub
					newl.out().write("Loc");
					One2OneChannel cc = Channel.createOne2One();
					this.myh.out().write(cc);					
				}
				
			}
			class repl3N implements CSProcess{
				One2OneChannel myh;
				One2OneChannel newl;
				One2OneChannel second;
				repl3N(One2OneChannel myh, One2OneChannel newl,One2OneChannel second){
					this.myh=myh;
					this.newl=newl;
					this.second=second;
				}

				@Override
				public void run() {
					// TODO Auto-generated method stub
					this.myh.out().write(newl);
					this.second.out().write(this.newl);						
				}
				
			}
			while(true){
				One2OneChannel newl = Channel.createOne2One();
			this.second.out().write(newl);
			One2OneChannel newl2 = Channel.createOne2One();
			BlackHole bc = new BlackHole(newl2.in());
			System.out.println("efige apo to prepei na proklithei deadlock");
			
			
			repl2N rep = new repl2N(this.myh, newl, this.second);
			repl3N rep2 = new repl3N(this.myh, newl, this.second);
			CSProcess[] Lsums = new CSProcess[]{rep, rep2};
			Parallel par = new Parallel(Lsums);
			ProcessManager manager = new ProcessManager(par);
			manager.start();
			}

	       
	       
	 
	   }	
		   
	 
	   public static void main(String[] args) {
		// TODO Auto-generated method stub
		   One2OneChannel theChannel = Channel.createOne2One();
		   One2OneChannel c5 = Channel.createOne2One();
		   TestAlt2 t1 = new TestAlt2(theChannel,c5);
		   TestAltern t2 = new TestAltern(theChannel, c5);
		   backup b1 = new backup(theChannel, c5);
		   backup2 b2 = new backup2(theChannel, c5);

		   CSProcess[] pro = {t1, t2, b1, b2};
		   Parallel par = new Parallel (pro);
		   par.run();
	   }

}
  class backup implements CSProcess{
	  One2OneChannel theChannel;
	  One2OneChannel second;
	 public  backup(One2OneChannel theChannel, One2OneChannel second){
		   this.theChannel=theChannel;
		   this.second=second;
	   }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			One2OneChannel newl = (One2OneChannel) this.second.in().read(); 
			System.out.println("Backup1: Received newl through second from first parallel of Replication!");
			///////////	
			backup12 b33 = new backup12(theChannel, second, newl);
			ProcessManager manager = new ProcessManager(b33);
			manager.start();

		}
	}
	   
}
  class backup12 implements CSProcess{
	  One2OneChannel theChannel;
	  One2OneChannel second;
	  One2OneChannel newl;
	 public  backup12(One2OneChannel theChannel, One2OneChannel second, One2OneChannel newl){
		   this.theChannel=theChannel;
		   this.second=second;
		   this.newl=newl;
	   }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Object c = newl.in().read();
		System.out.println("Backup1: Received String through newl - first parallel of Replication! " + c);
		Object s = this.theChannel.in().read();
		System.out.println("Backup1: Received String through myh - first parallel of Replication! " + s);

	}
	  
	  
  }
  
  class backup2 implements CSProcess{
	  One2OneChannel theChannel;
	  One2OneChannel second;
	   backup2(One2OneChannel theChannel, One2OneChannel second){
		   this.theChannel=theChannel;
		   this.second=second;
	   }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			One2OneChannel newl = (One2OneChannel) this.theChannel.in().read();
		System.out.println("Backup2: Received newl channel through myh from the 2nd parallel of replication");
		backup22 b44 = new backup22(theChannel, second);
		ProcessManager pp = new ProcessManager(b44);
		pp.start();
		}
	}	   
  }
  
  class backup22 implements CSProcess{
	  One2OneChannel theChannel;
	  One2OneChannel second;
	   backup22(One2OneChannel theChannel, One2OneChannel second){
		   this.theChannel=theChannel;
		   this.second=second;
	   }

	@Override
	public void run() {
		// TODO Auto-generated method stub

		One2OneChannel newl2 = (One2OneChannel) this.second.in().read();
		System.out.println("Backup2: Received newl channel through second from the 2nd paralle of replication");

	}
	  
  }