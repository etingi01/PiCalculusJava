package SystemType;
/*	public boolean checkForSame(Node sib,  String ch, String value, String agent){
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
}*/
/*	public void findParP(Node node){
NodeList children = node.getChildNodes();  
Node toRet=node;		
if (!(node.getNodeName().equals("parP"))){
	
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
	this.ParPNode = node;
	return;
}		
}*/
/*public ArrayList<Node> findAllIns(Node process, ArrayList<Node> nodesIn){
NodeList children = process.getChildNodes();  

 if ((process.getNodeName().equals("in"))||(process.getNodeName().equals("inExternal"))){
	 nodesIn.add(process);
 }
for (int temp = 0; temp < children.getLength(); temp++) {
    		if(children.item(temp).getChildNodes().getLength()>1){
    			return findAllIns(children.item(temp), nodesIn);
    			
    		}
 }	
return nodesIn;
}*/
/*public ArrayList<Node> findAllObjs(Node process, ArrayList<Node> nodesProc){
NodeList children = process.getChildNodes();  

 if (process.getNodeName().equals("in")||process.getNodeName().equals("out")||process.getNodeName().equals("inExternal")||process.getNodeName().equals("outExternal")){
	 nodesProc.add(process);
 }
for (int temp = 0; temp < children.getLength(); temp++) {
    		if(children.item(temp).getChildNodes().getLength()>1){
    			return findAllObjs(children.item(temp), nodesProc);
    		}
 }	
return nodesProc;
}*/

/*public void checkDups(Node node){
NodeList children = node.getChildNodes();    	
String ch="";
if (node.equals(null)){
	return;
}
for (int temp = 0; temp < children.getLength(); temp++) {
	if (!(children.item(temp).getNodeName().equals("#text"))){
		ArrayList<Node> inObjs = new ArrayList();
		inObjs = findAllIns(children.item(temp), inObjs);
		ArrayList<Node> otherObjs = new ArrayList();

		for(int t = temp+1; t<children.getLength(); t++){
        	if (!(children.item(t).getNodeName().equals("#text"))){
        		otherObjs = findAllObjs(children.item(t), otherObjs);
        	}
		}
		for(int i=0; i<inObjs.size(); i++){
			boolean sameexist=false;
			String value="";
			NodeList ino = inObjs.get(i).getChildNodes();
			for(int p=0; p<ino.getLength(); p++){
				if (ino.item(p).getNodeName().equals("obj")){
					value=ino.item(p).getTextContent();
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
					sameexist=true;
					maketheChanges(value,children.item(temp), temp );
				}

			}
			if(!sameexist){
				inObjs.remove(i);
			}

			
		}
	}    	
	
}

}

public void maketheChanges(String ref, Node whatChange, int process){
NodeList children = whatChange.getChildNodes();  
 if (whatChange.getNodeName().equals("subj")||whatChange.getNodeName().equals("obj")){
	 if(whatChange.getTextContent().equals(ref)){
		String newV = whatChange.getTextContent()+process;
		whatChange.setTextContent(newV);				
	}
 }
 if (children.getLength()>1){
for (int temp = 0; temp < children.getLength(); temp++) {
		if (!(children.item(temp).getNodeName().equals("#text"))){
    			maketheChanges(ref, children.item(temp), process);
    		}
 }	
 }		
}*/

public class EraseTheSimilars {

}
