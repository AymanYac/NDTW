package ntdw.service;

public class Node
{
    public String data;
    public String id;
    public Node prev;
    public Node next;
     
     
    // Constructor to create a new node
    // next and prev is by default initialized as null
    public Node(String d){data=d;} 
}