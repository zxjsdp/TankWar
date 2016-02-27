package com.zxjsdp.www;

import java.awt.*;


/**
 * Created by Jin on 2016/2/27.
 */
public class TankClient extends Frame {

    public void launchFrame() {
        this.setLocation(400, 300);
        this.setSize(1200, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.launchFrame();
    }
}
