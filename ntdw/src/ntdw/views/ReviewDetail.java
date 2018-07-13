 package ntdw.views;
 
 import java.awt.Color;
 import java.awt.Cursor;
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.FlowLayout;
 import java.awt.Font;
 import java.awt.GraphicsEnvironment;
 import java.awt.GridBagConstraints;
 import java.awt.GridBagLayout;
 import java.awt.HeadlessException;
 import java.awt.Insets;
 import java.awt.Rectangle;
 import java.awt.SystemColor;
 import java.awt.Toolkit;
 import java.awt.datatransfer.Clipboard;
 import java.awt.datatransfer.StringSelection;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.io.IOException;
 import java.io.PrintStream;
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
 import javax.swing.DefaultListModel;
 import javax.swing.GroupLayout;
 import javax.swing.GroupLayout.Alignment;
 import javax.swing.GroupLayout.ParallelGroup;
 import javax.swing.GroupLayout.SequentialGroup;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.JTextField;
 import javax.swing.LayoutStyle.ComponentPlacement;
 import javax.swing.SwingUtilities;
 import javax.swing.UIManager;
 import javax.swing.border.EmptyBorder;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 import javax.swing.plaf.synth.SynthLookAndFeel;
 import javax.swing.text.Document;
 import ntdw.common.CharacType;
 import ntdw.common.CharacValueRenderer;
 import ntdw.model.CharacValue;
 import ntdw.service.CharacMockDataService;
 import ntdw.service.DLL;
 import ntdw.service.Tools;
 
 
 
 
 
 
 
 
 public class ReviewDetail
   extends JFrame
 {
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
   
   public JPanel pnlClassification = new JPanel();
   public GridBagConstraints gbc_pnlClassification = new GridBagConstraints();
   public GridBagLayout gbl_pnlClassification = new GridBagLayout();
   public JLabel lblNewLabel_4 = new JLabel();
   public GridBagConstraints gbc_textField = new GridBagConstraints();
   public GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
   public GridBagConstraints gbc_textField_1 = new GridBagConstraints();
   public GridBagConstraints gbc_textField_2 = new GridBagConstraints();
   public GridBagConstraints gbc_textField_3 = new GridBagConstraints();
   
 
   CharacMockDataService characService = CharacMockDataService.getInstance();
   CharacValueRenderer characValueRenderer = new CharacValueRenderer();
   private JTextField textField_6 = new JTextField();
   protected boolean click = false;
   private String originalcid;
   private String originalDomain;
   private String originalGroup;
   private String originalfamily;
   private String originalclass;
   private List<CharacValue> modelLeft = new LinkedList();
   private List<CharacValue> modelRight = new LinkedList();
   private JPanel pnlCharacLeft = new JPanel();
   private JPanel pnlCharacRigth = new JPanel();
   private HashMap<String, String> currentFamily = new HashMap();
   protected boolean viewbutton = true;
   private ResultSet rs;
   private PreparedStatement st;
   private Connection conn;
   protected Date fedate;
   private Connection conn1;
   private ResultSet rs1;
   private PreparedStatement st1;
   private String url = new String();
   private Properties props = new Properties();
   private JPanel pnlCharValues = new JPanel();
   private JPanel pnlComment = new JPanel();
   private JLabel lblQuestion = new JLabel();
   private JLabel lblNewLabel_11 = new JLabel();
   private JButton btnArrowRigth = new JButton();
   private JButton btnArrowLeft = new JButton();
   private JLabel lblNewLabel_1 = new JLabel();
   private JButton btnApplyClassification = new JButton();
   private JLabel lblNewLabel_2 = new JLabel();
   private JLabel lblNewLabel_3 = new JLabel();
   private GridBagConstraints gbc_btnApplyClassification = new GridBagConstraints();
   private JButton btnApplyCancel = new JButton();
   private GridBagConstraints gbc_btnApplyCancel = new GridBagConstraints();
   private JLabel lblNewLabel_5 = new JLabel();
   private GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
   private GridBagConstraints gbc_textField_4 = new GridBagConstraints();
   private JLabel lblNewLabel_6 = new JLabel();
   private GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
   private GridBagConstraints gbc_textField_5 = new GridBagConstraints();
   private JLabel lblNewLabel_7 = new JLabel();
   private GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
   private GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
   private GridBagConstraints gbc_pnlCharValues = new GridBagConstraints();
   private GridBagLayout gbl_pnlCharValues = new GridBagLayout();
   private JScrollPane scrollPane = new JScrollPane();
   private GridBagConstraints gbc_scrollPane = new GridBagConstraints();
   private JPanel panelCharVals = new JPanel();
   private GridBagLayout gbl_panelCharVals = new GridBagLayout();
   private GridBagConstraints gbc_panel_1 = new GridBagConstraints();
   private JPanel panel_1 = new JPanel();
   private JLabel lblNewLabel_8 = new JLabel();
   private GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
   private GridBagLayout gbl_panel_1 = new GridBagLayout();
   private JLabel lblNewLabel_9 = new JLabel();
   private GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
   private GridBagConstraints gbc_panel_2 = new GridBagConstraints();
   private GridBagLayout gbl_panel_2 = new GridBagLayout();
   private JPanel panel_2 = new JPanel();
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
   private MouseAdapter classApply;
   private String status;
   private int completedVals;
   private String allwnxt;

   private boolean classchange;
   private HashSet<String> classesTemp = new HashSet();
   private HashSet<String> classesStub = new HashSet();
   protected Clock clock2;
   private final JLabel lblNewLabel_13 = new JLabel("REVIEW MODE");
   
 
 
 
 
 
   public static void main(String[] args)
   {
     EventQueue.invokeLater(new Runnable() {
       public void run() {
         try {
           ReviewDetail localReviewDetail = new ReviewDetail(null, null, null, null, null);
         } catch (Exception e) {
           StringBuilder sb = new StringBuilder(e.toString());
           StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
             sb.append("\n\tat ");
             sb.append(ste);
           }
           JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
             0);
         }
       }
     });
   }
   
 
 
 
 
 
 
 
 
   public ReviewDetail(DLL dll, String selectedAID, String login, JOptionPane pane, Clock clock)
     throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException, SQLException
   {
     Date start;
     
 
 
 
 
 
 
     if (selectedAID != null) {
       this.clock2 = clock;
       this.completedVals = 0;
       this.allwnxt = "";
       
       try {
         SynthLookAndFeel laf = new SynthLookAndFeel();
         laf.load(ReviewDetail.class.getResourceAsStream("/ntdw/resources/detail_laf.xml"), ReviewDetail.class);
         UIManager.setLookAndFeel(laf);
       }
       catch (Exception e) {
         StringBuilder sb = new StringBuilder(e.toString());
         StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
           sb.append("\n\tat ");
           sb.append(ste);
         }
         JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
           0);
       }
       start = new Date();
       String ip = Tools.load_ip();
       Class.forName("org.postgresql.Driver");
       this.url = ("jdbc:postgresql://" + ip + ":5432/" + Tools.getDatabaseName());
       this.props.setProperty("user", "postgres");
       this.props.setProperty("password", "Neonec");
       this.props.setProperty("loginTimeout", "20");
       this.props.setProperty("connectTimeout", "0");
       this.props.setProperty("socketTimeout", "0");
       
 
 
       load_item_data(selectedAID, this.fedate);
       load_class(selectedAID);
       
       load_ui_elements(selectedAID);
       load_chars(this.rs.getString("cid"), Boolean.valueOf(false), selectedAID);
       
 
       load_static_data(selectedAID, pane);
       
       closing_procedure(login, pane, selectedAID, start);
       previous_procedure(login, selectedAID, clock, start, dll, pane);
       next_procedure(login, selectedAID, clock, dll, start, pane);
       this.lblNewLabel_4.setText("Classification: " + (String)this.currentFamily.get(this.textField_3.getText()));
     }
     else
     {
       pane.setVisible(false);
       dispose();
       ReviewList reviewlist = new ReviewList(login, clock);
     }
   }
   
 
 
   private void next_procedure(final String login, final String selectedAID, final Clock clock, final DLL dll, final Date start, final JOptionPane pane)
   {
     MouseListener rightaction = new MouseAdapter()
     {
       public void mouseClicked(MouseEvent e)
       {
         if (login.equals("Aparna")) {
           try {
             ReviewDetail localReviewDetail1 = new ReviewDetail(dll, dll.getnextID(selectedAID), login, pane, clock);
 
           }
           catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|ClassNotFoundException|IOException|SQLException e1)
           {
             e1.printStackTrace();
           }
           return;
         }
         if ((ReviewDetail.this.textField_5.getText() != null) && (ReviewDetail.this.textField_5.getText().replace(" ", "").length() == 0) && ((ReviewDetail.this.textArea_2.getText().toUpperCase().contains(" REF")) || (ReviewDetail.this.textArea_2.getText().toUpperCase().contains(" TYPE ")))) {
           String[] options = { "Write Ref.", "Ignore" };
           int response = JOptionPane.showOptionDialog(null, new JLabel(
             "<html><h2><font color='red'>Possible Ref. in description fields, Enter Manufacturer Reference?</font></h2></html>"), "Empty Manufacturer Ref.", 
             -1, -1, 
             null, options, options[0]);
           if (response == 0) {
             return;
           }
         }
         
 
 
 
         String allow = "";
         try {
           allow = ReviewDetail.this.allow_next(ReviewDetail.this.textField_6.getText(), (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), ReviewDetail.this.status);
         }
         catch (HeadlessException|SQLException e2) {
           e2.printStackTrace();
         }
         if (!allow.contains("REVIEWED")) {

           return;
         }
         
         if ((ReviewDetail.this.hasChangedNow((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()))) && (
           (ReviewDetail.this.textArea_4.getText() == null) || (ReviewDetail.this.textArea_4.getText().replace(" ", "").length() == 0))) {
           JOptionPane.showMessageDialog(null, new JLabel(
             "<html><h2><font color='red'>Class change detected, please write comment !</font></h2></html>"));
           return;
         }
         
 
         ReviewDetail.this.dispose();
         pane.setVisible(false);
         
         try
         {
           Class.forName("org.postgresql.Driver");
           String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
           
           ReviewDetail.this.props.setProperty("user", "postgres");
           ReviewDetail.this.props.setProperty("password", "Neonec");
           ReviewDetail.this.props.setProperty("loginTimeout", "20");
           ReviewDetail.this.props.setProperty("connectTimeout", "0");
           ReviewDetail.this.props.setProperty("socketTimeout", "0");
           
 
 
 
 
 
 
 
           Connection conn0 = DriverManager.getConnection(url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp,type) VALUES ( ?, ?, ?, ?,?) ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value, comp=EXCLUDED.comp;");
           
 
 
 
           ReviewDetail.this.save_chars(prepStmt, conn0, selectedAID, login, start, allow);
           
 
           conn0 = DriverManager.getConnection(url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           prepStmt = conn0.prepareStatement("INSERT INTO public.wal (aid, chid, value,phase, comp,time) VALUES (?,?, ?, ?, ?, ?)");
           
           ReviewDetail.this.save_chars(prepStmt, conn0, clock, selectedAID, login);
           
 
 
 
 
           ReviewDetail localReviewDetail2 = new ReviewDetail(dll, dll.getnextID(selectedAID), login, pane, clock);

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
     this.btnArrowRigth.addMouseListener(rightaction);
     this.panelRigth.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent arg0) {
         ReviewDetail.this.btnArrowRigth.doClick();
       }
       
       public void mouseEntered(MouseEvent e) {
         ReviewDetail.this.btnArrowRigth.setCursor(Cursor.getPredefinedCursor(12));
         ReviewDetail.this.btnArrowRigth.setToolTipText("Save & Load Next");
       }
     });
   }
   
   protected void log_progress(Connection conn0, String login, String selectedAID, Clock clock) throws SQLException {
     if (this.allwnxt.contains("REVIEWED")) {
       conn0 = DriverManager.getConnection(this.url, this.props);
       PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.progress (login,aid,work,phase,card,time) VALUES (?,?,?,?,?,?) on conflict (aid,phase,work) DO UPDATE set login=EXCLUDED.login, card=EXCLUDED.card");
       prepStmt.setString(1, login);
       prepStmt.setString(2, selectedAID);
       if (this.allwnxt.contains("REWORK")) {
         prepStmt.setString(3, "REWORK");
       } else {
         prepStmt.setString(3, "INITIAL");
       }
				prepStmt.setString(4, "REVIEW");
       prepStmt.setInt(5, this.completedVals);
       prepStmt.setTimestamp(6, Tools.maintenant(clock));
       
       prepStmt.execute();
       
       prepStmt.close();
       conn0.close();
     } else {

     }
   }
   
   protected void log_classif(Connection conn0, String login, String selectedAID, String cid, String target, Clock clock) throws SQLException
   {
     if (cid.equals(target)) {
       return;
     }
     conn0 = DriverManager.getConnection(this.url, this.props);
     PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.reclassifs (login,aid,original,target,nature,work,phase,type,time) VALUES(?,?,?,?,?,?,?,?,?)");
     prepStmt.setString(1, login);
     prepStmt.setString(2, selectedAID);
     prepStmt.setString(3, cid);
     prepStmt.setString(4, target);
     if (isEphem(target)) {
       prepStmt.setString(5, "TEMP");
     }
     else if (isStub(cid)) {
       prepStmt.setString(5, "STUB");
     } else {
       prepStmt.setString(5, "REEL");
     }
     
     if (this.allwnxt.contains("REWORK")) {
       prepStmt.setString(6, "REWORK");
     } else {
       prepStmt.setString(6, "INITIAL");
     }
     prepStmt.setString(7, "REVIEW");
     if (!isEphem(cid)) {
       if (!isEphem(target)) {
         prepStmt.setString(8, "I");
       } else {
         prepStmt.setString(8, "II");
       }
     }
     else if (!isEphem(target)) {
       prepStmt.setString(8, "III");
     } else {
       prepStmt.setString(8, "II");
     }
     
 
     prepStmt.setTimestamp(9, Tools.maintenant(clock));
     
     prepStmt.execute();
     
     prepStmt.close();
     conn0.close();
   }
   
   private void previous_procedure(final String login, final String selectedAID, final Clock clock, final Date start, final DLL dll, final JOptionPane pane) {
     ActionListener actionLeft = new ActionListener()
     {
       public void actionPerformed(ActionEvent e) {
         if (login.equals("Aparna")) {
           try {
             ReviewDetail localReviewDetail1 = new ReviewDetail(dll, dll.getprevID(selectedAID), login, pane, clock);
 
           }
           catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|ClassNotFoundException|IOException|SQLException e1)
           {
             e1.printStackTrace();
           }
           return;
         }
         
         if ((ReviewDetail.this.textField_5.getText() != null) && (ReviewDetail.this.textField_5.getText().replace(" ", "").length() == 0) && ((ReviewDetail.this.textArea_2.getText().toUpperCase().contains(" REF")) || (ReviewDetail.this.textArea_2.getText().toUpperCase().contains(" TYPE ")))) {
           String[] options = { "Write Ref.", "Ignore" };
           int response = JOptionPane.showOptionDialog(null, new JLabel(
             "<html><h2><font color='red'>Possible Ref. in description fields, Enter Manufacturer Reference?</font></h2></html>"), "Empty Manufacturer Ref.", 
             -1, -1, 
             null, options, options[0]);
           if (response == 0) {
             return;
           }
         }
         
 
 
 
 
 
 
         String allow = "";
         try {
           allow = ReviewDetail.this.allow_next(ReviewDetail.this.textField_6.getText(), (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), ReviewDetail.this.status);
         }
         catch (HeadlessException|SQLException e2) {
           e2.printStackTrace();
         }
         if (!allow.contains("REVIEWED")) {

           return;
         }
         if ((ReviewDetail.this.hasChangedNow((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()))) && (
           (ReviewDetail.this.textArea_4.getText() == null) || (ReviewDetail.this.textArea_4.getText().replace(" ", "").length() == 0))) {
           JOptionPane.showMessageDialog(null, new JLabel(
             "<html><h2><font color='red'>Class change detected, please write comment !</font></h2></html>"));
           return;
         }
         
 
 
 
         ReviewDetail.this.dispose();
         pane.setVisible(false);
         try
         {
           Class.forName("org.postgresql.Driver");
           String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
           ReviewDetail.this.props.setProperty("user", "postgres");
           ReviewDetail.this.props.setProperty("password", "Neonec");
           
           ReviewDetail.this.props.setProperty("loginTimeout", "20");
           ReviewDetail.this.props.setProperty("connectTimeout", "0");
           ReviewDetail.this.props.setProperty("socketTimeout", "0");
           
 
 
 
 
           Connection conn0 = DriverManager.getConnection(url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp,type) VALUES (?, ?, ?, ?,?) ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value,comp=EXCLUDED.comp;");
           
 
           ReviewDetail.this.save_chars(prepStmt, conn0, selectedAID, login, start, allow);
           
 
 
           conn0 = DriverManager.getConnection(url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           prepStmt = conn0.prepareStatement("INSERT INTO public.wal (aid, chid, value,phase, comp,time) VALUES (?, ?, ?, ?, ?,?)");
           
           ReviewDetail.this.save_chars(prepStmt, conn0, clock, selectedAID, login);
           
           ReviewDetail localReviewDetail2 = new ReviewDetail(dll, dll.getprevID(selectedAID), login, pane, clock);

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
     this.btnArrowLeft.addActionListener(actionLeft);
     this.pnl_controlflex.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent arg0) {
         ReviewDetail.this.btnArrowLeft.doClick();
       }
       
       public void mouseEntered(MouseEvent e) {
         ReviewDetail.this.btnArrowLeft.setCursor(Cursor.getPredefinedCursor(12));
         ReviewDetail.this.btnArrowLeft.setToolTipText("Save & Load Previous");
       }
     });
   }
   
 
 
 
 
 
 
 
 
 
   private void closing_procedure(final String login, final JOptionPane pane, final String selectedAID, final Date start)
   {
     addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent event) {
         pane.setVisible(false);
         ReviewDetail.this.dispose();
         if (login.equals("Aparna")) {
           Home home = new Home(login, ReviewDetail.this.clock2);
           return;
         }
         try {
           Class.forName("org.postgresql.Driver");
           ReviewDetail.this.url = ("jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName());
           ReviewDetail.this.props.setProperty("user", "postgres");
           ReviewDetail.this.props.setProperty("password", "Neonec");
           ReviewDetail.this.props.setProperty("loginTimeout", "20");
           ReviewDetail.this.props.setProperty("connectTimeout", "0");
           ReviewDetail.this.props.setProperty("socketTimeout", "0");
           
 
           if ((ReviewDetail.this.hasChangedNow((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()))) && (
             (ReviewDetail.this.textArea_4.getText() == null) || (ReviewDetail.this.textArea_4.getText().replace(" ", "").length() == 0))) {
             JOptionPane.showMessageDialog(null, new JLabel(
               "<html><h2><font color='red'>WARNING: Data not Saved : Class change detected & No comment written.</font></h2></html>"));
             return;
           }
           
 
 
 
 
 
 
           Connection conn0 = DriverManager.getConnection(ReviewDetail.this.url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.data (aid, chid, value, comp,type) VALUES (?, ?, ?, ?, ?) ON CONFLICT (aid,chid) DO UPDATE SET value=EXCLUDED.value,comp=EXCLUDED.comp;");
           
 
           String allow = ReviewDetail.this.allow_next(ReviewDetail.this.textField_6.getText(), (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), ReviewDetail.this.status);
           if (!allow.contains("REVIEWED")) {

             Home home = new Home(login, ReviewDetail.this.clock2);
             return;
           }
           ReviewDetail.this.save_chars(prepStmt, conn0, selectedAID, login, start, allow);
           
 
           conn0 = DriverManager.getConnection(ReviewDetail.this.url, ReviewDetail.this.props);
           conn0.setAutoCommit(false);
           prepStmt = conn0.prepareStatement("INSERT INTO public.wal (aid, chid, value,phase, comp,time) VALUES (?,?, ?, ?, ?, ?)");
           
           ReviewDetail.this.save_chars(prepStmt, conn0, ReviewDetail.this.clock2, selectedAID, login);
           Home home = new Home(login, ReviewDetail.this.clock2);
 
 
 
 

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

	// TODO Auto-generated catch block

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



}

});

}
   
   private void save_chars(PreparedStatement prepStmt, Connection conn0, String selectedAID, String login, Date start, String allow) throws SQLException
   {
     if (this.modelLeft != null) {
       for (CharacValue element : this.modelLeft) {
         prepStmt.setString(1, selectedAID);
         prepStmt.setString(2, element.getId());
         prepStmt.setString(3, element.getvalue());
         
         prepStmt.setString(4, element.getComp());
         prepStmt.setString(5, element.getType().name());
         prepStmt.addBatch();
       }
       for (CharacValue element : this.modelRight) {
         prepStmt.setString(1, selectedAID);
         prepStmt.setString(2, element.getId());
         prepStmt.setString(3, element.getvalue());
         
         prepStmt.setString(4, element.getComp());
         prepStmt.setString(5, element.getType().name());
         prepStmt.addBatch();
       }
     }
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "MANUF");
     prepStmt.setString(3, this.textField_4.getText());
     
     prepStmt.setString(4, null);
     prepStmt.setString(5, null);
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "MANUF_REF");
     prepStmt.setString(3, this.textField_5.getText());
     
     prepStmt.setString(4, null);
     prepStmt.setString(5, null);
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "URL");
     prepStmt.setString(3, this.textArea_3.getText());
     
     prepStmt.setString(4, null);
     prepStmt.setString(5, null);
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "QUESTION");
     prepStmt.setString(3, this.textField_6.getText());
     
     prepStmt.setString(4, null);
     prepStmt.setString(5, null);
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "COMMENT");
     prepStmt.setString(3, this.textArea_4.getText());
     
     prepStmt.setString(4, null);
     prepStmt.setString(5, null);
     prepStmt.addBatch();
     
     prepStmt.executeBatch();
     conn0.commit();
     prepStmt.clearBatch();
     prepStmt.close();
     conn0.close();
     conn0 = DriverManager.getConnection(this.url, this.props);
     Date end = new Date();
     if (this.fedate != null) {
       prepStmt = conn0.prepareStatement("UPDATE public.items SET status='" + allow + "', cid= ?, \"classChange\"=?, \"ques\" = ?, timetaken = timetaken + ?, ledate = ? WHERE aid = ?");
       prepStmt.setString(1, (String)this.currentFamily.get(this.textField_3.getText()));
       
       prepStmt.setBoolean(2, hasChanged((String)this.currentFamily.get(this.textField_3.getText())));
       if (this.textField_6.getText().length() >= 5) {
         prepStmt.setString(3, this.textField_6.getText().substring(0, 5));
       } else {
         prepStmt.setString(3, this.textField_6.getText());
       }
       prepStmt.setString(6, selectedAID);
       prepStmt.setObject(5, LocalDate.now());
       prepStmt.setDouble(4, Double.valueOf((int)((end.getTime() - start.getTime()) / 1000L)).doubleValue());
     }
     else {
       prepStmt = conn0.prepareStatement("UPDATE public.items SET status='" + allow + "', cid= ?, \"classChange\"=?, \"ques\" = ?, timetaken = timetaken + ?, ledate = ? , fedate = ? WHERE aid = ?");
       prepStmt.setString(1, (String)this.currentFamily.get(this.textField_3.getText()));
       
       prepStmt.setBoolean(2, hasChanged((String)this.currentFamily.get(this.textField_3.getText())));
       if (this.textField_6.getText().length() >= 5) {
         prepStmt.setString(3, this.textField_6.getText().substring(0, 5));
       } else {
         prepStmt.setString(3, this.textField_6.getText());
       }
       prepStmt.setString(7, selectedAID);
       prepStmt.setObject(5, LocalDate.now());
       prepStmt.setObject(6, LocalDate.now());
       prepStmt.setDouble(4, Double.valueOf((int)((end.getTime() - start.getTime()) / 1000L)).doubleValue());
     }
     prepStmt.execute();
     
     prepStmt.close();
     conn0.close();
   }
   
   private void save_chars(PreparedStatement prepStmt, Connection conn0, Clock clock, String selectedAID, String login) throws SQLException { if (this.modelLeft != null) {
       for (CharacValue element : this.modelLeft) {
         prepStmt.setString(1, selectedAID);
         prepStmt.setString(2, element.getId());
         prepStmt.setString(3, element.getvalue());
         prepStmt.setString(4, "REVIEW");
         prepStmt.setString(5, element.getComp());
         prepStmt.setTimestamp(6, Tools.maintenant(clock));
         prepStmt.addBatch();
       }
       for (CharacValue element : this.modelRight) {
         prepStmt.setString(1, selectedAID);
         prepStmt.setString(2, element.getId());
         prepStmt.setString(3, element.getvalue());
         prepStmt.setString(4, "REVIEW");
         prepStmt.setString(5, element.getComp());
         prepStmt.setTimestamp(6, Tools.maintenant(clock));
         prepStmt.addBatch();
       }
     }
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "MANUF");
     prepStmt.setString(3, this.textField_4.getText());
     prepStmt.setString(4, "REVIEW");
     prepStmt.setString(5, null);
     prepStmt.setTimestamp(6, Tools.maintenant(clock));
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "MANUF_REF");
     prepStmt.setString(3, this.textField_5.getText());
     prepStmt.setString(4, "REVIEW");
     prepStmt.setString(5, null);
     prepStmt.setTimestamp(6, Tools.maintenant(clock));
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "URL");
     prepStmt.setString(3, this.textArea_3.getText());
     prepStmt.setString(4, "REVIEW");
     prepStmt.setString(5, null);
     prepStmt.setTimestamp(6, Tools.maintenant(clock));
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "QUESTION");
     prepStmt.setString(3, this.textField_6.getText());
     prepStmt.setString(4, "REVIEW");
     prepStmt.setString(5, null);
     prepStmt.setTimestamp(6, Tools.maintenant(clock));
     prepStmt.addBatch();
     
     prepStmt.setString(1, selectedAID);
     prepStmt.setString(2, "COMMENT");
     prepStmt.setString(3, this.textArea_4.getText());
     prepStmt.setString(4, "REVIEW");
     prepStmt.setString(5, null);
     prepStmt.setTimestamp(6, Tools.maintenant(clock));
     prepStmt.addBatch();
     
     prepStmt.executeBatch();
     conn0.commit();
     prepStmt.clearBatch();
     prepStmt.close();
     conn0.close();
     
     log_classif(conn0, login, selectedAID, this.originalcid, (String)this.currentFamily.get(this.textField_3.getText()), clock);
     log_progress(conn0, login, selectedAID, clock);
   }
   
 
   private void load_class(final String selectedAID)
     throws SQLException
   {
     final MouseAdapter classCancel = new MouseAdapter()
     {
       public void mouseClicked(MouseEvent e)
       {
         ReviewDetail.this.textField.setText(ReviewDetail.this.originalDomain);
         
         ReviewDetail.this.textField_1.setText(ReviewDetail.this.originalGroup);
         
         ReviewDetail.this.textField_2.setText(ReviewDetail.this.originalfamily);
         
         ReviewDetail.this.textField_3.setText(ReviewDetail.this.originalclass);
         ReviewDetail.this.currentFamily.clear();
         ReviewDetail.this.currentFamily.put(ReviewDetail.this.originalclass, ReviewDetail.this.originalcid);
         ReviewDetail.this.lblNewLabel_4.setText("Classification: " + (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()));
         try {
           ReviewDetail.this.load_chars((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), Boolean.valueOf(true), selectedAID);
 
         }
         catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|ClassNotFoundException|SQLException|IOException e1)
         {
           e1.printStackTrace();
         }
       }
     };
     final Runnable cancelClass = new Runnable()
     {
       public void run() {
         classCancel.mouseClicked(null);
       }
     };
     this.btnApplyCancel.addMouseListener(classCancel);
     this.textField_3.getDocument().addDocumentListener(new DocumentListener()
     {
       public void changedUpdate(DocumentEvent e) {}
       
 
       public void insertUpdate(DocumentEvent e)
       {
         if (!ReviewDetail.this.viewbutton) {
           ReviewDetail.this.btnApplyClassification.setVisible(false);
         }
         if (ReviewDetail.this.textField_3.getText().split("\\.").length != 4) {
           return;
         }
         if (ReviewDetail.this.textField_3.getText().split("\\.")[3].length() < 3) {
           return;
         }
         try
         {
           final Connection conn9 = DriverManager.getConnection(ReviewDetail.this.url, ReviewDetail.this.props);
           final PreparedStatement st9 = conn9.prepareStatement("select * from public.\"class_hierarchy\" WHERE \"cid\" = ?");
           String cidlook = ReviewDetail.this.textField_3.getText().substring(0, 15);
           
           st9.setString(1, cidlook);
           
           final ResultSet rs9 = st9.executeQuery();
           if (!rs9.isBeforeFirst()) {
             JOptionPane.showMessageDialog(ReviewDetail.this.textField_3, "<html><h2><font color='red'>Unknown Class ID, Canceling changes</font></h2></html>");
             
 
 
             rs9.close();
             st9.close();
             conn9.close();
             SwingUtilities.invokeLater(cancelClass);
             return;
           }
           rs9.next();
           ReviewDetail.this.textField.setText(rs9.getString("domain"));
           ReviewDetail.this.textField_1.setText(rs9.getString("groupe"));
           ReviewDetail.this.textField_2.setText(rs9.getString("family"));
           Runnable doHighlight = new Runnable()
           {
             public void run() {
               try {
                 String claas = rs9.getString("class");
                 ReviewDetail.this.currentFamily.clear();
                 
                 ReviewDetail.this.currentFamily.put(claas, rs9.getString("cid"));
                 
                 ReviewDetail.this.textField_3.setText(claas);
                 rs9.close();
                 st9.close();
                 conn9.close();
                 ReviewDetail.this.viewbutton = true;
                 ReviewDetail.this.btnApplyClassification.setVisible(false);
                 ReviewDetail.this.lblNewLabel_4.setText("Classification: " + (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()));
                 ReviewDetail.this.load_chars((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), Boolean.valueOf(true), selectedAID);
               }
               catch (SQLException|InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|ClassNotFoundException|IOException e)
               {
                 e.printStackTrace();
               }
             }
           };
           SwingUtilities.invokeLater(doHighlight);
 
         }
         catch (Exception e1)
         {
           e1.printStackTrace();
         }
       }
       
       public void removeUpdate(DocumentEvent e)
       {
         ReviewDetail.this.btnApplyClassification.setVisible(false);
 
 
 
       }
       
 
 
 
 
     });
     this.conn1 = DriverManager.getConnection(this.url, this.props);
     PreparedStatement st1 = this.conn1.prepareStatement("select * from public.\"class_hierarchy\" WHERE \"cid\" = ?");
     st1.setString(1, this.rs.getString("cid"));
     ResultSet rs1 = st1.executeQuery();
     rs1.next();
     this.originalcid = this.rs.getString("cid");
     this.classchange = this.rs.getBoolean("classChange");
     this.textField.setText(rs1.getString("domain"));
     this.originalDomain = rs1.getString("domain");
     
     this.textField_1.setText(rs1.getString("groupe"));
     this.originalGroup = rs1.getString("groupe");
     
     this.textField_2.setText(rs1.getString("family"));
     this.originalfamily = rs1.getString("family");
     
     this.textField_3.setText(rs1.getString("class"));
     this.originalclass = rs1.getString("class");
     
     rs1.close();
     st1.close();
     this.conn1.close();
   }
   
   private boolean hasChanged(String currentCID) {
     if (this.classchange) {
       return true;
     }
     if (!currentCID.equals(this.originalcid)) {
       return true;
     }
     return false;
   }
   
   private boolean hasChangedNow(String currentCID) { if (!currentCID.equals(this.originalcid)) {
       return true;
     }
     
     return false;
   }
   
 
 
   private void load_ui_elements(final String selectedAID)
     throws SQLException
   {
     setTitle("TECHNICAL ITEM REVIEW");
     setIconImage(Toolkit.getDefaultToolkit().getImage(ReviewDetail.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
     setDefaultCloseOperation(0);
     
     setBounds(0, 0, 0, 0);
     
     this.contentPane.setName("contentPane");
     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
     setContentPane(this.contentPane);
     this.gbl_contentPane.columnWidths = new int[] { 133, 133, 21, 300, 99 };
     this.gbl_contentPane.rowHeights = new int[5];
     this.gbl_contentPane.columnWeights = new double[] { 0.0D, 1.0D, 1.0D };
     this.gbl_contentPane.rowWeights = new double[] { 0.0D, 0.2D, 0.0D, 1.0D, Double.MIN_VALUE };
     this.contentPane.setLayout(this.gbl_contentPane);
     
     Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
     this.height = area.height;
     this.width = area.width;
     setPreferredSize(new Dimension(this.width, this.height));
     
     this.pnl_controlflex.setBackground(Color.CYAN);
     this.pnl_controlflex.setName("pnl_controlflex");
     this.pnl_controlflex.setForeground(SystemColor.activeCaption);
     this.gbc_pnl_controlflex.fill = 1;
     this.gbc_pnl_controlflex.insets = new Insets(0, 0, 5, 0);
     this.gbc_pnl_controlflex.gridx = 0;
     this.gbc_pnl_controlflex.gridy = 0;
     this.gbc_pnl_controlflex.gridwidth = 1;
     this.contentPane.add(this.pnl_controlflex, this.gbc_pnl_controlflex);
     
     this.btnArrowLeft.setText("        ");
     this.btnArrowLeft.setHorizontalAlignment(2);
     
 
     this.btnArrowLeft.setActionCommand("");
     this.btnArrowLeft.setSize(new Dimension((int)(100.0D * (area.width / 1366.0D)), 50));
     this.btnArrowLeft.setName("btnArrowLeft");
     
 
     this.lblNewLabel.setName("lb_controlflex");
     this.pnl_controlflex.setLayout(new FlowLayout(1, 5, 5));
     this.pnl_controlflex.add(this.btnArrowLeft);
     this.pnl_controlflex.add(this.lblNewLabel);
     
     this.panelCenter.setBackground(Color.CYAN);
     this.panelCenter.setName("panelCenter");
     
     this.gbc_panelCenter.insets = new Insets(0, 0, 5, 0);
     this.gbc_panelCenter.fill = 1;
     this.gbc_panelCenter.gridx = 1;
     this.gbc_panelCenter.gridy = 0;
     this.gbc_panelCenter.gridwidth = 3;
     this.contentPane.add(this.panelCenter, this.gbc_panelCenter);
     
 
     this.lblVal.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent e) {
         StringSelection stringSelection = new StringSelection(selectedAID + " / " + (String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()));
         Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
         clpbrd.setContents(stringSelection, null);
       }
     });
     this.lblNewLabel_13.setForeground(Color.RED);
     
     this.panelCenter.add(this.lblNewLabel_13);
     this.panelCenter.add(this.lblVal);
     this.lblVal.setName("lbl_val");
     this.lblVal.setText(selectedAID);
     
 
     this.panelRigth.setBackground(Color.CYAN);
     this.panelRigth.setName("panelRigth");
     
     this.gbc_panelRigth.insets = new Insets(0, 0, 5, 0);
     this.gbc_panelRigth.anchor = 11;
     this.gbc_panelRigth.fill = 2;
     this.gbc_panelRigth.gridx = 4;
     this.gbc_panelRigth.gridy = 0;
     this.contentPane.add(this.panelRigth, this.gbc_panelRigth);
     
     this.lblFlexor.setName("lbl_flexor");
     this.panelRigth.setLayout(new FlowLayout(1, 5, 5));
     this.panelRigth.add(this.lblFlexor);
     
     this.btnArrowRigth.setText("        ");
     this.btnArrowRigth.setSize(new Dimension((int)(100.0D * (area.width / 1366.0D)), 100));
     this.btnArrowRigth.setName("btnArrowRight");
     this.panelRigth.add(this.btnArrowRigth);
     
     this.pnlDescription.setName("shortDescription");
     this.gbc_pnlDescription.insets = new Insets(0, 0, 5, 0);
     this.gbc_pnlDescription.fill = 1;
     this.gbc_pnlDescription.gridx = 0;
     this.gbc_pnlDescription.gridy = 1;
     this.gbc_pnlDescription.gridwidth = 5;
     this.contentPane.add(this.pnlDescription, this.gbc_pnlDescription);
     
     this.gbl_pnlDescription.columnWidths = new int[2];
     this.gbl_pnlDescription.rowHeights = new int[2];
     this.gbl_pnlDescription.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
     this.gbl_pnlDescription.rowWeights = new double[] { 0.0D, 1.0D };
     this.pnlDescription.setLayout(this.gbl_pnlDescription);
     
     this.gbc_panel.insets = new Insets(0, 0, 5, 0);
     this.gbc_panel.fill = 1;
     this.gbc_panel.gridx = 0;
     this.gbc_panel.gridy = 0;
     this.pnlDescription.add(this.panel, this.gbc_panel);
     
 
     this.lblNewLabel_1.setText("Short description");
     this.lblNewLabel_1.setFont(new Font("Calibri", 1, 16));
     
     this.textArea.setSelectionColor(Color.LIGHT_GRAY);
     this.lblNewLabel_2.setText("Long description");
     this.lblNewLabel_2.setFont(new Font("Calibri", 1, 16));
     
     this.textArea_1.setSelectionColor(Color.LIGHT_GRAY);
     
     this.lblNewLabel_3.setText("PO text");
     this.lblNewLabel_3.setFont(new Font("Calibri", 1, 16));
     
     this.btnApplyClassification.setText("APPLY CLASSIFICATION CHANGE");
     
     GroupLayout gl_panel = new GroupLayout(panel);

textArea_2.setSelectionColor(Color.LIGHT_GRAY);

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
     
 
 
     this.pnlClassification.setName("pnlClassification");
     
     this.gbc_pnlClassification.gridheight = 3;
     this.gbc_pnlClassification.insets = new Insets(0, 0, 0, 5);
     this.gbc_pnlClassification.fill = 1;
     this.gbc_pnlClassification.gridx = 0;
     this.gbc_pnlClassification.gridy = 2;
     this.contentPane.add(this.pnlClassification, this.gbc_pnlClassification);
     
     this.gbl_pnlClassification.columnWidths = new int[2];
     this.gbl_pnlClassification.rowHeights = new int[2];
     this.gbl_pnlClassification.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
     this.gbl_pnlClassification.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D };
     this.pnlClassification.setLayout(this.gbl_pnlClassification);
     
 
     this.lblNewLabel_4.setFont(new Font("Calibri", 1, 16));
     
     this.gbc_lblNewLabel_4.fill = 2;
     this.gbc_lblNewLabel_4.anchor = 11;
     this.gbc_lblNewLabel_4.insets = new Insets(0, 15, 5, 0);
     this.gbc_lblNewLabel_4.gridx = 0;
     this.gbc_lblNewLabel_4.gridy = 0;
     this.pnlClassification.add(this.lblNewLabel_4, this.gbc_lblNewLabel_4);
     
     this.textField.setEditable(false);
     
 
     this.gbc_textField.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField.fill = 2;
     this.gbc_textField.gridx = 0;
     this.gbc_textField.gridy = 1;
     this.pnlClassification.add(this.textField, this.gbc_textField);
     
 
     this.textField_1.setEditable(false);
     
 
     this.gbc_textField_1.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField_1.fill = 2;
     this.gbc_textField_1.gridx = 0;
     this.gbc_textField_1.gridy = 2;
     this.pnlClassification.add(this.textField_1, this.gbc_textField_1);
     
 
     this.textField_2.setEditable(false);
     
 
     this.gbc_textField_2.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField_2.fill = 2;
     this.gbc_textField_2.gridx = 0;
     this.gbc_textField_2.gridy = 3;
     this.pnlClassification.add(this.textField_2, this.gbc_textField_2);
     
 
 
 
     this.gbc_textField_3.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField_3.fill = 2;
     this.gbc_textField_3.gridx = 0;
     this.gbc_textField_3.gridy = 4;
     this.pnlClassification.add(this.textField_3, this.gbc_textField_3);
     
 
 
 
 
 
 
     this.classApply = new MouseAdapter()
     {
       public void mouseClicked(MouseEvent e) {
         try {
           ReviewDetail.this.load_chars((String)ReviewDetail.this.currentFamily.get(ReviewDetail.this.textField_3.getText()), Boolean.valueOf(true), selectedAID);
         }
         catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|ClassNotFoundException|SQLException|IOException e1)
         {
           StringBuilder sb = new StringBuilder(e1.toString());
           StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e1.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
             sb.append("\n\tat ");
             sb.append(ste);
           }
           JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
             0);
         }
       }
     };
     this.btnApplyClassification.addMouseListener(this.classApply);
     this.btnApplyClassification.setFont(new Font("Calibri", 0, 12));
     this.btnApplyClassification.setName("btnApplyClassification");
     
     this.gbc_btnApplyClassification.insets = new Insets(0, 6, 5, 0);
     this.gbc_btnApplyClassification.fill = 1;
     this.gbc_btnApplyClassification.gridx = 0;
     this.gbc_btnApplyClassification.gridy = 5;
     this.pnlClassification.add(this.btnApplyClassification, this.gbc_btnApplyClassification);
     
     this.btnApplyCancel.setText("CANCEL CLASSIFICATION CHANGE");
     this.btnApplyCancel.setFont(new Font("Calibri", 0, 12));
     this.btnApplyCancel.setName("btnCancelClassification");
     
     this.gbc_btnApplyCancel.insets = new Insets(0, 6, 5, 0);
     this.gbc_btnApplyCancel.fill = 1;
     this.gbc_btnApplyCancel.gridx = 0;
     this.gbc_btnApplyCancel.gridy = 6;
     this.pnlClassification.add(this.btnApplyCancel, this.gbc_btnApplyCancel);
     
     this.lblNewLabel_5.setText("Manufacturer name");
     this.lblNewLabel_5.setFont(new Font("Calibri", 1, 16));
     
     this.gbc_lblNewLabel_5.fill = 2;
     this.gbc_lblNewLabel_5.insets = new Insets(0, 15, 0, 0);
     this.gbc_lblNewLabel_5.gridx = 0;
     this.gbc_lblNewLabel_5.gridy = 7;
     this.pnlClassification.add(this.lblNewLabel_5, this.gbc_lblNewLabel_5);
     
 
     this.textField_4.setSelectionColor(Color.LIGHT_GRAY);
     
     this.gbc_textField_4.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField_4.fill = 2;
     this.gbc_textField_4.gridx = 0;
     this.gbc_textField_4.gridy = 8;
     this.pnlClassification.add(this.textField_4, this.gbc_textField_4);
     this.textField_4.setColumns(10);
     
     this.lblNewLabel_6.setText("Manufacturer reference");
     this.lblNewLabel_6.setFont(new Font("Calibri", 1, 16));
     
     this.gbc_lblNewLabel_6.fill = 2;
     this.gbc_lblNewLabel_6.insets = new Insets(0, 15, 0, 0);
     this.gbc_lblNewLabel_6.gridx = 0;
     this.gbc_lblNewLabel_6.gridy = 9;
     this.pnlClassification.add(this.lblNewLabel_6, this.gbc_lblNewLabel_6);
     
 
     this.textField_5.setSelectionColor(Color.LIGHT_GRAY);
     
     this.gbc_textField_5.insets = new Insets(0, 6, 5, 0);
     this.gbc_textField_5.fill = 2;
     this.gbc_textField_5.gridx = 0;
     this.gbc_textField_5.gridy = 10;
     this.pnlClassification.add(this.textField_5, this.gbc_textField_5);
     this.textField_5.setColumns(10);
     
 
     this.lblNewLabel_7.setText("Source URL");
     this.lblNewLabel_7.setFont(new Font("Calibri", 1, 16));
     
     this.gbc_lblNewLabel_7.fill = 2;
     this.gbc_lblNewLabel_7.insets = new Insets(0, 15, 0, 0);
     this.gbc_lblNewLabel_7.gridx = 0;
     this.gbc_lblNewLabel_7.gridy = 11;
     this.pnlClassification.add(this.lblNewLabel_7, this.gbc_lblNewLabel_7);
     
     this.textArea_3.setSelectionColor(Color.LIGHT_GRAY);
     
     this.gbc_textArea_3.insets = new Insets(0, 6, 5, 0);
     this.gbc_textArea_3.fill = 1;
     this.gbc_textArea_3.gridx = 0;
     this.gbc_textArea_3.gridy = 12;
     this.pnlClassification.add(this.textArea_3, this.gbc_textArea_3);
     
     this.pnlCharValues.setName("pnlCharValues");
     
     this.gbc_pnlCharValues.gridheight = 3;
     this.gbc_pnlCharValues.insets = new Insets(0, 0, 5, 5);
     this.gbc_pnlCharValues.fill = 1;
     this.gbc_pnlCharValues.gridx = 1;
     this.gbc_pnlCharValues.gridy = 2;
     this.gbc_pnlCharValues.gridwidth = 4;
     this.contentPane.add(this.pnlCharValues, this.gbc_pnlCharValues);
     
     this.gbl_pnlCharValues.columnWidths = new int[1];
     this.gbl_pnlCharValues.rowHeights = new int[2];
     this.gbl_pnlCharValues.columnWeights = new double[] { 1.0D };
     this.gbl_pnlCharValues.rowWeights = new double[] { 1.0D, 0.2D };
     this.pnlCharValues.setLayout(this.gbl_pnlCharValues);
     
 
     this.gbc_scrollPane.fill = 1;
     this.gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
     this.gbc_scrollPane.gridx = 0;
     this.gbc_scrollPane.gridy = 0;
     this.pnlCharValues.add(this.scrollPane, this.gbc_scrollPane);
     
 
     this.scrollPane.setViewportView(this.panelCharVals);
     this.panelCharVals.setName("panelCharVals");
     
     this.gbl_panelCharVals.columnWidths = new int[2];
     this.gbl_panelCharVals.rowHeights = new int[2];
     this.gbl_panelCharVals.columnWeights = new double[] { 1.0D, 1.0D };
     this.gbl_panelCharVals.rowWeights = new double[] { 1.0D, 1.0D };
     this.panelCharVals.setLayout(this.gbl_panelCharVals);
     
 
 
     this.gbc_panel_1.insets = new Insets(0, 0, 5, 5);
     this.gbc_panel_1.fill = 1;
     this.gbc_panel_1.gridx = 0;
     this.gbc_panel_1.gridy = 0;
     this.panelCharVals.add(this.panel_1, this.gbc_panel_1);
     
     this.gbl_panel_1.columnWidths = new int[] { 350 };
     this.gbl_panel_1.rowHeights = new int[] { 16 };
     this.gbl_panel_1.columnWeights = new double[] { 0.0D, Double.MIN_VALUE };
     this.gbl_panel_1.rowWeights = new double[] { 0.0D, 0.0D, Double.MIN_VALUE };
     this.panel_1.setLayout(this.gbl_panel_1);
     
 
     this.lblNewLabel_8.setText("Characteristic Values");
     this.lblNewLabel_8.setForeground(new Color(68, 84, 105));
     this.lblNewLabel_8.setFont(new Font("Calibri", 1, 12));
     
     this.gbc_lblNewLabel_8.insets = new Insets(0, 62, 5, 0);
     this.gbc_lblNewLabel_8.anchor = 18;
     this.gbc_lblNewLabel_8.gridx = 0;
     this.gbc_lblNewLabel_8.gridy = 0;
     this.panel_1.add(this.lblNewLabel_8, this.gbc_lblNewLabel_8);
     
 
     this.lblNewLabel_9.setText("Value complement");
     
     this.lblNewLabel_9.setForeground(new Color(68, 84, 105));
     this.lblNewLabel_9.setFont(new Font("Calibri", 1, 12));
     this.gbc_lblNewLabel_9.anchor = 11;
     this.gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
     this.gbc_lblNewLabel_9.gridx = 1;
     this.gbc_lblNewLabel_9.gridy = 0;
     this.panel_1.add(this.lblNewLabel_9, this.gbc_lblNewLabel_9);
     
 
 
     this.gbc_panel_2.insets = new Insets(0, 0, 5, 5);
     this.gbc_panel_2.fill = 1;
     this.gbc_panel_2.gridx = 1;
     this.gbc_panel_2.gridy = 0;
     this.panelCharVals.add(this.panel_2, this.gbc_panel_2);
     
     this.gbl_panel_2.columnWidths = new int[] { 350 };
     this.gbl_panel_2.rowHeights = new int[] { 16 };
     this.gbl_panel_2.columnWeights = new double[] { 0.0D, Double.MIN_VALUE };
     this.gbl_panel_2.rowWeights = new double[] { 0.0D, 0.0D, Double.MIN_VALUE };
     this.panel_2.setLayout(this.gbl_panel_2);
     
 
     this.lblNewLabel_10.setText("Characteristic Values");
     this.lblNewLabel_10.setForeground(new Color(68, 84, 105));
     this.lblNewLabel_10.setFont(new Font("Calibri", 1, 12));
     
     this.gbc_lblNewLabel_10.insets = new Insets(0, 62, 5, 0);
     this.gbc_lblNewLabel_10.anchor = 18;
     this.gbc_lblNewLabel_10.gridx = 0;
     this.gbc_lblNewLabel_10.gridy = 0;
     this.panel_2.add(this.lblNewLabel_10, this.gbc_lblNewLabel_10);
     
 
     this.lblNewLabel_12.setText("Value complement");
     this.lblNewLabel_12.setForeground(new Color(68, 84, 105));
     this.lblNewLabel_12.setFont(new Font("Calibri", 1, 12));
     
     this.gbc_lblNewLabel_12.anchor = 11;
     this.gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
     this.gbc_lblNewLabel_12.gridx = 1;
     this.gbc_lblNewLabel_12.gridy = 0;
     this.panel_2.add(this.lblNewLabel_12, this.gbc_lblNewLabel_12);
     
     this.pnlCharacLeft.setName("pnlCharacLeft");
     
     this.gbc_pnlCharacLeft.anchor = 11;
     this.gbc_pnlCharacLeft.insets = new Insets(0, 0, 0, 5);
     this.gbc_pnlCharacLeft.fill = 2;
     this.gbc_pnlCharacLeft.gridx = 0;
     this.gbc_pnlCharacLeft.gridy = 1;
     this.panelCharVals.add(this.pnlCharacLeft, this.gbc_pnlCharacLeft);
     
     this.gbl_pnlCharacLeft.columnWidths = new int[1];
     this.gbl_pnlCharacLeft.rowHeights = new int[1];
     this.gbl_pnlCharacLeft.columnWeights = new double[] { Double.MIN_VALUE };
     this.gbl_pnlCharacLeft.rowWeights = new double[] { Double.MIN_VALUE };
     this.pnlCharacLeft.setLayout(this.gbl_pnlCharacLeft);
     
     this.pnlCharacRigth.setName("pnlCharacRigth");
     
     this.gbc_pnlCharacRigth.anchor = 11;
     this.gbc_pnlCharacRigth.fill = 2;
     this.gbc_pnlCharacRigth.gridx = 1;
     this.gbc_pnlCharacRigth.gridy = 1;
     this.panelCharVals.add(this.pnlCharacRigth, this.gbc_pnlCharacRigth);
     
     this.gbl_pnlCharacRigth.columnWidths = new int[1];
     this.gbl_pnlCharacRigth.rowHeights = new int[1];
     this.gbl_pnlCharacRigth.columnWeights = new double[] { Double.MIN_VALUE };
     this.gbl_pnlCharacRigth.rowWeights = new double[] { 0.0D };
     this.pnlCharacRigth.setLayout(this.gbl_pnlCharacRigth);
     
 
 
     this.pnlComment.setName("pnlComment");
     
     this.gbc_pnlComment.fill = 1;
     this.gbc_pnlComment.gridx = 0;
     this.gbc_pnlComment.gridy = 1;
     this.pnlCharValues.add(this.pnlComment, this.gbc_pnlComment);
     
     this.gbl_pnlComment.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, -85 };
     this.gbl_pnlComment.rowHeights = new int[3];
     this.gbl_pnlComment.columnWeights = new double[] { 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
     this.gbl_pnlComment.rowWeights = new double[] { 0.0D, 1.0D, Double.MIN_VALUE };
     this.pnlComment.setLayout(this.gbl_pnlComment);
     this.lblQuestion.setText("Question");
     
     this.lblQuestion.setForeground(new Color(68, 84, 105));
     this.lblQuestion.setFont(new Font("Calibri", 1, 12));
     
     this.gbc_lblQuestion.fill = 2;
     this.gbc_lblQuestion.insets = new Insets(0, 0, 5, 5);
     this.gbc_lblQuestion.gridx = 0;
     this.gbc_lblQuestion.gridy = 0;
     this.pnlComment.add(this.lblQuestion, this.gbc_lblQuestion);
     
 
     this.lblNewLabel_11.setText("Comment");
     this.lblNewLabel_11.setForeground(new Color(68, 84, 105));
     this.lblNewLabel_11.setFont(new Font("Calibri", 1, 12));
     
     this.gbc_lblNewLabel_11.fill = 2;
     this.gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
     this.gbc_lblNewLabel_11.gridx = 1;
     this.gbc_lblNewLabel_11.gridy = 0;
     this.pnlComment.add(this.lblNewLabel_11, this.gbc_lblNewLabel_11);
     
 
     this.textField_6.setSelectionColor(Color.LIGHT_GRAY);
     
     this.gbc_textField_6.insets = new Insets(0, 0, 0, 5);
     this.gbc_textField_6.fill = 1;
     this.gbc_textField_6.gridx = 0;
     this.gbc_textField_6.gridy = 1;
     this.pnlComment.add(this.textField_6, this.gbc_textField_6);
     this.textField_6.setColumns(10);
     
     this.textArea_4.setSelectionColor(Color.LIGHT_GRAY);
     this.gbc_textArea_4.gridwidth = 12;
     
     this.gbc_textArea_4.insets = new Insets(0, 0, 0, 5);
     this.gbc_textArea_4.fill = 1;
     this.gbc_textArea_4.gridx = 1;
     this.gbc_textArea_4.gridy = 1;
     this.pnlComment.add(this.textArea_4, this.gbc_textArea_4);
     
     Dimension d = this.gbl_contentPane.minimumLayoutSize(this.contentPane);
     setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(), 
       Double.valueOf(400.0D).intValue()));
     
 
     this.textArea.setText(this.rs.getString("sd"));
     this.textArea.setLineWrap(true);
     
     this.textArea_1.setText(this.rs.getString("ld"));
     
     this.textArea_2.setText(this.rs.getString("po"));
     
 
 
 
     this.textArea.setLineWrap(true);
     this.textArea_1.setLineWrap(true);
     this.textArea_2.setLineWrap(true);
     this.textArea_3.setLineWrap(true);
     
 
 
 
 
     this.btnApplyClassification.setVisible(false);
   }
   
   private void load_item_data(String selectedAID, Date fedate) throws SQLException
   {
     this.conn = DriverManager.getConnection(this.url, this.props);
     this.st = this.conn.prepareStatement("select * from public.\"items\" WHERE \"aid\" = ?");
     this.st.setString(1, selectedAID);
     this.rs = this.st.executeQuery();
     this.rs.next();
     this.status = this.rs.getString("status");
     fedate = this.rs.getDate("fedate");
   }
   
   private void load_static_data(String selectedAID, JOptionPane pane)
     throws SQLException
   {
     Connection conn9 = DriverManager.getConnection(this.url, this.props);
     PreparedStatement st9 = conn9.prepareStatement("select * from public.\"data\" WHERE \"aid\" = ? ");
     
     st9.setString(1, selectedAID);
     ResultSet rs9 = st9.executeQuery();
     
     while (rs9.next()) {
       if (rs9.getString("chid").equals("MANUF")) {
         this.textField_4.setText(rs9.getString("value"));
       }
       if (rs9.getString("chid").equals("MANUF_REF")) {
         this.textField_5.setText(rs9.getString("value"));
       }
       if (rs9.getString("chid").equals("URL")) {
         this.textArea_3.setText(rs9.getString("value"));
       }
       if (rs9.getString("chid").equals("QUESTION")) {
         this.textField_6.setText(rs9.getString("value"));
       }
       if (rs9.getString("chid").equals("COMMENT")) {
         this.textArea_4.setText(rs9.getString("value"));
       }
     }
     
     this.rs.close();
     
     this.st.close();
     
     this.conn.close();
     
     setVisible(true);
     this.currentFamily.clear();
     this.currentFamily.put(this.originalclass, this.originalcid);
     pack();
     pane.setVisible(false);
   }
   
 
   private void load_chars(String string, Boolean refresh, String selectedAID)
     throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, SQLException, IOException
   {
     List<CharacValue> characValues = this.characService.getCharacValues(string);
     if (characValues.isEmpty()) {
       this.pnlCharacLeft.removeAll();
       this.modelLeft.clear();
       this.modelRight.clear();
       this.pnlCharacRigth.removeAll();
       this.pnlCharacLeft.repaint();
       this.pnlCharacRigth.repaint();
       if (refresh.booleanValue()) {
         pack();
       }
       return;
     }
     
     characValues.get(0).stat=false;
     for (CharacValue cv : characValues) {
       cv.setVal(selectedAID, false, string);
     }
     
     Map<String, List<CharacValue>> listModels = toLeftOrRigthList(characValues);
     
     this.modelLeft = ((List)listModels.get("LEFT"));
     this.modelRight = ((List)listModels.get("RIGTH"));
     
     this.characValueRenderer.rendererComponents(this.pnlCharacLeft, this.modelLeft);
     this.characValueRenderer.rendererComponents(this.pnlCharacRigth, this.modelRight);
     if (refresh.booleanValue()) {
       pack();
     }
   }
   
   private DefaultListModel<CharacValue> toListModel(List<CharacValue> characValues) {
     DefaultListModel<CharacValue> listModel = new DefaultListModel();
     for (CharacValue characValue : characValues) {
       listModel.addElement(characValue);
     }
     return listModel;
   }
   
   private Map<String, List<CharacValue>> toLeftOrRigthList(List<CharacValue> characValues)
   {
     List<CharacValue> characValuesLeft = new ArrayList();
     List<CharacValue> characValuesRigth = new ArrayList();
     for (int i = 0; i < characValues.size(); i++) {
       if ((i + 1) % 2 == 0) {
         characValuesRigth.add((CharacValue)characValues.get(i));
       } else {
         characValuesLeft.add((CharacValue)characValues.get(i));
       }
     }
     Map<String, List<CharacValue>> result = new HashMap();
     result.put("RIGTH", characValuesRigth);
     result.put("LEFT", characValuesLeft);
     
     return result;
   }
   
   protected String allow_next(String question, String cid, String status) throws HeadlessException, SQLException
   {
     LinkedHashSet<String> critics = new LinkedHashSet();
     LinkedHashSet<String> nus = new LinkedHashSet();
     HashSet<String> allowed = new HashSet(Arrays.asList(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "," }));
     this.completedVals = 0;
     if (this.modelLeft == null) {

    	 if (status.contains("REWORK:")) {
         this.allwnxt = "REWORK:PENDING";
         return "REWORK:PENDING";
       }
       this.allwnxt = "PENDING";
       return "PENDING";
     }
     for (CharacValue element : this.modelLeft) {
			if (element.isCritical) {
				if (element.getType() == CharacType.FT) {
					if (element.getvalue().length() == 0) {
						critics.add(element.getId().split("\\.")[4]);
					}
				}
				else if (element.getType() == CharacType.NU)
				{
					if ((element.getComp().length() == 0) || (element.getvalue().length() == 0)) {
						critics.add(element.getId().split("\\.")[4]);
					}
				}
				else if (element.getvalue().length() == 0) {
					critics.add(element.getId().split("\\.")[4]);
				}
			}



			if (element.getType() == CharacType.FT) {
				if ((!element.getvalue().equals("Inconnu&&Unknown")) && (element.getvalue().replace(" ", "").replace("&","").length() != 0)) {
					this.completedVals += 1;
				}

			}
			else if (element.getType() == CharacType.NU) {
				for (int i = 0; i < element.getvalue().replace("&&", "").length(); i++)
				{
					if (!allowed.contains(Character.toString(element.getvalue().replace("&&", "").charAt(i)))) {
						nus.add(element.getId().split("\\.")[4]);
						break;
					}
				}
				if (( element.getvalue().replace("---", "").replace(" ", "").replace("&", "").length() != 0 ) && (element.getComp().replace("---", "").replaceAll(" ", "").length() != 0)) {
					this.completedVals += 1;
				}
			}
			else if ((element.getvalue().length() != 0) && (!element.getvalue().equals("Inconnu/Unknown"))) {
				this.completedVals += 1;
			}
		}



		for (CharacValue element : this.modelRight) {
			if (element.isCritical) {
				if (element.getType() == CharacType.FT) {
					if (element.getvalue().length() == 0) {
						critics.add(element.getId().split("\\.")[4]);
					}
				}
				else if (element.getType() == CharacType.NU)
				{
					if ((element.getComp().length() == 0) || (element.getvalue().length() == 0)) {
						critics.add(element.getId().split("\\.")[4]);
					}
				}
				else if (element.getvalue().length() == 0) {
					critics.add(element.getId().split("\\.")[4]);
				}
			}



			if (element.getType() == CharacType.FT) {
				if ((!element.getvalue().equals("Inconnu&&Unknown")) && (element.getvalue().replace(" ", "").replace("&","").length() != 0)) {
					this.completedVals += 1;
				}

			}
			else if (element.getType() == CharacType.NU) {
				for (int i = 0; i < element.getvalue().replace("&&", "").length(); i++)
				{
					if (!allowed.contains(Character.toString(element.getvalue().replace("&&", "").charAt(i)))) {
						nus.add(element.getId().split("\\.")[4]);
						break;
					}
				}
				if ((element.getvalue().replace("---", "").replace(" ", "").replace("&", "").length() != 0) && (element.getComp().replace("---", "").replaceAll(" ", "").length() != 0)) {
					this.completedVals += 1;
				}
			}
			else if ((element.getvalue().length() != 0) && (!element.getvalue().equals("Inconnu/Unknown"))) {
				this.completedVals += 1;
			}
		}
     
 
 
 
     if (!nus.isEmpty()) {
       JOptionPane.showMessageDialog(null, new JLabel(
         "<html><h2><font color='red'>Non numeric data in fields: \n (" + String.join(" et ", nus) + ") \n Use : [0-9]  '/'  '-'  ',' or (space) or '@' (Unknown)</font></h2></html>"));
       this.allwnxt = "KO";
       return "KO";
     }
     
 
     if (!critics.isEmpty())
     {
 
       if (question.replace(" ", "").length() == 0)
       {
         JOptionPane.showMessageDialog(null, new JLabel(
           "<html><h2><font color='red'>Missing critical Fields.\n (" + 
           String.join(" et ", critics) + ") \n . Not allowed in Review Screen</font></h2></html>"));
         this.allwnxt = "KO";
         return "KO";
       }
       
 
 
 
 
 
 
 
       JOptionPane.showMessageDialog(null, new JLabel(
         "<html><h2><font color='red'>Warning:PENDING: Missing critical fields. Not allowed in Review Screen.</font></h2></html>"));
       if (status.contains("REWORK:")) {
         this.allwnxt = "REWORK:PENDING";
         return "REWORK:PENDING";
       }
       this.allwnxt = "PENDING";
       return "PENDING";
     }
     
 
 
     if (question.replace(" ", "").length() != 0)
     {
       JOptionPane.showMessageDialog(null, new JLabel(
         "<html><h2><font color='red'>Warning:PENDING: Question Written. Not allowed in Review Screen</font></h2></html>"));
       if (status.contains("REWORK:")) {
         this.allwnxt = "REWORK:PENDING";
         return "REWORK:PENDING";
       }
       this.allwnxt = "PENDING";
       return "PENDING";
     }
     
     if (isEphem(cid) || isStub(cid))
     {
       JOptionPane.showMessageDialog(null, new JLabel(
         "<html><h2><font color='red'>Warning:PENDING: Temporary Class. Not allowed in Review Screen</font></h2></html>"));
       if (status.contains("REWORK:")) {
         this.allwnxt = "REWORK:PENDING";
         return "REWORK:PENDING";
       }
       this.allwnxt = "PENDING";
       return "PENDING";
     }
     
     if (status.contains("REWORK:")) {
       this.allwnxt = "REWORK:REVIEWED";
       return "REWORK:REVIEWED";
     }
     this.allwnxt = "REVIEWED";
     return "REVIEWED";
   }
   
   private boolean isEphem(String cid)
     throws SQLException
   {
     if (this.classesTemp.size() == 0) {
       Connection conn999 = DriverManager.getConnection(this.url, this.props);
       PreparedStatement st999 = conn999.prepareStatement("select classes_temp from public.\"ephemere\"");
       ResultSet rs999 = st999.executeQuery();
       while (rs999.next()) {
         this.classesTemp.add(rs999.getString("classes_temp"));
       }
       return this.classesTemp.contains(cid);
     }
     if (this.classesTemp.contains(cid)) {
       return true;
     }
     return false;
   }
   
   
   private boolean isStub(String cid)
		     throws SQLException
		   {
		     if (this.classesStub.size() == 0) {
		       Connection conn999 = DriverManager.getConnection(this.url, this.props);
		       PreparedStatement st999 = conn999.prepareStatement("select classes_stub from public.\"classes_stub\"");
		       ResultSet rs999 = st999.executeQuery();
		       while (rs999.next()) {
		         this.classesStub.add(rs999.getString("classes_stub"));
		       }
							rs999.close();
							st999.close();
							conn999.close();
		       return this.classesStub.contains(cid);
		     }
		     if (this.classesStub.contains(cid)) {
		       return true;
		     }
		     return false;
		   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\ReviewDetail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */