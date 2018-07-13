/*    */ package ntdw.service;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class DLL { public Node head;
/*  6 */   public boolean fetch = false;
/*    */   
/*    */   public String nextselected;
/*    */   public String prevselected;
/*    */   private String nextid;
/*    */   private String previd;
/*    */   public String lastcall;
/*    */   
/*    */   public void printvalues()
/*    */   {
/* 16 */     Node current = this.head;
/* 17 */     while (current.next != null) {
/* 18 */       System.out.println(current.id + " : " + current.data);
/* 19 */       current = current.next;
/*    */     }
/*    */   }
/*    */   
/*    */   public void sethead(String valueAt) {
/* 24 */     this.head = new Node(valueAt);
/*    */   }
/*    */   
/*    */   public String getprev(String selectedAID) {
/* 28 */     fetch(selectedAID);
/* 29 */     return this.prevselected;
/*    */   }
/*    */   
/*    */   private void fetch(String selectedAID) {
/* 33 */     Node currentNode = this.head;
/* 34 */     while (currentNode.next != null) {
/* 35 */       if (currentNode.id.equals(selectedAID)) {
/* 36 */         if (currentNode.prev != null) {
/* 37 */           this.prevselected = currentNode.prev.data;
/* 38 */           this.previd = currentNode.prev.id;
/*    */         } else {
/* 40 */           this.prevselected = "XXXXXXXXXX";
/* 41 */           this.previd = null;
/*    */         }
/* 43 */         if (currentNode.next.next != null) {
/* 44 */           this.nextselected = currentNode.next.data;
/* 45 */           this.nextid = currentNode.next.id;
/* 46 */           break; }
/* 47 */         this.nextselected = "XXXXXXXXXX";
/* 48 */         this.nextid = null;
/*    */         
/* 50 */         break;
/*    */       }
/* 52 */       currentNode = currentNode.next;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public String getnext(String selectedAID)
/*    */   {
/* 59 */     fetch(selectedAID);
/* 60 */     return this.nextselected;
/*    */   }
/*    */   
/*    */   public String getprevID(String selectedAID) {
/* 64 */     this.lastcall = "PREV";
/* 65 */     fetch(selectedAID);
/* 66 */     return this.previd;
/*    */   }
/*    */   
/*    */   public String getnextID(String selectedAID) {
/* 70 */     this.lastcall = "NEXT";
/* 71 */     fetch(selectedAID);
/* 72 */     return this.nextid;
/*    */   }
/*    */   
/*    */   public void remove(String selectedAID) {}
/*    */ }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\DLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */