package com.github.pmq24.rfid_guard.gui;

import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;
import com.github.pmq24.rfid_guard.reading.PredefinedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import com.impinj.octane.OctaneSdkException;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;

public class ConnectionWindow extends JFrame{
    private JLabel IPAddressLabel;
    private JTextField textIpAddress;
    private JButton connectButton;


    public ConnectionWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        connectButton.setEnabled(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database database = new SeededDatabase();
                TagReader tagReader = new PredefinedTagReader(database.getTagTable().selectAll());

                try {
                    String IpAddress = textIpAddress.getText();
                    URI uri = URI.create(IpAddress);

                    Socket socket = IO.socket(uri);

                    socket.connect();

                    Ack ack = args1 -> {
                        for (Object o : args1) {
                            System.out.println(o);
                        }
                    };

                    tagReader.setTagReadListener(tagRead -> {
                        socket.emit("detect", "{\"epc\":\"asd\"}", ack);
                    });
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }


        });


    }

    public void showGui() {
        EventQueue.invokeLater(() -> setVisible(true));
    }
}
