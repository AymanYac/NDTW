 package ntdw.service;
 
 import java.time.Clock;
 import java.time.Instant;
 import java.time.ZoneId;
 
 
 public class NanoClock
   extends Clock
 {
   private final Clock clock;
   private final long initialNanos;
   private final Instant initialInstant;
   
   public NanoClock()
   {
     this(Clock.systemUTC());
   }
   
   public NanoClock(Clock clock)
   {
     this.clock = clock;
     this.initialInstant = clock.instant();
     this.initialNanos = getSystemNanos();
   }
   
 
   public ZoneId getZone()
   {
     return this.clock.getZone();
   }
   
 
   public Instant instant()
   {
     return this.initialInstant.plusNanos(getSystemNanos() - this.initialNanos);
   }
   
 
   public Clock withZone(ZoneId zone)
   {
     return new NanoClock(this.clock.withZone(zone));
   }
   
   private long getSystemNanos()
   {
     return System.nanoTime();
   }
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\NanoClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */