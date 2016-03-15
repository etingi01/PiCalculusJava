package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Dimension;

public class nextScreen {

	private JFrame frame;
	private JTable table;

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
		frame.getContentPane().setName("Attributes of Base Types");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable(10,2);
		table.setIntercellSpacing(new Dimension(2, 10));

		table.setBounds(51, 39, 229, 102);
		frame.getContentPane().add(table);
	}

}
