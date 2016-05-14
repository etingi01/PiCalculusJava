package baseTypes;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import GUI.JaPiCa;
import SystemType.GUIselFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
public class CreateBaseTypes {
	
	public static ArrayList<CharacteristicsObj> BaseObj;
	public static ArrayList<String> basetypesN;

	public static int numOfObjects;
	public CreateBaseTypes(){
		BaseObj=new ArrayList();
		numOfObjects=0;
		basetypesN = new ArrayList<String>();

	}
	
	
	public static  void MenuButtons(int numOb){
		JFrame fm = new JFrame ("Insert the attributes of the Object: " + BaseObj.get(numOb).nameOb);
	    fm.setLayout( null );      // set the layout manager
	    fm.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	BaseObj.remove(numOb);
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
	    
		Button simpleType = new Button("Attribute: Primitive Data Type");
		simpleType.setForeground(new Color(255, 255, 255));
		simpleType.setBackground(new Color(51, 102, 255));
		simpleType.setBounds(30, 36, 164, 36);
		fm.getContentPane().add(simpleType);

		Button ODimT = new Button("Attribute: 1-D table");
		ODimT.setForeground(new Color(255, 255, 255));
		ODimT.setBackground(new Color(51, 102, 255));
		ODimT.setBounds(237, 36, 158, 36);
		fm.getContentPane().add(ODimT);

		Button TDimT = new Button("Attribute: 2-D table");
		TDimT.setForeground(new Color(255, 255, 255));
		TDimT.setBackground(new Color(51, 102, 255));
		TDimT.setBounds(30, 106, 164, 31);
		fm.getContentPane().add(TDimT);
		
		Button arType = new Button("Attribute: ArrayList");
		arType.setForeground(new Color(255, 255, 255));
		arType.setBackground(new Color(51, 102, 255));
		arType.setBounds(237, 106, 158, 31);
		fm.getContentPane().add(arType);
		
		Button finish = new Button("Save this Object");
		finish.setForeground(new Color(255, 255, 255));
		finish.setBackground(new Color(255, 102, 102));
		finish.setBounds(130, 179, 184, 36);
		fm.getContentPane().add(finish);
	    fm.pack();
   	    fm.setSize( 450, 300);  
	    fm.setLocationRelativeTo(null);

   	    fm.setVisible( true ); 
   	
   	 simpleType.addActionListener(new ActionListener() {
	    	
            public void actionPerformed(ActionEvent e)
            {
            	chooseSimple(numOb);
            	
            }
        });  
   	ODimT.addActionListener(new ActionListener() {
	    	
            public void actionPerformed(ActionEvent e)
            {
            	makeOTable(numOb);
            }
        });  
   	 
   	TDimT.addActionListener(new ActionListener() {
	    	
            public void actionPerformed(ActionEvent e)
            {
            	makeTTable(numOb);
            }
        });  
   	finish.addActionListener(new ActionListener() {
    	
        public void actionPerformed(ActionEvent e)
        {
        	fm.dispose();
        }
    });  
   	
   	
   	
   	arType.addActionListener(new ActionListener() {
    	
        public void actionPerformed(ActionEvent e)
        {
        	makeArrList(numOb);
        }
    });  
   	
	}
	
	public static void makeArrList(int numOb){
		JComboBox doc = new JComboBox();
		doc.addItem("int");
		doc.addItem("String");
		doc.addItem("boolean");
		doc.addItem("float");
		doc.addItem("double");
		doc.addItem("char");
		doc.addItem("long");
		doc.addItem("short");
		JTextField nameAttr = new JTextField();
		Object[] message = {
            	"Name of ArrayList: ", nameAttr,
            	"Type of ArrayList: ", doc            	
            };
		
		int option = JOptionPane.showConfirmDialog(null, message, "ArrayList", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();
	    	while(nameA.equals("")&&(option == JOptionPane.OK_OPTION)){
	    		option = JOptionPane.showConfirmDialog(null, message, "ArrayList", JOptionPane.OK_CANCEL_OPTION);
	    		typeA = doc.getSelectedItem().toString();
		    	nameA = nameAttr.getText();
	    	}
		    if (option == JOptionPane.OK_OPTION) {
		    	CharacteristicsObj thisOb = BaseObj.get(numOb);
		    	ObjChar newAtt = new ObjChar();
		    	newAtt.eidosOfAtt=3;
		    	newAtt.typeOfAtt=typeA;
		    	newAtt.nameOfAtt=nameA;
		    	newAtt.DimCol=0;
		    	newAtt.DimRow=0;
		    	thisOb.atts.add(newAtt);
		    	BaseObj.set(numOb, thisOb);
		    }
	    }

	}
	public static void makeTTable(int numOb){
		JComboBox doc = new JComboBox();
		doc.addItem("int");
		doc.addItem("String");
		doc.addItem("boolean");
		doc.addItem("float");
		doc.addItem("double");
		doc.addItem("char");
		doc.addItem("long");
		doc.addItem("short");
		JTextField nameAttr = new JTextField();
		//JTextField rows = new JTextField();
		//JTextField cols = new JTextField();
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l); //Optional

		JFormattedTextField rows = new JFormattedTextField(numberFormatter);
		JFormattedTextField cols = new JFormattedTextField(numberFormatter);
		
		Object[] message = {
            	"Name of 2D table: ", nameAttr,
            	"Number of Rows: ", rows,
            	"Number of Columns: ", cols,
            	"Type of 2D table: ", doc            	
            };
		int option = JOptionPane.showConfirmDialog(null, message, "2D TABLE", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();
	    	while((nameA.equals("")||(cols.getText().equals(""))||(rows.getText().equals("")))&&(option == JOptionPane.OK_OPTION)){
	    		option = JOptionPane.showConfirmDialog(null, message, "2D TABLE", JOptionPane.OK_CANCEL_OPTION);
	    		typeA = doc.getSelectedItem().toString();
		    	nameA = nameAttr.getText();	    	
	    	}
		    if (option == JOptionPane.OK_OPTION) {
		    	CharacteristicsObj thisOb = BaseObj.get(numOb);
		    	ObjChar newAtt = new ObjChar();
		    	newAtt.eidosOfAtt=2;
		    	newAtt.typeOfAtt=typeA;
		    	newAtt.nameOfAtt=nameA;
		    	newAtt.DimCol= Integer.parseInt(cols.getText());
		    	newAtt.DimRow=Integer.parseInt(rows.getText());;
		    	thisOb.atts.add(newAtt);
		    	BaseObj.set(numOb, thisOb);
		    }
	    }


	}
	public static void makeOTable(int numOb){
		JComboBox doc = new JComboBox();
		doc.addItem("int");
		doc.addItem("String");
		doc.addItem("boolean");
		doc.addItem("float");
		doc.addItem("double");
		doc.addItem("char");
		doc.addItem("long");
		doc.addItem("short");
		JTextField nameAttr = new JTextField();
	//	JTextField rows = new JTextField();

		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l); //Optional

		JFormattedTextField rows = new JFormattedTextField(numberFormatter);
		
		
		Object[] message = {
            	"Name of 1D table: ", nameAttr,
            	"Number of Rows: ", rows,
            	"Type of 1D table: ", doc            	
            };
		int option = JOptionPane.showConfirmDialog(null, message, "1D TABLE", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();
	    	while((nameA.equals("")||(rows.getText().equals("")))&&(option == JOptionPane.OK_OPTION)){
	    		option = JOptionPane.showConfirmDialog(null, message, "1D TABLE", JOptionPane.OK_CANCEL_OPTION);
		    	typeA = doc.getSelectedItem().toString();
		    	nameA = nameAttr.getText();	    	
	    	}
		    if (option == JOptionPane.OK_OPTION) {
		    	CharacteristicsObj thisOb = BaseObj.get(numOb);
		    	ObjChar newAtt = new ObjChar();
		    	newAtt.eidosOfAtt=1;
		    	newAtt.typeOfAtt=typeA;
		    	newAtt.nameOfAtt=nameA;
		    	newAtt.DimCol= 0;
		    	newAtt.DimRow=Integer.parseInt(rows.getText());;
		    	thisOb.atts.add(newAtt);
		    	BaseObj.set(numOb, thisOb);
		    }
	    }



	}
	
	public static void chooseSimple(int numOb){
		JComboBox doc = new JComboBox();
		doc.addItem("int");
		doc.addItem("String");
		doc.addItem("boolean");
		doc.addItem("float");
		doc.addItem("double");
		doc.addItem("char");
		doc.addItem("long");
		doc.addItem("short");
		JTextField nameAttr = new JTextField();
		Object[] message = {
            	"Name of attribute variable: ", nameAttr,
            	"Type of attribute variable: ", doc            	
            };
		
		int option = JOptionPane.showConfirmDialog(null, message, "Primitive Data Type", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();
	    	while(nameA.equals("")&&(option == JOptionPane.OK_OPTION)){
	    		option = JOptionPane.showConfirmDialog(null, message, "Primitive Data Type", JOptionPane.OK_CANCEL_OPTION);
		    	typeA = doc.getSelectedItem().toString();
		    	nameA = nameAttr.getText();
	    	}
		    if (option == JOptionPane.OK_OPTION) {
		    	CharacteristicsObj thisOb = BaseObj.get(numOb);	    	
		    	ObjChar newAtt = new ObjChar();
		    	newAtt.eidosOfAtt=0;
		    	newAtt.typeOfAtt=typeA;
		    	newAtt.nameOfAtt=nameA;
		    	newAtt.DimCol=0;
		    	newAtt.DimRow=0;
		    	thisOb.atts.add(newAtt);
		    	BaseObj.set(numOb, thisOb);
		    }
	    }
	}
	public static void createNewClass() throws IOException{
		System.out.println("irthe edw");
		for (int i=0; i<BaseObj.size(); i++){
			basetypesN.add(BaseObj.get(i).nameOb);
			String fileName= BaseObj.get(i).nameOb + ".java";
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");


			new File( fileName).createNewFile();


			writer.println("import java.util.ArrayList;");
			writer.println("public class " + BaseObj.get(i).nameOb + " {");
			for (int j=0; j<BaseObj.get(i).atts.size(); j++){
				//System.out.println(BaseObj.get(i).atts.get(j).nameOfAtt);
				ObjChar temp = BaseObj.get(i).atts.get(j);
				switch (temp.eidosOfAtt){
				case 0: 
					writer.println("private " + temp.typeOfAtt + " " + temp.nameOfAtt + ";");
					break;
				case 1:
					writer.println("private " + temp.typeOfAtt+"[]" + " "+ temp.nameOfAtt + " = new " + temp.typeOfAtt + "[" + temp.DimRow + "];");
					break;
				case 2:
					writer.println("private " + temp.typeOfAtt + "[][] " + temp.nameOfAtt + " = new " + temp.typeOfAtt + "[" + temp.DimRow + "][" + temp.DimCol + "];" );
					break;
				case 3: 
					writer.println("public ArrayList<"+ temp.typeOfAtt + "> " + temp.nameOfAtt + ";");
					break;
				}				
				
			}
			writer.println("public " + BaseObj.get(i).nameOb + "(){");
			for (int j=0; j<BaseObj.get(i).atts.size(); j++){
				ObjChar temp = BaseObj.get(i).atts.get(j);	

				if (temp.eidosOfAtt==3){
					writer.println(temp.nameOfAtt + "= new ArrayList();");
				}
			}
			writer.println("}");
			
			for (int j=0; j<BaseObj.get(i).atts.size(); j++){
				ObjChar temp = BaseObj.get(i).atts.get(j);
				switch (temp.eidosOfAtt){
				case 0: 
					writer.println("public " + temp.typeOfAtt+ " get" + temp.nameOfAtt +"() {" );
					writer.println("return " + temp.nameOfAtt + ";");
					writer.println("}");
					

					writer.println("public void set" + temp.nameOfAtt + "(" + temp.typeOfAtt+ " parameter ) {" );
					writer.println( temp.nameOfAtt + " = parameter; " );
					writer.println("}");
					break;
				case 1:
					writer.println("public " + temp.typeOfAtt+ "[] get" + temp.nameOfAtt +"() {" );
					writer.println("return " + temp.nameOfAtt + ";");
					writer.println("}");
					

					writer.println("public void set" + temp.nameOfAtt + "(" + temp.typeOfAtt+ " parameter[] ) {" );
					writer.println("for (int n=0; n<" + temp.nameOfAtt+ ".length; n++){" );
					writer.println( temp.nameOfAtt + "[n] = parameter[n]; " );
					writer.println("}");
					
					writer.println("}");
					break;
					
				case 2:
					writer.println("public " + temp.typeOfAtt+ "[][] get" + temp.nameOfAtt +"() {" );
					writer.println("return " + temp.nameOfAtt + ";");
					writer.println("}");
					

					writer.println("public void set" + temp.nameOfAtt + "(" + temp.typeOfAtt+ " parameter[][] ) {" );
					writer.println("for (int n=0; n<" + temp.nameOfAtt+ ".length; n++){" );
					writer.println("for (int n1=0; n1<" + temp.nameOfAtt+ "[0].length; n1++){");
					writer.println( temp.nameOfAtt + "[n][n1] = parameter[n][n1]; " );
					writer.println("}");
					writer.println("}");
					writer.println("}");					
					break;
				}
			}
			writer.println("}");
			writer.close();
			System.out.println(BaseObj.get(i).nameOb);
		}
	}
	
	
	public static void AddBaseObject(){

		JFrame fbase = new JFrame();
	    fbase.setLayout(null);     // set the layout manager

		fbase.getContentPane().setBackground(new Color(240, 240, 240));
		fbase.setTitle("JaPiCa - PiCalculus to Java");
   	    fbase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblNewJgoodiesTitle = new JLabel();
		lblNewJgoodiesTitle.setText("Base Types of the System");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 0));
		lblNewJgoodiesTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(10, 0, 231, 29);
		 //JButton objAdd = new JButton("Προσθήκη Αντικειμένου");
		Button objAdd = new Button("Insert Base Type");
		objAdd.setForeground(new Color(255, 255, 255));
		objAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		objAdd.setActionCommand("");
		objAdd.setBackground(new Color(51, 102, 255));
		objAdd.setBounds(154, 50, 140, 54);

		Button term = new Button("Next");
		term.setForeground(new Color(255, 255, 255));
		term.setFont(new Font("Dialog", Font.PLAIN, 12));
		term.setBackground(new Color(255, 102, 102));
		term.setBounds(119, 168, 207, 29);
		//term.addActionListener();
		fbase.getContentPane().add(lblNewJgoodiesTitle);

	    fbase.getContentPane().add(objAdd);
	    fbase.getContentPane().add(term);
	    
	    fbase.pack();
	    fbase.setSize(450, 250);
   	    fbase.setLocationRelativeTo(null);
	    fbase.setVisible( true ); 

	    objAdd.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	String nameNewO = JOptionPane.showInputDialog(null,"Give the name of the new object:");
            	if(nameNewO!=null){
            	while(nameNewO.equals("")){
                	nameNewO = JOptionPane.showInputDialog(null,"Give the name of the new object:");
                	if(nameNewO==null)
                		break;
            	}
            	if(nameNewO!=null){
	            	CharacteristicsObj obj1 = new CharacteristicsObj();
	            	numOfObjects++;
	            	obj1.nameOb=nameNewO;
	            	BaseObj.add(obj1);
	
	            	MenuButtons(numOfObjects-1);
            	}
            	}
            }
        });  
	    term.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	try {
					createNewClass();
					fbase.dispose();
					GUIselFiles showF = new GUIselFiles(basetypesN);
					showF.askFiles();
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });  
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateBaseTypes dimiourgia = new CreateBaseTypes();
		
		dimiourgia.AddBaseObject();
		
	
	}

}
