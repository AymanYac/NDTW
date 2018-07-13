/*     */ package ntdw.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.net.URL;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
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
/*     */ public class JLinkButton
/*     */   extends JButton
/*     */ {
/*     */   private static final String uiString = "LinkButtonUI";
/*     */   public static final int ALWAYS_UNDERLINE = 0;
/*     */   public static final int HOVER_UNDERLINE = 1;
/*     */   public static final int NEVER_UNDERLINE = 2;
/*     */   public static final int SYSTEM_DEFAULT = 3;
/*     */   private int linkBehavior;
/*     */   private Color linkColor;
/*     */   private Color colorPressed;
/*     */   private Color visitedLinkColor;
/*     */   private Color disabledLinkColor;
/*     */   private URL buttonURL;
/*     */   private Action defaultAction;
/*     */   private boolean isLinkVisited;
/*     */   
/*     */   public JLinkButton()
/*     */   {
/*  47 */     this(null, null, null);
/*     */   }
/*     */   
/*     */   public JLinkButton(Action action) {
/*  51 */     this();
/*  52 */     setAction(action);
/*     */   }
/*     */   
/*     */   public JLinkButton(Icon icon) {
/*  56 */     this(null, icon, null);
/*     */   }
/*     */   
/*     */   public JLinkButton(String s) {
/*  60 */     this(s, null, null);
/*     */   }
/*     */   
/*     */   public JLinkButton(URL url) {
/*  64 */     this(null, null, url);
/*     */   }
/*     */   
/*     */   public JLinkButton(String s, URL url) {
/*  68 */     this(s, null, url);
/*     */   }
/*     */   
/*     */   public JLinkButton(Icon icon, URL url) {
/*  72 */     this(null, icon, url);
/*     */   }
/*     */   
/*     */   public JLinkButton(String text, Icon icon, URL url) {
/*  76 */     super(text, icon);
/*  77 */     this.linkBehavior = 3;
/*  78 */     this.linkColor = Color.BLUE;
/*  79 */     this.colorPressed = Color.red;
/*  80 */     this.visitedLinkColor = new Color(128, 0, 128);
/*  81 */     if ((text == null) && (url != null))
/*  82 */       setText(url.toExternalForm());
/*  83 */     setLinkURL(url);
/*  84 */     setCursor(Cursor.getPredefinedCursor(12));
/*  85 */     setBorderPainted(false);
/*  86 */     setContentAreaFilled(false);
/*  87 */     setRolloverEnabled(true);
/*  88 */     addActionListener(this.defaultAction);
/*     */   }
/*     */   
/*     */   public void updateUI() {
/*  92 */     setUI(BasicLinkButtonUI.createUI(this));
/*     */   }
/*     */   
/*     */   private void setDefault() {
/*  96 */     UIManager.getDefaults().put("LinkButtonUI", "BasicLinkButtonUI");
/*     */   }
/*     */   
/*     */   public String getUIClassID() {
/* 100 */     return "LinkButtonUI";
/*     */   }
/*     */   
/*     */   protected void setupToolTipText() {
/* 104 */     String tip = null;
/* 105 */     if (this.buttonURL != null)
/* 106 */       tip = this.buttonURL.toExternalForm();
/* 107 */     setToolTipText(tip);
/*     */   }
/*     */   
/*     */   public void setLinkBehavior(int bnew) {
/* 111 */     checkLinkBehaviour(bnew);
/* 112 */     int old = this.linkBehavior;
/* 113 */     this.linkBehavior = bnew;
/* 114 */     firePropertyChange("linkBehavior", old, bnew);
/* 115 */     repaint();
/*     */   }
/*     */   
/*     */   private void checkLinkBehaviour(int beha) {
/* 119 */     if ((beha != 0) && (beha != 1) && 
/* 120 */       (beha != 2) && (beha != 3)) {
/* 121 */       throw new IllegalArgumentException("Not a legal LinkBehavior");
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLinkBehavior()
/*     */   {
/* 127 */     return this.linkBehavior;
/*     */   }
/*     */   
/*     */   public void setLinkColor(Color color) {
/* 131 */     Color colorOld = this.linkColor;
/* 132 */     this.linkColor = color;
/* 133 */     firePropertyChange("linkColor", colorOld, color);
/* 134 */     repaint();
/*     */   }
/*     */   
/*     */   public Color getLinkColor() {
/* 138 */     return this.linkColor;
/*     */   }
/*     */   
/*     */   public void setActiveLinkColor(Color colorNew) {
/* 142 */     Color colorOld = this.colorPressed;
/* 143 */     this.colorPressed = colorNew;
/* 144 */     firePropertyChange("activeLinkColor", colorOld, colorNew);
/* 145 */     repaint();
/*     */   }
/*     */   
/*     */   public Color getActiveLinkColor() {
/* 149 */     return this.colorPressed;
/*     */   }
/*     */   
/*     */   public void setDisabledLinkColor(Color color) {
/* 153 */     Color colorOld = this.disabledLinkColor;
/* 154 */     this.disabledLinkColor = color;
/* 155 */     firePropertyChange("disabledLinkColor", colorOld, color);
/* 156 */     if (!isEnabled())
/* 157 */       repaint();
/*     */   }
/*     */   
/*     */   public Color getDisabledLinkColor() {
/* 161 */     return this.disabledLinkColor;
/*     */   }
/*     */   
/*     */   public void setVisitedLinkColor(Color colorNew) {
/* 165 */     Color colorOld = this.visitedLinkColor;
/* 166 */     this.visitedLinkColor = colorNew;
/* 167 */     firePropertyChange("visitedLinkColor", colorOld, colorNew);
/* 168 */     repaint();
/*     */   }
/*     */   
/*     */   public Color getVisitedLinkColor() {
/* 172 */     return this.visitedLinkColor;
/*     */   }
/*     */   
/*     */   public URL getLinkURL() {
/* 176 */     return this.buttonURL;
/*     */   }
/*     */   
/*     */   public void setLinkURL(URL url) {
/* 180 */     URL urlOld = this.buttonURL;
/* 181 */     this.buttonURL = url;
/* 182 */     setupToolTipText();
/* 183 */     firePropertyChange("linkURL", urlOld, url);
/* 184 */     revalidate();
/* 185 */     repaint();
/*     */   }
/*     */   
/*     */   public void setLinkVisited(boolean flagNew) {
/* 189 */     boolean flagOld = this.isLinkVisited;
/* 190 */     this.isLinkVisited = flagNew;
/* 191 */     firePropertyChange("linkVisited", flagOld, flagNew);
/* 192 */     repaint();
/*     */   }
/*     */   
/*     */   public boolean isLinkVisited() {
/* 196 */     return this.isLinkVisited;
/*     */   }
/*     */   
/*     */   public void setDefaultAction(Action actionNew) {
/* 200 */     Action actionOld = this.defaultAction;
/* 201 */     this.defaultAction = actionNew;
/* 202 */     firePropertyChange("defaultAction", actionOld, actionNew);
/*     */   }
/*     */   
/*     */ 
/* 206 */   public Action getDefaultAction() { return this.defaultAction; }
/*     */   

protected String paramString() {

  String str;

  if (linkBehavior == ALWAYS_UNDERLINE)

    str = "ALWAYS_UNDERLINE";

  else if (linkBehavior == HOVER_UNDERLINE)

    str = "HOVER_UNDERLINE";

  else if (linkBehavior == NEVER_UNDERLINE)

    str = "NEVER_UNDERLINE";

  else

    str = "SYSTEM_DEFAULT";

  String colorStr = linkColor == null ? "" : linkColor.toString();

  String colorPressStr = colorPressed == null ? "" : colorPressed

      .toString();

  String disabledLinkColorStr = disabledLinkColor == null ? ""

      : disabledLinkColor.toString();

  String visitedLinkColorStr = visitedLinkColor == null ? ""

      : visitedLinkColor.toString();

  String buttonURLStr = buttonURL == null ? "" : buttonURL.toString();

  String isLinkVisitedStr = isLinkVisited ? "true" : "false";

  return super.paramString() + ",linkBehavior=" + str + ",linkURL="

      + buttonURLStr + ",linkColor=" + colorStr + ",activeLinkColor="

      + colorPressStr + ",disabledLinkColor=" + disabledLinkColorStr

      + ",visitedLinkColor=" + visitedLinkColorStr

      + ",linkvisitedString=" + isLinkVisitedStr;

}

}