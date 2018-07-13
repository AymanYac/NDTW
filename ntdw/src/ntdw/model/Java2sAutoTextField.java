/*     */ package ntdw.model;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class Java2sAutoTextField extends javax.swing.JTextField {
/*     */   private List dataList;
/*     */   private boolean isCaseSensitive;
/*     */   private boolean isStrict;
/*     */   private Java2sAutoComboBox autoComboBox;
/*     */   
/*     */   class AutoDocument extends javax.swing.text.PlainDocument {
/*     */     AutoDocument() {}
/*     */     
/*     */     public void replace(int i, int j, String s, javax.swing.text.AttributeSet attributeset) throws javax.swing.text.BadLocationException {
/*  15 */       super.remove(i, j);
/*  16 */       insertString(i, s, attributeset);
/*     */     }
/*     */     
/*     */     public void insertString(int i, String s, javax.swing.text.AttributeSet attributeset) throws javax.swing.text.BadLocationException
/*     */     {
/*  21 */       if ((s == null) || ("".equals(s)))
/*  22 */         return;
/*  23 */       String s1 = getText(0, i);
/*  24 */       String s2 = Java2sAutoTextField.this.getMatch(s1 + s);
/*  25 */       int j = i + s.length() - 1;
/*  26 */       if ((Java2sAutoTextField.this.isStrict) && (s2 == null)) {
/*  27 */         s2 = Java2sAutoTextField.this.getMatch(s1);
/*  28 */         j--;
/*  29 */       } else if ((!Java2sAutoTextField.this.isStrict) && (s2 == null)) {
/*  30 */         super.insertString(i, s, attributeset);
/*  31 */         return;
/*     */       }
/*  33 */       if ((Java2sAutoTextField.this.autoComboBox != null) && (s2 != null))
/*  34 */         Java2sAutoTextField.this.autoComboBox.setSelectedValue(s2);
/*  35 */       super.remove(0, getLength());
/*  36 */       super.insertString(0, s2, attributeset);
/*  37 */       Java2sAutoTextField.this.setSelectionStart(j + 1);
/*  38 */       Java2sAutoTextField.this.setSelectionEnd(getLength());
/*     */     }
/*     */     
/*     */     public void remove(int i, int j) throws javax.swing.text.BadLocationException {
/*  42 */       int k = Java2sAutoTextField.this.getSelectionStart();
/*  43 */       if (k > 0)
/*  44 */         k--;
/*  45 */       String s = Java2sAutoTextField.this.getMatch(getText(0, k));
/*  46 */       if ((!Java2sAutoTextField.this.isStrict) && (s == null)) {
/*  47 */         super.remove(i, j);
/*     */       } else {
/*  49 */         super.remove(0, getLength());
/*  50 */         super.insertString(0, s, null);
/*     */       }
/*  52 */       if ((Java2sAutoTextField.this.autoComboBox != null) && (s != null))
/*  53 */         Java2sAutoTextField.this.autoComboBox.setSelectedValue(s);
/*     */       try {
/*  55 */         Java2sAutoTextField.this.setSelectionStart(k);
/*  56 */         Java2sAutoTextField.this.setSelectionEnd(getLength());
/*     */       }
/*     */       catch (Exception localException) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Java2sAutoTextField(List list)
/*     */   {
/*  64 */     this.isCaseSensitive = false;
/*  65 */     this.isStrict = false;
/*  66 */     this.autoComboBox = null;
/*  67 */     if (list == null) {
/*  68 */       list = new java.util.ArrayList();
/*  69 */       list.add("");
/*     */     }
/*     */     else {
/*  72 */       this.dataList = list;
/*  73 */       init();
/*  74 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   Java2sAutoTextField(List list, Java2sAutoComboBox b) {
/*  79 */     this.isCaseSensitive = false;
/*  80 */     this.isStrict = true;
/*  81 */     this.autoComboBox = null;
/*  82 */     if (list == null) {
/*  83 */       throw new IllegalArgumentException("values can not be null");
/*     */     }
/*  85 */     this.dataList = list;
/*  86 */     this.autoComboBox = b;
/*  87 */     init();
/*     */   }
/*     */   
/*     */ 
/*     */   private void init()
/*     */   {
/*  93 */     setDocument(new AutoDocument());
/*  94 */     if ((this.isStrict) && (this.dataList.size() > 0))
/*  95 */       setText(this.dataList.get(0).toString());
/*     */   }
/*     */   
/*     */   private String getMatch(String s) {
/*  99 */     for (int i = 0; i < this.dataList.size(); i++) {
/* 100 */       String s1 = this.dataList.get(i).toString();
/* 101 */       if (s1 != null) {
/* 102 */         if ((!this.isCaseSensitive) && 
/* 103 */           (s1.toLowerCase().startsWith(s.toLowerCase())))
/* 104 */           return s1;
/* 105 */         if ((this.isCaseSensitive) && (s1.startsWith(s))) {
/* 106 */           return s1;
/*     */         }
/*     */       }
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public void replaceSelection(String s) {
/* 114 */     AutoDocument _lb = (AutoDocument)getDocument();
/* 115 */     if (_lb != null) {
/*     */       try {
/* 117 */         int i = Math.min(getCaret().getDot(), getCaret().getMark());
/* 118 */         int j = Math.max(getCaret().getDot(), getCaret().getMark());
/* 119 */         _lb.replace(i, j - i, s, null);
/*     */       } catch (Exception localException) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isCaseSensitive() {
/* 125 */     return this.isCaseSensitive;
/*     */   }
/*     */   
/*     */   public void setCaseSensitive(boolean flag) {
/* 129 */     this.isCaseSensitive = flag;
/*     */   }
/*     */   
/*     */   public boolean isStrict() {
/* 133 */     return this.isStrict;
/*     */   }
/*     */   
/*     */   public void setStrict(boolean flag) {
/* 137 */     this.isStrict = flag;
/*     */   }
/*     */   
/*     */   public List getDataList() {
/* 141 */     return this.dataList;
/*     */   }
/*     */   
/*     */   public void setDataList(List list) {
/* 145 */     if (list == null) {
/* 146 */       throw new IllegalArgumentException("values can not be null");
/*     */     }
/* 148 */     this.dataList = list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\Java2sAutoTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */