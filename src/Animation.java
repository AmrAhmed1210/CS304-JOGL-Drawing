import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animation extends JFrame implements GLEventListener {
    boolean isAnimating = false; // this tells us if animation is running

    float moveX = 0;
    float moveY = 0;
    float angle = 0;
    float scaleX = 1.0f;
    float scaleY = 1.0f;

    public static void main(String[] args) {
        new Animation();
    }

    public Animation() {
        super("JOGL Animation");

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
        JButton animateBtn = new JButton("Start Animation");

        // ===== Panels to organize buttons ===== //
        JPanel topPanel = new JPanel();
        topPanel.add(rotateBtn);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(moveBtn);
        bottomPanel.add(zoomIn);
        bottomPanel.add(zoomOut);
        bottomPanel.add(animateBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Button actions ===== //
        moveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveX += 20;
                canvas.display();
            }
        });

        rotateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                angle += 15;
                canvas.display();
            }
        });

        zoomIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaleX += 0.1f;
                scaleY += 0.1f;
                canvas.display();
            }
        });

        zoomOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaleX -= 0.1f;
                scaleY -= 0.1f;
                if (scaleX < 0.1f) scaleX = scaleY = 0.1f;
                canvas.display();
            }
        });

        animateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isAnimating) { // if animation not started yet
                    isAnimating = true;
                    animateBtn.setText("Stop Animation"); // change button text

                    // start a new thread for animation
                    new Thread(new Runnable() {
                        public void run() {
                            float direction = 1;

                            while (isAnimating) { // while animation running
                                moveX += 5 * direction;    // move square right
                                angle += 5; // rotate square

                                if (moveX > 200 || moveX < -200) {
                                    direction *= -1;
                                }
                                try {
                                    Thread.sleep(100); // wait 0.1 second between frames
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                // update the drawing on screen
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        canvas.display();
                                    }
                                });
                            }
                        }
                    }).start();

                } else {
                    // stop animation
                    isAnimating = false;
                    animateBtn.setText("Start Animation");
                }
            }
        });

        // ===== Show the window ===== //
        setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.8f, 0.9f, 1f, 1f);

        // ===== Set coordinate system ===== //
        gl.glOrtho(0, 800, 0, 600, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // ===== Draw the square ===== //
        gl.glPushMatrix();
        gl.glTranslatef(moveX, moveY, 0); // Apply translation
        gl.glRotatef(angle, 0, 0, 1); // Apply rotation
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
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

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
