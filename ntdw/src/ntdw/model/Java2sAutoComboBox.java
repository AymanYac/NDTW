 package ntdw.model;
 
 import java.util.List;
 
 public class Java2sAutoComboBox extends javax.swing.JComboBox {
   private AutoTextFieldEditor autoTextFieldEditor;
   private boolean isFired;
   
   private class AutoTextFieldEditor extends javax.swing.plaf.basic.BasicComboBoxEditor {
     private Java2sAutoTextField getAutoTextFieldEditor() {
       return (Java2sAutoTextField)this.editor;
     }
     
     AutoTextFieldEditor(List list) {
       this.editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
     }
   }
   
   public Java2sAutoComboBox(List list) {
     this.isFired = false;
     this.autoTextFieldEditor = new AutoTextFieldEditor(list);
     setEditable(true);
     setModel(new javax.swing.DefaultComboBoxModel(list.toArray())
     {
       protected void fireContentsChanged(Object obj, int i, int j) {
         if (!Java2sAutoComboBox.this.isFired) {
           super.fireContentsChanged(obj, i, j);
         }
       }
     });
     setEditor(this.autoTextFieldEditor);
   }
   
   public boolean isCaseSensitive() {
     return this.autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
   }
   
   public void setCaseSensitive(boolean flag) {
     this.autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
   }
   
   public boolean isStrict() {
     return this.autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
   }
   
   public void setStrict(boolean flag) {
     this.autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
   }
   
   public List getDataList() {
     return this.autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
   }
   
   public void setDataList(List list) {
     this.autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
     setModel(new javax.swing.DefaultComboBoxModel(list.toArray()));
   }
   
   void setSelectedValue(Object obj) {
     if (this.isFired) {
       return;
     }
     this.isFired = true;
     setSelectedItem(obj);
     fireItemStateChanged(new java.awt.event.ItemEvent(this, 701, this.selectedItemReminder, 
       1));
     this.isFired = false;
   }
   
 
   protected void fireActionEvent()
   {
     if (!this.isFired) {
       super.fireActionEvent();
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\Java2sAutoComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */