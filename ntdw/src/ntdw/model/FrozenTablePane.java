 package ntdw.model;
 
 import javax.swing.JTable;
 import javax.swing.JViewport;
 import javax.swing.table.TableModel;
 
 public class FrozenTablePane extends javax.swing.JScrollPane
 {
   public FrozenTablePane(JTable table, int colsToFreeze)
   {
     super(table);
     TableModel model = table.getModel();
     
     TableModel frozenModel = new javax.swing.table.DefaultTableModel(
       model.getRowCount(), 
       colsToFreeze);
     
     for (int i = 0; i < model.getRowCount(); i++) {
       for (int j = 0; j < colsToFreeze; j++) {
         String value = (String)model.getValueAt(i, j);
         frozenModel.setValueAt(value, i, j);
       }
     }
     
     JTable frozenTable = new JTable(frozenModel);
     
     for (int j = 0; j < colsToFreeze; j++)
       table.removeColumn(table.getColumnModel().getColumn(0));
     table.setAutoResizeMode(0);
     
     javax.swing.table.JTableHeader header = table.getTableHeader();
     frozenTable.setBackground(header.getBackground());
     frozenTable.setAutoResizeMode(0);
     frozenTable.setEnabled(false);
     
     JViewport viewport = new JViewport();
     viewport.setView(frozenTable);
     viewport.setPreferredSize(frozenTable.getPreferredSize());
     setRowHeaderView(viewport);
     setCorner("UPPER_LEFT_CORNER", 
       frozenTable.getTableHeader());
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\model\FrozenTablePane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */