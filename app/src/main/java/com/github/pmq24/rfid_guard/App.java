
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();

        JPanel pPanel = new JPanel();
        pPanel.add(new JLabel("Primary"));
        final Color ASH_GREY_COLOR = new Color(178, 190, 181);
        pPanel.setBackground(ASH_GREY_COLOR);
        mainWindow.setPrimaryPanel(pPanel);

        JPanel sPanel = new JPanel();
        sPanel.add(new JLabel("Secondary"));
        mainWindow.setSecondaryPanel(sPanel);

        mainWindow.show();

    }
}
