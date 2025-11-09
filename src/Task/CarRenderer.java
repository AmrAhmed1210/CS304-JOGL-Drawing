package Task;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class CarRenderer implements GLEventListener {
    public float moveX = 0;
    public float moveY = 0;
    public float angle = 0;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.6f, 0.9f, 1f, 1f);
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // ===== Draw the ground =====
        gl.glColor3f(0.2f, 0.8f, 0.3f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(800, 0);
        gl.glVertex2i(800, 200);
        gl.glVertex2i(0, 200);
        gl.glEnd();

        // ===== Draw the car =====
        gl.glPushMatrix();
        gl.glTranslatef(moveX, moveY, 0);
        gl.glScalef(scaleX, scaleY, 1f);
//        gl.glTranslatef(300, 250, 0);
//        gl.glRotatef(angle, 0, 0, 1);
//        gl.glTranslatef(-300, -250, 0);


        gl.glColor3f(1f, 0.2f, 0.1f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(100, 250);
        gl.glVertex2i(500, 250);
        gl.glVertex2i(500, 350);
        gl.glVertex2i(100, 350);
        gl.glEnd();


        gl.glColor3f(1f, 0.6f, 0.2f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(180, 350);
        gl.glVertex2i(420, 350);
        gl.glVertex2i(380, 420);
        gl.glVertex2i(220, 420);
        gl.glEnd();


        gl.glColor3f(0.1f, 0.1f, 0.1f);
        drawCircle(gl, (int) (180), (int) (220), 35, 40);


        gl.glColor3f(0.1f, 0.1f, 0.1f);
        drawCircle(gl, (int) (420), (int) (220), 35, 40);

        gl.glPopMatrix();
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
}
