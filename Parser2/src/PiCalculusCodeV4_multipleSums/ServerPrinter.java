package PiCalculusCodeV4_multipleSums;

import java.util.HashMap;


public class ServerPrinter {
	public Alink a ;
	public Blink b;
	public tmima tm;
	public Alink c;
	public ServerPrinter(){
		tm = new tmima();
		String [] tm2 ={"G", "E"};
		tm.setonomata(tm2);
		a = new Alink();
		a.per=tm;
		//b.bli=a;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flagB=true;
	    transferred mapFA = new transferred(null, false);
	    transferred mapFB = new transferred(null, flagB);
	    transferred mapFM = new transferred(null, false);
		ServerPrinter program = new ServerPrinter();
	    Thread server = new serverSide(mapFB, mapFA, program.a, mapFM );
	    Thread printer = new printerSide(mapFB, mapFA, mapFM);
	    Thread summation3 = new Summation3 (mapFA, mapFB, mapFM);
	    Thread summation4 = new Summation4 (mapFA, mapFB, mapFM);
	    
	    server.start();
	    printer.start();
	    summation3.start();
	    summation4.start();		
		
		
	}

}
