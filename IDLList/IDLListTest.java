package classes;

import static org.junit.Assert.*;
import org.junit.Test;

public class IDLListTest <E>{
    @Test
    public void test() {
        IDLList<Integer> l = new IDLList<Integer>();
        
        //adds 3 to front
        assertEquals(l.add(3), true);
        assertEquals(l.toString(), "null<-3->null");
        //adds 1 to front
        assertEquals(l.add(1), true);
        assertEquals(l.toString(), "null<-1-><-3->null");

        //adds 2 at index 1
        assertEquals(l.add(1, 2), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3->null");

        //appends 4
        assertEquals(l.append(4), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");

        //gets head and tests get method
        assertEquals(l.getHead(), l.get(0));

        //gets tail and tests get method
        assertEquals(l.getLast(), l.get(3));

        //check size of list
        assertEquals(l.size(), 4);

        //removes element at front of list then checks size
        l.remove();
        assertEquals(l.toString(), "null<-2-><-3-><-4->null");

        //adds 1 back to list
        assertEquals(l.add(1), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");

        //removes the last element of the list
        l.removeLast();
        assertEquals(l.toString(), "null<-1-><-2-><-3->null");

        //appends 4 back to the list
        assertEquals(l.append(4), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");

        //removes index 2
        l.removeAt(2);
        assertEquals(l.toString(), "null<-1-><-2-><-4->null");

        //adds 3 back at index 2
        assertEquals(l.add(2, 3), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");

        //appends 1 to the end of the list
        assertEquals(l.append(1), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4-><-1->null");

        //removes the first occurrence of 1 in the list
        assertEquals(l.remove(1), true);
        assertEquals(l.toString(), "null<-2-><-3-><-4-><-1->null");

        //removes the last element
        l.removeLast();
        assertEquals(l.toString(), "null<-2-><-3-><-4->null");

        //adds 1 back to the front of the list
        assertEquals(l.add(1), true);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");

        //tries to remove a 5 in the list
        assertEquals(l.remove(5), false);
        assertEquals(l.toString(), "null<-1-><-2-><-3-><-4->null");
    }
}