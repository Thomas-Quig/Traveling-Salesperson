import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author
 * @version
 */

public class Tour implements TourInterface
{
    // instance variables
    int size;
    ListNode front;
    ListNode back;

    // constructor
    public Tour()
    {
        size = 0;
        front = null;
        back = null;
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return size;
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        ListNode end = new ListNode(p);
        if(size == 0)
        {
            front = end;
        }
        else
        {
            back.next = end;
            back = back.next;
        }
        back = end;
        size++;
    } 

    // print every node in the list 
    public void print()
    {   
        ListNode currNode = front;
        while(currNode.next != null)
        {
            System.out.println(currNode.data);
            currNode = currNode.next;
        }
        System.out.println(currNode.data);
        System.out.println("done");
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        return -1;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
    }

    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }        

        public boolean hasNext()
        {
            return this.next != null;
        }
    }

}