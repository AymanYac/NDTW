package ntdw.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private int height;
	private int width;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(null, null);
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
	 * Create the frame.
	 * @param clock 
	 * @param string 
	 */
	public Home(String login, Clock clock) {
		try {
			SynthLookAndFeel laf = new SynthLookAndFeel();
			laf.load(Home.class.getResourceAsStream("/ntdw/resources/home_laf.xml"), Home.class);
			UIManager.setLookAndFeel(laf);

		} catch (Exception e) {
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
		setTitle("TECHNICAL ITEM DESCRIPTION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setName("contentPane");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.height = screenSize.height * 4/5;
		this.width = screenSize.width * 4/5;
		setPreferredSize(new Dimension(width, height));
		
		JButton btnNewButton = new JButton("DESCRIBE PARTS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ItemList desc = new ItemList(login,clock);
			}
		});
		btnNewButton.setName("btnDescribeParts");
		
		JButton btnReviewParts = new JButton("REVIEW PARTS");
		btnReviewParts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ReviewList desc = new ReviewList(login,clock);
			}
		});
		btnReviewParts.setName("btnReviewParts");
		
		JButton button = new JButton("Reports & Summary");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Report report = new Report(login, clock);
			}
		});
		button.setName("btnDescribeParts");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(512)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReviewParts, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
					.addGap(512))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(480)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
						.addComponent(btnReviewParts, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(243))
		);

		contentPane.setLayout(gl_contentPane);
		Dimension d = gl_contentPane.minimumLayoutSize(contentPane);
		setMinimumSize(new Dimension(Double.valueOf(d.getWidth() * 5 / 6).intValue(),
				Double.valueOf(d.getHeight() * 3 / 4).intValue()));
		//setMinimumSize(gl_contentPane.minimumLayoutSize(contentPane));

		setVisible(true);
		pack();
	}
}
