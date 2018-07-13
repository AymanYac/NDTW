 package ntdw.common;
 
 import java.awt.Dimension;
 import java.awt.Rectangle;
 import java.awt.event.ItemEvent;
 import java.awt.event.ItemListener;
 import java.util.ArrayList;
 import java.util.HashSet;
 import javax.swing.JPanel;
 import javax.swing.JTextField;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 import javax.swing.text.Document;
 import ntdw.model.AutoCompleteModel;
 import ntdw.views.AutoComplete;
 
 
 public class ComboBoxActionListner
   implements ItemListener
 {
   private JPanel panel;
   public JTextField textField = new JTextField();
   AutoComplete text = new AutoComplete(null);
   private Rectangle area;
   private HashSet<String> hist;
   
   public ComboBoxActionListner(JPanel panel, HashSet<String> hist, Rectangle area)
   {
     this.panel = panel;
     this.area = area;
     this.hist = hist;
   }
   
   public void itemStateChanged(ItemEvent event)
   {
     if (event.getStateChange() == 1) {
       String item = (String)event.getItem();
       if (item.equals("Autre / Other")) {
         AutoCompleteModel model = new AutoCompleteModel();
         model.addAll(new ArrayList(this.hist));
         this.text = new AutoComplete(model);
         
         DocumentListener subDoc = new DocumentListener()
         {
           public void changedUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void insertUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void removeUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           private void updateLabel(DocumentEvent e)
           {
             ComboBoxActionListner.this.textField.setText(ComboBoxActionListner.this.text.zoneTexte.getText());
           }
           
         };
         this.text.zoneTexte.getDocument().addDocumentListener(subDoc);
         this.text.setPreferredSize(new Dimension((int)(150.0D * (this.area.width / 1366.0D)), 20));
         
         this.textField.setVisible(false);
         this.panel.add(this.text);
         
         this.panel.validate();
         this.panel.repaint();
       }
       else if (this.textField != null) {
         this.panel.remove(this.textField);
         this.panel.remove(this.text);
         this.panel.validate();
         this.panel.repaint();
       }
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ComboBoxActionListner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */