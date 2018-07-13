/*    */ package ntdw.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoCompleteModel
/*    */ {
/*    */   private List<String> mots;
/*    */   
/*    */   public AutoCompleteModel()
/*    */   {
/* 17 */     this.mots = new ArrayList();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(String mot)
/*    */   {
/* 26 */     this.mots.add(mot);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAll(List<String> mots)
/*    */   {
/* 35 */     this.mots.addAll(mots);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public List<String> getChainesCorrespondates(String debut)
/*    */   {
/* 45 */     List<String> res = new ArrayList();
/* 46 */     for (String s : this.mots) {
/* 47 */       if ((debut != null) && (s.length() >= debut.length()) && (s.toUpperCase().substring(0, debut.length()).equals(debut.toUpperCase()))) {
/* 48 */         res.add(s);
/*    */       }
/*    */     }
/* 51 */     return res;
/*    */   }
/*    */   
/*    */   public List<String> getToutesChaines() {
/* 55 */     List<String> res = new ArrayList();
/* 56 */     for (String s : this.mots) {
/* 57 */       if (s.length() > 0) {
/* 58 */         res.add(s);
/*    */       }
/*    */     }
/* 61 */     return res;
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\AutoCompleteModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */