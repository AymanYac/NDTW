 package ntdw.service;
 
 import java.io.PrintStream;
 
 public class DLL { public Node head;
   public boolean fetch = false;
   
   public String nextselected;
   public String prevselected;
   private String nextid;
   private String previd;
   public String lastcall;
   
   public void printvalues()
   {
     Node current = this.head;
     while (current.next != null) {
       System.out.println(current.id + " : " + current.data);
       current = current.next;
     }
   }
   
   public void sethead(String valueAt) {
     this.head = new Node(valueAt);
   }
   
   public String getprev(String selectedAID) {
     fetch(selectedAID);
     return this.prevselected;
   }
   
   private void fetch(String selectedAID) {
     Node currentNode = this.head;
     while (currentNode.next != null) {
       if (currentNode.id.equals(selectedAID)) {
         if (currentNode.prev != null) {
           this.prevselected = currentNode.prev.data;
           this.previd = currentNode.prev.id;
         } else {
           this.prevselected = "XXXXXXXXXX";
           this.previd = null;
         }
         if (currentNode.next.next != null) {
           this.nextselected = currentNode.next.data;
           this.nextid = currentNode.next.id;
           break; }
         this.nextselected = "XXXXXXXXXX";
         this.nextid = null;
         
         break;
       }
       currentNode = currentNode.next;
     }
   }
   
 
   public String getnext(String selectedAID)
   {
     fetch(selectedAID);
     return this.nextselected;
   }
   
   public String getprevID(String selectedAID) {
     this.lastcall = "PREV";
     fetch(selectedAID);
     return this.previd;
   }
   
   public String getnextID(String selectedAID) {
     this.lastcall = "NEXT";
     fetch(selectedAID);
     return this.nextid;
   }
   
   public void remove(String selectedAID) {}
 }


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\DLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */