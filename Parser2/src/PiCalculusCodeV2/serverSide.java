package PiCalculusCodeV2;

import java.util.HashMap;

public class serverSide extends Thread {
	private boolean flagA;
	private boolean flagB;

	private Blink b;
	private Alink a;
	private Alink d;
	Object lock;
	protected ServerPrinter pr;
	
	public serverSide(Object lock, boolean flagA,boolean flagB){
		this.flagB=flagB;
		this.flagA=flagA;
		tmima tm;
		tm = new tmima();
		String [] tm2 ={"G", "E"};
		tm.setonomata(tm2);
		a = new Alink();
		a.per=tm;
		this.b = new Blink();
		this.lock=lock;

	}
    public void run() {
    	synchronized (lock){
    		while  (flagB!=true) {
    	            try {
    	            	Thread.sleep(300);
    	            	lock.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }
    			this.b.bli=this.a;	
    			ServerPrinter.b=this.b;
       			threadMessage("stelnei to a");
       			threadMessage(this.a.per.getonomata()[0] + " " + this.a.per.getonomata()[1]);
	    		flagB=false;	    		
	    		lock.notifyAll();
	    		
       			threadMessage("to esteile");

    		
    	}
    	
    	/*synchronized(lock){
    		while (flagA!=false){
    		
    		try {
    			lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		}
        

    			threadMessage("Pire to d");
    			//System.out.println(d.per.getonomata()[0] + d.per.getonomata()[1]);
    			
    	
    		
    	}*/
    	
    }
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
	
	
	
	
}
