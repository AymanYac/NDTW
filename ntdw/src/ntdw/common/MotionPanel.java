 package ntdw.common;
 
 import java.awt.Point;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseMotionAdapter;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 
 
 public class MotionPanel
   extends JPanel
 {
   private Point initialClick;
   private JFrame parent;
   
   public MotionPanel(final JFrame parent)
   {
     this.parent = parent;
     
     addMouseListener(new MouseAdapter()
     {
       public void mousePressed(MouseEvent e) {
         MotionPanel.this.initialClick = e.getPoint();
         MotionPanel.this.getComponentAt(MotionPanel.this.initialClick);
       }
       
     });
     addMouseMotionListener(new MouseMotionAdapter()
     {
 
       public void mouseDragged(MouseEvent e)
       {
         int thisX = parent.getLocation().x;
         int thisY = parent.getLocation().y;
         
         try
         {
           int xMoved = thisX + e.getX() - (thisX + MotionPanel.this.initialClick.x);
           int yMoved = thisY + e.getY() - (thisY + MotionPanel.this.initialClick.y);
           
 
           int X = thisX + xMoved;
           int Y = thisY + yMoved;
           parent.setLocation(X, Y);
         }
         catch (Exception localException) {}
       }
     });
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\common\MotionPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */