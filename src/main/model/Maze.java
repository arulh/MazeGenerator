package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Maze {

    public static class Coord {
        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private DisjointSet ds;
    public int width;
    public int height;
    private final Coord start;
    private final Coord end;
    private boolean[][] maze;

    public Maze(int w, int h, Coord s, Coord e) {
        this.ds = new DisjointSet(w*h);
        this.width = w;
        this.height = h;
        this.start = s;
        this.end = e;
        maze = new boolean[h][w];

        for (boolean[] l : maze) {
            Arrays.fill(l, false);
        }
    }

    private void makeMaze() {
        Random r = new Random();
        while (ds.find(setIndex(start)) != ds.find(setIndex(end))) {
            int i = r.nextInt(width*height);
            // get neighbours of random coordinate
        }
    }

    private Coord mazeCoord(int n) {
        return new Coord(n%width, n/width);
    }

    private int setIndex(Coord c) {
        return width*c.y + c.x;
    }

    public boolean[][] getMaze() {
        return maze;
    }

    public Coord getStart() {
        return start;
    }

    public Coord getEnd() {
        return end;
    }

}
