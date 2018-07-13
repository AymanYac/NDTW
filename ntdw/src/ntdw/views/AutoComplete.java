/*     */ package ntdw.views;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JWindow;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.ListModel;
/*     */ import ntdw.model.AutoCompleteModel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoComplete
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public JTextField zoneTexte;
/*     */   private JWindow fenetreRecherche;
/*     */   private JList resultats;
/*     */   private DefaultListModel modelListe;
/*     */   private AutoCompleteModel model;
/*     */   public boolean new_val;
/*     */   public AutoComplete couple;
/*     */   
/*     */   public AutoComplete(final AutoCompleteModel model)
/*     */   {
/*  69 */     this.model = model;
/*  70 */     this.new_val = false;
/*  71 */     this.zoneTexte = new JTextField();
/*  72 */     this.modelListe = new DefaultListModel();
/*  73 */     this.resultats = new JList(this.modelListe);
/*  74 */     this.resultats.setBorder(BorderFactory.createEtchedBorder());
/*  75 */     this.fenetreRecherche = new JWindow();
/*  76 */     JScrollPane scrollPane = new JScrollPane(this.resultats);
/*  77 */     JScrollBar vertical = scrollPane.getVerticalScrollBar();
/*  78 */     InputMap im = vertical.getInputMap(2);
/*  79 */     im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
/*  80 */     im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
/*  81 */     this.fenetreRecherche.add(scrollPane);
/*  82 */     this.zoneTexte.addKeyListener(new KeyListener()
/*     */     {
/*     */       public void keyTyped(KeyEvent e) {}
/*     */       
/*     */ 
/*     */       public void keyReleased(KeyEvent e)
/*     */       {
/*  89 */         if (e.getKeyCode() == 27) {
/*  90 */           AutoComplete.this.fenetreRecherche.setVisible(false);
/*     */         }
/*  92 */         else if (e.getKeyCode() == 40) {
/*  93 */           if (AutoComplete.this.resultats.getSelectedIndex() < AutoComplete.this.resultats.getModel().getSize()) {
/*  94 */             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getSelectedIndex() + 1);
/*  95 */             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */           }
/*     */           else {
/*  98 */             AutoComplete.this.resultats.setSelectedIndex(0);
/*  99 */             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */           }
/*     */         }
/* 102 */         else if (e.getKeyCode() == 38) {
/* 103 */           if (AutoComplete.this.resultats.getSelectedIndex() != AutoComplete.this.resultats.getModel().getSize()) {
/* 104 */             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getSelectedIndex() - 1);
/* 105 */             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */           }
/*     */           else {
/* 108 */             AutoComplete.this.resultats.setSelectedIndex(AutoComplete.this.resultats.getModel().getSize());
/* 109 */             AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */           }
/*     */         }
/* 112 */         else if (e.getKeyCode() == 10) {
/* 113 */           try{
						AutoComplete.this.couple.new_val = false;
					}catch(Exception NO_COUPLE) {
						
					}
/* 114 */           AutoComplete.this.new_val = false;
/* 115 */           AutoComplete.this.zoneTexte.setText(AutoComplete.this.resultats.getSelectedValue().toString());
/* 117 */           AutoComplete.this.fenetreRecherche.setVisible(false);
/*     */         }
/*     */         else {
/* 120 */           AutoComplete.this.update();
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */       public void keyPressed(KeyEvent e) {}
/* 126 */     });
/* 127 */     this.zoneTexte.addKeyListener(new KeyAdapter() {
/*     */       public void keyTyped(KeyEvent e) {
/* 129 */         if (AutoComplete.this.zoneTexte.getText().length() >= 30)
/* 130 */           e.consume();
/*     */       }
/* 132 */     });
/* 133 */     this.zoneTexte.addMouseListener(new MouseAdapter()
/*     */     {
/*     */       public void mouseClicked(MouseEvent e) {
/* 136 */         List<String> correspondants = model.getToutesChaines();
/* 137 */         AutoComplete.this.modelListe.clear();
/* 138 */         if (correspondants.size() == 0) {
/* 139 */           AutoComplete.this.fenetreRecherche.setVisible(false);
/*     */         }
/*     */         else
/*     */         {
/* 143 */           for (String s : correspondants) {
/* 144 */             AutoComplete.this.modelListe.addElement(s);
/*     */           }
/*     */           
/* 147 */           AutoComplete.this.fenetreRecherche.setBounds((int)AutoComplete.this.getLocationOnScreen().getX(), (int)AutoComplete.this.getLocationOnScreen().getY() + AutoComplete.this.zoneTexte.getHeight(), AutoComplete.this.getWidth(), 13 * AutoComplete.this.zoneTexte.getHeight());
/* 148 */           AutoComplete.this.fenetreRecherche.setVisible(true);
/* 149 */           AutoComplete.this.resultats.setSelectedIndex(0);
/* 150 */           AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */         }
/*     */         
/*     */       }
/* 154 */     });
/* 155 */     FocusListener l = new FocusListener()
/*     */     {
/*     */ 
/*     */       public void focusGained(FocusEvent e)
/*     */       {
/* 160 */         List<String> correspondants = new ArrayList();
/* 161 */         if (AutoComplete.this.zoneTexte.getText().length() > 0) {
/* 162 */           correspondants = model.getChainesCorrespondates(AutoComplete.this.zoneTexte.getText());
/*     */         } else {
/* 164 */           correspondants = model.getToutesChaines();
/*     */         }
/*     */         
/* 167 */         AutoComplete.this.modelListe.clear();
/* 168 */         if (correspondants.size() == 0) {
/* 169 */           AutoComplete.this.fenetreRecherche.setVisible(false);
/*     */         }
/*     */         else
/*     */         {
/* 173 */           for (String s : correspondants) {
/* 174 */             AutoComplete.this.modelListe.addElement(s);
/*     */           }
/*     */           
/* 177 */           AutoComplete.this.fenetreRecherche.setBounds((int)AutoComplete.this.getLocationOnScreen().getX(), (int)AutoComplete.this.getLocationOnScreen().getY() + AutoComplete.this.zoneTexte.getHeight(), AutoComplete.this.getWidth(), 13 * AutoComplete.this.zoneTexte.getHeight());
/* 178 */           AutoComplete.this.fenetreRecherche.setVisible(true);
/* 179 */           AutoComplete.this.resultats.setSelectedIndex(0);
/* 180 */           AutoComplete.this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */       public void focusLost(FocusEvent e)
/*     */       {
/* 187 */         AutoComplete.this.fenetreRecherche.setVisible(false);
/*     */       }
/*     */       
/*     */ 
/* 191 */     };
/* 192 */     this.zoneTexte.addFocusListener(l);
/* 193 */     setLayout(new GridLayout(1, 0));
/* 194 */     add(this.zoneTexte);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void update()
/*     */   {
/* 201 */     List<String> correspondants = this.model.getChainesCorrespondates(this.zoneTexte.getText());
/* 202 */     this.modelListe.clear();
/* 203 */     if (correspondants.size() == 0) {
/* 204 */       this.fenetreRecherche.setVisible(false);
/*     */     }
/*     */     else
/*     */     {
/* 208 */       for (String s : correspondants) {
/* 209 */         this.modelListe.addElement(s);
/* 210 */         this.fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY() + this.zoneTexte.getHeight(), getWidth(), 13 * this.zoneTexte.getHeight());
/* 211 */         this.fenetreRecherche.setVisible(true);
/* 212 */         this.resultats.setSelectedIndex(1);
/* 213 */         this.resultats.setSelectionBackground(Color.LIGHT_GRAY);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setText(String oldVal)
/*     */   {
/* 220 */     this.zoneTexte.setText(oldVal);
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\views\AutoComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */