package ntdw.service;

public class DLL 
{
    public Node head; // head of list
    public boolean fetch=false;
	public String nextselected;
	public String prevselected;
	private String nextid;
	private String previd;
	public String lastcall;
    

	public void printvalues() {
		// TODO Auto-generated method stub
		Node current = head;
		while(current.next!=null) {
			System.out.println(current.id+" : "+current.data);
			current = current.next;
		}
	}

	public void sethead(String valueAt) {
		this.head=new Node(valueAt);
	}

	public String getprev(String selectedAID) {
		fetch(selectedAID);
		return this.prevselected;
	}

	private void fetch(String selectedAID) {
			Node currentNode = this.head;
        	while(currentNode.next!=null) {
        		if(currentNode.id.equals(selectedAID)) {
        			if(currentNode.prev!=null) {
        				this.prevselected=currentNode.prev.data;
        				this.previd=currentNode.prev.id;
        			}else {
        				this.prevselected= "XXXXXXXXXX";
        				this.previd=null;
        			}
        			if(currentNode.next.next!=null) {
        				this.nextselected=currentNode.next.data;
        				this.nextid=currentNode.next.id;
        			}else {
        				this.nextselected= "XXXXXXXXXX";
        				this.nextid=null;
        			}
        			break;
        		}else {
        			currentNode=currentNode.next;
        		}
        	}
		}
		

	public String getnext(String selectedAID) {
		fetch(selectedAID);
		return this.nextselected;
	}

	public String getprevID(String selectedAID) {
		this.lastcall="PREV";
		fetch(selectedAID);
		return this.previd;
	}

	public String getnextID(String selectedAID) {
		this.lastcall="NEXT";
		fetch(selectedAID);
		return this.nextid;
	}

	public void remove(String selectedAID) {
		return;
		/*Node currentNode = this.head;
    	while(currentNode.next!=null) {
    		if(currentNode.id.equals(selectedAID)) {
    			if(currentNode.prev!=null) {
    				if(currentNode.next!=null) {
    					currentNode.prev.next=currentNode.next;
    					currentNode.next.prev=currentNode.prev;
    				}else {
    					currentNode.prev.next=null;
    				}
    			}else {
    				if(currentNode.next!=null) {
    					currentNode.next.prev=this.head;
    					this.head.next=currentNode.next;
    				}else {
    					this.head.next=null;
    				}
    				
    			}
    			break;
    		}else {
    			currentNode=currentNode.next;
    		}
    	}*/
	}
}