package com.github.pmq24.rfid_guard.gui;

import com.github.pmq24.rfid_guard.data.TagRead;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TagReadsTable extends JTable {

    public TagReadsTable() {
        unpurchasedRows = new HashSet<>();
        model = new TagReadsTableModel();
        setModel(model);

        setAutoscrolls(true);
    }

    public void addRow(TagRead tagRead, boolean isPurchased) {
        model.addRow(new Object[] {tagRead.getTagRfid(), tagRead.getTime().toString(), isPurchased});

        if (!isPurchased)
            unpurchasedRows.add(getRowCount() - 1);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component cell = super.prepareRenderer(renderer, row, column);

        final boolean thisCellBelongsToAnUnpurchasedRow = unpurchasedRows.contains(row);

        if (thisCellBelongsToAnUnpurchasedRow) {
            cell.setBackground(Color.RED);
        } else {
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }

    private final DefaultTableModel model;
    private final Set<Integer> unpurchasedRows;

}

class TagReadsTableModel extends DefaultTableModel {

    public static final String[] headers = new String[]{"RFID", "TIME", "PURCHASED"};
    public static final Class<?>[] columnClasses = new Class<?>[] {String.class, LocalDateTime.class, Boolean.class};

    public TagReadsTableModel() {
        super(null, headers);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}