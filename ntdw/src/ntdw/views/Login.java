/*     */ package ntdw.views;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.sql.SQLException;
/*     */ import java.time.Clock;
/*     */ import java.time.ZoneId;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.synth.SynthLookAndFeel;
/*     */ import ntdw.service.NanoClock;
/*     */ import ntdw.service.Tools;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Login
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel contentPane;
/*     */   private int height;
/*     */   private int width;
/*     */   private JTextField textField;
/*     */   private JPasswordField passwordField;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/*  55 */       SynthLookAndFeel laf = new SynthLookAndFeel();
/*  56 */       laf.load(Home.class.getResourceAsStream("/ntdw/resources/login_laf.xml"), Home.class);
/*  57 */       UIManager.setLookAndFeel(laf);
/*     */     }
/*     */     catch (Exception e) {
/*  60 */       StringBuilder sb = new StringBuilder(e.toString());
/*  61 */       StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/*  62 */         sb.append("\n\tat ");
/*  63 */         sb.append(ste);
/*     */       }
/*  65 */       JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/*  66 */         0);
/*     */     }
/*     */     
/*  69 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*     */         try {
/*  72 */           Login localLogin = new Login();
/*     */         } catch (Exception e) {
/*  74 */           StringBuilder sb = new StringBuilder(e.toString());
/*  75 */           StackTraceElement[] arrayOfStackTraceElement; int j = (arrayOfStackTraceElement = e.getStackTrace()).length; for (int i = 0; i < j; i++) { StackTraceElement ste = arrayOfStackTraceElement[i];
/*  76 */             sb.append("\n\tat ");
/*  77 */             sb.append(ste);
/*     */           }
/*  79 */           JOptionPane.showMessageDialog(new JFrame(), sb.toString(), "Dialog", 
/*  80 */             0);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Login()
/*     */   {
/*  90 */     final Clock clock = new NanoClock().withZone(ZoneId.of("UTC"));
/*  91 */     setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
/*  92 */     setTitle("TECHNICAL ITEM DESCRIPTION");
/*  93 */     setDefaultCloseOperation(3);
/*  94 */     setBounds(100, 100, 450, 300);
/*  95 */     this.contentPane = new JPanel();
/*  96 */     this.contentPane.setName("contentPane");
/*  97 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  98 */     setContentPane(this.contentPane);
/*  99 */     this.contentPane.setLayout(new BorderLayout(0, 0));
/*     */     
/* 101 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 102 */     this.height = (screenSize.height * 4 / 5);
/* 103 */     this.width = (screenSize.width * 4 / 5);
/* 104 */     setPreferredSize(new Dimension(this.width, this.height));
/*     */     
/* 106 */     JPanel panel = new JPanel();
/* 107 */     this.contentPane.add(panel, "Center");
/*     */     
/* 109 */     JPanel panel_1 = new JPanel();
/* 110 */     panel_1.setName("pnl_authentication");
/* 111 */     GroupLayout gl_panel = new GroupLayout(panel);
/* 112 */     gl_panel.setHorizontalGroup(
/* 113 */       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 114 */       .addGroup(gl_panel.createSequentialGroup()
/* 115 */       .addGap(273)
/* 116 */       .addComponent(panel_1, -1, 520, 32767)
/* 117 */       .addGap(273)));
/*     */     
/* 119 */     gl_panel.setVerticalGroup(
/* 120 */       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 121 */       .addGroup(gl_panel.createSequentialGroup()
/* 122 */       .addGap(153)
/* 123 */       .addComponent(panel_1, -1, 310, 32767)
/* 124 */       .addGap(153)));
/*     */     
/*     */ 
/* 127 */     AbstractAction checkpass = new AbstractAction()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/*     */         try
/*     */         {
/* 133 */           String ip = Tools.load_ip();
/* 134 */           if ((ip == null) || (ip.split("\\.").length != 4)) {
/* 135 */             JOptionPane.showMessageDialog(null, new JLabel(
/* 136 */               "<html><h2><font color='red'>Vérifiez les fichiers cypher !</font></h2></html>"));
/* 137 */             System.exit(0);
/*     */           } else {
/* 139 */             Boolean pass = Tools.checkpass(ip, Login.this.textField.getText().replace("'", "''"), String.valueOf(Login.this.passwordField.getPassword()).replace("'", "''"));
/* 140 */             if (pass.booleanValue()) {
/* 141 */               Login.this.dispose();
/* 142 */               Home localHome = new Home(Login.this.textField.getText().replace("'", "''"), clock);
/*     */             } else {
/* 144 */               JOptionPane.showMessageDialog(null, new JLabel(
/* 145 */                 "<html><h2><font color='red'>Vérifiez vos identifiants !</font></h2></html>"));
/*     */             }
/*     */             
/*     */           }
/*     */         }
/*     */         catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
/*     */         {
/* 152 */           JOptionPane.showMessageDialog(null, new JLabel(
/* 153 */             "<html><h2><font color='red'>Vérifiez les fichiers cypher !</font></h2></html>"));
/*     */           
/* 155 */           System.exit(0);
/* 156 */           e1.printStackTrace();
} catch (SQLException e1) {

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

}





}

};
/* 179 */     JPanel panel_2 = new JPanel();
/* 180 */     panel_2.setName("pnl_authentication_input");
/*     */     
/* 182 */     JLabel label = new JLabel("Login");
/*     */     
/* 184 */     JLabel label_1 = new JLabel("Password");
/*     */     
/* 186 */     this.textField = new JTextField();
/* 187 */     this.textField.setName("txt_login");
/* 188 */     this.textField.setColumns(10);
/* 189 */     this.textField.setBackground(Color.WHITE);
/* 190 */     this.textField.addActionListener(checkpass);
/*     */     
/* 192 */     this.passwordField = new JPasswordField();
/* 193 */     this.passwordField.setColumns(10);
/* 194 */     this.passwordField.addActionListener(checkpass);
/*     */     
GroupLayout gl_panel_2 = new GroupLayout(panel_2);

gl_panel_2

		.setHorizontalGroup(

				gl_panel_2.createParallelGroup(Alignment.LEADING)

						.addGroup(gl_panel_2.createSequentialGroup()

								.addGroup(

										gl_panel_2.createParallelGroup(Alignment.LEADING)

												.addGroup(gl_panel_2.createSequentialGroup()

														.addGap(49)

														.addComponent(

																label, GroupLayout.DEFAULT_SIZE, 66,

																Short.MAX_VALUE)

														.addGap(115))

												.addGroup(gl_panel_2.createSequentialGroup().addGap(40)

														.addGroup(gl_panel_2

																.createParallelGroup(Alignment.LEADING)

																.addGroup(gl_panel_2.createSequentialGroup()

																		.addGap(10)

																		.addComponent(label_1,

																				GroupLayout.DEFAULT_SIZE, 66,

																				Short.MAX_VALUE)

																		.addGap(114))

																.addComponent(textField,

																		GroupLayout.PREFERRED_SIZE, 190,

																		GroupLayout.PREFERRED_SIZE)

																.addComponent(passwordField,

																		GroupLayout.PREFERRED_SIZE, 190,

																		GroupLayout.PREFERRED_SIZE))))

								.addGap(60)));

gl_panel_2

		.setVerticalGroup(

				gl_panel_2

						.createParallelGroup(

								Alignment.LEADING)

						.addGroup(gl_panel_2.createSequentialGroup().addGap(38)

								.addComponent(label, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)

								.addPreferredGap(ComponentPlacement.RELATED)

								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20,

										GroupLayout.PREFERRED_SIZE)

								.addPreferredGap(ComponentPlacement.UNRELATED)

								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)

								.addPreferredGap(ComponentPlacement.RELATED).addComponent(passwordField,

										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)

								.addGap(130)));

panel_2.setLayout(gl_panel_2);
/*     */     
/* 242 */     JPanel panel_3 = new JPanel();
/* 243 */     panel_3.setBackground(new Color(68, 84, 105));
/* 244 */     panel_3.setName("pnl_logo");
/* 245 */     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
/* 246 */     gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 247 */       .addGroup(gl_panel_1.createSequentialGroup()
/* 248 */       .addComponent(panel_2, -1, 290, 32767)
/* 249 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 250 */       .addComponent(panel_3, -1, 389, 32767)));
/* 251 */     gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 252 */       .addComponent(panel_2, -1, 298, 32767)
/* 253 */       .addComponent(panel_3, -1, 287, 32767));
/* 254 */     panel_1.setLayout(gl_panel_1);
/* 255 */     panel.setLayout(gl_panel);
/* 256 */     Dimension d = gl_panel.minimumLayoutSize(panel);
/* 257 */     setMinimumSize(new Dimension(Double.valueOf(d.getWidth() * 2.0D / 3.0D).intValue(), 
/* 258 */       Double.valueOf(d.getHeight() * 2.0D / 3.0D).intValue()));
/* 259 */     setVisible(true);
/* 260 */     pack();
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\Login.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */