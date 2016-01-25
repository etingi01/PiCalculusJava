package PiCalculusCodeV4_multipleSums;

public class Summation3 extends Thread {
	transferred mapFA;
	transferred mapFB; 
	transferred mapFM;
	
	tmima l;
	Blink b;
	Alink m;
	
	public Summation3(transferred mapFA, transferred mapFB, transferred mapFM){
		this.mapFA=mapFA;
		this.mapFB=mapFB;
		this.mapFM=mapFM;
		String [] names = {"marina", "antonis"}; 
		l=new tmima();
		l.setonomata(names);
		this.m=new Alink();
		m.per=l;
		this.b=new Blink();
		b.bli=m;
		
		mapFB.data=b;
		mapFB.flag=true;
		
	}
	
	public void run(){
		outputAgent("B");
		inputAgent("M");

	}
	
	public  String outputAgent(String name){
		String nextch = "";
		if(name.equals("B")){
			synchronized(mapFB){
				while  (mapFB.flag!=true){
    	            try {
    	            	
    	            	mapFB.wait();
    	            } catch (InterruptedException e) {
    	                e.printStackTrace();
    	            }
				}
			}
		
   			threadMessage("stelnei to a");
   			threadMessage(this.b.bli.per.getonomata()[0] + " " + this.b.bli.per.getonomata()[1]);
    		mapFB.flag=false;
    		mapFB.data=this.b;	
    		mapFB.thrID="M";
   			threadMessage("to esteile");
   			nextch = mapFB.thrID;
   			mapFB.notify();
		}		
		return nextch;
	}
	public String inputAgent(String name){
		if(name.equals("M")){
			synchronized(mapFM){
	    		while (mapFM.flag!=true){
	    		
	    		try {
	    			mapFM.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	    		}
	    			Alink d = (Alink) mapFM.data;
	    			threadMessage("Pire to d");
	    			System.out.println(d.per.getonomata()[0] + d.per.getonomata()[1]);
	    			
	    	
	    		
	    	}

			
		}
		
		return this.mapFM.thrID;

		
	}
	  static void threadMessage(String message) {
	        String threadName =
	            Thread.currentThread().getName();
	        System.out.format("%s: %s%n",
	                          threadName,
	                          message);
	    }
}
