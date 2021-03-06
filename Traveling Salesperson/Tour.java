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

    private void insert(int index, Point p)
    {
        ListNode currNode = front;
        for(int i = 0; i < index; i++)
        {
            currNode = currNode.next;
        }
        ListNode newP = new ListNode(p, currNode.next);
        currNode.next = newP;
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
        int x = (int)front.data.getX();
        int y = (int)front.data.getY();
        int firstX = x;
        int firstY = y;
        ListNode currNode = front;
        int i = 0;
        while(currNode.next != null)
        {
            int oldX = x;
            int oldY = y;
            x = (int)currNode.next.data.getX();
            y = (int)currNode.next.data.getY();

            g.fillOval(oldX-2,oldY-2,5,5);
            g.drawLine(oldX, oldY, x, y);
            //g.drawString(String.format("" + i + " " + currNode.data), oldX + 10, oldY + 20);
            currNode = currNode.next;

            i++;
        }
        g.fillOval(x-2,y-2,5,5);
        g.drawLine(x,y,firstX,firstY);
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        ListNode currNode = front;
        double d = 0;
        while(currNode.next != null)
        {
            d += currNode.data.distance(currNode.next.data);
            currNode = currNode.next;
        }
        d += front.data.distance(currNode.data);
        return d;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   
        int index = 0;
        if(front == null)
        {
            front = new ListNode(p);
        }

        ListNode currNode = front;
        double smallestDistance = p.distance(front.data);
        int j = 0;
        while(currNode.next != null)
        {
            if((currNode.data.distance(p) < smallestDistance))
            {
                index = j;
                smallestDistance = currNode.data.distance(p);
            }
            currNode = currNode.next;
            j++;
        }
        insert(index,p);
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
        int smallestIndex = 0;
        if(front == null)
        {
            front = new ListNode(p);
            size++;
        }
        else
        {
            ListNode currNode = front;
            double smallestDistance = Double.POSITIVE_INFINITY;
            int index = 0;

            while(currNode.next != null)
            {
                double currDistance = currNode.data.distance(currNode.next.data);
                double potentialDistance = currNode.data.distance(p) + currNode.next.data.distance(p);

                if(potentialDistance - currDistance < smallestDistance)
                {
                    smallestIndex = index;
                    smallestDistance = potentialDistance - currDistance;
                }
                currNode = currNode.next;
                index++;
            }

        }
        insert(smallestIndex,p);
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
    }
}