package com.faculty.management.ui;

import com.faculty.management.dao.ReportDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class ReportManagement extends JFrame {

    private JComboBox<String> reportTypeComboBox;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private ReportDAO reportDAO;

    private JLabel reportCountLabel;
    private JLabel reportNameLabel;

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD = Color.WHITE;
    private final Color TEXT = new Color(40, 50, 65);
    private final Color MUTED = new Color(110, 120, 135);
    private final Color BORDER = new Color(225, 228, 235);

    public ReportManagement() {

        reportDAO = new ReportDAO();

        setTitle("Report Management");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        createUI();

        loadFacultyWorkloadReport();
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(20, 20));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(20, 25, 20, 25)
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        JLabel title =
                new JLabel("Reports");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Generate, view and export faculty workload and duty reports"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(MUTED);

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(5)
        );
        titlePanel.add(subtitle);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        centerPanel.setOpaque(false);

        // =====================================================
        // REPORT SELECTION CARD
        // =====================================================

        JPanel selectionCard =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        selectionCard.setBackground(CARD);

        selectionCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JPanel selectionLeft =
                new JPanel();

        selectionLeft.setLayout(
                new BoxLayout(
                        selectionLeft,
                        BoxLayout.Y_AXIS
                )
        );

        selectionLeft.setOpaque(false);

        JLabel selectionTitle =
                new JLabel(
                        "Report Selection"
                );

        selectionTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        selectionTitle.setForeground(TEXT);

        JLabel selectionHint =
                new JLabel(
                        "Choose the report you want to generate"
                );

        selectionHint.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        selectionHint.setForeground(MUTED);

        selectionLeft.add(selectionTitle);

        selectionLeft.add(
                Box.createVerticalStrut(4)
        );

        selectionLeft.add(selectionHint);

        JPanel selectionRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        selectionRight.setOpaque(false);

        JLabel reportLabel =
                new JLabel("Report Type:");

        reportLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        reportTypeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Faculty Workload Report",
                                "Duty Assignment Report",
                                "Faculty Workload Summary"
                        }
                );

        reportTypeComboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        reportTypeComboBox.setPreferredSize(
                new Dimension(240, 38)
        );

        JButton generateButton =
                createButton(
                        "Generate Report",
                        new Color(45, 105, 180)
                );

        selectionRight.add(reportLabel);
        selectionRight.add(reportTypeComboBox);
        selectionRight.add(generateButton);

        selectionCard.add(
                selectionLeft,
                BorderLayout.WEST
        );

        selectionCard.add(
                selectionRight,
                BorderLayout.EAST
        );

        centerPanel.add(
                selectionCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // REPORT TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        tableCard.setBackground(CARD);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        // Table header
        JPanel tableHeader =
                new JPanel(
                        new BorderLayout()
                );

        tableHeader.setOpaque(false);

        JPanel tableTitlePanel =
                new JPanel();

        tableTitlePanel.setLayout(
                new BoxLayout(
                        tableTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        tableTitlePanel.setOpaque(false);

        reportNameLabel =
                new JLabel(
                        "Faculty Workload Report"
                );

        reportNameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        reportNameLabel.setForeground(TEXT);

        JLabel tableHint =
                new JLabel(
                        "Report data generated from the current database records"
                );

        tableHint.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        tableHint.setForeground(MUTED);

        tableTitlePanel.add(reportNameLabel);

        tableTitlePanel.add(
                Box.createVerticalStrut(3)
        );

        tableTitlePanel.add(tableHint);

        reportCountLabel =
                new JLabel("Records: 0");

        reportCountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        reportCountLabel.setForeground(
                new Color(75, 90, 110)
        );

        tableHeader.add(
                tableTitlePanel,
                BorderLayout.WEST
        );

        tableHeader.add(
                reportCountLabel,
                BorderLayout.EAST
        );

        tableCard.add(
                tableHeader,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel();

        reportTable =
                new JTable(tableModel) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        reportTable.setRowHeight(32);

        reportTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        reportTable.setAutoCreateRowSorter(true);

        reportTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        reportTable.setShowGrid(true);

        reportTable.setGridColor(
                new Color(235, 238, 242)
        );

        reportTable.setSelectionBackground(
                new Color(220, 232, 245)
        );

        reportTable.setSelectionForeground(
                TEXT
        );

        reportTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        reportTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(0, 38)
                );

        reportTable.getTableHeader()
                .setBackground(
                        new Color(235, 239, 244)
                );

        reportTable.getTableHeader()
                .setForeground(TEXT);

        JScrollPane scrollPane =
                new JScrollPane(
                        reportTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTONS
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton refreshButton =
                createButton(
                        "Refresh",
                        new Color(75, 90, 110)
                );

        JButton exportButton =
                createButton(
                        "Export CSV",
                        new Color(46, 125, 50)
                );

        JButton closeButton =
                createButton(
                        "Close",
                        new Color(110, 120, 135)
                );

        buttonPanel.add(refreshButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(closeButton);

        JLabel infoLabel =
                new JLabel(
                        "Tip: Click a column header to sort the report"
                );

        infoLabel.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        infoLabel.setForeground(MUTED);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.WEST
        );

        bottomPanel.add(
                infoLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        generateButton.addActionListener(
                e -> generateSelectedReport()
        );

        refreshButton.addActionListener(
                e -> generateSelectedReport()
        );

        exportButton.addActionListener(
                e -> exportToCSV()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        reportTypeComboBox.addActionListener(
                e -> updateReportPreviewName()
        );

        add(mainPanel);
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private JButton createButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(background);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        Color normalColor = background;

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                normalColor.darker()
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                normalColor
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // UPDATE REPORT NAME
    // =========================================================

    private void updateReportPreviewName() {

        String selectedReport =
                (String) reportTypeComboBox
                        .getSelectedItem();

        if (selectedReport != null) {

            reportNameLabel.setText(
                    selectedReport
            );
        }
    }

    // =========================================================
    // GENERATE SELECTED REPORT
    // =========================================================

    private void generateSelectedReport() {

        String selectedReport =
                (String) reportTypeComboBox
                        .getSelectedItem();

        if (selectedReport == null) {
            return;
        }

        reportNameLabel.setText(
                selectedReport
        );

        if (selectedReport.equals(
                "Faculty Workload Report"
        )) {

            loadFacultyWorkloadReport();

        } else if (selectedReport.equals(
                "Duty Assignment Report"
        )) {

            loadDutyAssignmentReport();

        } else if (selectedReport.equals(
                "Faculty Workload Summary"
        )) {

            loadFacultyWorkloadSummary();
        }
    }

    // =========================================================
    // FACULTY WORKLOAD REPORT
    // =========================================================

    private void loadFacultyWorkloadReport() {

        tableModel.setColumnIdentifiers(
                new String[]{
                        "Faculty",
                        "Subject Code",
                        "Subject Name",
                        "Hours/Week",
                        "Semester",
                        "Academic Year"
                }
        );

        tableModel.setRowCount(0);

        List<Object[]> report =
                reportDAO.getFacultyWorkloadReport();

        for (Object[] row : report) {

            tableModel.addRow(row);
        }

        updateRecordCount();
        styleColumns();
    }

    // =========================================================
    // DUTY ASSIGNMENT REPORT
    // =========================================================

    private void loadDutyAssignmentReport() {

        tableModel.setColumnIdentifiers(
                new String[]{
                        "Faculty",
                        "Duty",
                        "Duty Type",
                        "Date",
                        "Start Time",
                        "End Time",
                        "Location",
                        "Status"
                }
        );

        tableModel.setRowCount(0);

        List<Object[]> report =
                reportDAO.getDutyAssignmentReport();

        for (Object[] row : report) {

            tableModel.addRow(row);
        }

        updateRecordCount();
        styleColumns();
    }

    // =========================================================
    // FACULTY WORKLOAD SUMMARY
    // =========================================================

    private void loadFacultyWorkloadSummary() {

        tableModel.setColumnIdentifiers(
                new String[]{
                        "Faculty",
                        "Total Hours/Week",
                        "Workload Status"
                }
        );

        tableModel.setRowCount(0);

        List<Object[]> report =
                reportDAO.getFacultyWorkloadSummary();

        for (Object[] row : report) {

            tableModel.addRow(row);
        }

        updateRecordCount();
        styleColumns();
    }

    // =========================================================
    // RECORD COUNT
    // =========================================================

    private void updateRecordCount() {

        reportCountLabel.setText(
                "Records: "
                        + tableModel.getRowCount()
        );
    }

    // =========================================================
    // TABLE COLUMN STYLE
    // =========================================================

    private void styleColumns() {

        if (reportTable.getColumnCount() == 0) {
            return;
        }

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        if (reportTable.getColumnCount() >= 2) {

            reportTable
                    .getColumnModel()
                    .getColumn(0)
                    .setPreferredWidth(180);
        }

        // Center numeric/status-like columns
        for (int i = 0;
             i < reportTable.getColumnCount();
             i++) {

            String columnName =
                    reportTable
                            .getColumnName(i);

            if (columnName.equals(
                    "Hours/Week"
            )
                    || columnName.equals(
                    "Semester"
            )
                    || columnName.equals(
                    "Academic Year"
            )
                    || columnName.equals(
                    "Total Hours/Week"
            )
                    || columnName.equals(
                    "Status"
            )
                    || columnName.equals(
                    "Date"
            )
                    || columnName.equals(
                    "Start Time"
            )
                    || columnName.equals(
                    "End Time"
            )) {

                reportTable
                        .getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(
                                centerRenderer
                        );
            }
        }
    }

    // =========================================================
    // EXPORT CSV
    // =========================================================

    private void exportToCSV() {

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "There is no data to export.",
                    "No Data",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setDialogTitle(
                "Save Report as CSV"
        );

        int result =
                fileChooser.showSaveDialog(this);

        if (result !=
                JFileChooser.APPROVE_OPTION) {

            return;
        }

        File file =
                fileChooser.getSelectedFile();

        if (!file.getName()
                .toLowerCase()
                .endsWith(".csv")) {

            file =
                    new File(
                            file.getAbsolutePath()
                                    + ".csv"
                    );
        }

        try (
                PrintWriter writer =
                        new PrintWriter(
                                file,
                                "UTF-8"
                        )
        ) {

            // Headers
            for (int i = 0;
                 i < tableModel.getColumnCount();
                 i++) {

                writer.print(
                        escapeCSV(
                                tableModel
                                        .getColumnName(i)
                        )
                );

                if (i <
                        tableModel.getColumnCount() - 1) {

                    writer.print(",");
                }
            }

            writer.println();

            // Data
            for (int row = 0;
                 row < tableModel.getRowCount();
                 row++) {

                for (int column = 0;
                     column <
                             tableModel.getColumnCount();
                     column++) {

                    Object value =
                            tableModel.getValueAt(
                                    row,
                                    column
                            );

                    writer.print(
                            escapeCSV(
                                    value == null
                                            ? ""
                                            : value.toString()
                            )
                    );

                    if (column <
                            tableModel.getColumnCount() - 1) {

                        writer.print(",");
                    }
                }

                writer.println();
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Report exported successfully!",
                    "Export Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to export report.",
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // CSV ESCAPE
    // =========================================================

    private String escapeCSV(
            String value
    ) {

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")) {

            return "\""
                    + value.replace(
                    "\"",
                    "\"\""
            )
                    + "\"";
        }

        return value;
    }
}