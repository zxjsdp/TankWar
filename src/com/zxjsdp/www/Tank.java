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
    public static final int X_SPEED = 6;
    public static final int Y_SPEED = 6;
    TankClient tc = null;
    int x, y;

    private boolean bL = false, bU = false, bR = false, bD = false;
    private boolean good;

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    private Direction dir = Direction.STOP;
    private Direction ptDir = Direction.D;

    public Tank(int x, int y, boolean good) {
        this.x = x;
        this.y = y;
        this.good = good;
    }

    public Tank(int x, int y, boolean good, TankClient tc) {
        this(x, y, good);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        if (good) g.setColor(Color.red);
        else g.setColor(Color.blue);
        g.fillOval(x, y, TANK_WIDTHS, TANK_HEIGHT);
        g.setColor(c);

        switch (ptDir) {
            case L:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x, y + Tank.TANK_HEIGHT / 2);
                break;
            case LU:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x, y);
                break;
            case U:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x + Tank.TANK_WIDTHS/2, y);
                break;
            case RU:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x + Tank.TANK_WIDTHS, y);
                break;
            case R:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x + Tank.TANK_WIDTHS, y + Tank.TANK_HEIGHT/2);
                break;
            case RD:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x + Tank.TANK_WIDTHS, y + Tank.TANK_HEIGHT);
                break;
            case D:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x + Tank.TANK_WIDTHS/2, y + Tank.TANK_HEIGHT);
                break;
            case LD:
                g.drawLine(x + Tank.TANK_WIDTHS / 2, y + Tank.TANK_HEIGHT / 2, x, y + Tank.TANK_HEIGHT);
                break;
        }

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

        if (this.dir != Direction.STOP) {
            this.ptDir = dir;
        }

        if (x < 0) x = 0;
        if (y < 30) y = 30;
        if (x + Tank.TANK_WIDTHS > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.TANK_WIDTHS;
        if (y + Tank.TANK_HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.TANK_HEIGHT;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                fire();
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        locateDirection();
    }

    public void KeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
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
        Missile m = new Missile(x, y, ptDir, tc);
        tc.missiles.add(m);
        return m;
    }

    private void checkBoarder() {
        if (x <= TankClient.WINDOW_INIT_POSITION + TANK_WIDTHS/2) {
            dir = opositeDirection(dir);
        } else if (x >= TankClient.WINDOW_INIT_POSITION + TankClient.GAME_WIDTH - TANK_WIDTHS/2) {
            dir = opositeDirection(dir);
        } else if (y <= TankClient.WINDOW_INIT_POSITION - Tank.TANK_HEIGHT/2) {
            dir = opositeDirection(dir);
        } else if (y >= TankClient.WINDOW_INIT_POSITION + TankClient.GAME_HEIGHT - TANK_HEIGHT/2) {
            dir = opositeDirection(dir);
        }
    }

    private Direction opositeDirection(Direction d) {
        if (d == Direction.L) {
            return Direction.R;
        } else if (d == Direction.R) {
            return Direction.L;
        } else if (d == Direction.U) {
            return Direction.D;
        } else if (d == Direction.D) {
            return Direction.U;
        } else if (d == Direction.LU) {
            return Direction.RD;
        } else if (d == Direction.RU) {
            return Direction.LD;
        } else if (d == Direction.RD) {
            return Direction.LU;
        } else {
            return Direction.RU;
        }
    }
}
