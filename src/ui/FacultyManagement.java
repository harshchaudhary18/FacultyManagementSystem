package com.faculty.management.ui;

import com.faculty.management.dao.FacultyDAO;
import com.faculty.management.model.Faculty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FacultyManagement extends JFrame {

    // ================= COLORS =================

    private final Color PRIMARY_COLOR =
            new Color(29, 48, 80);

    private final Color UPDATE_COLOR =
            new Color(45, 92, 145);

    private final Color ADD_COLOR =
            new Color(46, 125, 80);

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

    private JTextField facultyIdField;
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField designationField;
    private JTextField departmentIdField;

    private JTextField searchField;

    private JTable facultyTable;

    private DefaultTableModel tableModel;

    private FacultyDAO facultyDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FacultyManagement() {

        facultyDAO = new FacultyDAO();

        setTitle(
                "Faculty Duty & Workload Management System - Faculty Management"
        );

        setSize(1200, 750);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadFaculty();
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
                        "Faculty Management"
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
                        "Add, update, search and manage faculty records"
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
        // FACULTY DETAILS CARD
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
                                15,
                                30
                        )
                )
        );

        // =====================================================
        // DETAILS TITLE
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
                        "Faculty Details"
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
                        "Enter faculty information below"
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
                        new GridLayout(
                                2,
                                4,
                                20,
                                10
                        )
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

        // Faculty ID
        JPanel facultyIdPanel =
                createFieldPanel("Faculty ID");

        facultyIdField =
                getFieldFromPanel(
                        facultyIdPanel
                );

        facultyIdField.setEditable(false);

        facultyIdField.setBackground(
                new Color(
                        240,
                        243,
                        247
                )
        );

        formArea.add(
                facultyIdPanel
        );

        // User ID
        JPanel userIdPanel =
                createFieldPanel("User ID");

        userIdField =
                getFieldFromPanel(
                        userIdPanel
                );

        formArea.add(
                userIdPanel
        );

        // Name
        JPanel namePanel =
                createFieldPanel("Name");

        nameField =
                getFieldFromPanel(
                        namePanel
                );

        formArea.add(
                namePanel
        );

        // Email
        JPanel emailPanel =
                createFieldPanel("Email");

        emailField =
                getFieldFromPanel(
                        emailPanel
                );

        formArea.add(
                emailPanel
        );

        // Phone
        JPanel phonePanel =
                createFieldPanel("Phone");

        phoneField =
                getFieldFromPanel(
                        phonePanel
                );

        formArea.add(
                phonePanel
        );

        // Designation
        JPanel designationPanel =
                createFieldPanel("Designation");

        designationField =
                getFieldFromPanel(
                        designationPanel
                );

        formArea.add(
                designationPanel
        );

        // Department ID
        JPanel departmentPanel =
                createFieldPanel("Department ID");

        departmentIdField =
                getFieldFromPanel(
                        departmentPanel
                );

        formArea.add(
                departmentPanel
        );

        // Empty fourth position
        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setBackground(
                Color.WHITE
        );

        formArea.add(
                emptyPanel
        );

        detailsCard.add(
                formArea,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
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
                        "Add Faculty",
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
                e -> addFaculty()
        );

        updateButton.addActionListener(
                e -> updateFaculty()
        );

        deleteButton.addActionListener(
                e -> deleteFaculty()
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
                        "Faculty Records"
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
                new JLabel("Search:");

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
                        210,
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
                e -> searchFaculty()
        );

        viewAllButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadFaculty();
                }
        );

        searchField.addActionListener(
                e -> searchFaculty()
        );

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(viewAllButton);

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
                                "Faculty ID",
                                "User ID",
                                "Name",
                                "Email",
                                "Phone",
                                "Designation",
                                "Department ID"
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

        facultyTable =
                new JTable(tableModel);

        facultyTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        facultyTable.setRowHeight(30);

        facultyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        facultyTable.setShowGrid(true);

        facultyTable.setGridColor(
                new Color(
                        225,
                        230,
                        236
                )
        );

        facultyTable.setBackground(
                Color.WHITE
        );

        facultyTable.setSelectionBackground(
                new Color(
                        218,
                        231,
                        247
                )
        );

        facultyTable.setSelectionForeground(
                TEXT_COLOR
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader tableHeader =
                facultyTable.getTableHeader();

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
                        12
                )
        );

        tableHeader.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        // =====================================================
        // CENTER ALIGNMENT
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        facultyTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        facultyTable
                .getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        facultyTable
                .getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centerRenderer
                );

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        facultyTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(80);

        facultyTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(75);

        facultyTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(150);

        facultyTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(190);

        facultyTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(120);

        facultyTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(160);

        facultyTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(110);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        facultyTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );

        scrollPane.setPreferredSize(
                new Dimension(
                        1000,
                        220
                )
        );

        scrollPane.setMinimumSize(
                new Dimension(
                        700,
                        180
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        recordsCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        facultyTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        facultyTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    loadSelectedFaculty(
                                            row
                                    );
                                }
                            }
                        }
                );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        facultyTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    facultyTable
                                            .getSelectedRow();

                            if (row >= 0) {

                                loadSelectedFaculty(
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
                new JLabel(labelText);

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
                        200,
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
    // ADD
    // =====================================================

    private void addFaculty() {

        try {

            if (!validateInput(false)) {
                return;
            }

            Faculty faculty =
                    new Faculty();

            faculty.setUserId(
                    Integer.parseInt(
                            userIdField
                                    .getText()
                                    .trim()
                    )
            );

            faculty.setFacultyName(
                    nameField
                            .getText()
                            .trim()
            );

            faculty.setEmail(
                    emailField
                            .getText()
                            .trim()
            );

            faculty.setPhone(
                    phoneField
                            .getText()
                            .trim()
            );

            faculty.setDesignation(
                    designationField
                            .getText()
                            .trim()
            );

            faculty.setDepartmentId(
                    Integer.parseInt(
                            departmentIdField
                                    .getText()
                                    .trim()
                    )
            );

            if (facultyDAO.addFaculty(faculty)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Faculty added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadFaculty();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add faculty.",
                        "Add Faculty",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID and Department ID must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add faculty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE
    // =====================================================

    private void updateFaculty() {

        try {

            if (!validateInput(true)) {
                return;
            }

            Faculty faculty =
                    new Faculty();

            faculty.setFacultyId(
                    Integer.parseInt(
                            facultyIdField
                                    .getText()
                                    .trim()
                    )
            );

            faculty.setFacultyName(
                    nameField
                            .getText()
                            .trim()
            );

            faculty.setEmail(
                    emailField
                            .getText()
                            .trim()
            );

            faculty.setPhone(
                    phoneField
                            .getText()
                            .trim()
            );

            faculty.setDesignation(
                    designationField
                            .getText()
                            .trim()
            );

            faculty.setDepartmentId(
                    Integer.parseInt(
                            departmentIdField
                                    .getText()
                                    .trim()
                    )
            );

            if (facultyDAO.updateFaculty(faculty)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Faculty updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadFaculty();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed.",
                        "Update Faculty",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty ID and Department ID must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update faculty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    private void deleteFaculty() {

        try {

            String idText =
                    facultyIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a faculty member first.",
                        "Delete Faculty",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int facultyId =
                    Integer.parseInt(idText);

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this faculty member?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (facultyDAO
                        .deleteFaculty(facultyId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Faculty deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadFaculty();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete faculty.",
                            "Delete Faculty",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete faculty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD FACULTY
    // =====================================================

    private void loadFaculty() {

        try {

            tableModel.setRowCount(0);

            List<Faculty> list =
                    facultyDAO.getAllFaculty();

            for (Faculty faculty : list) {

                addFacultyToTable(
                        faculty
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load faculty records.\n\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SEARCH
    // =====================================================

    private void searchFaculty() {

        try {

            String name =
                    searchField
                            .getText()
                            .trim();

            if (name.isEmpty()) {

                loadFaculty();

                return;
            }

            tableModel.setRowCount(0);

            List<Faculty> list =
                    facultyDAO.searchFaculty(name);

            for (Faculty faculty : list) {

                addFacultyToTable(
                        faculty
                );
            }

            if (list.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No faculty found for: "
                                + name,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search faculty.\n\n"
                            + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD TO TABLE
    // =====================================================

    private void addFacultyToTable(
            Faculty faculty
    ) {

        tableModel.addRow(
                new Object[]{
                        faculty.getFacultyId(),
                        faculty.getUserId(),
                        faculty.getFacultyName(),
                        faculty.getEmail(),
                        faculty.getPhone(),
                        faculty.getDesignation(),
                        faculty.getDepartmentId()
                }
        );
    }

    // =====================================================
    // LOAD SELECTED
    // =====================================================

    private void loadSelectedFaculty(
            int row
    ) {

        facultyIdField.setText(
                getTableValue(row, 0)
        );

        userIdField.setText(
                getTableValue(row, 1)
        );

        nameField.setText(
                getTableValue(row, 2)
        );

        emailField.setText(
                getTableValue(row, 3)
        );

        phoneField.setText(
                getTableValue(row, 4)
        );

        designationField.setText(
                getTableValue(row, 5)
        );

        departmentIdField.setText(
                getTableValue(row, 6)
        );
    }

    // =====================================================
    // GET TABLE VALUE
    // =====================================================

    private String getTableValue(
            int row,
            int column
    ) {

        Object value =
                tableModel.getValueAt(
                        row,
                        column
                );

        return value == null
                ? ""
                : value.toString();
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    private boolean validateInput(
            boolean update
    ) {

        if (update &&
                facultyIdField
                        .getText()
                        .trim()
                        .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a faculty member first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (!update &&
                userIdField
                        .getText()
                        .trim()
                        .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter User ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            userIdField.requestFocus();

            return false;
        }

        if (nameField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter faculty name.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();

            return false;
        }

        if (emailField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter email.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            emailField.requestFocus();

            return false;
        }

        if (phoneField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter phone number.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return false;
        }

        if (designationField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter designation.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            designationField.requestFocus();

            return false;
        }

        if (departmentIdField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Department ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            departmentIdField.requestFocus();

            return false;
        }

        try {

            if (!update) {

                Integer.parseInt(
                        userIdField
                                .getText()
                                .trim()
                );
            }

            Integer.parseInt(
                    departmentIdField
                            .getText()
                            .trim()
            );

            if (update) {

                Integer.parseInt(
                        facultyIdField
                                .getText()
                                .trim()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID fields must contain valid numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        facultyIdField.setText("");

        userIdField.setText("");

        nameField.setText("");

        emailField.setText("");

        phoneField.setText("");

        designationField.setText("");

        departmentIdField.setText("");

        facultyTable.clearSelection();

        nameField.requestFocus();
    }

    // =====================================================
    // BUTTON
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
                        125,
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

                    FacultyManagement frame =
                            new FacultyManagement();

                    frame.setVisible(true);
                }
        );
    }
}