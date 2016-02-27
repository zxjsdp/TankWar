package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by Jin on 2016/2/27.
 */

public class TankClient extends Frame {
    int x = 50;
    int y = 50;

    Image offScreenImage = null;

    public void launchFrame() {
        this.setLocation(100, 100);
        this.setSize(1400, 900);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.green);
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);

        y += 2;
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1400, 900);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0, 0, 1400, 900);
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
}
