/*    */ package ntdw.resources.images;
/*    */ 
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CircularAnimatedIcon
/*    */   extends AnimatedIcon
/*    */ {
/* 13 */   private int direction = 1;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CircularAnimatedIcon(JComponent component, Icon... icons)
/*    */   {
/* 24 */     super(component, icons);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CircularAnimatedIcon(JComponent component, int delay, Icon... icons)
/*    */   {
/* 36 */     super(component, delay, icons);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CircularAnimatedIcon(JComponent component, int delay, int cycles, Icon... icons)
/*    */   {
/* 49 */     super(component, delay, cycles, icons);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected int getNextIconIndex(int currentIndex, int iconCount)
/*    */   {
/* 64 */     if (iconCount == 1) { return 0;
/*    */     }
/* 66 */     currentIndex += this.direction;
/*    */     
/*    */ 
/*    */ 
/* 70 */     if (currentIndex == iconCount)
/*    */     {
/* 72 */       currentIndex -= 2;
/* 73 */       this.direction = -1;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 78 */     if (currentIndex == 0)
/*    */     {
/* 80 */       currentIndex = 0;
/* 81 */       this.direction = 1;
/*    */     }
/*    */     
/* 84 */     return currentIndex;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected boolean isCycleCompleted(int currentIndex, int iconCount)
/*    */   {
/* 98 */     return (currentIndex == 0) && (this.direction == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\resources\images\CircularAnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */