package MouseListener.OOP;

import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class ListenWindow extends JFrame {
    private GLCanvas canvas;

    public ListenWindow() {
        super("Step 1 - Draw Ground and Circle");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create canvas and add listeners
        Draw draw = new Draw();
        canvas = new GLCanvas();

        // Pass the single Draw object and the canvas to the listeners
        mouseListener mouseListener = new mouseListener(draw, canvas);
        keyListener keyListener = new keyListener(draw, canvas);

        canvas.addGLEventListener(draw);
        canvas.addKeyListener(keyListener);
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);
        add(canvas);
        setVisible(true);

        // Important: request focus so keyboard works
        canvas.requestFocus();
    }
}