package com.github.pmq24.rfid_guard.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    public MainWindow() {
        setUp();
    }

    public void show() {
        EventQueue.invokeLater(() -> frame.setVisible(true));
    }

    private void setUp() {
        frame = new JFrame("RFID Guard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFrame frame;

}
