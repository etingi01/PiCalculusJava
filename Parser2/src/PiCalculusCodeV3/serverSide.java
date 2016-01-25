package PiCalculusCodeV3;

import java.util.HashMap;

public class serverSide extends Thread {
	private boolean flagA;
	private boolean flagB;
	//private boolean flagC;
	transferred mapFA;
	transferred mapFB;
	transferred mapFC;

	
	private Blink b;
	private Alink a;
	
	public serverSide(transferred mapFB,transferred mapFA, Alink a){
		this.flagB=flagB;
		
		this.mapFA = mapFA;
	    this.mapFB = mapFB;
		
	    this.a=new Alink();
		this.a=a;
		this.b = new Blink();
		this.b.bli=this.a;

		this.mapFB.data=this.b;
		this.mapFB.flag=true;
		//mapFA.put(false, this.a);
	
	}
    public void run() {
    	synchronized (mapFB){
    		while  (mapFB.flag!=true){
    	            try {
    	            	
    	            	mapFB.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }
       			threadMessage("stelnei to a");
       			threadMessage(this.a.per.getonomata()[0] + " " + this.a.per.getonomata()[1]);
	    		flagB=false;
	    		mapFB.flag=false;
	    		mapFB.data=this.b;	    		
       			threadMessage("to esteile");
    		
    	}
    	
    	synchronized(mapFA){
    		while (mapFA.flag!=true){
    		
    		try {
    			mapFA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		}
        

    			Alink d = (Alink) mapFA.data;
    			threadMessage("Pire to d");
    			System.out.println(d.per.getonomata()[0] + d.per.getonomata()[1]);
    			
    	
    		
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
