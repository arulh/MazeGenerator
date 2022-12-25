package ui;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends Window implements ActionListener, KeyListener {

    private static final int MAZE_FRAME_WIDTH = 500;
    private static final int MAZE_FRAME_HEIGHT = 500;

    private final int RECT_WIDTH;
    private final int RECT_HEIGHT;
    private final int BASE_POS = 11; // adjusts position of each rectangle to create border
    private final int AREA_OFFSET = 2; // adjusts separation between each rectangle

    private final Maze maze;
    private JLayeredPane base; // panel for maze objects
    private Maze.Coord pos;

    public GameWindow(Maze m) {
        this.maze = m;
        maze.generate();
        this.pos = maze.getStart();

        this.RECT_WIDTH = MAZE_FRAME_WIDTH/maze.width;
        this.RECT_HEIGHT = MAZE_FRAME_HEIGHT/maze.height;

        base = new JLayeredPane();
        base.setBounds(140, 190, MAZE_FRAME_WIDTH+20, MAZE_FRAME_HEIGHT+20);
        base.setOpaque(true);
        base.setBackground(Color.BLACK);
    }

    @Override
    public void launchWindow() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.addKeyListener(this);

        drawMaze();
        drawCurrentPos(maze.getStart().x*RECT_WIDTH + BASE_POS,
                maze.getStart().y*RECT_HEIGHT + BASE_POS);

        windowSettings("MAZE", new Color(84, 118, 157));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }

    private void drawMaze() {
        for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x++) {
                int a = 11; // adjusts position of each rectangle to create border
                int sep = 2; // adjusts separation between each rectangle

                if (maze.getStart().x == x && maze.getStart().y == y) {
                    base.add(drawRectangle(x*RECT_WIDTH + BASE_POS, y*RECT_HEIGHT + BASE_POS, Color.green),
                            JLayeredPane.DEFAULT_LAYER);
                    continue;
                } else if (maze.getEnd().x == x && maze.getEnd().y == y) {
                    base.add(drawRectangle(x*RECT_WIDTH + BASE_POS, y*RECT_HEIGHT + BASE_POS, Color.RED),
                            JLayeredPane.DEFAULT_LAYER);
                    continue;
                }

                if (maze.getWallLayout()[y][x]) {
                    base.add(drawRectangle(x*RECT_WIDTH + BASE_POS, y*RECT_HEIGHT + BASE_POS, new Color(169, 211, 167)),
                            JLayeredPane.DEFAULT_LAYER);
                } else {
                    base.add(drawRectangle(x*RECT_WIDTH + BASE_POS, y*RECT_HEIGHT + BASE_POS, Color.DARK_GRAY),
                            JLayeredPane.DEFAULT_LAYER );
                }
            }
        }
        frame.add(base);
    }

    private JPanel drawRectangle(int x, int y, Color c) {
        JPanel rect = new JPanel();
        rect.setBackground(c);
        rect.setBounds(x, y, RECT_WIDTH-AREA_OFFSET, RECT_HEIGHT-AREA_OFFSET);

        return rect;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP key");
        } else {
            System.out.println(e.getKeyChar());
        }
    }

    private void drawCurrentPos(int x, int y) {
        JPanel panel = new JPanel();
        ImageIcon player = new ImageIcon("data/icons/cat.png");
        Image img = player.getImage();
        Image scaledIMG = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        player = new ImageIcon(scaledIMG);
        JLabel label = new JLabel(player);
        panel.add(label);
        panel.setBounds(x, y, RECT_WIDTH-AREA_OFFSET, RECT_HEIGHT-AREA_OFFSET);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        base.add(panel, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    protected void addMenuBar() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // empty
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // empty
    }
}
