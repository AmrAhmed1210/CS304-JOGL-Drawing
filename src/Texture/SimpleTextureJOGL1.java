package Texture;

import javax.media.opengl.*;
import javax.media.opengl.GLCanvas;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class TextureDemo extends JFrame implements GLEventListener {

    private Texture texture;

    public TextureDemo() {
        setTitle("Old Texture Example");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        add(canvas);

        setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        try {
            texture = TextureIO.newTexture(new File("fish.png"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gl.glEnable(GL.GL_TEXTURE_2D);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        texture.enable();
        texture.bind();

        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0f, 0f); gl.glVertex2f(100, 100);
        gl.glTexCoord2f(1f, 0f); gl.glVertex2f(300, 100);
        gl.glTexCoord2f(1f, 1f); gl.glVertex2f(300, 300);
        gl.glTexCoord2f(0f, 1f); gl.glVertex2f(100, 300);

        gl.glEnd();

        texture.disable();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean mc, boolean dc) {}

    public static void main(String[] args) {
        new TextureDemo();
    }
}
