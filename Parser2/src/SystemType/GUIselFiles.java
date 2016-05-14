package SystemType;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import baseTypes.CreateBaseTypes;

public class GUIselFiles {
	public static String systemfile = "";
	public static String gattrfile = "";
	public static ArrayList<String> basetypesN = new ArrayList<String>();
	public static boolean systemGiven = false;
	public static boolean gattrGiven = false;

	public GUIselFiles(ArrayList<String> base){
		this.basetypesN.addAll(base);
	}
	
	public void ask1File(int type){
		JFrame frmSelectFiles = new JFrame();
		frmSelectFiles.setTitle("Select File");
		frmSelectFiles.setBounds(100, 100, 528, 300);
		frmSelectFiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFiles.getContentPane().setLayout(null);
		/*desktopPane.setBounds(118, 124, 1, 1);
		frmSelectFiles.getContentPane().add(desktopPane);*/
		
		Button button = new Button("Browse");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 102, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type ==1){
					chooseTheFiles insSyst = new chooseTheFiles();
					try {
						systemGiven = insSyst.showRequestForFiles(1);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(systemGiven){
						systemfile = insSyst.SystemName;
					}

				}
				else
					if(type == 0){
						chooseTheFiles insSyst = new chooseTheFiles();
						try {
							gattrGiven = insSyst.showRequestForFiles(0);
							if(gattrGiven){
								gattrfile = insSyst.g_atrr;
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}				
					}
			}
		});
		button.setBounds(330, 118, 96, 29);
		frmSelectFiles.getContentPane().add(button);
		
		Button button_2 = new Button("Next");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(255, 102, 102));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSelectFiles.dispose();
				if(!systemGiven && (type == 1)){
					ask1File(1);
					return;
				}
				if (!gattrGiven && (type == 0)){
					ask1File(0);
					return;
				}
				try {
					convStage();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		});
		button_2.setBounds(426, 222, 76, 29);
		frmSelectFiles.getContentPane().add(button_2);
		
		Label label = new Label("Select the necessary XML files");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 10, 188, 29);
		frmSelectFiles.getContentPane().add(label);
		
		String miss = "";
		if (type == 1)
			miss = " modelled system";
		if (type == 0)
			miss = " attributes of the G envieronment";

		Label label2 = new Label("You have not selected the XML file with the" + miss);
		label2.setFont(new Font("Dialog", Font.PLAIN, 11));
		//label2.setAlignment(Label.CENTER);
		label2.setBounds(95, 40, 400, 67);
		frmSelectFiles.getContentPane().add(label2);
		
		
		
		
		
        /*JTextArea textArea = new JTextArea("You have not selected the XML file with the modelled system");
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setLineWrap(true);
		textArea.setBounds(140, 45, 251, 67);
		frmSelectFiles.getContentPane().add(textArea);*/
		
		JLabel lblNewLabel = new JLabel("Insert the XML file");
		lblNewLabel.setBounds(20, 120, 138, 27);
		frmSelectFiles.getContentPane().add(lblNewLabel);
		frmSelectFiles.pack();
		frmSelectFiles.setSize(530, 300);
	    frmSelectFiles.setLocationRelativeTo(null);
	    frmSelectFiles.setVisible( true ); 

	}
	
	public void convStage() throws ParserConfigurationException, SAXException, IOException{		
		ConvertSystem myParser = new ConvertSystem(systemfile);
		ConvertSystem gattr = new ConvertSystem(gattrfile);
		//myParser.packagename = JOptionPane.showInputDialog(null, "Give the Package of the program");
		System.out.println(gattr.rootNode.getNodeName());
		HashMap<String, String> g_attr = gattr.takeGattr(gattr.rootNode);
		System.out.println(g_attr);
		boolean foundGS=false;
		for(int b=0; b<basetypesN.size(); b++){
			System.out.println(basetypesN.get(b));
			myParser.baseTypes.add(basetypesN.get(b));
		}
		ArrayList<String> inoutch = new ArrayList<String>();
		inoutch.addAll(myParser.decideGlobals(myParser.rootNode, inoutch));
		myParser.globalChannels.putAll(g_attr);
		for(int i=0; i<inoutch.size(); i++){
			if(!g_attr.containsKey(inoutch.get(i)))
			myParser.globalChannels.put(inoutch.get(i), "unknown");
		}
		System.out.println(myParser.globalChannels);
		myParser.decideItem(myParser.rootNode,foundGS);
		
	}
	
	public void askFiles(){
		JFrame frmSelectFiles = new JFrame();
		frmSelectFiles.setTitle("Select Files");
		frmSelectFiles.setBounds(100, 100, 528, 300);
		frmSelectFiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFiles.getContentPane().setLayout(null);
		
		Button button = new Button("Browse");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 102, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseTheFiles insSyst = new chooseTheFiles();
				try {
					systemGiven = insSyst.showRequestForFiles(1);
					if(systemGiven){
						System.out.println("Sistima " + insSyst.SystemName);
						systemfile = insSyst.SystemName;
						System.out.println("Sistima " + systemfile);

					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		button.setBounds(331, 66, 96, 29);
		frmSelectFiles.getContentPane().add(button);
		
		Button button_1 = new Button("Browse");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(331, 121, 96, 29);
		frmSelectFiles.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseTheFiles insSyst = new chooseTheFiles();
				try {
					gattrGiven = insSyst.showRequestForFiles(0);
					if(gattrGiven){
						System.out.println("Perivallon G " + insSyst.g_atrr);
						gattrfile = insSyst.g_atrr;
						System.out.println("Perivallon G " + gattrfile);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		Button button_2 = new Button("Next");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(255, 102, 102));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSelectFiles.dispose();
				if(!systemGiven && !gattrGiven){
						askFiles();
						return;
					
				}

				if(!systemGiven){
					ask1File(1);
					return;
				}
				if (!gattrGiven){
					ask1File(0);
					return;
				}
				try {
					convStage();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		});
		button_2.setBounds(426, 222, 76, 29);
		frmSelectFiles.getContentPane().add(button_2);
		
		Label label = new Label("Select the necessary XML files");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 10, 188, 29);
		frmSelectFiles.getContentPane().add(label);
		
		Label label_1 = new Label("XML file with the modelled system");
		label_1.setBounds(73, 66, 188, 29);
		frmSelectFiles.getContentPane().add(label_1);
		
		Label label_2 = new Label("XML file with attributes of G environment");
		label_2.setBounds(73, 124, 210, 26);
		frmSelectFiles.getContentPane().add(label_2);
		
		frmSelectFiles.pack();
		frmSelectFiles.setSize(530, 300);
	    frmSelectFiles.setLocationRelativeTo(null);
	    frmSelectFiles.setVisible( true ); 

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GUIselFiles re = new GUIselFiles();
		//re.askFiles();
	}

}
