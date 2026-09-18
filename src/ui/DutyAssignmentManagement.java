package com.faculty.management.ui;

import com.faculty.management.dao.DutyAssignmentDAO;
import com.faculty.management.dao.DutyDAO;
import com.faculty.management.dao.FacultyDAO;
import com.faculty.management.model.Duty;
import com.faculty.management.model.DutyAssignment;
import com.faculty.management.model.Faculty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DutyAssignmentManagement extends JFrame {

    private JComboBox<Faculty> facultyComboBox;
    private JComboBox<Duty> dutyComboBox;
    private JComboBox<String> statusComboBox;

    private JTextField assignmentIdField;
    private JTextField searchField;

    private JTable assignmentTable;
    private DefaultTableModel tableModel;

    private DutyAssignmentDAO assignmentDAO;
    private FacultyDAO facultyDAO;
    private DutyDAO dutyDAO;

    private final Font LABEL_FONT =
            new Font("Arial", Font.BOLD, 13);

    private final Font FIELD_FONT =
            new Font("Arial", Font.PLAIN, 14);

    private final Font BUTTON_FONT =
            new Font("Arial", Font.BOLD, 13);

    public DutyAssignmentManagement() {

        assignmentDAO = new DutyAssignmentDAO();
        facultyDAO = new FacultyDAO();
        dutyDAO = new DutyDAO();

        setTitle("Duty Assignment Management");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        createUI();

        loadFaculty();
        loadDuties();
        loadAssignments();
    }

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);

        JPanel titlePanel =
                new JPanel();
        titlePanel.setLayout(
                new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );
        titlePanel.setOpaque(false);

        JLabel title =
                new JLabel("Duty Assignment Management");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setForeground(new Color(35, 45, 60));

        JLabel subtitle =
                new JLabel(
                        "Assign duties to faculty and manage assignment status"
                );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitle.setForeground(
                new Color(110, 120, 135)
        );

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
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
                new JPanel(new BorderLayout(15, 15));

        centerPanel.setOpaque(false);

        // =====================================================
        // FORM CARD
        // =====================================================

        JPanel formCard =
                new JPanel(new BorderLayout());

        formCard.setBackground(Color.WHITE);

        formCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)
                        ),
                        new EmptyBorder(
                                18, 20, 18, 20
                        )
                )
        );

        JLabel formTitle =
                new JLabel("Assignment Details");

        formTitle.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        formTitle.setForeground(
                new Color(45, 55, 70)
        );

        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                1, 4, 15, 0
                        )
                );

        formPanel.setOpaque(false);

        // Assignment ID
        JPanel idPanel =
                createFieldPanel("Assignment ID");

        assignmentIdField =
                new JTextField();

        assignmentIdField.setEditable(false);
        styleTextField(assignmentIdField);

        idPanel.add(assignmentIdField);

        // Faculty
        JPanel facultyPanel =
                createFieldPanel("Faculty");

        facultyComboBox =
                new JComboBox<>();

        styleComboBox(facultyComboBox);

        facultyPanel.add(facultyComboBox);

        // Duty
        JPanel dutyPanel =
                createFieldPanel("Duty");

        dutyComboBox =
                new JComboBox<>();

        styleComboBox(dutyComboBox);

        dutyPanel.add(dutyComboBox);

        // Status
        JPanel statusPanel =
                createFieldPanel("Status");

        statusComboBox =
                new JComboBox<>(
                        new String[]{
                                "ASSIGNED",
                                "COMPLETED",
                                "CANCELLED"
                        }
                );

        styleComboBox(statusComboBox);

        statusPanel.add(statusComboBox);

        formPanel.add(idPanel);
        formPanel.add(facultyPanel);
        formPanel.add(dutyPanel);
        formPanel.add(statusPanel);

        formCard.add(
                formPanel,
                BorderLayout.CENTER
        );

        centerPanel.add(
                formCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(new BorderLayout(10, 10));

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)
                        ),
                        new EmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        // Table header
        JPanel tableHeader =
                new JPanel(new BorderLayout());

        tableHeader.setOpaque(false);

        JLabel tableTitle =
                new JLabel("Current Duty Assignments");

        tableTitle.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        tableTitle.setForeground(
                new Color(45, 55, 70)
        );

        // Search
        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchPanel.setOpaque(false);

        JLabel searchLabel =
                new JLabel("Search:");

        searchLabel.setFont(LABEL_FONT);

        searchField =
                new JTextField(18);

        styleTextField(searchField);

        JButton searchButton =
                createButton(
                        "Search",
                        new Color(52, 73, 94)
                );

        JButton viewAllButton =
                createButton(
                        "View All",
                        new Color(90, 100, 115)
                );

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(viewAllButton);

        tableHeader.add(
                tableTitle,
                BorderLayout.WEST
        );

        tableHeader.add(
                searchPanel,
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
                new DefaultTableModel(
                        new String[]{
                                "Assignment ID",
                                "Faculty",
                                "Duty",
                                "Status"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        assignmentTable =
                new JTable(tableModel);

        assignmentTable.setRowHeight(32);

        assignmentTable.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        assignmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        assignmentTable.setAutoCreateRowSorter(true);

        assignmentTable.setShowGrid(true);

        assignmentTable.setGridColor(
                new Color(235, 238, 242)
        );

        assignmentTable.setSelectionBackground(
                new Color(220, 232, 245)
        );

        assignmentTable.setSelectionForeground(
                new Color(30, 40, 50)
        );

        assignmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        assignmentTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(0, 36)
                );

        assignmentTable.getTableHeader()
                .setBackground(
                        new Color(235, 239, 244)
                );

        assignmentTable.getTableHeader()
                .setForeground(
                        new Color(45, 55, 70)
                );

        // Center ID + Status
        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        assignmentTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(centerRenderer);

        assignmentTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        new StatusCellRenderer()
                );

        assignmentTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(120);

        assignmentTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(250);

        assignmentTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(300);

        assignmentTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        JScrollPane scrollPane =
                new JScrollPane(
                        assignmentTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 235)
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
        // BUTTON PANEL
        // =====================================================

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

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

        JButton assignButton =
                createButton(
                        "Assign Duty",
                        new Color(46, 125, 50)
                );

        JButton updateButton =
                createButton(
                        "Update Status",
                        new Color(30, 100, 180)
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        new Color(190, 55, 55)
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        new Color(110, 120, 135)
                );

        JButton refreshButton =
                createButton(
                        "Refresh",
                        new Color(70, 85, 105)
                );

        buttonPanel.add(assignButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.WEST
        );

        JLabel infoLabel =
                new JLabel(
                        "Select an assignment from the table to update or delete it."
                );

        infoLabel.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        infoLabel.setForeground(
                new Color(120, 125, 135)
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

        assignButton.addActionListener(
                e -> assignDuty()
        );

        updateButton.addActionListener(
                e -> updateStatus()
        );

        deleteButton.addActionListener(
                e -> deleteAssignment()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> {
                    loadFaculty();
                    loadDuties();
                    loadAssignments();
                    clearFields();
                }
        );

        searchButton.addActionListener(
                e -> searchAssignments()
        );

        viewAllButton.addActionListener(
                e -> {
                    searchField.setText("");
                    loadAssignments();
                }
        );

        searchField.addActionListener(
                e -> searchAssignments()
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        assignmentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int selectedRow =
                                assignmentTable
                                        .getSelectedRow();

                        if (selectedRow >= 0) {

                            int row =
                                    assignmentTable
                                            .convertRowIndexToModel(
                                                    selectedRow
                                            );

                            assignmentIdField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    0
                                            )
                                            .toString()
                            );

                            String facultyName =
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    1
                                            )
                                            .toString();

                            String dutyName =
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    2
                                            )
                                            .toString();

                            String status =
                                    tableModel
                                            .getValueAt(
                                                    row,
                                                    3
                                            )
                                            .toString();

                            selectFaculty(
                                    facultyName
                            );

                            selectDuty(
                                    dutyName
                            );

                            statusComboBox
                                    .setSelectedItem(
                                            status
                                    );
                        }
                    }
                });

        // Double click table row
        assignmentTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    assignmentTable
                                            .getSelectedRow();

                            if (row >= 0) {

                                int modelRow =
                                        assignmentTable
                                                .convertRowIndexToModel(
                                                        row
                                                );

                                assignmentIdField.setText(
                                        tableModel
                                                .getValueAt(
                                                        modelRow,
                                                        0
                                                )
                                                .toString()
                                );
                            }
                        }
                    }
                });

        add(mainPanel);
    }

    // =========================================================
    // FIELD PANEL
    // =========================================================

    private JPanel createFieldPanel(
            String labelText
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setOpaque(false);

        JLabel label =
                new JLabel(labelText);

        label.setFont(LABEL_FONT);

        label.setForeground(
                new Color(65, 75, 90)
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(6)
        );

        return panel;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(FIELD_FONT);

        field.setPreferredSize(
                new Dimension(180, 36)
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        36
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 210, 218)
                        ),
                        new EmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<?> comboBox
    ) {

        comboBox.setFont(FIELD_FONT);

        comboBox.setPreferredSize(
                new Dimension(180, 36)
        );

        comboBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        36
                )
        );
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(text);

        button.setFont(BUTTON_FONT);

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
                        10, 18, 10, 18
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
    // LOAD FACULTY
    // =========================================================

    private void loadFaculty() {

        facultyComboBox.removeAllItems();

        List<Faculty> facultyList =
                facultyDAO.getAllFaculty();

        for (Faculty faculty : facultyList) {

            facultyComboBox.addItem(faculty);
        }
    }

    // =========================================================
    // LOAD DUTIES
    // =========================================================

    private void loadDuties() {

        dutyComboBox.removeAllItems();

        List<Duty> dutyList =
                dutyDAO.getAllDuties();

        for (Duty duty : dutyList) {

            dutyComboBox.addItem(duty);
        }
    }

    // =========================================================
    // LOAD ASSIGNMENTS
    // =========================================================

    private void loadAssignments() {

        tableModel.setRowCount(0);

        List<DutyAssignment> assignments =
                assignmentDAO.getAllAssignments();

        for (DutyAssignment assignment :
                assignments) {

            String facultyName =
                    getFacultyName(
                            assignment.getFacultyId()
                    );

            String dutyName =
                    getDutyName(
                            assignment.getDutyId()
                    );

            tableModel.addRow(
                    new Object[]{
                            assignment.getAssignmentId(),
                            facultyName,
                            dutyName,
                            assignment.getStatus()
                    }
            );
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchAssignments() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        if (search.isEmpty()) {

            loadAssignments();
            return;
        }

        tableModel.setRowCount(0);

        List<DutyAssignment> assignments =
                assignmentDAO.getAllAssignments();

        for (DutyAssignment assignment :
                assignments) {

            String facultyName =
                    getFacultyName(
                            assignment.getFacultyId()
                    );

            String dutyName =
                    getDutyName(
                            assignment.getDutyId()
                    );

            String status =
                    assignment.getStatus();

            String assignmentId =
                    String.valueOf(
                            assignment.getAssignmentId()
                    );

            if (facultyName
                    .toLowerCase()
                    .contains(search)
                    || dutyName
                    .toLowerCase()
                    .contains(search)
                    || status
                    .toLowerCase()
                    .contains(search)
                    || assignmentId
                    .contains(search)) {

                tableModel.addRow(
                        new Object[]{
                                assignment.getAssignmentId(),
                                facultyName,
                                dutyName,
                                status
                        }
                );
            }
        }
    }

    // =========================================================
    // ASSIGN DUTY
    // =========================================================

    private void assignDuty() {

        Faculty faculty =
                (Faculty) facultyComboBox
                        .getSelectedItem();

        Duty duty =
                (Duty) dutyComboBox
                        .getSelectedItem();

        String status =
                (String) statusComboBox
                        .getSelectedItem();

        if (faculty == null ||
                duty == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select faculty and duty.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        DutyAssignment assignment =
                new DutyAssignment();

        assignment.setFacultyId(
                faculty.getFacultyId()
        );

        assignment.setDutyId(
                duty.getDutyId()
        );

        assignment.setStatus(status);

        if (assignmentDAO.assignDuty(
                assignment
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duty assigned successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAssignments();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to assign duty.\n"
                            + "The faculty may already be assigned to this duty.",
                    "Assignment Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updateStatus() {

        if (assignmentIdField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an assignment first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int assignmentId =
                    Integer.parseInt(
                            assignmentIdField
                                    .getText()
                                    .trim()
                    );

            String status =
                    (String) statusComboBox
                            .getSelectedItem();

            if (assignmentDAO.updateStatus(
                    assignmentId,
                    status
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Assignment status updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAssignments();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update status.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid assignment ID.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteAssignment() {

        if (assignmentIdField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an assignment first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this assignment?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            int assignmentId =
                    Integer.parseInt(
                            assignmentIdField
                                    .getText()
                                    .trim()
                    );

            if (assignmentDAO.deleteAssignment(
                    assignmentId
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Assignment deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAssignments();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to delete assignment.",
                        "Delete Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid assignment ID.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET FACULTY NAME
    // =========================================================

    private String getFacultyName(
            int facultyId
    ) {

        for (int i = 0;
             i < facultyComboBox.getItemCount();
             i++) {

            Faculty faculty =
                    facultyComboBox.getItemAt(i);

            if (faculty.getFacultyId()
                    == facultyId) {

                return faculty.getFacultyName();
            }
        }

        return "Unknown Faculty";
    }

    // =========================================================
    // GET DUTY NAME
    // =========================================================

    private String getDutyName(
            int dutyId
    ) {

        for (int i = 0;
             i < dutyComboBox.getItemCount();
             i++) {

            Duty duty =
                    dutyComboBox.getItemAt(i);

            if (duty.getDutyId()
                    == dutyId) {

                return duty.getDutyName();
            }
        }

        return "Unknown Duty";
    }

    // =========================================================
    // SELECT FACULTY
    // =========================================================

    private void selectFaculty(
            String facultyName
    ) {

        for (int i = 0;
             i < facultyComboBox.getItemCount();
             i++) {

            Faculty faculty =
                    facultyComboBox.getItemAt(i);

            if (faculty.getFacultyName()
                    .equals(facultyName)) {

                facultyComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // SELECT DUTY
    // =========================================================

    private void selectDuty(
            String dutyName
    ) {

        for (int i = 0;
             i < dutyComboBox.getItemCount();
             i++) {

            Duty duty =
                    dutyComboBox.getItemAt(i);

            if (duty.getDutyName()
                    .equals(dutyName)) {

                dutyComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        assignmentIdField.setText("");

        searchField.setText("");

        if (facultyComboBox.getItemCount() > 0) {

            facultyComboBox.setSelectedIndex(0);
        }

        if (dutyComboBox.getItemCount() > 0) {

            dutyComboBox.setSelectedIndex(0);
        }

        statusComboBox.setSelectedIndex(0);

        assignmentTable.clearSelection();
    }

    // =========================================================
    // STATUS RENDERER
    // =========================================================

    private static class StatusCellRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
        ) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            if (!isSelected &&
                    value != null) {

                String status =
                        value.toString();

                if (status.equals("ASSIGNED")) {

                    component.setForeground(
                            new Color(30, 100, 180)
                    );

                } else if (
                        status.equals("COMPLETED")
                ) {

                    component.setForeground(
                            new Color(40, 130, 60)
                    );

                } else if (
                        status.equals("CANCELLED")
                ) {

                    component.setForeground(
                            new Color(190, 55, 55)
                    );
                }

            } else if (isSelected) {

                component.setForeground(
                        new Color(30, 40, 50)
                );
            }

            return component;
        }
    }
}