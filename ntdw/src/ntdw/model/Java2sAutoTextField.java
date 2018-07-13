 package ntdw.model;
 
 import java.util.List;
 
 public class Java2sAutoTextField extends javax.swing.JTextField {
   private List dataList;
   private boolean isCaseSensitive;
   private boolean isStrict;
   private Java2sAutoComboBox autoComboBox;
   
   class AutoDocument extends javax.swing.text.PlainDocument {
     AutoDocument() {}
     
     public void replace(int i, int j, String s, javax.swing.text.AttributeSet attributeset) throws javax.swing.text.BadLocationException {
       super.remove(i, j);
       insertString(i, s, attributeset);
     }
     
     public void insertString(int i, String s, javax.swing.text.AttributeSet attributeset) throws javax.swing.text.BadLocationException
     {
       if ((s == null) || ("".equals(s)))
         return;
       String s1 = getText(0, i);
       String s2 = Java2sAutoTextField.this.getMatch(s1 + s);
       int j = i + s.length() - 1;
       if ((Java2sAutoTextField.this.isStrict) && (s2 == null)) {
         s2 = Java2sAutoTextField.this.getMatch(s1);
         j--;
       } else if ((!Java2sAutoTextField.this.isStrict) && (s2 == null)) {
         super.insertString(i, s, attributeset);
         return;
       }
       if ((Java2sAutoTextField.this.autoComboBox != null) && (s2 != null))
         Java2sAutoTextField.this.autoComboBox.setSelectedValue(s2);
       super.remove(0, getLength());
       super.insertString(0, s2, attributeset);
       Java2sAutoTextField.this.setSelectionStart(j + 1);
       Java2sAutoTextField.this.setSelectionEnd(getLength());
     }
     
     public void remove(int i, int j) throws javax.swing.text.BadLocationException {
       int k = Java2sAutoTextField.this.getSelectionStart();
       if (k > 0)
         k--;
       String s = Java2sAutoTextField.this.getMatch(getText(0, k));
       if ((!Java2sAutoTextField.this.isStrict) && (s == null)) {
         super.remove(i, j);
       } else {
         super.remove(0, getLength());
         super.insertString(0, s, null);
       }
       if ((Java2sAutoTextField.this.autoComboBox != null) && (s != null))
         Java2sAutoTextField.this.autoComboBox.setSelectedValue(s);
       try {
         Java2sAutoTextField.this.setSelectionStart(k);
         Java2sAutoTextField.this.setSelectionEnd(getLength());
       }
       catch (Exception localException) {}
     }
   }
   
   public Java2sAutoTextField(List list)
   {
     this.isCaseSensitive = false;
     this.isStrict = false;
     this.autoComboBox = null;
     if (list == null) {
       list = new java.util.ArrayList();
       list.add("");
     }
     else {
       this.dataList = list;
       init();
       return;
     }
   }
   
   Java2sAutoTextField(List list, Java2sAutoComboBox b) {
     this.isCaseSensitive = false;
     this.isStrict = true;
     this.autoComboBox = null;
     if (list == null) {
       throw new IllegalArgumentException("values can not be null");
     }
     this.dataList = list;
     this.autoComboBox = b;
     init();
   }
   
 
   private void init()
   {
     setDocument(new AutoDocument());
     if ((this.isStrict) && (this.dataList.size() > 0))
       setText(this.dataList.get(0).toString());
   }
   
   private String getMatch(String s) {
     for (int i = 0; i < this.dataList.size(); i++) {
       String s1 = this.dataList.get(i).toString();
       if (s1 != null) {
         if ((!this.isCaseSensitive) && 
           (s1.toLowerCase().startsWith(s.toLowerCase())))
           return s1;
         if ((this.isCaseSensitive) && (s1.startsWith(s))) {
           return s1;
         }
       }
     }
     return null;
   }
   
   public void replaceSelection(String s) {
     AutoDocument _lb = (AutoDocument)getDocument();
     if (_lb != null) {
       try {
         int i = Math.min(getCaret().getDot(), getCaret().getMark());
         int j = Math.max(getCaret().getDot(), getCaret().getMark());
         _lb.replace(i, j - i, s, null);
       } catch (Exception localException) {}
     }
   }
   
   public boolean isCaseSensitive() {
     return this.isCaseSensitive;
   }
   
   public void setCaseSensitive(boolean flag) {
     this.isCaseSensitive = flag;
   }
   
   public boolean isStrict() {
     return this.isStrict;
   }
   
   public void setStrict(boolean flag) {
     this.isStrict = flag;
   }
   
   public List getDataList() {
     return this.dataList;
   }
   
   public void setDataList(List list) {
     if (list == null) {
       throw new IllegalArgumentException("values can not be null");
     }
     this.dataList = list;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\Java2sAutoTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */