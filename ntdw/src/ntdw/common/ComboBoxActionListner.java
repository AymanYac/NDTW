package ntdw.common;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboBoxActionListner implements ItemListener{
	
	private JPanel panel;
	public JTextField textField = new JTextField();

	public ComboBoxActionListner(JPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String item = (String) event.getItem();
			if(item.equals("Autre / Other")){
				textField.setPreferredSize(new Dimension(75, 20));
				panel.add(textField);
				panel.validate();
				panel.repaint();
			}else{
				if(textField != null){
					panel.remove(textField);
					panel.validate();
					panel.repaint();
				}
			}
		}
	}
	
	

}
