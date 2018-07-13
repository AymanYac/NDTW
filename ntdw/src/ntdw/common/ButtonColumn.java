/*     */ package ntdw.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.AbstractCellEditor;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.LineBorder;
/*     */ import javax.swing.table.TableCellEditor;
/*     */ import javax.swing.table.TableCellRenderer;
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
/*     */ public class ButtonColumn
/*     */   extends AbstractCellEditor
/*     */   implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
/*     */ {
/*     */   private JTable table;
/*     */   private Action action;
/*     */   private int mnemonic;
/*     */   private Border originalBorder;
/*     */   private Border focusBorder;
/*     */   private JButton renderButton;
/*     */   private JButton editButton;
/*     */   private Object editorValue;
/*     */   private boolean isButtonColumnEditor;
/*     */   
/*     */   public ButtonColumn(JTable table, Action action, int column)
/*     */   {
/*  60 */     this.table = table;
/*  61 */     this.action = action;
/*     */     
/*  63 */     this.renderButton = new JLinkButton();
/*  64 */     this.editButton = new JLinkButton();
/*  65 */     this.editButton.addActionListener(this);
/*  66 */     setFocusBorder(new LineBorder(Color.BLUE));
/*     */     
/*  68 */     TableColumnModel columnModel = table.getColumnModel();
/*  69 */     columnModel.getColumn(column).setCellRenderer(this);
/*  70 */     columnModel.getColumn(column).setCellEditor(this);
/*  71 */     table.addMouseListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Border getFocusBorder()
/*     */   {
/*  82 */     return this.focusBorder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFocusBorder(Border focusBorder)
/*     */   {
/*  92 */     this.focusBorder = focusBorder;
/*  93 */     this.editButton.setBorder(focusBorder);
/*     */   }
/*     */   
/*     */   public int getMnemonic()
/*     */   {
/*  98 */     return this.mnemonic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMnemonic(int mnemonic)
/*     */   {
/* 108 */     this.mnemonic = mnemonic;
/* 109 */     this.renderButton.setMnemonic(mnemonic);
/* 110 */     this.editButton.setMnemonic(mnemonic);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
/*     */   {
/* 117 */     if (value == null)
/*     */     {
/* 119 */       this.editButton.setText("");
/* 120 */       this.editButton.setIcon(null);
/*     */     }
/* 122 */     else if ((value instanceof Icon))
/*     */     {
/* 124 */       this.editButton.setText("");
/* 125 */       this.editButton.setIcon((Icon)value);
/*     */     }
/*     */     else
/*     */     {
/* 129 */       this.editButton.setText(value.toString());
/* 130 */       this.editButton.setIcon(null);
/*     */     }
/*     */     
/* 133 */     this.editorValue = value;
/* 134 */     return this.editButton;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getCellEditorValue()
/*     */   {
/* 140 */     return this.editorValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*     */   {
/* 149 */     if (isSelected)
/*     */     {
/* 151 */       this.renderButton.setForeground(table.getSelectionForeground());
/* 152 */       this.renderButton.setBackground(table.getSelectionBackground());
/*     */     }
/*     */     else
/*     */     {
/* 156 */       this.renderButton.setForeground(table.getForeground());
/* 157 */       this.renderButton.setBackground(UIManager.getColor("Button.background"));
/*     */     }
/*     */     
/* 160 */     if (hasFocus)
/*     */     {
/* 162 */       this.renderButton.setBorder(this.focusBorder);
/*     */     }
/*     */     
/*     */ 
/* 166 */     if (value == null)
/*     */     {
/* 168 */       this.renderButton.setText("");
/* 169 */       this.renderButton.setIcon(null);
/*     */     }
/* 171 */     else if ((value instanceof Icon))
/*     */     {
/* 173 */       this.renderButton.setText("");
/* 174 */       this.renderButton.setIcon((Icon)value);
/*     */     }
/*     */     else
/*     */     {
/* 178 */       this.renderButton.setText(value.toString());
/* 179 */       this.renderButton.setIcon(null);
/*     */     }
/*     */     
/* 182 */     return this.renderButton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)

{

	int row = table.convertRowIndexToModel( table.getEditingRow() );

	fireEditingStopped();



	//  Invoke the Action



	ActionEvent event = new ActionEvent(

		table,

		ActionEvent.ACTION_PERFORMED,

		"" + row);

	action.actionPerformed(event);

}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 215 */     if ((this.table.isEditing()) && 
/* 216 */       (this.table.getCellEditor() == this)) {
/* 217 */       this.isButtonColumnEditor = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 222 */     if ((this.isButtonColumnEditor) && 
/* 223 */       (this.table.isEditing())) {
/* 224 */       this.table.getCellEditor().stopCellEditing();
/*     */     }
/* 226 */     this.isButtonColumnEditor = false;
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ButtonColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */