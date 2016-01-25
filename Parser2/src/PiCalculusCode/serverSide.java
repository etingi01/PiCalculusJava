package PiCalculusCode;

import java.util.HashMap;

public class serverSide extends Thread {
	private boolean flagA;
	private boolean flagB;
	//private boolean flagC;
    HashMap<Boolean, Object> mapFA;
    HashMap<Boolean, Object> mapFB;
    HashMap<Boolean, Object> mapFC;

	
	private Blink b;
	private Alink a;
	
	public serverSide(HashMap<Boolean, Object> mapFB,HashMap<Boolean, Object> mapFA, Alink a){
		this.flagB=flagB;
		
		this.mapFA = mapFA;
	    this.mapFB = mapFB;
	    this.a=new Alink();
		this.a=a;
		this.b = new Blink();
		this.b.bli=this.a;

		this.mapFB.put(true, this.b);
	    //mapFA.put(false, this.a);
	
	}
    public void run() {
    	synchronized (mapFB){
    		while  (!(mapFB.containsKey(true))) {
    	            try {
    	            	
    	            	mapFB.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }
       			threadMessage("stelnei to a");
       			threadMessage(this.a.per.getonomata()[0] + " " + this.a.per.getonomata()[1]);
	    		flagB=false;
	    		mapFB.remove(true);
	    		mapFB.put(flagB, this.b);
	    		
	    		//mapFB.notifyAll();
	    		
       			threadMessage("to esteile");

    		
    	}
    	
    	synchronized(mapFA){
    		while (!mapFA.containsKey(false)){
    		
    		try {
    			mapFA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		}
        

    			Alink d = (Alink) mapFA.get(false);
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
