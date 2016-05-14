package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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

public class askSecond {

	private JFrame frmSelectFiles;
	private final JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					askSecond window = new askSecond();
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
	public askSecond() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelectFiles = new JFrame();
		frmSelectFiles.setTitle("Select Files");
		frmSelectFiles.setBounds(100, 100, 528, 300);
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
		button.setBounds(330, 118, 96, 29);
		frmSelectFiles.getContentPane().add(button);
		
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
		
		
        JTextArea textArea = new JTextArea("You have not selected the XML file with the modelled system");
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setLineWrap(true);
		textArea.setBounds(140, 45, 251, 67);
		frmSelectFiles.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Please insert an XML file");
		lblNewLabel.setBounds(20, 120, 138, 27);
		frmSelectFiles.getContentPane().add(lblNewLabel);
		//Label label_1 = new Label("You have not selected the XML file with the modelled system");
		//label_1.setBounds(22, 66, 251, 62);
		//frmSelectFiles.getContentPane().add(label_1);
	}
}
