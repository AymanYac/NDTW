 package ntdw.views;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;

import javax.swing.JFormattedTextField;
 import javax.swing.JFormattedTextField.AbstractFormatter;
 
 public class DateLabelFormatter
   extends JFormattedTextField.AbstractFormatter
 {
   private String datePattern = "yyyy-M-d";
   private SimpleDateFormat dateFormatter = new SimpleDateFormat(this.datePattern);
   
   public Object stringToValue(String text) throws ParseException
   {
     return this.dateFormatter.parseObject(text);
   }
   
   public String valueToString(Object value) throws ParseException
   {
     if (value != null) {
       Calendar cal = (Calendar)value;
       return this.dateFormatter.format(cal.getTime());
     }
     
     return "";
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\DateLabelFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */