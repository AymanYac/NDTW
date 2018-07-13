 package ntdw.service;
 
 import java.io.IOException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.NoSuchProviderException;
 import java.sql.Array;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.Properties;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.ShortBufferException;
 import ntdw.common.CharacType;
 import ntdw.model.CharacValue;
 
 
 public class CharacMockDataService
 {
   private static CharacMockDataService INSTANCE = new CharacMockDataService();
   
 
 
   public static CharacMockDataService getInstance()
   {
     return INSTANCE;
   }
   
   public List<CharacValue> getCharacValues(String cid) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
     List<CharacValue> characValues = new ArrayList();
     Class.forName("org.postgresql.Driver");
     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
     Properties props = new Properties();
     props.setProperty("user", "postgres");
     props.setProperty("password", "Neonec");
     props.setProperty("loginTimeout", "20");
     props.setProperty("connectTimeout", "0");
     props.setProperty("socketTimeout", "0");
     
     Connection conn = DriverManager.getConnection(url, props);
     PreparedStatement st = conn.prepareStatement("select * from public.\"char_schema\" WHERE \"chId\" LIKE ? order by \"chId\"");
     st.setString(1, cid + "%");
     ResultSet rs = st.executeQuery();
     while (rs.next()) {
       if (rs.getBoolean("isClosed"))
       {
         CharacValue characValue = new CharacValue();
         setVLCharac(rs.getString("chName"), characValue, rs.getArray("allowedValues"), rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
         characValues.add(characValue);
       }
       else if (rs.getBoolean("isNumeric")) {
         CharacValue characValue = new CharacValue();
         setNUCharac(rs.getString("chName"), characValue, rs.getArray("allowedNums"), rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
         characValues.add(characValue);
       } else {
         CharacValue characValue = new CharacValue();
         setFTCharac(rs.getString("chName"), characValue, rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
         characValues.add(characValue);
       }
     }
     
     rs.close();
     st.close();
     conn.close();
     
     return characValues;
   }
   
   private void setVLCharac(String name, CharacValue characValue, Array array, String chID, boolean b, String string) throws SQLException {
     characValue.isCritical = b;
     characValue.setId(string);
     characValue.setType(CharacType.VL);
     String[] value = (String[])array.getArray();
     characValue.setListValues(new ArrayList(Arrays.asList(value)));
     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
     characValue.setName(name);
   }
   
   private void setNUCharac(String name, CharacValue characValue, Array array, String chID, boolean b, String string) throws SQLException {
     characValue.isCritical = b;
     characValue.setId(string);
     characValue.setType(CharacType.NU);
     String[] value = (String[])array.getArray();
     characValue.setListValues(new ArrayList(Arrays.asList(value)));
     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
     characValue.setName(name);
   }
   
   private void setFTCharac(String name, CharacValue characValue, String chID, boolean b, String string) {
     characValue.isCritical = b;
     characValue.setId(string);
     characValue.setType(CharacType.FT);
     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
     characValue.setName(name);
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\CharacMockDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */