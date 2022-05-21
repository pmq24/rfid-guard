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
        unknownRows = new HashSet<>();
        model = new TagReadsTableModel();
        setModel(model);
    }

    public void addRow(TagRead tagRead, String status) {
        model.addRow(new Object[] {tagRead.getTagRfid(), tagRead.getTime().toString(), status});

        if (status.equals("Not Purchased"))
            unpurchasedRows.add(getRowCount() - 1);

        if (status.equals("Unknown"))
            unknownRows.add(getRowCount() - 1);

        scrollRectToVisible(getCellRect(getRowCount() - 1, 0, false));
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component cell = super.prepareRenderer(renderer, row, column);

        final boolean thisCellBelongsToAnUnpurchasedRow = unpurchasedRows.contains(row);
        final boolean thisCellBelongsToAnUnknownRow = unknownRows.contains(row);

        if (thisCellBelongsToAnUnpurchasedRow) {
            cell.setBackground(Color.RED);
        } else if (thisCellBelongsToAnUnknownRow) {
            cell.setBackground(Color.YELLOW);
        } else {
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }

    private final DefaultTableModel model;
    private final Set<Integer> unpurchasedRows;
    private final Set<Integer> unknownRows;

}

class TagReadsTableModel extends DefaultTableModel {

    public static final String[] headers = new String[]{"RFID", "TIME", "STATUS"};
    public static final Class<?>[] columnClasses = new Class<?>[] {String.class, LocalDateTime.class, String.class};

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