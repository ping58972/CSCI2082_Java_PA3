package edu.century.pa3;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

public class Driver implements ActionListener{

	private JFrame frame;
	private SimpleParser simpleParser;
	private JButton btnNewButton;
	private JTextField textField;
	private JFileChooser fileChooser;
	File file;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
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
	public Driver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Shoose File");
		panel.add(lblNewLabel);
		
		btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Check Error");
		btnNewButton_1.addActionListener(this);
		panel.add(btnNewButton_1);
		
		
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		textField.setText("UnbalanceFoo.java");
		 file = new File("UnbalanceFoo.java");
		simpleParser = new SimpleParser(file);
		textArea.setText("Well Come to Check Error File Program!\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
			if(command.equals("Browse")) {
				 fileChooser = new JFileChooser();
				
				if(fileChooser.showOpenDialog(null)==fileChooser.APPROVE_OPTION) {
					textField.setText(fileChooser.getSelectedFile().getName());
					simpleParser.init(fileChooser.getSelectedFile());
				}
			}
			if(command.equals("Check Error")) {
				textArea.setText("");
				textArea.append(simpleParser.fileToString()+"\n");
				textArea.append("***********************************\nResult: ");
				textArea.append(simpleParser.displayError()+"\n");
						
			}
		
		
	}
	

}
