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
 import java.awt.event.ActionListener;
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
 import javax.swing.JScrollBar;
 import javax.swing.JScrollPane;
 import javax.swing.JTable;
 import javax.swing.UIManager;
 import javax.swing.border.EmptyBorder;
 import javax.swing.plaf.synth.SynthLookAndFeel;
 import javax.swing.table.TableColumn;
 import javax.swing.table.TableColumnModel;
 import javax.swing.table.TableModel;
 import ntdw.common.ArticleTableModel2;
 import ntdw.common.ButtonColumn;
 import ntdw.common.MotionPanel;
 import ntdw.model.Article;
 import ntdw.service.ArticleMockDataService;
 import ntdw.service.DLL;
 import ntdw.service.Node;
 import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
 
 
 
 
 
 public class ReviewList
   extends JFrame
 {
   private JPanel contentPane;
   private JTable table;
   private ArticleMockDataService service = ArticleMockDataService.getInstance();
   
   private int height;
   
   private int width;
   
   protected Clock clock;
   
   public static void main(String[] args)
   {
     EventQueue.invokeLater(new Runnable() {
       public void run() {
         try {
           ReviewList localReviewList = new ReviewList("Elise", null);
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
   
 
 
 
 
   public ReviewList(String login, Clock clock)
   {
     this.clock = clock;
     this.service.stat = Boolean.valueOf(false);
     try {
       SynthLookAndFeel laf = new SynthLookAndFeel();
       laf.load(ItemList.class.getResourceAsStream("/ntdw/resources/laf.xml"), ItemList.class);
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
     
     setIconImage(Toolkit.getDefaultToolkit()
       .getImage(ItemList.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
     setTitle("TECHNICAL ITEM DESCRIPTION WIZARD");
     setMinimumSize(new Dimension(500, 300));
     
     try
     {
       initComponents(login);
 
     }
     catch (InvalidKeyException|ClassNotFoundException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|SQLException|IOException e1)
     {
       e1.printStackTrace();
     }
   }
   
   private void initComponents(final String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
   {
     setDefaultCloseOperation(3);
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
     gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 4 };
     gbl_contentPane.columnWeights = new double[] { 1.0D, 1.0D, 1.0D, 1.0D };
     gbl_contentPane.rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 1.0D, Double.MIN_VALUE };
     this.contentPane.setLayout(gbl_contentPane);
     
     JPanel panel = new JPanel();
     panel.setName("pnl_user_name");
     panel.setBackground(Color.CYAN);
     GridBagConstraints gbc_panel = new GridBagConstraints();
     gbc_panel.fill = 1;
     gbc_panel.gridx = 0;
     gbc_panel.gridy = 0;
     this.contentPane.add(panel, gbc_panel);
     
     JLabel lblSalman = new JLabel("REVIEWER : " + login);
     lblSalman.setName("lbl_user_name");
     panel.add(lblSalman);
     
     JPanel panel_1 = new JPanel();
     panel_1.setName("pnl_target");
     panel_1.setBackground(Color.CYAN);
     GridBagConstraints gbc_panel_1 = new GridBagConstraints();
     gbc_panel_1.fill = 1;
     gbc_panel_1.gridx = 1;
     gbc_panel_1.gridy = 0;
     this.contentPane.add(panel_1, gbc_panel_1);
     
     JPanel panel_2 = new JPanel();
     panel_2.setName("pnl_completed_week");
     panel_2.setBackground(Color.CYAN);
     GridBagConstraints gbc_panel_2 = new GridBagConstraints();
     gbc_panel_2.fill = 1;
     gbc_panel_2.gridx = 2;
     gbc_panel_2.gridy = 0;
     this.contentPane.add(panel_2, gbc_panel_2);
     
     JPanel panel_3 = new JPanel();
     panel_3.setName("pnl_completed_day");
     panel_3.setBackground(Color.CYAN);
     GridBagConstraints gbc_panel_3 = new GridBagConstraints();
     gbc_panel_3.fill = 1;
     gbc_panel_3.gridx = 3;
     gbc_panel_3.gridy = 0;
     this.contentPane.add(panel_3, gbc_panel_3);
     
     JPanel panel_5 = new JPanel();
     GridBagConstraints gbc_panel_5 = new GridBagConstraints();
     gbc_panel_5.insets = new Insets(10, 0, 5, 5);
     gbc_panel_5.gridx = 0;
     gbc_panel_5.gridy = 1;
     this.contentPane.add(panel_5, gbc_panel_5);
     
     JLabel lblNewLabel_3 = new JLabel("Selected rows:");
     lblNewLabel_3.setVisible(false);
     panel_5.add(lblNewLabel_3);
     
     JLabel lblNewLabel_4 = new JLabel("183");
     lblNewLabel_4.setVisible(false);
     panel_5.add(lblNewLabel_4);
     
     JPanel panel_4 = new JPanel();
     GridBagConstraints gbc_panel_4 = new GridBagConstraints();
     gbc_panel_4.insets = new Insets(10, 0, 5, 0);
     gbc_panel_4.anchor = 13;
     gbc_panel_4.fill = 3;
     gbc_panel_4.gridx = 2;
     gbc_panel_4.gridy = 1;
     gbc_panel_4.gridwidth = 2;
     this.contentPane.add(panel_4, gbc_panel_4);
     
     JButton btnNewButton_1 = new JButton("CLEAR FILTER");
     btnNewButton_1.setVisible(false);
     panel_4.add(btnNewButton_1);
     
     JButton button = new JButton("Go Home");
     button.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         ReviewList.this.service.stat = Boolean.valueOf(false);
         ReviewList.this.dispose();
         Home home = new Home(login, ReviewList.this.clock);
       }
     });
     panel_4.add(button);
     
     JScrollPane scrollPane = new JScrollPane();
     GridBagConstraints gbc_scrollPane = new GridBagConstraints();
     gbc_scrollPane.gridheight = 2;
     gbc_scrollPane.fill = 1;
     gbc_scrollPane.gridwidth = 4;
     gbc_scrollPane.insets = new Insets(0, 8, 8, 8);
     gbc_scrollPane.gridx = 0;
     gbc_scrollPane.gridy = 2;
     
     scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
     this.contentPane.add(scrollPane, gbc_scrollPane);
     
 
     Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
     this.height = screenSize.height;
     this.width = screenSize.width;
     setPreferredSize(new Dimension(this.width, this.height));
     
     List<Article> articles = this.service.getArticles(login, true);
     this.table = new JTable(new ArticleTableModel2(articles));
     TableRowFilterSupport.forTable(this.table).searchable(true).apply();
     this.table.getColumnModel().getColumn(0).setResizable(true);
     this.table.getColumnModel().getColumn(0).setPreferredWidth(Double.valueOf(this.width / 18).intValue());
     this.table.getColumnModel().getColumn(1).setPreferredWidth(Double.valueOf(this.width / 6).intValue());
     this.table.getColumnModel().getColumn(2).setPreferredWidth(Double.valueOf(this.width / 9).intValue());
     this.table.getColumnModel().getColumn(3).setPreferredWidth(Double.valueOf(this.width / 9).intValue());
     this.table.getColumnModel().getColumn(5).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
     this.table.getColumnModel().getColumn(6).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
     this.table.getColumnModel().getColumn(7).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
     
     Action articleIdClickAction = new AbstractAction()
     {
 
       public void actionPerformed(ActionEvent e)
       {
         JTable table = (JTable)e.getSource();
         int modelRow = Integer.valueOf(e.getActionCommand()).intValue();
         String selectedAID = (String)table.getModel().getValueAt(modelRow, 0);
         DLL dll = new DLL();
         dll.sethead((String)table.getValueAt(0, 1));
         boolean flag = false;
         Node currentNode; for (int i = 0; i < table.getRowCount(); i++) {
           String currentSD = (String)table.getValueAt(i, 1);
           String currentAID = (String)table.getValueAt(i, 0);
           currentNode = dll.head;
           while (currentNode.next != null) {
             currentNode = currentNode.next;
           }
           currentNode.id = currentAID;
           currentNode.data = currentSD;
           currentNode.next = new Node(currentSD);
           currentNode.next.prev = currentNode;
         }
         
 
 
 
 
         ReviewList.this.dispose();
         ReviewList.this.service.stat = Boolean.valueOf(false);
         StackTraceElement[] arrayOfStackTraceElement;
         //StackTraceElement ste;
         try {
           SynthLookAndFeel laf = new SynthLookAndFeel();
           laf.load(ReviewDetail.class.getResourceAsStream("/ntdw/resources/detail_laf.xml"), ReviewDetail.class);
           UIManager.setLookAndFeel(laf);

} catch (Exception e33) {

	StringBuilder sb = new StringBuilder(e33.toString());

    for (StackTraceElement ste : e33.getStackTrace()) {

        sb.append("\n\tat ");

        sb.append(ste);

    }

    JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog",

            JOptionPane.ERROR_MESSAGE);

}







try {

	JOptionPane pane = new JOptionPane(new JLabel(

		    "<html><h2><font color='red'>\"Loading item, please wait\"</font></h2></html>"));

	JDialog dialog = pane.createDialog(null, "Chargement");

	 dialog.setModal(false);

	 dialog.setVisible(false);

	ReviewDetail ReviewDetail = new ReviewDetail(dll,selectedAID,login,pane,clock);

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
     ButtonColumn articleIdButton = new ButtonColumn(this.table, articleIdClickAction, 0);
     
     scrollPane.setViewportView(this.table);
     Dimension d = gbl_contentPane.minimumLayoutSize(this.contentPane);
     setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(), 
       Double.valueOf(400.0D).intValue()));
     
     pack();
     setVisible(true);
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\ReviewList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */