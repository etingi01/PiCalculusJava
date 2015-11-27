package Parser;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Parser.Tree.nod;

import javax.xml.parsers.*;
import java.io.*;

public class myDomParser {
	private Document doc;
	private Node rootNode;
	
	public String decideProcess(Node node){
		String s="";
		if (node.getParentNode().getNodeName().equals("in")){
			s=".(";
			return s;
		}
		if (node.getParentNode().getNodeName().equals("out")){
			s=".(";
			return s;
		}
		s="(";
		return s;
	}
	
	public String decideSymbol(Node node, int n){
		String sy = "";
		String nodeName = node.getNodeName();
		switch (nodeName){
		case "process": sy=decideProcess(node);
		break;
		case "resNP": sy="(new ";
		break;
		case "in": sy = "in ";
		break;
		case "out": sy="out " ;
		break;
		case "subj": sy=node.getTextContent() + "(";
	//	System.out.println("Subj: "+  sy);
		break;
		case "obj":sy=node.getTextContent() +" : ";
	//	System.out.println("obj: "+  sy);
		break;
		case "type": sy= node.getTextContent()+ ") ";
		break;
		case "name": sy= node.getTextContent()+ " : ";
		break;
		case "Nil": sy= "0";
		break;
		case "repl": sy= "!";
		break;
		case "ParP": 
			sy="( ";
		break;
		case "sumP": 
			sy="( ";
		break;
		
		default: sy=nodeName;
		break;
		}
		if (n==33){
			if(node.getParentNode().getNodeName().equals("process")){
				Node father = node.getParentNode();
				if (father.getParentNode().getNodeName().equals("ParP")){
				//	System.out.println("Irthe Edw");
					if(father.isEqualNode(father.getParentNode().getFirstChild())){
						sy=sy+" | ";
					}
				}
				if (father.getParentNode().getNodeName().equals("sumP")){
					if(father.isEqualNode(father.getParentNode().getFirstChild())){
						sy=sy+" + ";
					}
				}
				
			}			
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
				String p="";
				String a="";
			while ((!ancestor.isEqualNode(rootNode))){
				if (ancestor.isEqualNode(rootNode))
					break;
				if (ancestor.getNodeName().equals("process")){
					sy = sy + " ) ";
				}
				if (ancestor.getNodeName().equals("sumP")){
					sy = sy + " ) " ;
				}
				if (ancestor.getNodeName().equals("ParP")){
					sy = sy + " ) " ;					
				}
				
				p="";
				Node ancestor2 = ancestor.getParentNode();
				lastAnc = ancestor2.getLastChild();
				prinText = lastAnc.getPreviousSibling();
				
				/*if (!(prinText.isEqualNode(ancestor))){
					break;
				}*/
				if (ancestor.isEqualNode(ancestor2.getFirstChild().getNextSibling())){
					if (ancestor2.getNodeName().equals("ParP")){
					sy=sy+"| ";
					break;
					}
					else
						if (ancestor2.getNodeName().equals("sumP")){
							sy=sy + "+ ";
							break;

						}
				}else{
					p=a="";
					if (!(prinText.isEqualNode(ancestor))){
						break;
					}
						
					}
				ancestor = ancestor.getParentNode();
				
			}
			
			}
		
		}		
		return sy;
        
        
	}
String all="";
	public void printPi(Node nNode, String spaces, int n){
		//System.out.println(spaces + nNode.getNodeName());
		//System.out.println(nNode.getNodeName() + spaces);
		String part="";
		if (n>1){
		part = decideSymbol(nNode,n);
		}
		spaces= spaces + part;
		all = all + part;
		//System.out.print(part);
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	//System.out.println(nList.item(temp).getNodeName());
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		if ((temp==nList.getLength()-2)){
        			n=33;
        		}else{
        			n=2;
        		}
        		printPi(nList.item(temp), spaces, n);
        	}    	
        }
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
	public myDomParser(String file) throws SAXException, IOException, ParserConfigurationException{
	File inputFile = new File(file);
    DocumentBuilderFactory dbFactory 
       = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    doc = dBuilder.parse(inputFile);
    rootNode = doc.getDocumentElement();
	}

	public static void convertion(Node node){
		Tree myTree;
		nod riza =new nod<String>();
		riza.data="Process";

		if (node.getNodeName().equals("Parp")){
			
			NodeList nlist = node.getChildNodes();
			int num=0;
			for (int i=0; i<nlist.getLength(); i++){
				if (nlist.item(i).getNodeName().equals("process")){
					num++;
				}
			}
			for (int i=0; i<=num; i++){
				riza.children.add("Summation" + i);
			}
		}
		
		if (node.getNodeName().equals("in")|node.getNodeName().equals("out")){
			riza.data="Process";
			riza.children.add("Summation 1");
			nod paidi = (nod) riza.children.get(0);
			paidi.children.add("ActionSequence");
			nod paidi2 = (nod) paidi.children.get(0);
			paidi2.children.add("in");
			
			
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		myDomParser myParser = new myDomParser("FilesForXMLPrograms/newFile.xml");
    	myParser.printPi(myParser.rootNode,"", 1);   
    	myParser.printXML(myParser.rootNode,"");   

    	System.out.println(myParser.all);
    	//System.out.println(myParser.decideSymbol(myParser.rootNode.getFirstChild().getNextSibling(), "", 1));
	}

}
