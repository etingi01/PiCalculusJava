package ETPSYSTEM;
import org.jcsp.lang.*;
import java.lang.management.*;
import ETPSYSTEM.Sum2O.repl2O;

public class TestAlt2 implements CSProcess {

	public One2OneChannel myh =  Channel.createOne2One();
	public One2OneChannel second = Channel.createOne2One();
    public TestAlt2(One2OneChannel myh, One2OneChannel second){
    	this.myh = myh;
    	this.second=second;
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub

			
			class repl2N implements CSProcess{
				One2OneChannel myh;
				One2OneChannel newl;
				One2OneChannel second;
				repl2N(One2OneChannel myh, One2OneChannel newl,One2OneChannel second){
					this.myh=myh;
					this.newl=newl;
					this.second=second;
				}

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("TestAlt2 - repl2N");
					this.second.out().write(this.newl);					
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
					System.out.println("newl - Out aplo TestAlt2 - repl3");
					this.second.out().write(newl);
					newl.out().write("Loc");
					One2OneChannel cc = Channel.createOne2One();
					this.myh.out().write(cc);
				}
				
			}
			while(true){
				One2OneChannel newl = Channel.createOne2One();		
			this.myh.out().write(newl);
			repl2N rep = new repl2N(this.myh, newl, this.second);
			repl3N rep2 = new repl3N(this.myh, newl, this.second);
			CSProcess[] Lsums = new CSProcess[]{rep, rep2};
			Parallel par = new Parallel(Lsums);
			ProcessManager manager = new ProcessManager(par);
			manager.start();
			}


		
		
		

		}
}
