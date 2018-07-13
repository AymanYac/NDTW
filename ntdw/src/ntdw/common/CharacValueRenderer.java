/*     */ package ntdw.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.text.Document;
/*     */ import ntdw.model.AutoCompleteModel;
/*     */ import ntdw.model.CharacValue;
/*     */ import ntdw.views.AutoComplete;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharacValueRenderer
/*     */ {
/*  42 */   protected boolean refresh = true;
/*     */   
/*     */   public void rendererComponents(JPanel pnlParent, List<CharacValue> characValues) {
/*  45 */     pnlParent.removeAll();
/*  46 */     int i = 1;
/*  47 */     for (CharacValue characValue : characValues) {

/*  48 */       JPanel pnlName = new JPanel(new FlowLayout(0));
/*  49 */       JLabel jlabelName = new JLabel(characValue.getName().toString());
/*  50 */       Rectangle area = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
/*  51 */       jlabelName.setPreferredSize(new Dimension((int)(400.0D * (area.width / 1366.0D)), 10));
/*  52 */       jlabelName.setForeground(new Color(68, 84, 105));
/*  53 */       jlabelName.setFont(new Font("Calibri", 1, 12));
/*  54 */       pnlName.add(jlabelName);
/*  55 */       GridBagConstraints gbc_pnlCharacName = new GridBagConstraints();
/*  56 */       gbc_pnlCharacName.insets = new Insets(0, 60, 0, 0);
/*  57 */       gbc_pnlCharacName.fill = 1;
/*  58 */       gbc_pnlCharacName.gridx = 0;
/*  59 */       gbc_pnlCharacName.gridy = i;
/*  60 */       pnlParent.add(pnlName, gbc_pnlCharacName);
/*  61 */       i++;
/*     */       
/*  63 */       JPanel pnl = new JPanel(new FlowLayout(0));
/*  64 */       JLabel labelIcn = new JLabel();
/*  65 */       labelIcn.setOpaque(true);
/*  66 */       if (characValue.isCritical) {
/*  67 */         labelIcn.setBackground(Color.RED);
/*     */       } else {
/*  69 */         labelIcn.setBackground(Color.BLUE);
/*     */       }
/*  71 */       labelIcn.setText(String.format("%02d", new Object[] { characValue.getNbCharacteristic() }));
/*  72 */       pnl.add(labelIcn);
/*  73 */       switch (characValue.getType()) {
/*     */       case NU: 
/*  75 */         JLabel jlabelType = new JLabel(characValue.getType().toString());
/*  76 */         jlabelType.setForeground(Color.BLACK);
/*  77 */         pnl.add(jlabelType);
/*  78 */         final JTextField textFieldNU = new JTextField();
/*  79 */         final JTextField comp = new JTextField();
/*     */         
/*  81 */         if (characValue.getName().startsWith("Plage")) {
/*  82 */           final JTextField valG = new JTextField();
/*  83 */           final JTextField valD = new JTextField();
/*  84 */           DocumentListener subDoc_NU = new DocumentListener()
/*     */           {
/*     */             public void changedUpdate(DocumentEvent e) {
/*  87 */               updateLabel(e);
/*     */             }
/*     */             
/*     */             public void insertUpdate(DocumentEvent e)
/*     */             {
/*  92 */               updateLabel(e);
/*     */             }
/*     */             
/*     */             public void removeUpdate(DocumentEvent e)
/*     */             {
/*  97 */               updateLabel(e);
/*     */             }
/*     */             
private void updateLabel(DocumentEvent e) {

    java.awt.EventQueue.invokeLater(new Runnable() {



        @Override

        public void run() {

        	textFieldNU.setText(valG.getText()+"&&"+valD.getText());

        }

    });

}

};
/* 110 */           valG.getDocument().addDocumentListener(subDoc_NU);
/* 111 */           valD.getDocument().addDocumentListener(subDoc_NU);
/*     */           try {
/* 113 */             valG.setText(characValue.oldVal.split("&&")[0]);
/* 114 */             valD.setText(characValue.oldVal.split("&&")[1]);
/*     */           } catch (Exception e) {
/* 116 */             valG.setText("");
/* 117 */             valD.setText("");
/*     */           }
/* 119 */           valG.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
/* 120 */           valD.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
/* 121 */           textFieldNU.setVisible(false);
/* 122 */           pnl.add(valG);
/* 123 */           pnl.add(valD);
/*     */           
/* 125 */           final JButton UKN = new JButton("?");
/* 126 */           UKN.setFocusable(false);
/* 127 */           UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
/* 128 */           UKN.setForeground(Color.decode("#445469"));
/* 129 */           UKN.setOpaque(true);
/* 130 */           UKN.setBackground(Color.decode("#F29B26"));
/* 131 */           UKN.addActionListener(new ActionListener() {
/*     */             public void actionPerformed(ActionEvent e) {
/* 133 */               valG.setText("---");
/* 134 */               valD.setText("---");
/* 135 */               comp.setText("---");
/*     */             }
/* 137 */           });
/* 138 */           UKN.addMouseListener(new MouseAdapter()
/*     */           {
/*     */             public void mouseEntered(MouseEvent e) {
/* 141 */               UKN.setCursor(Cursor.getPredefinedCursor(12));
/* 142 */               UKN.setToolTipText("Set to Unknown");
/*     */             }
/* 144 */           });
/* 145 */           pnl.add(UKN);
/*     */         }
/*     */         else
/*     */         {
/* 149 */           textFieldNU.setPreferredSize(new Dimension((int)(250.0D * (area.width / 1366.0D)), 20));
/* 150 */           if (characValue.oldVal != null) {
/* 151 */             textFieldNU.setText(characValue.oldVal);
/*     */           }
/* 153 */           pnl.add(textFieldNU);
/* 154 */           final JButton UKN = new JButton("?");
/* 155 */           UKN.setFocusable(false);
/* 156 */           UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
/* 157 */           UKN.setForeground(Color.decode("#445469"));
/* 158 */           UKN.setOpaque(true);
/* 159 */           UKN.setBackground(Color.decode("#F29B26"));
/* 160 */           UKN.addActionListener(new ActionListener() {
/*     */             public void actionPerformed(ActionEvent e) {
/* 162 */               textFieldNU.setText("---");
/* 163 */               comp.setText("---");
/*     */             }
/* 165 */           });
/* 166 */           UKN.addMouseListener(new MouseAdapter()
/*     */           {
/*     */             public void mouseEntered(MouseEvent e) {
/* 169 */               UKN.setCursor(Cursor.getPredefinedCursor(12));
/* 170 */               UKN.setToolTipText("Set to Unknown");
/*     */             }
/* 172 */           });
/* 173 */           pnl.add(UKN);
/*     */         }
/*     */         
/* 176 */         characValue.text = textFieldNU;
/* 177 */         characValue.box = null;
/*     */         
/*     */ 
/*     */ 
/* 181 */         String[] options2 = new String[characValue.getListValues().size()];
/* 182 */         options2 = (String[])characValue.getListValues().toArray(options2);
/*     */         
/* 184 */         if (characValue.oldComp != null) {
/* 185 */           comp.setText(characValue.oldComp);
/*     */         }
/* 187 */         comp.setPreferredSize(new Dimension((int)(150.0D * (area.width / 1366.0D)), 20));
/* 188 */         pnl.add(comp);
/* 189 */         characValue.comp = comp;
/*     */         
/* 191 */         JLabel uoms = new JLabel("(" + String.join(" or ", options2) + ")");
/* 192 */         uoms.setForeground(Color.BLACK);
/* 193 */         uoms.setPreferredSize(new Dimension((int)(40.0D * (area.width / 1366.0D)), 20));
/* 194 */         pnl.add(uoms);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 200 */         break;
/*     */       case FT: 
/* 202 */         JLabel jlabelFT = new JLabel(characValue.getType().toString() + " ");
/* 203 */         jlabelFT.setForeground(Color.BLACK);
/* 204 */         pnl.add(jlabelFT);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 209 */         final JTextField textFieldTypeFT = new JTextField();
/* 210 */         AutoCompleteModel modelG = new AutoCompleteModel();
/* 211 */         AutoCompleteModel modelD = new AutoCompleteModel();
/*     */         
/* 213 */         final HashMap<String, String> dicoD = new HashMap();
/* 214 */         final HashMap<String, String> dicoG = new HashMap();
/* 215 */         List<String> listG = new ArrayList();
/* 216 */         List<String> listD = new ArrayList();
/* 217 */         characValue.hist.remove("Inconnu&&Unknown");
/* 218 */         for (String val : characValue.hist) {
/*     */           try {
/* 220 */             String valG = val.split("&&")[0];
/* 221 */             listG.add(valG);
/* 222 */             String valD = val.split("&&")[1];
/* 223 */             listD.add(valD);
/*     */             
/* 225 */             dicoG.put(valG, valD);
/* 226 */             dicoD.put(valD, valG);
/*     */           }
/*     */           catch (Exception localException1) {}
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 234 */         modelG.addAll(new ArrayList(listG));
/* 235 */         modelD.addAll(new ArrayList(listD));
/*     */         
/* 237 */         final AutoComplete textG = new AutoComplete(modelG);
/* 238 */         final AutoComplete textD = new AutoComplete(modelD);
/* 239 */         textG.couple = textD;
/* 240 */         textD.couple = textG;
/*     */         
/* 242 */         DocumentListener subDocG = new DocumentListener()
/*     */         {
/*     */           public void changedUpdate(DocumentEvent e)
/*     */           {
/* 246 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           public void insertUpdate(DocumentEvent e)
/*     */           {
/* 251 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           public void removeUpdate(DocumentEvent e)
/*     */           {
/* 256 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           private void updateLabel(DocumentEvent e)
/*     */           {
/* 261 */             textFieldTypeFT.setText(textG.zoneTexte.getText() + "&&" + textD.zoneTexte.getText());
/* 262 */             if (CharacValueRenderer.this.refresh) {
/* 263 */               if (dicoG.containsKey(textG.zoneTexte.getText())) {
/* 264 */                 CharacValueRenderer.this.refresh = false;
/* 265 */                 textD.zoneTexte.setText((String)dicoG.get(textG.zoneTexte.getText()));
/* 266 */                 CharacValueRenderer.this.refresh = true;
/*     */               }
/* 268 */               else if (!textD.new_val) {
/* 269 */                 CharacValueRenderer.this.refresh = false;
/* 270 */                 textD.zoneTexte.setText("");
/* 271 */                 CharacValueRenderer.this.refresh = true;
/* 272 */                 textG.new_val = true;
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */           
/* 279 */         };
/* 280 */         DocumentListener subDocD = new DocumentListener()
/*     */         {
/*     */           public void changedUpdate(DocumentEvent e)
/*     */           {
/* 284 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           public void insertUpdate(DocumentEvent e)
/*     */           {
/* 289 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           public void removeUpdate(DocumentEvent e)
/*     */           {
/* 294 */             updateLabel(e);
/*     */           }
/*     */           
/*     */           private void updateLabel(DocumentEvent e)
/*     */           {
/* 299 */             textFieldTypeFT.setText(textG.zoneTexte.getText() + "&&" + textD.zoneTexte.getText());
/* 300 */             if (CharacValueRenderer.this.refresh) {
/* 301 */               if (dicoD.containsKey(textD.zoneTexte.getText())) {
/* 302 */                 CharacValueRenderer.this.refresh = false;
/* 303 */                 textG.zoneTexte.setText((String)dicoD.get(textD.zoneTexte.getText()));
/* 304 */                 CharacValueRenderer.this.refresh = true;
/*     */               }
/* 306 */               else if (!textG.new_val) {
/* 307 */                 CharacValueRenderer.this.refresh = false;
/* 308 */                 textG.zoneTexte.setText("");
/* 309 */                 CharacValueRenderer.this.refresh = true;
/* 310 */                 textD.new_val = true;
/*     */ 
/*     */               }
/*     */               
/*     */             }
/*     */             
/*     */           }
/*     */           
/*     */ 
/* 319 */         };
/* 320 */         textG.zoneTexte.getDocument().addDocumentListener(subDocG);
/* 321 */         textD.zoneTexte.getDocument().addDocumentListener(subDocD);
/*     */         
/* 323 */         if ((characValue.oldVal != null) && (characValue.oldVal.contains("&&"))) {
/*     */           try
/*     */           {
/* 326 */             textG.zoneTexte.setText(characValue.oldVal.split("&&")[0]);
/* 327 */             textD.zoneTexte.setText(characValue.oldVal.split("&&")[1]);
/*     */           } catch (Exception e) {
/* 329 */             textG.zoneTexte.setText("");
/* 330 */             textD.zoneTexte.setText("");
/*     */           }
/*     */         }
/*     */         
/* 334 */         characValue.text = textFieldTypeFT;
/* 335 */         characValue.box = null;
/*     */         
/* 337 */         textFieldTypeFT.setVisible(false);
/*     */         
/* 339 */         textG.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
/* 340 */         textD.setPreferredSize(new Dimension((int)(124.0D * (area.width / 1366.0D)), 20));
/*     */         
/* 342 */         final JButton UKN = new JButton("?");
/* 343 */         UKN.setFocusable(false);
/* 344 */         UKN.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
/* 345 */         UKN.setForeground(Color.decode("#445469"));
/* 346 */         UKN.setOpaque(true);
/* 347 */         UKN.setBackground(Color.decode("#F29B26"));
/* 348 */         UKN.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 350 */             textG.setText("Inconnu");
/* 351 */             textD.setText("Unknown");
/*     */           }
/* 353 */         });
/* 354 */         UKN.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseEntered(MouseEvent e) {
/* 357 */             UKN.setCursor(Cursor.getPredefinedCursor(12));
/* 358 */             UKN.setToolTipText("Set to Unknown");
/*     */           }
/*     */           
/* 361 */         });
/* 362 */         pnl.add(textG);
/* 363 */         pnl.add(textD);
/* 364 */         pnl.add(UKN);
/*     */         
/*     */ 
/* 367 */         break;
/*     */       case VL: 
/* 369 */         JLabel jlabelVL = new JLabel(characValue.getType().toString() + " ");
/* 370 */         jlabelVL.setForeground(Color.BLACK);
/* 371 */         pnl.add(jlabelVL);
/* 372 */         characValue.getListValues().add("Autre / Other");
/* 373 */         String[] options = new String[characValue.getListValues().size()];
/* 374 */         options = (String[])characValue.getListValues().toArray(options);
/* 375 */         final JComboBox<String> patternList = new JComboBox(options);
/* 376 */         if (characValue.oldVal != null) {
/* 377 */           if (characValue.oldVal.equals("Autre / Other")) {
/* 378 */             patternList.addItem("Autre / Other: " + characValue.oldComp);
/* 379 */             patternList.setSelectedItem("Autre / Other: " + characValue.oldComp);
/*     */           } else {
/* 381 */             patternList.addItem(characValue.oldVal);
/* 382 */             patternList.setSelectedItem(characValue.oldVal);
/*     */           }
/*     */         }
/*     */         
/* 386 */         characValue.box = patternList;
/* 387 */         characValue.text = null;
/* 388 */         patternList.setPreferredSize(new Dimension((int)(250.0D * (area.width / 1366.0D)), 20));
/* 389 */         pnl.add(patternList);
/* 390 */         ComboBoxActionListner lst = new ComboBoxActionListner(pnl, characValue.hist, area);
/* 391 */         patternList.addItemListener(lst);
/* 392 */         characValue.comp = lst.textField;
/*     */         
/* 394 */         final JButton UKN1 = new JButton("?");
/* 395 */         UKN1.setFocusable(false);
/* 396 */         UKN1.setPreferredSize(new Dimension((int)(8.0D * (area.width / 1366.0D)), 20));
/* 397 */         UKN1.setForeground(Color.decode("#445469"));
/* 398 */         UKN1.setOpaque(true);
/* 399 */         UKN1.setBackground(Color.decode("#F29B26"));
/* 400 */         UKN1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 402 */             patternList.setSelectedItem("Inconnu/Unknown");
/*     */           }
/*     */           
/* 405 */         });
/* 406 */         UKN1.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseEntered(MouseEvent e) {
/* 409 */             UKN1.setCursor(Cursor.getPredefinedCursor(12));
/* 410 */             UKN1.setToolTipText("Set to Unknown");
/*     */           }
/* 412 */         });
/* 413 */         pnl.add(UKN1);
/*     */       }
/*     */       
/* 416 */       GridBagConstraints gbc_pnlCharac = new GridBagConstraints();
/* 417 */       gbc_pnlCharac.insets = new Insets(0, 0, 0, 5);
/* 418 */       gbc_pnlCharac.fill = 1;
/* 419 */       gbc_pnlCharac.gridx = 0;
/* 420 */       gbc_pnlCharac.gridy = i;
/* 421 */       pnlParent.add(pnl, gbc_pnlCharac);
/* 422 */       i++;
/*     */     }
/* 424 */     pnlParent.repaint();
/*     */   }
/*     */ }

