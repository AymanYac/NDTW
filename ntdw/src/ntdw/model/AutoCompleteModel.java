 package ntdw.model;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 
 
 
 public class AutoCompleteModel
 {
   private List<String> mots;
   
   public AutoCompleteModel()
   {
     this.mots = new ArrayList();
   }
   
 
 
 
 
   public void add(String mot)
   {
     this.mots.add(mot);
   }
   
 
 
 
 
   public void addAll(List<String> mots)
   {
     this.mots.addAll(mots);
   }
   
 
 
 
 
 
   public List<String> getChainesCorrespondates(String debut)
   {
     List<String> res = new ArrayList();
     for (String s : this.mots) {
       if ((debut != null) && (s.length() >= debut.length()) && (s.toUpperCase().substring(0, debut.length()).equals(debut.toUpperCase()))) {
         res.add(s);
       }
     }
     return res;
   }
   
   public List<String> getToutesChaines() {
     List<String> res = new ArrayList();
     for (String s : this.mots) {
       if (s.length() > 0) {
         res.add(s);
       }
     }
     return res;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\AutoCompleteModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */