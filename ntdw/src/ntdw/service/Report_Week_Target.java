/*     */ package ntdw.service;
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
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import ntdw.model.Target;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Report_Week_Target
/*     */ {
/*  27 */   private static Report_Week_Target INSTANCE = new Report_Week_Target();
/*  28 */   private List<Target> targets = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public static Report_Week_Target getInstance() { return INSTANCE; }
/*     */   
/*     */   public List<Target> getArticles(String login) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException {
/*  37 */     Class.forName("org.postgresql.Driver");
/*  38 */     String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
/*  39 */     Properties props = new Properties();
/*  40 */     props.setProperty("user", "postgres");
/*  41 */     props.setProperty("password", "Neonec");
/*  42 */     props.setProperty("loginTimeout", "20");
/*  43 */     props.setProperty("connectTimeout", "0");
/*  44 */     props.setProperty("socketTimeout", "0");
/*     */     
/*  46 */     Connection conn = DriverManager.getConnection(url, props);
/*  47 */     PreparedStatement st = null;
/*  48 */     if ((login.equals("Elise")) || (login.equals("Aparna"))) {
/*  49 */       st = conn.prepareStatement("select * from items");
/*     */     } else {
/*  51 */       st = conn.prepareStatement("select * from items where classifier = ?");
/*  52 */       st.setString(1, login);
/*     */     }
/*     */     
/*  55 */     ResultSet rs = st.executeQuery();
/*  56 */     HashMap<String, HashMap<Integer, HashMap<String, Integer>>> tmp = new HashMap();
/*  57 */     while (rs.next()) {
/*  58 */       String user = rs.getString("classifier");
/*  59 */       Integer week = Integer.valueOf(rs.getInt("target_week"));
/*  60 */       String status = rs.getString("status");
/*  61 */       if (tmp.containsKey(user)) {
/*  62 */         if (((HashMap)tmp.get(user)).containsKey(week)) {
/*  63 */           if (status.contains("COMPLET")) {
/*  64 */             status = "COMPLET";
/*     */           } else {
/*  66 */             status = "PENDING";
/*     */           }
/*  68 */           if (((HashMap)((HashMap)tmp.get(user)).get(week)).containsKey(status)) {
/*  69 */             Integer count = (Integer)((HashMap)((HashMap)tmp.get(user)).get(week)).get(status);
/*  70 */             count = Integer.valueOf(count.intValue() + 1);
/*  71 */             HashMap tmp_status = new HashMap();
/*  72 */             tmp_status.put(status, count);
/*  73 */             HashMap tmp_week = new HashMap();
/*  74 */             tmp_week.put(week, tmp_status);
/*  75 */             tmp.put(user, tmp_week);
/*     */           }
/*     */           else {
/*  78 */             Integer count = Integer.valueOf(1);
/*  79 */             HashMap tmp_status = new HashMap();
/*  80 */             tmp_status.put(status, count);
/*  81 */             HashMap tmp_week = new HashMap();
/*  82 */             tmp_week.put(week, tmp_status);
/*  83 */             tmp.put(user, tmp_week);
/*     */           }
/*     */         } else {
/*  86 */           if (status.contains("COMPLET")) {
/*  87 */             status = "COMPLET";
/*     */           } else {
/*  89 */             status = "PENDING";
/*     */           }
/*  91 */           Integer count = Integer.valueOf(1);
/*  92 */           HashMap tmp_status = new HashMap();
/*  93 */           tmp_status.put(status, count);
/*  94 */           HashMap tmp_week = new HashMap();
/*  95 */           tmp_week.put(week, tmp_status);
/*  96 */           tmp.put(user, tmp_week);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 101 */         if (status.contains("COMPLET")) {
/* 102 */           status = "COMPLET";
/*     */         } else {
/* 104 */           status = "PENDING";
/*     */         }
/* 106 */         Integer count = Integer.valueOf(1);
/* 107 */         HashMap tmp_status = new HashMap();
/* 108 */         tmp_status.put(status, count);
/* 109 */         HashMap tmp_week = new HashMap();
/* 110 */         tmp_week.put(week, tmp_status);
/* 111 */         tmp.put(user, tmp_week);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     return this.targets;
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\Report_Week_Target.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */