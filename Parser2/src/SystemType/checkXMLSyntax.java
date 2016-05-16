package SystemType;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class checkXMLSyntax {
	public static String sysfile = "";
	public static String gafile = "";
	public boolean mist = false;
	public static ArrayList<String> baset = new ArrayList<String>();
	public Document doc;
	public Node rootNode;
	public Node ParPNode;
	public String outTree = "";
	public checkXMLSyntax(String file, String gatr, ArrayList<String> ba) throws ParserConfigurationException, SAXException, IOException{
		this.sysfile = file;
		this.gafile = gatr;
		this.baset = ba;
		File inputFile = new File(file);
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(inputFile);
		rootNode = doc.getDocumentElement();
	}
	public boolean checkAfterSystem(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(chN.equals("parS"))
        		Notcorrect = false;
        	if(chN.equals("resGS"))
        		Notcorrect = false;
        	if(chN.equals("resGP"))
        		Notcorrect = false;
        	if(chN.equals("resNS"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()>1){
        	m=true;
        }
		return m;
	}
	
	public boolean checkAfterRepl(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(i==0 && chN.equals("process"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()!=1)
        	m=true;
		return m;
	}
	public boolean checkAfterParP(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(chN.equals("process"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()<2){
        	m = true;
        }
		return m;
	}

	
	
	public boolean checkAfterParS(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(chN.equals("system"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()<2){
        	m=true;
        }
		return m;
	}

	
	
	public boolean checkAfterResGP(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        System.out.println("resGP" + children.size());
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	
        	if(i==0 && chN.equals("group"))
        		Notcorrect = false;
        	if(i==1 && chN.equals("process"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()!=2){
        	m=true;
        }
		return m;
	}

	public boolean checkAfterResGS(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(i==0 && chN.equals("group"))
        		Notcorrect = false;
        	if(i==1 && chN.equals("system"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()!=2)
        	m=true;
		return m;
	}
	public boolean checkAfterResNP(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(((i%2)==0) && chN.equals("name"))
        		Notcorrect = false;
        	if(((i%2)==1) && chN.equals("type"))
        		Notcorrect = false;
        	if((i!=0) && ((i%2)==0) && chN.equals("process"))
        		Notcorrect = false;
          	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        ArrayList<String> chNames = new ArrayList<String>();
        for(int b=0; b<children.size(); b++){
        	chNames.add(children.get(b).getNodeName());
        }
        if((!chNames.contains("process"))||(!chNames.contains("name"))||(!chNames.contains("type")))
        	m= true;
        return m;
	}

	public boolean checkAfterResNS(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();	
        System.out.println("resNS children: " + children);

        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        System.out.println("resNS children: " + children);
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(((i%2)==0) && chN.equals("name"))
        		Notcorrect = false;
        	if(((i%2)==1) && chN.equals("type"))
        		Notcorrect = false;
        	if((i!=0) && ((i%2)==0) && chN.equals("system"))
        		Notcorrect = false;
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        ArrayList<String> chNames = new ArrayList<String>();
        for(int b=0; b<children.size(); b++){
        	chNames.add(children.get(b).getNodeName());
        }
        if((!chNames.contains("system"))||(!chNames.contains("name"))||(!chNames.contains("type")))
        	m= true;
        	return m;
	}

	public boolean checkAfterOut(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(i==0 && chN.equals("subj"))
        		Notcorrect = false;
        	if(i==1 && chN.equals("obj"))
        		Notcorrect = false;
        	if(i==2 && chN.equals("process"))
        		Notcorrect = false;        	
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()!=3){
        	m=true;
        }
		return m;
	}
	public boolean checkAfterType(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
        return m;
	}

	public boolean checkAfterName(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
		return m;
	}

	
	
	public boolean checkAfterObj(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
		return m;
	}

	
	
	public boolean checkAfterSubj(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
		return m;
	}
	public boolean checkAfterNil(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
		return m;
	}
	public boolean checkAfterIn(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(i==0 && chN.equals("subj"))
        		Notcorrect = false;
        	if(i==1 && chN.equals("obj"))
        		Notcorrect = false;
        	if(i==2 && chN.equals("type"))
        		Notcorrect = false;        
        	if(i==3 && chN.equals("process"))
        		Notcorrect = false;        	
        	m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}   	
        }
        if(children.size()!=4){
        	m=true;
        }
		return m;
	}

	
	public boolean checkAfterProcess(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        for(int i=0; i<children.size(); i++){
        	String chN = children.get(i).getNodeName();
        	boolean Notcorrect = true;
        	if(chN.equals("in"))
        		Notcorrect = false;
        	if(chN.equals("inExternal"))
        		Notcorrect = false;
        	if(chN.equals("out"))
        		Notcorrect = false;
        	if(chN.equals("outExternal"))
        		Notcorrect = false;
        	if(chN.equals("parP"))
        		Notcorrect = false;
        	if(chN.equals("repl"))
        		Notcorrect = false;
        	if(chN.equals("Nil"))
        		Notcorrect = false;
        	if(chN.equals("resNP"))
        		Notcorrect = false;
            m = Notcorrect;
        	if(Notcorrect){
        		break;
        	}
        }
        if(children.size()>1){
        	m=true;
        }
		return m;
	}
	
	public boolean checkAfterGroup(Node node){
		boolean m = true;
		NodeList nList = node.getChildNodes(); 
		ArrayList<Node> children = new ArrayList<Node>();		
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		children.add(nList.item(temp));
        	}    	
        }       
        if(children.size()==0){
        	m=false;
        }
		return m;
	}

	
	public void printXML(Node nNode, String spaces){
		String message = "";
		
		spaces= spaces + "    ";
		boolean mistake = false;
		String typeEdge = nNode.getNodeName();
		switch (typeEdge){
		case "process": 
			mistake = checkAfterProcess(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above process.";
			}
			break;
		case "system":
			mistake = checkAfterSystem(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above system.";
			}
			break;
		case "parP":
			mistake = checkAfterParP(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above parallel process composition.";
			}
			break;
		case "parS":
			mistake = checkAfterParS(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above parallel system composition.";
			}
			break;
		case "resGS":
			mistake = checkAfterResGS(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above new system construct.";
			}
			
			break;	
		case "resGP":
			mistake = checkAfterResGP(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above new group construct.";
			}
			break;
		case "resNP": 
			mistake = checkAfterResNP(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above new name/channel construct.";
			}
		break;
		case "in":
			mistake = checkAfterIn(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above input action construct.";
			}
		break;
		case "out": 
			mistake = checkAfterOut(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above output action construct.";
			}
		break;
		
		case "inExternal" : 
			mistake = checkAfterIn(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above input action construct.";
			}
		break;
		case "outExternal": 
			mistake = checkAfterOut(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above output action construct.";
			}
		break;
		case "subj":
			mistake = checkAfterSubj(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: Channels must not have children.";
			}
			break;
		case "obj":
			mistake = checkAfterObj(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: Channels must not have children.";
			}
			break;
		case "Nil": 
			mistake = checkAfterNil(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: Nil constructs must not have children.";
			}
			break;
		case "repl": 
			mistake = checkAfterRepl(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above replication action construct.";
			}
			break;
		case "resNS": 
			mistake = checkAfterResNS(nNode);
			if(mistake){
				message = "\n" + spaces + "There is a mistake on the children of the above new name construct.";
			}
		break;
		case "type":
			mistake = checkAfterType(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: The edges of type declaration must not have children.";
			}
		break;
		case "name":
			mistake = checkAfterName(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: The edges of name declaration must not have children.";
			}
		break;		

		case "group": 
			mistake = checkAfterGroup(nNode);
			if(mistake){
				message = "\n" + spaces + "Mistake: The edges of groups' name declaration must not have children.";
			}
		break;
		default: 
			mistake = true;
			message = "\n" + spaces + "The above name of edge is not permitted. \n" + spaces +  "Please read again the user guide.";
			break;
		}		
		outTree = outTree + "\n" + spaces + nNode.getNodeName() + message;  
		if(mistake){
			outTree = outTree + "\n\n";
			this.mist = mistake;
			
			return;
		/*	JTextArea textArea = new JTextArea(outTree);
			JScrollPane scrollPane = new JScrollPane(textArea);  
			textArea.setLineWrap(true);  
			textArea.setWrapStyleWord(true); 
			scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
			String[] options = new String[] {"Insert new XML file", "Exit"};
			int res = JOptionPane.showOptionDialog(
				    null, 
				    scrollPane,
				    "Error!", 
				    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
			        null, options, options[0]);*/

		
		
		}else{		
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		if(!this.mist)
        		printXML(nList.item(temp), spaces);
        	}    	
        }
		}
	}	

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		ArrayList<String> non = new ArrayList<String>();
		checkXMLSyntax checked = new checkXMLSyntax("FilesForXMLPrograms/NewFile.xml", "FilesForXMLPrograms/g_attrubute.xml", non);
		checked.printXML(checked.rootNode, "");
	}
	
}
