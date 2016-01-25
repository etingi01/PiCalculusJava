package PiCalculusCodeV4_multipleSums;

public class Summation4 extends Thread {
	transferred mapFA;
	transferred mapFB; 
	transferred mapFM;
	
	private tmima d;
	
	private Blink b;
	private Alink q;
		
	public Summation4(transferred mapFA,transferred mapFB, transferred mapFM){
		d = new tmima();
		String[] d1 = {"pablos", "kiriakos"};
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
    	String nextCh ="";
    	if(name.equals("B")){
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
            	nextCh=mapFB.thrID;
            	threadMessage("To pire apo to thread: "+ mapFB.thrID);
        		threadMessage(this.b.bli.per.getonomata()[0]+ " " + this.b.bli.per.getonomata()[1]);
        		//Places the content of channel b in the c location
            	this.q=this.b.bli;
            	this.q.per=d;
            	mapFB.flag=true;
            	mapFB.notifyAll();
        	}

    	}

    	return nextCh;
    }
    
   public String outputAgent(String nextChannel){
	   String nextSync="";
	   if(nextChannel.equals("M")){
		   synchronized(mapFM){
	    		while (mapFM.flag!=false){

	    		   try {
	    			   mapFM.wait();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
	        		mapFM.data=this.q;

	       			threadMessage("stelnei to d");
	    			threadMessage(this.q.per.getonomata()[0] + " " + this.q.per.getonomata()[1]);
	    	    	mapFM.flag=true;
	    			mapFM.data=this.q;    			
	    			mapFM.notifyAll();
	    		}
	   }
	   if(nextChannel.equals("A")){
		   synchronized(mapFA){
	    		while (mapFA.flag!=false){

	    		   try {
	    			   mapFA.wait();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
	        		mapFA.data=this.q;

	       			threadMessage("stelnei to d");
	    			threadMessage(this.q.per.getonomata()[0] + " " + this.q.per.getonomata()[1]);
	    	    	mapFA.flag=true;
	    			mapFA.data=this.q;    			
	    			mapFA.notifyAll();
	    		}
	   }
	   
	   
	   
	   
	   return nextSync;
   }
   
    static void threadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
}
