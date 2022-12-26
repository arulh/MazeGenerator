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
    private JPanel player;

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
        player = drawCurrentPos();
        base.add(player, JLayeredPane.PALETTE_LAYER);

        windowSettings("MAZE", new Color(84, 118, 157));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }

    private void drawMaze() {
        for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x++) {
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
            Maze.Coord c = new Maze.Coord(pos.x-1, pos.y);
            if (validMovement(c)) movePlayer(c);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key");
            Maze.Coord c = new Maze.Coord(pos.x, pos.y+1);
            if (validMovement(c)) movePlayer(c);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key");
            Maze.Coord c = new Maze.Coord(pos.x+1, pos.y);
            if (validMovement(c)) movePlayer(c);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP key");
            Maze.Coord c = new Maze.Coord(pos.x, pos.y-1);
            if (validMovement(c)) movePlayer(c);
        }
    }

    private boolean validMovement(Maze.Coord c) {
        boolean inMaze = (c.x >= 0 && c.x < maze.width) && (c.y >= 0 && c.y < maze.height);
        if (!inMaze) return false;
        boolean openSpace = maze.getWallLayout()[c.y][c.x];
        return openSpace;
    }

    private void movePlayer(Maze.Coord c) {
        base.remove(player);
        base.moveToBack(player);

        pos = c;
        player = drawCurrentPos();
        base.add(player, JLayeredPane.PALETTE_LAYER);
    }

    private JPanel drawCurrentPos() {
        JPanel panel = new JPanel();
        ImageIcon player = new ImageIcon("data/icons/cat.png");
        Image img = player.getImage();
        Image scaledIMG = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        player = new ImageIcon(scaledIMG);
        JLabel label = new JLabel(player);
        panel.add(label);
        panel.setBounds(pos.x*RECT_WIDTH + BASE_POS, pos.y*RECT_HEIGHT + BASE_POS,
                RECT_WIDTH-AREA_OFFSET, RECT_HEIGHT-AREA_OFFSET);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        return panel;
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
