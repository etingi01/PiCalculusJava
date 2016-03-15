package SystemType;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import ETPSYSTEM.ChannelValue;
import ETPSYSTEM.atomicGroup;
import Parser.Tree.nod;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ConvertSystem {
	
	
	private Document doc;
	private Node rootNode;
	private Node ParPNode;
	
	public ConvertSystem(String file) throws ParserConfigurationException, SAXException, IOException {
		File inputFile = new File(file);
	    DocumentBuilderFactory dbFactory 
	       = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    doc = dBuilder.parse(inputFile);
	    rootNode = doc.getDocumentElement();
	}
	public static HashMap<String,String> handleresNS(HashMap<String, String> resNS, Node node){
		NodeList names = node.getChildNodes();
		for (int p=0; p<names.getLength(); p++){
			if(names.item(p).getNodeName().equals("name")){
				String name = names.item(p).getTextContent();
				String type = names.item(p+2).getTextContent();
				System.out.println(name +  " " + type);
				resNS.put(name, type);
			}
		}

		return resNS;
	}
	public static void handleAtomicGroup(Node resGP, String groupName, HashMap<String, String> resNS,PrintWriter writer){
		System.out.println("handle atomic group");
		NodeList processes = resGP.getChildNodes();
		ArrayList<String> prNames = new ArrayList<String>();
		System.out.println(processes.getLength());
		for(int l=0; l<processes.getLength(); l++){
			System.out.println(processes.item(l).getNodeName());
			if(processes.item(l).getNodeName().equals("process")){
				prNames.add(processes.item(l).getTextContent());
				System.out.println("vrike processes se atomic group");
				System.out.print(processes.item(l).getTextContent());
			}
		}
		for(int l=0; l<prNames.size(); l++){
			writer.print("\t\t\t Process" + prNames.get(l) + " " + prNames.get(l) + " = new Process" + prNames.get(l) + "(");
		    Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()){
					writer.print(", ");
				}
		    }
		    writer.println(");");
	
		}
		//CSProcess[] PAprocesses = new CSProcess[]{A};
		writer.print("\t\t\t CSProcess[] " + groupName + "processes = new CSProcess[]{");
		for(int l=0; l<prNames.size(); l++){
			writer.print(prNames.get(l));
			if(l!=prNames.size()-1){
				writer.print(", ");
			}
		}
		writer.println("};");
		writer.println("\t\t\t atomicGroup " + groupName + " = new atomicGroup(" + groupName + "processes, \"" + groupName + "\");");
		//otan ginoun ta aparaitita tipwmata se afto to simeio sto arxeio pou exoume to parallel
		//na stelnoume ta nodes se mia sinartisi gia na diaxeirizetai ta process kai tin ierarxia

	}
	public static void handleParS(Node node, PrintWriter writer, HashMap<String, String> resNS){
		NodeList parallelG = node.getChildNodes();
		ArrayList<Node> systems = new ArrayList<Node>();
		for (int i=0; i<parallelG.getLength(); i++){
			if(parallelG.item(i).getNodeName().equals("system")){
				systems.add(parallelG.item(i));
				System.out.println("System " + i);
			}
		}
		ArrayList<String> parallelItems = new ArrayList<String>();
		for(int b=0; b<systems.size(); b++){
			if(systems.get(b).getFirstChild().getNextSibling().getNodeName().equals("resGP")){
				Node resGP = systems.get(b).getFirstChild().getNextSibling();
				String groupName = resGP.getFirstChild().getNextSibling().getTextContent();
				parallelItems.add(groupName);
				handleAtomicGroup(resGP, groupName, resNS, writer);
			}
			if(systems.get(b).getFirstChild().getNextSibling().getNodeName().equals("resGS")){
				Node resGP = systems.get(b).getFirstChild().getNextSibling();
				String groupName = resGP.getFirstChild().getNextSibling().getTextContent();
				parallelItems.add(groupName);
				printSysteminClass(groupName, resNS, writer);
			}
		}
		writer.print("\t\t\t CSProcess[] parParts = new CSProcess[]{");
		for(int k=0; k<parallelItems.size(); k++){
			writer.print(parallelItems.get(k));
			if(k!=parallelItems.size()-1){
				writer.print(",");
			}
		}
		writer.println("};");
		writer.println("\t\t\t Parallel par = new Parallel(parParts);");
		writer.println("\t\t\t par.run();");
	}
	public static void printSysteminClass(String groupName, HashMap<String, String> resNS, PrintWriter writer){
		writer.print("\t\t\t System" + groupName + " " + groupName + " = new System" + groupName + "(" );
	    Iterator it = resNS.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.print(pair.getKey());
			if(it.hasNext()){
				writer.print(", ");
			}
	    }
		writer.println(");");

	}
	public static void childrenResNS(Node node, PrintWriter writer, HashMap<String, String> resNS){
		NodeList resNSChild = node.getChildNodes();
		for(int i=0; i<resNSChild.getLength(); i++){
			if(resNSChild.item(i).getNodeName().equals("system")){
				Node nextresNS = resNSChild.item(i).getFirstChild().getNextSibling();
				if(nextresNS.getNodeName().equals("ParS")){
					handleParS(nextresNS, writer, resNS);
				}
				if(nextresNS.getNodeName().equals("resGP")){
					String groupName = nextresNS.getFirstChild().getNextSibling().getTextContent();
					handleAtomicGroup(nextresNS, groupName, resNS, writer);
					writer.println("\t\t\t " + groupName + ".run();");
				}
				if(nextresNS.getNodeName().equals("resGS")){
					String groupSystem = nextresNS.getFirstChild().getNextSibling().getTextContent();
					printSysteminClass(groupSystem, resNS, writer);
					writer.println("\t\t\t " + groupSystem + ".run();");

				}
			}
		}
	}
	public  void implementGS(Node node) throws IOException{
		HashMap<String,String> previousRes = new HashMap<String, String>();
		Node ancestorGS = node.getParentNode();
		while(!(ancestorGS.isEqualNode(rootNode))){
			if(ancestorGS.getNodeName().equals("resNS")){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(previousRes,ancestorGS);
				previousRes.putAll(ResNSpart);
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
		
		
		
		
		
		//Na prostethei anadromi gia na vrethoun ta kanalia tis prohgoumenis ierarxias
		NodeList GSList = node.getChildNodes();
		Node group = GSList.item(1);
		boolean hasResNS = false;
		int indexResNS = -1;
		boolean hasParS = false;
		int indexParS = -1;
		boolean hasResGS = false;
		int indexResGS = -1;
		boolean hasResGP = false;
		int indexResGP = -1;
		
		String nameOFS=group.getTextContent();
		String classSys = "System" + nameOFS ;
		Node system = GSList.item(3);
		System.out.println(system.getNodeName());
		HashMap<String, String> resNS = new HashMap<String, String>();
		NodeList SGroup = system.getChildNodes();
		for(int p=0; p<SGroup.getLength(); p++){
			if(SGroup.item(p).getNodeName().equals("resNS")){
				hasResNS = true;
				indexResNS = p;
				resNS = handleresNS(resNS,SGroup.item(p));
			}
			if(SGroup.item(p).getNodeName().equals("ParS")){
				hasParS = true;
				indexParS = p;
			}
			if(SGroup.item(p).getNodeName().equals("resGS")){
				hasResGS = true;
				indexResGS = p;
			}
			if(SGroup.item(p).getNodeName().equals("resGP")){
				hasResGP = true;
				indexResGP = p;
			}

		}
		
		String fileName= classSys + ".java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();
		
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + classSys + " {");
		writer.println("\t public String nameSystem = \"" + classSys +"\";" );
	    Iterator it = resNS.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }
	    Iterator it2 = previousRes.entrySet().iterator();
	    while (it2.hasNext()) {
	        Map.Entry pair = (Map.Entry)it2.next();
			writer.println("\t\t public ChannelValue " + pair.getKey() + " ;");
	    }
	    writer.print("\t\t public " + classSys + "(" );
	    Iterator it3 = previousRes.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it3.hasNext()){
				writer.print(",");
			}
	    }
	    writer.println("){");
	    Iterator it4 = resNS.entrySet().iterator();
	    while (it4.hasNext()) {
	        Map.Entry pair = (Map.Entry)it4.next();
	        String namen= (String) pair.getKey();
	        String typename = (String) pair.getValue();
	        writer.println("\t\t\t " + namen + " = new ChannelValue(); ");
	        writer.println("\t\t\t " + namen + ".type = \"" + typename +"\" ;");
	    }
	    Iterator it5 = previousRes.entrySet().iterator();
	    while (it5.hasNext()) {
	        Map.Entry pair = (Map.Entry)it5.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }
	    writer.println("\t\t }");
	    
	    //merge resNS with previousRes
	    resNS.putAll(previousRes);
	    writer.println("\t\t public void run() {");

	    if(hasResNS){
	    	System.out.println("vrike ResNS");
	    	childrenResNS(SGroup.item(indexResNS), writer, resNS);
	    }
	    if(hasParS){
	    	handleParS(SGroup.item(indexParS), writer, resNS);
	    }
	    if(hasResGP){
	    	//handleAtomicGroup(Node resGP, String groupName, HashMap<String, String> resNS,PrintWriter writer)
	    	Node groupP = SGroup.item(indexResGP);
	    	String groupName = groupP.getFirstChild().getNextSibling().getTextContent();
	    	handleAtomicGroup(groupP, groupName, resNS, writer);
			writer.println("\t\t\t " + groupName + ".run();");
	    }
	    if(hasResGS){
	    	Node groupS =SGroup.item(indexResGS);
	    	String groupName=groupS.getFirstChild().getNextSibling().getTextContent();
			handleAtomicGroup(groupS, groupName, resNS, writer);
			writer.println("\t\t\t " + groupName + ".run();");
	    }
	    writer.println("\t\t }");

	    writer.println("\t }");
		writer.close();

	}
	
	public void decideItem(Node node, boolean foundGS) throws IOException{
		NodeList nList = node.getChildNodes(); 
		if(node.getNodeName().equals("resGS")){
			foundGS=true;
			implementGS(node);
		}
		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	//System.out.println(nList.item(temp).getNodeName());
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		decideItem(nList.item(temp), foundGS);
        	}    	
        }

	}
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		ConvertSystem myParser = new ConvertSystem("FilesForXMLPrograms/SystemETP.xml");
		boolean foundGS=false;
		myParser.decideItem(myParser.rootNode,foundGS);
		

	}
}
