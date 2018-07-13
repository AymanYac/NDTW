 package ntdw.service;
 
 import java.io.IOException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.NoSuchProviderException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Properties;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.ShortBufferException;
 import ntdw.model.Target;
 
 
 
 
 public class Report_Week_Target
 {
   private static Report_Week_Target INSTANCE = new Report_Week_Target();
   private List<Target> targets = new ArrayList();
   
 
 
 
 
   public static Report_Week_Target getInstance() { return INSTANCE; }
   
   public List<Target> getArticles(String login) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException {
     Class.forName("org.postgresql.Driver");
     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
     Properties props = new Properties();
     props.setProperty("user", "postgres");
     props.setProperty("password", "Neonec");
     props.setProperty("loginTimeout", "20");
     props.setProperty("connectTimeout", "0");
     props.setProperty("socketTimeout", "0");
     
     Connection conn = DriverManager.getConnection(url, props);
     PreparedStatement st = null;
     if ((login.equals("Elise")) || (login.equals("Aparna"))) {
       st = conn.prepareStatement("select * from items");
     } else {
       st = conn.prepareStatement("select * from items where classifier = ?");
       st.setString(1, login);
     }
     
     ResultSet rs = st.executeQuery();
     HashMap<String, HashMap<Integer, HashMap<String, Integer>>> tmp = new HashMap();
     while (rs.next()) {
       String user = rs.getString("classifier");
       Integer week = Integer.valueOf(rs.getInt("target_week"));
       String status = rs.getString("status");
       if (tmp.containsKey(user)) {
         if (((HashMap)tmp.get(user)).containsKey(week)) {
           if (status.contains("COMPLET")) {
             status = "COMPLET";
           } else {
             status = "PENDING";
           }
           if (((HashMap)((HashMap)tmp.get(user)).get(week)).containsKey(status)) {
             Integer count = (Integer)((HashMap)((HashMap)tmp.get(user)).get(week)).get(status);
             count = Integer.valueOf(count.intValue() + 1);
             HashMap tmp_status = new HashMap();
             tmp_status.put(status, count);
             HashMap tmp_week = new HashMap();
             tmp_week.put(week, tmp_status);
             tmp.put(user, tmp_week);
           }
           else {
             Integer count = Integer.valueOf(1);
             HashMap tmp_status = new HashMap();
             tmp_status.put(status, count);
             HashMap tmp_week = new HashMap();
             tmp_week.put(week, tmp_status);
             tmp.put(user, tmp_week);
           }
         } else {
           if (status.contains("COMPLET")) {
             status = "COMPLET";
           } else {
             status = "PENDING";
           }
           Integer count = Integer.valueOf(1);
           HashMap tmp_status = new HashMap();
           tmp_status.put(status, count);
           HashMap tmp_week = new HashMap();
           tmp_week.put(week, tmp_status);
           tmp.put(user, tmp_week);
         }
       }
       else
       {
         if (status.contains("COMPLET")) {
           status = "COMPLET";
         } else {
           status = "PENDING";
         }
         Integer count = Integer.valueOf(1);
         HashMap tmp_status = new HashMap();
         tmp_status.put(status, count);
         HashMap tmp_week = new HashMap();
         tmp_week.put(week, tmp_status);
         tmp.put(user, tmp_week);
       }
     }
     
 
 
 
 
 
 
     return this.targets;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\Report_Week_Target.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */