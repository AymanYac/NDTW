 package ntdw.common;
 
 import java.awt.Color;
 import java.awt.Component;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import javax.swing.AbstractCellEditor;
 import javax.swing.Action;
 import javax.swing.Icon;
 import javax.swing.JButton;
 import javax.swing.JTable;
 import javax.swing.UIManager;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import javax.swing.table.TableCellEditor;
 import javax.swing.table.TableCellRenderer;
 import javax.swing.table.TableColumn;
 import javax.swing.table.TableColumnModel;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ButtonColumn
   extends AbstractCellEditor
   implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
 {
   private JTable table;
   private Action action;
   private int mnemonic;
   private Border originalBorder;
   private Border focusBorder;
   private JButton renderButton;
   private JButton editButton;
   private Object editorValue;
   private boolean isButtonColumnEditor;
   
   public ButtonColumn(JTable table, Action action, int column)
   {
     this.table = table;
     this.action = action;
     
     this.renderButton = new JLinkButton();
     this.editButton = new JLinkButton();
     this.editButton.addActionListener(this);
     setFocusBorder(new LineBorder(Color.BLUE));
     
     TableColumnModel columnModel = table.getColumnModel();
     columnModel.getColumn(column).setCellRenderer(this);
     columnModel.getColumn(column).setCellEditor(this);
     table.addMouseListener(this);
   }
   
 
 
 
 
 
 
   public Border getFocusBorder()
   {
     return this.focusBorder;
   }
   
 
 
 
 
 
   public void setFocusBorder(Border focusBorder)
   {
     this.focusBorder = focusBorder;
     this.editButton.setBorder(focusBorder);
   }
   
   public int getMnemonic()
   {
     return this.mnemonic;
   }
   
 
 
 
 
 
   public void setMnemonic(int mnemonic)
   {
     this.mnemonic = mnemonic;
     this.renderButton.setMnemonic(mnemonic);
     this.editButton.setMnemonic(mnemonic);
   }
   
 
 
   public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
   {
     if (value == null)
     {
       this.editButton.setText("");
       this.editButton.setIcon(null);
     }
     else if ((value instanceof Icon))
     {
       this.editButton.setText("");
       this.editButton.setIcon((Icon)value);
     }
     else
     {
       this.editButton.setText(value.toString());
       this.editButton.setIcon(null);
     }
     
     this.editorValue = value;
     return this.editButton;
   }
   
 
   public Object getCellEditorValue()
   {
     return this.editorValue;
   }
   
 
 
 
 
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
     if (isSelected)
     {
       this.renderButton.setForeground(table.getSelectionForeground());
       this.renderButton.setBackground(table.getSelectionBackground());
     }
     else
     {
       this.renderButton.setForeground(table.getForeground());
       this.renderButton.setBackground(UIManager.getColor("Button.background"));
     }
     
     if (hasFocus)
     {
       this.renderButton.setBorder(this.focusBorder);
     }
     
 
     if (value == null)
     {
       this.renderButton.setText("");
       this.renderButton.setIcon(null);
     }
     else if ((value instanceof Icon))
     {
       this.renderButton.setText("");
       this.renderButton.setIcon((Icon)value);
     }
     else
     {
       this.renderButton.setText(value.toString());
       this.renderButton.setIcon(null);
     }
     
     return this.renderButton;
   }
   
 
 
 
 
 
 
   public void actionPerformed(ActionEvent e)

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
   
 
 
 
 
 
 
 
 
   public void mousePressed(MouseEvent e)
   {
     if ((this.table.isEditing()) && 
       (this.table.getCellEditor() == this)) {
       this.isButtonColumnEditor = true;
     }
   }
   
   public void mouseReleased(MouseEvent e) {
     if ((this.isButtonColumnEditor) && 
       (this.table.isEditing())) {
       this.table.getCellEditor().stopCellEditing();
     }
     this.isButtonColumnEditor = false;
   }
   
   public void mouseClicked(MouseEvent e) {}
   
   public void mouseEntered(MouseEvent e) {}
   
   public void mouseExited(MouseEvent e) {}
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\ButtonColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */