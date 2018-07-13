 package ntdw.model;
 
 import java.io.IOException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.NoSuchProviderException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Properties;
 import java.util.Set;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.ShortBufferException;
 import javax.swing.JComboBox;
 import javax.swing.JTextField;
 import ntdw.common.CharacType;
 import ntdw.service.Tools;
 
 
 
 public class CharacValue
 {
   private String id;
   private CharacType type;
   private String name;
   private List<String> ListValues;
   private Integer nbCharacteristic;
   public boolean isCritical;
   public JTextField text;
   public JComboBox<String> box;
   public JTextField comp;
   public String oldVal;
   public String oldComp;
   public HashSet<String> hist;
   public static HashMap<String, HashSet<String>> histVals = new HashMap();
   public static boolean stat = false;
   public static HashMap<String, String> oldVals = new HashMap();
   public static HashMap<String, String> oldComps = new HashMap();
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public CharacType getType() {
     return this.type;
   }
   
   public void setType(CharacType type) { this.type = type; }
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) { this.name = name; }
   
   public List<String> getListValues() {
     return this.ListValues;
   }
   
   public void setListValues(List<String> listValues) { this.ListValues = listValues; }
   
   public Integer getNbCharacteristic() {
     return this.nbCharacteristic;
   }
   
   public void setNbCharacteristic(Integer nbCharacteristic) { this.nbCharacteristic = nbCharacteristic; }
   
   public String getvalue() {
     if (this.text != null) {
       return this.text.getText();
     }
     return (String)this.box.getSelectedItem();
   }
   
   public String getComp() { if (this.comp != null) {
       return this.comp.getText();
     }
     return null;
   }
   
   public void setVal(String selectedAID, boolean b, String cid) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException { if (stat) {
       if (oldVals.containsKey(getId())) {
         this.oldVal = ((String)oldVals.get(getId()));
       } else {
         this.oldVal = "";
       }
       if (oldComps.containsKey(getId())) {
         this.oldComp = ((String)oldComps.get(getId()));
       } else {
         this.oldVal = "";
       }
       if (histVals.containsKey(getId())) {
         this.hist = ((HashSet)histVals.get(getId()));
       } else {
         this.hist = new HashSet();
       }
     } else {
       this.oldVal = "";
       stat(selectedAID, cid, b);
     }
   }
   
   private void stat(String selectedAID, String cid, boolean b) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException
   {
     stat = true;
     oldVals.clear();
     oldComps.clear();
     histVals.clear();
     Class.forName("org.postgresql.Driver");
     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
     Properties props = new Properties();
     props.setProperty("user", "postgres");
     props.setProperty("password", "Neonec");
     props.setProperty("loginTimeout", "20");
     props.setProperty("connectTimeout", "0");
     props.setProperty("socketTimeout", "0");
     
     Connection conn = DriverManager.getConnection(url, props);
     PreparedStatement st = conn.prepareStatement("select * from public.\"data\" WHERE \"aid\" = ? AND \"chid\" LIKE ?");
     
     st.setString(1, selectedAID);
     st.setString(2, cid + "%");
     ResultSet rs = st.executeQuery();
     
     while (rs.next()) {
       oldVals.put(rs.getString("chid"), rs.getString("value"));
       oldComps.put(rs.getString("chid"), rs.getString("comp"));
     }
     
 
 
 
 
 
 
 
 
 
     if (oldVals.containsKey(getId())) {
       this.oldVal = ((String)oldVals.get(getId()));
     }
     if (oldComps.containsKey(getId())) {
       this.oldComp = ((String)oldComps.get(getId()));
     }
     
 
     rs.close();
     st.close();
     conn.close();
     Connection conn1 = DriverManager.getConnection(url, props);
     PreparedStatement st1 = conn1.prepareStatement("select * from public.\"data\" WHERE \"chid\" LIKE ? and (type = ? or type =?)");
     st1.setString(1, cid + "%");
     st1.setString(2, "FT");
     st1.setString(3, "VL");
     ResultSet rs1 = st1.executeQuery();
     while (rs1.next()) {
       add_hists(histVals, rs1.getString("chid"), rs1.getString("value"), rs1.getString("type"), rs1.getString("comp"));
     }
     
     if (histVals.containsKey(getId())) {
       this.hist = ((HashSet)histVals.get(getId()));
     } else {
       this.hist = new HashSet();
     }
     
     rs1.close();
     st1.close();
     conn1.close();
   }
   
   private void add_hists(HashMap<String, HashSet<String>> histVals, String chid, String val, String type, String comp) {
     if (type.equals("FT")) {
       if (histVals.keySet().contains(chid)) {
         HashSet<String> tmp = (HashSet)histVals.get(chid);
         tmp.add(val);
         histVals.put(chid, tmp);
       } else {
         HashSet<String> tmp = new HashSet();
         tmp.add(val);
         histVals.put(chid, tmp);
       }
     }
     else if (histVals.keySet().contains(chid)) {
       HashSet<String> tmp = (HashSet)histVals.get(chid);
       if (val.startsWith("Autre / Other")) {
         try {
           val = val.split(":")[1].trim();
         } catch (Exception e) {
           try {
             val = comp;
           } catch (Exception f) {
             val = "";
           }
         }
       } else {
         return;
       }
       tmp.add(val);
       histVals.put(chid, tmp);
     } else {
       HashSet<String> tmp = new HashSet();
       if (val.startsWith("Autre / Other")) {
         try {
           val = val.split(":")[1].trim();
         } catch (Exception e) {
           try {
             val = comp;
           } catch (Exception f) {
             val = "";
           }
         }
       } else {
         return;
       }
       tmp.add(val);
       histVals.put(chid, tmp);
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\CharacValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */