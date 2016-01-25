package PiCalculusCode;

import java.util.HashMap;

public class printerSide extends Thread  {
	private boolean flagB;
	private boolean flagC;
	private boolean flagA;
    HashMap<Boolean, Object> mapFB;
    HashMap<Boolean, Object> mapFC;
    HashMap<Boolean, Object> mapFA;

    private tmima d;

	
	
	private Blink b;
	private Alink c;
	
	public printerSide(HashMap<Boolean, Object> mapFB,HashMap<Boolean, Object> mapFA){
		this.flagA=false;
		d = new tmima();
		String[] d1 = {"Eleni", "George"};
		d.setonomata(d1);
	    this.mapFB=mapFB;
	   // mapFC = new HashMap<Boolean, Object>();
	    this.mapFA = mapFA;
		this.b = new Blink();
	    //mapFB.put(false, b);
		//mapFA.put(true, this.c);
	    
	}
	
    public void run() {
    	synchronized (mapFB){
			while (!(mapFB.containsKey(false))){
	    		try {
	    			mapFB.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}	
    		threadMessage("Pire to a");
        	this.b=(Blink) mapFB.get(false);
    		threadMessage(this.b.bli.per.getonomata()[0]+ " " + this.b.bli.per.getonomata()[1]);
    		
        	this.c=this.b.bli;
        	this.c.per=d;
        		//threadMessage(d.getonomata()[0]+ " " + d.getonomata()[1]);
        	//	flagB=false;
        	//	mapFB.put(flagB, this.b);
        	//	mapFB.notify();
    	}
	   	mapFA.put(true, this.c);

    	synchronized(mapFA){
    	/*	while (!(mapFA.containsKey(true))){

    		   try {
    			   mapFA.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }*/

       			threadMessage("stelnei to d");
    			threadMessage(this.c.per.getonomata()[0] + " " + this.c.per.getonomata()[1]);
    			flagA=false;
    			mapFA.remove(true);
    	    	mapFA.put(flagA, this.c);

    			mapFA.notify();
    			
    			
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
