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
import java.awt.SystemColor;

public class JaPiCa {

	private JFrame frmJapicaPi;
	private JFrame nextScreen;

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
		frmJapicaPi.setBackground(new Color(0, 128, 128));
		nextScreen = new JFrame();
		nextScreen.getContentPane().setBackground(new Color(135, 206, 250));
		frmJapicaPi.getContentPane().setBackground(new Color(100, 149, 237));
		frmJapicaPi.setTitle("JaPiCa - PiCalculus to Java");
		frmJapicaPi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJapicaPi.getContentPane().setLayout(null);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Base Types of the System");
		lblNewJgoodiesTitle.setForeground(new Color(0, 0, 0));
		lblNewJgoodiesTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewJgoodiesTitle.setBounds(10, 0, 231, 29);
		frmJapicaPi.getContentPane().add(lblNewJgoodiesTitle);
		
		Button button = new Button("Insert Base Type");
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.setActionCommand("");
		button.setBackground(new Color(152, 251, 152));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmJapicaPi.getContentPane().remove(textField);
				init2();
			}
		});
		button.setBounds(154, 50, 140, 54);
		frmJapicaPi.getContentPane().add(button);
		
		Button button_1 = new Button("FINISH");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBackground(new Color(255, 102, 102));
		button_1.setBounds(119, 168, 207, 29);
		frmJapicaPi.getContentPane().add(button_1);
	}
	public void init2(){
		
	}
}
