 package ntdw.views;
 
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.GridBagConstraints;
 import java.awt.GridBagLayout;
 import java.awt.Insets;
 import java.awt.Toolkit;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.time.Clock;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JOptionPane;
 import javax.swing.JPanel;
 import javax.swing.UIManager;
 import javax.swing.border.EmptyBorder;
 import javax.swing.plaf.synth.SynthLookAndFeel;
 
 
 
 
 
 
 
 
 public class Home
   extends JFrame
 {
   private JPanel contentPane;
   private int height;
   private int width;
   
   public static void main(String[] args)
   {
     EventQueue.invokeLater(new Runnable() {
       public void run() {
         try {
           Home localHome = new Home(null, null);
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
   
 
 
 
   public Home(final String login, final Clock clock)
   {
     try
     {
       SynthLookAndFeel laf = new SynthLookAndFeel();
       laf.load(Home.class.getResourceAsStream("/ntdw/resources/home_laf.xml"), Home.class);
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
     
     setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/ntdw/resources/images/Neonec-white-logo only.png")));
     setTitle("TECHNICAL ITEM DESCRIPTION");
     setDefaultCloseOperation(3);
     
     this.contentPane = new JPanel();
     this.contentPane.setName("contentPane");
     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
     setContentPane(this.contentPane);
     
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     
 
     this.height = (screenSize.height * 4 / 5);
     this.width = (screenSize.width * 4 / 5);
     setPreferredSize(new Dimension(this.width, this.height));
     int startheigt = screenSize.height * 1 / 8;
     int startwidth = screenSize.width * 1 / 20;
     setBounds(startheigt, startwidth, startheigt + this.height, startwidth + this.width);
     
     this.contentPane.setMinimumSize(screenSize);
     GridBagLayout gbl_contentPane = new GridBagLayout();
     gbl_contentPane.columnWidths = new int[] { 75, 568, 556 };
     gbl_contentPane.rowHeights = new int[] { 0, 436, 23, 23 };
     gbl_contentPane.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
     gbl_contentPane.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
     this.contentPane.setLayout(gbl_contentPane);
     
     JButton btnNewButton = new JButton("DESCRIBE PARTS");
     btnNewButton.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         Home.this.dispose();
         ItemList desc = new ItemList(login, clock);
       }
     });
     btnNewButton.setName("btnDescribeParts");
     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
     gbc_btnNewButton.anchor = 15;
     gbc_btnNewButton.fill = 2;
     gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
     gbc_btnNewButton.gridx = 1;
     gbc_btnNewButton.gridy = 1;
     this.contentPane.add(btnNewButton, gbc_btnNewButton);
     
     JButton button = new JButton("Reports & Summary");
     button.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         Home.this.dispose();
         Report report = new Report(login, clock);
       }
       
     });
     JButton btnReviewParts = new JButton("REVIEW PARTS");
     btnReviewParts.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         Home.this.dispose();
         ReviewList desc = new ReviewList(login, clock);
       }
     });
     btnReviewParts.setName("btnReviewParts");
     GridBagConstraints gbc_btnReviewParts = new GridBagConstraints();
     gbc_btnReviewParts.anchor = 15;
     gbc_btnReviewParts.fill = 2;
     gbc_btnReviewParts.insets = new Insets(0, 0, 5, 0);
     gbc_btnReviewParts.gridx = 2;
     gbc_btnReviewParts.gridy = 1;
     this.contentPane.add(btnReviewParts, gbc_btnReviewParts);
     button.setName("btnDescribeParts");
     GridBagConstraints gbc_button = new GridBagConstraints();
     gbc_button.insets = new Insets(0, 0, 5, 0);
     gbc_button.anchor = 11;
     gbc_button.fill = 2;
     gbc_button.gridwidth = 2;
     gbc_button.gridx = 1;
     gbc_button.gridy = 2;
     this.contentPane.add(button, gbc_button);
     
 
 
 
     setVisible(true);
     pack();
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\Home.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */