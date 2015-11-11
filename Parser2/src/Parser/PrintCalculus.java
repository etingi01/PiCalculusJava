package Parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class PrintCalculus {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
    	DomParser myParser = new DomParser("6thProgram.txt");
    	myParser.printPi(myParser.getRoot(), "", 1);    
    	System.out.println();
	}

}
