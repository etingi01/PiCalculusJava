package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Font;

public class selectFiles {

	private JFrame frmSelectFiles;
	private final JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectFiles window = new selectFiles();
					window.frmSelectFiles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public selectFiles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelectFiles = new JFrame();
		frmSelectFiles.setTitle("Select Files");
		frmSelectFiles.setBounds(100, 100, 531, 300);
		frmSelectFiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectFiles.getContentPane().setLayout(null);
		desktopPane.setBounds(118, 124, 1, 1);
		frmSelectFiles.getContentPane().add(desktopPane);
		
		Button button = new Button("Browse");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(51, 102, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(331, 66, 96, 29);
		frmSelectFiles.getContentPane().add(button);
		
		Button button_1 = new Button("Browse");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(51, 102, 255));
		button_1.setBounds(331, 121, 96, 29);
		frmSelectFiles.getContentPane().add(button_1);
		
		Button button_2 = new Button("Next");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(255, 102, 102));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	}
}
