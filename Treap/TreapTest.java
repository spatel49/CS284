package classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreapTest <E> {
    @Test
    public void test() {
        Treap<Integer> t = new Treap<Integer>();

        //Adds new nodes to the treap from HW example
        assertEquals(t.add(4, 19), true);
        assertEquals(t.add(2, 31), true);
        assertEquals(t.add(6, 70), true);
        assertEquals(t.add(1, 84), true);
        assertEquals(t.add(3, 12), true);
        assertEquals(t.add(5, 83), true);
        assertEquals(t.add(7, 26), true);
        assertEquals(t.add(4, 19), false);

        //String Representation of Treap
        assertEquals(t.toString(), "(key=1, priority=84)\n" +
                "  null\n" +
                "  (key=5, priority=83)\n" +
                "    (key=2, priority=31)\n" +
                "      null\n" +
                "      (key=4, priority=19)\n" +
                "        (key=3, priority=12)\n" +
                "          null\n" +
                "          null\n" +
                "        null\n" +
                "    (key=6, priority=70)\n" +
                "      null\n" +
                "      (key=7, priority=26)\n" +
                "        null\n" +
                "        null");

        //Find Method
        assertEquals(t.find(4), true);
        assertEquals(t.find(25), false);

        //Delete Method
        assertEquals(t.delete(200), false);
        assertEquals(t.delete(4), true);
    }}