package PiCalculusCodeV2;

import java.util.HashMap;

public class printerSide extends Thread  {
	private boolean flagB;
	private boolean flagA;
	Object lock;
    private tmima d;
	private Blink b;
	private Alink c;
	
	public printerSide(Object lock, boolean flagA,boolean flagB, Blink b){
		this.flagA=flagA;
		this.flagB=flagB;
		d = new tmima();
		String[] d1 = {"Eleni", "George"};
		d.setonomata(d1);
		this.b = new Blink();	   
		this.lock=lock;
	}
	
    public void run() {
    	synchronized (lock){
			while (flagB!=false){
	    		try {
	    			lock.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}	
    		threadMessage("Pire to a");
        	//this.b=(Blink) mapFB.get(false);
    		threadMessage(this.b.bli.per.getonomata()[0]+ " " + this.b.bli.per.getonomata()[1]);

        	this.c=this.b.bli;
        	this.c.per=d;
    	}
    	flagA=true;
	   	//mapFA.put(true, this.c);

    	/*synchronized(lock){
    		while (flagA!=true){

    		   try {
    			   lock.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

       			threadMessage("stelnei to d");
    			threadMessage(this.c.per.getonomata()[0] + " " + this.c.per.getonomata()[1]);
    			flagA=false;

    			lock.notify();
    			
    			
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
