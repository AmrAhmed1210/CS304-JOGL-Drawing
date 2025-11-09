package MouseListener.OOP;

import javax.media.opengl.GLCanvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener implements KeyListener {
    private Draw drawObject;
    private GLCanvas canvas;

    public keyListener(Draw drawObject, GLCanvas canvas) {
        this.drawObject = drawObject;
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        float currentX = drawObject.getX();
        float currentY = drawObject.getY();

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT -> currentX += 10;
            case KeyEvent.VK_LEFT -> currentX -= 10;
            case KeyEvent.VK_UP -> currentY += 10;
            case KeyEvent.VK_DOWN -> currentY -= 10;
        }

        drawObject.setX(currentX);
        drawObject.setY(currentY);

        canvas.display();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}