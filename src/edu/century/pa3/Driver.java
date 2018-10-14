package edu.century.pa3;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Driver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
		String balanced= "C:\\Users\\Ping\\Documents\\Fall2018\\CSCI2082\\CSCI2082_InclassPractice\\StackLinkL.java";
		String unBalanced = "C:\\Users\\Ping\\Documents\\Fall2018\\CSCI2082\\CSCI2082_InclassPractice\\Unbalance.java";
		SimpleParser sl = new SimpleParser(balanced);
		SimpleParser sU = new SimpleParser(unBalanced);
		System.out.println(sl.fileToString());
		//System.out.println(sl.getChackBalance());
		//sl.getChackBalance();
		System.out.println(sU.fileToString());
		//System.out.println(sU.getChackBalance());
		sU.getChackBalance();
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
	}

}
