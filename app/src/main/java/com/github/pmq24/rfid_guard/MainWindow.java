
package com.github.pmq24.rfid_guard;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {

        rootSplitPane = new javax.swing.JSplitPane();
        readersPane = new javax.swing.JPanel();
        readersPaneTitle = new javax.swing.JLabel();
        readersListScrollPane = new javax.swing.JScrollPane();
        readersList = new javax.swing.JList<>();
        addReaderButton = new javax.swing.JButton();
        readsPane = new javax.swing.JPanel();
        readsPaneTitle = new javax.swing.JLabel();
        readsTableScrollPane = new javax.swing.JScrollPane();
        readsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rootSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        rootSplitPane.setDividerSize(10);

        readersPane.setAlignmentX(0.0F);
        readersPane.setAlignmentY(0.0F);
        readersPane.setLayout(new javax.swing.BoxLayout(readersPane, javax.swing.BoxLayout.PAGE_AXIS));

        readersPaneTitle.setText("Readers");
        readersPaneTitle.setAlignmentY(0.0F);
        readersPaneTitle.setMinimumSize(new java.awt.Dimension(17, 17));
        readersPane.add(readersPaneTitle);

        readersListScrollPane.setAlignmentX(0.0F);
        readersListScrollPane.setAlignmentY(0.0F);

        readersList.setModel(new javax.swing.AbstractListModel<String>() {
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

        readsPane.setAlignmentX(0.0F);
        readsPane.setAlignmentY(0.0F);
        readsPane.setLayout(new javax.swing.BoxLayout(readsPane, javax.swing.BoxLayout.PAGE_AXIS));

        readsPaneTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        readsPaneTitle.setText("Reads");
        readsPaneTitle.setAlignmentY(0.0F);
        readsPane.add(readsPaneTitle);

        readsTableScrollPane.setAlignmentX(0.0F);
        readsTableScrollPane.setAlignmentY(0.0F);

        readsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "RFID", "Time"
            }
        ) {
            final Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            final boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        readsTable.setAlignmentX(0.0F);
        readsTable.setAlignmentY(0.0F);
        readsTableScrollPane.setViewportView(readsTable);
        if (readsTable.getColumnModel().getColumnCount() > 0) {
            readsTable.getColumnModel().getColumn(0).setResizable(false);
            readsTable.getColumnModel().getColumn(1).setResizable(false);
        }

        readsPane.add(readsTableScrollPane);

        rootSplitPane.setRightComponent(readsPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootSplitPane)
        );

        pack();
    }

    private javax.swing.JButton addReaderButton;
    private javax.swing.JTable readsTable;
    private javax.swing.JList<String> readersList;
    private javax.swing.JScrollPane readersListScrollPane;
    private javax.swing.JPanel readersPane;
    private javax.swing.JLabel readersPaneTitle;
    private javax.swing.JPanel readsPane;
    private javax.swing.JLabel readsPaneTitle;
    private javax.swing.JScrollPane readsTableScrollPane;
    private javax.swing.JSplitPane rootSplitPane;
}
