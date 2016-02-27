package com.zxjsdp.www;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by Jin on 2016/2/27.
 */

public class TankClient extends Frame {

    public void launchFrame() {
        this.setLocation(400, 300);
        this.setSize(1200, 800);
        this.setTitle("TankWar");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        setVisible(true);
    }
}
