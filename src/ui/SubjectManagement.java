package com.faculty.management.ui;

import com.faculty.management.dao.SubjectDAO;
import com.faculty.management.model.Subject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SubjectManagement extends JFrame {

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

    private JTextField subjectIdField;
    private JTextField subjectCodeField;
    private JTextField subjectNameField;
    private JTextField departmentIdField;
    private JTextField semesterField;
    private JTextField hoursField;
    private JTextField searchField;

    private JTable subjectTable;
    private DefaultTableModel tableModel;

    private SubjectDAO subjectDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SubjectManagement() {

        subjectDAO = new SubjectDAO();

        setTitle(
                "Faculty Duty & Workload Management System - Subject Management"
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

        loadSubjects();
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
                        "Subject Management"
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
                        "Add, update, search and manage subject records"
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
        // CENTER
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
        // SUBJECT DETAILS CARD
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
        // HEADING
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
                        "Subject Details"
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
                        "Enter subject information below"
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

        // Subject ID
        JPanel idPanel =
                createFieldPanel(
                        "Subject ID"
                );

        subjectIdField =
                getFieldFromPanel(
                        idPanel
                );

        subjectIdField.setEditable(false);

        subjectIdField.setBackground(
                new Color(
                        240,
                        243,
                        247
                )
        );

        formArea.add(idPanel);

        // Subject Code
        JPanel codePanel =
                createFieldPanel(
                        "Subject Code"
                );

        subjectCodeField =
                getFieldFromPanel(
                        codePanel
                );

        formArea.add(codePanel);

        // Subject Name
        JPanel namePanel =
                createFieldPanel(
                        "Subject Name"
                );

        subjectNameField =
                getFieldFromPanel(
                        namePanel
                );

        formArea.add(namePanel);

        // Department ID
        JPanel departmentPanel =
                createFieldPanel(
                        "Department ID"
                );

        departmentIdField =
                getFieldFromPanel(
                        departmentPanel
                );

        formArea.add(departmentPanel);

        // Semester
        JPanel semesterPanel =
                createFieldPanel(
                        "Semester"
                );

        semesterField =
                getFieldFromPanel(
                        semesterPanel
                );

        formArea.add(semesterPanel);

        // Hours
        JPanel hoursPanel =
                createFieldPanel(
                        "Hours / Week"
                );

        hoursField =
                getFieldFromPanel(
                        hoursPanel
                );

        formArea.add(hoursPanel);

        // Empty space
        JPanel emptyPanel1 =
                new JPanel();

        emptyPanel1.setBackground(
                Color.WHITE
        );

        formArea.add(emptyPanel1);

        JPanel emptyPanel2 =
                new JPanel();

        emptyPanel2.setBackground(
                Color.WHITE
        );

        formArea.add(emptyPanel2);

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
                        "Add Subject",
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
                e -> addSubject()
        );

        updateButton.addActionListener(
                e -> updateSubject()
        );

        deleteButton.addActionListener(
                e -> deleteSubject()
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
                        "Subject Records"
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
        // SEARCH
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
                e -> searchSubjects()
        );

        viewAllButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadSubjects();
                }
        );

        searchField.addActionListener(
                e -> searchSubjects()
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
                                "Subject ID",
                                "Code",
                                "Subject Name",
                                "Department",
                                "Semester",
                                "Hours / Week"
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

        subjectTable =
                new JTable(
                        tableModel
                );

        subjectTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subjectTable.setRowHeight(
                30
        );

        subjectTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        subjectTable.setShowGrid(
                true
        );

        subjectTable.setGridColor(
                new Color(
                        225,
                        230,
                        236
                )
        );

        subjectTable.setBackground(
                Color.WHITE
        );

        subjectTable.setSelectionBackground(
                new Color(
                        218,
                        231,
                        247
                )
        );

        subjectTable.setSelectionForeground(
                TEXT_COLOR
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader tableHeader =
                subjectTable.getTableHeader();

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

        subjectTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        subjectTable
                .getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        subjectTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        subjectTable
                .getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        subjectTable
                .getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        subjectTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        subjectTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);

        subjectTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(250);

        subjectTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        subjectTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(90);

        subjectTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(110);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        subjectTable
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

        subjectTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        subjectTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    loadSelectedSubject(
                                            row
                                    );
                                }
                            }
                        }
                );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        subjectTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    subjectTable
                                            .getSelectedRow();

                            if (row >= 0) {

                                loadSelectedSubject(
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
    // ADD SUBJECT
    // =====================================================

    private void addSubject() {

        try {

            if (!validateInput(false)) {
                return;
            }

            Subject subject =
                    new Subject();

            subject.setSubjectCode(
                    subjectCodeField
                            .getText()
                            .trim()
            );

            subject.setSubjectName(
                    subjectNameField
                            .getText()
                            .trim()
            );

            subject.setDepartmentId(
                    Integer.parseInt(
                            departmentIdField
                                    .getText()
                                    .trim()
                    )
            );

            subject.setSemester(
                    Integer.parseInt(
                            semesterField
                                    .getText()
                                    .trim()
                    )
            );

            subject.setHoursPerWeek(
                    Integer.parseInt(
                            hoursField
                                    .getText()
                                    .trim()
                    )
            );

            if (subjectDAO.addSubject(subject)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Subject added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadSubjects();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add subject.",
                        "Add Subject",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department ID, semester and hours must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add subject.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE SUBJECT
    // =====================================================

    private void updateSubject() {

        try {

            if (!validateInput(true)) {
                return;
            }

            Subject subject =
                    new Subject();

            subject.setSubjectId(
                    Integer.parseInt(
                            subjectIdField
                                    .getText()
                                    .trim()
                    )
            );

            subject.setSubjectCode(
                    subjectCodeField
                            .getText()
                            .trim()
            );

            subject.setSubjectName(
                    subjectNameField
                            .getText()
                            .trim()
            );

            subject.setDepartmentId(
                    Integer.parseInt(
                            departmentIdField
                                    .getText()
                                    .trim()
                    )
            );

            subject.setSemester(
                    Integer.parseInt(
                            semesterField
                                    .getText()
                                    .trim()
                    )
            );

            subject.setHoursPerWeek(
                    Integer.parseInt(
                            hoursField
                                    .getText()
                                    .trim()
                    )
            );

            if (subjectDAO.updateSubject(subject)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Subject updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadSubjects();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed.",
                        "Update Subject",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department ID, semester and hours must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update subject.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE SUBJECT
    // =====================================================

    private void deleteSubject() {

        try {

            String idText =
                    subjectIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a subject first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(idText);

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this subject?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (subjectDAO.deleteSubject(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Subject deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadSubjects();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete subject.",
                            "Delete Subject",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Subject ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete subject.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD SUBJECTS
    // =====================================================

    private void loadSubjects() {

        try {

            tableModel.setRowCount(0);

            List<Subject> subjects =
                    subjectDAO.getAllSubjects();

            for (Subject subject :
                    subjects) {

                addSubjectToTable(
                        subject
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load subject records.\n\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SEARCH SUBJECTS
    // =====================================================

    private void searchSubjects() {

        try {

            String search =
                    searchField
                            .getText()
                            .trim();

            if (search.isEmpty()) {

                loadSubjects();

                return;
            }

            tableModel.setRowCount(0);

            List<Subject> subjects =
                    subjectDAO.searchSubjects(search);

            for (Subject subject :
                    subjects) {

                addSubjectToTable(
                        subject
                );
            }

            if (subjects.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No subject found for: "
                                + search,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search subjects.\n\n"
                            + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD SUBJECT TO TABLE
    // =====================================================

    private void addSubjectToTable(
            Subject subject
    ) {

        tableModel.addRow(
                new Object[]{
                        subject.getSubjectId(),
                        subject.getSubjectCode(),
                        subject.getSubjectName(),
                        subject.getDepartmentId(),
                        subject.getSemester(),
                        subject.getHoursPerWeek()
                }
        );
    }

    // =====================================================
    // LOAD SELECTED SUBJECT
    // =====================================================

    private void loadSelectedSubject(
            int row
    ) {

        subjectIdField.setText(
                getTableValue(row, 0)
        );

        subjectCodeField.setText(
                getTableValue(row, 1)
        );

        subjectNameField.setText(
                getTableValue(row, 2)
        );

        departmentIdField.setText(
                getTableValue(row, 3)
        );

        semesterField.setText(
                getTableValue(row, 4)
        );

        hoursField.setText(
                getTableValue(row, 5)
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
                subjectIdField
                        .getText()
                        .trim()
                        .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a subject first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (subjectCodeField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter subject code.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            subjectCodeField.requestFocus();

            return false;
        }

        if (subjectNameField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter subject name.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            subjectNameField.requestFocus();

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

        if (semesterField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter semester.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            semesterField.requestFocus();

            return false;
        }

        if (hoursField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter hours per week.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            hoursField.requestFocus();

            return false;
        }

        try {

            Integer.parseInt(
                    departmentIdField
                            .getText()
                            .trim()
            );

            int semester =
                    Integer.parseInt(
                            semesterField
                                    .getText()
                                    .trim()
                    );

            int hours =
                    Integer.parseInt(
                            hoursField
                                    .getText()
                                    .trim()
                    );

            if (semester <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semester must be greater than 0.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );

                semesterField.requestFocus();

                return false;
            }

            if (hours <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hours per week must be greater than 0.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );

                hoursField.requestFocus();

                return false;
            }

            if (update) {

                Integer.parseInt(
                        subjectIdField
                                .getText()
                                .trim()
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department ID, semester and hours must contain valid numbers.",
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

        subjectIdField.setText("");

        subjectCodeField.setText("");

        subjectNameField.setText("");

        departmentIdField.setText("");

        semesterField.setText("");

        hoursField.setText("");

        searchField.setText("");

        subjectTable.clearSelection();

        subjectCodeField.requestFocus();
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

                    SubjectManagement frame =
                            new SubjectManagement();

                    frame.setVisible(true);
                }
        );
    }
}