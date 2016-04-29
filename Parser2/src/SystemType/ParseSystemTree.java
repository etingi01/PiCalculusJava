package SystemType;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Parser.Tree.nod;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

public class ParseSystemTree {
	private Document doc;
	private Node rootNode;
	private Node ParPNode;
	public String decideProcess(Node node){
		String s=" ";
		if (node.getParentNode().getNodeName().equals("in")){
			s=". ";
			return s;
		}
		if (node.getParentNode().getNodeName().equals("out")){
			s=". ";
			return s;
		}
		//s="(";
		return s;
	}
	
	public String decideSymbol(Node node, int n){
		String sy = "";
		String nodeName = node.getNodeName();
		switch (nodeName){
		//prepei na diorthwsw ti diatipwsi oson afora to process-ParP
		case "process": sy=node.getTextContent();
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
				if(systemChild.item(i).getNodeName().equals("ParS"))
					hasParS=true;
				if(systemChild.item(i).getNodeName().equals("resGP"))
					hasParS=true;
				if(systemChild.item(i).getNodeName().equals("resGS"))
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

		case "group": sy=node.getTextContent()+")";
		break;
		case "ParS": 
			sy="[";
		break;
		case "sumP": 
			sy="( ";
		break;
		
		default: sy=nodeName;
		break;
		}
		if (n==33){
			if(node.getParentNode().getNodeName().equals("system")){
				Node father = node.getParentNode();
				if (father.getParentNode().getNodeName().equals("ParS")){
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
				if (ancestor.getNodeName().equals("system")){
					NodeList ancChild = ancestor.getChildNodes();
					boolean hasP=false;
					for(int b=0; b<ancChild.getLength(); b++){
						if(ancChild.item(b).getNodeName().equals("ParS")){
							hasP=true;
						}
						if(ancChild.item(b).getNodeName().equals("resGP")){
							hasP=true;
						}
						if(ancChild.item(b).getNodeName().equals("resGS")){
							hasP=true;
						}

					}
					if(!hasP)
						sy = sy + ")";
					else
						sy = sy + "";
				}
				if (ancestor.getNodeName().equals("ParS")){
					sy = sy + "]" ;					
				}
				
				p="";
				Node ancestor2 = ancestor.getParentNode();
				lastAnc = ancestor2.getLastChild();
				prinText = lastAnc.getPreviousSibling();
				
				/*if (!(prinText.isEqualNode(ancestor))){
					break;
				}*/
				if (ancestor.isEqualNode(ancestor2.getFirstChild().getNextSibling())){
					if (ancestor2.getNodeName().equals("ParS")){
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
	public boolean checkForSame(Node sib,  String ch, String value, String agent){
		boolean exist=false;
		if ((sib.getNodeName().equals("subj"))&&(sib.getNodeValue().equals(ch))){
			if((sib.getNextSibling().getNodeName().equals("obj"))&&(sib.getNextSibling().getNodeValue().equals(value))){
				if (sib.getParentNode().getNodeName().equals(agent)){
					exist=true;
					return exist;
				}
			}	
		}
		
		while(!(sib.equals(null))){
			NodeList sibChildr = sib.getChildNodes();
			for (int i=0; i<sibChildr.getLength(); i++){
	        	if (!(sibChildr.item(i).getNodeName().equals("#text"))){
	        		checkForSame(sibChildr.item(i), ch, value, agent);
	        	}
			}
			
			
			
		}
		return exist;
	}
	public void findParP(Node node){
		NodeList children = node.getChildNodes();  
		Node toRet=node;
		System.out.println(node.getNodeName());
		
		if (!(node.getNodeName().equals("ParP"))){
			
			for (int temp = 0; temp < children.getLength(); temp++) {
	        		if(children.item(temp).getChildNodes().getLength()>1){
	        			System.out.println("Ksana findPar me " + children.item(temp).getNodeName());
	        			System.out.println(children.item(temp).getFirstChild().getNodeName());
	        			findParP(children.item(temp));
	        			return;
	        		}
	        }
		}
		else{
			System.out.println(node.getNodeName());
	    	System.out.println("Tha epistrepsei to node: " + node.getNodeName());
	    	this.ParPNode = node;
	    	return;
		}
		
		
		
		
	}
	public ArrayList<Node> findAllIns(Node process, ArrayList<Node> nodesIn){
		NodeList children = process.getChildNodes();  

		 if (process.getNodeName().equals("in")){
			 nodesIn.add(process);
		 }
		for (int temp = 0; temp < children.getLength(); temp++) {
	        		if(children.item(temp).getChildNodes().getLength()>1){
	        			System.out.println("findAllIns me " + children.item(temp).getNodeName());
	        			System.out.println(children.item(temp).getFirstChild().getNodeName());
	        			return findAllIns(children.item(temp), nodesIn);
	        			
	        		}
	     }	
		return nodesIn;
	}
	public ArrayList<Node> findAllObjs(Node process, ArrayList<Node> nodesProc){
		NodeList children = process.getChildNodes();  

		 if (process.getNodeName().equals("in")||process.getNodeName().equals("out")){
			 nodesProc.add(process);
		 }
		for (int temp = 0; temp < children.getLength(); temp++) {
	        		if(children.item(temp).getChildNodes().getLength()>1){
	        			System.out.println("findAllObjs me " + children.item(temp).getNodeName());
	        			System.out.println(children.item(temp).getFirstChild().getNodeName());
	        			return findAllObjs(children.item(temp), nodesProc);
	        			
	        		}
	     }	
		return nodesProc;

		
	}
	
	public void checkDups(Node node){
		NodeList children = node.getChildNodes();    	
		String ch="";
		if (node.equals(null)){
			return;
		}
        for (int temp = 0; temp < children.getLength(); temp++) {
        	if (!(children.item(temp).getNodeName().equals("#text"))){
        		ArrayList<Node> inObjs = new ArrayList();
        		inObjs = findAllIns(children.item(temp), inObjs);
        		System.out.println("Ta in objects se afto to meros tou par einai: " + inObjs.size());
        		ArrayList<Node> otherObjs = new ArrayList();

        		for(int t = temp+1; t<children.getLength(); t++){
                	if (!(children.item(t).getNodeName().equals("#text"))){
                		otherObjs = findAllObjs(children.item(t), otherObjs);
                	}
        		}
        		System.out.println("Other objs: "+ otherObjs.size());
        		
        		
        		for(int i=0; i<inObjs.size(); i++){
        			boolean sameexist=false;
        			String value="";
    				NodeList ino = inObjs.get(i).getChildNodes();
    				for(int p=0; p<ino.getLength(); p++){
    					if (ino.item(p).getNodeName().equals("obj")){
    						value=ino.item(p).getTextContent();
    						System.out.println("To kanali poy tha elexthei: " + value);
    					}
    				}
        			for(int j=0; j<otherObjs.size(); j++){
        				String ov="";
        				NodeList outO = otherObjs.get(j).getChildNodes();
        				for(int q=0; q<outO.getLength(); q++){
        					if (outO.item(q).getNodeName().equals("obj")){
        						ov=outO.item(q).getTextContent();
        					}
        				}
        				if(ov.equals(value)){
        					System.out.println("Vrike");
        					sameexist=true;
        					maketheChanges(value,children.item(temp), temp );
        				}

        			}
        			if(!sameexist){
        				inObjs.remove(i);
        			}
	
        			
        		}
        		///////////////////////////
        	
            	
        	}    	
        	
        }

	}
	
	public void maketheChanges(String ref, Node whatChange, int process){
		NodeList children = whatChange.getChildNodes();  
		System.out.println("To name einai: " + whatChange.getNodeName());
		System.out.println(ref);
		 if (whatChange.getNodeName().equals("subj")||whatChange.getNodeName().equals("obj")){
			 System.out.println("exoume subj h obj");
			 System.out.println(whatChange.getNodeName() + " = Name kai " + whatChange.getTextContent() );
			 if(whatChange.getTextContent().equals(ref)){
				System.out.println("Tha allaksei:" + ref);
				System.out.println("Prepei na allaxei:" + whatChange.getTextContent());

				String newV = whatChange.getTextContent()+process;
				System.out.println("To neo onoma: "+ newV);
				whatChange.setTextContent(newV);
				
			}
		 }
		 if (children.getLength()>1){
		for (int temp = 0; temp < children.getLength(); temp++) {
        		if (!(children.item(temp).getNodeName().equals("#text"))){
	        			System.out.println(children.item(temp).getFirstChild().getNodeName());
	        			maketheChanges(ref, children.item(temp), process);
	        			
	        		}
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
	

	
	public ParseSystemTree(String file) throws SAXException, IOException, ParserConfigurationException{
	File inputFile = new File(file);
    DocumentBuilderFactory dbFactory 
       = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    doc = dBuilder.parse(inputFile);
    rootNode = doc.getDocumentElement();
	}

	
	
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		ParseSystemTree myParser = new ParseSystemTree("FilesForXMLPrograms/HouseSystem.xml");
    	myParser.printXML(myParser.rootNode,"");   
		myParser.printPi(myParser.rootNode,"", 1);   
    	System.out.print(myParser.all);
    	//myParser.findParP(myParser.rootNode);
		//Node parallel = myParser.ParPNode;

    	//myParser.checkDups(parallel);

    	//System.out.println(myParser.decideSymbol(myParser.rootNode.getFirstChild().getNextSibling(), "", 1));
	}

}
