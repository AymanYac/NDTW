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
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import ntdw.common.MotionPanel;
import ntdw.models.FixedColumnTable;
import ntdw.service.ResultSetToExcel;
import ntdw.service.ResultSetToExcel.FormatType;
import ntdw.service.Tools;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;


























public class Report
extends JFrame
{
	private JPanel contentPane;
	private JTable table;
	private int height;
	private int width;
	protected boolean click = false;
	protected FixedColumnTable fct;
	private HashMap<String, String> targets = new HashMap();
	protected HashMap<String, String> old_targets = new HashMap();





	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Report frame = new Report("Elise", null);
			}
		});
	}








	public Report(String login, Clock clock)
	{
		SynthLookAndFeel laf = new SynthLookAndFeel();
		try {
			laf.load(ItemList.class.getResourceAsStream("/ntdw/resources/laf.xml"), ItemList.class);
			UIManager.setLookAndFeel(laf);
		}
		catch (UnsupportedLookAndFeelException|ParseException e1) {
			e1.printStackTrace();
		}



		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ItemList.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
		setTitle("TECHNICAL ITEM DESCRIPTION WIZARD");
		setMinimumSize(new Dimension(500, 300));
		try
		{
			start(login, clock);

		}
		catch (InvalidKeyException|ClassNotFoundException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|SQLException|IOException e)
		{
			e.printStackTrace();
		}
	}
























	@SuppressWarnings("resource")
	private void start(final String login, final Clock clock)
			throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
	{

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				Report.this.dispose();
				Home home = new Home(login, clock);
				Report.this.save_targets();
			} });
		setLocationRelativeTo(null);
		setBounds(0, 0, 0, 0);
		this.contentPane = new MotionPanel(this);
		this.contentPane.setAutoscrolls(true);
		this.contentPane.setBackground(Color.CYAN);
		this.contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
		this.contentPane.setName("contentPane");
		setContentPane(this.contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[4];
		gbl_contentPane.rowHeights = new int[] { 0, 0, 33, 171, 0, 0, 0, 0, 0, 0, 0, 4 };
		gbl_contentPane.columnWeights = new double[] { 1.0D, 1.0D, 1.0D, 1.0D };
		gbl_contentPane.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0, 0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 1.0D, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setName("pnl_user_name");
		panel.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = 1;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.contentPane.add(panel, gbc_panel);

		JLabel lblSalman = new JLabel("User : " + login);
		lblSalman.setName("lbl_user_name");
		panel.add(lblSalman);

		JPanel panel_1 = new JPanel();
		panel_1.setName("pnl_target");
		panel_1.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = 1;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		this.contentPane.add(panel_1, gbc_panel_1);

		JLabel lblNewLabel = new JLabel("SELECT START DATE >");

		panel_1.add(lblNewLabel);
		final SqlDateModel model1 = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		panel_1.add(datePicker1);

		JPanel panel_2 = new JPanel();
		panel_2.setName("pnl_completed_week");
		panel_2.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(5, 5, 5, 5);
		gbc_panel_2.fill = 1;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		this.contentPane.add(panel_2, gbc_panel_2);

		JLabel lblCompletedThisWeek = new JLabel("SELECT END DATE >");

		panel_2.add(lblCompletedThisWeek);
		final SqlDateModel model = new SqlDateModel();

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel_2.add(datePicker);


		JPanel panel_3 = new JPanel();
		panel_3.setName("pnl_completed_day");
		panel_3.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(5, 5, 5, 0);
		gbc_panel_3.fill = 1;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 0;
		this.contentPane.add(panel_3, gbc_panel_3);

		JLabel lblCompletedThisDay = new JLabel("APPLY DATE SELECTION");
		lblCompletedThisDay.setName("lbl_completed_day");
		panel_3.add(lblCompletedThisDay);
		DefaultTableModel modeldata = new DefaultTableModel();

		JOptionPane pane = new JOptionPane(new JLabel("Loading Data"));
		final JScrollPane scrollPane = new JScrollPane(this.table, 20, 30);
		final JDialog dialog = pane.createDialog(scrollPane, "Loading Data");





		JTable table1 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		JScrollPane scrollPane_1 = new JScrollPane();













		panel_3.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("deprecation")
			public void mousePressed(MouseEvent e)
			{


				if(model1.getValue()==null) {
					JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'> Please select a start/end date to get dynamic reports. </font></h2></html>"));
					return;
				}
				dialog.setModal(false);
				dialog.setVisible(true);

				Report.this.save_targets();

				java.util.Date depart = Report.this.get_prev_monday((java.sql.Date)model1.getValue());
				java.util.Date fin = Report.this.get_next_monday((java.sql.Date)model.getValue());

				LinkedList<java.util.Date> lundis = Report.this.get_mondays(depart, fin);



				LinkedHashMap<String, HashMap<String, String>> week_perfs = new LinkedHashMap();
				LinkedHashSet<String> known_weeks = new LinkedHashSet();
				LinkedHashSet<String> known_users = new LinkedHashSet();
				for (int i = 0; i < lundis.size(); i++) {
					HashMap<String, String> user_counts = new HashMap();
					java.util.Date lundi = (java.util.Date)lundis.get(i);
					String week = lundi.toString().split(" ")[2] + " " + lundi.toString().split(" ")[1] + " " + lundi.toString().split(" ")[5].substring(2, 4);
					known_weeks.add(week);
					java.util.Date apres = null;
					try {
						apres = (java.util.Date)lundis.get(i + 1);
					} catch (Exception V) {
						apres = null;
					}
					try {
						Class.forName("org.postgresql.Driver");
						String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
						Properties props = new Properties();
						props.setProperty("user", "postgres");
						props.setProperty("password", "Neonec");
						props.setProperty("loginTimeout", "20");
						props.setProperty("connectTimeout", "0");
						props.setProperty("socketTimeout", "0");

						Connection conn = DriverManager.getConnection(url, props);
						PreparedStatement st = null;
						if ((login.equals("Elise")) || (login.equals("Aparna"))) {
							if (apres == null) {
								st = conn.prepareStatement("select login, count(*) AS COUNT from progress where work = 'INITIAL' and phase = 'DESCRIPTION' and time >= ? group by login");
								Timestamp ts = new Timestamp(lundi.getTime());
								st.setTimestamp(1, ts);
							} else {
								st = conn.prepareStatement("select login, count(*) AS COUNT from progress where work = 'INITIAL' and phase = 'DESCRIPTION' and time >= ? and time < ? group by login");
								Timestamp ts = new Timestamp(lundi.getTime());
								st.setTimestamp(1, ts);
								Timestamp ts2 = new Timestamp(apres.getTime());
								st.setTimestamp(2, ts2);

							}


						}
						else if (apres == null) {
							st = conn.prepareStatement("select login, count(*) AS COUNT from progress where work = 'INITIAL' and phase = 'DESCRIPTION' and  login = ? AND time >= ? group by login");
							st.setString(1, login);
							Timestamp ts = new Timestamp(lundi.getTime());
							st.setTimestamp(2, ts);
						} else {
							st = conn.prepareStatement("select login, count(*) AS COUNT from progress where work = 'INITIAL' and phase = 'DESCRIPTION' and  login = ? AND time >= ? AND time < ? group by login");
							st.setString(1, login);
							Timestamp ts = new Timestamp(lundi.getTime());
							st.setTimestamp(2, ts);
							Timestamp ts2 = new Timestamp(apres.getTime());
							st.setTimestamp(3, ts2);
						}



						ResultSet rs = st.executeQuery();

						while (rs.next()) {
							known_users.add(rs.getString("login"));
							user_counts.put(rs.getString("login"), rs.getString("count"));
						}
						rs.close();
						st.close();
						conn.close();


					}
					catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException|ClassNotFoundException|SQLException e1)
					{


						e1.printStackTrace();
					}
					week_perfs.put(week, user_counts);
				}
				Properties props;
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
					props = new Properties();
					props.setProperty("user", "postgres");
					props.setProperty("password", "Neonec");
					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");

					Connection conn = DriverManager.getConnection(url, props);
					PreparedStatement st = conn.prepareStatement("select * from public.targets");
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						Report.this.old_targets.put(rs.getString("userweek"), rs.getString("value"));
					}

				}
				catch (ClassNotFoundException|SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
				{
					e1.printStackTrace();
				}
				dialog.setVisible(false);

				if (!Report.this.click) {
					Report.this.click = true;
					modeldata.addColumn("User name");
					for (java.util.Date lundi : lundis) {
						modeldata.addColumn(lundi.toString().split(" ")[2] + " " + lundi.toString().split(" ")[1] + " " + lundi.toString().split(" ")[5].substring(2, 4));
					}

					for (String user : known_users) {
						modeldata.addRow(Report.this.perf_row(week_perfs, user, Boolean.valueOf(true)));
						modeldata.addRow(Report.this.perf_row(week_perfs, user, Boolean.valueOf(false)));
					}
					Report.this.table.setModel(modeldata);

					Report.this.resizeColumnWidth(Report.this.table);
					Report.this.table.setAutoResizeMode(0);
					scrollPane.setViewportView(Report.this.table);
					Report.this.fct = new FixedColumnTable(1, scrollPane);
					Report.this.fct.main.getModel().addTableModelListener(new TableModelListener()
					{
						public void tableChanged(TableModelEvent e) {
							int row = e.getFirstRow();
							int column = e.getColumn();
							TableModel model = (TableModel)e.getSource();
							String week = model.getColumnName(column);
							String user = (String)model.getValueAt(row, 0);
							String data = (String)model.getValueAt(row, column);
							Report.this.save_target(user, week, data);
						}


					});
				}
				else
				{
					int OldColCount = Report.this.fct.main.getColumnCount();
					TableColumn column;
					for (int i = 0; i < OldColCount; i++)
					{
						TableColumnModel columnModel = Report.this.fct.main.getColumnModel();
						column = columnModel.getColumn(0);
						columnModel.removeColumn(column);
					}


					DefaultTableModel modeldata2 = new DefaultTableModel();
					modeldata2.addColumn("User name");
					for (java.util.Date lundi : lundis) {
						modeldata2.addColumn(lundi.toString().split(" ")[2] + " " + lundi.toString().split(" ")[1] + " " + lundi.toString().split(" ")[5].substring(2, 4));
					}
					for (String user : known_users) {
						modeldata2.addRow(Report.this.perf_row(week_perfs, user, Boolean.valueOf(true)));
						modeldata2.addRow(Report.this.perf_row(week_perfs, user, Boolean.valueOf(false)));
					}
					Report.this.fct.main.setModel(modeldata2);
					Report.this.fct.main.getModel().addTableModelListener(new TableModelListener()
					{
						public void tableChanged(TableModelEvent e) {
							int row = e.getFirstRow();
							int column = e.getColumn();
							TableModel model = (TableModel)e.getSource();
							String week = model.getColumnName(column);
							String user = (String)model.getValueAt(row, 0);
							String data = (String)model.getValueAt(row, column);
							Report.this.save_target(user, week, data);
						}
					});
				}








				HashMap<String,HashMap<String,Integer>> workTally = new HashMap<String,HashMap<String,Integer>> ();
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
					props = new Properties();
					props.setProperty("user", "postgres");
					props.setProperty("password", "Neonec");
					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");

					Connection conn = DriverManager.getConnection(url, props);
					PreparedStatement st = null;
					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*) from public.progress  where work ='INITIAL' and phase = 'DESCRIPTION' AND time >=? AND time <=? group by login");
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.progress  where work ='INITIAL' and phase = 'DESCRIPTION' AND login=? AND time >=? AND time <=? group by login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("ITEMS COMPLETED ON INITIAL", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("ITEMS COMPLETED ON INITIAL", complete);
							workTally.put(user,user_work);
						}
					}

					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*) from public.progress  where work = 'REWORK' and phase = 'DESCRIPTION' AND time >=? AND time <=? group by login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.progress  where work = 'REWORK' and phase = 'DESCRIPTION' AND login=? AND time >=? AND time <=? group by login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("ITEMS COMPLETED ON REWORK", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("ITEMS COMPLETED ON REWORK", complete);
							workTally.put(user,user_work);
						}
					}

					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*) from public.progress  where phase = 'REVIEW' AND time >=? AND time <=? group by login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.progress  where phase = 'REVIEW' AND login=? AND time >=? AND time <=? group by login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("ITEMS REVIEWED (INTL & RWRK)", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("ITEMS REVIEWED (INTL & RWRK)", complete);
							workTally.put(user,user_work);
						}
					}





					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,SUM(card) as count from public.progress  where work = 'INITIAL' and phase ='DESCRIPTION' and time >=? AND time <=? group by login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,SUM(card) as count from public.progress where work ='INITIAL' and phase='DESCRIPTION' and login = ? AND time >=? AND time <=? group by login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("VALUES COMPLETED ON INITIAL", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("VALUES COMPLETED ON INITIAL", complete);
							workTally.put(user,user_work);
						}
					}

					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,SUM(card) as count from public.progress  where phase ='REVIEW' and time >=? AND time <=? group by login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);  st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,SUM(card) as count from public.progress where phase='REVIEW' and login = ? AND time >=? AND time <=? group by login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);  st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("VALUES REVIEWED (INTL & RWRK)", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("VALUES REVIEWED (INTL & RWRK)", complete);
							workTally.put(user,user_work);
						}
					}




























					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'I' AND time >=? AND time <=? group by reclassifs.login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'I' and login = ? AND time >=? AND time <=? group by reclassifs.login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("RECLASSIFS TYPE I", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("RECLASSIFS TYPE I", complete);
							workTally.put(user,user_work);
						}
					}

					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'II' AND time >=? AND time <=? group by reclassifs.login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'II' and login = ? AND time >=? AND time <=? group by reclassifs.login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("RECLASSIFS TYPE II", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("RECLASSIFS TYPE II", complete);
							workTally.put(user,user_work);
						}
					}

					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'III' AND time >=? AND time <=? group by reclassifs.login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'III' and login = ? AND time >=? AND time <=? group by reclassifs.login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("RECLASSIFS TYPE III", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("RECLASSIFS TYPE III", complete);
							workTally.put(user,user_work);
						}
					}


					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'IV' AND time >=? AND time <=? group by reclassifs.login");

						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(1, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(2, ts2);
					}else {
						st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'IV' and login = ? AND time >=? AND time <=? group by reclassifs.login");
						st.setString(1, login);
						Timestamp ts = new Timestamp(model1.getValue().getTime());
						ts.setHours(0);
						ts.setMinutes(0);
						ts.setSeconds(0);st.setTimestamp(2, ts);
						Timestamp ts2 = new Timestamp(model.getValue().getTime());
						ts2.setHours(23);
						ts2.setMinutes(59);
						ts2.setSeconds(59);
						st.setTimestamp(3, ts2);
					}
					rs = st.executeQuery();

					while(rs.next()) {
						try {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = workTally.get(user);
							user_work.put("RECLASSIFS TYPE IV", complete);
							workTally.put(user,user_work);
						}catch(Exception NO_SUCH_USER) {
							String user = rs.getString("login");
							int complete = rs.getInt("count");
							HashMap<String,Integer> user_work = new HashMap<String,Integer>();
							user_work.put("RECLASSIFS TYPE IV", complete);
							workTally.put(user,user_work);
						}
					}
					rs.close();
					st.close();
					conn.close();
					DefaultTableModel modeldata_1 = new DefaultTableModel();
					ArrayList<String> cols = new ArrayList<String>();
					cols.add("ITEMS COMPLETED ON INITIAL");
					cols.add("VALUES COMPLETED ON INITIAL");

					cols.add("ITEMS COMPLETED ON REWORK");

					cols.add("ITEMS REVIEWED (INTL & RWRK)");
					cols.add("VALUES REVIEWED (INTL & RWRK)");

					cols.add("RECLASSIFS TYPE I");
					cols.add("RECLASSIFS TYPE II");
					cols.add("RECLASSIFS TYPE III");

					modeldata_1.addColumn("User");
					for(String col:cols) {
						modeldata_1.addColumn(col);
					}

					for(String user:workTally.keySet()) {
						modeldata_1.addRow(workRow(user,workTally.get(user),cols));
					}
					table1.setModel(modeldata_1);







					scrollPane_1.setViewportView(table1);


				}
				catch (ClassNotFoundException|SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
				{
					e1.printStackTrace();
				}


























			}








			public void mouseReleased(MouseEvent e) {}
		});
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(5, 5, 5, 0);
		gbc_panel_4.anchor = 13;
		gbc_panel_4.fill = 3;
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 1;
		gbc_panel_4.gridwidth = 2;
		this.contentPane.add(panel_4, gbc_panel_4);

		JButton btnNewButton_1 = new JButton("Download data");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {


				if(model1.getValue()==null || model.getValue()==null) {
					JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'> Please enter a start and end date for your data extraction. </font></h2></html>"));
					return;
				}

				Boolean bool=false;
				String[] options = { "YES", "NO" };
				int response = JOptionPane.showOptionDialog(null, new JLabel(
						"<html><h2><font color='red'> Would you like to also download new items?</font></h2></html>"), "Downloading ...", 
						-1, -1, 
						null, options, options[0]);
				if (response == 0) {
					bool=true; 
				}






				String home = System.getProperty("user.home");
				try {
					Class.forName("org.postgresql.Driver");
					String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
					Properties props = new Properties();
					props.setProperty("user", "postgres");
					props.setProperty("password", "Neonec");
					props.setProperty("loginTimeout", "20");
					props.setProperty("connectTimeout", "0");
					props.setProperty("socketTimeout", "0");

					Connection conn = DriverManager.getConnection(url, props);
					PreparedStatement stmt = null;
					String user = null;
					if ((login.equals("Elise")) || (login.equals("Aparna"))) {
						if(bool) {
							stmt = conn.prepareStatement("select CAR.aid as \"Article#\",sd as \"Short Desc.\",ld as \"Long Desc.\",po as \"PO text\",ocid as \"Intial Class#\",cid as \"Final Class#\",family as \"Family Name\",class as \"Class Name\",chid as \"Char#\",\"chName\" as \"Char Name\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\" as \"UOM\", value as \"Value\", comp as \"Complement\", type as \"Char Type\", classifier as \"Descriptor\",reviewer as \"Reviewer\",status as \"Status\",ledate as \"Last edit date\" from\r\n" + 
									"(select aid,sd,ld,po,ocid, items.cid, family, class, classifier,reviewer,status,ledate,visible from items left join class_hierarchy on items.cid = class_hierarchy.cid order by ledate DESC) CAR\r\n" + 
									"left join\r\n" + 
									"(\r\n" + 
									"\r\n" + 
									"select aid,chid,\"chName\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\",value,comp,type from \r\n" + 
									"(select * from data where LEFT(aid,6)!='XXXXXX') CA \r\n" + 
									"left join \r\n" + 
									"char_schema \r\n" + 
									"on CA.chid = char_schema.\"chId\"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									") CDR\r\n" + 
									"on CAR.aid = CDR.aid where (ledate IS NULL and visible = 'OUI')  or (ledate <= ? and ledate >= ? and visible = 'OUI') order by ledate DESC");
							Timestamp ts = new Timestamp(model.getValue().getTime());
							stmt.setTimestamp(1, ts);
							Timestamp ts2 = new Timestamp(model1.getValue().getTime());
							stmt.setTimestamp(2, ts2);

						}else {
							stmt = conn.prepareStatement("select CAR.aid as \"Article#\",sd as \"Short Desc.\",ld as \"Long Desc.\",po as \"PO text\",ocid as \"Intial Class#\",cid as \"Final Class#\",family as \"Family Name\",class as \"Class Name\",chid as \"Char#\",\"chName\" as \"Char Name\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\" as \"UOM\", value as \"Value\", comp as \"Complement\", type as \"Char Type\", classifier as \"Descriptor\",reviewer as \"Reviewer\",status as \"Status\",ledate as \"Last edit date\" from\r\n" + 
									"(select aid,sd,ld,po,ocid, items.cid, family, class, classifier,reviewer,status,ledate,visible from items left join class_hierarchy on items.cid = class_hierarchy.cid order by ledate DESC) CAR\r\n" + 
									"right join\r\n" + 
									"(\r\n" + 
									"\r\n" + 
									"select aid,chid,\"chName\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\",value,comp,type from \r\n" + 
									"(select * from data where LEFT(aid,6)!='XXXXXX') CA \r\n" + 
									"left join \r\n" + 
									"char_schema \r\n" + 
									"on CA.chid = char_schema.\"chId\"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									") CDR\r\n" + 
									"on CAR.aid = CDR.aid where ledate <= ? and ledate >= ? and visible = 'OUI' order by ledate DESC");
							Timestamp ts = new Timestamp(model.getValue().getTime());
							stmt.setTimestamp(1, ts);
							Timestamp ts2 = new Timestamp(model1.getValue().getTime());
							stmt.setTimestamp(2, ts2);

						}
						user="ALL_USERS";

					}else {
						if(bool) {
							stmt = conn.prepareStatement("select CAR.aid as \"Article#\",sd as \"Short Desc.\",ld as \"Long Desc.\",po as \"PO text\",ocid as \"Intial Class#\",cid as \"Final Class#\",family as \"Family Name\",class as \"Class Name\",chid as \"Char#\",\"chName\" as \"Char Name\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\" as \"UOM\", value as \"Value\", comp as \"Complement\", type as \"Char Type\", classifier as \"Descriptor\",reviewer as \"Reviewer\",status as \"Status\",ledate as \"Last edit date\" from\r\n" + 
									"(select aid,sd,ld,po,ocid, items.cid, family, class, classifier,reviewer,status,ledate,visible from items left join class_hierarchy on items.cid = class_hierarchy.cid order by ledate DESC) CAR\r\n" + 
									"left join\r\n" + 
									"(\r\n" + 
									"\r\n" + 
									"select aid,chid,\"chName\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\",value,comp,type from \r\n" + 
									"(select * from data where LEFT(aid,6)!='XXXXXX') CA \r\n" + 
									"left join \r\n" + 
									"char_schema \r\n" + 
									"on CA.chid = char_schema.\"chId\"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									") CDR\r\n" + 
									"on CAR.aid = CDR.aid where (ledate IS NULL and classifier = ? and visible = 'OUI')  or (ledate <= ? and ledate >= ? and classifier = ? and visible = 'OUI') order by ledate DESC");
							stmt.setString(1, login);
							Timestamp ts = new Timestamp(model.getValue().getTime());
							stmt.setTimestamp(2, ts);
							Timestamp ts2 = new Timestamp(model1.getValue().getTime());
							stmt.setTimestamp(3, ts2);
							stmt.setString(4, login);

						}else {
							stmt = conn.prepareStatement("select CAR.aid as \"Article#\",sd as \"Short Desc.\",ld as \"Long Desc.\",po as \"PO text\",ocid as \"Intial Class#\",cid as \"Final Class#\",family as \"Family Name\",class as \"Class Name\",chid as \"Char#\",\"chName\" as \"Char Name\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\" as \"UOM\", value as \"Value\", comp as \"Complement\", type as \"Char Type\", classifier as \"Descriptor\",reviewer as \"Reviewer\",status as \"Status\",ledate as \"Last edit date\" from\r\n" + 
									"(select aid,sd,ld,po,ocid, items.cid, family, class, classifier,reviewer,status,ledate,visible from items left join class_hierarchy on items.cid = class_hierarchy.cid order by ledate DESC) CAR\r\n" + 
									"right join\r\n" + 
									"(\r\n" + 
									"\r\n" + 
									"select aid,chid,\"chName\",\"isClosed\",\"isNumeric\",\"isCritical\",\"allowedNums\",value,comp,type from \r\n" + 
									"(select * from data where LEFT(aid,6)!='XXXXXX') CA \r\n" + 
									"left join \r\n" + 
									"char_schema \r\n" + 
									"on CA.chid = char_schema.\"chId\"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									"\r\n" + 
									") CDR\r\n" + 
									"on CAR.aid = CDR.aid where ledate <= ? and ledate >= ? and classifier = ? and visible = 'OUI' order by ledate DESC");
							Timestamp ts = new Timestamp(model.getValue().getTime());
							stmt.setTimestamp(1, ts);
							Timestamp ts2 = new Timestamp(model1.getValue().getTime());
							stmt.setTimestamp(2, ts2);
							stmt.setString(3, login);

						}
						user=login;
					}




					ResultSet resultSet = stmt.executeQuery();

					ArrayList<ResultSetToExcel.FormatType> typecolonnes = new ArrayList<ResultSetToExcel.FormatType>();
					for(int z=0;z<20;z+=1) {
						typecolonnes.add(ResultSetToExcel.FormatType.TEXT);
					}
					typecolonnes.add(ResultSetToExcel.FormatType.DATE);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
					LocalDateTime now = LocalDateTime.now();
					ResultSetToExcel resultSetToExcel = new ResultSetToExcel(resultSet, (FormatType[]) typecolonnes.toArray(new FormatType[0]), "Dwnlded on "+dtf.format(now));
					resultSetToExcel.generate(new File(home+"\\EXTRACT_"+user+"_"+String.valueOf(model1.getValue().getDate())+"-"+String.valueOf(model1.getValue().getMonth()+1)+"___"+String.valueOf(model.getValue().getDate())+"-"+String.valueOf(model.getValue().getMonth()+1)+".xls"));
					JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'> File saved under : "+home+"</font></h2></html>"));

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'> A file with the same name is already open. </font></h2></html>"));


				}









			}
		});
		btnNewButton_1.setVisible(true);
		panel_4.add(btnNewButton_1);

		JButton button = new JButton("Go Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Report.this.dispose();
				Home home = new Home(login, clock);
				Report.this.save_targets();
			}
		});
		panel_4.add(button);




		Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.height = screenSize.height;
		this.width = screenSize.width;
		setPreferredSize(new Dimension(this.width, this.height));

		JLabel lblAffectedItemsSnapshot = new JLabel("Affected items snapshot : This is the current items assigned to you and their respective statuses");
		GridBagConstraints gbc_lblAffectedItemsSnapshot = new GridBagConstraints();
		gbc_lblAffectedItemsSnapshot.anchor = GridBagConstraints.WEST;
		gbc_lblAffectedItemsSnapshot.gridwidth = 4;
		gbc_lblAffectedItemsSnapshot.insets = new Insets(5, 5, 5, 5);
		gbc_lblAffectedItemsSnapshot.gridx = 0;
		gbc_lblAffectedItemsSnapshot.gridy = 2;
		this.contentPane.add(lblAffectedItemsSnapshot, gbc_lblAffectedItemsSnapshot);
		Dimension d = gbl_contentPane.minimumLayoutSize(this.contentPane);



		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 4;
		gbc_scrollPane_3.insets = new Insets(5, 5, 5, 0);
		gbc_scrollPane_3.fill = 1;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 3;
		this.contentPane.add(scrollPane_3, gbc_scrollPane_3);






		JTable table2 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		JScrollPane scrollPane_2 = new JScrollPane();


		HashMap<String,HashMap<String,Integer>> workTally = new HashMap<String,HashMap<String,Integer>> ();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "Neonec");
			props.setProperty("loginTimeout", "20");
			props.setProperty("connectTimeout", "0");
			props.setProperty("socketTimeout", "0");

			Connection conn = DriverManager.getConnection(url, props);
			PreparedStatement st = null;
			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*) from public.progress  where work ='INITIAL' and phase = 'DESCRIPTION' group by login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.progress  where work ='INITIAL' and phase = 'DESCRIPTION' AND login=? group by login");
				st.setString(1, login);
			}
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("ITEMS COMPLETED ON INITIAL", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("ITEMS COMPLETED ON INITIAL", complete);
					workTally.put(user,user_work);
				}
			}

			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*) from public.progress  where work ='REWORK' and phase = 'DESCRIPTION' group by login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.progress   where work ='REWORK' and phase = 'DESCRIPTION' AND login=? group by login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("ITEMS COMPLETED ON REWORK", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("ITEMS COMPLETED ON REWORK", complete);
					workTally.put(user,user_work);
				}
			}

			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*) from public.progress  where phase = 'REVIEW' group by login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.progress   where phase = 'REVIEW' AND login=? group by login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("ITEMS REVIEWED (INTL & RWRK)", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("ITEMS REVIEWED (INTL & RWRK)", complete);
					workTally.put(user,user_work);
				}
			}














			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,SUM(card) as count from public.progress where work = 'INITIAL' and phase='DESCRIPTION' group by login");
			}else {
				st = conn.prepareStatement("select login,SUM(card) as count from public.progress where work = 'INTIAL' and phase='DESCRIPTION' and login = ? group by login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("VALUES COMPLETED ON INITIAL", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("VALUES COMPLETED ON INITIAL", complete);
					workTally.put(user,user_work);
				}
			}

			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,SUM(card) as count from public.progress where phase='REVIEW' group by login");
			}else {
				st = conn.prepareStatement("select login,SUM(card) as count from public.progress where phase='REVIEW' and login = ? group by login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("VALUES REVIEWED (INTL & RWRK)", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("VALUES REVIEWED (INTL & RWRK)", complete);
					workTally.put(user,user_work);
				}
			}




			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'I' group by reclassifs.login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'I' and login = ? group by reclassifs.login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("RECLASSIFS TYPE I", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("RECLASSIFS TYPE I", complete);
					workTally.put(user,user_work);
				}
			}

			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'II' group by reclassifs.login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'II' and login = ? group by reclassifs.login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("RECLASSIFS TYPE II", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("RECLASSIFS TYPE II", complete);
					workTally.put(user,user_work);
				}
			}

			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'III' group by reclassifs.login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'III' and login = ? group by reclassifs.login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("RECLASSIFS TYPE III", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("RECLASSIFS TYPE III", complete);
					workTally.put(user,user_work);
				}
			}


			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("select login,count(*)  from public.reclassifs where type = 'IV' group by reclassifs.login");
			}else {
				st = conn.prepareStatement("select login,count(*) from public.reclassifs where type = 'IV' and login = ? group by reclassifs.login");
				st.setString(1, login);
			}
			rs = st.executeQuery();

			while(rs.next()) {
				try {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = workTally.get(user);
					user_work.put("RECLASSIFS TYPE IV", complete);
					workTally.put(user,user_work);
				}catch(Exception NO_SUCH_USER) {
					String user = rs.getString("login");
					int complete = rs.getInt("count");
					HashMap<String,Integer> user_work = new HashMap<String,Integer>();
					user_work.put("RECLASSIFS TYPE IV", complete);
					workTally.put(user,user_work);
				}
			}
			rs.close();
			st.close();
			conn.close();
			DefaultTableModel modeldata_2 = new DefaultTableModel();
			ArrayList<String> cols = new ArrayList<String>();
			cols.add("ITEMS COMPLETED ON INITIAL");
			cols.add("VALUES COMPLETED ON INITIAL");

			cols.add("ITEMS COMPLETED ON REWORK");

			cols.add("ITEMS REVIEWED (INTL & RWRK)");
			cols.add("VALUES REVIEWED (INTL & RWRK)");

			cols.add("RECLASSIFS TYPE I");
			cols.add("RECLASSIFS TYPE II");
			cols.add("RECLASSIFS TYPE III");

			modeldata_2.addColumn("User");
			for(String col:cols) {
				modeldata_2.addColumn(col);
			}

			for(String user:workTally.keySet()) {
				modeldata_2.addRow(workRow(user,workTally.get(user),cols));
			}
			table2.setModel(modeldata_2);







			scrollPane_2.setViewportView(table2);



		}
		catch (ClassNotFoundException|SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
		{
			e1.printStackTrace();
		}






























		JTable table3 = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};


		DefaultTableModel modeldata3 = new DefaultTableModel();
		ArrayList<String> statuses = new ArrayList<String> ();
		statuses.add("NOUVEAU");
		statuses.add("REWORK:NOUVEAU");
		statuses.add("IN_PROGRESS");
		statuses.add("REWORK:IN_PROGRESS");
		statuses.add("PENDING");
		statuses.add("REWORK:PENDING");
		statuses.add("COMPLET");
		statuses.add("REWORK:COMPLET");
		statuses.add("REVIEWED");
		statuses.add("REWORK:REVIEWED");
		HashMap<String,HashMap<String,Integer>> snapshot = new HashMap<String,HashMap<String,Integer>> ();

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "Neonec");
			props.setProperty("loginTimeout", "20");
			props.setProperty("connectTimeout", "0");
			props.setProperty("socketTimeout", "0");

			Connection conn = DriverManager.getConnection(url, props);
			PreparedStatement st = null;
			if ((login.equals("Elise")) || (login.equals("Aparna"))) {
				st = conn.prepareStatement("SELECT classifier, status, count(*) FROM public.items where visible = 'OUI' group by status,classifier;");
			}else {
				st = conn.prepareStatement("SELECT classifier, status, count(*) FROM public.items where classifier=? and visible = 'OUI' group by status,classifier;");
				st.setString(1, login);
			}

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String user = rs.getString("classifier");
				String status = rs.getString("status");
				Integer count = rs.getInt("count");

				try {
					HashMap <String,Integer> user_stat = snapshot.get(user);
					user_stat.put(status, count);
					snapshot.put(user, user_stat);
				}catch(Exception NO_SUCH_USER) {
					HashMap <String,Integer> user_stat = new HashMap <String,Integer>();
					user_stat.put(status, count);
					snapshot.put(user, user_stat);
				}

			}

			rs.close();
			st.close();
			conn.close();
		}
		catch (ClassNotFoundException|SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
		{
			e1.printStackTrace();
		}

		modeldata3.addColumn("User");
		for(String stat:statuses) {
			modeldata3.addColumn(stat);
		}
		for(String user:snapshot.keySet()) {
			modeldata3.addRow(SnapRow(user,statuses,snapshot.get(user)));
		}

		table3.setModel(modeldata3);

		scrollPane_3.setViewportView(table3);

		JLabel lblStaticWorkProgression = new JLabel("Static work progression : This summarizes your global work since July 15th 2018");
		GridBagConstraints gbc_lblStaticWorkProgression = new GridBagConstraints();
		gbc_lblStaticWorkProgression.anchor = GridBagConstraints.WEST;
		gbc_lblStaticWorkProgression.gridwidth = 4;
		gbc_lblStaticWorkProgression.insets = new Insets(0, 0, 5, 5);
		gbc_lblStaticWorkProgression.gridx = 0;
		gbc_lblStaticWorkProgression.gridy = 4;
		contentPane.add(lblStaticWorkProgression, gbc_lblStaticWorkProgression);









		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 4;
		gbc_scrollPane_2.fill = 1;
		gbc_scrollPane_2.insets = new Insets(5, 5, 5, 0);
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 6;
		this.contentPane.add(scrollPane_2, gbc_scrollPane_2);

		JLabel lblNewLabel_1 = new JLabel("Dynamic work progression : Choose a start/end date and get a summary of your date-local work");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 7;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.insets = new Insets(5, 5, 5, 0);
		gbc_scrollPane_5.fill = 1;
		gbc_scrollPane_5.gridx = 3;
		gbc_scrollPane_5.gridy = 8;
		this.contentPane.add(scrollPane_5, gbc_scrollPane_5);

		JLabel lblTargetTally = new JLabel("Target Tally : Choose a start / end date and follow your weekly performance in contrast with the expected results set by management.");
		GridBagConstraints gbc_lblTargetTally = new GridBagConstraints();
		gbc_lblTargetTally.anchor = 17;
		gbc_lblTargetTally.gridwidth = 4;
		gbc_lblTargetTally.insets = new Insets(5, 5, 5, 0);
		gbc_lblTargetTally.gridx = 0;
		gbc_lblTargetTally.gridy = 9;
		this.contentPane.add(lblTargetTally, gbc_lblTargetTally);




		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.fill = 1;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 8;
		gbc_scrollPane_1.insets = new Insets(5, 5, 5, 0);
		this.contentPane.add(scrollPane_1, gbc_scrollPane_1);



		this.table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return (row % 2 != 0) && ((login.equals("Elise")) || (login.equals("Aparna")));



			}





		};
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = 1;
		gbc_scrollPane.insets = new Insets(5, 5, 0, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 10;

		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
		this.contentPane.add(scrollPane, gbc_scrollPane);

		scrollPane.setViewportView(this.table);
		setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(), 
				Double.valueOf(400.0D).intValue()));

		pack();
		setVisible(true);
	}

	private Vector workRow(String user, HashMap<String, Integer> hashMap, ArrayList<String> cols) {
		Vector<String> vec = new Vector(cols.size()+1);
		vec.add(0,user);
		int i = 0;
		for(String col:cols) {
			i+=1;
			try {
				vec.add(i,hashMap.get(col).toString());
			}catch(Exception NO_SUCH_WORK_FOR_USER) {
				vec.add(i,"0");
			}
		}
		return vec;
	}








	private Vector SnapRow(String user, ArrayList<String> statuses, HashMap<String, Integer> hashMap) {
		Vector<String> vec = new Vector(statuses.size()+1);
		vec.add(0,user);
		int i = 0;
		for(String stat:statuses) {
			i+=1;
			try {
				vec.add(i,hashMap.get(stat).toString());
			}catch(Exception NO_SUCH_STATUS_FOR_USER) {
				vec.add(i,"0");
			}
		}
		return vec;
	}








	protected Vector perf_row_without_user(LinkedHashMap<String, HashMap<String, String>> week_perfs, String user, boolean actual) {
		Vector<String> vec = new Vector(week_perfs.size());
		if (actual) {
			for (String week : week_perfs.keySet()) {
				try {
					vec.add((String)((HashMap)week_perfs.get(week)).get(user));
				} catch (Exception G) {
					vec.add("0");
				}
			}
		} else {
			for (String week : week_perfs.keySet()) {
				vec.add("0");
			}
		}

		return vec;
	}

	protected Vector perf_row(LinkedHashMap<String, HashMap<String, String>> week_perfs, String user, Boolean actual) {
		Vector<String> vec = new Vector(week_perfs.size() + 1);
		if (actual.booleanValue()) {
			vec.add(0, user + " : Actual     ");
			for (String week : week_perfs.keySet()) {
				try {
					vec.add((String)((HashMap)week_perfs.get(week)).get(user));
				} catch (Exception G) {
					vec.add("0");
				}
			}
		} else {
			vec.add(0, user + " : Target     ");
			for (String week : week_perfs.keySet()) {
				try {
					vec.add((String)this.old_targets.get(user + "&&" + week));
				} catch (Exception e) {
					vec.add("0");
				}
			}
		}

		return vec;
	}

	protected LinkedList<java.util.Date> get_mondays(java.util.Date depart, java.util.Date fin)
	{
		LinkedList<java.util.Date> lundis = new LinkedList();

		Calendar date1 = Calendar.getInstance();
		date1.clear();
		date1.setTime(depart);
		Calendar date2 = Calendar.getInstance();
		date2.clear();
		date2.setTime(fin);
		while (date1.getTime().compareTo(date2.getTime()) != 0) {
			if (date1.get(7) == 2) {
				lundis.add(date1.getTime());
			}
			date1.add(5, 1);
		}

		return lundis;
	}

	protected java.util.Date get_next_monday(java.sql.Date value) {
		Calendar date1 = Calendar.getInstance();
		date1.clear();
		if (value != null) {
			date1.set(Integer.parseInt(value.toString().split("-")[0].trim()), Integer.parseInt(value.toString().split("-")[1].trim()) - 1, Integer.parseInt(value.toString().split("-")[2].trim()));
		} else {
			date1.set(2019, 1, 1);
		}
		while (date1.get(7) != 2) {
			date1.add(5, 1);
		}

		return date1.getTime();
	}

	protected java.util.Date get_prev_monday(java.sql.Date value) {
		Calendar date1 = Calendar.getInstance();
		date1.clear();
		if (value != null) {
			date1.set(Integer.parseInt(value.toString().split("-")[0].trim()), Integer.parseInt(value.toString().split("-")[1].trim()) - 1, Integer.parseInt(value.toString().split("-")[2].trim()));
		}
		else {
			date1.set(2018, 1, 1);
		}
		while (date1.get(7) != 2) {
			date1.add(5, -1);
		}

		return date1.getTime();
	}

	public void save_target(String user, String week, String value)
	{
		user = user.split(" : ")[0];
		this.targets.put(user + "&&" + week, value);
	}

	public void save_targets() { if (this.targets.keySet().size() == 0) {
		return;
	}
	try {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");

		Connection conn = DriverManager.getConnection(url, props);
		conn.setAutoCommit(false);
		PreparedStatement st = conn.prepareStatement("INSERT INTO public.targets(userweek,value) values (?,?)ON CONFLICT(userweek) DO UPDATE SET value=EXCLUDED.value;");



		for (String userweek : this.targets.keySet()) {
			st.setString(1, userweek);
			st.setString(2, (String)this.targets.get(userweek));
			st.addBatch();
		}
		st.executeBatch();
		conn.commit();
		st.clearBatch();
		st.close();
		conn.close();

	}
	catch (ClassNotFoundException|SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e)
	{
		e.printStackTrace();
	}
	}


	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException
	{
		ResultSetMetaData metaData = rs.getMetaData();


		Vector<String> columnNames = new Vector();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}


		Vector<Vector<Object>> data = new Vector();
		while (rs.next()) {
			Vector<Object> vector = new Vector();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);
	}



	public void resizeColumnWidth(JTable table)
	{
		TableColumnModel columnModel = table.getColumnModel();
		int column = 0; if (column < table.getColumnCount()) {
			int width = 15;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\Report.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */