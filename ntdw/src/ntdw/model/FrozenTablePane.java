/*    */ package ntdw.model;
/*    */ 
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.JViewport;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ public class FrozenTablePane extends javax.swing.JScrollPane
/*    */ {
/*    */   public FrozenTablePane(JTable table, int colsToFreeze)
/*    */   {
/* 11 */     super(table);
/* 12 */     TableModel model = table.getModel();
/*    */     
/* 14 */     TableModel frozenModel = new javax.swing.table.DefaultTableModel(
/* 15 */       model.getRowCount(), 
/* 16 */       colsToFreeze);
/*    */     
/* 18 */     for (int i = 0; i < model.getRowCount(); i++) {
/* 19 */       for (int j = 0; j < colsToFreeze; j++) {
/* 20 */         String value = (String)model.getValueAt(i, j);
/* 21 */         frozenModel.setValueAt(value, i, j);
/*    */       }
/*    */     }
/*    */     
/* 25 */     JTable frozenTable = new JTable(frozenModel);
/*    */     
/* 27 */     for (int j = 0; j < colsToFreeze; j++)
/* 28 */       table.removeColumn(table.getColumnModel().getColumn(0));
/* 29 */     table.setAutoResizeMode(0);
/*    */     
/* 31 */     javax.swing.table.JTableHeader header = table.getTableHeader();
/* 32 */     frozenTable.setBackground(header.getBackground());
/* 33 */     frozenTable.setAutoResizeMode(0);
/* 34 */     frozenTable.setEnabled(false);
/*    */     
/* 36 */     JViewport viewport = new JViewport();
/* 37 */     viewport.setView(frozenTable);
/* 38 */     viewport.setPreferredSize(frozenTable.getPreferredSize());
/* 39 */     setRowHeaderView(viewport);
/* 40 */     setCorner("UPPER_LEFT_CORNER", 
/* 41 */       frozenTable.getTableHeader());
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\FrozenTablePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */