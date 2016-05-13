import org.jcsp.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Systemhouse implements CSProcess {
	 public String nameSystem = "Systemhouse";
	 public ChannelValue door ;
	 public ChannelValue window ;
		 public Systemhouse(){
			 door = new ChannelValue(); 
			 door.type = "Td" ;
			 window = new ChannelValue(); 
			 window.type = "Tw" ;
		 }
		 public void run() {
			 Processpets petsGrPr = new Processpets(door, window);
			 CSProcess[] petsprocesses = new CSProcess[]{petsGrPr};
			 atomicGroup pets = new atomicGroup(petsprocesses, "pets");
			 Processpeople peopleGrPr = new Processpeople(door, window);
			 CSProcess[] peopleprocesses = new CSProcess[]{peopleGrPr};
			 atomicGroup people = new atomicGroup(peopleprocesses, "people");
			 CSProcess[] parParts = new CSProcess[]{pets,people};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
	 }