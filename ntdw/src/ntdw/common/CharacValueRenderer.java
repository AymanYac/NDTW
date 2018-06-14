package ntdw.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ntdw.model.CharacValue;

public class CharacValueRenderer {

	public void rendererComponents(JPanel pnlParent, List<CharacValue> characValues) {
		pnlParent.removeAll();
		int i = 1;
		for (CharacValue characValue : characValues) {
			JPanel pnlName = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
			JLabel jlabelName = new JLabel(characValue.getName().toString());
			Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			jlabelName.setPreferredSize(new Dimension((int) (200 * (area.width/1366.0)), 10));
			jlabelName.setForeground(new Color(68, 84, 105));
			jlabelName.setFont(new Font("Calibri", Font.BOLD, 12));
			pnlName.add(jlabelName);
			GridBagConstraints gbc_pnlCharacName = new GridBagConstraints();
			gbc_pnlCharacName.insets = new Insets(0, 60, 0, 0);
			gbc_pnlCharacName.fill = GridBagConstraints.BOTH; 
			gbc_pnlCharacName.gridx = 0;
			gbc_pnlCharacName.gridy = i;
			pnlParent.add(pnlName, gbc_pnlCharacName);
			i++;

			JPanel pnl = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
			JLabel labelIcn = new JLabel() ;
			labelIcn.setOpaque(true);
			if (characValue.isCritical){
				labelIcn.setBackground(Color.RED);
			}else {
				labelIcn.setBackground(Color.BLUE);
			}
			labelIcn.setText(String.format("%02d", characValue.getNbCharacteristic()));
			pnl.add(labelIcn);
			switch (characValue.getType()) {
			case NU:
				JLabel jlabelType = new JLabel(characValue.getType().toString());
				jlabelType.setForeground(Color.BLACK);
				pnl.add(jlabelType);
				JTextField textFieldNU = new JTextField();
				textFieldNU.setPreferredSize(new Dimension((int) (250 * (area.width/1366.0)), 20));
				if(characValue.oldVal!=null) {
					textFieldNU.setText(characValue.oldVal);
				}
				characValue.text=textFieldNU;
				characValue.box=null;
				pnl.add(textFieldNU);
				

				String[] options2 = new String[characValue.getListValues().size()];
				options2 = characValue.getListValues().toArray(options2);

				JTextField comp = new JTextField();
				if(characValue.oldComp!=null) {
					comp.setText(characValue.oldComp);
				}
				comp.setPreferredSize(new Dimension((int) (150 * (area.width/1366.0)), 20));
				pnl.add(comp);
				characValue.comp=comp;
				
				JLabel uoms = new JLabel("("+String.join(" or ", options2)+")");
				uoms.setForeground(Color.BLACK);
				uoms.setPreferredSize(new Dimension((int) (40 * (area.width/1366.0)), 20));
				pnl.add(uoms);
				
				//options2 = characValue.getListValues().toArray(options2);
				//JComboBox<String> textFieldUnit = new JComboBox<>(options2);
				//textFieldUnit.setPreferredSize(new Dimension(250, 20));
				//pnl.add(textFieldUnit);
				break;
			case FT:
				JLabel jlabelFT = new JLabel(characValue.getType().toString() + " ");
				jlabelFT.setForeground(Color.BLACK);
				pnl.add(jlabelFT);
				JTextField textFieldTypeFT = new JTextField();
				if(characValue.oldVal!=null) {
					textFieldTypeFT.setText(characValue.oldVal);
				}
				characValue.text=textFieldTypeFT;
				characValue.box=null;
				textFieldTypeFT.setPreferredSize(new Dimension((int) (250 * (area.width/1366.0)), 20));
				pnl.add(textFieldTypeFT);
				break;
			case VL:
				JLabel jlabelVL = new JLabel(characValue.getType().toString() + " ");
				jlabelVL.setForeground(Color.BLACK);
				pnl.add(jlabelVL);
				characValue.getListValues().add("Autre / Other");
				String[] options = new String[characValue.getListValues().size()];
				options = characValue.getListValues().toArray(options);
				JComboBox<String> patternList = new JComboBox<>(options);
				if(characValue.oldVal!=null) {
					if(characValue.oldVal.equals("Autre / Other")) {
						patternList.addItem("Autre / Other: "+characValue.oldComp);
						patternList.setSelectedItem("Autre / Other: "+characValue.oldComp);
					}else {
						patternList.addItem(characValue.oldVal);
						patternList.setSelectedItem(characValue.oldVal);
					}
					
				}
				characValue.box=patternList;
				characValue.text=null;
				patternList.setPreferredSize(new Dimension((int) (250 * (area.width/1366.0)), 20));
				pnl.add(patternList);
				ComboBoxActionListner lst = new ComboBoxActionListner(pnl);
				patternList.addItemListener(lst);
				characValue.comp=lst.textField;
				break;
			}
			GridBagConstraints gbc_pnlCharac = new GridBagConstraints();
			gbc_pnlCharac.insets = new Insets(0, 0, 0, 5);
			gbc_pnlCharac.fill = GridBagConstraints.BOTH;
			gbc_pnlCharac.gridx = 0;
			gbc_pnlCharac.gridy = i;
			pnlParent.add(pnl, gbc_pnlCharac);
			i++;
		}
		pnlParent.repaint();
	}
}
