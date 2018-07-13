 package ntdw.resources.images;
 
 import javax.swing.Icon;
 import javax.swing.JComponent;
 
 
 
 
 
 public class CircularAnimatedIcon
   extends AnimatedIcon
 {
   private int direction = 1;
   
 
 
 
 
 
 
 
   public CircularAnimatedIcon(JComponent component, Icon... icons)
   {
     super(component, icons);
   }
   
 
 
 
 
 
 
 
   public CircularAnimatedIcon(JComponent component, int delay, Icon... icons)
   {
     super(component, delay, icons);
   }
   
 
 
 
 
 
 
 
 
   public CircularAnimatedIcon(JComponent component, int delay, int cycles, Icon... icons)
   {
     super(component, delay, cycles, icons);
   }
   
 
 
 
 
 
 
 
 
 
 
   protected int getNextIconIndex(int currentIndex, int iconCount)
   {
     if (iconCount == 1) { return 0;
     }
     currentIndex += this.direction;
     
 
 
     if (currentIndex == iconCount)
     {
       currentIndex -= 2;
       this.direction = -1;
     }
     
 
 
     if (currentIndex == 0)
     {
       currentIndex = 0;
       this.direction = 1;
     }
     
     return currentIndex;
   }
   
 
 
 
 
 
 
 
 
 
   protected boolean isCycleCompleted(int currentIndex, int iconCount)
   {
     return (currentIndex == 0) && (this.direction == 1);
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\resources\images\CircularAnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */