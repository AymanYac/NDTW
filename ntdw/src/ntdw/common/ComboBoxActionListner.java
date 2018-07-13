/*    */ package ntdw.common;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ import javax.swing.event.DocumentEvent;
/*    */ import javax.swing.event.DocumentListener;
/*    */ import javax.swing.text.Document;
/*    */ import ntdw.model.AutoCompleteModel;
/*    */ import ntdw.views.AutoComplete;
/*    */ 
/*    */ 
/*    */ public class ComboBoxActionListner
/*    */   implements ItemListener
/*    */ {
/*    */   private JPanel panel;
/* 22 */   public JTextField textField = new JTextField();
/* 23 */   AutoComplete text = new AutoComplete(null);
/*    */   private Rectangle area;
/*    */   private HashSet<String> hist;
/*    */   
/*    */   public ComboBoxActionListner(JPanel panel, HashSet<String> hist, Rectangle area)
/*    */   {
/* 29 */     this.panel = panel;
/* 30 */     this.area = area;
/* 31 */     this.hist = hist;
/*    */   }
/*    */   
/*    */   public void itemStateChanged(ItemEvent event)
/*    */   {
/* 36 */     if (event.getStateChange() == 1) {
/* 37 */       String item = (String)event.getItem();
/* 38 */       if (item.equals("Autre / Other")) {
/* 39 */         AutoCompleteModel model = new AutoCompleteModel();
/* 40 */         model.addAll(new ArrayList(this.hist));
/* 41 */         this.text = new AutoComplete(model);
/*    */         
/* 43 */         DocumentListener subDoc = new DocumentListener()
/*    */         {
/*    */           public void changedUpdate(DocumentEvent e)
/*    */           {
/* 47 */             updateLabel(e);
/*    */           }
/*    */           
/*    */           public void insertUpdate(DocumentEvent e)
/*    */           {
/* 52 */             updateLabel(e);
/*    */           }
/*    */           
/*    */           public void removeUpdate(DocumentEvent e)
/*    */           {
/* 57 */             updateLabel(e);
/*    */           }
/*    */           
/*    */           private void updateLabel(DocumentEvent e)
/*    */           {
/* 62 */             ComboBoxActionListner.this.textField.setText(ComboBoxActionListner.this.text.zoneTexte.getText());
/*    */           }
/*    */           
/* 65 */         };
/* 66 */         this.text.zoneTexte.getDocument().addDocumentListener(subDoc);
/* 67 */         this.text.setPreferredSize(new Dimension((int)(150.0D * (this.area.width / 1366.0D)), 20));
/*    */         
/* 69 */         this.textField.setVisible(false);
/* 70 */         this.panel.add(this.text);
/*    */         
/* 72 */         this.panel.validate();
/* 73 */         this.panel.repaint();
/*    */       }
/* 75 */       else if (this.textField != null) {
/* 76 */         this.panel.remove(this.textField);
/* 77 */         this.panel.remove(this.text);
/* 78 */         this.panel.validate();
/* 79 */         this.panel.repaint();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ComboBoxActionListner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */