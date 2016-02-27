package com.zxjsdp.www;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Jin on 2016/2/28.
 */
public class Tank {
    public static final int TANK_WIDTHS = 30;
    public static final int TANK_HEIGHT = 30;
    public static final int X_SPEED = 2;
    public static final int Y_SPEED = 2;
    TankClient tc = null;
    int x, y;

    private boolean bL = false, bU = false, bR = false, bD = false;

    public void KeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                checkBoarder();
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                checkBoarder();
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
        }
        locateDirection();
    }

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    private Direction dir = Direction.STOP;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, TankClient tc) {
        this(x, y);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, TANK_WIDTHS, TANK_HEIGHT);
        g.setColor(c);
        move();
    }

    void move() {
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
            case STOP:
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                tc.myMissile = fire();
                checkBoarder();
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                checkBoarder();
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                checkBoarder();
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        locateDirection();
    }

    void locateDirection() {
        if (bL && !bU && !bR && !bD) dir = Direction.L;
        else if (bL && bU && !bR && !bD) dir = Direction.LU;
        else if (!bL && bU && !bR && !bD) dir = Direction.U;
        else if (!bL && bU && bR && !bD) dir = Direction.RU;
        else if (!bL && !bU && bR && !bD) dir = Direction.R;
        else if (!bL && !bU && bR && bD) dir = Direction.RD;
        else if (!bL && !bU && !bR && bD) dir = Direction.D;
        else if (bL && !bU && !bR && bD) dir = Direction.LD;
    }

    public Missile fire() {
        int x = this.x + Tank.TANK_WIDTHS / 2 - Missile.MISSILE_WIDTHS / 2;
        int y = this.y + Tank.TANK_HEIGHT / 2 - Missile.MISSILE_HEIGHT / 2;
        Missile m = new Missile(x, y, dir);
        return m;
    }

    private void checkBoarder() {
        if (x < 0) {
            x = 1400;
        } else if (x > 1400) {
            x = 0;
        } else if (y < 0) {
            y = 900;
        } else if (y > 900) {
            y = 0;
        }
    }
}
