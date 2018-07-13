 package ntdw.views;
 
 import java.awt.BorderLayout;
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.Toolkit;
 import java.awt.event.ActionEvent;
 import java.io.IOException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.NoSuchProviderException;
 import java.sql.SQLException;
 import java.time.Clock;
 import java.time.ZoneId;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.ShortBufferException;
 import javax.swing.AbstractAction;
 import javax.swing.GroupLayout;
 import javax.swing.GroupLayout.Alignment;
 import javax.swing.GroupLayout.ParallelGroup;
 import javax.swing.GroupLayout.SequentialGroup;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 import javax.swing.JPasswordField;
 import javax.swing.JTextField;
import javax.swing.LayoutStyle;
 import javax.swing.LayoutStyle.ComponentPlacement;
 import javax.swing.UIManager;
 import javax.swing.border.EmptyBorder;
 import javax.swing.plaf.synth.SynthLookAndFeel;
 import ntdw.service.NanoClock;
 import ntdw.service.Tools;
 
 
 
 
 
 public class Login
   extends JFrame
 {
   private JPanel contentPane;
   private int height;
   private int width;
   private JTextField textField;
   private JPasswordField passwordField;
   
   public static void main(String[] args)
   {
     try
     {
       SynthLookAndFeel laf = new SynthLookAndFeel();
       laf.load(Home.class.getResourceAsStream("/ntdw/resources/login_laf.xml"), Home.class);
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
     
     EventQueue.invokeLater(new Runnable() {
       public void run() {
         try {
           Login localLogin = new Login();
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
   
 
 
   public Login()
   {
     final Clock clock = new NanoClock().withZone(ZoneId.of("UTC"));
     setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
     setTitle("TECHNICAL ITEM DESCRIPTION");
     setDefaultCloseOperation(3);
     setBounds(100, 100, 450, 300);
     this.contentPane = new JPanel();
     this.contentPane.setName("contentPane");
     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
     setContentPane(this.contentPane);
     this.contentPane.setLayout(new BorderLayout(0, 0));
     
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     this.height = (screenSize.height * 4 / 5);
     this.width = (screenSize.width * 4 / 5);
     setPreferredSize(new Dimension(this.width, this.height));
     
     JPanel panel = new JPanel();
     this.contentPane.add(panel, "Center");
     
     JPanel panel_1 = new JPanel();
     panel_1.setName("pnl_authentication");
     GroupLayout gl_panel = new GroupLayout(panel);
     gl_panel.setHorizontalGroup(
       gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
       .addGroup(gl_panel.createSequentialGroup()
       .addGap(273)
       .addComponent(panel_1, -1, 520, 32767)
       .addGap(273)));
     
     gl_panel.setVerticalGroup(
       gl_panel.createParallelGroup(GroupLayout.Alignment.TRAILING)
       .addGroup(gl_panel.createSequentialGroup()
       .addGap(153)
       .addComponent(panel_1, -1, 310, 32767)
       .addGap(153)));
     
 
     AbstractAction checkpass = new AbstractAction()
     {
       public void actionPerformed(ActionEvent e)
       {
         try
         {
           String ip = Tools.load_ip();
           if ((ip == null) || (ip.split("\\.").length != 4)) {
             JOptionPane.showMessageDialog(null, new JLabel(
               "<html><h2><font color='red'>Vérifiez les fichiers cypher !</font></h2></html>"));
             System.exit(0);
           } else {
             Boolean pass = Tools.checkpass(ip, Login.this.textField.getText().replace("'", "''"), String.valueOf(Login.this.passwordField.getPassword()).replace("'", "''"));
             if (pass.booleanValue()) {
               Login.this.dispose();
               Home localHome = new Home(Login.this.textField.getText().replace("'", "''"), clock);
             } else {
               JOptionPane.showMessageDialog(null, new JLabel(
                 "<html><h2><font color='red'>Vérifiez vos identifiants !</font></h2></html>"));
             }
             
           }
         }
         catch (InvalidKeyException|NoSuchAlgorithmException|NoSuchProviderException|NoSuchPaddingException|ShortBufferException|IllegalBlockSizeException|BadPaddingException|IOException e1)
         {
           JOptionPane.showMessageDialog(null, new JLabel(
             "<html><h2><font color='red'>Vérifiez les fichiers cypher !</font></h2></html>"));
           
           System.exit(0);
           e1.printStackTrace();
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
     JPanel panel_2 = new JPanel();
     panel_2.setName("pnl_authentication_input");
     
     JLabel label = new JLabel("Login");
     
     JLabel label_1 = new JLabel("Password");
     
     this.textField = new JTextField();
     this.textField.setName("txt_login");
     this.textField.setColumns(10);
     this.textField.setBackground(Color.WHITE);
     this.textField.addActionListener(checkpass);
     
     this.passwordField = new JPasswordField();
     this.passwordField.setColumns(10);
     this.passwordField.addActionListener(checkpass);
     
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
     
     JPanel panel_3 = new JPanel();
     panel_3.setBackground(new Color(68, 84, 105));
     panel_3.setName("pnl_logo");
     GroupLayout gl_panel_1 = new GroupLayout(panel_1);
     gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
       .addGroup(gl_panel_1.createSequentialGroup()
       .addComponent(panel_2, -1, 290, 32767)
       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
       .addComponent(panel_3, -1, 389, 32767)));
     gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
       .addComponent(panel_2, -1, 298, 32767)
       .addComponent(panel_3, -1, 287, 32767));
     panel_1.setLayout(gl_panel_1);
     panel.setLayout(gl_panel);
     Dimension d = gl_panel.minimumLayoutSize(panel);
     setMinimumSize(new Dimension(Double.valueOf(d.getWidth() * 2.0D / 3.0D).intValue(), 
       Double.valueOf(d.getHeight() * 2.0D / 3.0D).intValue()));
     setVisible(true);
     pack();
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\Login.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */