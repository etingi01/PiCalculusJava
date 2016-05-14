package SystemType;

import java.util.Properties;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;       
public class chooseTheFiles {
	public static String SystemName;
	public static String g_atrr;
	
	public chooseTheFiles(){
		SystemName = "";
		g_atrr = "";
	}
	
	public boolean showRequestForFiles(int sysORg) throws FileNotFoundException{
		boolean systemIns = false;
		JFrame fm = new JFrame ("Insert the Files: ");
	    fm.setLocationRelativeTo(null);
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(xmlfilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (sysORg==1)
			fileChooser.setDialogTitle("Select the XML file of the system");
		if(sysORg==0)
			fileChooser.setDialogTitle("Select the XML file of the attributes of the environment G");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fm.setVisible(false);
		int result = fileChooser.showOpenDialog(fm);
		if( result == fileChooser.APPROVE_OPTION){
			File selectedFile = fileChooser.getSelectedFile();
			if (sysORg==1)
				SystemName = selectedFile.getAbsolutePath();
			if (sysORg==0)
				g_atrr = selectedFile.getAbsolutePath();
			
			
			systemIns = true;
			fm.dispose();
		}else{
			System.out.println("no selected file");
			fm.dispose();
		}
			return systemIns;
		}
	
	public static void main(String[] args) throws FileNotFoundException  {
		chooseTheFiles cf = new chooseTheFiles();
		cf.showRequestForFiles(1);
	}
}
