 package ntdw.common;
 
 import java.util.ArrayList;
 import java.util.List;
 import javax.swing.table.AbstractTableModel;
 import ntdw.model.Article;
 
 
 
 
 public class ArticleTableModel
   extends AbstractTableModel
 {
   private static final long serialVersionUID = 1L;
   private List<Article> articles = new ArrayList();
   private String[] columnNames = { "Article ID", "Short description", "Family", "Class", "Manufact.", "Prio.", "Quest.", "Status" };
   
   public ArticleTableModel(List<Article> articles) {
     this.articles = articles;
   }
   
 
 
   public ArticleTableModel(List<Article> articles2, String login) {}
   
 
   public boolean isCellEditable(int row, int col)
   {
     return col==0;
   }
   
   public String getColumnName(int columnIndex)
   {
     return this.columnNames[columnIndex];
   }
   
   public int getRowCount()
   {
     return this.articles.size();
   }
   
   public int getColumnCount()
   {
     return 8;
   }
   
   public Object getValueAt(int rowIndex, int columnIndex)
   {
     Article article = (Article)this.articles.get(rowIndex);
     switch (columnIndex) {
     case 0: 
       return article.getId();
     case 1: 
       return article.getDescription();
     case 2: 
       return article.getFamily();
     case 3: 
       return article.getArtClass();
     case 4: 
       return article.getManufact();
     case 5: 
       return Integer.valueOf(article.getPrio());
     case 6: 
       return article.getQuestion();
     case 7: 
       return article.getStatus();
     }
     return null;
   }
   
   public Class<?> getColumnClass(int columnIndex)
   {
     switch (columnIndex) {
     case 0: 
       return ButtonColumn.class;
     case 1: 
       return String.class;
     case 2: 
       return String.class;
     case 3: 
       return String.class;
     case 4: 
       return String.class;
     case 5: 
       return Integer.TYPE;
     case 6: 
       return String.class;
     case 7: 
       return String.class;
     }
     return String.class;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ArticleTableModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */