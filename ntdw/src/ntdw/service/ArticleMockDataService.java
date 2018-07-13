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
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.TemporalField;
/*     */ import java.time.temporal.WeekFields;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.ShortBufferException;
/*     */ import ntdw.model.Article;
/*     */ 
/*     */ 
/*     */ public class ArticleMockDataService
/*     */ {
/*  28 */   private static ArticleMockDataService INSTANCE = new ArticleMockDataService();
/*  29 */   private List<Article> articles = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  35 */   public static ArticleMockDataService getInstance() { return INSTANCE; }
/*     */   
/*  37 */   public Boolean stat = Boolean.valueOf(false);
/*     */   public int weekTarget;
/*     */   public int compDay;
/*     */   public int compWeek;
/*     */   
/*     */   public String getWeekTarget(String login)
/*     */     throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
/*     */   {
/*  45 */     stat(login);
/*  46 */     return String.valueOf(this.weekTarget);
/*     */   }
/*     */   
/*     */   public String getCompWeek(String login)
/*     */     throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
/*     */   {
/*  52 */     stat(login);
/*  53 */     return String.valueOf(this.compWeek);
/*     */   }
/*     */   
/*     */   public String getCompDay(String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException {
/*  57 */     stat(login);
/*  58 */     return String.valueOf(this.compDay);
/*     */   }
/*     */   
/*     */   private void stat(String login) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
/*     */   {
/*  63 */     if (!this.stat.booleanValue())
/*     */     {
/*  65 */       this.stat = Boolean.valueOf(true);
/*  66 */       this.articles.clear();
/*  67 */       int weekTarget = 0;
/*  68 */       int compWeek = 0;
/*  69 */       int compDay = 0;
/*  70 */       Class.forName("org.postgresql.Driver");
/*  71 */       String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
/*  72 */       Properties props = new Properties();
/*  73 */       props.setProperty("user", "postgres");
/*  74 */       props.setProperty("password", "Neonec");
/*  75 */       props.setProperty("loginTimeout", "20");
/*  76 */       props.setProperty("connectTimeout", "0");
/*  77 */       props.setProperty("socketTimeout", "0");
/*     */       
/*  79 */       Connection conn = DriverManager.getConnection(url, props);
/*  80 */       PreparedStatement st = null;
/*  81 */       if ((login.equals("Elise")) || (login.equals("Aparna"))) {
/*  82 */         st = conn.prepareStatement("WITH articles as (select items.aid,sd,items.cid,value AS manuf,classifier,prio,status,ques,ledate from public.items LEFT JOIN public.data ON items.aid=data.aid AND data.chid=? WHERE items.status NOT IN (?,?,?,?) and items.visible = 'OUI' order by loadtime ) select articles.aid,sd,family,articles.cid,class,manuf,prio,status,ques,ledate from articles,public.class_hierarchy WHERE articles.cid=class_hierarchy.cid");
/*  83 */         st.setString(2, "COMPLET");
/*  84 */         st.setString(3, "REVIEWED");
/*  85 */         st.setString(4, "REWORK:COMPLET");
/*  86 */         st.setString(5, "REWORK:REVIEWED");
/*     */       } else {
/*  88 */         st = conn.prepareStatement("WITH articles as (select items.aid,sd,items.cid,value AS manuf,classifier,prio,status,ques,ledate from public.items LEFT JOIN public.data ON items.aid=data.aid AND data.chid=? WHERE items.classifier=? AND items.status NOT IN (?,?,?,?) and items.visible = 'OUI' order by loadtime) select articles.aid,sd,family,articles.cid,class,manuf,prio,status,ques,ledate from articles,public.class_hierarchy WHERE articles.cid=class_hierarchy.cid");
/*  89 */         st.setString(2, login);
/*  90 */         st.setString(3, "COMPLET");
/*  91 */         st.setString(4, "REVIEWED");
/*  92 */         st.setString(5, "REWORK:COMPLET");
/*  93 */         st.setString(6, "REWORK:REVIEWED");
/*     */       }
/*  95 */       st.setString(1, "MANUF");
/*  96 */       ResultSet rs = st.executeQuery();
/*  97 */       while (rs.next())
/*     */       {
/*  99 */         Article article = new Article();
/* 100 */         article.setId(rs.getString("aid"));
/* 101 */         article.setDescription(rs.getString("sd"));
/* 102 */         article.setFamily(rs.getString("cid").substring(0, 11) + " " + rs.getString("family"));
/* 103 */         article.setArtClass(rs.getString("cid") + " " + rs.getString("class"));
/* 104 */         article.setManufact(rs.getString("manuf"));
/* 105 */         article.setPrio(rs.getInt("prio"));

/* 107 */         article.setQuestion(rs.getString("ques"));
/* 108 */         article.setStatus(rs.getString("status"));
/*     */         
/* 110 */         this.articles.add(article);
/*     */         
/* 112 */         
/* 115 */         if ((rs.getString("status").equals("COMPLET")) || (rs.getString("status").equals("REVIEWED"))) {
/* 116 */           LocalDate ledate = (LocalDate)rs.getObject("ledate", LocalDate.class);
/* 117 */           if (ledate != null) {
/* 118 */             if (ledate.equals(LocalDate.now())) {
/* 119 */               compDay++;
/* 120 */               compWeek++;
/*     */             } else {
/* 122 */               TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
/* 123 */               if (ledate.get(woy) == LocalDate.now().get(woy)) {
/* 124 */                 compWeek++;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 132 */       rs.close();
/* 133 */       st.close();
/* 134 */       conn.close();
/* 135 */       this.weekTarget = weekTarget;
/* 136 */       this.compDay = compDay;
/* 137 */       this.compWeek = compWeek;
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Article> getArticles(String login, boolean review) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, SQLException, IOException
/*     */   {
/* 143 */     if (!review) {
/* 144 */       stat(login);
/*     */     } else {
/* 146 */       stat2(login);
/*     */     }
/* 148 */     return this.articles;
/*     */   }
/*     */   
/*     */   private void stat2(String login) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException {
/* 152 */     if (!this.stat.booleanValue())
/*     */     {
/*     */ 
/* 155 */       this.stat = Boolean.valueOf(true);
/* 156 */       this.articles.clear();
/* 157 */       int weekTarget = 0;
/* 158 */       int compWeek = 0;
/* 159 */       int compDay = 0;
/* 160 */       Class.forName("org.postgresql.Driver");
/* 161 */       String url = "jdbc:postgresql://" + Tools.load_ip() + ":5432/" + Tools.getDatabaseName();
/* 162 */       Properties props = new Properties();
/* 163 */       props.setProperty("user", "postgres");
/* 164 */       props.setProperty("password", "Neonec");
/* 165 */       props.setProperty("loginTimeout", "20");
/* 166 */       props.setProperty("connectTimeout", "0");
/* 167 */       props.setProperty("socketTimeout", "0");
/*     */       
/* 169 */       Connection conn = DriverManager.getConnection(url, props);
/* 170 */       PreparedStatement st = null;
/* 171 */       if ((login.equals("Elise")) || (login.equals("Aparna"))) {
/* 172 */         st = conn.prepareStatement("select items.aid,sd,family,items.cid,class,prio,status,classifier,reviewer, ques from public.items,public.class_hierarchy WHERE items.cid=class_hierarchy.cid AND  \"status\" IN (?,?,?,?) and visible = 'OUI' order by loadtime");
/* 173 */         st.setString(1, "COMPLET");
/* 174 */         st.setString(2, "REVIEWED");
/* 175 */         st.setString(3, "REWORK:COMPLET");
/* 176 */         st.setString(4, "REWORK:REVIEWED");
/*     */       }
/*     */       else {
/* 179 */         st = conn.prepareStatement("select items.aid,sd,family,items.cid,class,prio,status,classifier,reviewer, ques from public.items,public.class_hierarchy WHERE items.cid=class_hierarchy.cid AND ( \"reviewer\" = ? or \"classifier\" = ? ) AND  \"status\" IN (?,?,?,?) and visible = 'OUI' order by loadtime");
/* 180 */         st.setString(1, login);
				  st.setString(2, login);
/* 181 */         st.setString(3, "COMPLET");
/* 182 */         st.setString(4, "REVIEWED");
/* 183 */         st.setString(5, "REWORK:COMPLET");
/* 184 */         st.setString(6, "REWORK:REVIEWED");
/*     */       }
/*     */       
/* 187 */       ResultSet rs = st.executeQuery();
/* 188 */       while (rs.next())
/*     */       {
/* 190 */         Article article = new Article();
/* 191 */         article.setId(rs.getString("aid"));
/* 192 */         article.setDescription(rs.getString("sd"));
/* 193 */         article.setFamily(rs.getString("cid").substring(0, 11) + " " + rs.getString("family"));
/* 194 */         article.setArtClass(rs.getString("cid") + " " + rs.getString("class"));
/* 195 */         article.setManufact(rs.getString("classifier"));
/* 196 */         article.setPrio(rs.getInt("prio"));
/* 197 */         article.setQuestion(rs.getString("ques"));
/* 199 */         article.setStatus(rs.getString("status"));
/*     */         
/* 201 */         this.articles.add(article);
/*     */       }
/*     */       
/* 204 */       rs.close();
/* 205 */       st.close();
/* 206 */       conn.close();
/* 207 */       this.weekTarget = weekTarget;
/* 208 */       this.compDay = compDay;
/* 209 */       this.compWeek = compWeek;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\ArticleMockDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */