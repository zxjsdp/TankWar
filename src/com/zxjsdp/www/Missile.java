package com.zxjsdp.www;

import java.awt.*;
import java.util.List;

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

    public boolean isGood() {
        return good;
    }

    private boolean good;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, boolean good, Tank.Direction dir, TankClient tc) {
        this(x, y, dir);
        this.good = good;
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!this.live) {
            tc.missiles.remove(this);
        }
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
        }
    }

    public boolean isLive() {
        return live;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, MISSILE_WIDTHS, MISSILE_HEIGHT);
    }

    public boolean hitTank(Tank t) {
        if (this.isLive() && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
            t.setLive(false);
            this.live = false;
            Explode e = new Explode(x, y, tc);
            tc.explodes.add(e);
            return true;
        }
        return false;
    }

    public boolean hitTanks(List<Tank> tanks) {
        for (int i=0; i<tanks.size(); i++) {
            if (this.hitTank(tc.tanks.get(i)))
                return true;
        }
        return false;
    }
}
