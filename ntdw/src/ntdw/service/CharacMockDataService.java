/*     */ package ntdw.service;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.sql.Array;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import ntdw.common.CharacType;
/*     */ import ntdw.model.CharacValue;
/*     */ 
/*     */ 
/*     */ public class CharacMockDataService
/*     */ {
/*  27 */   private static CharacMockDataService INSTANCE = new CharacMockDataService();
/*     */   
/*     */ 
/*     */ 
/*     */   public static CharacMockDataService getInstance()
/*     */   {
/*  33 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public List<CharacValue> getCharacValues(String cid) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
/*  37 */     List<CharacValue> characValues = new ArrayList();
/*  38 */     Class.forName("org.postgresql.Driver");
/*  39 */     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
/*  40 */     Properties props = new Properties();
/*  41 */     props.setProperty("user", "postgres");
/*  42 */     props.setProperty("password", "Neonec");
/*  43 */     props.setProperty("loginTimeout", "20");
/*  44 */     props.setProperty("connectTimeout", "0");
/*  45 */     props.setProperty("socketTimeout", "0");
/*     */     
/*  47 */     Connection conn = DriverManager.getConnection(url, props);
/*  48 */     PreparedStatement st = conn.prepareStatement("select * from public.\"char_schema\" WHERE \"chId\" LIKE ? order by \"chId\"");
/*  49 */     st.setString(1, cid + "%");
/*  50 */     ResultSet rs = st.executeQuery();
/*  51 */     while (rs.next()) {
/*  52 */       if (rs.getBoolean("isClosed"))
/*     */       {
/*  54 */         CharacValue characValue = new CharacValue();
/*  55 */         setVLCharac(rs.getString("chName"), characValue, rs.getArray("allowedValues"), rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
/*  56 */         characValues.add(characValue);
/*     */       }
/*  58 */       else if (rs.getBoolean("isNumeric")) {
/*  59 */         CharacValue characValue = new CharacValue();
/*  60 */         setNUCharac(rs.getString("chName"), characValue, rs.getArray("allowedNums"), rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
/*  61 */         characValues.add(characValue);
/*     */       } else {
/*  63 */         CharacValue characValue = new CharacValue();
/*  64 */         setFTCharac(rs.getString("chName"), characValue, rs.getString("chID"), rs.getBoolean("isCritical"), rs.getString("chId"));
/*  65 */         characValues.add(characValue);
/*     */       }
/*     */     }
/*     */     
/*  69 */     rs.close();
/*  70 */     st.close();
/*  71 */     conn.close();
/*     */     
/*  73 */     return characValues;
/*     */   }
/*     */   
/*     */   private void setVLCharac(String name, CharacValue characValue, Array array, String chID, boolean b, String string) throws SQLException {
/*  77 */     characValue.isCritical = b;
/*  78 */     characValue.setId(string);
/*  79 */     characValue.setType(CharacType.VL);
/*  80 */     String[] value = (String[])array.getArray();
/*  81 */     characValue.setListValues(new ArrayList(Arrays.asList(value)));
/*  82 */     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
/*  83 */     characValue.setName(name);
/*     */   }
/*     */   
/*     */   private void setNUCharac(String name, CharacValue characValue, Array array, String chID, boolean b, String string) throws SQLException {
/*  87 */     characValue.isCritical = b;
/*  88 */     characValue.setId(string);
/*  89 */     characValue.setType(CharacType.NU);
/*  90 */     String[] value = (String[])array.getArray();
/*  91 */     characValue.setListValues(new ArrayList(Arrays.asList(value)));
/*  92 */     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
/*  93 */     characValue.setName(name);
/*     */   }
/*     */   
/*     */   private void setFTCharac(String name, CharacValue characValue, String chID, boolean b, String string) {
/*  97 */     characValue.isCritical = b;
/*  98 */     characValue.setId(string);
/*  99 */     characValue.setType(CharacType.FT);
/* 100 */     characValue.setNbCharacteristic(Integer.valueOf(Integer.parseInt(chID.split("\\.")[4])));
/* 101 */     characValue.setName(name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\CharacMockDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */