package com.Run;

import com.MMT_Shop.mani.Login;
import com.MMT_Shop.mani.ViewChao;
import java.awt.Frame;

public class MMT_Run {

    public static void main(String[] args) {
        Frame parent = null;
        new ViewChao(parent, true).setVisible(true);
        new Login(parent, true).setVisible(true);
    }
}
