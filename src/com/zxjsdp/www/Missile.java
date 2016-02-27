package com.zxjsdp.www;

import java.awt.*;

/**
 * Created by Jin on 2016/2/28.
 */
public class Missile {
    public static final int MISSILE_WIDTH = 10;
    public static final int MISSILE_HEIGHT = 10;
    public static final int X_SPEED = 4;
    public static final int Y_SPEED = 4;
    int x, y;
    Tank.Direction dir;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.black);
        g.fillOval(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
        g.setColor(c);

        move();
    }

    private void move() {
        switch (dir) {
            case L:
                x -= X_SPEED;
                break;
            case LU:
                x -= X_SPEED;
                y -= Y_SPEED;
                break;
            case U:
                y -= Y_SPEED;
                break;
            case RU:
                x += X_SPEED;
                y -= Y_SPEED;
                break;
            case R:
                x += X_SPEED;
                break;
            case RD:
                x += X_SPEED;
                y += Y_SPEED;
                break;
            case D:
                y += Y_SPEED;
                break;
            case LD:
                x -= X_SPEED;
                y += Y_SPEED;
                break;
        }
    }

}
