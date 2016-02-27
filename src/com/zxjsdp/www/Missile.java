package com.zxjsdp.www;

import java.awt.*;

/**
 * Created by Jin on 2016/2/28.
 */
public class Missile {
    public static final int MISSILE_WIDTHS = 10;
    public static final int MISSILE_HEIGHT = 10;
    public static final int X_SPEED = 12;
    public static final int Y_SPEED = 12;
    int x, y;
    Tank.Direction dir;
    TankClient tc = null;
    private boolean live = true;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, Tank.Direction dir, TankClient tc) {
        this(x, y, dir);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.black);
        g.fillOval(x, y, MISSILE_WIDTHS, MISSILE_HEIGHT);
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

        if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
            live = false;
            tc.missiles.remove(this);
        }
    }

    public boolean isLive() {
        return live;
    }
}
