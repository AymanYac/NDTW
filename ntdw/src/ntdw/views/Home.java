/*     */ package ntdw.views;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.time.Clock;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.synth.SynthLookAndFeel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Home
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private int height;
/*     */   private int width;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  36 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/*  39 */           Home localHome = new Home(null, null);
/*     */         } catch (Exception e) {
/*  41 */           StringBuilder sb = new StringBuilder(e.toString());
/*  42 */           StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/*  43 */             sb.append("\n\tat ");
/*  44 */             sb.append(ste);
/*     */           }
/*  46 */           JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/*  47 */             0);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Home(final String login, final Clock clock)
/*     */   {
/*     */     try
/*     */     {
/*  60 */       SynthLookAndFeel laf = new SynthLookAndFeel();
/*  61 */       laf.load(Home.class.getResourceAsStream("/ntdw/resources/home_laf.xml"), Home.class);
/*  62 */       UIManager.setLookAndFeel(laf);
/*     */     }
/*     */     catch (Exception e) {
/*  65 */       StringBuilder sb = new StringBuilder(e.toString());
/*  66 */       StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/*  67 */         sb.append("\n\tat ");
/*  68 */         sb.append(ste);
/*     */       }
/*  70 */       JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/*  71 */         0);
/*     */     }
/*     */     
/*  74 */     setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
/*  75 */     setTitle("TECHNICAL ITEM DESCRIPTION");
/*  76 */     setDefaultCloseOperation(3);
/*     */     
/*  78 */     this.contentPane = new JPanel();
/*  79 */     this.contentPane.setName("contentPane");
/*  80 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  81 */     setContentPane(this.contentPane);
/*     */     
/*  83 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*     */     
/*     */ 
/*  86 */     this.height = (screenSize.height * 4 / 5);
/*  87 */     this.width = (screenSize.width * 4 / 5);
/*  88 */     setPreferredSize(new Dimension(this.width, this.height));
/*  89 */     int startheigt = screenSize.height * 1 / 8;
/*  90 */     int startwidth = screenSize.width * 1 / 20;
/*  91 */     setBounds(startheigt, startwidth, startheigt + this.height, startwidth + this.width);
/*     */     
/*  93 */     this.contentPane.setMinimumSize(screenSize);
/*  94 */     GridBagLayout gbl_contentPane = new GridBagLayout();
/*  95 */     gbl_contentPane.columnWidths = new int[] { 75, 568, 556 };
/*  96 */     gbl_contentPane.rowHeights = new int[] { 0, 436, 23, 23 };
/*  97 */     gbl_contentPane.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  98 */     gbl_contentPane.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  99 */     this.contentPane.setLayout(gbl_contentPane);
/*     */     
/* 101 */     JButton btnNewButton = new JButton("DESCRIBE PARTS");
/* 102 */     btnNewButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 104 */         Home.this.dispose();
/* 105 */         ItemList desc = new ItemList(login, clock);
/*     */       }
/* 107 */     });
/* 108 */     btnNewButton.setName("btnDescribeParts");
/* 109 */     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
/* 110 */     gbc_btnNewButton.anchor = 15;
/* 111 */     gbc_btnNewButton.fill = 2;
/* 112 */     gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
/* 113 */     gbc_btnNewButton.gridx = 1;
/* 114 */     gbc_btnNewButton.gridy = 1;
/* 115 */     this.contentPane.add(btnNewButton, gbc_btnNewButton);
/*     */     
/* 117 */     JButton button = new JButton("Reports & Summary");
/* 118 */     button.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 120 */         Home.this.dispose();
/* 121 */         Report report = new Report(login, clock);
/*     */       }
/*     */       
/* 124 */     });
/* 125 */     JButton btnReviewParts = new JButton("REVIEW PARTS");
/* 126 */     btnReviewParts.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 128 */         Home.this.dispose();
/* 129 */         ReviewList desc = new ReviewList(login, clock);
/*     */       }
/* 131 */     });
/* 132 */     btnReviewParts.setName("btnReviewParts");
/* 133 */     GridBagConstraints gbc_btnReviewParts = new GridBagConstraints();
/* 134 */     gbc_btnReviewParts.anchor = 15;
/* 135 */     gbc_btnReviewParts.fill = 2;
/* 136 */     gbc_btnReviewParts.insets = new Insets(0, 0, 5, 0);
/* 137 */     gbc_btnReviewParts.gridx = 2;
/* 138 */     gbc_btnReviewParts.gridy = 1;
/* 139 */     this.contentPane.add(btnReviewParts, gbc_btnReviewParts);
/* 140 */     button.setName("btnDescribeParts");
/* 141 */     GridBagConstraints gbc_button = new GridBagConstraints();
/* 142 */     gbc_button.insets = new Insets(0, 0, 5, 0);
/* 143 */     gbc_button.anchor = 11;
/* 144 */     gbc_button.fill = 2;
/* 145 */     gbc_button.gridwidth = 2;
/* 146 */     gbc_button.gridx = 1;
/* 147 */     gbc_button.gridy = 2;
/* 148 */     this.contentPane.add(button, gbc_button);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 153 */     setVisible(true);
/* 154 */     pack();
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\Home.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */