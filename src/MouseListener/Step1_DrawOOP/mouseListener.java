package MouseListener.OOP;

import javax.media.opengl.GLCanvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class mouseListener implements MouseListener, MouseMotionListener {

    private Draw drawObject;
    private GLCanvas canvas;

    public mouseListener(Draw drawObject, GLCanvas canvas) {
        this.drawObject = drawObject;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawObject.setX(e.getX());
        drawObject.setY(600 - e.getY());
        canvas.display();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawObject.setX(e.getX());
        drawObject.setY(600 - e.getY());
        canvas.display();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}