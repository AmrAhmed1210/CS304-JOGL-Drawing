import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class Draw extends JFrame implements GLEventListener {

    public static void main(String[] args) {
        new Draw();
    }

    public Draw() {
        super("JOGL Basic Shapes Example");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Create and add OpenGL canvas ===== //
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        add(canvas, BorderLayout.CENTER);

        // ===== Show the window ===== //
        setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // ===== Create GL instance ===== //
        GL gl = drawable.getGL();


        gl.glClearColor(0.8f, 0.9f, 1f, 1f);

        // ===== Set coordinate system ===== //
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // ===== Clear background ===== //
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // ===== square ===== //
        gl.glColor3f(1f, 0.5f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(300, 200);
        gl.glVertex2i(500, 200);
        gl.glVertex2i(500, 400);
        gl.glVertex2i(300, 400);
        gl.glEnd();

        // ===== circle ===== //
        gl.glColor3f(0f, 0f, 1f);
        drawCircle(gl, 400, 300, 80, 50);

        // ===== triangle ===== //
        gl.glColor3f(0f, 1f, 0f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(100, 100);
        gl.glVertex2i(200, 250);
        gl.glVertex2i(50, 250);
        gl.glEnd();

        // ===== rectangle ===== //
        gl.glColor3f(1f, 0f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(550, 100);
        gl.glVertex2i(700, 100);
        gl.glVertex2i(700, 200);
        gl.glVertex2i(550, 200);
        gl.glEnd();

        // ===== line ===== //
        gl.glColor3f(0f, 0f, 0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(50, 500);
        gl.glVertex2i(250, 500);
        gl.glEnd();

        // ===== pentagon ===== //
        gl.glColor3f(1f, 1f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(600, 400);
        gl.glVertex2i(650, 450);
        gl.glVertex2i(625, 500);
        gl.glVertex2i(575, 500);
        gl.glVertex2i(550, 450);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    // ===== Method draw a circle ===== //
    private void drawCircle(GL gl, int centerX, int centerY, int radius, int segments) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < segments; i++) {
            double theta = 2 * Math.PI * i / segments;
            double x = centerX + radius * Math.cos(theta);
            double y = centerY + radius * Math.sin(theta);
            gl.glVertex2d(x, y);
        }
        gl.glEnd();
    }
}
