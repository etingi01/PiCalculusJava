package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class nextScreen {

	private JFrame frmPiCalculus;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nextScreen window = new nextScreen();
					window.frmPiCalculus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public nextScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPiCalculus = new JFrame();
		frmPiCalculus.setTitle("Pi - Calculus Syntax");
		frmPiCalculus.getContentPane().setForeground(new Color(255, 255, 255));
		frmPiCalculus.getContentPane().setName("Attributes of Base Types");
		frmPiCalculus.setBounds(100, 100, 450, 300);
		frmPiCalculus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPiCalculus.getContentPane().setLayout(null);
		
		Button button_4 = new Button("Yes");
		button_4.setForeground(new Color(255, 255, 255));
		button_4.setBackground(new Color(0, 204, 255));
		button_4.setBounds(30, 188, 164, 31);
		frmPiCalculus.getContentPane().add(button_4);
		
		Button button_5 = new Button("No");
		button_5.setForeground(new Color(255, 255, 255));
		button_5.setBackground(new Color(0, 204, 255));
		button_5.setBounds(237, 188, 158, 31);
		frmPiCalculus.getContentPane().add(button_5);
		
		textField = new JTextField();
		textField.setBounds(30, 63, 365, 107);
		frmPiCalculus.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Is this your modelled system in Pi-Calculus Syntax?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(30, 11, 298, 41);
		frmPiCalculus.getContentPane().add(lblNewLabel);
	}
}
