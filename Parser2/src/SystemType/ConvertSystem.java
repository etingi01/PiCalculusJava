package SystemType;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.ProcessManager;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import ETPSYSTEM.ChannelValue;
import ETPSYSTEM.atomicGroup;
import Parser.Tree.nod;
import baseTypes.CreateBaseTypes;

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
	//private static int processNewGP = 0;
	private static int ParPproc = 0;
	private static int ReplP=0;
	private static ArrayList<String> baseTypes = new ArrayList<String>();
	private static HashMap<String, String> globalChannels = new HashMap<String, String>();
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
	public static void handleAtomicGroup(Node resGP, String groupName, PrintWriter writer) throws IOException{
		System.out.println("handle atomic group");
		NodeList processes = resGP.getChildNodes();
		ArrayList<String> prNames = new ArrayList<String>();
		System.out.println(processes.getLength());
		
		HashMap<String, String> resNS = new HashMap<String, String>();
		
		Node ancestorGS = resGP.getParentNode();		
		while(ancestorGS!=null){
			if(ancestorGS.getNodeName().equals("resNS")){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
		for(int l=0; l<processes.getLength(); l++){
			if(processes.item(l).getNodeName().equals("process")){
				String nameProcess = groupName;
				prNames.add(nameProcess);
			}
		}
		for(int l=0; l<prNames.size(); l++){
			String varp = prNames.get(l)+"GrPr";
			writer.print("\t\t\t Process" + prNames.get(l) + " " + varp  + " = new Process" + prNames.get(l) + "(");
		    Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()||!globalChannels.isEmpty()){
					writer.print(", ");
				}
		    }
		    Iterator it2 = globalChannels.entrySet().iterator();
		    while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
				writer.print(pair.getKey());
				if(it2.hasNext()){
					writer.print(", ");
				}
		    }

		    writer.println(");");
	
		}
		//CSProcess[] PAprocesses = new CSProcess[]{A};
		writer.print("\t\t\t CSProcess[] " + groupName + "processes = new CSProcess[]{");
		for(int l=0; l<prNames.size(); l++){
			writer.print(prNames.get(l)+"GrPr");
			if(l!=prNames.size()-1){
				writer.print(", ");
			}
		}
		writer.println("};");
		writer.println("\t\t\t atomicGroup " + groupName + " = new atomicGroup(" + groupName + "processes, \"" + groupName + "\");");
		//otan ginoun ta aparaitita tipwmata se afto to simeio sto arxeio pou exoume to parallel
		//na stelnoume ta nodes se mia sinartisi gia na diaxeirizetai ta process kai tin ierarxia
		for(int l=0; l<processes.getLength(); l++){
			if(processes.item(l).getNodeName().equals("process")){
				handleProcessesNewGP(processes.item(l), prNames, resNS);
			}
		}
		
		

	}
	public static void handleProcessesNewGP(Node process, ArrayList<String> prname, HashMap<String, String> resNS) throws IOException{
		String namePro = "Process"+prname.get(0);
		String fileName = namePro + ".java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();		
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + namePro + " implements CSProcess {");
		writer.println("\t public String nameProcess = \"" + namePro +"\";" );
	    Iterator it = resNS.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }
	    Iterator it7 = globalChannels.entrySet().iterator();
	    while (it7.hasNext()) {
	        Map.Entry pair = (Map.Entry)it7.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }

	    writer.print("\t public " + namePro + "(" );
	    Iterator it3 = resNS.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it3.hasNext()||!globalChannels.isEmpty()){
				writer.print(",");
			}
	    }
	    Iterator it6 = globalChannels.entrySet().iterator();
	    while (it6.hasNext()) {
	        Map.Entry pair = (Map.Entry)it6.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it6.hasNext()){
				writer.print(",");
			}
	    }

	    writer.println("){");
	    Iterator it5 = resNS.entrySet().iterator();
	    while (it5.hasNext()) {
	        Map.Entry pair = (Map.Entry)it5.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }
	    Iterator it8 = globalChannels.entrySet().iterator();
	    while (it8.hasNext()) {
	        Map.Entry pair = (Map.Entry)it8.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }

		writer.println("\t }");
	    writer.println("\t public void run() {");
	    Node nextPro = process.getFirstChild().getNextSibling();
	    if(nextPro.getNodeName().equals("parP")){
	    	handleParP(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("in")){
	    	handleIn(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("out")){
	    	handleOut(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("resNP")){
	    	handleResNP(nextPro, writer);
	    	Node nextProc = null;
	    	NodeList resCh = nextPro.getChildNodes();
	    	for(int yy=0; yy<resCh.getLength(); yy++){
	    		if(resCh.item(yy).getNodeName().equals("process")){
	    			nextProc = resCh.item(yy).getFirstChild().getNextSibling();
	    		}
	    	}
	    	goToNext(nextProc, writer);
	    }
	    if(nextPro.getNodeName().equals("repl")){
	    	handleRepl(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("Nil")){
	    	handleNil(nextPro, writer);

	    }
	    
		writer.println("\t }");
		writer.println("}");
		writer.close();
	}
	public static void createReplClass(Node node, String replName){
		
	}
	
	public static void createProcessPar(Node node, String proName) throws IOException{
		HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = node.getParentNode();		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	

			ancestorGS = ancestorGS.getParentNode();
		}

		String fileName = proName + ".java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();		
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + proName + " implements CSProcess {");
		writer.println("\t public String nameProcess = \"" + proName +"\";" );
	    Iterator it = resNS.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }
	    Iterator it10 = globalChannels.entrySet().iterator();
	    while (it10.hasNext()) {
	        Map.Entry pair = (Map.Entry)it10.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }

	    writer.print("\t public " + proName + "(" );
	    Iterator it3 = resNS.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it3.hasNext()||!globalChannels.isEmpty()){
				writer.print(",");
			}
	    }
	    Iterator it7 = globalChannels.entrySet().iterator();
	    while (it7.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it7.hasNext()){
				writer.print(",");
			}
	    }

	    writer.println("){");
	    Iterator it5 = resNS.entrySet().iterator();
	    while (it5.hasNext()) {
	        Map.Entry pair = (Map.Entry)it5.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }
	    Iterator it15 = globalChannels.entrySet().iterator();
	    while (it15.hasNext()) {
	        Map.Entry pair = (Map.Entry)it15.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }

		writer.println("\t }");
	    writer.println("\t public void run() {");
	    Node nextPro = node.getFirstChild().getNextSibling();
	    if(nextPro.getNodeName().equals("parP")){
	    	handleParP(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("in")){
	    	handleIn(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("out")){
	    	handleOut(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("resNP")){
	    	handleResNP(nextPro, writer);
	    	Node nextProc = null;
	    	NodeList resCh = nextPro.getChildNodes();
	    	for(int yy=0; yy<resCh.getLength(); yy++){
	    		if(resCh.item(yy).getNodeName().equals("process")){
	    			nextProc = resCh.item(yy).getFirstChild().getNextSibling();
	    		}
	    	}
	    	goToNext(nextProc, writer);
	    }
	    if(nextPro.getNodeName().equals("repl")){
	    	handleRepl(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("Nil")){
	    	handleNil(nextPro, writer);

	    }
	    
		writer.println("\t }");
		writer.println("}");
		writer.close();

	}
	
	
	public static void handleParP(Node parP, PrintWriter writer) throws IOException{
		NodeList children = parP.getChildNodes();
		ArrayList<Node> processes = new ArrayList<Node>();
		for(int i=0; i<children.getLength(); i++){
			if(processes.get(i).getNodeName().equals("process")){
				processes.add(processes.get(i));
			}
		}
		HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = parP.getParentNode();		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
		

		ArrayList<String> parallelItems = new ArrayList<String>();
		for(int i=0; i<processes.size(); i++){
			ParPproc++;
			String processName = "ParallelProc" + ParPproc;
			parallelItems.add(processName);
			createProcessPar(processes.get(i), processName);

		}
		for(int k=0; k<parallelItems.size(); k++){
			writer.print("\t\t\t " + parallelItems.get(k) + " " + "ParP"+k + " = new " + parallelItems.get(k) + "(");
		    Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()){
					writer.print(", ");
				}
		    }
		    Iterator it2 = globalChannels.entrySet().iterator();
		    while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
				writer.print(pair.getKey());
				if(it2.hasNext()){
					writer.print(", ");
				}
		    }

		    writer.println(");");

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
	public static void createParRepl(Node repl, Node node, String proName) throws IOException{
		HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = repl.getParentNode();		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	

			ancestorGS = ancestorGS.getParentNode();
		}

		String fileName = proName + ".java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();		
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + proName + " implements CSProcess {");
		writer.println("\t public String nameProcess = \"" + proName +"\";" );
	    Iterator it = resNS.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }
	    Iterator it10 = globalChannels.entrySet().iterator();
	    while (it10.hasNext()) {
	        Map.Entry pair = (Map.Entry)it10.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }

	    writer.print("\t public " + proName + "(" );
	    Iterator it3 = resNS.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it3.hasNext()||!globalChannels.isEmpty()){
				writer.print(",");
			}
	    }
	    Iterator it7 = globalChannels.entrySet().iterator();
	    while (it7.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
			writer.print(" ChannelValue " + pair.getKey());
			if(it7.hasNext()){
				writer.print(",");
			}
	    }

	    writer.println("){");
	    Iterator it5 = resNS.entrySet().iterator();
	    while (it5.hasNext()) {
	        Map.Entry pair = (Map.Entry)it5.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }
	    Iterator it15 = globalChannels.entrySet().iterator();
	    while (it15.hasNext()) {
	        Map.Entry pair = (Map.Entry)it15.next();
			writer.println("\t\t\t this." + pair.getKey() + " = " + pair.getKey() + ";");
	    }

		writer.println("\t }");
	    writer.println("\t public void run() {");
	    writer.println("\t\t while (true) {");
	    handleResNP(repl, writer);
	    //ReplInParSResNP
	    //simplirwma
	    writer.println("\t\t }");
		writer.println("\t }");
		writer.println("}");
		
		writer.close();

	}
	public static void handleParResRepl(Node repl, Node parP, PrintWriter writer) throws IOException{
		NodeList children = parP.getChildNodes();
		ArrayList<Node> processes = new ArrayList<Node>();
		for(int i=0; i<children.getLength(); i++){
			if(processes.get(i).getNodeName().equals("process")){
				processes.add(processes.get(i));
			}
		}
		HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = repl.getParentNode();		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
		

		ArrayList<String> parallelItems = new ArrayList<String>();
		for(int i=0; i<processes.size(); i++){
			ParPproc++;
			String processName = "ParallelProc" + ParPproc;
			parallelItems.add(processName);
			createParRepl(repl, processes.get(i), processName);

		}
		for(int k=0; k<parallelItems.size(); k++){
			writer.print("\t\t\t " + parallelItems.get(k) + " " + "ParP"+k + " = new " + parallelItems.get(k) + "(");
		    Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()){
					writer.print(", ");
				}
		    }
		    Iterator it2 = globalChannels.entrySet().iterator();
		    while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
				writer.print(pair.getKey());
				if(it2.hasNext()){
					writer.print(", ");
				}
		    }

		    writer.println(");");

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
	public static void ReplInParSResNP(Node in, PrintWriter writer) throws IOException{
		NodeList children = in.getChildNodes();
		String inPlace="";
		String typeInplace = "";
		String throughChannel="";
		Node nextProcess=null;
		for(int i=0; i<children.getLength(); i++){
			if(children.item(i).getNodeName().equals("obj")){
				inPlace=children.item(i).getTextContent();
			}
			if(children.item(i).getNodeName().equals("type")){
				typeInplace=children.item(i).getTextContent();
			}

			if(children.item(i).getNodeName().equals("subj")){
				throughChannel=children.item(i).getTextContent();
			}
			if(children.item(i).getNodeName().equals("process")){
				nextProcess=children.item(i);
			}
		}
		boolean alreadyExists = false;
	    Iterator it2 = globalChannels.entrySet().iterator();
	    while (it2.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry)it2.next();
	        String name = (String) pair.getKey();
	        String typeName = (String) pair.getValue();
	        if(name.equals(inPlace)&&typeName.equals(typeInplace)){
	        	alreadyExists=true;
	        }
	    }
	    HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = in.getParentNode();		
		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
	    Iterator it3 = resNS.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry)it3.next();
	        String name = (String) pair.getKey();
	        String typeName = (String) pair.getValue();
	        if(name.equals(inPlace)&&typeName.equals(typeInplace)){
	        	alreadyExists=true;
	        }
	    }
	    if(!alreadyExists){
	    	if(baseTypes.contains(typeInplace)){
	    		writer.println("\t\t\t "+ typeInplace + "  "+ inPlace + " = new " + typeInplace + "();");
	    	}else{
	    		writer.println("\t\t\t ChannelValue " + inPlace + " = new ChannelValue()");
	    		writer.println("\t\t\t " + inPlace + ".type = \"" + typeInplace + "\";" );
	    	}
	    }
    	if(baseTypes.contains(typeInplace)){
    		if(alreadyExists)
    		writer.println("\t\t\t this." + inPlace + " = (" + typeInplace + ") "+throughChannel+".channel.in().read();");
    		else
    			writer.println("\t\t\t " + inPlace + " = (" + typeInplace + ") "+throughChannel+".channel.in().read();");   			
    	}else{
    		if(alreadyExists)
        		writer.println("\t\t\t "+inPlace + " = (ChannelValue) " + throughChannel+".channel.in().read();");
    		else
        		writer.println("\t\t\t "+inPlace + " = (ChannelValue) " + throughChannel+".channel.in().read();");
    	}

		
		
	}
	public static void ReplOutParSResNP(Node out, PrintWriter writer) throws IOException{
		NodeList chOut = out.getChildNodes();
		String thC="";
		String whatC = "";
		Node next = null;
		for(int i=0; i<chOut.getLength(); i++){
			//		this.read.channel.out().write(newl);
			if(chOut.item(i).getNodeName().equals("subj")){
				thC = chOut.item(i).getTextContent();
			}
			if(chOut.item(i).getNodeName().equals("obj")){
				whatC = chOut.item(i).getTextContent();
			}
			writer.println(thC + ".channel.out().write( " + whatC + " );");
			if(chOut.item(i).getNodeName().equals("process")){
				next = chOut.item(i);
			}

		}
		
	}

	public static void handleIn(Node in, PrintWriter writer) throws IOException{
		NodeList children = in.getChildNodes();
		String inPlace="";
		String typeInplace = "";
		String throughChannel="";
		Node nextProcess=null;
		for(int i=0; i<children.getLength(); i++){
			if(children.item(i).getNodeName().equals("obj")){
				inPlace=children.item(i).getTextContent();
			}
			if(children.item(i).getNodeName().equals("type")){
				typeInplace=children.item(i).getTextContent();
			}

			if(children.item(i).getNodeName().equals("subj")){
				throughChannel=children.item(i).getTextContent();
			}
			if(children.item(i).getNodeName().equals("process")){
				nextProcess=children.item(i);
			}
		}
		boolean alreadyExists = false;
	    Iterator it2 = globalChannels.entrySet().iterator();
	    while (it2.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry)it2.next();
	        String name = (String) pair.getKey();
	        String typeName = (String) pair.getValue();
	        if(name.equals(inPlace)&&typeName.equals(typeInplace)){
	        	alreadyExists=true;
	        }
	    }
	    HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = in.getParentNode();		
		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	
			ancestorGS = ancestorGS.getParentNode();
		}
	    Iterator it3 = resNS.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry<String, String> pair = (Map.Entry)it3.next();
	        String name = (String) pair.getKey();
	        String typeName = (String) pair.getValue();
	        if(name.equals(inPlace)&&typeName.equals(typeInplace)){
	        	alreadyExists=true;
	        }
	    }
	    if(!alreadyExists){
	    	if(baseTypes.contains(typeInplace)){
	    		writer.println("\t\t\t "+ typeInplace + "  "+ inPlace + " = new " + typeInplace + "();");
	    	}else{
	    		writer.println("\t\t\t ChannelValue " + inPlace + " = new ChannelValue();");
	    		writer.println("\t\t\t " + inPlace + ".type = \"" + typeInplace + "\";" );
	    	}
	    }
    	if(baseTypes.contains(typeInplace)){
    		if(alreadyExists)
    		writer.println("\t\t\t this." + inPlace + " = (" + typeInplace + ") "+throughChannel+".channel.in().read();");
    		else
    			writer.println("\t\t\t " + inPlace + " = (" + typeInplace + ") "+throughChannel+".channel.in().read();");   			
    	}else{
    		if(alreadyExists)
        		writer.println("\t\t\t "+inPlace + " = (ChannelValue) " + throughChannel+".channel.in().read();");
    		else
        		writer.println("\t\t\t "+inPlace + " = (ChannelValue) " + throughChannel+".channel.in().read();");
    	}
		Node nextPro = nextProcess.getFirstChild().getNextSibling();
		
		if(!(in.getParentNode().getParentNode().equals("repl"))){
			System.out.println("In paidi tou repl");
			goToNext(nextPro, writer);

		}
	}
		
		
		public static void goToNext(Node nextPro, PrintWriter writer) throws IOException{
		if(nextPro.getNodeName().equals("parP")){
	    	handleParP(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("in")){
	    	handleIn(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("out")){
	    	handleOut(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("resNP")){
	    	handleResNP(nextPro, writer);
	    	Node nextProc = null;
	    	NodeList resCh = nextPro.getChildNodes();
	    	for(int yy=0; yy<resCh.getLength(); yy++){
	    		if(resCh.item(yy).getNodeName().equals("process")){
	    			nextProc = resCh.item(yy).getFirstChild().getNextSibling();
	    		}
	    	}
	    	goToNext(nextProc, writer);
	    }
	    if(nextPro.getNodeName().equals("repl")){
	    	handleRepl(nextPro, writer);
	    }
	    if(nextPro.getNodeName().equals("Nil")){
	    	handleNil(nextPro, writer);

	    }

	}
	public static void handleOut(Node out, PrintWriter writer) throws IOException{
		NodeList chOut = out.getChildNodes();
		String thC="";
		String whatC = "";
		Node next = null;
		for(int i=0; i<chOut.getLength(); i++){
			//		this.read.channel.out().write(newl);
			if(chOut.item(i).getNodeName().equals("subj")){
				thC = chOut.item(i).getTextContent();
			}
			if(chOut.item(i).getNodeName().equals("obj")){
				whatC = chOut.item(i).getTextContent();
			}
			
			if(chOut.item(i).getNodeName().equals("process")){
				next = chOut.item(i);
			}
		}
		writer.println("\t\t\t "+thC + ".channel.out().write( " + whatC + " );");

			Node nextPro = next.getFirstChild().getNextSibling();
			
			if(!(out.getParentNode().getParentNode().equals("repl"))){
				System.out.println("In paidi tou repl");
				goToNext(nextPro, writer);
			}
			

		
		
	}
	public static void handleResNP(Node resNP, PrintWriter writer){
		NodeList resChi = resNP.getChildNodes();
		HashMap<String, String> news = new HashMap<String, String>();
		for(int i=0; i<resChi.getLength(); i++){
			if(resChi.item(i).getNodeName().equals("name")){
				String na = resChi.item(i).getTextContent();
				String ty = resChi.item(i).getNextSibling().getNextSibling().getTextContent();
				news.put(na, ty);
			}
		}
	    Iterator it3 = news.entrySet().iterator();
	    while (it3.hasNext()) {
	        Map.Entry pair = (Map.Entry)it3.next();
	        String name = (String) pair.getKey();
	        String typeName = (String) pair.getValue();
	        if(baseTypes.contains(typeName)){
	        	writer.println("\t\t\t " + typeName + " " + name + " = new " + typeName + " ();");
	        }else{
	        	writer.println("\t\t\t ChannelValue "+ name + " = new ChannelValue();");
	        	writer.println("\t\t\t "+ name + ".type = \"" + typeName + "\";");
	        }
	        
	        
	    }

		
	}
	
	public static void handleInRepl(HashMap<String,String> resNS,Node firstInstr, PrintWriter writer) throws IOException{
		ReplP++;
		writer.println("\t\t\t while(true){ ");
		handleIn(firstInstr, writer);
		writer.print("\t\t\t Repl" + ReplP + " rep = new ReplP" + ReplP +"(");
		 Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()){
					writer.print(", ");
				}
		    }
		    Iterator it2 = globalChannels.entrySet().iterator();
		    while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
				writer.print(pair.getKey());
				if(it2.hasNext()){
					writer.print(", ");
				}
		    }
		writer.println(");");
		writer.println("\t\t\t ProcessManager manager = new ProcessManager(rep);");
		writer.println("\t\t\t manager.start();");
		writer.println("\t\t\t }");
		NodeList inChi = firstInstr.getChildNodes();
		Node proIn= null;
		for(int pp=0; pp<inChi.getLength(); pp++){
			if (inChi.item(pp).getNodeName().equals("process")){
				proIn=inChi.item(pp);
			}
		}
		createProcessPar(proIn, "Repl"+ReplP);
	}
	public static void handleOutRepl(HashMap<String,String> resNS,Node firstInstr, PrintWriter writer) throws IOException{
		ReplP++;
		writer.println("\t\t\t while(true){ ");
		handleOut(firstInstr, writer);
		writer.print("\t\t\t Repl" + ReplP + " rep = new ReplP" + ReplP +"(");
		 Iterator it = resNS.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
				writer.print(pair.getKey());
				if(it.hasNext()){
					writer.print(", ");
				}
		    }
		    Iterator it2 = globalChannels.entrySet().iterator();
		    while (it2.hasNext()) {
		        Map.Entry pair = (Map.Entry)it2.next();
				writer.print(pair.getKey());
				if(it2.hasNext()){
					writer.print(", ");
				}
		    }
		writer.println(");");
		writer.println("\t\t\t ProcessManager manager = new ProcessManager(rep);");
		writer.println("\t\t\t manager.start();");
		writer.println("\t\t\t }");
		NodeList inChi = firstInstr.getChildNodes();
		Node proIn= null;
		for(int pp=0; pp<inChi.getLength(); pp++){
			if (inChi.item(pp).getNodeName().equals("process")){
				proIn=inChi.item(pp);
			}
		}
		createProcessPar(proIn, "Repl"+ReplP);

	
	}
	public static void handleRepl(Node repl, PrintWriter writer) throws IOException{
		Node replProc = repl.getFirstChild().getNextSibling();
		Node firstInstr = replProc.getFirstChild().getNextSibling();
		String fInName = firstInstr.getNodeName();
		HashMap<String, String> resNS = new HashMap<String, String>();
		Node ancestorGS = repl.getParentNode();		
		while(ancestorGS!=null){
			if((ancestorGS.getNodeName().equals("resNS"))||(ancestorGS.getNodeName().equals("resNP"))){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
			}	
			if(ancestorGS.getNodeName().equals("in")){
				NodeList childrenIn = ancestorGS.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						inplace=childrenIn.item(v);
					}
					if(childrenIn.item(v).getNodeName().equals("type")){
						type=childrenIn.item(v);
					}
				}
				resNS.put(inplace.getTextContent(), type.getTextContent());
			}	
			ancestorGS = ancestorGS.getParentNode();
		}

		if(fInName.equals("resNP")){
			NodeList children = firstInstr.getChildNodes();
			Node nextProcess=null;
			for(int j=0; j<children.getLength(); j++){
				if(children.item(j).getNodeName().equals("process")){
					nextProcess=children.item(j);
				}
			}
			Node Pro = nextProcess.getFirstChild().getNextSibling();
			if(Pro.getNodeName().equals("in")){
				handleResNP(firstInstr, writer);
				handleInRepl(resNS, firstInstr, writer);

			}
			if(Pro.getNodeName().equals("out")){
				handleResNP(firstInstr, writer);
				handleOutRepl(resNS, firstInstr, writer);

			}
			if(Pro.getNodeName().equals("parP")){
				handleParResRepl(firstInstr, Pro, writer);
				//simplirwma
			}
		}
		if(fInName.equals("parP")){
			//handleReplPar(firstInstr);
			//simplirwma
		}
		if(fInName.equals("in")){
			handleInRepl(resNS, firstInstr, writer);
		}
		if(fInName.equals("out")){
			handleOutRepl(resNS, firstInstr, writer);
		}
	}
	public static void handleNil(Node nil, PrintWriter writer){
		
	}





	public static void handleParS(Node node, PrintWriter writer, HashMap<String, String> resNS) throws IOException{
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
				handleAtomicGroup(resGP, groupName, writer);
			}
			if(systems.get(b).getFirstChild().getNextSibling().getNodeName().equals("resGS")){
				Node resGS = systems.get(b).getFirstChild().getNextSibling();
				String groupName = resGS.getFirstChild().getNextSibling().getTextContent();
				parallelItems.add(groupName);
				printSysteminClass(resGS, groupName, writer);
			}
			if(systems.get(b).getFirstChild().getNextSibling().getNodeName().equals("resNS")){
				System.out.println("resNS in a ParS of a resGS");
				Node rNS = systems.get(b).getFirstChild().getNextSibling();
				parallelItems.addAll(resNSinParS(rNS, writer));
				HashMap<String, String> newnames = new HashMap<String, String>();
				newnames = findAllResNS(newnames, rNS);
			    /*Iterator it4 = newnames.entrySet().iterator();
			    while (it4.hasNext()) {
			        Map.Entry pair = (Map.Entry)it4.next();
			        String namen= (String) pair.getKey();
			        String typename = (String) pair.getValue();
			        writer.println("\t\t\t ChannelValue " + namen + " = new ChannelValue(); ");
			        writer.println("\t\t\t " + namen + ".type = \"" + typename +"\" ;");
			    }*/
				
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
	
	public static void printSysteminClass(Node node, String groupName,  PrintWriter writer){
		HashMap<String, String> resNS = new HashMap<String, String>();
		
		Node ancestorGS = node.getParentNode();		
		while(ancestorGS!=null){
			if(ancestorGS.getNodeName().equals("resNS")){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				resNS.putAll(ResNSpart);
				System.out.println("To resNS periexei: " + resNS);
			}	
			ancestorGS = ancestorGS.getParentNode();
		}

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
	public static void childrenResNS(Node node, PrintWriter writer, HashMap<String, String> resNS) throws IOException{
		NodeList resNSChild = node.getChildNodes();
		for(int i=0; i<resNSChild.getLength(); i++){
			if(resNSChild.item(i).getNodeName().equals("system")){
				Node nextresNS = resNSChild.item(i).getFirstChild().getNextSibling();
				if(nextresNS.getNodeName().equals("ParS")){
					handleParS(nextresNS, writer, resNS);
				}
				if(nextresNS.getNodeName().equals("resGP")){
					String groupName = nextresNS.getFirstChild().getNextSibling().getTextContent();
					handleAtomicGroup(nextresNS, groupName, writer);
					writer.println("\t\t\t " + groupName + ".run();");
				}
				if(nextresNS.getNodeName().equals("resGS")){
					String groupSystem = nextresNS.getFirstChild().getNextSibling().getTextContent();
					printSysteminClass(nextresNS, groupSystem, writer);
					writer.println("\t\t\t " + groupSystem + ".run();");

				}
			}
		}
	}
	public  void implementGS(Node node) throws IOException{
		HashMap<String,String> previousRes = new HashMap<String, String>();
		Node ancestorGS = node.getParentNode();
		//while(!(ancestorGS.isEqualNode(rootNode))){
		
		while(ancestorGS!=null){
			if(ancestorGS.getNodeName().equals("resNS")){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS);
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
				//edw itan handleresNS
				resNS = findAllResNS(resNS,SGroup.item(p));
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
		writer.println("public class " + classSys + " implements CSProcess {");
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
	    	String groupName = groupP.getFirstChild().getNextSibling().getTextContent() ;
	    	handleAtomicGroup(groupP, groupName, writer);
			writer.println("\t\t\t " + groupName + ".run();");
	    }
	    if(hasResGS){
	    	Node groupS =SGroup.item(indexResGS);
	    	String groupName=groupS.getFirstChild().getNextSibling().getTextContent();
	    	printSysteminClass(groupS, groupName, writer);
			writer.println("\t\t\t " + groupName + ".run();");
	    }
	    writer.println("\t\t }");

	    writer.println("\t }");
		writer.close();

	}
	public static ArrayList<String> resNSinParS(Node nodeS, PrintWriter writer) throws IOException{
		ArrayList<String> internal = new ArrayList<String>();
		HashMap<String, String> previousRes = new HashMap<String, String>();
		
		Node ancestorGS = nodeS.getParentNode();		
		while(ancestorGS!=null){
			if(ancestorGS.getNodeName().equals("resNS")){
				HashMap <String, String> ResNSpart =  new HashMap<String, String>();
				ResNSpart = handleresNS(ResNSpart,ancestorGS); //mporei na exei lathos
				previousRes.putAll(ResNSpart);
			}	
			ancestorGS = ancestorGS.getParentNode();
		}

		NodeList resNSChi = nodeS.getChildNodes();
		Node afterRes=null;
		for(int i=0; i<resNSChi.getLength(); i++){
			if(resNSChi.item(i).getNodeName().equals("system")){
				afterRes = resNSChi.item(i).getFirstChild().getNextSibling();
			}
		}
		
		if(afterRes.getNodeName().equals("resGP")){
			internal.add(afterRes.getFirstChild().getNextSibling().getTextContent());
			String group = afterRes.getFirstChild().getNextSibling().getTextContent() ;
			handleAtomicGroup(afterRes, group, writer );
		//	writer.println("\t\t\t " + group + ".run();");

		}
		if(afterRes.getNodeName().equals("resGS")){
			internal.add(afterRes.getFirstChild().getNextSibling().getTextContent());
			String group = afterRes.getFirstChild().getNextSibling().getTextContent();
			printSysteminClass(afterRes, group, writer);
		}
		if(afterRes.getNodeName().equals("ParS")){
			internal.addAll(ParSnotinresGS(afterRes, writer));
		}


		return internal;
	}
	
	//EMEINA EDW
	public static ArrayList<String> ParSnotinresGS(Node node, PrintWriter writer) throws IOException{
		ArrayList<String> groups = new ArrayList<String>();
		NodeList parch = node.getChildNodes();
		for(int i=0; i<parch.getLength(); i++){
			Node ch = null;
			if(parch.item(i).getNodeName().equals("system")){
				ch = parch.item(i).getFirstChild().getNextSibling();
				if(ch.getNodeName().equals("resGP")){
					groups.add(ch.getFirstChild().getNextSibling().getTextContent());
					String group = ch.getFirstChild().getNextSibling().getTextContent() ;
					handleAtomicGroup(ch, group, writer );
					//writer.println("\t\t\t " + group + ".run();");
				}
				if(ch.getNodeName().equals("resGS")){
					groups.add(ch.getFirstChild().getNextSibling().getTextContent());
					String group = ch.getFirstChild().getNextSibling().getTextContent();
					printSysteminClass(ch, group, writer);

				}
				if(ch.getNodeName().equals("resNS")){
					groups.addAll(resNSinParS(ch, writer));
				}
				
				
			}
		}
		
		
		
		
		return groups;
	}
	
	public static HashMap<String, String> findAllResNS(HashMap<String, String> resNSmap, Node node){
		NodeList childrenParS = node.getChildNodes();
		if(node.getNodeName().equals("resNS")){
			HashMap<String, String> internalResNS = new HashMap<String, String>();
			internalResNS = handleresNS(internalResNS, node);
			resNSmap.putAll(internalResNS);
		}
		for(int i=0; i<childrenParS.getLength(); i++){
			if(childrenParS.item(i).getNodeName().equals("system")){
				Node nodeS = childrenParS.item(i).getFirstChild().getNextSibling();
				if(nodeS.getNodeName().equals("resNS")){
					HashMap<String, String> internalResNS = new HashMap<String, String>();
					internalResNS = handleresNS(internalResNS, nodeS);
					resNSmap.putAll(internalResNS);
					resNSmap.putAll(findAllResNS(resNSmap, nodeS));
					//return resNSmap;

				}
				if(nodeS.getNodeName().equals("ParS")){
					resNSmap.putAll(findAllResNS(resNSmap, nodeS));
					//return resNSmap;
				}
			}
		}
		return resNSmap;
	}
	
	public static void createRootParS(Node node) throws IOException{
		//globalChannels
		String fileName=  "SystemBoot.java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + "SystemBoot" + " implements CSProcess {");
		NodeList childrenParS = node.getChildNodes();
		ArrayList<String> parallelItems = new ArrayList<String>();
		HashMap<String, String> resNSmap = new HashMap<String, String>();
		resNSmap = findAllResNS(resNSmap, node);

		writer.println("\t public String nameSystem = \"SystemBoot\" ;" );
	    Iterator it = resNSmap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }

	    writer.println("\t\t public SystemBoot " + "(){" );
	    Iterator it4 = resNSmap.entrySet().iterator();
	    while (it4.hasNext()) {
	        Map.Entry pair = (Map.Entry)it4.next();
	        String namen= (String) pair.getKey();
	        String typename = (String) pair.getValue();
	        writer.println("\t\t\t " + namen + " = new ChannelValue(); ");
	        writer.println("\t\t\t " + namen + ".type = \"" + typename +"\" ;");
	    }
	    writer.println("\t\t }");

	    writer.println("\t\t public void run() {");

		
		int numResNS = 0;
		for(int i=0; i<childrenParS.getLength(); i++){
			if(childrenParS.item(i).getNodeName().equals("system")){
				Node nodeS = childrenParS.item(i).getFirstChild().getNextSibling();
				if(nodeS.getNodeName().equals("resNS")){
					HashMap<String, String> internalResNS = new HashMap<String, String>();
					numResNS++;
					String groupName ="resNS"+numResNS;
					parallelItems.addAll(resNSinParS(nodeS, writer));
					//resNSinParS(groupName, nodeS, internalResNS);
					//parallelItems.add(groupName);
				}
				if(nodeS.getNodeName().equals("resGP")){
			    	String groupName = nodeS.getFirstChild().getNextSibling().getNodeName() ;
			    	handleAtomicGroup(nodeS, groupName, writer);
					parallelItems.add(groupName);
			    	//writer.println("\t\t\t " + groupName + ".run();");
				}
				if(nodeS.getNodeName().equals("resGS")){
					String groupName = nodeS.getFirstChild().getNextSibling().getTextContent();
					parallelItems.add(groupName);
					printSysteminClass(nodeS, groupName, writer);

				}
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
		writer.println("\t\t }");

	    writer.println("}");
	    writer.close();
	}
	public static void createRoorResNS(Node node) throws IOException{
		//globalChannels
		String fileName=  "SystemBoot.java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + "SystemBoot" + " implements CSProcess {");
		HashMap<String, String> resNSmap = new HashMap<String, String>();
		resNSmap = findAllResNS(resNSmap, node);
		writer.println("\t public String nameSystem = \"SystemBoot\" ;" );
	    Iterator it = resNSmap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
			writer.println("\t public ChannelValue " + pair.getKey() + " ;");
	    }

	    writer.println("\t\t public SystemBoot " + "(){" );
	    Iterator it4 = resNSmap.entrySet().iterator();
	    while (it4.hasNext()) {
	        Map.Entry pair = (Map.Entry)it4.next();
	        String namen= (String) pair.getKey();
	        String typename = (String) pair.getValue();
	        writer.println("\t\t\t " + namen + " = new ChannelValue(); ");
	        writer.println("\t\t\t " + namen + ".type = \"" + typename +"\" ;");
	    }
	    writer.println("\t\t }");

	    writer.println("\t\t public void run() {");
	    NodeList resNSchildren = node.getChildNodes();
	    for(int p=0; p<resNSchildren.getLength(); p++){
	    	if(resNSchildren.item(p).getNodeName().equals("system")){
	    		Node systemType = resNSchildren.item(p).getFirstChild().getNextSibling();
	    		if(systemType.getNodeName().equals("ParS")){
	    			ArrayList<String> parallelSystems = new ArrayList<String>();
	    			parallelSystems.addAll(ParSnotinresGS(systemType, writer));
	    			writer.print("\t\t\t CSProcess[] parParts = new CSProcess[]{");
	    			for(int k=0; k<parallelSystems.size(); k++){
	    				writer.print(parallelSystems.get(k));
	    				if(k!=parallelSystems.size()-1){
	    					writer.print(",");
	    				}
	    			}
	    			writer.println("};");
	    			writer.println("\t\t\t Parallel par = new Parallel(parParts);");
	    			writer.println("\t\t\t par.run();");

	    			
	    		}
	    		if(systemType.getNodeName().equals("resGS")){
	    			String groupName = systemType.getFirstChild().getNextSibling().getNodeName();
					printSysteminClass(systemType, groupName, writer);
			    	writer.println("\t\t\t " + groupName + ".run();");

	    		}
	    		if(systemType.getNodeName().equals("resGP")){
	    			String groupName = systemType.getFirstChild().getNextSibling().getTextContent();
					handleAtomicGroup(systemType, groupName, writer);
			    	writer.println("\t\t\t " + groupName + ".run();");

	    		}
	    		
	    		
	    		
	    	}
	    }
		
		
	    writer.println("\t\t }");

	    writer.println("}");
		writer.close();

	}
	public static void createRootResGP(Node node) throws IOException{
		//global Channels
		String fileName=  "SystemBoot.java";
		@SuppressWarnings("resource")
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		new File(fileName).createNewFile();
		writer.println("import org.jcsp.lang.*;");
		writer.println("import java.io.*;");
		writer.println("import java.util.ArrayList;");
		writer.println("import java.util.HashMap;");
		writer.println("public class " + "SystemBoot" + " implements CSProcess {");
		writer.println("\t public String nameSystem = \"SystemBoot\" ;" );
	    writer.println("\t\t public SystemBoot " + "(){" );
	    writer.println("\t\t}");
		String groupName = node.getFirstChild().getNextSibling().getNodeName();
		handleAtomicGroup(node, groupName, writer);
    	writer.println("\t\t\t " + groupName + ".run();");

	    writer.println("}");
		writer.close();

	}

	public void decideItem(Node node, boolean foundGS) throws IOException{
		NodeList nList = node.getChildNodes(); 
		if(node.isEqualNode(rootNode)){
			if(node.getNodeName().equals("ParS")){
				createRootParS(node);
			}
			if(node.getNodeName().equals("resNS")){
				createRoorResNS(node);
			}
			if(node.getNodeName().equals("resGP")){
				createRootResGP(node);
			}
		}

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
	public boolean checkResIn(Node node, String str){
		Node ancestor = node.getParentNode();
		boolean nonglobal = false;
		while(ancestor!=null){
			if((ancestor.getNodeName().equals("resNS"))||(ancestor.getNodeName().equals("resNP"))){
				NodeList ancch = ancestor.getChildNodes();
				for(int b=0; b<ancch.getLength(); b++){
					if(ancch.item(b).getNodeName().equals("name")){
						if(ancch.item(b).getTextContent().equals(str))
							nonglobal=true;
					}
				}
				
				
			}
			if(ancestor.getNodeName().equals("in")){
				NodeList childrenIn = ancestor.getChildNodes();
				Node inplace = null;
				Node type = null;
				for(int v=0; v<childrenIn.getLength(); v++){
					if(childrenIn.item(v).getNodeName().equals("obj")){
						if(childrenIn.item(v).getTextContent().equals(str))
							nonglobal=true;
					
					}
				}
			}	

			ancestor = ancestor.getParentNode();
		}
		return nonglobal;
	}
	public ArrayList<String> decideGlobals(Node node, ArrayList<String> global){
		NodeList chi = node.getChildNodes();
		
		
		for(int i = 0; i<chi.getLength(); i++){
			String name = null;
			if(chi.item(i).equals("in")){
				NodeList vhIn = chi.item(i).getChildNodes();
				for(int j=0; j<vhIn.getLength(); j++){
					if (vhIn.item(j).getNodeName().equals("subj")){
						name=vhIn.item(j).getTextContent();
						if(!checkResIn(chi.item(i), name))
							global.add(name);
					}
				}
			}
			if(chi.item(i).equals("out")){
				String name2=null;
				NodeList vhIn = chi.item(i).getChildNodes();
				for(int j=0; j<vhIn.getLength(); j++){
					if (vhIn.item(j).getNodeName().equals("subj")){
						name=vhIn.item(j).getTextContent();
						if(!checkResIn(chi.item(i), name))
							global.add(name);

					}
					if (vhIn.item(j).getNodeName().equals("obj")){
						name2=vhIn.item(j).getTextContent();
						if(!checkResIn(chi.item(i), name2))
							global.add(name2);

					}
				}
			}
			return global;
		}
		
		return global;
		
	}
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		ConvertSystem myParser = new ConvertSystem("FilesForXMLPrograms/HouseSystem.xml");
		boolean foundGS=false;
		//CreateBaseTypes dimiourgia = new CreateBaseTypes();		
		//dimiourgia.AddBaseObject();
		//for(int b=0; b<dimiourgia.BaseObj.size(); b++){
		//	myParser.baseTypes.add(dimiourgia.BaseObj.get(b).nameOb);
		//}
		//ArrayList<String> inoutch = new ArrayList<String>();
		//inoutch.addAll(myParser.decideGlobals(myParser.rootNode, inoutch));
		
		myParser.decideItem(myParser.rootNode,foundGS);
		

	}
}
