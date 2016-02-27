package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by Jin on 2016/2/27.
 */

public class TankClient extends Frame {

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
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(50, 50, 30, 30);
        g.setColor(c);
    }
}
