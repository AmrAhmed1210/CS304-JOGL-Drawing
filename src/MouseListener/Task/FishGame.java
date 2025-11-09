package MouseListener.Task;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FishGame extends JFrame implements GLEventListener, KeyListener {

    private GLCanvas canvas;
    private float fishX = 400;
    private float fishY = 300;
    private float fishSize = 50;

    private final List<float[]> smallFishList = new ArrayList<>();

    public FishGame() {
        super("Fish Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        add(canvas);

        setVisible(true);
        canvas.requestFocus();

        for (int i = 0; i < 5; i++) {
            float x = (float) (Math.random() * 700 + 50);
            float y = (float) (Math.random() * 400 + 150);
            smallFishList.add(new float[]{x, y});
        }
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.4f, 0.7f, 1f, 1f);
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glColor3f(0.2f, 0.8f, 0.3f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(800, 0);
        gl.glVertex2i(800, 150);
        gl.glVertex2i(0, 150);
        gl.glEnd();

        gl.glColor3f(0f, 0f, 1f);
        for (float[] fish : smallFishList) {
            drawCircle(gl, (int) fish[0], (int) fish[1], 20, 30);
        }

        gl.glColor3f(1f, 0.5f, 0f);
        drawCircle(gl, (int) fishX, (int) fishY, (int) fishSize, 40);

        ArrayList<float[]> eaten = new ArrayList<>();
        for (float[] fish : smallFishList) {
            double d = Math.sqrt(Math.pow(fishX - fish[0], 2) + Math.pow(fishY - fish[1], 2));
            if (d < fishSize + 20) {
                eaten.add(fish);
                fishSize += 5;
            }
        }
        smallFishList.removeAll(eaten);

    }

    private void drawCircle(GL gl, int cx, int cy, int r, int segments) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < segments; i++) {
            double theta = 2 * Math.PI * i / segments;
            gl.glVertex2d(cx + r * Math.cos(theta), cy + r * Math.sin(theta));
        }
        gl.glEnd();
    }


    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT -> fishX += 10;
            case KeyEvent.VK_LEFT -> fishX -= 10;
            case KeyEvent.VK_UP -> fishY += 10;
            case KeyEvent.VK_DOWN -> fishY -= 10;
        }
        canvas.display();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new FishGame();
    }
}
