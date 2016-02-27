package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Jin on 2016/2/28.
 */
public class Tank {
    public static final int TANK_SIZE = 30;
    int x, y;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, TANK_SIZE, TANK_SIZE);
        g.setColor(c);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            x += 5;
        }
        switch (key) {
            case KeyEvent.VK_LEFT:
                x -= 5;
                checkBoarder();
                break;
            case KeyEvent.VK_UP:
                y -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                x += 5;
                checkBoarder();
                break;
            case KeyEvent.VK_DOWN:
                y += 5;
                break;
        }
    }

    private void checkBoarder() {
        if (x < 0) {
            x = 1400;
        }
        if (x > 1400) {
            x = 0;
        }
    }
}
