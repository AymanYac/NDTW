package ntdw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ntdw {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ntdw window = new Ntdw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ntdw() {
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
