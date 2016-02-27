package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by Jin on 2016/2/27.
 */

public class TankClient extends Frame {
    public static final int WINDOW_INIT_POSITION = 100;
    public static final int GAME_WIDTH = 1400;
    public static final int GAME_HEIGHT = 900;
    public static final int TANK_SIZE = 30;

    int x = 50;
    int y = 50;

    Image offScreenImage = null;

    public void launchFrame() {
        this.setLocation(WINDOW_INIT_POSITION, WINDOW_INIT_POSITION);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.green);

        this.addKeyListener(new KeyMonitor());
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, TANK_SIZE, TANK_SIZE);
        g.setColor(c);

//        y += 2;
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                x += 5;
            }
            switch (key) {
                case KeyEvent.VK_LEFT:
                    x -= 5;
                    break;
                case KeyEvent.VK_UP:
                    y -= 5;
                    break;
                case KeyEvent.VK_RIGHT:
                    x += 5;
                    break;
                case KeyEvent.VK_DOWN:
                    y += 5;
                    break;
            }
        }
    }
}
