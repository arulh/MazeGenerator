package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisjointSetTest {
    DisjointSet ds;

    @BeforeEach
    void runBefore() {
        ds = new DisjointSet(10);
    }

    @Test
    void testConstructor() {
        ArrayList<Integer> lst = ds.getSets();

        assertEquals(10, lst.size());
        assertEquals(-1, lst.get(0));
        assertEquals(-1, lst.get(9));
    }

    @Test
    void testFind() {
        ArrayList<Integer> lst1 = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 5, -1, -1));
        ds.setList(lst1);
        assertEquals(5, ds.find(0));
        assertEquals(4, ds.find(4));
        assertEquals(5, ds.find(2));
        assertEquals(5, ds.find(3));
    }

    @Test
    void testUnion() {
        ds.union(1, 4);
        assertEquals(1, ds.find(4));

        ds.union(4, 3);
        assertEquals(1, ds.find(3));

        ArrayList<Integer> before = ds.getSets();
        ds.union(3, 4);
        ArrayList<Integer> after = ds.getSets();
        assertEquals(after, before);

        ds.union(5, 1);
        assertEquals(5, ds.find(1));

        ds.union(2, 0);
        ds.union(4, 0);
        ArrayList<Integer> lst = new ArrayList<>(Arrays.asList(2, 5, 5, 1, 1, -1, -1, -1, -1, -1));
        assertEquals(lst, ds.getSets());

    }
}
