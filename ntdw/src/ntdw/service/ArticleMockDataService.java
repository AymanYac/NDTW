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
 import java.time.LocalDate;
 import java.time.temporal.TemporalField;
 import java.time.temporal.WeekFields;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;
 import java.util.Properties;
 import javax.crypto.BadPaddingException;
 import javax.crypto.IllegalBlockSizeException;
 import javax.crypto.NoSuchPaddingException;
 import javax.crypto.ShortBufferException;
 import ntdw.model.Article;
 
 
 public class ArticleMockDataService
 {
   private static ArticleMockDataService INSTANCE = new ArticleMockDataService();
   private List<Article> articles = new ArrayList();
   
 
 
 
 
   public static ArticleMockDataService getInstance() { return INSTANCE; }
   
   public Boolean stat = Boolean.valueOf(false);
   public int weekTarget;
   public int compDay;
   public int compWeek;
   
   public String getWeekTarget(String login)
     throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
   {
     stat(login);
     return String.valueOf(this.weekTarget);
   }
   
   public String getCompWeek(String login)
     throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
   {
     stat(login);
     return String.valueOf(this.compWeek);
   }
   
   public String getCompDay(String login) throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException {
     stat(login);
     return String.valueOf(this.compDay);
   }
   
   private void stat(String login) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
   {
     if (!this.stat.booleanValue())
     {
       this.stat = Boolean.valueOf(true);
       this.articles.clear();
       int weekTarget = 0;
       int compWeek = 0;
       int compDay = 0;
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
         st = conn.prepareStatement("WITH articles as (select items.aid,sd,items.cid,value AS manuf,classifier,prio,status,ques,ledate from public.items LEFT JOIN public.data ON items.aid=data.aid AND data.chid=? WHERE items.status NOT IN (?,?,?,?) and items.visible = 'OUI' order by loadtime ) select articles.aid,sd,family,articles.cid,class,manuf,prio,status,ques,ledate from articles,public.class_hierarchy WHERE articles.cid=class_hierarchy.cid");
         st.setString(2, "COMPLET");
         st.setString(3, "REVIEWED");
         st.setString(4, "REWORK:COMPLET");
         st.setString(5, "REWORK:REVIEWED");
       } else {
         st = conn.prepareStatement("WITH articles as (select items.aid,sd,items.cid,value AS manuf,classifier,prio,status,ques,ledate from public.items LEFT JOIN public.data ON items.aid=data.aid AND data.chid=? WHERE items.classifier=? AND items.status NOT IN (?,?,?,?) and items.visible = 'OUI' order by loadtime) select articles.aid,sd,family,articles.cid,class,manuf,prio,status,ques,ledate from articles,public.class_hierarchy WHERE articles.cid=class_hierarchy.cid");
         st.setString(2, login);
         st.setString(3, "COMPLET");
         st.setString(4, "REVIEWED");
         st.setString(5, "REWORK:COMPLET");
         st.setString(6, "REWORK:REVIEWED");
       }
       st.setString(1, "MANUF");
       ResultSet rs = st.executeQuery();
       while (rs.next())
       {
         Article article = new Article();
         article.setId(rs.getString("aid"));
         article.setDescription(rs.getString("sd"));
         article.setFamily(rs.getString("cid").substring(0, 11) + " " + rs.getString("family"));
         article.setArtClass(rs.getString("cid") + " " + rs.getString("class"));
         article.setManufact(rs.getString("manuf"));
         article.setPrio(rs.getInt("prio"));

         article.setQuestion(rs.getString("ques"));
         article.setStatus(rs.getString("status"));
         
         this.articles.add(article);
         
         
         if ((rs.getString("status").equals("COMPLET")) || (rs.getString("status").equals("REVIEWED"))) {
           LocalDate ledate = (LocalDate)rs.getObject("ledate", LocalDate.class);
           if (ledate != null) {
             if (ledate.equals(LocalDate.now())) {
               compDay++;
               compWeek++;
             } else {
               TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
               if (ledate.get(woy) == LocalDate.now().get(woy)) {
                 compWeek++;
               }
             }
           }
         }
       }
       
 
       rs.close();
       st.close();
       conn.close();
       this.weekTarget = weekTarget;
       this.compDay = compDay;
       this.compWeek = compWeek;
     }
   }
   
   public List<Article> getArticles(String login, boolean review) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, SQLException, IOException
   {
     if (!review) {
       stat(login);
     } else {
       stat2(login);
     }
     return this.articles;
   }
   
   private void stat2(String login) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException {
     if (!this.stat.booleanValue())
     {
 
       this.stat = Boolean.valueOf(true);
       this.articles.clear();
       int weekTarget = 0;
       int compWeek = 0;
       int compDay = 0;
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
         st = conn.prepareStatement("select items.aid,sd,family,items.cid,class,prio,status,classifier,reviewer, ques from public.items,public.class_hierarchy WHERE items.cid=class_hierarchy.cid AND  \"status\" IN (?,?,?,?) and visible = 'OUI' order by loadtime");
         st.setString(1, "COMPLET");
         st.setString(2, "REVIEWED");
         st.setString(3, "REWORK:COMPLET");
         st.setString(4, "REWORK:REVIEWED");
       }
       else {
         st = conn.prepareStatement("select items.aid,sd,family,items.cid,class,prio,status,classifier,reviewer, ques from public.items,public.class_hierarchy WHERE items.cid=class_hierarchy.cid AND ( \"reviewer\" = ? or \"classifier\" = ? ) AND  \"status\" IN (?,?,?,?) and visible = 'OUI' order by loadtime");
         st.setString(1, login);
				  st.setString(2, login);
         st.setString(3, "COMPLET");
         st.setString(4, "REVIEWED");
         st.setString(5, "REWORK:COMPLET");
         st.setString(6, "REWORK:REVIEWED");
       }
       
       ResultSet rs = st.executeQuery();
       while (rs.next())
       {
         Article article = new Article();
         article.setId(rs.getString("aid"));
         article.setDescription(rs.getString("sd"));
         article.setFamily(rs.getString("cid").substring(0, 11) + " " + rs.getString("family"));
         article.setArtClass(rs.getString("cid") + " " + rs.getString("class"));
         article.setManufact(rs.getString("classifier"));
         article.setPrio(rs.getInt("prio"));
         article.setQuestion(rs.getString("ques"));
         article.setStatus(rs.getString("status"));
         
         this.articles.add(article);
       }
       
       rs.close();
       st.close();
       conn.close();
       this.weekTarget = weekTarget;
       this.compDay = compDay;
       this.compWeek = compWeek;
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\ArticleMockDataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */