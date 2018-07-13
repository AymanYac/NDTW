/*     */ package ntdw.resources.images;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.Timer;
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
/*     */ public class AnimatedIcon
/*     */   implements Icon, ActionListener, Runnable
/*     */ {
/*     */   private static final int DEFAULT_DELAY = 500;
/*     */   private static final int DEFAULT_CYCLES = -1;
/*     */   public static final float TOP = 0.0F;
/*     */   public static final float LEFT = 0.0F;
/*     */   public static final float CENTER = 0.5F;
/*     */   public static final float BOTTOM = 1.0F;
/*     */   public static final float RIGHT = 1.0F;
/*     */   private JComponent component;
/*  44 */   private List<Icon> icons = new ArrayList();
/*     */   
/*     */   private int cycles;
/*  47 */   private boolean showFirstIcon = false;
/*     */   
/*  49 */   private float alignmentX = 0.5F;
/*  50 */   private float alignmentY = 0.5F;
/*     */   
/*     */ 
/*     */   private int iconX;
/*     */   
/*     */ 
/*     */   private int iconY;
/*     */   
/*     */ 
/*     */   private int iconWidth;
/*     */   
/*     */   private int iconHeight;
/*     */   
/*     */   private int currentIconIndex;
/*     */   
/*     */   private int cyclesCompleted;
/*     */   
/*  67 */   private boolean animationFinished = true;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Timer timer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnimatedIcon(JComponent component, Icon... icons)
/*     */   {
/*  79 */     this(component, 500, icons);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnimatedIcon(JComponent component, int delay, Icon... icons)
/*     */   {
/*  91 */     this(component, delay, -1, icons);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnimatedIcon(JComponent component, int delay, int cycles, Icon... icons)
/*     */   {
/* 104 */     this.component = component;
/* 105 */     setCycles(cycles);
/*     */     
/* 107 */     for (int i = 0; i < icons.length; i++)
/*     */     {
/* 109 */       if (icons[i] == null)
/*     */       {
/* 111 */         String message = "Icon (" + i + ") cannot be null";
/* 112 */         throw new IllegalArgumentException(message);
/*     */       }
/*     */       
/*     */ 
/* 116 */       addIcon(new Icon[] { icons[i] });
/*     */     }
/*     */     
/*     */ 
/* 120 */     this.timer = new Timer(delay, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addIcon(Icon... icons)
/*     */   {
/*     */     Icon[] arrayOfIcon;
/*     */     
/*     */ 
/* 130 */     int j = (arrayOfIcon = icons).length; for (int i = 0; i < j; i++) { Icon icon = arrayOfIcon[i];
/*     */       
/* 132 */       if (icon != null)
/*     */       {
/* 134 */         this.icons.add(icon);
/* 135 */         calculateIconDimensions();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void calculateIconDimensions()
/*     */   {
/* 146 */     this.iconWidth = 0;
/* 147 */     this.iconHeight = 0;
/*     */     
/* 149 */     for (Icon icon : this.icons)
/*     */     {
/* 151 */       this.iconWidth = Math.max(this.iconWidth, icon.getIconWidth());
/* 152 */       this.iconHeight = Math.max(this.iconHeight, icon.getIconHeight());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getAlignmentX()
/*     */   {
/* 163 */     return this.alignmentX;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlignmentX(float alignmentX)
/*     */   {
/* 174 */     this.alignmentX = (alignmentX < 0.0F ? 0.0F : alignmentX > 1.0F ? 1.0F : alignmentX);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getAlignmentY()
/*     */   {
/* 184 */     return this.alignmentY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAlignmentY(float alignmentY)
/*     */   {
/* 195 */     this.alignmentY = (alignmentY < 0.0F ? 0.0F : alignmentY > 1.0F ? 1.0F : alignmentY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCurrentIconIndex()
/*     */   {
/* 205 */     return this.currentIconIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCurrentIconIndex(int index)
/*     */   {
/* 215 */     this.currentIconIndex = index;
/* 216 */     this.component.repaint(this.iconX, this.iconY, this.iconWidth, this.iconHeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCycles()
/*     */   {
/* 226 */     return this.cycles;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCycles(int cycles)
/*     */   {
/* 238 */     this.cycles = cycles;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDelay()
/*     */   {
/* 248 */     return this.timer.getDelay();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDelay(int delay)
/*     */   {
/* 258 */     this.timer.setDelay(delay);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Icon getIcon(int index)
/*     */   {
/* 270 */     return (Icon)this.icons.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIconCount()
/*     */   {
/* 280 */     return this.icons.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShowFirstIcon()
/*     */   {
/* 290 */     return this.showFirstIcon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShowFirstIcon(boolean showFirstIcon)
/*     */   {
/* 302 */     this.showFirstIcon = showFirstIcon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pause()
/*     */   {
/* 311 */     this.timer.stop();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void start()
/*     */   {
/* 319 */     if (!this.timer.isRunning())
/*     */     {
/* 321 */       setCurrentIconIndex(0);
/* 322 */       this.animationFinished = false;
/* 323 */       this.cyclesCompleted = 0;
/* 324 */       this.timer.start();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void restart()
/*     */   {
/* 334 */     if (!this.timer.isRunning())
/*     */     {
/* 336 */       if (this.animationFinished) {
/* 337 */         start();
/*     */       } else {
/* 339 */         this.timer.restart();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void stop()
/*     */   {
/* 348 */     this.timer.stop();
/* 349 */     setCurrentIconIndex(0);
/* 350 */     this.animationFinished = true;
/*     */   }
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
/*     */   public int getIconWidth()
/*     */   {
/* 364 */     return this.iconWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIconHeight()
/*     */   {
/* 375 */     return this.iconHeight;
/*     */   }
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
/*     */   public void paintIcon(Component c, Graphics g, int x, int y)
/*     */   {
/* 392 */     if (c == this.component)
/*     */     {
/* 394 */       this.iconX = x;
/* 395 */       this.iconY = y;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 400 */     Icon icon = (Icon)this.icons.get(this.currentIconIndex);
/* 401 */     int width = getIconWidth();
/* 402 */     int height = getIconHeight();
/*     */     
/* 404 */     int offsetX = getOffset(width, icon.getIconWidth(), this.alignmentX);
/* 405 */     int offsetY = getOffset(height, icon.getIconHeight(), this.alignmentY);
/*     */     
/* 407 */     icon.paintIcon(c, g, x + offsetX, y + offsetY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private int getOffset(int maxValue, int iconValue, float alignment)
/*     */   {
/* 417 */     float offset = (maxValue - iconValue) * alignment;
/* 418 */     return Math.round(offset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 431 */     setCurrentIconIndex(getNextIconIndex(this.currentIconIndex, this.icons.size()));
/* 432 */     this.component.repaint(this.iconX, this.iconY, this.iconWidth, this.iconHeight);
/*     */     
/*     */ 
/*     */ 
/* 436 */     if (isCycleCompleted(this.currentIconIndex, this.icons.size()))
/*     */     {
/* 438 */       this.cyclesCompleted += 1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 443 */     if ((this.cycles > 0) && 
/* 444 */       (this.cycles <= this.cyclesCompleted))
/*     */     {
/* 446 */       this.timer.stop();
/* 447 */       this.animationFinished = true;
/*     */       
/*     */ 
/*     */ 
/* 451 */       if ((isShowFirstIcon()) && 
/* 452 */         (getCurrentIconIndex() != 0))
/*     */       {
/* 454 */         new Thread(this).start();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/* 468 */       Thread.sleep(this.timer.getDelay());
/* 469 */       setCurrentIconIndex(0);
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
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
/*     */   protected int getNextIconIndex(int currentIndex, int iconCount)
/*     */   {
/* 490 */     currentIndex++;return currentIndex % iconCount;
/*     */   }
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
/*     */   protected boolean isCycleCompleted(int currentIndex, int iconCount)
/*     */   {
/* 510 */     return currentIndex == iconCount - 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\resources\images\AnimatedIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */