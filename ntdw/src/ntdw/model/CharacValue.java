/*     */ package ntdw.model;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JTextField;
/*     */ import ntdw.common.CharacType;
/*     */ import ntdw.service.Tools;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharacValue
/*     */ {
/*     */   private String id;
/*     */   private CharacType type;
/*     */   private String name;
/*     */   private List<String> ListValues;
/*     */   private Integer nbCharacteristic;
/*     */   public boolean isCritical;
/*     */   public JTextField text;
/*     */   public JComboBox<String> box;
/*     */   public JTextField comp;
/*     */   public String oldVal;
/*     */   public String oldComp;
/*     */   public HashSet<String> hist;
/*  42 */   public static HashMap<String, HashSet<String>> histVals = new HashMap();
/*  43 */   public static boolean stat = false;
/*  44 */   public static HashMap<String, String> oldVals = new HashMap();
/*  45 */   public static HashMap<String, String> oldComps = new HashMap();
/*     */   
/*     */   public String getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*  51 */   public void setId(String id) { this.id = id; }
/*     */   
/*     */   public CharacType getType() {
/*  54 */     return this.type;
/*     */   }
/*     */   
/*  57 */   public void setType(CharacType type) { this.type = type; }
/*     */   
/*     */   public String getName() {
/*  60 */     return this.name;
/*     */   }
/*     */   
/*  63 */   public void setName(String name) { this.name = name; }
/*     */   
/*     */   public List<String> getListValues() {
/*  66 */     return this.ListValues;
/*     */   }
/*     */   
/*  69 */   public void setListValues(List<String> listValues) { this.ListValues = listValues; }
/*     */   
/*     */   public Integer getNbCharacteristic() {
/*  72 */     return this.nbCharacteristic;
/*     */   }
/*     */   
/*  75 */   public void setNbCharacteristic(Integer nbCharacteristic) { this.nbCharacteristic = nbCharacteristic; }
/*     */   
/*     */   public String getvalue() {
/*  78 */     if (this.text != null) {
/*  79 */       return this.text.getText();
/*     */     }
/*  81 */     return (String)this.box.getSelectedItem();
/*     */   }
/*     */   
/*  84 */   public String getComp() { if (this.comp != null) {
/*  85 */       return this.comp.getText();
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */   
/*  90 */   public void setVal(String selectedAID, boolean b, String cid) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException { if (stat) {
/*  91 */       if (oldVals.containsKey(getId())) {
/*  92 */         this.oldVal = ((String)oldVals.get(getId()));
/*     */       } else {
/*  94 */         this.oldVal = "";
/*     */       }
/*  96 */       if (oldComps.containsKey(getId())) {
/*  97 */         this.oldComp = ((String)oldComps.get(getId()));
/*     */       } else {
/*  99 */         this.oldVal = "";
/*     */       }
/* 101 */       if (histVals.containsKey(getId())) {
/* 102 */         this.hist = ((HashSet)histVals.get(getId()));
/*     */       } else {
/* 104 */         this.hist = new HashSet();
/*     */       }
/*     */     } else {
/* 107 */       this.oldVal = "";
/* 108 */       stat(selectedAID, cid, b);
/*     */     }
/*     */   }
/*     */   
/*     */   private void stat(String selectedAID, String cid, boolean b) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException
/*     */   {
/* 114 */     stat = true;
/* 115 */     oldVals.clear();
/* 116 */     oldComps.clear();
/* 117 */     histVals.clear();
/* 118 */     Class.forName("org.postgresql.Driver");
/* 119 */     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
/* 120 */     Properties props = new Properties();
/* 121 */     props.setProperty("user", "postgres");
/* 122 */     props.setProperty("password", "Neonec");
/* 123 */     props.setProperty("loginTimeout", "20");
/* 124 */     props.setProperty("connectTimeout", "0");
/* 125 */     props.setProperty("socketTimeout", "0");
/*     */     
/* 127 */     Connection conn = DriverManager.getConnection(url, props);
/* 128 */     PreparedStatement st = conn.prepareStatement("select * from public.\"data\" WHERE \"aid\" = ? AND \"chid\" LIKE ?");
/*     */     
/* 130 */     st.setString(1, selectedAID);
/* 131 */     st.setString(2, cid + "%");
/* 132 */     ResultSet rs = st.executeQuery();
/*     */     
/* 134 */     while (rs.next()) {
/* 135 */       oldVals.put(rs.getString("chid"), rs.getString("value"));
/* 136 */       oldComps.put(rs.getString("chid"), rs.getString("comp"));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 148 */     if (oldVals.containsKey(getId())) {
/* 149 */       this.oldVal = ((String)oldVals.get(getId()));
/*     */     }
/* 151 */     if (oldComps.containsKey(getId())) {
/* 152 */       this.oldComp = ((String)oldComps.get(getId()));
/*     */     }
/*     */     
/*     */ 
/* 156 */     rs.close();
/* 157 */     st.close();
/* 158 */     conn.close();
/* 159 */     Connection conn1 = DriverManager.getConnection(url, props);
/* 160 */     PreparedStatement st1 = conn1.prepareStatement("select * from public.\"data\" WHERE \"chid\" LIKE ? and (type = ? or type =?)");
/* 161 */     st1.setString(1, cid + "%");
/* 162 */     st1.setString(2, "FT");
/* 163 */     st1.setString(3, "VL");
/* 164 */     ResultSet rs1 = st1.executeQuery();
/* 165 */     while (rs1.next()) {
/* 166 */       add_hists(histVals, rs1.getString("chid"), rs1.getString("value"), rs1.getString("type"), rs1.getString("comp"));
/*     */     }
/*     */     
/* 169 */     if (histVals.containsKey(getId())) {
/* 170 */       this.hist = ((HashSet)histVals.get(getId()));
/*     */     } else {
/* 172 */       this.hist = new HashSet();
/*     */     }
/*     */     
/* 175 */     rs1.close();
/* 176 */     st1.close();
/* 177 */     conn1.close();
/*     */   }
/*     */   
/*     */   private void add_hists(HashMap<String, HashSet<String>> histVals, String chid, String val, String type, String comp) {
/* 181 */     if (type.equals("FT")) {
/* 182 */       if (histVals.keySet().contains(chid)) {
/* 183 */         HashSet<String> tmp = (HashSet)histVals.get(chid);
/* 184 */         tmp.add(val);
/* 185 */         histVals.put(chid, tmp);
/*     */       } else {
/* 187 */         HashSet<String> tmp = new HashSet();
/* 188 */         tmp.add(val);
/* 189 */         histVals.put(chid, tmp);
/*     */       }
/*     */     }
/* 192 */     else if (histVals.keySet().contains(chid)) {
/* 193 */       HashSet<String> tmp = (HashSet)histVals.get(chid);
/* 194 */       if (val.startsWith("Autre / Other")) {
/*     */         try {
/* 196 */           val = val.split(":")[1].trim();
/*     */         } catch (Exception e) {
/*     */           try {
/* 199 */             val = comp;
/*     */           } catch (Exception f) {
/* 201 */             val = "";
/*     */           }
/*     */         }
/*     */       } else {
/* 205 */         return;
/*     */       }
/* 207 */       tmp.add(val);
/* 208 */       histVals.put(chid, tmp);
/*     */     } else {
/* 210 */       HashSet<String> tmp = new HashSet();
/* 211 */       if (val.startsWith("Autre / Other")) {
/*     */         try {
/* 213 */           val = val.split(":")[1].trim();
/*     */         } catch (Exception e) {
/*     */           try {
/* 216 */             val = comp;
/*     */           } catch (Exception f) {
/* 218 */             val = "";
/*     */           }
/*     */         }
/*     */       } else {
/* 222 */         return;
/*     */       }
/* 224 */       tmp.add(val);
/* 225 */       histVals.put(chid, tmp);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\CharacValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */