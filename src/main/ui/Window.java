package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Window extends JFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 800;

    protected JFrame frame;

    public abstract void launchWindow();

    protected abstract void addMenuBar();

    protected void windowSettings(String text, Color color) {
        frame.setTitle(text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
        frame.getContentPane().setBackground(color);
    }

    protected JButton createButton(String text, int x, int y, ActionListener al) {
        JButton button = new JButton();
        button.setBounds(x, y, 100, 50);
        button.addActionListener(al);
        button.setText(text);
        return button;
    }
}
