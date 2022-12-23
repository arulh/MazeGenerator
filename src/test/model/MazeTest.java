package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MazeTest {

    Maze maze1;

    @BeforeEach
    void runBefore() {
        maze1 = new Maze(10, 10, new Maze.Coord(0, 0), new Maze.Coord(9, 9));
    }

    @Test
    void testConstructor() {
        assertEquals(10, maze1.width);
        assertEquals(10, maze1.height);

        assertEquals(0, maze1.getStart().x);
        assertEquals(0, maze1.getStart().y);

        assertEquals(9, maze1.getEnd().x);
        assertEquals(9, maze1.getEnd().y);

        boolean[][] maze = maze1.getMaze();
        for (int x = 0; x < maze1.width; x++) {
            for (int y = 0; y < maze1.height; y++) {
                assertFalse(maze[y][x]);
            }
        }
        assertEquals(10, maze.length);
        assertEquals(10, maze[0].length);
    }
}
