 package ntdw.common;
 
 import java.awt.Color;
 import java.awt.Cursor;
 import java.awt.Dimension;
 import java.awt.EventQueue;
 import java.awt.FlowLayout;
 import java.awt.Font;
 import java.awt.GraphicsEnvironment;
 import java.awt.GridBagConstraints;
 import java.awt.Insets;
 import java.awt.Rectangle;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import javax.swing.JButton;
 import javax.swing.JComboBox;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JTextField;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 import javax.swing.text.Document;
 import ntdw.model.AutoCompleteModel;
 import ntdw.model.CharacValue;
 import ntdw.views.AutoComplete;
 
 
 
 
 
 
 
 
 public class CharacValueRenderer
 {
   protected boolean refresh = true;
   
   public void rendererComponents(JPanel pnlParent, List<CharacValue> characValues) {
     pnlParent.removeAll();
     int i = 1;
     for (CharacValue characValue : characValues) {

       JPanel pnlName = new JPanel(new FlowLayout(0));
       JLabel jlabelName = new JLabel(characValue.getName().toString());
       Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
       jlabelName.setPreferredSize(new Dimension((int)(400.0D * (area.width / 1366.0D)), 10));
       jlabelName.setForeground(new Color(68, 84, 105));
       jlabelName.setFont(new Font("Calibri", 1, 12));
       pnlName.add(jlabelName);
       GridBagConstraints gbc_pnlCharacName = new GridBagConstraints();
       gbc_pnlCharacName.insets = new Insets(0, 60, 0, 0);
       gbc_pnlCharacName.fill = 1;
       gbc_pnlCharacName.gridx = 0;
       gbc_pnlCharacName.gridy = i;
       pnlParent.add(pnlName, gbc_pnlCharacName);
       i++;
       
       JPanel pnl = new JPanel(new FlowLayout(0));
       JLabel labelIcn = new JLabel();
       labelIcn.setOpaque(true);
       if (characValue.isCritical) {
         labelIcn.setBackground(Color.RED);
       } else {
         labelIcn.setBackground(Color.BLUE);
       }
       labelIcn.setText(String.format("%02d", new Object[] { characValue.getNbCharacteristic() }));
       pnl.add(labelIcn);
       switch (characValue.getType()) {
       case NU: 
         JLabel jlabelType = new JLabel(characValue.getType().toString());
         jlabelType.setForeground(Color.BLACK);
         pnl.add(jlabelType);
         final JTextField textFieldNU = new JTextField();
         final JTextField comp = new JTextField();
         
         if (characValue.getName().startsWith("Plage")) {
           final JTextField valG = new JTextField();
           final JTextField valD = new JTextField();
           DocumentListener subDoc_NU = new DocumentListener()
           {
             public void changedUpdate(DocumentEvent e) {
               updateLabel(e);
             }
             
             public void insertUpdate(DocumentEvent e)
             {
               updateLabel(e);
             }
             
             public void removeUpdate(DocumentEvent e)
             {
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
           try {
             valG.setText(characValue.oldVal.split("&&")[0]);
             valD.setText(characValue.oldVal.split("&&")[1]);
           } catch (Exception e) {
             valG.setText("");
             valD.setText("");
           }
           valG.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
           valD.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
           textFieldNU.setVisible(false);
           pnl.add(valG);
           pnl.add(valD);
           
           final JButton UKN = new JButton("?");
           UKN.setFocusable(false);
           UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
           UKN.setForeground(Color.decode("#445469"));
           UKN.setOpaque(true);
           UKN.setBackground(Color.decode("#F29B26"));
           UKN.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
               valG.setText("---");
               valD.setText("---");
               comp.setText("---");
             }
           });
           UKN.addMouseListener(new MouseAdapter()
           {
             public void mouseEntered(MouseEvent e) {
               UKN.setCursor(Cursor.getPredefinedCursor(12));
               UKN.setToolTipText("Set to Unknown");
             }
           });
           pnl.add(UKN);
         }
         else
         {
           textFieldNU.setPreferredSize(new Dimension((int)(250.0D * (area.width / 1366.0D)), 20));
           if (characValue.oldVal != null) {
             textFieldNU.setText(characValue.oldVal);
           }
           pnl.add(textFieldNU);
           final JButton UKN = new JButton("?");
           UKN.setFocusable(false);
           UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
           UKN.setForeground(Color.decode("#445469"));
           UKN.setOpaque(true);
           UKN.setBackground(Color.decode("#F29B26"));
           UKN.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
               textFieldNU.setText("---");
               comp.setText("---");
             }
           });
           UKN.addMouseListener(new MouseAdapter()
           {
             public void mouseEntered(MouseEvent e) {
               UKN.setCursor(Cursor.getPredefinedCursor(12));
               UKN.setToolTipText("Set to Unknown");
             }
           });
           pnl.add(UKN);
         }
         
         characValue.text = textFieldNU;
         characValue.box = null;
         
 
 
         String[] options2 = new String[characValue.getListValues().size()];
         options2 = (String[])characValue.getListValues().toArray(options2);
         
         if (characValue.oldComp != null) {
           comp.setText(characValue.oldComp);
         }
         comp.setPreferredSize(new Dimension((int)(150.0D * (area.width / 1366.0D)), 20));
         pnl.add(comp);
         characValue.comp = comp;
         
         JLabel uoms = new JLabel("(" + String.join(" or ", options2) + ")");
         uoms.setForeground(Color.BLACK);
         uoms.setPreferredSize(new Dimension((int)(40.0D * (area.width / 1366.0D)), 20));
         pnl.add(uoms);
         
 
 
 
 
         break;
       case FT: 
         JLabel jlabelFT = new JLabel(characValue.getType().toString() + " ");
         jlabelFT.setForeground(Color.BLACK);
         pnl.add(jlabelFT);
         
 
 
 
         final JTextField textFieldTypeFT = new JTextField();
         AutoCompleteModel modelG = new AutoCompleteModel();
         AutoCompleteModel modelD = new AutoCompleteModel();
         
         final HashMap<String, String> dicoD = new HashMap();
         final HashMap<String, String> dicoG = new HashMap();
         List<String> listG = new ArrayList();
         List<String> listD = new ArrayList();
         characValue.hist.remove("Inconnu&&Unknown");
         for (String val : characValue.hist) {
           try {
             String valG = val.split("&&")[0];
             listG.add(valG);
             String valD = val.split("&&")[1];
             listD.add(valD);
             
             dicoG.put(valG, valD);
             dicoD.put(valD, valG);
           }
           catch (Exception localException1) {}
         }
         
 
 
 
         modelG.addAll(new ArrayList(listG));
         modelD.addAll(new ArrayList(listD));
         
         final AutoComplete textG = new AutoComplete(modelG);
         final AutoComplete textD = new AutoComplete(modelD);
         textG.couple = textD;
         textD.couple = textG;
         
         DocumentListener subDocG = new DocumentListener()
         {
           public void changedUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void insertUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void removeUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           private void updateLabel(DocumentEvent e)
           {
             textFieldTypeFT.setText(textG.zoneTexte.getText() + "&&" + textD.zoneTexte.getText());
             if (CharacValueRenderer.this.refresh) {
               if (dicoG.containsKey(textG.zoneTexte.getText())) {
                 CharacValueRenderer.this.refresh = false;
                 textD.zoneTexte.setText((String)dicoG.get(textG.zoneTexte.getText()));
                 CharacValueRenderer.this.refresh = true;
               }
               else if (!textD.new_val) {
                 CharacValueRenderer.this.refresh = false;
                 textD.zoneTexte.setText("");
                 CharacValueRenderer.this.refresh = true;
                 textG.new_val = true;
               }
               
             }
             
           }
           
         };
         DocumentListener subDocD = new DocumentListener()
         {
           public void changedUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void insertUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           public void removeUpdate(DocumentEvent e)
           {
             updateLabel(e);
           }
           
           private void updateLabel(DocumentEvent e)
           {
             textFieldTypeFT.setText(textG.zoneTexte.getText() + "&&" + textD.zoneTexte.getText());
             if (CharacValueRenderer.this.refresh) {
               if (dicoD.containsKey(textD.zoneTexte.getText())) {
                 CharacValueRenderer.this.refresh = false;
                 textG.zoneTexte.setText((String)dicoD.get(textD.zoneTexte.getText()));
                 CharacValueRenderer.this.refresh = true;
               }
               else if (!textG.new_val) {
                 CharacValueRenderer.this.refresh = false;
                 textG.zoneTexte.setText("");
                 CharacValueRenderer.this.refresh = true;
                 textD.new_val = true;
 
               }
               
             }
             
           }
           
 
         };
         textG.zoneTexte.getDocument().addDocumentListener(subDocG);
         textD.zoneTexte.getDocument().addDocumentListener(subDocD);
         
         if ((characValue.oldVal != null) && (characValue.oldVal.contains("&&"))) {
           try
           {
             textG.zoneTexte.setText(characValue.oldVal.split("&&")[0]);
             textD.zoneTexte.setText(characValue.oldVal.split("&&")[1]);
           } catch (Exception e) {
             textG.zoneTexte.setText("");
             textD.zoneTexte.setText("");
           }
         }
         
         characValue.text = textFieldTypeFT;
         characValue.box = null;
         
         textFieldTypeFT.setVisible(false);
         
         textG.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
         textD.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
         
         final JButton UKN = new JButton("?");
         UKN.setFocusable(false);
         UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
         UKN.setForeground(Color.decode("#445469"));
         UKN.setOpaque(true);
         UKN.setBackground(Color.decode("#F29B26"));
         UKN.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             textG.setText("Inconnu");
             textD.setText("Unknown");
           }
         });
         UKN.addMouseListener(new MouseAdapter()
         {
           public void mouseEntered(MouseEvent e) {
             UKN.setCursor(Cursor.getPredefinedCursor(12));
             UKN.setToolTipText("Set to Unknown");
           }
           
         });
         pnl.add(textG);
         pnl.add(textD);
         pnl.add(UKN);
         
 
         break;
       case VL: 
         JLabel jlabelVL = new JLabel(characValue.getType().toString() + " ");
         jlabelVL.setForeground(Color.BLACK);
         pnl.add(jlabelVL);
         characValue.getListValues().add("Autre / Other");
         String[] options = new String[characValue.getListValues().size()];
         options = (String[])characValue.getListValues().toArray(options);
         final JComboBox<String> patternList = new JComboBox(options);
         if (characValue.oldVal != null) {
           if (characValue.oldVal.equals("Autre / Other")) {
             patternList.addItem("Autre / Other: " + characValue.oldComp);
             patternList.setSelectedItem("Autre / Other: " + characValue.oldComp);
           } else {
             patternList.addItem(characValue.oldVal);
             patternList.setSelectedItem(characValue.oldVal);
           }
         }
         
         characValue.box = patternList;
         characValue.text = null;
         patternList.setPreferredSize(new Dimension((int)(250.0D * (area.width / 1366.0D)), 20));
         pnl.add(patternList);
         ComboBoxActionListner lst = new ComboBoxActionListner(pnl, characValue.hist, area);
         patternList.addItemListener(lst);
         characValue.comp = lst.textField;
         
         final JButton UKN1 = new JButton("?");
         UKN1.setFocusable(false);
         UKN1.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
         UKN1.setForeground(Color.decode("#445469"));
         UKN1.setOpaque(true);
         UKN1.setBackground(Color.decode("#F29B26"));
         UKN1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             patternList.setSelectedItem("Inconnu/Unknown");
           }
           
         });
         UKN1.addMouseListener(new MouseAdapter()
         {
           public void mouseEntered(MouseEvent e) {
             UKN1.setCursor(Cursor.getPredefinedCursor(12));
             UKN1.setToolTipText("Set to Unknown");
           }
         });
         pnl.add(UKN1);
       }
       
       GridBagConstraints gbc_pnlCharac = new GridBagConstraints();
       gbc_pnlCharac.insets = new Insets(0, 0, 0, 5);
       gbc_pnlCharac.fill = 1;
       gbc_pnlCharac.gridx = 0;
       gbc_pnlCharac.gridy = i;
       pnlParent.add(pnl, gbc_pnlCharac);
       i++;
     }
     pnlParent.repaint();
   }
 }

