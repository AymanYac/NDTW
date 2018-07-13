 package ntdw.resources.images;
 
 import java.awt.Component;
 import java.awt.Graphics;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.util.ArrayList;
 import java.util.List;
 import javax.swing.Icon;
 import javax.swing.JComponent;
 import javax.swing.Timer;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AnimatedIcon
   implements Icon, ActionListener, Runnable
 {
   private static final int DEFAULT_DELAY = 500;
   private static final int DEFAULT_CYCLES = -1;
   public static final float TOP = 0.0F;
   public static final float LEFT = 0.0F;
   public static final float CENTER = 0.5F;
   public static final float BOTTOM = 1.0F;
   public static final float RIGHT = 1.0F;
   private JComponent component;
   private List<Icon> icons = new ArrayList();
   
   private int cycles;
   private boolean showFirstIcon = false;
   
   private float alignmentX = 0.5F;
   private float alignmentY = 0.5F;
   
 
   private int iconX;
   
 
   private int iconY;
   
 
   private int iconWidth;
   
   private int iconHeight;
   
   private int currentIconIndex;
   
   private int cyclesCompleted;
   
   private boolean animationFinished = true;
   
 
 
 
   private Timer timer;
   
 
 
 
   public AnimatedIcon(JComponent component, Icon... icons)
   {
     this(component, 500, icons);
   }
   
 
 
 
 
 
 
 
   public AnimatedIcon(JComponent component, int delay, Icon... icons)
   {
     this(component, delay, -1, icons);
   }
   
 
 
 
 
 
 
 
 
   public AnimatedIcon(JComponent component, int delay, int cycles, Icon... icons)
   {
     this.component = component;
     setCycles(cycles);
     
     for (int i = 0; i < icons.length; i++)
     {
       if (icons[i] == null)
       {
         String message = "Icon (" + i + ") cannot be null";
         throw new IllegalArgumentException(message);
       }
       
 
       addIcon(new Icon[] { icons[i] });
     }
     
 
     this.timer = new Timer(delay, this);
   }
   
 
 
   public void addIcon(Icon... icons)
   {
     Icon[] arrayOfIcon;
     
 
     int j = (arrayOfIcon = icons).length; for (int i = 0; i < j; i++) { Icon icon = arrayOfIcon[i];
       
       if (icon != null)
       {
         this.icons.add(icon);
         calculateIconDimensions();
       }
     }
   }
   
 
 
 
 
   private void calculateIconDimensions()
   {
     this.iconWidth = 0;
     this.iconHeight = 0;
     
     for (Icon icon : this.icons)
     {
       this.iconWidth = Math.max(this.iconWidth, icon.getIconWidth());
       this.iconHeight = Math.max(this.iconHeight, icon.getIconHeight());
     }
   }
   
 
 
 
 
 
   public float getAlignmentX()
   {
     return this.alignmentX;
   }
   
 
 
 
 
 
 
   public void setAlignmentX(float alignmentX)
   {
     this.alignmentX = (alignmentX < 0.0F ? 0.0F : alignmentX > 1.0F ? 1.0F : alignmentX);
   }
   
 
 
 
 
 
   public float getAlignmentY()
   {
     return this.alignmentY;
   }
   
 
 
 
 
 
 
   public void setAlignmentY(float alignmentY)
   {
     this.alignmentY = (alignmentY < 0.0F ? 0.0F : alignmentY > 1.0F ? 1.0F : alignmentY);
   }
   
 
 
 
 
 
   public int getCurrentIconIndex()
   {
     return this.currentIconIndex;
   }
   
 
 
 
 
 
   public void setCurrentIconIndex(int index)
   {
     this.currentIconIndex = index;
     this.component.repaint(this.iconX, this.iconY, this.iconWidth, this.iconHeight);
   }
   
 
 
 
 
 
   public int getCycles()
   {
     return this.cycles;
   }
   
 
 
 
 
 
 
 
   public void setCycles(int cycles)
   {
     this.cycles = cycles;
   }
   
 
 
 
 
 
   public int getDelay()
   {
     return this.timer.getDelay();
   }
   
 
 
 
 
 
   public void setDelay(int delay)
   {
     this.timer.setDelay(delay);
   }
   
 
 
 
 
 
 
 
   public Icon getIcon(int index)
   {
     return (Icon)this.icons.get(index);
   }
   
 
 
 
 
 
   public int getIconCount()
   {
     return this.icons.size();
   }
   
 
 
 
 
 
   public boolean isShowFirstIcon()
   {
     return this.showFirstIcon;
   }
   
 
 
 
 
 
 
 
   public void setShowFirstIcon(boolean showFirstIcon)
   {
     this.showFirstIcon = showFirstIcon;
   }
   
 
 
 
 
   public void pause()
   {
     this.timer.stop();
   }
   
 
 
 
   public void start()
   {
     if (!this.timer.isRunning())
     {
       setCurrentIconIndex(0);
       this.animationFinished = false;
       this.cyclesCompleted = 0;
       this.timer.start();
     }
   }
   
 
 
 
 
   public void restart()
   {
     if (!this.timer.isRunning())
     {
       if (this.animationFinished) {
         start();
       } else {
         this.timer.restart();
       }
     }
   }
   
 
 
   public void stop()
   {
     this.timer.stop();
     setCurrentIconIndex(0);
     this.animationFinished = true;
   }
   
 
 
 
 
 
 
 
 
 
   public int getIconWidth()
   {
     return this.iconWidth;
   }
   
 
 
 
 
 
 
   public int getIconHeight()
   {
     return this.iconHeight;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
     if (c == this.component)
     {
       this.iconX = x;
       this.iconY = y;
     }
     
 
 
     Icon icon = (Icon)this.icons.get(this.currentIconIndex);
     int width = getIconWidth();
     int height = getIconHeight();
     
     int offsetX = getOffset(width, icon.getIconWidth(), this.alignmentX);
     int offsetY = getOffset(height, icon.getIconHeight(), this.alignmentY);
     
     icon.paintIcon(c, g, x + offsetX, y + offsetY);
   }
   
 
 
 
 
 
   private int getOffset(int maxValue, int iconValue, float alignment)
   {
     float offset = (maxValue - iconValue) * alignment;
     return Math.round(offset);
   }
   
 
 
 
 
 
 
 
 
   public void actionPerformed(ActionEvent e)
   {
     setCurrentIconIndex(getNextIconIndex(this.currentIconIndex, this.icons.size()));
     this.component.repaint(this.iconX, this.iconY, this.iconWidth, this.iconHeight);
     
 
 
     if (isCycleCompleted(this.currentIconIndex, this.icons.size()))
     {
       this.cyclesCompleted += 1;
     }
     
 
 
     if ((this.cycles > 0) && 
       (this.cycles <= this.cyclesCompleted))
     {
       this.timer.stop();
       this.animationFinished = true;
       
 
 
       if ((isShowFirstIcon()) && 
         (getCurrentIconIndex() != 0))
       {
         new Thread(this).start();
       }
     }
   }
   
 
 
 
 
 
   public void run()
   {
     try
     {
       Thread.sleep(this.timer.getDelay());
       setCurrentIconIndex(0);
     }
     catch (Exception localException) {}
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   protected int getNextIconIndex(int currentIndex, int iconCount)
   {
     currentIndex++;return currentIndex % iconCount;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   protected boolean isCycleCompleted(int currentIndex, int iconCount)
   {
     return currentIndex == iconCount - 1;
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\resources\images\AnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */