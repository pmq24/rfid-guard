package com.github.pmq24.rfid_guard.gui;

import com.github.pmq24.rfid_guard.data.TagRead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TagReadsPanel extends JPanel {

    public TagReadsPanel() {
        LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JLabel label = new JLabel("READS");
        add(label);

        setBorder(new EmptyBorder(16, 16, 16, 16));

        table = new TagReadsTable();
        add(new JScrollPane(table));
    }

    public void addTableRow(TagRead tagRead, String status, String Notes) {
        table.addRow(tagRead, status, Notes);
    }

    private final TagReadsTable table;

}
