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

public class nextScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nextScreen window = new nextScreen();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setName("Attributes of Base Types");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Button button_2 = new Button("Attribute: Primitive Data Type");
		button_2.setForeground(new Color(255, 255, 255));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBackground(new Color(0, 204, 255));
		button_2.setBounds(30, 36, 164, 36);
		frame.getContentPane().add(button_2);
		
		Button button_3 = new Button("Attribute: 1-D table");
		button_3.setForeground(new Color(255, 255, 255));
		button_3.setBackground(new Color(0, 204, 255));
		button_3.setBounds(237, 36, 158, 36);
		frame.getContentPane().add(button_3);
		
		Button button_4 = new Button("Attribute: 2-D table");
		button_4.setForeground(new Color(255, 255, 255));
		button_4.setBackground(new Color(0, 204, 255));
		button_4.setBounds(30, 106, 164, 31);
		frame.getContentPane().add(button_4);
		
		Button button_5 = new Button("Attribute: ArrayList");
		button_5.setForeground(new Color(255, 255, 255));
		button_5.setBackground(new Color(0, 204, 255));
		button_5.setBounds(237, 106, 158, 31);
		frame.getContentPane().add(button_5);
		
		Button button_6 = new Button("Save this Object");
		button_6.setForeground(new Color(255, 255, 255));
		button_6.setBackground(new Color(0, 204, 255));
		button_6.setBounds(130, 179, 184, 36);
		frame.getContentPane().add(button_6);
	}
}
