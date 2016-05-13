package SystemType;

import java.util.Properties;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;       
public class chooseTheFiles {
	public static String System;
	public static String g_atrr;
	
	public chooseTheFiles(){
		System = "";
		g_atrr = "";
	}
	
	public void showRequestForFiles() throws FileNotFoundException{
		JFrame fm = new JFrame ("Insert the Files: ");
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(fm);

		    Scanner reader = new Scanner(fc.getSelectedFile());
			fm.setVisible(true);

		}
	
	public static void main(String[] args) throws FileNotFoundException  {
		chooseTheFiles cf = new chooseTheFiles();
		cf.showRequestForFiles();
	}
}
