/*     */ package ntdw.models;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FixedColumnTable
/*     */   implements ChangeListener, PropertyChangeListener
/*     */ {
/*     */   public JTable main;
/*     */   public JTable fixed;
/*     */   public JScrollPane scrollPane;
/*     */   
/*     */   public FixedColumnTable(int fixedColumns, JScrollPane scrollPane)
/*     */   {
/*  35 */     this.scrollPane = scrollPane;
/*     */     
/*  37 */     this.main = ((JTable)scrollPane.getViewport().getView());
/*  38 */     this.main.setAutoCreateColumnsFromModel(true);
/*  39 */     this.main.addPropertyChangeListener(this);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  44 */     int totalColumns = this.main.getColumnCount();
/*     */     
/*  46 */     this.fixed = new JTable();
/*  47 */     this.fixed.setAutoCreateColumnsFromModel(false);
/*  48 */     this.fixed.setModel(this.main.getModel());
/*  49 */     this.fixed.setSelectionModel(this.main.getSelectionModel());
/*  50 */     this.fixed.setFocusable(false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  55 */     for (int i = 0; i < fixedColumns; i++)
/*     */     {
/*  57 */       TableColumnModel columnModel = this.main.getColumnModel();
/*  58 */       TableColumn column = columnModel.getColumn(0);
/*  59 */       columnModel.removeColumn(column);
/*  60 */       this.fixed.getColumnModel().addColumn(column);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  65 */     this.fixed.setPreferredScrollableViewportSize(this.fixed.getPreferredSize());
/*  66 */     scrollPane.setRowHeaderView(this.fixed);
/*  67 */     scrollPane.setCorner("UPPER_LEFT_CORNER", this.fixed.getTableHeader());
/*     */     
/*     */ 
/*     */ 
/*  71 */     scrollPane.getRowHeader().addChangeListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JTable getFixedTable()
/*     */   {
/*  79 */     return this.fixed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void stateChanged(ChangeEvent e)
/*     */   {
/*  88 */     JViewport viewport = (JViewport)e.getSource();
/*  89 */     this.scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void propertyChange(PropertyChangeEvent e)
/*     */   {
/*  98 */     if ("selectionModel".equals(e.getPropertyName()))
/*     */     {
/* 100 */       this.fixed.setSelectionModel(this.main.getSelectionModel());
/*     */     }
/*     */     
/* 103 */     if ("model".equals(e.getPropertyName()))
/*     */     {
/* 105 */       this.fixed.setModel(this.main.getModel());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\models\FixedColumnTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */