package Task;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class CarWindow extends JFrame {
    private boolean isAnimating = true;
    private CarRenderer renderer;

    public CarWindow() {
        super("JOGL Car Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // GLCanvas + Renderer
        GLCanvas canvas = new GLCanvas();
        renderer = new CarRenderer();
        canvas.addGLEventListener(renderer);
        add(canvas, BorderLayout.CENTER);

        // ===== Buttons =====
        JButton moveBtn = new JButton("Move Right");
        JButton rotateBtn = new JButton("Rotate");
        JButton zoomIn = new JButton("Zoom In");
        JButton zoomOut = new JButton("Zoom Out");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(moveBtn);
        bottomPanel.add(rotateBtn);
        bottomPanel.add(zoomIn);
        bottomPanel.add(zoomOut);
        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Button actions =====
        moveBtn.addActionListener(e -> {
            renderer.moveX += 20;
            canvas.display();
        });

        rotateBtn.addActionListener(e -> {
            renderer.angle += 15;
            canvas.display();
        });

        zoomIn.addActionListener(e -> {
            renderer.scaleX += 0.1f;
            renderer.scaleY += 0.1f;
            canvas.display();
        });

        zoomOut.addActionListener(e -> {
            renderer.scaleX -= 0.1f;
            renderer.scaleY -= 0.1f;
            if (renderer.scaleX < 0.1f) renderer.scaleX = renderer.scaleY = 0.1f;
            canvas.display();
        });

        // ===== Start animation thread =====
        new Thread(() -> {
            float direction = 1;
            while (isAnimating) {
                renderer.moveX += 5 * direction;
                renderer.angle += 2;
                if (renderer.moveX > 150 || renderer.moveX < -150) direction *= -1;

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}

                SwingUtilities.invokeLater(canvas::display);
            }
        }).start();

        setVisible(true);
    }
}
