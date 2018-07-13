/*     */ package ntdw.views;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.sql.SQLException;
/*     */ import java.time.Clock;
/*     */ import java.util.List;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.synth.SynthLookAndFeel;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ import ntdw.common.ArticleTableModel2;
/*     */ import ntdw.common.ButtonColumn;
/*     */ import ntdw.common.MotionPanel;
/*     */ import ntdw.model.Article;
/*     */ import ntdw.service.ArticleMockDataService;
/*     */ import ntdw.service.DLL;
/*     */ import ntdw.service.Node;
/*     */ import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReviewList
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private JTable table;
/*  60 */   private ArticleMockDataService service = ArticleMockDataService.getInstance();
/*     */   
/*     */   private int height;
/*     */   
/*     */   private int width;
/*     */   
/*     */   protected Clock clock;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  70 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/*  73 */           ReviewList localReviewList = new ReviewList("Elise", null);
/*     */         } catch (Exception e) {
/*  75 */           StringBuilder sb = new StringBuilder(e.toString());
/*  76 */           StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/*  77 */             sb.append("\n\tat ");
/*  78 */             sb.append(ste);
/*     */           }
/*  80 */           JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/*  81 */             0);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ReviewList(String login, Clock clock)
/*     */   {
/*  93 */     this.clock = clock;
/*  94 */     this.service.stat = Boolean.valueOf(false);
/*     */     try {
/*  96 */       SynthLookAndFeel laf = new SynthLookAndFeel();
/*  97 */       laf.load(ItemList.class.getResourceAsStream("/ntdw/resources/laf.xml"), ItemList.class);
/*  98 */       UIManager.setLookAndFeel(laf);
/*     */     }
/*     */     catch (Exception e) {
/* 101 */       StringBuilder sb = new StringBuilder(e.toString());
/* 102 */       StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/* 103 */         sb.append("\n\tat ");
/* 104 */         sb.append(ste);
/*     */       }
/* 106 */       JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/* 107 */         0);
/*     */     }
/*     */     
/* 110 */     setIconImage(Toolkit.getDefaultToolkit()
/* 111 */       .getImage(ItemList.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
/* 112 */     setTitle("TECHNICAL ITEM DESCRIPTION WIZARD");
/* 113 */     setMinimumSize(new Dimension(500, 300));
/*     */     
/*     */     try
/*     */     {
/* 117 */       initComponents(login);
/*     */ 
/*     */     }
/*     */     catch (InvalidKeyException|ClassNotFoundException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|SQLException|IOException e1)
/*     */     {
/* 122 */       e1.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private void initComponents(final String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
/*     */   {
/* 128 */     setDefaultCloseOperation(3);
/* 129 */     setLocationRelativeTo(null);
/* 130 */     setBounds(0, 0, 0, 0);
/* 131 */     this.contentPane = new MotionPanel(this);
/* 132 */     this.contentPane.setAutoscrolls(true);
/* 133 */     this.contentPane.setBackground(Color.CYAN);
/* 134 */     this.contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));
/* 135 */     this.contentPane.setName("contentPane");
/* 136 */     setContentPane(this.contentPane);
/*     */     
/* 138 */     GridBagLayout gbl_contentPane = new GridBagLayout();
/* 139 */     gbl_contentPane.columnWidths = new int[4];
/* 140 */     gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 4 };
/* 141 */     gbl_contentPane.columnWeights = new double[] { 1.0D, 1.0D, 1.0D, 1.0D };
/* 142 */     gbl_contentPane.rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 1.0D, Double.MIN_VALUE };
/* 143 */     this.contentPane.setLayout(gbl_contentPane);
/*     */     
/* 145 */     JPanel panel = new JPanel();
/* 146 */     panel.setName("pnl_user_name");
/* 147 */     panel.setBackground(Color.CYAN);
/* 148 */     GridBagConstraints gbc_panel = new GridBagConstraints();
/* 149 */     gbc_panel.fill = 1;
/* 150 */     gbc_panel.gridx = 0;
/* 151 */     gbc_panel.gridy = 0;
/* 152 */     this.contentPane.add(panel, gbc_panel);
/*     */     
/* 154 */     JLabel lblSalman = new JLabel("REVIEWER : " + login);
/* 155 */     lblSalman.setName("lbl_user_name");
/* 156 */     panel.add(lblSalman);
/*     */     
/* 158 */     JPanel panel_1 = new JPanel();
/* 159 */     panel_1.setName("pnl_target");
/* 160 */     panel_1.setBackground(Color.CYAN);
/* 161 */     GridBagConstraints gbc_panel_1 = new GridBagConstraints();
/* 162 */     gbc_panel_1.fill = 1;
/* 163 */     gbc_panel_1.gridx = 1;
/* 164 */     gbc_panel_1.gridy = 0;
/* 165 */     this.contentPane.add(panel_1, gbc_panel_1);
/*     */     
/* 167 */     JPanel panel_2 = new JPanel();
/* 168 */     panel_2.setName("pnl_completed_week");
/* 169 */     panel_2.setBackground(Color.CYAN);
/* 170 */     GridBagConstraints gbc_panel_2 = new GridBagConstraints();
/* 171 */     gbc_panel_2.fill = 1;
/* 172 */     gbc_panel_2.gridx = 2;
/* 173 */     gbc_panel_2.gridy = 0;
/* 174 */     this.contentPane.add(panel_2, gbc_panel_2);
/*     */     
/* 176 */     JPanel panel_3 = new JPanel();
/* 177 */     panel_3.setName("pnl_completed_day");
/* 178 */     panel_3.setBackground(Color.CYAN);
/* 179 */     GridBagConstraints gbc_panel_3 = new GridBagConstraints();
/* 180 */     gbc_panel_3.fill = 1;
/* 181 */     gbc_panel_3.gridx = 3;
/* 182 */     gbc_panel_3.gridy = 0;
/* 183 */     this.contentPane.add(panel_3, gbc_panel_3);
/*     */     
/* 185 */     JPanel panel_5 = new JPanel();
/* 186 */     GridBagConstraints gbc_panel_5 = new GridBagConstraints();
/* 187 */     gbc_panel_5.insets = new Insets(10, 0, 5, 5);
/* 188 */     gbc_panel_5.gridx = 0;
/* 189 */     gbc_panel_5.gridy = 1;
/* 190 */     this.contentPane.add(panel_5, gbc_panel_5);
/*     */     
/* 192 */     JLabel lblNewLabel_3 = new JLabel("Selected rows:");
/* 193 */     lblNewLabel_3.setVisible(false);
/* 194 */     panel_5.add(lblNewLabel_3);
/*     */     
/* 196 */     JLabel lblNewLabel_4 = new JLabel("183");
/* 197 */     lblNewLabel_4.setVisible(false);
/* 198 */     panel_5.add(lblNewLabel_4);
/*     */     
/* 200 */     JPanel panel_4 = new JPanel();
/* 201 */     GridBagConstraints gbc_panel_4 = new GridBagConstraints();
/* 202 */     gbc_panel_4.insets = new Insets(10, 0, 5, 0);
/* 203 */     gbc_panel_4.anchor = 13;
/* 204 */     gbc_panel_4.fill = 3;
/* 205 */     gbc_panel_4.gridx = 2;
/* 206 */     gbc_panel_4.gridy = 1;
/* 207 */     gbc_panel_4.gridwidth = 2;
/* 208 */     this.contentPane.add(panel_4, gbc_panel_4);
/*     */     
/* 210 */     JButton btnNewButton_1 = new JButton("CLEAR FILTER");
/* 211 */     btnNewButton_1.setVisible(false);
/* 212 */     panel_4.add(btnNewButton_1);
/*     */     
/* 214 */     JButton button = new JButton("Go Home");
/* 215 */     button.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 217 */         ReviewList.this.service.stat = Boolean.valueOf(false);
/* 218 */         ReviewList.this.dispose();
/* 219 */         Home home = new Home(login, ReviewList.this.clock);
/*     */       }
/* 221 */     });
/* 222 */     panel_4.add(button);
/*     */     
/* 224 */     JScrollPane scrollPane = new JScrollPane();
/* 225 */     GridBagConstraints gbc_scrollPane = new GridBagConstraints();
/* 226 */     gbc_scrollPane.gridheight = 2;
/* 227 */     gbc_scrollPane.fill = 1;
/* 228 */     gbc_scrollPane.gridwidth = 4;
/* 229 */     gbc_scrollPane.insets = new Insets(0, 8, 8, 8);
/* 230 */     gbc_scrollPane.gridx = 0;
/* 231 */     gbc_scrollPane.gridy = 2;
/*     */     
/* 233 */     scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
/* 234 */     this.contentPane.add(scrollPane, gbc_scrollPane);
/*     */     
/*     */ 
/* 237 */     Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
/* 238 */     this.height = screenSize.height;
/* 239 */     this.width = screenSize.width;
/* 240 */     setPreferredSize(new Dimension(this.width, this.height));
/*     */     
/* 242 */     List<Article> articles = this.service.getArticles(login, true);
/* 243 */     this.table = new JTable(new ArticleTableModel2(articles));
/* 244 */     TableRowFilterSupport.forTable(this.table).searchable(true).apply();
/* 245 */     this.table.getColumnModel().getColumn(0).setResizable(true);
/* 246 */     this.table.getColumnModel().getColumn(0).setPreferredWidth(Double.valueOf(this.width / 18).intValue());
/* 247 */     this.table.getColumnModel().getColumn(1).setPreferredWidth(Double.valueOf(this.width / 6).intValue());
/* 248 */     this.table.getColumnModel().getColumn(2).setPreferredWidth(Double.valueOf(this.width / 9).intValue());
/* 249 */     this.table.getColumnModel().getColumn(3).setPreferredWidth(Double.valueOf(this.width / 9).intValue());
/* 250 */     this.table.getColumnModel().getColumn(5).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
/* 251 */     this.table.getColumnModel().getColumn(6).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
/* 252 */     this.table.getColumnModel().getColumn(7).setPreferredWidth(Double.valueOf(this.width / 50).intValue());
/*     */     
/* 254 */     Action articleIdClickAction = new AbstractAction()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 259 */         JTable table = (JTable)e.getSource();
/* 260 */         int modelRow = Integer.valueOf(e.getActionCommand()).intValue();
/* 261 */         String selectedAID = (String)table.getModel().getValueAt(modelRow, 0);
/* 262 */         DLL dll = new DLL();
/* 263 */         dll.sethead((String)table.getValueAt(0, 1));
/* 264 */         boolean flag = false;
/* 265 */         Node currentNode; for (int i = 0; i < table.getRowCount(); i++) {
/* 266 */           String currentSD = (String)table.getValueAt(i, 1);
/* 267 */           String currentAID = (String)table.getValueAt(i, 0);
/* 268 */           currentNode = dll.head;
/* 269 */           while (currentNode.next != null) {
/* 270 */             currentNode = currentNode.next;
/*     */           }
/* 272 */           currentNode.id = currentAID;
/* 273 */           currentNode.data = currentSD;
/* 274 */           currentNode.next = new Node(currentSD);
/* 275 */           currentNode.next.prev = currentNode;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 282 */         ReviewList.this.dispose();
/* 283 */         ReviewList.this.service.stat = Boolean.valueOf(false);
/*     */         StackTraceElement[] arrayOfStackTraceElement;
/*     */         //StackTraceElement ste;
/*     */         try {
/* 287 */           SynthLookAndFeel laf = new SynthLookAndFeel();
/* 288 */           laf.load(ReviewDetail.class.getResourceAsStream("/ntdw/resources/detail_laf.xml"), ReviewDetail.class);
/* 289 */           UIManager.setLookAndFeel(laf);

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
/* 339 */     ButtonColumn articleIdButton = new ButtonColumn(this.table, articleIdClickAction, 0);
/*     */     
/* 341 */     scrollPane.setViewportView(this.table);
/* 342 */     Dimension d = gbl_contentPane.minimumLayoutSize(this.contentPane);
/* 343 */     setMinimumSize(new Dimension(Double.valueOf(d.getWidth()).intValue(), 
/* 344 */       Double.valueOf(400.0D).intValue()));
/*     */     
/* 346 */     pack();
/* 347 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\ReviewList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */