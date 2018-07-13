/*    */ package ntdw.model;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Java2sAutoComboBox extends javax.swing.JComboBox {
/*    */   private AutoTextFieldEditor autoTextFieldEditor;
/*    */   private boolean isFired;
/*    */   
/*    */   private class AutoTextFieldEditor extends javax.swing.plaf.basic.BasicComboBoxEditor {
/*    */     private Java2sAutoTextField getAutoTextFieldEditor() {
/* 11 */       return (Java2sAutoTextField)this.editor;
/*    */     }
/*    */     
/*    */     AutoTextFieldEditor(List list) {
/* 15 */       this.editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
/*    */     }
/*    */   }
/*    */   
/*    */   public Java2sAutoComboBox(List list) {
/* 20 */     this.isFired = false;
/* 21 */     this.autoTextFieldEditor = new AutoTextFieldEditor(list);
/* 22 */     setEditable(true);
/* 23 */     setModel(new javax.swing.DefaultComboBoxModel(list.toArray())
/*    */     {
/*    */       protected void fireContentsChanged(Object obj, int i, int j) {
/* 26 */         if (!Java2sAutoComboBox.this.isFired) {
/* 27 */           super.fireContentsChanged(obj, i, j);
/*    */         }
/*    */       }
/* 30 */     });
/* 31 */     setEditor(this.autoTextFieldEditor);
/*    */   }
/*    */   
/*    */   public boolean isCaseSensitive() {
/* 35 */     return this.autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
/*    */   }
/*    */   
/*    */   public void setCaseSensitive(boolean flag) {
/* 39 */     this.autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
/*    */   }
/*    */   
/*    */   public boolean isStrict() {
/* 43 */     return this.autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
/*    */   }
/*    */   
/*    */   public void setStrict(boolean flag) {
/* 47 */     this.autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
/*    */   }
/*    */   
/*    */   public List getDataList() {
/* 51 */     return this.autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
/*    */   }
/*    */   
/*    */   public void setDataList(List list) {
/* 55 */     this.autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
/* 56 */     setModel(new javax.swing.DefaultComboBoxModel(list.toArray()));
/*    */   }
/*    */   
/*    */   void setSelectedValue(Object obj) {
/* 60 */     if (this.isFired) {
/* 61 */       return;
/*    */     }
/* 63 */     this.isFired = true;
/* 64 */     setSelectedItem(obj);
/* 65 */     fireItemStateChanged(new java.awt.event.ItemEvent(this, 701, this.selectedItemReminder, 
/* 66 */       1));
/* 67 */     this.isFired = false;
/*    */   }
/*    */   
/*    */ 
/*    */   protected void fireActionEvent()
/*    */   {
/* 73 */     if (!this.isFired) {
/* 74 */       super.fireActionEvent();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\Java2sAutoComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */