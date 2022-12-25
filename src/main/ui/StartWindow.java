package ui;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends Window implements ActionListener {

    private final JButton easyMazeButton;
    private final JButton mediumMazeButton;
    private final JButton hardMazeButton;

    public StartWindow() {
        easyMazeButton = createButton("easy maze", 100, 200, this);
        mediumMazeButton = createButton("medium maze", 100, 300, this);
        hardMazeButton = createButton("hard maze", 100, 400, this);
    }

    @Override
    public void launchWindow() {
        frame = new JFrame();
        frame.setLayout(null);

        frame.add(easyMazeButton);
        frame.add(mediumMazeButton);
        frame.add(hardMazeButton);

        windowSettings("HOME", new Color(84, 118, 157));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyMazeButton) {
            System.out.println("Easy button clicked");

            Maze maze = new Maze(10, 10, new Maze.Coord(0, 0), new Maze.Coord(9, 9));

            GameWindow gw = new GameWindow(maze);
            gw.launchWindow();
            frame.dispose();
        } else if (e.getSource() == mediumMazeButton) {
            System.out.println("Medium button clicked");
        } else if (e.getSource() == hardMazeButton) {
            System.out.println("Hard button clicked");
        }
    }


    @Override
    protected void addMenuBar() {

    }
}
