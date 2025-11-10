package MouseListener.Quizs;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class quiz1 extends JFrame implements GLEventListener, KeyListener {
    float moveX = 0;
    float moveY = 0;
    float dx = 5;
    float dy = 0;

    boolean isAnimating = true;
    private GLCanvas canvas;

    public quiz1() {
        super("Quiz1");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        add(canvas);

        new Thread(() -> {
            float direction = 1;
            while (isAnimating) {
                moveX += dx * direction;
                moveY += dy * direction;

                if ((moveX > 300 || moveX < -300) || (moveY > 200 || moveY < -200)) direction *= -1;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }

                SwingUtilities.invokeLater(canvas::display);
            }
        }).start();

        setVisible(true);

        canvas.requestFocus();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.6f, 0.9f, 1f, 1f);

        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glTranslatef(moveX, moveY, 0);

        gl.glColor3f(1f, 0.5f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(300, 200);
        gl.glVertex2i(500, 200);
        gl.glVertex2i(500, 400);
        gl.glVertex2i(300, 400);
        gl.glEnd();
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT -> {
                dx = 5;
                dy = 0;
            }
            case KeyEvent.VK_LEFT -> {
                dx = -5;
                dy = 0;
            }
            case KeyEvent.VK_UP -> {
                dx = 0;
                dy = 5;
            }
            case KeyEvent.VK_DOWN -> {
                dx = 0;
                dy = -5;
            }
        }
        canvas.display();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new quiz1();
    }
}