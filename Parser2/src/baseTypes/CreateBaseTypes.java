package baseTypes;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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
import java.util.ArrayList;
public class CreateBaseTypes {
	
	public static ArrayList<CharacteristicsObj> BaseObj;
	public static int numOfObjects;
	public CreateBaseTypes(){
		BaseObj=new ArrayList();
		numOfObjects=0;
	}
	
	
	public static  void MenuButtons(int numOb){
		JFrame fm = new JFrame ("Attributes για Αντικείμενο: " + BaseObj.get(numOb).nameOb);
	    fm.setLayout( new FlowLayout() );      // set the layout manager
   	//    fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	    JButton simpleType = new JButton("Προσθήκη Attribute Απλού Τύπου"); // construct a JButton
	    JButton ODimT = new JButton("Προσθήκη Μονοδιάστατου Πίνακα");
	    JButton TDimT = new JButton("Προσθήκη Δυσδιάστατου Πίνακα");
	    JButton arType = new JButton("Προσθήκη ArrayList");
	    fm.getContentPane().add(simpleType);
	    fm.getContentPane().add(ODimT);
	    fm.getContentPane().add(TDimT);
	    fm.getContentPane().add(arType);
	    fm.pack();
   	    fm.setSize( 500, 250);     
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
            	"Όνομα του ArrayList: ", nameAttr,
            	"Τύπος του ArrayList: ", doc            	
            };
		
		int option = JOptionPane.showConfirmDialog(null, message, "Δημιουργία Απλού Τύπου Attribute", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();

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
		JTextField rows = new JTextField();
		JTextField cols = new JTextField();

		Object[] message = {
            	"Όνομα του 2D πίνακα: ", nameAttr,
            	"Διαστάσεις - Αρ. Γραμμών: ", rows,
            	"Διαστάσεις - Αρ. Στηλών: ", cols,
            	"Τύπος του 2D πίνακα: ", doc            	
            };
		int option = JOptionPane.showConfirmDialog(null, message, "Δημιουργία Απλού Τύπου Attribute", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();

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
		JTextField rows = new JTextField();

		Object[] message = {
            	"Όνομα του 1D πίνακα: ", nameAttr,
            	"Διαστάσεις - Αρ. Γραμμών: ", rows,
            	"Τύπος του 1D πίνακα: ", doc            	
            };
		int option = JOptionPane.showConfirmDialog(null, message, "Δημιουργία Απλού Τύπου Attribute", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();

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
            	"Όνομα του attribute: ", nameAttr,
            	"Τύπος του attribute: ", doc            	
            };
		
		int option = JOptionPane.showConfirmDialog(null, message, "Δημιουργία Απλού Τύπου Attribute", JOptionPane.OK_CANCEL_OPTION);
	    if (option == JOptionPane.OK_OPTION) {
	    	String typeA = doc.getSelectedItem().toString();
	    	String nameA = nameAttr.getText();

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
	public static void createNewClass() throws IOException{
		System.out.println("irthe edw");
		for (int i=0; i<BaseObj.size(); i++){
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
		fbase.getContentPane().setBackground(new Color(135, 206, 250));
	    fbase.getContentPane().setLayout(null);     // set the layout manager
   	    fbase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
   	    JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Base Types of the System");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 0));
		lblNewJgoodiesTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(10, 0, 231, 29);
		fbase.getContentPane().add(lblNewJgoodiesTitle);
		 //JButton objAdd = new JButton("Προσθήκη Αντικειμένου");
		Button objAdd = new Button("Insert Base Type");
		objAdd.setForeground(new Color(255, 255, 255));
		objAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		objAdd.setActionCommand("");
		objAdd.setBackground(new Color(51, 102, 255));
		objAdd.setBounds(154, 50, 140, 54);

		Button term = new Button("FINISH");
		term.setForeground(new Color(255, 255, 255));
		term.setFont(new Font("Dialog", Font.PLAIN, 12));
		term.setBackground(new Color(255, 102, 102));
		term.setBounds(119, 168, 207, 29);

	    fbase.getContentPane().add(objAdd);
	    fbase.getContentPane().add(term);
	    //
	    fbase.pack();
	    fbase.setVisible( true ); 

	    objAdd.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	String nameNewO = JOptionPane.showInputDialog(null,"Δώστε το ονομα του καινούργιου αντικειμένου:");
            	CharacteristicsObj obj1 = new CharacteristicsObj();
            	numOfObjects++;
            	obj1.nameOb=nameNewO;
            	BaseObj.add(obj1);

            	MenuButtons(numOfObjects-1);
            }
        });  
	    term.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	try {
					createNewClass();
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
