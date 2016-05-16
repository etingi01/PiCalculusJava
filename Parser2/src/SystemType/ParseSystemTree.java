package SystemType;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import Parser.Tree.nod;
import javax.xml.parsers.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.*;
import java.util.ArrayList;

public class ParseSystemTree {
	public Document doc;
	public Node rootNode;
	public Node ParPNode;
	public static ArrayList<String> basetype = new ArrayList<String>();
	public static String syste = "";
	public static String ga = "";
	public String decideProcess(Node node){
		String s=" ";
		if(!(node.getFirstChild().getNextSibling().getNodeName().equals("Nil"))){   
			if ((node.getParentNode().getNodeName().equals("in"))||(node.getParentNode().getNodeName().equals("inExternal"))){
				s=". ";
				return s;
			}
			if ((node.getParentNode().getNodeName().equals("out"))||(node.getParentNode().getNodeName().equals("outExternal"))){
				s=". ";
				return s;
			}
		}
		return s;
	}
	
	public String decideSymbol(Node node, int n){
		String sy = "";
		String nodeName = node.getNodeName();
		switch (nodeName){
		//prepei na diorthwsw ti diatipwsi oson afora to process-ParP
		case "process": sy=decideProcess(node);
		break;
		case "resNP": sy="";
		break;
		case "in": sy = "in ";
		break;
		case "out": sy="out " ;
		break;
		
		case "inExternal" : sy = "in ";
		break;
		case "outExternal": sy="out " ;
		break;
		case "subj":
		if(node.getParentNode().getNodeName().equals("in")||node.getParentNode().getNodeName().equals("inExternal")){
			 sy=node.getTextContent() + "(";
		}
		else{
			sy=node.getTextContent();

		}
		break;
		case "obj":
		if(node.getParentNode().getNodeName().equals("in")||node.getParentNode().getNodeName().equals("inExternal")){
			sy=node.getTextContent() +" : ";
		}
		else{
			sy="(" + node.getTextContent() + ") ";

		}
		break;		
		case "Nil": sy= ". 0 ";
		break;
		case "repl": sy= "! (";
		break;
		case "parP": 
			sy="( ";
		break;
		case "resGP": sy="(new ";
		break;
		case "resGS": sy="(new ";
		break;
		case "resNS": sy="";
		break;
		case "system":
			NodeList systemChild = node.getChildNodes();
			boolean hasParS=false;			
			for (int i=0; i<systemChild.getLength(); i++){
				if(systemChild.item(i).getNodeName().equals("parS"))
					hasParS=true;
				if(systemChild.item(i).getNodeName().equals("resGP"))
					hasParS=true;
				if(systemChild.item(i).getNodeName().equals("resGS"))
					hasParS=true;
				if(systemChild.item(i).getNodeName().equals("resNS"))
					hasParS=true;
			}
			if(!hasParS)
			sy="(";
			else
				sy="";
		break;
		case "type": sy= node.getTextContent()+ ") ";
		break;
		case "name": sy="(new " + node.getTextContent()+ " : ";
		break;		

		case "group": sy=node.getTextContent()+") ";
		
		if (node.getParentNode().getNodeName().equals("resGP")){
			sy = sy+ "( ";
		}
		break;
		case "parS": 
			sy="[";
		break;
		case "sumP": 
			sy="( ";
		break;
		
		default: sy=nodeName;
		break;
		}
		NodeList nodeChildren = node.getChildNodes();
		int numChildText = 0;
		for (int i = 0; i<nodeChildren.getLength(); i++) {
			  Node e = nodeChildren.item(i);
			   if (e.getNodeName().equals("#text")) {
				   numChildText++;
			   }
		}
		if ((nodeChildren.getLength()==0)||(numChildText==nodeChildren.getLength())){
			Node ancestor = node.getParentNode();
			Node lastAnc = ancestor.getLastChild();
			Node prinText = lastAnc.getPreviousSibling();
			if (prinText.isEqualNode(node)){
				String p="";
				String a="";
			Node previousanc = ancestor;
			while ((!ancestor.isEqualNode(rootNode))){
				if (ancestor.isEqualNode(rootNode))
					break;
				if(ancestor.getNodeName().equals("process")||ancestor.getNodeName().equals("system"))
					previousanc = ancestor;
				else
				
				if(ancestor.getNodeName().equals("repl")){
					if(previousanc.isSameNode(ancestor.getLastChild().getPreviousSibling())){
						sy = sy + ")" ;					

					}			
				}
				else
				if(ancestor.getNodeName().equals("parP")){
					if(previousanc.isSameNode(ancestor.getLastChild().getPreviousSibling())){						
						sy = sy + ")" ;					

					}
				}
				else{
				if(ancestor.getNodeName().equals("resGP")){
					boolean pr = true;
					Node fa = node.getParentNode();
					while(!fa.isSameNode(ancestor)){
						if(!fa.isSameNode(fa.getParentNode().getLastChild().getPreviousSibling())){
							pr = false;
							break;
						}
						fa = fa.getParentNode();
					}
					if(pr){
						sy = sy + ")" ;					

					}		
					else
						sy = sy + "" ;
				}
				}
				if (ancestor.getNodeName().equals("parS")){
					sy = sy + "]" ;					
				}
				p="";
				Node ancestor2 = ancestor.getParentNode();
				lastAnc = ancestor2.getLastChild();
				prinText = lastAnc.getPreviousSibling();
				if (ancestor.isEqualNode(ancestor2.getFirstChild().getNextSibling())){					
					if ((ancestor2.getNodeName().equals("parS"))/*||(ancestor2.getNodeName().equals("parP"))*/){
						Node prx = node.getParentNode();
						boolean rmo = true;
						while(prx.getParentNode()!=ancestor){
							if(prx.isSameNode(prx.getParentNode().getLastChild().getPreviousSibling())){
								prx = prx.getParentNode();
							}
							else {
								rmo = false;
								break;
							}
						}
						if(rmo)
						sy=sy+" | ";	
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
		if (n==33){			
			if(!(node.getChildNodes().getLength()>1))
			if(node.getParentNode().getNodeName().equals("system")||node.getParentNode().getNodeName().equals("process")){
				Node father = node.getParentNode();
				Node pre = null;
				while(father.getParentNode()!=null){
					pre = father;
					if((father.getParentNode().getNodeName().equals("parS")||father.getParentNode().getNodeName().equals("parP"))){
						if((!(father.isSameNode(father.getParentNode().getLastChild().getPreviousSibling()))) && (father.getParentNode().getNodeName().equals("parP"))){
						
						sy=sy+" | ";
						break;
						}
						else{
							if(father.getParentNode().getNodeName().equals("parP") && father.getParentNode().getParentNode().getParentNode().getNodeName().equals("parP")){
								sy=sy+" | ";
								break;
							}else{
								if(father.getParentNode().getNodeName().equals("parS") && father.getParentNode().getParentNode().getParentNode().getNodeName().equals("parS")){
									sy=sy+" | ";
									break;
								}
								else
									break;			
							}
						}
					}
					father = father.getParentNode();

				}
			}			
		}
		return sy;     
	}
String all="";
	public void printPi(Node nNode, String spaces, int n){
		String part="";
		part = decideSymbol(nNode,n);
		spaces= spaces + part;
		all = all + part;
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
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
		
		NodeList nList = nNode.getChildNodes();  
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	if (!(nList.item(temp).getNodeName().equals("#text"))){
        		printXML(nList.item(temp), spaces);
        	}    	
        }       
	}	

	public ParseSystemTree(String file, String gat, ArrayList<String> bt) throws SAXException, IOException, ParserConfigurationException{
		this.syste = file;
		this.ga = gat;
		this.basetype = bt;
		File inputFile = new File(file);
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(inputFile);
		rootNode = doc.getDocumentElement();
	}
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		/*ParseSystemTree myParser = new ParseSystemTree("FilesForXMLPrograms/SystemETP.xml");
    	myParser.printXML(myParser.rootNode,"");   
		myParser.printPi(myParser.rootNode,"", 1);   
    	System.out.print(myParser.all);
    	JTextArea msg = new JTextArea(myParser.all);
    	msg.setLineWrap(true);
    	Insets m = new Insets(5,5,5,5);
    	msg.setMargin(m);
    	msg.setWrapStyleWord(true);
    	msg.setSize(650, 150);
   		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setEditable(false);
		Font f = new Font("Dialog", Font.BOLD, 11);
		msg.setFont(f);
    	JScrollPane scrollPane = new JScrollPane(msg);
    	int dialogResult = JOptionPane.showConfirmDialog(null, scrollPane, "Is this your modelled system in Pi-Calculus Syntax?",JOptionPane.YES_NO_OPTION);  	
    	if(dialogResult == JOptionPane.YES_OPTION){
    		translation();
    	}
    	if(dialogResult == JOptionPane.NO_OPTION){
			GUIselFiles showF = new GUIselFiles(myParser.basetype);
			showF.askFiles();
    	}*/
	}
}
