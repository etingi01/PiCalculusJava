package PiCalculusCode;

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
	    HashMap<Boolean, Object> mapFA = new HashMap<Boolean, Object>();
	    HashMap<Boolean, Object> mapFB = new HashMap<Boolean, Object>();
		ServerPrinter program = new ServerPrinter();
	    Thread server = new serverSide(mapFB, mapFA, program.a);
	    Thread printer = new printerSide(mapFB, mapFA);
	    
	    server.start();
	    printer.start();
		
		
	}

}
