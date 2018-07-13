/*    */ package ntdw.service;
/*    */ 
/*    */ import java.time.Clock;
/*    */ import java.time.Instant;
/*    */ import java.time.ZoneId;
/*    */ 
/*    */ 
/*    */ public class NanoClock
/*    */   extends Clock
/*    */ {
/*    */   private final Clock clock;
/*    */   private final long initialNanos;
/*    */   private final Instant initialInstant;
/*    */   
/*    */   public NanoClock()
/*    */   {
/* 17 */     this(Clock.systemUTC());
/*    */   }
/*    */   
/*    */   public NanoClock(Clock clock)
/*    */   {
/* 22 */     this.clock = clock;
/* 23 */     this.initialInstant = clock.instant();
/* 24 */     this.initialNanos = getSystemNanos();
/*    */   }
/*    */   
/*    */ 
/*    */   public ZoneId getZone()
/*    */   {
/* 30 */     return this.clock.getZone();
/*    */   }
/*    */   
/*    */ 
/*    */   public Instant instant()
/*    */   {
/* 36 */     return this.initialInstant.plusNanos(getSystemNanos() - this.initialNanos);
/*    */   }
/*    */   
/*    */ 
/*    */   public Clock withZone(ZoneId zone)
/*    */   {
/* 42 */     return new NanoClock(this.clock.withZone(zone));
/*    */   }
/*    */   
/*    */   private long getSystemNanos()
/*    */   {
/* 47 */     return System.nanoTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\NanoClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */