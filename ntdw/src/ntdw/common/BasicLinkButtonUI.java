/*     */ package ntdw.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.metal.MetalButtonUI;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BasicLinkButtonUI
/*     */   extends MetalButtonUI
/*     */ {
/* 237 */   private static final BasicLinkButtonUI ui = new BasicLinkButtonUI();
/*     */   
/*     */ 
/*     */ 
/*     */   public static ComponentUI createUI(JComponent jcomponent)
/*     */   {
/* 243 */     return ui;
/*     */   }
/*     */   
/*     */   protected void paintText(Graphics g, JComponent com, Rectangle rect, String s)
/*     */   {
/* 248 */     JLinkButton bn = (JLinkButton)com;
/* 249 */     ButtonModel bnModel = bn.getModel();
/* 250 */     Color color = bn.getForeground();
/* 251 */     Object obj = null;
/* 252 */     if (bnModel.isEnabled()) {
/* 253 */       if (bnModel.isPressed()) {
/* 254 */         bn.setForeground(bn.getActiveLinkColor());
/* 255 */       } else if (bn.isLinkVisited()) {
/* 256 */         bn.setForeground(bn.getVisitedLinkColor());
/*     */       }
/*     */       else {
/* 259 */         bn.setForeground(bn.getLinkColor());
/*     */       }
/* 261 */     } else if (bn.getDisabledLinkColor() != null) {
/* 262 */       bn.setForeground(bn.getDisabledLinkColor());
/*     */     }
/* 264 */     super.paintText(g, com, rect, s);
/* 265 */     int behaviour = bn.getLinkBehavior();
/* 266 */     boolean drawLine = false;
/* 267 */     if (behaviour == 1) {
/* 268 */       if (bnModel.isRollover())
/* 269 */         drawLine = true;
/* 270 */     } else if ((behaviour == 0) || (behaviour == 3))
/* 271 */       drawLine = true;
/* 272 */     if (!drawLine)
/* 273 */       return;
/* 274 */     FontMetrics fm = g.getFontMetrics();
/* 275 */     int x = rect.x + getTextShiftOffset();
/* 276 */     int y = rect.y + fm.getAscent() + fm.getDescent() + getTextShiftOffset() - 1;
/* 277 */     if (bnModel.isEnabled()) {
/* 278 */       g.setColor(bn.getForeground());
/* 279 */       g.drawLine(x, y, x + rect.width - 1, y);
/*     */     } else {
/* 281 */       g.setColor(bn.getBackground().brighter());
/* 282 */       g.drawLine(x, y, x + rect.width - 1, y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\BasicLinkButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */