package MouseListener;

import MouseListener.OOP.mouseListener;

import javax.swing.*;
import java.awt.event.*;

public class MouseExample extends JFrame implements MouseListener {

    public MouseExample() {
        setTitle("Mouse Listener Example");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(this);

        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at X=" + e.getX() + ", Y=" + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered window");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited window");
    }

    public static void main(String[] args) {
        new MouseExample();
    }
}

