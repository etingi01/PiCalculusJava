package testClasses;

import org.jcsp.lang.*;

import ETPSYSTEM.ChannelValue;
import ETPSYSTEM.atomicGroup;

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
			 Processpets pets = new Processpets(door, window);
			 CSProcess[] petsprocesses = new CSProcess[]{pets};
			 atomicGroup petsgroup = new atomicGroup(petsprocesses, "pets");
			 Processpeople people = new Processpeople(door, window);
			 CSProcess[] peopleprocesses = new CSProcess[]{people};
			 atomicGroup peoplegroup = new atomicGroup(peopleprocesses, "people");
			 CSProcess[] parParts = new CSProcess[]{petsgroup,peoplegroup};
			 Parallel par = new Parallel(parParts);
			 par.run();
		 }
		 
		 
		 public static void main(String[] args){
			 Systemhouse house = new Systemhouse();
			 house.run();
		 }
	 }
