 package ntdw.models;
 
 import java.awt.Point;
 import java.beans.PropertyChangeEvent;
 import java.beans.PropertyChangeListener;
 import javax.swing.JScrollBar;
 import javax.swing.JScrollPane;
 import javax.swing.JTable;
 import javax.swing.JViewport;
 import javax.swing.event.ChangeEvent;
 import javax.swing.event.ChangeListener;
 import javax.swing.table.TableColumn;
 import javax.swing.table.TableColumnModel;
 
 
 
 
 
 
 
 
 
 
 
 
 public class FixedColumnTable
   implements ChangeListener, PropertyChangeListener
 {
   public JTable main;
   public JTable fixed;
   public JScrollPane scrollPane;
   
   public FixedColumnTable(int fixedColumns, JScrollPane scrollPane)
   {
     this.scrollPane = scrollPane;
     
     this.main = ((JTable)scrollPane.getViewport().getView());
     this.main.setAutoCreateColumnsFromModel(true);
     this.main.addPropertyChangeListener(this);
     
 
 
 
     int totalColumns = this.main.getColumnCount();
     
     this.fixed = new JTable();
     this.fixed.setAutoCreateColumnsFromModel(false);
     this.fixed.setModel(this.main.getModel());
     this.fixed.setSelectionModel(this.main.getSelectionModel());
     this.fixed.setFocusable(false);
     
 
 
 
     for (int i = 0; i < fixedColumns; i++)
     {
       TableColumnModel columnModel = this.main.getColumnModel();
       TableColumn column = columnModel.getColumn(0);
       columnModel.removeColumn(column);
       this.fixed.getColumnModel().addColumn(column);
     }
     
 
 
     this.fixed.setPreferredScrollableViewportSize(this.fixed.getPreferredSize());
     scrollPane.setRowHeaderView(this.fixed);
     scrollPane.setCorner("UPPER_LEFT_CORNER", this.fixed.getTableHeader());
     
 
 
     scrollPane.getRowHeader().addChangeListener(this);
   }
   
 
 
 
   public JTable getFixedTable()
   {
     return this.fixed;
   }
   
 
 
 
 
   public void stateChanged(ChangeEvent e)
   {
     JViewport viewport = (JViewport)e.getSource();
     this.scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
   }
   
 
 
 
 
   public void propertyChange(PropertyChangeEvent e)
   {
     if ("selectionModel".equals(e.getPropertyName()))
     {
       this.fixed.setSelectionModel(this.main.getSelectionModel());
     }
     
     if ("model".equals(e.getPropertyName()))
     {
       this.fixed.setModel(this.main.getModel());
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\models\FixedColumnTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */