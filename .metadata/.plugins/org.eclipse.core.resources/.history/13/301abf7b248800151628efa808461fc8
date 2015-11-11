import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class DomParser {
	private Document doc;
	private Node rootNode;
	public DomParser(String file) throws SAXException, IOException, ParserConfigurationException{
	File inputFile = new File(file);
    DocumentBuilderFactory dbFactory 
       = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    doc = dBuilder.parse(inputFile);
    rootNode = doc.getDocumentElement();
	}
	
	public void printXML(Node nNode, String spaces){
		spaces= spaces + " ";
		System.out.println(spaces + nNode.getNodeName());
		//System.out.println(nNode.getNodeName() + spaces);
		
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	//System.out.println(nList.item(temp).getNodeName());
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		printXML(nList.item(temp), spaces);
        	}    	
        }
        
	}	
        
	public void printPi(Node nNode, String spaces, int n){
		//System.out.println(spaces + nNode.getNodeName());
		//System.out.println(nNode.getNodeName() + spaces);
		String part="";
		if (n>1){
		part = decideSymbol(nNode);
		}
		spaces= spaces + part;
		System.out.print(part);
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	//System.out.println(nList.item(temp).getNodeName());
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		printPi(nList.item(temp), spaces, ++n);
        	}    	
        }
	}	
	
	public String decideSymbol(Node node){
		String sy;
		String nodeName = node.getNodeName();
		switch (nodeName){
		case "concurrency": sy="|";
		break;
		case "punto": sy=".";
		break;
		case "new": sy="(new "+node.getTextContent() + " ) ";
		break;
		case "in": sy = "in "+node.getTextContent();
		break;
		case "out": sy="out " + node.getTextContent();
		break;
		case "brackets": sy="(";
		break;
		case "summation":sy="";
		break;
		default: sy=nodeName;
		break;
		}
		Node brCheck=node.getParentNode();
		if (brCheck.getNodeName().equals("brackets")){
			Node Last = brCheck.getLastChild();
			Node prinLast = Last.getPreviousSibling();
			System.out.println(prinLast.getNodeName());
			NodeList nList = brCheck.getChildNodes(); 
			int sizeList=nList.getLength();
			if (node.equals(prinLast)){
				sy=sy+") ";
			}
		}
		
		return sy;
	}
	
	
	public Node getRoot(){
		return this.rootNode;
	}
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
    	DomParser myParser = new DomParser("thirdProgram.txt");
    	myParser.printXML(myParser.rootNode, "");    
    }

}
