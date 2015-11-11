package Parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
/*DomParser 
 * Class that is used to parse the xml file.
 * Contains the structure that contains the xml tree (doc)
 * Contains the root node of the tree (rootNode)
 */
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
	
	/* PrintXML: Prints the XML tree.
	 * Recursive Function
	 * Takes the tree's root node and a null String
	 */
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
        
	
	/* Method that converts the xml structure in Pi-Calculus model.
	 * It uses the method decideSymbol for every node of the Tree.
	 * It is a recursive method that passes the tree using DFS
	 */
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
	/* decideSymbol() based on the name of node
	 * return the right String that should be printed
	 * in the PiCalculus form.
	 * Then, there are some steps to print the closing brackets correctly.
	 */
	public String decideSymbol(Node node){
		String sy;
		String nodeName = node.getNodeName();
		switch (nodeName){
		case "concurrency": sy=" | ";
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
		case "plus": sy= " + ";
		break;
		default: sy=nodeName;
		break;
		}
		//System.out.println("Eimaste sto" + node.getNodeName());
		NodeList nodeChildren = node.getChildNodes();
		int numChildText = 0;
		//if (nodeChildren.getLength()!=0)
		//System.out.println("exei paidia: " +nodeChildren.item(0).getNodeName());
		for (int i = 0; i<nodeChildren.getLength(); i++) {
			  Node e = nodeChildren.item(i);
			   if (e.getNodeName().equals("#text")) {
				   numChildText++;
			   }
		}
		if ((nodeChildren.getLength()==0)||(numChildText==nodeChildren.getLength())){
		//	System.out.println(node.getNodeName());
			//System.out.println("mpike edw");
			Node ancestor = node.getParentNode();
			Node lastAnc = ancestor.getLastChild();
			Node prinText = lastAnc.getPreviousSibling();
			//System.out.println(node.getNodeName());
			//System.out.println(prinText.getNodeName());
			if (prinText.isEqualNode(node)){
				//System.out.println("mpike edw");
			while ((!ancestor.isEqualNode(rootNode))){
				if (ancestor.isEqualNode(rootNode))
					break;
				if (ancestor.getNodeName().equals("brackets")){
					sy = sy + " ) ";
				}
				Node ancestor2 = ancestor.getParentNode();
				lastAnc = ancestor2.getLastChild();
				prinText = lastAnc.getPreviousSibling();
				if (!(prinText.isEqualNode(ancestor)))
					break;
				
				ancestor = ancestor.getParentNode();
				
			}
			
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
