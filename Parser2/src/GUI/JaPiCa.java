package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.DropMode;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import java.awt.Rectangle;
import java.awt.Point;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JaPiCa {

	private JFrame frmJapicaPi;
	private JFrame nextScreen;

	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JaPiCa window = new JaPiCa();
					window.frmJapicaPi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JaPiCa() {
		initialize();
		init2();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frmJapicaPi = new JFrame();
		nextScreen = new JFrame();
		nextScreen.getContentPane().setBackground(new Color(135, 206, 250));
		frmJapicaPi.getContentPane().setBackground(new Color(135, 206, 250));
		frmJapicaPi.setTitle("JaPiCa - PiCalculus to Java");
		frmJapicaPi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJapicaPi.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setLocation(new Point(173, 121));
		textField.setPreferredSize(new Dimension(20, 10));
		textField.setSize(new Dimension(105, 24));
		textField.setMinimumSize(new Dimension(20, 10));
		textField.setMaximumSize(new Dimension(20, 10));
		frmJapicaPi.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Base Types of the System");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 0));
		lblNewJgoodiesTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(10, 0, 231, 29);
		frmJapicaPi.getContentPane().add(lblNewJgoodiesTitle);
		
		JLabel lblNumberOfDifferent = DefaultComponentFactory.getInstance().createLabel("Number of different base types:");
		lblNumberOfDifferent.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNumberOfDifferent.setBounds(138, 87, 186, 23);
		frmJapicaPi.getContentPane().add(lblNumberOfDifferent);
		
		Button button = new Button("Next>");
		button.setBackground(new Color(204, 204, 204));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmJapicaPi.getContentPane().remove(textField);
				init2();
			}
		});
		button.setBounds(324, 193, 70, 22);
		frmJapicaPi.getContentPane().add(button);
	}
	public void init2(){
		
	}
}
