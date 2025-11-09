package Animation;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class AnimationRenderer implements GLEventListener {
    public float moveX = 0;
    public float moveY = 0;
    public float angle = 0;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClearColor(0.8f, 0.9f, 1f, 1f);
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glTranslatef(moveX, moveY, 0);
        gl.glScalef(scaleX, scaleY, 1f);

        gl.glColor3f(1f, 0.5f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(300, 200);
        gl.glVertex2i(500, 200);
        gl.glVertex2i(500, 400);
        gl.glVertex2i(300, 400);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glColor3f(0f, 0f, 1f);
        drawCircle(gl, (int) (400 + moveX), (int) (300 + moveY), 80, 50);
    }

    private void drawCircle(GL gl, int cx, int cy, int r, int segments) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < segments; i++) {
            double theta = 2 * Math.PI * i / segments;
            gl.glVertex2d(cx + r * Math.cos(theta), cy + r * Math.sin(theta));
        }
        gl.glEnd();
    }

    @Override public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}
    @Override public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
}
