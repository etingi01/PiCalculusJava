package PiCalculusCodeV4_multipleSums;

public class printerSide extends Thread  {
	transferred mapFB;
	transferred mapFM;
    transferred mapFA;

    private tmima d;

	
	
	private Blink b;
	private Alink c;
	
	public printerSide(transferred mapFB,transferred mapFA, transferred mapFM){
		d = new tmima();
		String[] d1 = {"Eleni", "George"};
		d.setonomata(d1);
	    this.mapFB=mapFB;
	    this.mapFA = mapFA;
	    this.mapFM=mapFM;
		this.b = new Blink();
	}
	
    public void run() {
    	String nextChannel = inputAgent("B");
    	outputAgent(nextChannel);
    	
    	

    	

    	
    	
    }
    
    public String inputAgent(String name){
    	String nextCh="";
    	if (name.equals("B")){
    		synchronized (mapFB){
    			while (mapFB.flag!=false){
    	    		try {
    	    			threadMessage("perimenei");
    	    			mapFB.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    			}	
        		threadMessage("Pire to a");
            	this.b=(Blink) mapFB.data;
            	nextCh=mapFB.thrID;
            	threadMessage("To pire apo to thread: "+ mapFB.thrID);
        		threadMessage(this.b.bli.per.getonomata()[0]+ " " + this.b.bli.per.getonomata()[1]);
        		//Places the content of channel b in the c location
            	this.c=this.b.bli;
            	this.c.per=d;
            	mapFB.flag=true;
            	mapFB.notifyAll();
        	}

    	}
    	
    	
    	return nextCh;
    }
    
    public String outputAgent(String name){
    	String nextChannel="";
    	if (name.equals("A")){
        	synchronized(mapFA){
        		while (mapFA.flag!=false){

        		   try {
   	    			threadMessage("perimenei");
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
        			nextChannel = mapFA.thrID;
        			
        			mapFA.notifyAll();
        		}
    	}
    	if (name.equals("M")){
        	synchronized(mapFM){
        		while (mapFM.flag!=false){

        		   try {
        			   mapFM.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
    	        }
            		mapFM.data=this.c;

           			threadMessage("stelnei to d");
        			threadMessage(this.c.per.getonomata()[0] + " " + this.c.per.getonomata()[1]);
        	    	mapFM.flag=true;
        			mapFM.data=this.c;    	
        			nextChannel = mapFM.thrID;
        			mapFM.notifyAll();
        		}
    	}
    	
    	return nextChannel;
    }
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
	
}
