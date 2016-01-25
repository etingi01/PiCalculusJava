package PiCalculusCodeV3;

import java.util.HashMap;

public class printerSide extends Thread  {
	private boolean flagB;
	private boolean flagC;
	private boolean flagA;
	transferred mapFB;
	transferred mapFC;
    transferred mapFA;

    private tmima d;

	
	
	private Blink b;
	private Alink c;
	
	public printerSide(transferred mapFB,transferred mapFA){
		this.flagA=false;
		d = new tmima();
		String[] d1 = {"Eleni", "George"};
		d.setonomata(d1);
	    this.mapFB=mapFB;
	    this.mapFA = mapFA;
		this.b = new Blink();
	}
	
    public void run() {
    	synchronized (mapFB){
			while (mapFB.flag!=false){
	    		try {
	    			mapFB.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}	
    		threadMessage("Pire to a");
        	this.b=(Blink) mapFB.data;
    		threadMessage(this.b.bli.per.getonomata()[0]+ " " + this.b.bli.per.getonomata()[1]);
    		//Places the content of channel b in the c location
        	this.c=this.b.bli;
        	this.c.per=d;
    	}

    	synchronized(mapFA){
    		while (mapFA.flag!=false){

    		   try {
    			   mapFA.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
        		mapFA.data=this.c;

       			threadMessage("stelnei to d");
    			threadMessage(this.c.per.getonomata()[0] + " " + this.c.per.getonomata()[1]);
    	    	mapFA.flag=true;
    			mapFA.data=this.c;    			
    			mapFA.notifyAll();
    		}
    	

    	
    	
    }
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
	
}
