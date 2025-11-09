package MouseListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.event.*;

public class Step1_Draw extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

    // Coordinates for the circle center
    private float x = 400;
    private float y = 350;

    private GLCanvas canvas; // Declare it here (not inside the constructor)

    public Step1_Draw() {
        super("Step 1 - Draw Ground and Circle");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // === Create canvas and add listeners ===
        canvas = new GLCanvas(); // fixed: removed "GLCanvas" redeclaration
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        add(canvas);
        setVisible(true);

        // Important: request focus so keyboard works
        canvas.requestFocus();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Set background color (sky blue)
        gl.glClearColor(0.6f, 0.9f, 1f, 1f);

        // Define coordinate system (left=0, right=800, bottom=0, top=600)
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the screen
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // === Draw green ground ===
        gl.glColor3f(0.2f, 0.8f, 0.3f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(800, 0);
        gl.glVertex2i(800, 200);
        gl.glVertex2i(0, 200);
        gl.glEnd();

        // === Draw red circle ===
        gl.glColor3f(1f, 0f, 0f);
        drawCircle(gl, (int) x, (int) y, 50, 40);
    }

    // === Draw a circle ===
    private void drawCircle(GL gl, int cx, int cy, int r, int segments) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < segments; i++) {
            double theta = 2 * Math.PI * i / segments;
            gl.glVertex2d(cx + r * Math.cos(theta), cy + r * Math.sin(theta));
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    // === Keyboard listener ===
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Get which key was pressed
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT -> x += 10; // move right
            case KeyEvent.VK_LEFT -> x -= 10;  // move left
            case KeyEvent.VK_UP -> y += 10;    // move up
            case KeyEvent.VK_DOWN -> y -= 10;  // move down
        }

        // Redraw after moving
        canvas.display();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    // ===== Mouse =====
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = 600 - e.getY();
        canvas.display();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = 600 - e.getY();
        canvas.display();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // === Main ===
    public static void main(String[] args) {
        new Step1_Draw();
    }
}
