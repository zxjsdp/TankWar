package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;


/**
 * Created by Jin on 2016/2/27.
 */

public class TankClient extends Frame {
    public static final int WINDOW_INIT_POSITION = 100;
    public static final int GAME_WIDTH = 1400;
    public static final int GAME_HEIGHT = 900;

    Tank myTank = new Tank(80, 80, true, this);
    Tank enemyTank = new Tank(100, 100 ,false, this);
    Explode e = new Explode(70, 70, this);
    List<Explode> explodes = new ArrayList<Explode>();
    List<Missile> missiles = new ArrayList<Missile>();

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
        g.drawString("Missiles count: " + missiles.size(), 30, 60);
        g.drawString("Explodes count: " + explodes.size(), 30, 80);

        for (int i=0; i<missiles.size(); i++) {
            Missile m = missiles.get(i);
            m.hitTank(enemyTank);
            m.draw(g);
        }
        for (int i=0; i<explodes.size(); i++) {
            Explode e = explodes.get(i);
            e.draw(g);
        }
        myTank.draw(g);
        enemyTank.draw(g);
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
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.KeyReleased(e);
        }
    }
}
