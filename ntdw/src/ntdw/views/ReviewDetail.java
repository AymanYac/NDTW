package ntdw.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.synth.SynthLookAndFeel;

import ntdw.common.CharacType;
import ntdw.common.CharacValueRenderer;
import ntdw.model.CharacValue;
import ntdw.service.CharacMockDataService;
import ntdw.service.DLL;
import ntdw.service.Tools;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;

public class ReviewDetail extends JFrame {

	private JPanel contentPane = new JPanel();
	private int height;
	private int width;
	
	private JTextField textField = new JTextField();
	private JTextField textField_1 = new JTextField();
	private JTextField textField_2 = new JTextField();
	private JTextField textField_3 = new JTextField();
	private JTextField textField_4 = new JTextField();
	private JTextField textField_5 = new JTextField();
	private JTextArea textArea = new JTextArea();
	private JTextArea textArea_1 = new JTextArea();
	private JTextArea textArea_2 = new JTextArea();
	private JTextArea textArea_3 = new JTextArea();
	private JTextArea textArea_4 = new JTextArea();
	
	public GridBagLayout gbl_contentPane = new GridBagLayout(); 
	public JPanel pnl_controlflex = new JPanel(); 
	public GridBagConstraints gbc_pnl_controlflex = new GridBagConstraints(); 
	public JLabel lblNewLabel = new JLabel(); 
	public JPanel panelCenter = new JPanel(); 
	public GridBagConstraints gbc_panelCenter = new GridBagConstraints(); 
	public JLabel lblVal = new JLabel(); 
	public JPanel panelRigth = new JPanel(); 
	public GridBagConstraints gbc_panelRigth = new GridBagConstraints(); 
	public JLabel lblFlexor = new JLabel(); 
	public JPanel pnlDescription = new JPanel(); 
	public GridBagConstraints gbc_pnlDescription = new GridBagConstraints(); 
	public GridBagLayout gbl_pnlDescription = new GridBagLayout(); 
	public JPanel panel = new JPanel(); 
	public GridBagConstraints gbc_panel = new GridBagConstraints(); 
	//public GroupLayout gl_panel = new GroupLayout(); 
	public JPanel pnlClassification = new JPanel(); 
	public GridBagConstraints gbc_pnlClassification = new GridBagConstraints(); 
	public GridBagLayout gbl_pnlClassification = new GridBagLayout(); 
	public JLabel lblNewLabel_4 = new JLabel(); 
	public GridBagConstraints gbc_textField = new GridBagConstraints(); 
	public GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints(); 
	public GridBagConstraints gbc_textField_1 = new GridBagConstraints(); 
	public GridBagConstraints gbc_textField_2 = new GridBagConstraints(); 
	public GridBagConstraints gbc_textField_3  = new GridBagConstraints();

	

	CharacMockDataService characService = CharacMockDataService.getInstance();
	CharacValueRenderer characValueRenderer = new CharacValueRenderer();
	private JTextField textField_6 = new JTextField();
	protected boolean click=false;
	private String originalcid;
	private String originalDomain;
	private String originalGroup;
	private String originalfamily;
	private String originalclass;
	private List<CharacValue> modelLeft = new LinkedList<CharacValue>();
	private List<CharacValue> modelRight = new LinkedList<CharacValue>();
	private JPanel pnlCharacLeft = new JPanel();
	private JPanel pnlCharacRigth= new JPanel();
	private HashMap<String,String> currentFamily = new HashMap<String,String>();
	protected boolean viewbutton = true;
	private ResultSet rs;
	private PreparedStatement st;
	private Connection conn;
	private MouseAdapter classApply;
	private JLabel lblNewLabel_6 = new JLabel();
	private JLabel lblNewLabel_7 = new JLabel();
	private JPanel pnlCharValues = new JPanel();
	private JPanel pnlComment = new JPanel();
	private JLabel lblQuestion = new JLabel();
	private JLabel lblNewLabel_11 = new JLabel();
	private ResultSet rs1;
	private PreparedStatement st1;
	private Connection conn1;
	protected String url;
	protected Properties props = new Properties();
	private JButton btnApplyClassification = new JButton();
	private JButton btnApplyCancel = new JButton();
	private JLabel lblNewLabel_5 = new JLabel();

	private JButton btnArrowLeft = new JButton();
	private JButton btnArrowRigth = new JButton();
	private JLabel lblNewLabel_1 = new JLabel();
	private JLabel lblNewLabel_2 = new JLabel();
	private JLabel lblNewLabel_3 = new JLabel();
	private GridBagConstraints gbc_btnApplyClassification = new GridBagConstraints();
	private GridBagConstraints gbc_btnApplyCancel = new GridBagConstraints();
	private GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	private GridBagConstraints gbc_textField_4 = new GridBagConstraints();
	private GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
	private GridBagConstraints gbc_textField_5 = new GridBagConstraints();
	private GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
	private GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
	private GridBagConstraints gbc_pnlCharValues = new GridBagConstraints();
	private GridBagLayout gbl_pnlCharValues = new GridBagLayout();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panelCharVals = new JPanel();
	private JPanel panel_1 = new JPanel();
	private GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	private GridBagLayout gbl_panel_1 = new GridBagLayout();
	private JLabel lblNewLabel_8 = new JLabel();
	private GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
	private JLabel lblNewLabel_9 = new JLabel();
	private GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
	private JPanel panel_2 = new JPanel();
	private GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	private GridBagLayout gbl_panel_2 = new GridBagLayout();
	private JLabel lblNewLabel_10 = new JLabel();
	private GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
	private JLabel lblNewLabel_12 = new JLabel();
	private GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
	private GridBagConstraints gbc_pnlCharacLeft = new GridBagConstraints();
	private GridBagLayout gbl_pnlCharacLeft = new GridBagLayout();
	private GridBagConstraints gbc_pnlCharacRigth = new GridBagConstraints();
	private GridBagLayout gbl_pnlCharacRigth = new GridBagLayout();
	private GridBagConstraints gbc_pnlComment = new GridBagConstraints();
	private GridBagLayout gbl_pnlComment = new GridBagLayout();
	private GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
	private GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
	private GridBagConstraints gbc_textField_6 = new GridBagConstraints();
	private GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
	private GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	private GridBagLayout gbl_panelCharVals = new GridBagLayout();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewDetail frame = new ReviewDetail(null,null,null,null,null);
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
	 * @param selectedAID 
	 * @param dll 
	 * @param login 
	 * @param pane 
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws ShortBufferException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public ReviewDetail(DLL dll, String selectedAID, String login, JOptionPane pane,Clock clock) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException, SQLException {
		if(selectedAID!=null) {
			try {
				SynthLookAndFeel laf = new SynthLookAndFeel();
				laf.load(PartDetail.class.getResourceAsStream("/ntdw/resources/detail_laf.xml"), PartDetail.class);
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
			Date start = new Date();
			String ip = Tools.load_ip();
			Class.forName("org.postgresql.Driver");
			url = "jdbc:postgresql://"+ip+":5432/northwind";
			props.setProperty("user","postgres");
			props.setProperty("password","Neonec");
			props.setProperty("loginTimeout", "20");
			props.setProperty("connectTimeout", "0");
			props.setProperty("socketTimeout", "0");
			//props.setProperty("ssl","true");
			
			load_item_data(selectedAID/*conn, url, props, st, selectedAID, rs*/);
			load_ui_elements(selectedAID/*,btnApplyClassification,btnApplyCancel,lblNewLabel_5*/,dll);
			load_chars(rs.getString("cid"),false,selectedAID);
			load_class(/*btnApplyClassification,btnApplyCancel*/);
						
			
			load_static_data(/*url, props,*/ selectedAID,pane);
			
			closing_procedure(login, pane, selectedAID, start);
			
			
			previous_procedure(dll, selectedAID, pane, start, login, clock);
			next_procedure(login, selectedAID, start, dll, clock, pane);
			
			
		}else {
			pane.setVisible(false);
			dispose();
			ReviewList ReviewList = new ReviewList(login, clock);
		}
	}
	
	
	
    
	private void next_procedure(String login,String selectedAID,Date start,DLL dll,Clock clock,JOptionPane pane) {
		lblFlexor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				pane.setVisible(true);
				if(login.equals("Corinne")) {
					try {
						ReviewDetail ReviewDetail = new ReviewDetail(dll,dll.getnextID(selectedAID),login,pane,clock);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
							| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException
							| BadPaddingException | ClassNotFoundException | IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
				
				
				
				try {
					Class.forName("org.postgresql.Driver");
					url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
					props.setProperty("user","postgres");
					props.setProperty("password","Neonec");

					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");
					//props.setProperty("ssl","true");
					Connection conn0 = DriverManager.getConnection(url, props);
					conn0.setAutoCommit(false);        
					PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp) VALUES (?, ?, ?, ?, ?)"
							+ " ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value,comp=EXCLUDED.comp;");
					
					save_chars(prepStmt, conn0);
					
					
					ReviewDetail ReviewDetail = new ReviewDetail(dll,dll.getnextID(selectedAID),login,pane,clock);
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

			private void save_chars(PreparedStatement prepStmt,Connection conn0) throws SQLException {
				if(modelLeft != null) {
					for(CharacValue element:modelLeft) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					for(CharacValue element:modelRight) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					}
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF");
					prepStmt.setString(3,textField_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF_REF");
					prepStmt.setString(3,textField_5.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "URL");
					prepStmt.setString(3,textArea_3.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "QUESTION");
					prepStmt.setString(3,textField_6.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "COMMENT");
					prepStmt.setString(3,textArea_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.executeBatch();
					conn0.commit();
					prepStmt.clearBatch();
					prepStmt.close();
					conn0.close();
					conn0 = DriverManager.getConnection(url, props);
					Date end = new Date();
					
					prepStmt = conn0.prepareStatement("UPDATE public.items SET status='REVIEWED',cid= ?,\"classChange\"=? , \"ques\"=?, timetaken = timetaken + ?, ledate = ? WHERE aid = ?");
					prepStmt.setString(1, currentFamily.get((String) textField_3.getText()));
					prepStmt.setBoolean(2, !originalcid.equals(currentFamily.get((String) textField_3.getText())));
					if(textField_6.getText().length()>=5) {
						prepStmt.setString(3, textField_6.getText().substring(0,5));
					}else {
						prepStmt.setString(3, textField_6.getText());
					}
					prepStmt.setDouble(4, Double.valueOf((int)((end.getTime() - start.getTime()) / 1000)));
					prepStmt.setString(6, selectedAID);
					prepStmt.setObject(5, LocalDate.now());
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					prepStmt.execute();
					
					prepStmt.close();
					conn0.close();
			}
		});
	}

	
	private void previous_procedure(DLL dll,String selectedAID,JOptionPane pane,Date start,String login,Clock clock) {
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				pane.setVisible(true);
				if(login.equals("Corinne")) {
					try {
						ReviewDetail ReviewDetail = new ReviewDetail(dll,dll.getprevID(selectedAID),login,pane,clock);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
							| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException
							| BadPaddingException | ClassNotFoundException | IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				
				
				
				
				
				try {
					Class.forName("org.postgresql.Driver");
					url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
					props.setProperty("user","postgres");
					props.setProperty("password","Neonec");

					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");
					//props.setProperty("ssl","true");
					Connection conn0 = DriverManager.getConnection(url, props);
					conn0.setAutoCommit(false);
					PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp) VALUES (?, ?, ?, ?, ?)"
							+ " ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value,comp=EXCLUDED.comp;");
					
					save_chars(conn0, prepStmt);
					
					ReviewDetail ReviewDetail = new ReviewDetail(dll,dll.getprevID(selectedAID),login,pane,clock);
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

			private void save_chars(Connection conn0, PreparedStatement prepStmt) throws SQLException {
				if(modelLeft!=null) {
					for(CharacValue element:modelLeft) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					for(CharacValue element:modelRight) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					}
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF");
					prepStmt.setString(3,textField_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF_REF");
					prepStmt.setString(3,textField_5.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "URL");
					prepStmt.setString(3,textArea_3.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "QUESTION");
					prepStmt.setString(3,textField_6.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "QUESTION");
					prepStmt.setString(3,textField_6.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "COMMENT");
					prepStmt.setString(3,textArea_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.executeBatch();
					conn0.commit();
					prepStmt.clearBatch();
					prepStmt.close();
					conn0.close();
					
					conn0 = DriverManager.getConnection(url, props);
					Date end = new Date();
					
					prepStmt = conn0.prepareStatement("UPDATE public.items SET status='REVIEWED',cid= ?,\"classChange\"=?, \"ques\"=?, timetaken=timetaken + ?, ledate = ? WHERE aid = ?");
					prepStmt.setString(1, currentFamily.get((String) textField_3.getText()));
					prepStmt.setBoolean(2, !originalcid.equals(currentFamily.get((String) textField_3.getText())));
					if(textField_6.getText().length()>=5) {
						prepStmt.setString(3, textField_6.getText().substring(0,5));
					}else {
						prepStmt.setString(3, textField_6.getText());
					}
					prepStmt.setDouble(4, Double.valueOf((int)((end.getTime() - start.getTime()) / 1000)));
					prepStmt.setString(6, selectedAID);
					prepStmt.setObject(5, LocalDate.now());
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					prepStmt.execute();
					
					prepStmt.close();
					conn0.close();	
			}
		});
	}

	private void closing_procedure(String login,JOptionPane pane,String selectedAID,Date start) {
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent event,Clock clock) {
	            pane.setVisible(true);
	            dispose();
	            if(login.equals("Corinne")) {
	            	Home home = new Home(login, clock);
	            	return;
	            }
	            try {
					Class.forName("org.postgresql.Driver");
					url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
					props.setProperty("user","postgres");
					props.setProperty("password","Neonec");
					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");
					//props.setProperty("ssl","true");
					Connection conn0 = DriverManager.getConnection(url, props);
					conn0.setAutoCommit(false);        
					PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp) VALUES (?, ?, ?, ?, ?)"
							+ " ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value,comp=EXCLUDED.comp;");
					
					save_chars(prepStmt,conn0);
				} catch (ClassNotFoundException e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (InvalidKeyException e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchAlgorithmException e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchProviderException e) {
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchPaddingException e) {
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
					StringBuilder sb = new StringBuilder(e.toString());
				    for (StackTraceElement ste : e.getStackTrace()) {
				        sb.append("\n\tat ");
				        sb.append(ste);
				    }
				    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
				            JOptionPane.ERROR_MESSAGE);
				}
				Home home = new Home(login, clock);
	        }

			private void save_chars(PreparedStatement prepStmt, Connection conn0) throws SQLException {
				if(modelLeft!=null) {
					for(CharacValue element:modelLeft) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					for(CharacValue element:modelRight) {
						prepStmt.setString(1,selectedAID);
						prepStmt.setString(2,element.getId());
						prepStmt.setString(3, element.getvalue());
						//prepStmt.setString(4,"REVIEW");
						prepStmt.setString(4,element.getComp());
						prepStmt.addBatch();
		        	}
					}
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF");
					prepStmt.setString(3,textField_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "MANUF_REF");
					prepStmt.setString(3,textField_5.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "URL");
					prepStmt.setString(3,textArea_3.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "QUESTION");
					prepStmt.setString(3,textField_6.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.setString(1,selectedAID);
					prepStmt.setString(2, "COMMENT");
					prepStmt.setString(3,textArea_4.getText());
					//prepStmt.setString(4,"REVIEW");
					prepStmt.setString(4,null);
					prepStmt.addBatch();
					
					prepStmt.executeBatch();
					conn0.commit();
					prepStmt.clearBatch();
					prepStmt.close();
					conn0.close();
					conn0 = DriverManager.getConnection(url, props);
					Date end = new Date();
					
					prepStmt = conn0.prepareStatement("UPDATE public.items SET status='PENDING', cid= ?, \"classChange\"=?, \"ques\"=?, timetaken = timetaken + ? , ledate = ? WHERE aid = ?");
					prepStmt.setString(1, currentFamily.get((String) textField_3.getText()));
					prepStmt.setBoolean(2, !originalcid.equals(currentFamily.get((String) textField_3.getText())));
					if(textField_6.getText().length()>=5) {
						prepStmt.setString(3, textField_6.getText().substring(0,5));
					}else {
						prepStmt.setString(3, textField_6.getText());
					}
					prepStmt.setDouble(4, Double.valueOf((int)((end.getTime() - start.getTime()) / 1000)));
					prepStmt.setString(6, selectedAID);
					prepStmt.setObject(5, LocalDate.now());
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					prepStmt.execute();
					
					prepStmt.close();
					conn0.close();
			}
	    });
	}

	
	private void load_class(/*JButton btnApplyClassification,JButton btnApplyCancel*/) throws SQLException {
		MouseAdapter classCancel = new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(originalDomain);
				//textField_1.removeAllItems();
				textField_1.setText(originalGroup);
				//textField_2.removeAllItems();
				textField_2.setText(originalfamily);
				//textField_3.removeAllItems();
				textField_3.setText(originalclass);
				currentFamily.clear();
				currentFamily.put(originalclass, originalcid);
				lblNewLabel_4.setText("Classification: "+currentFamily.get((String) textField_3.getText()));
				classApply.mouseClicked(e);
			}};
		Runnable cancelClass = new Runnable() {
	        @Override
	        public void run() {
	        	classCancel.mouseClicked(null); 
	        }
	    };
	    btnApplyCancel.addMouseListener(classCancel);
		textField_3.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e){
				
			}

			public void insertUpdate(DocumentEvent e) {
				if(!viewbutton ) {
					btnApplyClassification.setVisible(false);
				}
				if(textField_3.getText().split("\\.").length !=4) {
					return;
				}else{
					if(textField_3.getText().split("\\.")[3].length() <3) {
						return;
					}
					try {
						Connection conn9;
						conn9 = DriverManager.getConnection(url, props);
						PreparedStatement st9 = conn9.prepareStatement("select * from public.\"class_hierarchy\" WHERE \"cid\" = ?");
						String cidlook = textField_3.getText().substring(0, 15);
						//System.out.println(cidlook);
						st9.setString(1, cidlook);
						//st9.setString(1, textField_3.getText());
						ResultSet rs9 = st9.executeQuery();
						if(!rs9.isBeforeFirst()) {
							JOptionPane.showMessageDialog(textField_3, "<html><h2><font color='red'>Unknown Class ID, Canceling changes</font></h2></html>");
							/*textField.setText("N/A");
							textField_1.setText("N/A");
							textField_2.setText("N/A");*/
							rs9.close();
							st9.close();
							conn9.close();
							SwingUtilities.invokeLater(cancelClass);
							return;
						}
						rs9.next();
						textField.setText(rs9.getString("domain"));
						textField_1.setText(rs9.getString("groupe"));
						textField_2.setText(rs9.getString("family"));
						Runnable doHighlight = new Runnable() {
					        @Override
					        public void run() {
					        	try {
					        		String claas = rs9.getString("class");
									currentFamily.clear();
					        		//currentFamily.put(claas, textField_3.getText());
									currentFamily.put(claas, rs9.getString("cid"));
					        		
									textField_3.setText(claas);
									rs9.close();
									st9.close();
									conn9.close();
									viewbutton=true;
									btnApplyClassification.setVisible(false);
									lblNewLabel_4.setText("Classification: "+currentFamily.get((String) textField_3.getText()));
									classApply.mouseClicked(null);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					        }
					    };
						SwingUtilities.invokeLater(doHighlight);
						//textField_3.setText(rs9.getString("class"));
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			public void removeUpdate(DocumentEvent e) {
				btnApplyClassification.setVisible(false);
			}

		
		
		
		});
		
		
		conn1 = DriverManager.getConnection(url, props);
		st1 = conn1.prepareStatement("select * from public.\"class_hierarchy\" WHERE \"cid\" = ?");
		st1.setString(1, rs.getString("cid"));
		rs1 = st1.executeQuery();
		rs1.next();
		originalcid = rs.getString("cid");
		textField.setText(rs1.getString("domain"));
		originalDomain = (rs1.getString("domain"));
		//textField_1.removeAllItems();
		textField_1.setText(rs1.getString("groupe"));
		originalGroup = (rs1.getString("groupe"));
		//textField_2.removeAllItems();
		textField_2.setText(rs1.getString("family"));
		originalfamily = (rs1.getString("family"));
		//textField_3.removeAllItems();
		textField_3.setText(rs1.getString("class"));
		originalclass = (rs1.getString("class"));
		
	}

	


	private void load_ui_elements(/*DLL dll, String selectedAID, JButton btnApplyClassification, JButton btnApplyCancel, JLabel lblNewLabel_5*/String selectedAID,DLL dll) throws SQLException {
		setTitle("TECHNICAL ITEM DESCRIPTION");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PartDetail.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(0, 0, 0, 0);
		
		contentPane.setName("contentPane");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		gbl_contentPane.columnWidths = new int[]{133,133, 21, 300,99};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0,0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0,0.2, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.height = screenSize.height;
		this.width = screenSize.width;
		setPreferredSize(new Dimension(width, height));
		
		pnl_controlflex.setBackground(SystemColor.activeCaption);
		pnl_controlflex.setName("pnl_controlflex");
		pnl_controlflex.setForeground(SystemColor.activeCaption);


		gbc_pnl_controlflex.anchor = GridBagConstraints.WEST;
		gbc_pnl_controlflex.fill = GridBagConstraints.VERTICAL;
		gbc_pnl_controlflex.insets = new Insets(0, 0, 5, 0);
		gbc_pnl_controlflex.gridx = 0;
		gbc_pnl_controlflex.gridy = 0;
		gbc_pnl_controlflex.gridwidth = 1;
		contentPane.add(pnl_controlflex, gbc_pnl_controlflex);
		
		btnArrowLeft.setText("        ");
		btnArrowLeft.setHorizontalAlignment(SwingConstants.LEFT);
		btnArrowLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnArrowLeft.setActionCommand("");
		btnArrowLeft.setSize(new Dimension((int) (49* (screenSize.width/1366.0)), 50));
		btnArrowLeft.setName("btnArrowLeft");
		
		lblNewLabel.setText(dll.getprev(selectedAID));
		lblNewLabel.setName("lb_controlflex");
		pnl_controlflex.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnl_controlflex.add(btnArrowLeft);
		pnl_controlflex.add(lblNewLabel);
		
		panelCenter.setBackground(SystemColor.activeCaption);
		panelCenter.setName("panelCenter");

		gbc_panelCenter.insets = new Insets(0, 0, 5, 0);
		gbc_panelCenter.fill = GridBagConstraints.BOTH;
		gbc_panelCenter.gridx = 1;
		gbc_panelCenter.gridy = 0;
		gbc_panelCenter.gridwidth = 3;
		contentPane.add(panelCenter, gbc_panelCenter);
		
		lblVal.setText(selectedAID);
		lblVal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringSelection stringSelection = new StringSelection(selectedAID + " / " + currentFamily.get((String) textField_3.getText()));
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
		panelCenter.add(lblVal);
		lblVal.setName("lbl_val");
		

		panelRigth.setBackground(SystemColor.activeCaption);
		panelRigth.setName("panelRigth");

		gbc_panelRigth.insets = new Insets(0, 0, 5, 0);
		gbc_panelRigth.anchor = GridBagConstraints.NORTH;
		gbc_panelRigth.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelRigth.gridx = 4;
		gbc_panelRigth.gridy = 0;
		contentPane.add(panelRigth, gbc_panelRigth);
		

		lblFlexor.setText(dll.getnext(selectedAID));
		lblFlexor.setName("lbl_flexor");
		panelRigth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelRigth.add(lblFlexor);
		
		btnArrowRigth.setText("        ");
		btnArrowRigth.setSize(new Dimension(100, 100));
		btnArrowRigth.setName("btnArrowRight");
		panelRigth.add(btnArrowRigth);
		
		pnlDescription.setName("shortDescription");
		gbc_pnlDescription.insets = new Insets(0, 0, 5, 0);
		gbc_pnlDescription.fill = GridBagConstraints.BOTH;
		gbc_pnlDescription.gridx = 0;
		gbc_pnlDescription.gridy = 1;
		gbc_pnlDescription.gridwidth = 5;
		contentPane.add(pnlDescription, gbc_pnlDescription);

		gbl_pnlDescription.columnWidths = new int[]{0, 0};
		gbl_pnlDescription.rowHeights = new int[]{0, 0};
		gbl_pnlDescription.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlDescription.rowWeights = new double[]{0.0, 1.0};
		pnlDescription.setLayout(gbl_pnlDescription);
		
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		pnlDescription.add(panel, gbc_panel);
		

		lblNewLabel_1.setText("Short description");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		
		textArea.setSelectionColor(Color.LIGHT_GRAY);
		lblNewLabel_2.setText("Long description");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 16));
		
		textArea_1.setSelectionColor(Color.LIGHT_GRAY);
		
		lblNewLabel_3.setText("PO text");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 16));
		
		btnApplyClassification.setText("APPLY CLASSIFICATION CHANGE");
		
		
		textArea_2.setSelectionColor(Color.LIGHT_GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_3))
						.addComponent(textArea_2, GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(9)
									.addComponent(lblNewLabel_1)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(35)
									.addComponent(lblNewLabel_2)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		

		pnlClassification.setName("pnlClassification");

		gbc_pnlClassification.gridheight = 3;
		gbc_pnlClassification.insets = new Insets(0, 0, 0, 5);
		gbc_pnlClassification.fill = GridBagConstraints.BOTH;
		gbc_pnlClassification.gridx = 0;
		gbc_pnlClassification.gridy = 2;
		contentPane.add(pnlClassification, gbc_pnlClassification);

		gbl_pnlClassification.columnWidths = new int[]{0, 0};
		gbl_pnlClassification.rowHeights = new int[]{0, 0};
		gbl_pnlClassification.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlClassification.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		pnlClassification.setLayout(gbl_pnlClassification);
		

		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 16));

		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 15, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		pnlClassification.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField.setEditable(false);

		gbc_textField.insets = new Insets(0, 6, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		pnlClassification.add(textField, gbc_textField);
		//textField.setColumns(10);
		
		textField_1.setEditable(false);

		gbc_textField_1.insets = new Insets(0, 6, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 2;
		pnlClassification.add(textField_1, gbc_textField_1);
		//textField_1.setColumns(10);
		
		textField_2.setEditable(false);


		gbc_textField_2.insets = new Insets(0, 6, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 3;
		pnlClassification.add(textField_2, gbc_textField_2);
		//textField_2.setColumns(10);
		


		gbc_textField_3.insets = new Insets(0, 6, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 4;
		pnlClassification.add(textField_3, gbc_textField_3);
		//textField_3.setColumns(10);
		
		
		
		///////////////////////////////////////////////////////////
		

		
		
		classApply = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				load_chars(currentFamily.get((String) textField_3.getText()),true, selectedAID);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
					| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException
					| BadPaddingException | ClassNotFoundException | SQLException | IOException e1) {
				StringBuilder sb = new StringBuilder(e1.toString());
			    for (StackTraceElement ste : e1.getStackTrace()) {
			        sb.append("\n\tat ");
			        sb.append(ste);
			    }
			    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",
			            JOptionPane.ERROR_MESSAGE);
			}
		}};
		
		btnApplyClassification.addMouseListener(classApply);
		btnApplyClassification.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnApplyClassification.setName("btnApplyClassification");

		gbc_btnApplyClassification.insets = new Insets(0, 6, 5, 0);
		gbc_btnApplyClassification.fill = GridBagConstraints.BOTH;
		gbc_btnApplyClassification.gridx = 0;
		gbc_btnApplyClassification.gridy = 5;
		pnlClassification.add(btnApplyClassification, gbc_btnApplyClassification);
			
		btnApplyCancel.setText("CANCEL CLASSIFICATION CHANGE");
		btnApplyCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnApplyCancel.setName("btnCancelClassification");

		gbc_btnApplyCancel.insets = new Insets(0, 6, 5, 0);
		gbc_btnApplyCancel.fill = GridBagConstraints.BOTH;
		gbc_btnApplyCancel.gridx = 0;
		gbc_btnApplyCancel.gridy = 6;
		pnlClassification.add(btnApplyCancel, gbc_btnApplyCancel);
		
		lblNewLabel_5.setText("Manufacturer name");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 16));

		gbc_lblNewLabel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_5.insets = new Insets(0, 15, 0, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 7;
		pnlClassification.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		
		textField_4.setSelectionColor(Color.LIGHT_GRAY);

		gbc_textField_4.insets = new Insets(0, 6, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 0;
		gbc_textField_4.gridy = 8;
		pnlClassification.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_6.setText("Manufacturer reference");
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 16));

		gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 15, 0, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 9;
		pnlClassification.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		
		textField_5.setSelectionColor(Color.LIGHT_GRAY);

		gbc_textField_5.insets = new Insets(0, 6, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 10;
		pnlClassification.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		lblNewLabel_7.setText("Source URL");
		lblNewLabel_7.setFont(new Font("Calibri", Font.BOLD, 16));

		gbc_lblNewLabel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_7.insets = new Insets(0, 15, 0, 0);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 11;
		pnlClassification.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		textArea_3.setSelectionColor(Color.LIGHT_GRAY);

		gbc_textArea_3.insets = new Insets(0, 6, 5, 0);
		gbc_textArea_3.fill = GridBagConstraints.BOTH;
		gbc_textArea_3.gridx = 0;
		gbc_textArea_3.gridy = 12;
		pnlClassification.add(textArea_3, gbc_textArea_3);
		
		pnlCharValues.setName("pnlCharValues");

		gbc_pnlCharValues.gridheight = 3;
		gbc_pnlCharValues.insets = new Insets(0, 0, 5, 5);
		gbc_pnlCharValues.fill = GridBagConstraints.BOTH;
		gbc_pnlCharValues.gridx = 1;
		gbc_pnlCharValues.gridy = 2;
		gbc_pnlCharValues.gridwidth = 4;
		contentPane.add(pnlCharValues, gbc_pnlCharValues);

		gbl_pnlCharValues.columnWidths = new int[]{0};
		gbl_pnlCharValues.rowHeights = new int[]{0,0};
		gbl_pnlCharValues.columnWeights = new double[]{1.0};
		gbl_pnlCharValues.rowWeights = new double[]{1.0,0.2};
		pnlCharValues.setLayout(gbl_pnlCharValues);
		

		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlCharValues.add(scrollPane, gbc_scrollPane);
		

		scrollPane.setViewportView(panelCharVals);
		panelCharVals.setName("panelCharVals");
		
		gbl_panelCharVals.columnWidths = new int[]{0,0};
		gbl_panelCharVals.rowHeights = new int[]{0,0};
		gbl_panelCharVals.columnWeights = new double[]{1.0,1.0};
		gbl_panelCharVals.rowWeights = new double[]{1.0,1.0};
		panelCharVals.setLayout(gbl_panelCharVals);
		


		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panelCharVals.add(panel_1, gbc_panel_1);
		
		gbl_panel_1.columnWidths = new int[]{350, 0};
		gbl_panel_1.rowHeights = new int[]{16, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		

		lblNewLabel_8.setText("Characteristic Values");
		lblNewLabel_8.setForeground(new Color(68, 84, 105));
		lblNewLabel_8.setFont(new Font("Calibri", Font.BOLD, 12));

		gbc_lblNewLabel_8.insets = new Insets(0, 62, 5, 0);
		gbc_lblNewLabel_8.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 0;
		panel_1.add(lblNewLabel_8, gbc_lblNewLabel_8);
		

		lblNewLabel_9.setText("Value complement");

		lblNewLabel_9.setForeground(new Color(68, 84, 105));
		lblNewLabel_9.setFont(new Font("Calibri", Font.BOLD, 12));
		gbc_lblNewLabel_9.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 0;
		panel_1.add(lblNewLabel_9, gbc_lblNewLabel_9);
		


		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panelCharVals.add(panel_2, gbc_panel_2);

		gbl_panel_2.columnWidths = new int[]{350, 0};
		gbl_panel_2.rowHeights = new int[]{16, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		

		lblNewLabel_10.setText("Characteristic Values");
		lblNewLabel_10.setForeground(new Color(68, 84, 105));
		lblNewLabel_10.setFont(new Font("Calibri", Font.BOLD, 12));
		
		gbc_lblNewLabel_10.insets = new Insets(0, 62, 5, 0);
		gbc_lblNewLabel_10.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 0;
		panel_2.add(lblNewLabel_10, gbc_lblNewLabel_10);
		

		lblNewLabel_12.setText("Value complement");
		lblNewLabel_12.setForeground(new Color(68, 84, 105));
		lblNewLabel_12.setFont(new Font("Calibri", Font.BOLD, 12));

		gbc_lblNewLabel_12.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 0;
		panel_2.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		pnlCharacLeft.setName("pnlCharacLeft");

		gbc_pnlCharacLeft.anchor = GridBagConstraints.NORTH;
		gbc_pnlCharacLeft.insets = new Insets(0, 0, 0, 5);
		gbc_pnlCharacLeft.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlCharacLeft.gridx = 0;
		gbc_pnlCharacLeft.gridy = 1;
		panelCharVals.add(pnlCharacLeft, gbc_pnlCharacLeft);

		gbl_pnlCharacLeft.columnWidths = new int[]{0};
		gbl_pnlCharacLeft.rowHeights = new int[]{0};
		gbl_pnlCharacLeft.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pnlCharacLeft.rowWeights = new double[]{Double.MIN_VALUE};
		pnlCharacLeft.setLayout(gbl_pnlCharacLeft);
		
		pnlCharacRigth.setName("pnlCharacRigth");
		
		gbc_pnlCharacRigth.anchor = GridBagConstraints.NORTH;
		gbc_pnlCharacRigth.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlCharacRigth.gridx = 1;
		gbc_pnlCharacRigth.gridy = 1;
		panelCharVals.add(pnlCharacRigth, gbc_pnlCharacRigth);

		gbl_pnlCharacRigth.columnWidths = new int[]{0};
		gbl_pnlCharacRigth.rowHeights = new int[]{0};
		gbl_pnlCharacRigth.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_pnlCharacRigth.rowWeights = new double[]{0};
		pnlCharacRigth.setLayout(gbl_pnlCharacRigth);
		
		
		
		pnlComment.setName("pnlComment");

		gbc_pnlComment.fill = GridBagConstraints.BOTH;
		gbc_pnlComment.gridx = 0;
		gbc_pnlComment.gridy = 1;
		pnlCharValues.add(pnlComment, gbc_pnlComment);

		gbl_pnlComment.columnWidths = new int[]{0, 0, 0, 0, 0, 0, -85, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlComment.rowHeights = new int[]{0, 0, 0};
		gbl_pnlComment.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlComment.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlComment.setLayout(gbl_pnlComment);
		
		lblQuestion.setForeground(new Color(68, 84, 105));
		lblQuestion.setFont(new Font("Calibri", Font.BOLD, 12));
		
		gbc_lblQuestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuestion.gridx = 0;
		gbc_lblQuestion.gridy = 0;
		pnlComment.add(lblQuestion, gbc_lblQuestion);
		
		
		lblNewLabel_11.setText("Comment");
		lblNewLabel_11.setForeground(new Color(68, 84, 105));
		lblNewLabel_11.setFont(new Font("Calibri", Font.BOLD, 12));
		
		gbc_lblNewLabel_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 0;
		pnlComment.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		textField_6.setSelectionColor(Color.LIGHT_GRAY);

		gbc_textField_6.insets = new Insets(0, 0, 0, 5);
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.gridx = 0;
		gbc_textField_6.gridy = 1;
		pnlComment.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		textArea_4.setSelectionColor(Color.LIGHT_GRAY);
		
		gbc_textArea_4.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.gridx = 1;
		gbc_textArea_4.gridy = 1;
		pnlComment.add(textArea_4, gbc_textArea_4);
		
		Dimension d = gbl_contentPane.minimumLayoutSize(contentPane);
		setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(),
				Double.valueOf(400).intValue()));
		
		//textArea.setEditable(false);
		textArea.setText(rs.getString("sd"));
		textArea.setLineWrap(true);
		//textArea_1.setEditable(false);
		textArea_1.setText(rs.getString("ld"));
		//textArea_2.setEditable(false);
		textArea_2.setText(rs.getString("po"));
		//textField.setEditable(false);
		


		textArea.setLineWrap(true);
		textArea_1.setLineWrap(true);
		textArea_2.setLineWrap(true);
		textArea_3.setLineWrap(true);
		

		
		lblNewLabel_4.setText("Classification: "+currentFamily.get((String) textField_3.getText()));
		btnApplyClassification.setVisible(false);
		
		
	}

	

	private void load_item_data(/*Connection conn, String url, Properties props, PreparedStatement st,*/ String selectedAID/*, ResultSet rs*/) throws SQLException {
		conn = DriverManager.getConnection(url, props);
		st = conn.prepareStatement("select * from public.\"items\" WHERE \"aid\" = ?");
		st.setString(1, selectedAID);
		rs = st.executeQuery();
		rs.next();
	}

	



	private void load_static_data(/*String url, Properties props,*/ String selectedAID,JOptionPane pane) throws SQLException {
		Connection conn9 = DriverManager.getConnection(url, props);
		PreparedStatement st9 = conn9.prepareStatement("select * from public.\"data\" WHERE \"aid\" = ?");
		//st9.setString(2, "REVIEW");
		st9.setString(1, selectedAID);
		ResultSet rs9 = st9.executeQuery();
		//if(rs9.isBeforeFirst()) {
			while(rs9.next()) {
				if(rs9.getString("chid").equals("MANUF")) {
					textField_4.setText(rs9.getString("value"));
				}
				if(rs9.getString("chid").equals("MANUF_REF")) {
					textField_5.setText(rs9.getString("value"));
				}
				if(rs9.getString("chid").equals("URL")) {
					textArea_3.setText(rs9.getString("value"));
				}
				if(rs9.getString("chid").equals("QUESTION")) {
					textField_6.setText(rs9.getString("value"));
				}
				if(rs9.getString("chid").equals("COMMENT")) {
					textArea_4.setText(rs9.getString("value"));
				}
			}
			
			rs.close();
			rs1.close();
			st.close();
			st1.close();
			conn.close();
			conn1.close();
			setVisible(true);
			currentFamily.clear();
			currentFamily.put(originalclass, originalcid);
			pack();
			pane.setVisible(false);
		
	}

	

	private void load_chars(String string,Boolean refresh, String selectedAID) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, SQLException, IOException {
    	List<CharacValue> characValues = characService.getCharacValues(string);
    	if(characValues.isEmpty()) {
    		pnlCharacLeft.removeAll();
    		pnlCharacRigth.removeAll();
    		pnlCharacLeft.repaint();
    		pnlCharacRigth.repaint();
    		if(refresh) {
    			pack();
    		}
    		return;
    	}
    	characValues.get(0).stat=false;
    	for(CharacValue cv:characValues) {
    		cv.setVal(selectedAID,false,string);
    	}
    	
    	Map<String,List<CharacValue>> listModels = toLeftOrRigthList(characValues);
		
		modelLeft = listModels.get("LEFT");
		modelRight = listModels.get("RIGTH");
		
		characValueRenderer.rendererComponents(pnlCharacLeft, modelLeft);
		characValueRenderer.rendererComponents(pnlCharacRigth, modelRight);
		if(refresh) {
			pack();
		}
	}

	private DefaultListModel<CharacValue> toListModel(List<CharacValue> characValues){
    	DefaultListModel<CharacValue> listModel = new DefaultListModel<>();
    	for(CharacValue characValue : characValues){
    		listModel.addElement(characValue);
    	}
		return listModel;
    	
    }
	
	private Map<String,List<CharacValue>> toLeftOrRigthList(List<CharacValue> characValues){
		List<CharacValue> characValuesLeft = new ArrayList<>();
		List<CharacValue> characValuesRigth = new ArrayList<>();
		for(int i=0; i<characValues.size();i++){
			if((i+1)%2 == 0){
				characValuesRigth.add(characValues.get(i));
			}else{
				characValuesLeft.add(characValues.get(i));
			}
		}
		Map<String,List<CharacValue>> result = new HashMap<>();
		result.put("RIGTH", characValuesRigth);
		result.put("LEFT", characValuesLeft);
		
		return result;
	}
}
