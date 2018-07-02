package ntdw.common;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ntdw.model.AutoCompleteModel;
import ntdw.views.AutoComplete;

public class ComboBoxActionListner implements ItemListener{
	
	private JPanel panel;
	public JTextField textField = new JTextField();
	AutoComplete text = new AutoComplete(null);
	private Rectangle area;
	private HashSet<String> hist;

	public ComboBoxActionListner(JPanel panel, HashSet<String> hist, Rectangle area) {
		super();
		this.panel = panel;
		this.area= area;
		this.hist=hist;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String item = (String) event.getItem();
			if(item.equals("Autre / Other")){
				AutoCompleteModel model = new AutoCompleteModel();
				model.addAll(new ArrayList<String>(hist));
				text = new AutoComplete(model);
				
				DocumentListener subDoc = new DocumentListener() {

		            @Override
		            public void changedUpdate(DocumentEvent e) {
		                updateLabel(e);
		            }

		            @Override
		            public void insertUpdate(DocumentEvent e) {
		                updateLabel(e);
		            }

		            @Override
		            public void removeUpdate(DocumentEvent e) {
		                updateLabel(e);
		            }

		            private void updateLabel(DocumentEvent e) {
		            	
		            	textField.setText(text.zoneTexte.getText());
		            }
		        };
				
		        text.zoneTexte.getDocument().addDocumentListener(subDoc);
		        text.setPreferredSize(new Dimension((int) (150 * (area.width/1366.0)), 20));
				//textField.setPreferredSize(new Dimension((int) (150 * (area.width/1366.0)), 20));
				textField.setVisible(false);
				panel.add(text);
				//panel.add(textField);
				panel.validate();
				panel.repaint();
			}else{
				if(textField != null){
					panel.remove(textField);
					panel.remove(text);
					panel.validate();
					panel.repaint();
				}
			}
		}
	}
	
	

}
