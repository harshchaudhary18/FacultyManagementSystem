package com.faculty.management.ui;

import com.faculty.management.dao.DutyDAO;
import com.faculty.management.model.Duty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DutyManagement extends JFrame {

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

    private JTextField dutyIdField;
    private JTextField dutyNameField;
    private JTextField dutyTypeField;
    private JTextField dutyDateField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField locationField;
    private JTextField searchField;

    private JTable dutyTable;
    private DefaultTableModel tableModel;

    private DutyDAO dutyDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DutyManagement() {

        dutyDAO = new DutyDAO();

        setTitle(
                "Faculty Duty & Workload Management System - Duty Management"
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

        loadDuties();
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
                        "Duty Management"
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
                        "Create, update, search and manage faculty duties"
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
        // DETAILS CARD
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
                        "Duty Details"
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
                        "Enter duty information below"
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
        // FORM
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

        // Duty ID
        JPanel idPanel =
                createFieldPanel(
                        "Duty ID"
                );

        dutyIdField =
                getFieldFromPanel(
                        idPanel
                );

        dutyIdField.setEditable(false);

        dutyIdField.setBackground(
                new Color(
                        240,
                        243,
                        247
                )
        );

        formArea.add(idPanel);

        // Duty Name
        JPanel namePanel =
                createFieldPanel(
                        "Duty Name"
                );

        dutyNameField =
                getFieldFromPanel(
                        namePanel
                );

        formArea.add(namePanel);

        // Duty Type
        JPanel typePanel =
                createFieldPanel(
                        "Duty Type"
                );

        dutyTypeField =
                getFieldFromPanel(
                        typePanel
                );

        formArea.add(typePanel);

        // Date
        JPanel datePanel =
                createFieldPanel(
                        "Date (YYYY-MM-DD)"
                );

        dutyDateField =
                getFieldFromPanel(
                        datePanel
                );

        formArea.add(datePanel);

        // Start Time
        JPanel startPanel =
                createFieldPanel(
                        "Start Time (HH:MM)"
                );

        startTimeField =
                getFieldFromPanel(
                        startPanel
                );

        formArea.add(startPanel);

        // End Time
        JPanel endPanel =
                createFieldPanel(
                        "End Time (HH:MM)"
                );

        endTimeField =
                getFieldFromPanel(
                        endPanel
                );

        formArea.add(endPanel);

        // Location
        JPanel locationPanel =
                createFieldPanel(
                        "Location"
                );

        locationField =
                getFieldFromPanel(
                        locationPanel
                );

        formArea.add(locationPanel);

        // Empty
        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setBackground(
                Color.WHITE
        );

        formArea.add(emptyPanel);

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
                        "Add Duty",
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
                e -> addDuty()
        );

        updateButton.addActionListener(
                e -> updateDuty()
        );

        deleteButton.addActionListener(
                e -> deleteDuty()
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
                        "Duty Records"
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
                e -> searchDuties()
        );

        viewAllButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadDuties();
                }
        );

        searchField.addActionListener(
                e -> searchDuties()
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
                                "ID",
                                "Duty Name",
                                "Type",
                                "Date",
                                "Start",
                                "End",
                                "Location"
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

        dutyTable =
                new JTable(
                        tableModel
                );

        dutyTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        dutyTable.setRowHeight(
                30
        );

        dutyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        dutyTable.setShowGrid(
                true
        );

        dutyTable.setGridColor(
                new Color(
                        225,
                        230,
                        236
                )
        );

        dutyTable.setBackground(
                Color.WHITE
        );

        dutyTable.setSelectionBackground(
                new Color(
                        218,
                        231,
                        247
                )
        );

        dutyTable.setSelectionForeground(
                TEXT_COLOR
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        JTableHeader tableHeader =
                dutyTable.getTableHeader();

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

        dutyTable
                .getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        dutyTable
                .getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        dutyTable
                .getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        dutyTable
                .getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        dutyTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(60);

        dutyTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        dutyTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(130);

        dutyTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        dutyTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(90);

        dutyTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(90);

        dutyTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(220);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        dutyTable
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

        dutyTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        dutyTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    loadSelectedDuty(
                                            row
                                    );
                                }
                            }
                        }
                );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        dutyTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    dutyTable
                                            .getSelectedRow();

                            if (row >= 0) {

                                loadSelectedDuty(
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
    // ADD
    // =====================================================

    private void addDuty() {

        try {

            if (!validateFields()) {
                return;
            }

            Duty duty =
                    createDutyFromFields();

            if (dutyDAO.addDuty(duty)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Duty added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadDuties();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add duty.",
                        "Add Duty",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Invalid Date / Time",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add duty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // UPDATE
    // =====================================================

    private void updateDuty() {

        try {

            String idText =
                    dutyIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a duty first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (!validateFields()) {
                return;
            }

            Duty duty =
                    createDutyFromFields();

            duty.setDutyId(
                    Integer.parseInt(
                            idText
                    )
            );

            if (dutyDAO.updateDuty(duty)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Duty updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadDuties();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed.",
                        "Update Duty",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Duty ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Invalid Date / Time",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update duty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // DELETE
    // =====================================================

    private void deleteDuty() {

        try {

            String idText =
                    dutyIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a duty first.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int dutyId =
                    Integer.parseInt(
                            idText
                    );

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this duty?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (dutyDAO.deleteDuty(dutyId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Duty deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadDuties();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete duty.",
                            "Delete Duty",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Duty ID.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete duty.\n\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD DUTIES
    // =====================================================

    private void loadDuties() {

        try {

            tableModel.setRowCount(0);

            List<Duty> duties =
                    dutyDAO.getAllDuties();

            for (Duty duty :
                    duties) {

                addDutyToTable(
                        duty
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load duty records.\n\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADD DUTY TO TABLE
    // =====================================================

    private void addDutyToTable(
            Duty duty
    ) {

        tableModel.addRow(
                new Object[]{
                        duty.getDutyId(),
                        duty.getDutyName(),
                        duty.getDutyType(),
                        duty.getDutyDate(),
                        formatTime(
                                duty.getStartTime()
                        ),
                        formatTime(
                                duty.getEndTime()
                        ),
                        duty.getLocation()
                }
        );
    }

    // =====================================================
    // FORMAT TIME
    // =====================================================

    private String formatTime(
            Time time
    ) {

        if (time == null) {
            return "";
        }

        String value =
                time.toString();

        if (value.length() >= 5) {

            return value.substring(
                    0,
                    5
            );
        }

        return value;
    }

    // =====================================================
    // SEARCH
    // =====================================================

    private void searchDuties() {

        try {

            String search =
                    searchField
                            .getText()
                            .trim();

            if (search.isEmpty()) {

                loadDuties();

                return;
            }

            tableModel.setRowCount(0);

            List<Duty> duties =
                    dutyDAO.searchDuties(
                            search
                    );

            for (Duty duty :
                    duties) {

                addDutyToTable(
                        duty
                );
            }

            if (duties.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No duty found for: "
                                + search,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to search duties.\n\n"
                            + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // VALIDATE
    // =====================================================

    private boolean validateFields() {

        if (dutyNameField
                .getText()
                .trim()
                .isEmpty()) {

            showValidation(
                    "Please enter duty name.",
                    dutyNameField
            );

            return false;
        }

        if (dutyTypeField
                .getText()
                .trim()
                .isEmpty()) {

            showValidation(
                    "Please enter duty type.",
                    dutyTypeField
            );

            return false;
        }

        if (dutyDateField
                .getText()
                .trim()
                .isEmpty()) {

            showValidation(
                    "Please enter duty date.",
                    dutyDateField
            );

            return false;
        }

        if (startTimeField
                .getText()
                .trim()
                .isEmpty()) {

            showValidation(
                    "Please enter start time.",
                    startTimeField
            );

            return false;
        }

        if (endTimeField
                .getText()
                .trim()
                .isEmpty()) {

            showValidation(
                    "Please enter end time.",
                    endTimeField
            );

            return false;
        }

        // Validate date
        try {

            Date.valueOf(
                    dutyDateField
                            .getText()
                            .trim()
            );

        } catch (IllegalArgumentException e) {

            showValidation(
                    "Date must be in YYYY-MM-DD format.",
                    dutyDateField
            );

            return false;
        }

        // Validate start time
        Time startTime;

        try {

            startTime =
                    Time.valueOf(
                            startTimeField
                                    .getText()
                                    .trim()
                                    + ":00"
                    );

        } catch (IllegalArgumentException e) {

            showValidation(
                    "Start time must be in HH:MM format.",
                    startTimeField
            );

            return false;
        }

        // Validate end time
        Time endTime;

        try {

            endTime =
                    Time.valueOf(
                            endTimeField
                                    .getText()
                                    .trim()
                                    + ":00"
                    );

        } catch (IllegalArgumentException e) {

            showValidation(
                    "End time must be in HH:MM format.",
                    endTimeField
            );

            return false;
        }

        // End time must be after start time
        if (!endTime.after(startTime)) {

            showValidation(
                    "End time must be after start time.",
                    endTimeField
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // CREATE DUTY
    // =====================================================

    private Duty createDutyFromFields() {

        Duty duty =
                new Duty();

        duty.setDutyName(
                dutyNameField
                        .getText()
                        .trim()
        );

        duty.setDutyType(
                dutyTypeField
                        .getText()
                        .trim()
        );

        duty.setDutyDate(
                Date.valueOf(
                        dutyDateField
                                .getText()
                                .trim()
                )
        );

        duty.setStartTime(
                Time.valueOf(
                        startTimeField
                                .getText()
                                .trim()
                                + ":00"
                )
        );

        duty.setEndTime(
                Time.valueOf(
                        endTimeField
                                .getText()
                                .trim()
                                + ":00"
                )
        );

        duty.setLocation(
                locationField
                        .getText()
                        .trim()
        );

        return duty;
    }

    // =====================================================
    // LOAD SELECTED DUTY
    // =====================================================

    private void loadSelectedDuty(
            int row
    ) {

        dutyIdField.setText(
                getTableValue(
                        row,
                        0
                )
        );

        dutyNameField.setText(
                getTableValue(
                        row,
                        1
                )
        );

        dutyTypeField.setText(
                getTableValue(
                        row,
                        2
                )
        );

        dutyDateField.setText(
                getTableValue(
                        row,
                        3
                )
        );

        startTimeField.setText(
                getTableValue(
                        row,
                        4
                )
        );

        endTimeField.setText(
                getTableValue(
                        row,
                        5
                )
        );

        locationField.setText(
                getTableValue(
                        row,
                        6
                )
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
    // VALIDATION MESSAGE
    // =====================================================

    private void showValidation(
            String message,
            JTextField field
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation",
                JOptionPane.WARNING_MESSAGE
        );

        field.requestFocus();
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        dutyIdField.setText("");

        dutyNameField.setText("");

        dutyTypeField.setText("");

        dutyDateField.setText("");

        startTimeField.setText("");

        endTimeField.setText("");

        locationField.setText("");

        searchField.setText("");

        dutyTable.clearSelection();

        dutyNameField.requestFocus();
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
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

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

                    DutyManagement frame =
                            new DutyManagement();

                    frame.setVisible(true);
                }
        );
    }
}