package ntdw.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ntdw.model.AutoCompleteModel;
import ntdw.model.CharacValue;
import ntdw.model.Java2sAutoTextField;
import ntdw.views.AutoComplete;

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
				JTextField comp = new JTextField();
				
				if(characValue.getName().startsWith("Plage")) {
					JTextField valG = new JTextField();
					JTextField valD = new JTextField();
					DocumentListener subDoc_NU = new DocumentListener() {
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
			                java.awt.EventQueue.invokeLater(new Runnable() {

			                    @Override
			                    public void run() {
			                    	textFieldNU.setText(valG.getText()+"&&"+valD.getText());
			                    }
			                });
			            }
			        };
			        valG.getDocument().addDocumentListener(subDoc_NU);
			        valD.getDocument().addDocumentListener(subDoc_NU);
			        if(characValue.oldVal!=null && characValue.oldVal.contains("&&")) {
			        	valG.setText(characValue.oldVal.split("&&")[0]);
						valD.setText(characValue.oldVal.split("&&")[1]);
					}
			        valG.setPreferredSize(new Dimension((int) (124 * (area.width/1366.0)), 20));
			        valD.setPreferredSize(new Dimension((int) (124 * (area.width/1366.0)), 20));
			        textFieldNU.setVisible(false);
			        pnl.add(valG);
			        pnl.add(valD);
			        
			        JButton UKN = new JButton("?");
					UKN.setPreferredSize(new Dimension((int) (8 * (area.width/1366.0)), 20));
					UKN.setForeground(Color.decode("#445469"));
					UKN.setOpaque(true);
					UKN.setBackground(Color.decode("#F29B26"));
					UKN.addActionListener(new ActionListener() { 
						  public void actionPerformed(ActionEvent e) { 
						    valG.setText("---");
						    valD.setText("---");
						    comp.setText("---");
						  } 
						} );
					UKN.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							UKN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							UKN.setToolTipText("Set to Unknown");
						}
					});
					pnl.add(UKN);

			        
				}else {	
					textFieldNU.setPreferredSize(new Dimension((int) (250 * (area.width/1366.0)), 20));
					if(characValue.oldVal!=null) {
						textFieldNU.setText(characValue.oldVal);
					}
					pnl.add(textFieldNU);
					JButton UKN = new JButton("?");
					UKN.setPreferredSize(new Dimension((int) (8 * (area.width/1366.0)), 20));
					UKN.setForeground(Color.decode("#445469"));
					UKN.setOpaque(true);
					UKN.setBackground(Color.decode("#F29B26"));
					UKN.addActionListener(new ActionListener() { 
						  public void actionPerformed(ActionEvent e) { 
							textFieldNU.setText("---");
							comp.setText("---");
						  } 
						} );
					UKN.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							UKN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							UKN.setToolTipText("Set to Unknown");
						}
					});
					pnl.add(UKN);
					
				}
				characValue.text=textFieldNU;
				characValue.box=null;
				
				

				String[] options2 = new String[characValue.getListValues().size()];
				options2 = characValue.getListValues().toArray(options2);

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
				
				
				
				//Java2sAutoTextField textFieldTypeFT = new Java2sAutoTextField();
				JTextField textFieldTypeFT = new JTextField();
				AutoCompleteModel modelG = new AutoCompleteModel();
				AutoCompleteModel modelD = new AutoCompleteModel();
				//modelG.addAll(new ArrayList<String>(characValue.hist));
				HashSet<String> listG = new HashSet<String>();
				HashSet<String> listD = new HashSet<String>();
				for(String val:characValue.hist) {
					if(val!=null && val.contains("&&")){
					String valG=val.split("&&")[0];
					listG.add(valG);
					String valD=val.split("&&")[1];
					listD.add(valD);	
					}
				}
				listG.remove("Inconnu");
				listD.remove("Unknown");
				modelG.addAll(new ArrayList<String>(listG));
				modelD.addAll(new ArrayList<String>(listD));
				
				AutoComplete textG = new AutoComplete(modelG);
				AutoComplete textD = new AutoComplete(modelD);
				
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
		                java.awt.EventQueue.invokeLater(new Runnable() {

		                    @Override
		                    public void run() {
		                    	textFieldTypeFT.setText(textG.zoneTexte.getText()+"&&"+textD.zoneTexte.getText());
		                    }
		                });
		            }
		        };
				textG.zoneTexte.getDocument().addDocumentListener(subDoc);
				textD.zoneTexte.getDocument().addDocumentListener(subDoc);
				
				if(characValue.oldVal!=null && characValue.oldVal.contains("&&")) {
					//textFieldTypeFT.setText(characValue.oldVal);
					textG.zoneTexte.setText(characValue.oldVal.split("&&")[0]);
					textD.zoneTexte.setText(characValue.oldVal.split("&&")[1]);
				}
				characValue.text=textFieldTypeFT;
				characValue.box=null;
				//textFieldTypeFT.setPreferredSize(new Dimension((int) (250 * (area.width/1366.0)), 20));
				textFieldTypeFT.setVisible(false);
				//pnl.add(textFieldTypeFT);
				textG.setPreferredSize(new Dimension((int) (124 * (area.width/1366.0)), 20));
				textD.setPreferredSize(new Dimension((int) (124 * (area.width/1366.0)), 20));
				if(true) {
					JButton UKN = new JButton("?");
					UKN.setPreferredSize(new Dimension((int) (8 * (area.width/1366.0)), 20));
					UKN.setForeground(Color.decode("#445469"));
					UKN.setOpaque(true);
					UKN.setBackground(Color.decode("#F29B26"));
					UKN.addActionListener(new ActionListener() { 
						  public void actionPerformed(ActionEvent e) { 
						    textG.setText("Inconnu");
						    textD.setText("Unknown");
						  } 
						} );
					UKN.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							UKN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							UKN.setToolTipText("Set to Unknown");
						}
					});
					
					pnl.add(textG);
					pnl.add(textD);
					pnl.add(UKN);
				}
				
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

				JButton UKN = new JButton("?");
				UKN.setPreferredSize(new Dimension((int) (8 * (area.width/1366.0)), 20));
				UKN.setForeground(Color.decode("#445469"));
				UKN.setOpaque(true);
				UKN.setBackground(Color.decode("#F29B26"));
				UKN.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
					   patternList.setSelectedItem("Inconnu/Unknown");
					  } 
					} );
				
				UKN.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						UKN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						UKN.setToolTipText("Set to Unknown");
					}
				});
				pnl.add(UKN);
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
