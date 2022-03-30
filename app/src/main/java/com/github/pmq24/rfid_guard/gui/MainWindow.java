package com.github.pmq24.rfid_guard.gui;

import lombok.Getter;

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

    public void setSecondaryPanel(JPanel panel) {
        secondaryPanel = panel;
        frame.add(secondaryPanel, BorderLayout.LINE_START);
    }

    public void setPrimaryPanel(JPanel panel) {
        primaryPanel = panel;
        frame.add(primaryPanel, BorderLayout.CENTER);
    }


    private JFrame frame;

    @Getter private JPanel secondaryPanel;
    @Getter private JPanel primaryPanel;

}
