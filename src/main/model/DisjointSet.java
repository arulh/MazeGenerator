package model;

import java.util.ArrayList;
import java.util.Random;

// Disjoint set data structure implemented using ArrayList
// Representative of set has value -1
public class DisjointSet  {
    private ArrayList<Integer> sets;

    public DisjointSet(int size) {
        this.sets = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            sets.add(-1);
        }
    }

    // returns the index of the set representative
    public int find(int n) {
        if (sets.get(n) == -1) return n;
        else {
            return find(sets.get(n));
        }
    }


    public void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) return;

        sets.set(y, x);
    }

    public ArrayList<Integer> getSets() {
        return sets;
    }

    // for testing purposes
    public void setList(ArrayList<Integer> l) {
        sets = l;
    }
}
