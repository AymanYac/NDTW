 package ntdw.views;
 
 import java.awt.Color;
 import java.awt.GridLayout;
 import java.awt.Point;
 import java.awt.event.FocusEvent;
 import java.awt.event.FocusListener;
 import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import javax.swing.BorderFactory;
 import javax.swing.DefaultListModel;
 import javax.swing.InputMap;
 import javax.swing.JList;
 import javax.swing.JPanel;
 import javax.swing.JScrollBar;
 import javax.swing.JScrollPane;
 import javax.swing.JTextField;
 import javax.swing.JWindow;
 import javax.swing.KeyStroke;
 import javax.swing.ListModel;
 import ntdw.model.AutoCompleteModel;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AutoComplete
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   public JTextField zoneTexte;
   private JWindow fenetreRecherche;
   private JList resultats;
   private DefaultListModel modelListe;
   private AutoCompleteModel model;
   public boolean new_val;
   public AutoComplete couple;
   
   public AutoComplete(final AutoCompleteModel model)
   {
     this.model = model;
     this.new_val = false;
     this.zoneTexte = new JTextField();
     this.modelListe = new DefaultListModel();
     this.resultats = new JList(this.modelListe);
     this.resultats.setBorder(BorderFactory.createEtchedBorder());
     this.fenetreRecherche = new JWindow();
     JScrollPane scrollPane = new JScrollPane(this.resultats);
     JScrollBar vertical = scrollPane.getVerticalScrollBar();
     InputMap im = vertical.getInputMap(2);
     im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
     im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
     this.fenetreRecherche.add(scrollPane);
     this.zoneTexte.addKeyListener(new KeyListener()
     {
       public void keyTyped(KeyEvent e) {}
       
 
       public void keyReleased(KeyEvent e)
       {
         if (e.getKeyCode() == 27) {
           AutoComplete.this.fenetreRecherche.setVisible(false);
         }
         else if (e.getKeyCode() == 40) {
           if (AutoComplete.this.resultats.getSelectedIndex() < AutoComplete.this.resultats.getModel().getSize()) {
             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getSelectedIndex() + 1);
             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
           }
           else {
             AutoComplete.this.resultats.setSelectedIndex(0);
             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
           }
         }
         else if (e.getKeyCode() == 38) {
           if (AutoComplete.this.resultats.getSelectedIndex() != AutoComplete.this.resultats.getModel().getSize()) {
             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getSelectedIndex() - 1);
             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
           }
           else {
             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getModel().getSize());
             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
           }
         }
         else if (e.getKeyCode() == 10) {
           try{
						AutoComplete.this.couple.new_val = false;
					}catch(Exception NO_COUPLE) {
						
					}
           AutoComplete.this.new_val = false;
           AutoComplete.this.zoneTexte.setText(AutoComplete.this.resultats.getSelectedValue().toString());
           AutoComplete.this.fenetreRecherche.setVisible(false);
         }
         else {
           AutoComplete.this.update();
         }
       }
       
 
       public void keyPressed(KeyEvent e) {}
     });
     this.zoneTexte.addKeyListener(new KeyAdapter() {
       public void keyTyped(KeyEvent e) {
         if (AutoComplete.this.zoneTexte.getText().length() >= 30)
           e.consume();
       }
     });
     this.zoneTexte.addMouseListener(new MouseAdapter()
     {
       public void mouseClicked(MouseEvent e) {
         List<String> correspondants = model.getToutesChaines();
         AutoComplete.this.modelListe.clear();
         if (correspondants.size() == 0) {
           AutoComplete.this.fenetreRecherche.setVisible(false);
         }
         else
         {
           for (String s : correspondants) {
             AutoComplete.this.modelListe.addElement(s);
           }
           
           AutoComplete.this.fenetreRecherche.setBounds((int)AutoComplete.this.getLocationOnScreen().getX(), (int)AutoComplete.this.getLocationOnScreen().getY() + AutoComplete.this.zoneTexte.getHeight(), AutoComplete.this.getWidth(), 13 * AutoComplete.this.zoneTexte.getHeight());
           AutoComplete.this.fenetreRecherche.setVisible(true);
           AutoComplete.this.resultats.setSelectedIndex(0);
           AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
         }
         
       }
     });
     FocusListener l = new FocusListener()
     {
 
       public void focusGained(FocusEvent e)
       {
         List<String> correspondants = new ArrayList();
         if (AutoComplete.this.zoneTexte.getText().length() > 0) {
           correspondants = model.getChainesCorrespondates(AutoComplete.this.zoneTexte.getText());
         } else {
           correspondants = model.getToutesChaines();
         }
         
         AutoComplete.this.modelListe.clear();
         if (correspondants.size() == 0) {
           AutoComplete.this.fenetreRecherche.setVisible(false);
         }
         else
         {
           for (String s : correspondants) {
             AutoComplete.this.modelListe.addElement(s);
           }
           
           AutoComplete.this.fenetreRecherche.setBounds((int)AutoComplete.this.getLocationOnScreen().getX(), (int)AutoComplete.this.getLocationOnScreen().getY() + AutoComplete.this.zoneTexte.getHeight(), AutoComplete.this.getWidth(), 13 * AutoComplete.this.zoneTexte.getHeight());
           AutoComplete.this.fenetreRecherche.setVisible(true);
           AutoComplete.this.resultats.setSelectedIndex(0);
           AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
         }
       }
       
 
       public void focusLost(FocusEvent e)
       {
         AutoComplete.this.fenetreRecherche.setVisible(false);
       }
       
 
     };
     this.zoneTexte.addFocusListener(l);
     setLayout(new GridLayout(1, 0));
     add(this.zoneTexte);
   }
   
 
 
   public void update()
   {
     List<String> correspondants = this.model.getChainesCorrespondates(this.zoneTexte.getText());
     this.modelListe.clear();
     if (correspondants.size() == 0) {
       this.fenetreRecherche.setVisible(false);
     }
     else
     {
       for (String s : correspondants) {
         this.modelListe.addElement(s);
         this.fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY() + this.zoneTexte.getHeight(), getWidth(), 13 * this.zoneTexte.getHeight());
         this.fenetreRecherche.setVisible(true);
         this.resultats.setSelectedIndex(1);
         this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
       }
     }
   }
   
   public void setText(String oldVal)
   {
     this.zoneTexte.setText(oldVal);
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\AutoComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */