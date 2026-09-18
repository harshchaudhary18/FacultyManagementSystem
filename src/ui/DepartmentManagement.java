package com.faculty.management.ui;

import com.faculty.management.dao.DepartmentDAO;
import com.faculty.management.model.Department;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DepartmentManagement extends JFrame {

    // ================= COLORS =================

    private final Color PRIMARY_COLOR =
            new Color(29, 48, 80);

    private final Color ADD_COLOR =
            new Color(46, 125, 80);

    private final Color UPDATE_COLOR =
            new Color(45, 92, 145);

    private final Color DELETE_COLOR =
            new Color(190, 65, 65);

    private final Color CLEAR_COLOR =
            new Color(100, 110, 125);

    private final Color BACKGROUND_COLOR =
            new Color(245, 248, 252);

    private final Color BORDER_COLOR =
            new Color(220, 226, 234);

    private final Color TEXT_COLOR =
            new Color(35, 50, 70);

    private final Color MUTED_COLOR =
            new Color(95, 110, 130);

    // ================= FIELDS =================

    private JTextField departmentIdField;
    private JTextField departmentNameField;
    private JTextField searchField;

    private JTable departmentTable;
    private DefaultTableModel tableModel;

    private DepartmentDAO departmentDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DepartmentManagement() {

        departmentDAO = new DepartmentDAO();

        setTitle(
                "Faculty Duty & Workload Management System - Department Management"
        );

        setSize(1100, 700);

        setMinimumSize(
                new Dimension(900, 600)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadDepartments();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                Color.WHITE
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setBackground(
                Color.WHITE
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Department Management"
                );

        title.setForeground(
                PRIMARY_COLOR
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Add, update, search and manage department records"
                );

        subtitle.setForeground(
                MUTED_COLOR
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        JButton closeButton =
                createButton(
                        "Close",
                        DELETE_COLOR
                );

        closeButton.setPreferredSize(
                new Dimension(
                        105,
                        48
                )
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                closeButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        centerPanel.setBackground(
                BACKGROUND_COLOR
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // =====================================================
        // DEPARTMENT DETAILS CARD
        // =====================================================

        JPanel detailsCard =
                new JPanel(
                        new BorderLayout()
                );

        detailsCard.setBackground(
                Color.WHITE
        );

        detailsCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                20,
                                30,
                                18,
                                30
                        )
                )
        );

        // =====================================================
        // DETAILS HEADING
        // =====================================================

        JPanel detailsHeading =
                new JPanel();

        detailsHeading.setBackground(
                Color.WHITE
        );

        detailsHeading.setLayout(
                new BoxLayout(
                        detailsHeading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel detailsTitle =
                new JLabel(
                        "Department Details"
                );

        detailsTitle.setForeground(
                PRIMARY_COLOR
        );

        detailsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel detailsSubtitle =
                new JLabel(
                        "Enter department information below"
                );

        detailsSubtitle.setForeground(
                MUTED_COLOR
        );

        detailsSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        detailsHeading.add(
                detailsTitle
        );

        detailsHeading.add(
                Box.createVerticalStrut(4)
        );

        detailsHeading.add(
                detailsSubtitle
        );

        detailsCard.add(
                detailsHeading,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM AREA
        // =====================================================

        JPanel formArea =
                new JPanel(
                        new GridBagLayout()
                );

        formArea.setBackground(
                Color.WHITE
        );

        formArea.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        5,
                        0
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        gbc.insets =
                new Insets(
                        5,
                        10,
                        5,
                        10
                );

        // =====================================================
        // DEPARTMENT ID
        // =====================================================

        JPanel idPanel =
                createFieldPanel(
                        "Department ID"
                );

        departmentIdField =
                getFieldFromPanel(
                        idPanel
                );

        departmentIdField.setEditable(false);

        departmentIdField.setBackground(
                new Color(
                        240,
                        243,
                        247
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        formArea.add(
                idPanel,
                gbc
        );

        // =====================================================
        // DEPARTMENT NAME
        // =====================================================

        JPanel namePanel =
                createFieldPanel(
                        "Department Name"
                );

        departmentNameField =
                getFieldFromPanel(
                        namePanel
                );

        gbc.gridx = 1;

        formArea.add(
                namePanel,
                gbc
        );

        // Empty area
        gbc.gridx = 2;
        gbc.weightx = 2.0;

        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setBackground(
                Color.WHITE
        );

        formArea.add(
                emptyPanel,
                gbc
        );

        detailsCard.add(
                formArea,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        JButton addButton =
                createButton(
                        "Add Department",
                        ADD_COLOR
                );

        JButton updateButton =
                createButton(
                        "Update",
                        UPDATE_COLOR
                );

        JButton deleteButton =
                createButton(
                        "Delete",
                        DELETE_COLOR
                );

        JButton clearButton =
                createButton(
                        "Clear",
                        CLEAR_COLOR
                );

        addButton.addActionListener(
                e -> addDepartment()
        );

        updateButton.addActionListener(
                e -> updateDepartment()
        );

        deleteButton.addActionListener(
                e -> deleteDepartment()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        detailsCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                detailsCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // RECORDS CARD
        // =====================================================

        JPanel recordsCard =
                new JPanel(
                        new BorderLayout()
                );

        recordsCard.setBackground(
                Color.WHITE
        );

        recordsCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        // =====================================================
        // RECORD HEADER
        // =====================================================

        JPanel recordsHeader =
                new JPanel(
                        new BorderLayout()
                );

        recordsHeader.setBackground(
                Color.WHITE
        );

        JLabel recordsTitle =
                new JLabel(
                        "Department Records"
                );

        recordsTitle.setForeground(
                PRIMARY_COLOR
        );

        recordsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        // =====================================================
        // SEARCH PANEL
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        JLabel searchLabel =
                new JLabel(
                        "Search:"
                );

        searchLabel.setForeground(
                TEXT_COLOR
        );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        220,
                        40
                )
        );

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        200,
                                        215
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );

        JButton searchButton =
                createButton(
                        "Search",
                        UPDATE_COLOR
                );

        JButton viewAllButton =
                createButton(
                        "View All",
                        CLEAR_COLOR
                );

        searchButton.setPreferredSize(
                new Dimension(
                        95,
                        40
                )
        );

        viewAllButton.setPreferredSize(
                new Dimension(
                        95,
                        40
                )
        );

        searchButton.addActionListener(
                e -> searchDepartments()
        );

        viewAllButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadDepartments();
                }
        );

        searchField.addActionListener(
                e -> searchDepartments()
        );

        searchPanel.add(
                searchLabel
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                viewAllButton
        );

        recordsHeader.add(
                recordsTitle,
                BorderLayout.WEST
        );

        recordsHeader.add(
                searchPanel,
                BorderLayout.EAST
        );

        recordsCard.add(
                recordsHeader,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE MODEL
        // =====================================================

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Department ID",
                                "Department Name"
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

        // =====================================================
        // TABLE
        // =====================================================

        departmentTable =
                new JTable(
                        tableModel
                );

        departmentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        departmentTable.setRowHeight(
                32
        );

        departmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        departmentTable.setShowGrid(
                true
        );

        departmentTable.setGridColor(
                new Color(
                        225,
                        230,
                        236
                )
        );

        departmentTable.setBackground(
                Color.WHITE
        );

        departmentTable.setSelectionBackground(
                new Color(
                        218,
                        231,
                        247
                )
        );

        departmentTable.setSelectionForeground(
                TEXT_COLOR
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader tableHeader =
                departmentTable.getTableHeader();

        tableHeader.setBackground(
                PRIMARY_COLOR
        );

        tableHeader.setForeground(
                Color.WHITE
        );

        tableHeader.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        tableHeader.setPreferredSize(
                new Dimension(
                        100,
                        40
                )
        );

        // =====================================================
        // CENTER ALIGN ID
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        departmentTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        // =====================================================
        // COLUMN WIDTH
        // =====================================================

        departmentTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        180
                );

        departmentTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        500
                );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        departmentTable
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        900,
                        220
                )
        );

        scrollPane.setMinimumSize(
                new Dimension(
                        700,
                        180
                )
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );

        recordsCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        departmentTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        departmentTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    loadSelectedDepartment(
                                            row
                                    );
                                }
                            }
                        }
                );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        departmentTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    departmentTable
                                            .getSelectedRow();

                            if (row >= 0) {

                                loadSelectedDepartment(
                                        row
                                );
                            }
                        }
                    }
                }
        );

        centerPanel.add(
                recordsCard,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =====================================================
    // FIELD PANEL
    // =====================================================

    private JPanel createFieldPanel(
            String labelText
    ) {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                Color.WHITE
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setForeground(
                TEXT_COLOR
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JTextField field =
                createTextField();

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(field);

        return panel;
    }

    // =====================================================
    // GET FIELD
    // =====================================================

    private JTextField getFieldFromPanel(
            JPanel panel
    ) {

        return (JTextField)
                panel.getComponent(2);
    }

    // =====================================================
    // TEXT FIELD
    // =====================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        260,
                        40
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        195,
                                        205,
                                        218
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                9,
                                5,
                                9
                        )
                )
        );

        return field;
    }

    // =====================================================
    // ADD DEPARTMENT
    // =====================================================

    private void addDepartment() {

        String name =
                departmentNameField
                        .getText()
                        .trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter department name.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            departmentNameField.requestFocus();

            return;
        }

        try {

            Department department =
                    new Department();

            department.setDepartmentName(
                    name
            );

            if (departmentDAO
                    .addDepartment(department)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Department added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadDepartments();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add department.",
                        "Add Department",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add department.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE DEPARTMENT
    // =====================================================

    private void updateDepartment() {

        try {

            String idText =
                    departmentIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a department first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idText
                    );

            String name =
                    departmentNameField
                            .getText()
                            .trim();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter department name.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                departmentNameField.requestFocus();

                return;
            }

            Department department =
                    new Department();

            department.setDepartmentId(
                    id
            );

            department.setDepartmentName(
                    name
            );

            if (departmentDAO
                    .updateDepartment(department)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Department updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadDepartments();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed.",
                        "Update Department",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Department ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update department.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE DEPARTMENT
    // =====================================================

    private void deleteDepartment() {

        try {

            String idText =
                    departmentIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a department first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            idText
                    );

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this department?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (departmentDAO
                        .deleteDepartment(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Department deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadDepartments();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete department.",
                            "Delete Department",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Department ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete department.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD DEPARTMENTS
    // =====================================================

    private void loadDepartments() {

        try {

            tableModel.setRowCount(0);

            List<Department> departments =
                    departmentDAO
                            .getAllDepartments();

            for (Department department :
                    departments) {

                tableModel.addRow(
                        new Object[]{
                                department.getDepartmentId(),
                                department.getDepartmentName()
                        }
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load department records.\n\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SEARCH DEPARTMENTS
    // =====================================================

    private void searchDepartments() {

        try {

            String name =
                    searchField
                            .getText()
                            .trim();

            if (name.isEmpty()) {

                loadDepartments();

                return;
            }

            tableModel.setRowCount(0);

            List<Department> departments =
                    departmentDAO
                            .searchDepartments(name);

            for (Department department :
                    departments) {

                tableModel.addRow(
                        new Object[]{
                                department.getDepartmentId(),
                                department.getDepartmentName()
                        }
                );
            }

            if (departments.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No department found for: "
                                + name,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search departments.\n\n"
                            + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD SELECTED DEPARTMENT
    // =====================================================

    private void loadSelectedDepartment(
            int row
    ) {

        Object id =
                tableModel.getValueAt(
                        row,
                        0
                );

        Object name =
                tableModel.getValueAt(
                        row,
                        1
                );

        departmentIdField.setText(
                id == null
                        ? ""
                        : id.toString()
        );

        departmentNameField.setText(
                name == null
                        ? ""
                        : name.toString()
        );
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        departmentIdField.setText("");

        departmentNameField.setText("");

        searchField.setText("");

        departmentTable.clearSelection();

        departmentNameField.requestFocus();
    }

    // =====================================================
    // CREATE BUTTON
    // =====================================================

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
                        14
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        135,
                        42
                )
        );

        return button;
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    DepartmentManagement frame =
                            new DepartmentManagement();

                    frame.setVisible(true);
                }
        );
    }
}