package MouseListener.OOP;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Draw implements GLEventListener {

    private float x = 400;
    private float y = 350;

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

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

        // Draw green ground
        gl.glColor3f(0.2f, 0.8f, 0.3f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(800, 0);
        gl.glVertex2i(800, 200);
        gl.glVertex2i(0, 200);
        gl.glEnd();

        // Draw red circle
        gl.glColor3f(1f, 0f, 0f);
        drawCircle(gl, (int) x, (int) y, 50, 40);
    }

    // Draw a circle
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
}