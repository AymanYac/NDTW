/*    */ package ntdw.views;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;

import javax.swing.JFormattedTextField;
/*    */ import javax.swing.JFormattedTextField.AbstractFormatter;
/*    */ 
/*    */ public class DateLabelFormatter
/*    */   extends JFormattedTextField.AbstractFormatter
/*    */ {
/* 11 */   private String datePattern = "yyyy-M-d";
/* 12 */   private SimpleDateFormat dateFormatter = new SimpleDateFormat(this.datePattern);
/*    */   
/*    */   public Object stringToValue(String text) throws ParseException
/*    */   {
/* 16 */     return this.dateFormatter.parseObject(text);
/*    */   }
/*    */   
/*    */   public String valueToString(Object value) throws ParseException
/*    */   {
/* 21 */     if (value != null) {
/* 22 */       Calendar cal = (Calendar)value;
/* 23 */       return this.dateFormatter.format(cal.getTime());
/*    */     }
/*    */     
/* 26 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\DateLabelFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */