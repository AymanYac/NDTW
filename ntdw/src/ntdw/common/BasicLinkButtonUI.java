 package ntdw.common;
 
 import java.awt.Color;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.Rectangle;
 import javax.swing.ButtonModel;
 import javax.swing.JComponent;
 import javax.swing.plaf.ComponentUI;
 import javax.swing.plaf.metal.MetalButtonUI;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 class BasicLinkButtonUI
   extends MetalButtonUI
 {
   private static final BasicLinkButtonUI ui = new BasicLinkButtonUI();
   
 
 
   public static ComponentUI createUI(JComponent jcomponent)
   {
     return ui;
   }
   
   protected void paintText(Graphics g, JComponent com, Rectangle rect, String s)
   {
     JLinkButton bn = (JLinkButton)com;
     ButtonModel bnModel = bn.getModel();
     Color color = bn.getForeground();
     Object obj = null;
     if (bnModel.isEnabled()) {
       if (bnModel.isPressed()) {
         bn.setForeground(bn.getActiveLinkColor());
       } else if (bn.isLinkVisited()) {
         bn.setForeground(bn.getVisitedLinkColor());
       }
       else {
         bn.setForeground(bn.getLinkColor());
       }
     } else if (bn.getDisabledLinkColor() != null) {
       bn.setForeground(bn.getDisabledLinkColor());
     }
     super.paintText(g, com, rect, s);
     int behaviour = bn.getLinkBehavior();
     boolean drawLine = false;
     if (behaviour == 1) {
       if (bnModel.isRollover())
         drawLine = true;
     } else if ((behaviour == 0) || (behaviour == 3))
       drawLine = true;
     if (!drawLine)
       return;
     FontMetrics fm = g.getFontMetrics();
     int x = rect.x + getTextShiftOffset();
     int y = rect.y + fm.getAscent() + fm.getDescent() + getTextShiftOffset() - 1;
     if (bnModel.isEnabled()) {
       g.setColor(bn.getForeground());
       g.drawLine(x, y, x + rect.width - 1, y);
     } else {
       g.setColor(bn.getBackground().brighter());
       g.drawLine(x, y, x + rect.width - 1, y);
     }
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\BasicLinkButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */