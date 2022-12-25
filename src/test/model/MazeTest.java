package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    Maze maze1;
    Maze maze2;

    @BeforeEach
    void runBefore() {
        maze1 = new Maze(10, 10, new Maze.Coord(0, 0), new Maze.Coord(9, 9));
        maze2 = new Maze(4, 4, new Maze.Coord(0, 0), new Maze.Coord(3, 3));
    }

    @Test
    void testConstructor() {
        assertEquals(10, maze1.width);
        assertEquals(10, maze1.height);

        assertEquals(0, maze1.getStart().x);
        assertEquals(0, maze1.getStart().y);

        assertEquals(9, maze1.getEnd().x);
        assertEquals(9, maze1.getEnd().y);

        boolean[][] maze = maze1.getWallLayout();
        for (int x = 0; x < maze1.width; x++) {
            for (int y = 0; y < maze1.height; y++) {
                if (x == maze1.getStart().x || y == maze1.getStart().y) continue;
                if (x == maze1.getEnd().x || y == maze1.getEnd().y) continue;
                assertFalse(maze[y][x]);
            }
        }
        assertEquals(10, maze.length);
        assertEquals(10, maze[0].length);
    }

    @Test
    void testGenerate() {
        maze1.generate();
    }
}
