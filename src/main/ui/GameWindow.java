package ui;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends Window implements ActionListener {

    private static final int MAZE_FRAME_WIDTH = 500;
    private static final int MAZE_FRAME_HEIGHT = 500;

    private final Maze maze;

    public GameWindow() {
//        this.maze = new Maze(4, 4, new Maze.Coord(0, 0), new Maze.Coord(3, 3));
        this.maze = new Maze(10, 10, new Maze.Coord(0, 0), new Maze.Coord(9, 9));
        maze.generate();
    }

    @Override
    public void launchWindow() {
        frame = new JFrame();
        frame.setLayout(null);

        drawMaze();

        windowSettings("MAZE", new Color(84, 118, 157));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void drawMaze() {
        JLayeredPane base = new JLayeredPane();
        base.setBounds(140, 190, MAZE_FRAME_WIDTH+20, MAZE_FRAME_HEIGHT+20);
        base.setOpaque(true);
        base.setBackground(Color.BLACK);

        for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x++) {
                int a = 11; // adjusts position of each rectangle to create border
                int sep = 2; // adjusts separation between each rectangle
                int rectWidth = MAZE_FRAME_WIDTH/maze.width;
                int rectHeight = MAZE_FRAME_HEIGHT/maze.height;

                if (maze.getWallLayout()[y][x]) {
                    base.add(drawRectangle(x*rectWidth + a, y*rectHeight + a,
                            rectWidth - sep, rectHeight - sep, Color.green), JLayeredPane.DEFAULT_LAYER);
                } else {
                    base.add(drawRectangle(x*rectWidth + a, y*rectHeight + a,
                            rectWidth - sep, rectHeight - sep, Color.GRAY), JLayeredPane.DEFAULT_LAYER );
                }
            }
        }
        frame.add(base);
    }

    private JPanel drawRectangle(int x, int y, int w, int h, Color c) {
        JPanel rect = new JPanel();
        rect.setBackground(c);
        rect.setBounds(x, y, w, h);

        return rect;
    }

    @Override
    protected void addMenuBar() {

    }
}
