import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class Moving extends JFrame implements GLEventListener {

    float moveX = 0;
    float moveY = 0;
    float angle = 0;
    float scaleX = 1.0f;
    float scaleY = 1.0f;

    public static void main(String[] args) {
        new Moving();
    }

    public Moving() {
        super("JOGL - Move, Rotate & Scale Example");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Create and add OpenGL canvas ===== //
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        add(canvas, BorderLayout.CENTER);

        // ===== Buttons section ===== //
        JButton moveBtn = new JButton("Move Right");
        JButton rotateBtn = new JButton("Rotate");
        JButton zoomIn = new JButton("Zoom In");
        JButton zoomOut = new JButton("Zoom Out");

        // ===== Panels to organize buttons ===== //
        JPanel topPanel = new JPanel();
        topPanel.add(rotateBtn);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(moveBtn);
        bottomPanel.add(zoomIn);
        bottomPanel.add(zoomOut);
        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Button actions ===== //
        moveBtn.addActionListener(e -> {
            moveX += 20;
            canvas.display();
        });

        rotateBtn.addActionListener(e -> {
            angle += 15;
            canvas.display();
        });

        zoomIn.addActionListener(e -> {
            scaleX += 0.1f;
            scaleY += 0.1f;
            canvas.display();
        });

        zoomOut.addActionListener(e -> {
            scaleX -= 0.1f;
            scaleY -= 0.1f;
            if (scaleX < 0.1f) scaleX = scaleY = 0.1f;
            canvas.display();
        });

        // ===== Show the window ===== //
        setVisible(true);
    }

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

        // ===== Draw the square ===== //
        gl.glPushMatrix();
        gl.glTranslatef(moveX, moveY, 0); // Apply translation
        gl.glRotatef(angle, 0, 0, 1);// Apply rotation
        gl.glScalef(scaleX, scaleY, 1f);  // Apply scaling

        gl.glColor3f(1f, 0.5f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(300, 200);
        gl.glVertex2i(500, 200);
        gl.glVertex2i(500, 400);
        gl.glVertex2i(300, 400);
        gl.glEnd();
        gl.glPopMatrix();

        // ===== Draw the circle inside the square ===== //
        gl.glColor3f(0f, 0f, 1f); // Blue
        drawCircle(gl, (int) (400 + moveX), (int) (300 + moveY), 80, 50);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

    // =====  method draw a circle ===== //
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
