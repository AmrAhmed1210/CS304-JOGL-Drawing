package Texture;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class FishGame extends JFrame implements GLEventListener, KeyListener {

    private GLCanvas canvas;

    // fish position
    private float fishX = 400;
    private float fishY = 300;
    private float fishSize = 50;

    // textures
    private Texture fishTexture;
    private Texture fishTextures;
    private Texture seaTexture;

    // {x, y, vx, vy}
    private final List<float[]> smallFishList = new ArrayList<>();

    public FishGame() {
        setTitle("Simple Fish Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new GLCanvas();
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);

        add(canvas);
        setVisible(true);
        canvas.requestFocus();

        // create small fish with random position + random speed
        for (int i = 0; i < 5; i++) {
//            float x = (float) (Math.random() * 700 + 50);
//            float y = (float) (Math.random() * 400 + 150);
//
//            float vx = (float) (Math.random() * 1.2 - 0.6);
//            float vy = (float) (Math.random() * 1.2 - 0.6);

// تحريك السمك من اليمين للشمال

            // تحريك السمكة
            fish[0] += fish[2];

            // لو خرجت من الشمال هترجع يمين تاني
            if (fish[0] < -50) {
                fish[0] = 850; // خارج الشاشة يمين
                fish[1] = (float) (Math.random() * 500 + 50); // مكان جديد عشوائي
                fish[2] = -((float) Math.random() * 2 + 0.5f); // سرعة جديدة سالبة
            }

        }

            smallFishList.add(new float[]{x, y, vx, vy});
        }
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(0f, 0.5f, 1f, 1f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 800, 0, 600, -1, 1);

        gl.glEnable(GL.GL_TEXTURE_2D);

        try {
            seaTexture = TextureIO.newTexture(new File("src/Images/sea.jpg"), false);
            fishTexture = TextureIO.newTexture(new File("src/Images/fish.jpg"), false);
            fishTextures = TextureIO.newTexture(new File("src/Images/smallfish.jpg"), false);
        } catch (Exception e) {
            System.out.println("Error loading texture: " + e.getMessage());
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // background
        drawTexture(gl, seaTexture, 0, 0, 800, 600);

        // small fish movement + draw
        for (float[] fish : smallFishList) {

            // movement
            fish[0] += fish[2];
            fish[1] += fish[3];

            // bounce on borders
            if (fish[0] < 0 || fish[0] > 780) fish[2] = -fish[2];
            if (fish[1] < 0 || fish[1] > 570) fish[3] = -fish[3];

            drawTexture(gl, fishTextures, fish[0], fish[1], 20, 30);
        }

        // collision detection
        ArrayList<float[]> eaten = new ArrayList<>();
        for (float[] fish : smallFishList) {
            double d = Math.sqrt(Math.pow(fishX - fish[0], 2) + Math.pow(fishY - fish[1], 2));
            if (d < fishSize + 20) {
                eaten.add(fish);
                fishSize += 5;
            }
        }
        smallFishList.removeAll(eaten);

        // draw big fish
        drawTexture(gl, fishTexture, fishX - fishSize, fishY - fishSize, fishSize * 2, fishSize * 2);

        canvas.repaint();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {}

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {}

    private void drawTexture(GL gl, Texture tex, float x, float y, float w, float h) {
        if (tex == null) return;
        tex.enable();
        tex.bind();

        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0, 1); gl.glVertex2f(x, y);
        gl.glTexCoord2f(1, 1); gl.glVertex2f(x + w, y);
        gl.glTexCoord2f(1, 0); gl.glVertex2f(x + w, y + h);
        gl.glTexCoord2f(0, 0); gl.glVertex2f(x, y + h);

        gl.glEnd();

        tex.disable();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        float sp = 10;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> fishX += sp;
            case KeyEvent.VK_LEFT -> fishX -= sp;
            case KeyEvent.VK_UP -> fishY += sp;
            case KeyEvent.VK_DOWN -> fishY -= sp;
        }
        canvas.display();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new FishGame();
    }
}
