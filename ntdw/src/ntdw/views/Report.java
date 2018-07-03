package ntdw.views;

import java.awt.Color;
import java.awt.Component;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;
import org.joda.time.DateTimeConstants;
import org.oxbow.swingbits.table.filter.TableRowFilterSupport;

import ntdw.common.ArticleTableModel;
import ntdw.common.ButtonColumn;
import ntdw.common.MotionPanel;
import ntdw.model.Article;
import ntdw.model.Target;
import ntdw.models.FixedColumnTable;
import ntdw.service.ArticleMockDataService;
import ntdw.service.DLL;
import ntdw.service.Node;
import ntdw.service.Tools;

import java.awt.SystemColor;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

public class Report extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//private Report_Week_Target service = Report_Week_Target.getInstance();
	private int height;
	private int width;
	protected Clock clock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Report frame = new Report("Elise", null);
				
			}
		});
	}

	/**
	 * Create the frame.
	 * @param login 
	 * @param clock 
	 */
	public Report(String login, Clock clock) {
		this.clock=clock;

			SynthLookAndFeel laf = new SynthLookAndFeel();
			try {
				laf.load(ItemList.class.getResourceAsStream("/ntdw/resources/laf.xml"), ItemList.class);
				UIManager.setLookAndFeel(laf);
			} catch (UnsupportedLookAndFeelException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ItemList.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
		setTitle("TECHNICAL ITEM DESCRIPTION WIZARD");
		setMinimumSize(new Dimension(500, 300));
		
		try {
			start(login);
		} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchProviderException
				| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException | BadPaddingException
				| SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void start(String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
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
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 171, 0, 0, 0, 0, 4 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setName("pnl_user_name");
		panel.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		JLabel lblSalman = new JLabel("User : "+login);
		lblSalman.setName("lbl_user_name");
		panel.add(lblSalman);

		JPanel panel_1 = new JPanel();
		panel_1.setName("pnl_target");
		panel_1.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);

		JLabel lblNewLabel = new JLabel("SELECT START DATE >");
		//lblNewLabel.setName("lbl_week_target");
		panel_1.add(lblNewLabel);
		SqlDateModel model1 = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1,new DateLabelFormatter());
		panel_1.add(datePicker1);

		JPanel panel_2 = new JPanel();
		panel_2.setName("pnl_completed_week");
		panel_2.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_2, gbc_panel_2);

		JLabel lblCompletedThisWeek = new JLabel("SELECT END DATE >");
		//lblCompletedThisWeek.setName("lbl_completed_week");
		panel_2.add(lblCompletedThisWeek);
		SqlDateModel model = new SqlDateModel();
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		panel_2.add(datePicker);

		//JLabel lblNewLabel_2 = new JLabel(service.getCompWeek(login));
		//lblNewLabel_2.setName("lbl_completed_week_value");
		//panel_2.add(lblNewLabel_2);
		
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		JPanel panel_3 = new JPanel();
		panel_3.setName("pnl_completed_day");
		panel_3.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 0;
		contentPane.add(panel_3, gbc_panel_3);

		JLabel lblCompletedThisDay = new JLabel("APPLY DATE SELECTION");
		lblCompletedThisDay.setName("lbl_completed_day");
		panel_3.add(lblCompletedThisDay);
		
		panel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            	java.util.Date depart = get_prev_monday(model1.getValue());
            	java.util.Date fin = get_next_monday(model.getValue());
            	
            	LinkedList<java.util.Date> lundis = get_mondays(depart,fin);
            	
            	
            	DefaultTableModel modeldata = new DefaultTableModel();
        		modeldata.addColumn("User name");
        		for (java.util.Date lundi:lundis) {
        			modeldata.addColumn(lundi.toString().split(" ")[1]+" "+lundi.toString().split(" ")[2]);
        		}
        		LinkedHashMap<String,HashMap<String,String>> week_perfs = new LinkedHashMap<String,HashMap<String,String>>();
        		LinkedHashSet<String> known_weeks = new LinkedHashSet<String>();
        		HashSet<String> known_users = new HashSet<String>();
        		for(int i=0;i<lundis.size();i++) {
        			HashMap<String,String> user_counts = new HashMap<String,String> ();
        			java.util.Date lundi = lundis.get(i);
        			String week = lundi.toString().split(" ")[1]+" "+lundi.toString().split(" ")[2];
        			known_weeks.add(week);
        			java.util.Date apres = null;
        			try {
        				apres=lundis.get(i+1);
        			}catch(Exception V) {
        				apres=null;
        			}
        			try {
        				Class.forName("org.postgresql.Driver");
						String url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
						Properties props = new Properties();
	        			props.setProperty("user","postgres");
	        			props.setProperty("password","Neonec");
	        			props.setProperty("loginTimeout", "20");
	        			props.setProperty("connectTimeout", "0");
	        			props.setProperty("socketTimeout", "0");
	        			//props.setProperty("ssl","true");
	        			Connection conn = DriverManager.getConnection(url, props);
	        			PreparedStatement st = null;
	        			if(login.equals("Elise") || login.equals("Corinne")) {
	        				if(apres==null) {
	        					st = conn.prepareStatement("select login, count(*) AS COUNT from progress where time >= ? group by login");
	        					java.sql.Timestamp ts =  new java.sql.Timestamp(lundi.getTime());
		        				st.setTimestamp(1,ts);
	        				}else {
	        					st = conn.prepareStatement("select login, count(*) AS COUNT from progress where time >= ? and time < ? group by login");
	        					java.sql.Timestamp ts =  new java.sql.Timestamp(lundi.getTime());
		        				st.setTimestamp(1,ts);
		        				java.sql.Timestamp ts2 =  new java.sql.Timestamp(apres.getTime());
		        				st.setTimestamp(2,ts2);
	        				}
	        				
	        				
	        				
	        			}else {
	        				if(apres==null) {
	        					st = conn.prepareStatement("select login, count(*) AS COUNT from progress where classifier = ? AND time >= ? group by login");
		        				st.setString(1, login);
		        				java.sql.Timestamp ts =  new java.sql.Timestamp(lundi.getTime());
			        			st.setTimestamp(2,ts);
	        				}else {
	        					st = conn.prepareStatement("select login, count(*) AS COUNT from progress where classifier = ? AND time > ? AND time < ? group by login");
		        				st.setString(1, login);
		        				java.sql.Timestamp ts =  new java.sql.Timestamp(lundi.getTime());
			        			st.setTimestamp(2,ts);
			        			java.sql.Timestamp ts2 =  new java.sql.Timestamp(apres.getTime());
		        				st.setTimestamp(3,ts2);
	        				}
	        				
	        			}
	        			
	        			ResultSet rs = st.executeQuery();
	        			
	        			while(rs.next()) {
	        				known_users.add(rs.getString("login"));
	        				user_counts.put(rs.getString("login"), rs.getString("count"));
	        			}
	        			rs.close();
	        			st.close();
	        			conn.close();
	        			
	        			
	        			
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException
							| NoSuchPaddingException | ShortBufferException | IllegalBlockSizeException
							| BadPaddingException | IOException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			week_perfs.put(week, user_counts);
        			
        		}
        		
        		
        		for(String user:known_users) {
        			modeldata.addRow(perf_row(week_perfs,user,true));
        			modeldata.addRow(perf_row(week_perfs,user,false));
        		}
        		table.setModel(modeldata);
        		//table.getColumnModel().getColumn(0).setWidth(Double.valueOf(width/6).intValue());
        		resizeColumnWidth( table);
        		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        		
        		FixedColumnTable fct = new FixedColumnTable(1, scrollPane);
        		
        		
            	
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
		//JLabel lblNewLabel_1 = new JLabel(service.getCompDay(login));
		//lblNewLabel_1.setName("lbl_completed_day_value");
		//panel_3.add(lblNewLabel_1);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(10, 0, 5, 5);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		contentPane.add(panel_5, gbc_panel_5);

		JLabel lblNewLabel_3 = new JLabel("");
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

		JButton btnNewButton_1 = new JButton("Download data");
		btnNewButton_1.setVisible(true);
		panel_4.add(btnNewButton_1);

		JButton button = new JButton("Go Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				Home home = new Home(login, null);
			}
		});
		panel_4.add(button);

		
		
		
		Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.height = screenSize.height;
		this. width = screenSize.width;
		setPreferredSize(new Dimension(width, height));
		
		
		
		
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");
		//props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		PreparedStatement st = null;
		if(login.equals("Elise") || login.equals("Corinne")) {
			st = conn.prepareStatement("select * from items");
		}else {
			st = conn.prepareStatement("select * from items where classifier = ?");
			st.setString(1, login);
		}

		ResultSet rs = st.executeQuery();
		HashMap<String,HashMap<Integer,HashMap<String,Integer>>> tmp = new HashMap<String,HashMap<Integer,HashMap<String,Integer>>> ();
		while(rs.next()) {
			String user=rs.getString("classifier");
			Integer week=rs.getInt("target_week");
			String status=rs.getString("status");
			if(tmp.containsKey(user)) {
				if(tmp.get(user).containsKey(week)) {
					if(status.contains("COMPLET")) {
						status="COMPLET";
					}else {
						status="PENDING";
					}
					if(tmp.get(user).get(week).containsKey(status)) {
						Integer count = tmp.get(user).get(week).get(status);
						count = count+1;
						HashMap tmp_statuses = tmp.get(user).get(week);
						tmp_statuses.put(status, count);
						HashMap tmp_weeks = tmp.get(user);
						tmp_weeks.put(week, tmp_statuses);
						tmp.put(user, tmp_weeks);
						
					}else {
						Integer count = 1;
						HashMap tmp_statuses = tmp.get(user).get(week);
						tmp_statuses.put(status, count);
						HashMap tmp_weeks = tmp.get(user);
						tmp_weeks.put(week, tmp_statuses);
						tmp.put(user, tmp_weeks);
					}
				}else {
					if(status.contains("COMPLET")) {
						status="COMPLET";
					}else {
						status="PENDING";
					}
					Integer count = 1;
					HashMap tmp_status = new HashMap<String,Integer>();
					tmp_status.put(status, count);
					HashMap tmp_weeks = tmp.get(user);
					tmp_weeks.put(week, tmp_status);
					tmp.put(user, tmp_weeks);
					
					
				}
			}else {
				if(status.contains("COMPLET")) {
					status="COMPLET";
				}else {
					status="PENDING";
				}
				Integer count = 1;
				HashMap tmp_status = new HashMap<String,Integer>();
				tmp_status.put(status, count);
				HashMap tmp_week = new HashMap<Integer,HashMap<String,Integer>>();
				tmp_week.put(week, tmp_status);
				tmp.put(user, tmp_week);
			}
			
			
		}
		rs.close();
		st.close();
		conn.close();
		Integer cols=0;
		HashSet<Integer> weeks = new HashSet<Integer>();
		for(String nom:tmp.keySet()) {
			Integer size = tmp.get(nom).size();
			if(size>cols) {
				size=cols;
			}
			for(Integer week:tmp.get(nom).keySet()) {
				weeks.add(week);
			}
		}
		
		List<Integer> sortedList = new ArrayList<Integer>(weeks);
		Collections.sort(sortedList);
		
		DefaultTableModel modeldata = new DefaultTableModel();
		modeldata.addColumn("User name");
		for(Integer ww:sortedList) {
			modeldata.addColumn("Week "+ww.toString());
		}
		for(String nom:tmp.keySet()) {
			modeldata.addRow(row(nom,tmp,sortedList));
			
		}
		//List<Target> articles = service.getArticles(login);

		
		//for(int i=0;i<cols;i++) {
		//	table.getColumnModel().getColumn(i).setWidth(Double.valueOf(width/6).intValue());
		//}
		
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//TableRowFilterSupport.forTable(table).searchable(true).apply();
		//table.getColumnModel().getColumn(0).setResizable(true);
		/*table.getColumnModel().getColumn(0).setPreferredWidth(Double.valueOf(width/18).intValue());
		table.getColumnModel().getColumn(1).setPreferredWidth(Double.valueOf(width/6).intValue());
		table.getColumnModel().getColumn(2).setPreferredWidth(Double.valueOf(width/9).intValue());
		table.getColumnModel().getColumn(3).setPreferredWidth(Double.valueOf(width/9).intValue());
		table.getColumnModel().getColumn(5).setPreferredWidth(Double.valueOf(width/50).intValue());
		table.getColumnModel().getColumn(6).setPreferredWidth(Double.valueOf(width/50).intValue());
		table.getColumnModel().getColumn(7).setPreferredWidth(Double.valueOf(width/50).intValue());*/
		
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
		    	return;
		    	
		    }
		};
		//ButtonColumn articleIdButton = new ButtonColumn(table, articleIdClickAction, 0);
		
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 8, 8, 8);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;

		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
		
		JLabel label_1 = new JLabel("New label");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		contentPane.add(label_1, gbc_label_1);
		contentPane.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(table);
		Dimension d = gbl_contentPane.minimumLayoutSize(contentPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		Class.forName("org.postgresql.Driver");

		props.setProperty("user","postgres");
		props.setProperty("password","Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");
		//props.setProperty("ssl","true");
		conn = DriverManager.getConnection(url, props);
		st = conn.prepareStatement("select * from progress");
		rs = st.executeQuery();
		
		JTable table2 = new JTable(buildTableModel(rs));
		scrollPane_2.setViewportView(table2);
		
		
		rs.close();
		st.close();
		conn.close();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 2;
		gbc_scrollPane_3.gridy = 3;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridwidth = 2;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 2;
		gbc_scrollPane_4.gridy = 4;
		contentPane.add(scrollPane_4, gbc_scrollPane_4);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.gridwidth = 2;
		gbc_scrollPane_5.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 2;
		gbc_scrollPane_5.gridy = 5;
		contentPane.add(scrollPane_5, gbc_scrollPane_5);
		
		JLabel label = new JLabel("New label");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 6;
		contentPane.add(label, gbc_label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
		
		
		
		
		
		
		
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 4;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 7;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);
		
		Class.forName("org.postgresql.Driver");

		props.setProperty("user","postgres");
		props.setProperty("password","Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");
		//props.setProperty("ssl","true");
		conn = DriverManager.getConnection(url, props);
		st = null;
		if(login.equals("Elise") || login.equals("Corinne")) {
		

		st = conn.prepareStatement("select * from progress");
		rs=st.executeQuery();
		
		JTable table1 = new JTable(buildTableModel(rs));
		rs.close();
		st.close();
		conn.close();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table1);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 7;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(),
				Double.valueOf(400).intValue()));
		
		pack();
		setVisible(true);
		}
	}

	protected Vector perf_row(LinkedHashMap<String, HashMap<String, String>> week_perfs, String user,Boolean actual) {
		Vector<String> vec = new Vector<String>(week_perfs.size()+1);
		if(actual) {
			vec.add(0,user+" : Actual");
			for(String week:week_perfs.keySet()) {
				try{
					vec.add(week_perfs.get(week).get(user));
				}catch (Exception G){
					vec.add("0");
				}
			}
		}else {
			vec.add(0,user+" : Target");
			for(String week:week_perfs.keySet()) {
				vec.add("0");
			}
		}
		
		return vec;
	}

	@SuppressWarnings("deprecation")
	protected LinkedList<java.util.Date> get_mondays(java.util.Date depart, java.util.Date fin) {
		LinkedList<java.util.Date> lundis = new LinkedList<java.util.Date> ();
		
		Calendar date1 = Calendar.getInstance();
		date1.clear();
		date1.setTime(depart);
		Calendar date2 = Calendar.getInstance();
		date2.clear();
		date2.setTime(fin);
		while(date1.getTime().compareTo(date2.getTime())!=0) {
			if(date1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				lundis.add(date1.getTime());
			}
			date1.add(Calendar.DATE, 1);
		}

		return lundis;
	}

	protected java.util.Date get_next_monday(Date value) {
		Calendar date1 = Calendar.getInstance();
		date1.clear();
    	if(value!=null) {
    		date1.set(Integer.parseInt(value.toString().split("-")[0].trim()), Integer.parseInt(value.toString().split("-")[1].trim()) - 1, Integer.parseInt(value.toString().split("-")[2].trim()));
    	}else {
    		date1.set(2019, 01,01);
    	}
    	while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
    		date1.add(Calendar.DATE, 1);
    	}

    	return date1.getTime();
	}
	
	protected java.util.Date get_prev_monday(Date value) {
		Calendar date1 = Calendar.getInstance();
		date1.clear();
		if(value!=null) {
			date1.set(Integer.parseInt(value.toString().split("-")[0].trim()), Integer.parseInt(value.toString().split("-")[1].trim())-1, Integer.parseInt(value.toString().split("-")[2].trim()));
	    	//System.out.println(date1.getTime());
		}else {
			date1.set(2018, 01,01);
		}
    	while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
    	    date1.add(Calendar.DATE, -1);
    	}

    	return date1.getTime();
	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {
		
	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}

	private Vector row(String login, HashMap<String, HashMap<Integer, HashMap<String, Integer>>> tmp, List<Integer> sortedList) {
		Vector<String> vec = new Vector<String>(sortedList.size()+1);
		vec.add(0,login);
		int j=0;
		for(Integer i:sortedList) {
			j++;

			if(tmp.get(login).containsKey(i)) {
				Integer comp=0;
				Integer pend=0;
				if(tmp.get(login).get(i).containsKey("COMPLET")) {
					comp = tmp.get(login).get(i).get("COMPLET");
				}
				if(tmp.get(login).get(i).containsKey("PENDING")) {
					comp = tmp.get(login).get(i).get("PENDING");
				}
				Integer tot = comp+pend;
				vec.add(j,comp.toString()+"/"+tot.toString());
			}else {
				vec.add(j,"0/0");
			}
		}
		return vec;
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	        break;
	    }
	}
	
	
	
	/*
	if(login.equals("Elise") || login.equals("Corinne")) {
		st=conn.prepareStatement("select * from\r\n" + 
				"\r\n" + 
				"(select CDR.classifier AS \"USER\",CDR.CC AS \"COMPLETED ITEMS\", CDR.RC AS \"COMPLETED REWORKS\", CAR.CP AS \"PENDING\", CAR.RP AS \"PENDING REWORKS\" from\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"(select p1.classifier, p1.Cp, p2.Rp from \r\n" + 
				"    	(select classifier,count(*) AS Cp from items where status='PENDING' group by classifier) p1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS Rp from items where status='REWORK:PENDING' group by classifier) p2\r\n" + 
				"	on\r\n" + 
				"    	p1.classifier = p2.classifier) CAR\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"(select t1.classifier, t1.CC, t2.RC from \r\n" + 
				"    	(select classifier,count(*) AS CC from items where status='COMPLET' group by classifier) t1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS RC from items where status='REWORK:COMPLET' group by classifier) t2\r\n" + 
				"	on\r\n" + 
				"    	t1.classifier = t2.classifier) CDR\r\n" + 
				"\r\n" + 
				"ON CAR.classifier = CDR.classifier ) CA\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"(select CAR.classifier AS \"USER\",CDR.CC AS \"NEW ITEMS\", CDR.RC AS \"IN_PROGRESS REWORKS\", CAR.CP AS \"IN_PROGRESS\" from\r\n" + 
				"\r\n" + 
				"(select p1.classifier, p1.Cp from \r\n" + 
				"    	(select classifier,count(*) AS Cp from items where status='IN_PROGRESS' group by classifier) p1\r\n" + 
				"	) CAR\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"(select t1.classifier, t1.CC, t2.RC from \r\n" + 
				"    	(select classifier,count(*) AS CC from items where status='NOUVEAU' group by classifier) t1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS RC from items where status='REWORK:IN_PROGRESS' group by classifier) t2\r\n" + 
				"	on\r\n" + 
				"    	t1.classifier = t2.classifier) CDR\r\n" + 
				"\r\n" + 
				"ON CAR.classifier = CDR.classifier) CD\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"ON CA.\"USER\" = CD.\"USER\"");
		
		
	}else {
		st=conn.prepareStatement("select * from\r\n" + 
				"\r\n" + 
				"(select CDR.classifier AS \"USER\",CDR.CC AS \"COMPLETED ITEMS\", CDR.RC AS \"COMPLETED REWORKS\", CAR.CP AS \"PENDING\", CAR.RP AS \"PENDING REWORKS\" from\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"(select p1.classifier, p1.Cp, p2.Rp from \r\n" + 
				"    	(select classifier,count(*) AS Cp from items where status='PENDING' group by classifier) p1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS Rp from items where status='REWORK:PENDING' group by classifier) p2\r\n" + 
				"	on\r\n" + 
				"    	p1.classifier = p2.classifier) CAR\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"(select t1.classifier, t1.CC, t2.RC from \r\n" + 
				"    	(select classifier,count(*) AS CC from items where status='COMPLET' group by classifier) t1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS RC from items where status='REWORK:COMPLET' group by classifier) t2\r\n" + 
				"	on\r\n" + 
				"    	t1.classifier = t2.classifier) CDR\r\n" + 
				"\r\n" + 
				"ON CAR.classifier = CDR.classifier ) CA\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"(select CAR.classifier AS \"USER\",CDR.CC AS \"NEW ITEMS\", CDR.RC AS \"IN_PROGRESS REWORKS\", CAR.CP AS \"IN_PROGRESS\" from\r\n" + 
				"\r\n" + 
				"(select p1.classifier, p1.Cp from \r\n" + 
				"    	(select classifier,count(*) AS Cp from items where status='IN_PROGRESS' group by classifier) p1\r\n" + 
				"	) CAR\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"FULL OUTER JOIN\r\n" + 
				"\r\n" + 
				"(select t1.classifier, t1.CC, t2.RC from \r\n" + 
				"    	(select classifier,count(*) AS CC from items where status='NOUVEAU' group by classifier) t1\r\n" + 
				"	full outer join\r\n" + 
				"    	(select classifier,count(*) AS RC from items where status='REWORK:IN_PROGRESS' group by classifier) t2\r\n" + 
				"	on\r\n" + 
				"    	t1.classifier = t2.classifier) CDR\r\n" + 
				"\r\n" + 
				"ON CAR.classifier = CDR.classifier) CD\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"ON CA.\"USER\" = CD.\"USER\" where CA.\"USER\" = ?");
		st.setString(1, login);
		
	}
	*/
	
	/*st = conn.prepareStatement("SELECT CA.login AS \"USER\", CA.NB_COMPLETED AS \"COMPLETED_ITEMS\", CA.NB_REWORK \"COMPLETED_REWORKS\", CA.NB_CV \"COMPLETED VALUES\", CD.RECLASS_I AS \"TYPE I RECLASS\", CD.RECLASS_II AS \"TYPE II RECLASS\", CD.RECLASS_III AS \"TYPE III RECLASS\" from (\r\n" + 
	"\r\n" + 
	"\r\n" + 
	"select CAR.login, CAR.NB_COMPLETED, CAR.NB_REWORK, CDR.NB_CV from (select C.login, C.NB_COMPLETED, R.NB_REWORK\r\n" + 
	"from \r\n" + 
	"    (select login,count(*) as NB_COMPLETED from public.progress  where status = 'COMPLET' group by login) C\r\n" + 
	"FULL OUTER JOIN \r\n" + 
	"    (select login,count(*) as NB_REWORK from public.progress  where status = 'REWORK' group by login) R\r\n" + 
	"on\r\n" + 
	"    R.login = C.login ) CAR FULL OUTER JOIN (select login,SUM(card) as NB_CV from public.progress  group by login)  CDR\r\n" + 
	"    on CAR.login = CDR.login\r\n" + 
	") CA FULL OUTER JOIN (\r\n" + 
	"\r\n" + 
	"select CAR.login, CAR.RECLASS_I, CAR.RECLASS_II, CDR.RECLASS_III from (select C.login, C.RECLASS_I, R.RECLASS_II\r\n" + 
	"from \r\n" + 
	"    (select login,count(*) AS RECLASS_I from public.reclassifs where type = 'I' group by reclassifs.login) C\r\n" + 
	"FULL OUTER JOIN \r\n" + 
	"    (select login,count(*) AS RECLASS_II from public.reclassifs where type = 'II' group by reclassifs.login) R\r\n" + 
	"on\r\n" + 
	"    R.login = C.login ) CAR FULL OUTER JOIN (select login,count(*) AS RECLASS_III from public.reclassifs where type = 'III' group by reclassifs.login)  CDR\r\n" + 
	"    on CAR.login = CDR.login ) CD\r\n" + 
	"\r\n" + 
	"    on CA.login=CD.login");
}else {
st = conn.prepareStatement("SELECT CA.login, CA.NB_COMPLETED, CA.NB_REWORK, CA.NB_CV , CD.RECLASS_I, CD.RECLASS_II, CD.RECLASS_III from (\r\n" + 
	"\r\n" + 
	"\r\n" + 
	"select CAR.login, CAR.NB_COMPLETED, CAR.NB_REWORK, CDR.NB_CV from (select C.login, C.NB_COMPLETED, R.NB_REWORK\r\n" + 
	"from \r\n" + 
	"    (select login,count(*) as NB_COMPLETED from public.progress  where status = 'COMPLET' group by login) C\r\n" + 
	"FULL OUTER JOIN \r\n" + 
	"    (select login,count(*) as NB_REWORK from public.progress  where status = 'REWORK' group by login) R\r\n" + 
	"on\r\n" + 
	"    R.login = C.login ) CAR FULL OUTER JOIN (select login,SUM(card) as NB_CV from public.progress  group by login)  CDR\r\n" + 
	"    on CAR.login = CDR.login\r\n" + 
	") CA FULL OUTER JOIN (\r\n" + 
	"\r\n" + 
	"select CAR.login, CAR.RECLASS_I, CAR.RECLASS_II, CDR.RECLASS_III from (select C.login, C.RECLASS_I, R.RECLASS_II\r\n" + 
	"from \r\n" + 
	"    (select login,count(*) AS RECLASS_I from public.reclassifs where type = 'I' group by reclassifs.login) C\r\n" + 
	"FULL OUTER JOIN \r\n" + 
	"    (select login,count(*) AS RECLASS_II from public.reclassifs where type = 'II' group by reclassifs.login) R\r\n" + 
	"on\r\n" + 
	"    R.login = C.login ) CAR FULL OUTER JOIN (select login,count(*) AS RECLASS_III from public.reclassifs where type = 'III' group by reclassifs.login)  CDR\r\n" + 
	"    on CAR.login = CDR.login ) CD\r\n" + 
	"\r\n" + 
	"    on CA.login=CD.login where CA.login = ?");
st.setString(1, login);
}*/
	
	
	
	
	
	
	
	
	
	
	
}
