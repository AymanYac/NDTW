package ntdw.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.time.Clock;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.oxbow.swingbits.table.filter.TableRowFilterSupport;

import ntdw.common.ArticleTableModel;
import ntdw.common.ButtonColumn;
import ntdw.common.MotionPanel;
import ntdw.model.Article;
import ntdw.service.ArticleMockDataService;
import ntdw.service.DLL;
import ntdw.service.Node;

import java.awt.SystemColor;
import java.awt.event.ActionListener;

public class ItemList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArticleMockDataService service = ArticleMockDataService.getInstance();
	private int height;
	private int width;
	protected Clock clock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemList frame = new ItemList("Elise", null);
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
	 * @param login 
	 * @param clock 
	 */
	public ItemList(String login, Clock clock) {
		this.clock=clock;
		service.stat=false;
		try {
			SynthLookAndFeel laf = new SynthLookAndFeel();
			laf.load(ItemList.class.getResourceAsStream("/ntdw/resources/laf.xml"), ItemList.class);
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
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ItemList.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
		setTitle("TECHNICAL ITEM DESCRIPTION WIZARD");
		setMinimumSize(new Dimension(500, 300));
		
		try {
			initComponents(login);
		} catch (ClassNotFoundException | SQLException e) {
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (ShortBufferException e) {
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (IllegalBlockSizeException e) {
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (BadPaddingException e) {
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StringBuilder sb = new StringBuilder(e.toString());
		    for (StackTraceElement ste : e.getStackTrace()) {
		        sb.append("\n\tat ");
		        sb.append(ste);
		    }
		    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		            JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initComponents(String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0,0,0);
		contentPane = new MotionPanel(this);
		contentPane.setAutoscrolls(true);
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		contentPane.setName("contentPane");
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 4 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setName("pnl_user_name");
		panel.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		JLabel lblSalman = new JLabel("DESCRIPTOR : "+login);
		lblSalman.setName("lbl_user_name");
		panel.add(lblSalman);

		JPanel panel_1 = new JPanel();
		panel_1.setName("pnl_target");
		panel_1.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);

		JLabel lblNewLabel = new JLabel("Week target : ");
		lblNewLabel.setName("lbl_week_target");
		panel_1.add(lblNewLabel);

		JLabel label = new JLabel(service.getWeekTarget(login));
		label.setName("lbl_week_target_value");
		panel_1.add(label);

		JPanel panel_2 = new JPanel();
		panel_2.setName("pnl_completed_week");
		panel_2.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_2, gbc_panel_2);

		JLabel lblCompletedThisWeek = new JLabel("Completed this week :");
		lblCompletedThisWeek.setName("lbl_completed_week");
		panel_2.add(lblCompletedThisWeek);

		JLabel lblNewLabel_2 = new JLabel(service.getCompWeek(login));
		lblNewLabel_2.setName("lbl_completed_week_value");
		panel_2.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setName("pnl_completed_day");
		panel_3.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 0;
		contentPane.add(panel_3, gbc_panel_3);

		JLabel lblCompletedThisDay = new JLabel("Completed this day : ");
		lblCompletedThisDay.setName("lbl_completed_day");
		panel_3.add(lblCompletedThisDay);

		JLabel lblNewLabel_1 = new JLabel(service.getCompDay(login));
		lblNewLabel_1.setName("lbl_completed_day_value");
		panel_3.add(lblNewLabel_1);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(10, 0, 5, 5);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		contentPane.add(panel_5, gbc_panel_5);

		JLabel lblNewLabel_3 = new JLabel("Click anywhere on the table to display selected rows:");
		panel_5.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel();
		panel_5.add(lblNewLabel_4);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(10, 0, 5, 0);
		gbc_panel_4.anchor = GridBagConstraints.EAST;
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 1;
		gbc_panel_4.gridwidth = 2;
		contentPane.add(panel_4, gbc_panel_4);

		JButton btnNewButton_1 = new JButton("CLEAR FILTER");
		btnNewButton_1.setVisible(false);
		panel_4.add(btnNewButton_1);

		JButton button = new JButton("Go Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				service.stat=false;
				dispose();
				Home home = new Home(login, null);
			}
		});
		panel_4.add(button);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 8, 8, 8);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;

		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
		contentPane.add(scrollPane, gbc_scrollPane);
		
		
		Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.height = screenSize.height;
		this. width = screenSize.width;
		setPreferredSize(new Dimension(width, height));

		List<Article> articles = service.getArticles(login,false);
		table = new JTable(new ArticleTableModel(articles));
		TableRowFilterSupport.forTable(table).searchable(true).apply();
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(Double.valueOf(width/18).intValue());
		table.getColumnModel().getColumn(1).setPreferredWidth(Double.valueOf(width/6).intValue());
		table.getColumnModel().getColumn(2).setPreferredWidth(Double.valueOf(width/9).intValue());
		table.getColumnModel().getColumn(3).setPreferredWidth(Double.valueOf(width/9).intValue());
		table.getColumnModel().getColumn(5).setPreferredWidth(Double.valueOf(width/50).intValue());
		table.getColumnModel().getColumn(6).setPreferredWidth(Double.valueOf(width/50).intValue());
		table.getColumnModel().getColumn(7).setPreferredWidth(Double.valueOf(width/50).intValue());
		
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent evnt) {
		    	lblNewLabel_3.setText("Selected rows: ");
		    	lblNewLabel_4.setText(String.valueOf(table.getRowCount()));
		    }});
		/*MouseListener l = new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		    	 lblNewLabel_4.setText(String.valueOf(table.getRowCount()));
		     }
		  };
		
		contentPane.addMouseListener(l);
		*/
		Action articleIdClickAction = new AbstractAction()
		{
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
	        	String selectedAID=(String) table.getModel().getValueAt(modelRow,0);
	        	DLL dll = new DLL();
	        	dll.sethead((String) table.getValueAt(0, 1));
	        	boolean flag = false;
		        for(int i=0;i<table.getRowCount();i++) {
		        	String currentSD = (String) table.getValueAt(i, 1);
		        	String currentAID= (String) table.getValueAt(i, 0);
		        	Node currentNode = dll.head;
		        	while(currentNode.next!=null) {
		        		currentNode = currentNode.next;
		        	}
	        		currentNode.id=currentAID;
	        		currentNode.data=currentSD;
		        	currentNode.next= new Node(currentSD);
		        	currentNode.next.prev=currentNode;
		        	
		        }
		        //dll.printvalues();
		        //System.out.println(dll.prevselected);
		        //System.out.println(selectedAID);
		        //System.out.println(dll.nextselected);
		        dispose();
		        service.stat=false;
		        try {
		        	JOptionPane pane = new JOptionPane(new JLabel(
						    "<html><h2><font color='red'>\"Loading item, please wait\"</font></h2></html>"));
		        	JDialog dialog = pane.createDialog(null, "Chargement");
		        	 dialog.setModal(false);
		        	 dialog.setVisible(false);
		        	 try {
		 				SynthLookAndFeel laf = new SynthLookAndFeel();
		 				laf.load(PartDetail.class.getResourceAsStream("/ntdw/resources/detail_laf.xml"), PartDetail.class);
		 				UIManager.setLookAndFeel(laf);

		 			} catch (Exception ee) {
		 				StringBuilder sb = new StringBuilder(ee.toString());
		 			    for (StackTraceElement ste : ee.getStackTrace()) {
		 			        sb.append("\n\tat ");
		 			        sb.append(ste);
		 			    }
		 			    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
		 			            JOptionPane.ERROR_MESSAGE);
		 			}
					PartDetail partdetail = new PartDetail(dll,selectedAID,login,pane, clock);
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
						| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException
						| BadPaddingException | IOException e1) {
					StringBuilder sb = new StringBuilder(e1.toString());
				    for (StackTraceElement ste : e1.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e1) {
					StringBuilder sb = new StringBuilder(e1.toString());
				    for (StackTraceElement ste : e1.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					StringBuilder sb = new StringBuilder(e1.toString());
				    for (StackTraceElement ste : e1.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				}
		    }
		};
		ButtonColumn articleIdButton = new ButtonColumn(table, articleIdClickAction, 0);
		
		scrollPane.setViewportView(table);
		Dimension d = gbl_contentPane.minimumLayoutSize(contentPane);
		setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(),
				Double.valueOf(400).intValue()));
		
		pack();
		setVisible(true);
	}
}
