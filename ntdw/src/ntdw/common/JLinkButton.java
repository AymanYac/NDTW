 package ntdw.common;
 
 import java.awt.Color;
 import java.awt.Cursor;
 import java.net.URL;
 import javax.swing.Action;
 import javax.swing.Icon;
 import javax.swing.JButton;
 import javax.swing.UIDefaults;
 import javax.swing.UIManager;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class JLinkButton
   extends JButton
 {
   private static final String uiString = "LinkButtonUI";
   public static final int ALWAYS_UNDERLINE = 0;
   public static final int HOVER_UNDERLINE = 1;
   public static final int NEVER_UNDERLINE = 2;
   public static final int SYSTEM_DEFAULT = 3;
   private int linkBehavior;
   private Color linkColor;
   private Color colorPressed;
   private Color visitedLinkColor;
   private Color disabledLinkColor;
   private URL buttonURL;
   private Action defaultAction;
   private boolean isLinkVisited;
   
   public JLinkButton()
   {
     this(null, null, null);
   }
   
   public JLinkButton(Action action) {
     this();
     setAction(action);
   }
   
   public JLinkButton(Icon icon) {
     this(null, icon, null);
   }
   
   public JLinkButton(String s) {
     this(s, null, null);
   }
   
   public JLinkButton(URL url) {
     this(null, null, url);
   }
   
   public JLinkButton(String s, URL url) {
     this(s, null, url);
   }
   
   public JLinkButton(Icon icon, URL url) {
     this(null, icon, url);
   }
   
   public JLinkButton(String text, Icon icon, URL url) {
     super(text, icon);
     this.linkBehavior = 3;
     this.linkColor = Color.BLUE;
     this.colorPressed = Color.red;
     this.visitedLinkColor = new Color(128, 0, 128);
     if ((text == null) && (url != null))
       setText(url.toExternalForm());
     setLinkURL(url);
     setCursor(Cursor.getPredefinedCursor(12));
     setBorderPainted(false);
     setContentAreaFilled(false);
     setRolloverEnabled(true);
     addActionListener(this.defaultAction);
   }
   
   public void updateUI() {
     setUI(BasicLinkButtonUI.createUI(this));
   }
   
   private void setDefault() {
     UIManager.getDefaults().put("LinkButtonUI", "BasicLinkButtonUI");
   }
   
   public String getUIClassID() {
     return "LinkButtonUI";
   }
   
   protected void setupToolTipText() {
     String tip = null;
     if (this.buttonURL != null)
       tip = this.buttonURL.toExternalForm();
     setToolTipText(tip);
   }
   
   public void setLinkBehavior(int bnew) {
     checkLinkBehaviour(bnew);
     int old = this.linkBehavior;
     this.linkBehavior = bnew;
     firePropertyChange("linkBehavior", old, bnew);
     repaint();
   }
   
   private void checkLinkBehaviour(int beha) {
     if ((beha != 0) && (beha != 1) && 
       (beha != 2) && (beha != 3)) {
       throw new IllegalArgumentException("Not a legal LinkBehavior");
     }
   }
   
   public int getLinkBehavior()
   {
     return this.linkBehavior;
   }
   
   public void setLinkColor(Color color) {
     Color colorOld = this.linkColor;
     this.linkColor = color;
     firePropertyChange("linkColor", colorOld, color);
     repaint();
   }
   
   public Color getLinkColor() {
     return this.linkColor;
   }
   
   public void setActiveLinkColor(Color colorNew) {
     Color colorOld = this.colorPressed;
     this.colorPressed = colorNew;
     firePropertyChange("activeLinkColor", colorOld, colorNew);
     repaint();
   }
   
   public Color getActiveLinkColor() {
     return this.colorPressed;
   }
   
   public void setDisabledLinkColor(Color color) {
     Color colorOld = this.disabledLinkColor;
     this.disabledLinkColor = color;
     firePropertyChange("disabledLinkColor", colorOld, color);
     if (!isEnabled())
       repaint();
   }
   
   public Color getDisabledLinkColor() {
     return this.disabledLinkColor;
   }
   
   public void setVisitedLinkColor(Color colorNew) {
     Color colorOld = this.visitedLinkColor;
     this.visitedLinkColor = colorNew;
     firePropertyChange("visitedLinkColor", colorOld, colorNew);
     repaint();
   }
   
   public Color getVisitedLinkColor() {
     return this.visitedLinkColor;
   }
   
   public URL getLinkURL() {
     return this.buttonURL;
   }
   
   public void setLinkURL(URL url) {
     URL urlOld = this.buttonURL;
     this.buttonURL = url;
     setupToolTipText();
     firePropertyChange("linkURL", urlOld, url);
     revalidate();
     repaint();
   }
   
   public void setLinkVisited(boolean flagNew) {
     boolean flagOld = this.isLinkVisited;
     this.isLinkVisited = flagNew;
     firePropertyChange("linkVisited", flagOld, flagNew);
     repaint();
   }
   
   public boolean isLinkVisited() {
     return this.isLinkVisited;
   }
   
   public void setDefaultAction(Action actionNew) {
     Action actionOld = this.defaultAction;
     this.defaultAction = actionNew;
     firePropertyChange("defaultAction", actionOld, actionNew);
   }
   
 
   public Action getDefaultAction() { return this.defaultAction; }
   

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