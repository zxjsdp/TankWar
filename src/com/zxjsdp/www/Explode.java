package com.zxjsdp.www;

import java.awt.*;

/**
 * Created by Jin on 2016/2/28.
 */
public class Explode {
    int x, y;
    private boolean live = true;
    private TankClient tc = null;

    int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
    int step = 0;

    public Explode(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!live) return;
        if (step == diameter.length) {
            live = false;
            step = 0;
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.orange);
        g.fillOval(x, y, diameter[step], diameter[step]);
        step++;
        g.setColor(c);
    }

}
