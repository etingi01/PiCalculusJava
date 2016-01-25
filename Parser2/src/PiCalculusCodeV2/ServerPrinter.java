package PiCalculusCodeV2;

import java.util.HashMap;


public class ServerPrinter {
	protected Alink a ;
	protected boolean flagB = true;
	protected boolean flagA = false;
	protected Object lock;
	protected tmima d;
	protected Blink b = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Object lock = new Object();
		Alink a;
		ServerPrinter program = new ServerPrinter();
	    Thread server = new serverSide(lock, program);
	    Thread printer = new printerSide(lock, program);
	    
	    server.start();
	    printer.start();
		
		
	}

}
