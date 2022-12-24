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
    private boolean[][] maze; // true if there is path

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

        maze[start.y][start.x] = true;
        maze[end.y][end.x] = true;
    }

    public void makeMaze() {
        Random r = new Random();
        while (ds.find(setIndex(start)) != ds.find(setIndex(end))) {
            int x = r.nextInt(width*height-1);
            Coord c = mazeCoord(x);
            maze[c.y][c.x] = true;

            for (Coord coord : getNeighbours(c)) {
                boolean inMaze = (coord.x >= 0 && coord.x < width) && (coord.y >= 0 && coord.y < height);
                if (!inMaze) continue;

                boolean explored = maze[coord.y][coord.x];

                if (explored) {
                    ds.union(setIndex(c), setIndex(coord));
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x]) {
                    System.out.print("0 ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.print("\n");
        }
    }

    private Coord[] getNeighbours(Coord c) {
        Coord[] l = new Coord[4];
        l[0] = new Coord(c.x-1, c.y); // left
        l[1] = new Coord(c.x, c.y-1); // down
        l[2] = new Coord(c.x+1, c.y); // right
        l[3] = new Coord(c.x, c.y+1); // up

        return l;
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
