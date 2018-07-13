 package ntdw.model;
 
 public class Article {
   private String id;
   private String description;
   private String family;
   private String artClass;
   private String manufact;
   private int prio;
   private String status;
   private String question;
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public String getDescription() {
     return this.description;
   }
   
   public void setDescription(String description) { this.description = description; }
   
   public String getFamily() {
     return this.family;
   }
   
   public void setFamily(String family) { this.family = family; }
   
   public String getArtClass() {
     return this.artClass;
   }
   
   public void setArtClass(String artClass) { this.artClass = artClass; }
   
   public String getManufact() {
     return this.manufact;
   }
   
   public void setManufact(String manufact) { this.manufact = manufact; }
   
   public int getPrio() {
     return this.prio;
   }
   
   public void setPrio(int prio) { this.prio = prio; }
   
   
   
   
   public String getStatus() {
     return this.status;
   }
   
   public void setStatus(String status) { this.status = status; }
   
   public void setQuestion(String string) {
     this.question = string;
   }
   
   public String getQuestion() {
     return this.question;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\Article.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */