
package com.github.pmq24.rfid_guard.gui;

import com.github.pmq24.rfid_guard.data.TagRead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame {

    public interface CloseListener {
        void onClose();
    }

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        rootSplitPane = new JSplitPane();

        readersPane = new JPanel();
        readersListScrollPane = new JScrollPane();
        readersList = new JList<>();
        addReaderButton = new JButton();

        rootSplitPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        rootSplitPane.setDividerSize(10);

        readersPane.setAlignmentX(0.0F);
        readersPane.setAlignmentY(0.0F);
        readersPane.setLayout(new BoxLayout(readersPane, BoxLayout.PAGE_AXIS));

        readersListScrollPane.setAlignmentX(0.0F);
        readersListScrollPane.setAlignmentY(0.0F);

        readersList.setModel(new AbstractListModel<String>() {
            final String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        readersListScrollPane.setViewportView(readersList);

        readersPane.add(readersListScrollPane);

        addReaderButton.setText("Add reader");
        addReaderButton.setAlignmentY(1.0F);
        readersPane.add(addReaderButton);

        rootSplitPane.setLeftComponent(readersPane);

        tagReadsPanel = new TagReadsPanel();
        rootSplitPane.setRightComponent(tagReadsPanel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(rootSplitPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(rootSplitPane)
        );

        pack();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                EventQueue.invokeLater(() -> setVisible(false));
            }

            @Override
            public void windowClosed(WindowEvent e) {
                closeListener.onClose();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void showGui() {
        EventQueue.invokeLater(() -> setVisible(true));
    }

    public void addTagReadRow(TagRead tagRead, boolean isPurchased) {
        EventQueue.invokeLater(() -> {
            tagReadsPanel.addTableRow(tagRead, isPurchased);
        });
    }

    public void setCloseListener(CloseListener listener) {
        closeListener = listener;
    }

    private JSplitPane rootSplitPane;

    private TagReadsPanel tagReadsPanel;

    private JButton addReaderButton;
    private JTable readsTable;
    private JList<String> readersList;
    private JScrollPane readersListScrollPane;
    private JPanel readersPane;

    private CloseListener closeListener;

}
