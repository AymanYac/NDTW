/*    */ package ntdw.common;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ import ntdw.model.Article;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArticleTableModel
/*    */   extends AbstractTableModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   private List<Article> articles = new ArrayList();
/* 16 */   private String[] columnNames = { "Article ID", "Short description", "Family", "Class", "Manufact.", "Prio.", "Quest.", "Status" };
/*    */   
/*    */   public ArticleTableModel(List<Article> articles) {
/* 20 */     this.articles = articles;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public ArticleTableModel(List<Article> articles2, String login) {}
/*    */   
/*    */ 
/*    */   public boolean isCellEditable(int row, int col)
/*    */   {
/* 30 */     return col==0;
/*    */   }
/*    */   
/*    */   public String getColumnName(int columnIndex)
/*    */   {
/* 35 */     return this.columnNames[columnIndex];
/*    */   }
/*    */   
/*    */   public int getRowCount()
/*    */   {
/* 40 */     return this.articles.size();
/*    */   }
/*    */   
/*    */   public int getColumnCount()
/*    */   {
/* 45 */     return 8;
/*    */   }
/*    */   
/*    */   public Object getValueAt(int rowIndex, int columnIndex)
/*    */   {
/* 50 */     Article article = (Article)this.articles.get(rowIndex);
/* 51 */     switch (columnIndex) {
/*    */     case 0: 
/* 53 */       return article.getId();
/*    */     case 1: 
/* 55 */       return article.getDescription();
/*    */     case 2: 
/* 57 */       return article.getFamily();
/*    */     case 3: 
/* 59 */       return article.getArtClass();
/*    */     case 4: 
/* 61 */       return article.getManufact();
/*    */     case 5: 
/* 63 */       return Integer.valueOf(article.getPrio());
/*    */     case 6: 
/* 67 */       return article.getQuestion();
/*    */     case 7: 
/* 69 */       return article.getStatus();
/*    */     }
/* 71 */     return null;
/*    */   }
/*    */   
/*    */   public Class<?> getColumnClass(int columnIndex)
/*    */   {
/* 76 */     switch (columnIndex) {
/*    */     case 0: 
/* 78 */       return ButtonColumn.class;
/*    */     case 1: 
/* 80 */       return String.class;
/*    */     case 2: 
/* 82 */       return String.class;
/*    */     case 3: 
/* 84 */       return String.class;
/*    */     case 4: 
/* 86 */       return String.class;
/*    */     case 5: 
/* 88 */       return Integer.TYPE;
/*    */     case 6: 
/* 90 */       return String.class;
/*    */     case 7: 
/* 92 */       return String.class;
/*    */     }
/* 96 */     return String.class;
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ArticleTableModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */