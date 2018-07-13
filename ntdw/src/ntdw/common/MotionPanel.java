/*    */ package ntdw.common;
/*    */ 
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseMotionAdapter;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MotionPanel
/*    */   extends JPanel
/*    */ {
/*    */   private Point initialClick;
/*    */   private JFrame parent;
/*    */   
/*    */   public MotionPanel(final JFrame parent)
/*    */   {
/* 20 */     this.parent = parent;
/*    */     
/* 22 */     addMouseListener(new MouseAdapter()
/*    */     {
/*    */       public void mousePressed(MouseEvent e) {
/* 25 */         MotionPanel.this.initialClick = e.getPoint();
/* 26 */         MotionPanel.this.getComponentAt(MotionPanel.this.initialClick);
/*    */       }
/*    */       
/* 29 */     });
/* 30 */     addMouseMotionListener(new MouseMotionAdapter()
/*    */     {
/*    */ 
/*    */       public void mouseDragged(MouseEvent e)
/*    */       {
/* 35 */         int thisX = parent.getLocation().x;
/* 36 */         int thisY = parent.getLocation().y;
/*    */         
/*    */         try
/*    */         {
/* 40 */           int xMoved = thisX + e.getX() - (thisX + MotionPanel.this.initialClick.x);
/* 41 */           int yMoved = thisY + e.getY() - (thisY + MotionPanel.this.initialClick.y);
/*    */           
/*    */ 
/* 44 */           int X = thisX + xMoved;
/* 45 */           int Y = thisY + yMoved;
/* 46 */           parent.setLocation(X, Y);
/*    */         }
/*    */         catch (Exception localException) {}
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\MotionPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */