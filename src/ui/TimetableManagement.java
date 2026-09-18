package com.faculty.management.ui;

import com.faculty.management.dao.TimetableDAO;
import com.faculty.management.db.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TimetableManagement extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color PRIMARY = new Color(37, 99, 235);
    private final Color PRIMARY_DARK = new Color(30, 64, 175);
    private final Color BG = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(31, 41, 55);
    private final Color MUTED = new Color(107, 114, 128);
    private final Color BORDER = new Color(220, 224, 230);
    private final Color SUCCESS = new Color(22, 163, 74);
    private final Color DANGER = new Color(220, 38, 38);

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JComboBox<String> facultyCombo;
    private JComboBox<String> subjectCombo;
    private JComboBox<String> dayCombo;
    private JComboBox<String> timeCombo;
    private JTextField roomField;

    private JTable timetableTable;
    private DefaultTableModel tableModel;

    private final TimetableDAO timetableDAO = new TimetableDAO();

    private int selectedTimetableId = -1;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TimetableManagement() {

        setTitle("Timetable Management");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buildUI();
        loadFacultyCombo();
        loadSubjectCombo();
        loadTimetable();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG);

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(WHITE);
        header.setBorder(new EmptyBorder(18, 25, 18, 25));

        JLabel titleLabel = new JLabel("Timetable Management");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        titleLabel.setForeground(TEXT);

        JLabel subtitleLabel = new JLabel(
                "Create and manage faculty weekly timetable"
        );
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(MUTED);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(WHITE);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        header.add(titlePanel, BorderLayout.WEST);

        mainPanel.add(header, BorderLayout.NORTH);

        // -----------------------------------------------------
        // CENTER
        // -----------------------------------------------------

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(BG);
        centerPanel.setBorder(new EmptyBorder(18, 20, 20, 20));

        // -----------------------------------------------------
        // FORM CARD
        // -----------------------------------------------------

        JPanel formCard = new JPanel(new BorderLayout());
        formCard.setBackground(WHITE);
        formCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(18, 20, 18, 20)
                )
        );

        JLabel formTitle = new JLabel("Create / Update Timetable");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        formTitle.setForeground(TEXT);

        formCard.add(formTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 5, 15, 12));
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        // Faculty
        JPanel facultyPanel = createFieldPanel("Faculty");
        facultyCombo = new JComboBox<>();
        styleCombo(facultyCombo);
        facultyPanel.add(facultyCombo);

        // Subject
        JPanel subjectPanel = createFieldPanel("Subject");
        subjectCombo = new JComboBox<>();
        styleCombo(subjectCombo);
        subjectPanel.add(subjectCombo);

        // Day
        JPanel dayPanel = createFieldPanel("Day");
        dayCombo = new JComboBox<>(new String[]{
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        });
        styleCombo(dayCombo);
        dayPanel.add(dayCombo);

        // Time
        JPanel timePanel = createFieldPanel("Time Slot");
        timeCombo = new JComboBox<>(new String[]{
                "08:00 AM - 09:00 AM",
                "09:00 AM - 10:00 AM",
                "10:00 AM - 11:00 AM",
                "11:00 AM - 12:00 PM",
                "01:00 PM - 02:00 PM",
                "02:00 PM - 03:00 PM",
                "03:00 PM - 04:00 PM"
        });
        styleCombo(timeCombo);
        timePanel.add(timeCombo);

        // Room
        JPanel roomPanel = createFieldPanel("Room / Lab");
        roomField = new JTextField();
        styleTextField(roomField);
        roomPanel.add(roomField);

        formPanel.add(facultyPanel);
        formPanel.add(subjectPanel);
        formPanel.add(dayPanel);
        formPanel.add(timePanel);
        formPanel.add(roomPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0)
        );
        buttonPanel.setBackground(WHITE);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton addButton = createButton(
                "Add Timetable",
                PRIMARY
        );

        JButton updateButton = createButton(
                "Update",
                SUCCESS
        );

        JButton deleteButton = createButton(
                "Delete",
                DANGER
        );

        JButton clearButton = createButton(
                "Clear",
                new Color(107, 114, 128)
        );

        JButton refreshButton = createButton(
                "Refresh",
                PRIMARY_DARK
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        JPanel formBottom = new JPanel(new BorderLayout());
        formBottom.setBackground(WHITE);

        formBottom.add(formPanel, BorderLayout.CENTER);
        formBottom.add(buttonPanel, BorderLayout.SOUTH);

        formCard.add(formBottom, BorderLayout.CENTER);

        centerPanel.add(formCard, BorderLayout.NORTH);

        // -----------------------------------------------------
        // TABLE CARD
        // -----------------------------------------------------

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(WHITE);
        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        new EmptyBorder(15, 15, 15, 15)
                )
        );

        JLabel tableTitle = new JLabel("Faculty Timetable");
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        tableTitle.setForeground(TEXT);

        tableCard.add(tableTitle, BorderLayout.NORTH);

        // Table
        String[] columns = {
                "ID",
                "Faculty",
                "Subject",
                "Day",
                "Start Time",
                "End Time",
                "Room / Lab"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {
                return false;
            }
        };

        timetableTable = new JTable(tableModel);

        timetableTable.setRowHeight(38);
        timetableTable.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        timetableTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        timetableTable.getTableHeader().setBackground(
                new Color(239, 242, 247)
        );

        timetableTable.getTableHeader().setForeground(TEXT);

        timetableTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        timetableTable.setGridColor(BORDER);

        JScrollPane scrollPane =
                new JScrollPane(timetableTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(BORDER)
        );

        tableCard.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(tableCard, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // -----------------------------------------------------
        // BUTTON ACTIONS
        // -----------------------------------------------------

        addButton.addActionListener(e -> addTimetable());

        updateButton.addActionListener(e -> updateTimetable());

        deleteButton.addActionListener(e -> deleteTimetable());

        clearButton.addActionListener(e -> clearFields());

        refreshButton.addActionListener(e -> {

            loadFacultyCombo();
            loadSubjectCombo();
            loadTimetable();

        });

        // -----------------------------------------------------
        // TABLE ROW CLICK
        // -----------------------------------------------------

        timetableTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                timetableTable.getSelectedRow();

                        if (row != -1) {

                            selectTableRow(row);
                        }
                    }
                });
    }

    // =========================================================
    // FIELD PANEL
    // =========================================================

    private JPanel createFieldPanel(String labelText) {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        panel.setBackground(WHITE);

        JLabel label = new JLabel(labelText);

        label.setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        label.setForeground(TEXT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));

        return panel;
    }

    // =========================================================
    // STYLE COMBO
    // =========================================================

    private void styleCombo(JComboBox<String> combo) {

        combo.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        combo.setBackground(WHITE);

        combo.setPreferredSize(
                new Dimension(180, 34)
        );
    }

    // =========================================================
    // STYLE TEXT FIELD
    // =========================================================

    private void styleTextField(JTextField field) {

        field.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        field.setPreferredSize(
                new Dimension(180, 34)
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("SansSerif", Font.BOLD, 13)
        );

        button.setForeground(WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setPreferredSize(
                new Dimension(125, 36)
        );

        return button;
    }

    // =========================================================
    // LOAD FACULTY COMBO
    // =========================================================

    private void loadFacultyCombo() {

        facultyCombo.removeAllItems();

        String sql =
                "SELECT faculty_id, faculty_name " +
                        "FROM faculty " +
                        "ORDER BY faculty_name";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                int id =
                        result.getInt("faculty_id");

                String name =
                        result.getString("faculty_name");

                facultyCombo.addItem(
                        id + " - " + name
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load faculty records.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD SUBJECT COMBO
    // =========================================================

    private void loadSubjectCombo() {

        subjectCombo.removeAllItems();

        String sql =
                "SELECT subject_id, subject_name " +
                        "FROM subjects " +
                        "ORDER BY subject_name";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                int id =
                        result.getInt("subject_id");

                String name =
                        result.getString("subject_name");

                subjectCombo.addItem(
                        id + " - " + name
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load subject records.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET ID FROM COMBO
    // =========================================================

    private int getSelectedId(
            JComboBox<String> combo) {

        if (combo.getSelectedItem() == null) {
            return -1;
        }

        String value =
                combo.getSelectedItem().toString();

        try {

            return Integer.parseInt(
                    value.split(" - ", 2)[0]
            );

        } catch (Exception e) {

            return -1;
        }
    }

    // =========================================================
    // ADD TIMETABLE
    // =========================================================

    private void addTimetable() {

        int facultyId =
                getSelectedId(facultyCombo);

        int subjectId =
                getSelectedId(subjectCombo);

        String day =
                dayCombo.getSelectedItem().toString();

        String room =
                roomField.getText().trim();

        if (facultyId == -1) {

            showWarning("Please select a faculty.");
            return;
        }

        if (subjectId == -1) {

            showWarning("Please select a subject.");
            return;
        }

        if (room.isEmpty()) {

            showWarning("Please enter room / lab.");
            return;
        }

        String[] time =
                getTimeValues();

        boolean success =
                timetableDAO.addTimetable(
                        facultyId,
                        subjectId,
                        day,
                        time[0],
                        time[1],
                        room
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Timetable added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();
            loadTimetable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add timetable.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE TIMETABLE
    // =========================================================

    private void updateTimetable() {

        if (selectedTimetableId == -1) {

            showWarning(
                    "Please select a timetable record first."
            );

            return;
        }

        int facultyId =
                getSelectedId(facultyCombo);

        int subjectId =
                getSelectedId(subjectCombo);

        String day =
                dayCombo.getSelectedItem().toString();

        String room =
                roomField.getText().trim();

        if (facultyId == -1 || subjectId == -1) {

            showWarning(
                    "Please select faculty and subject."
            );

            return;
        }

        if (room.isEmpty()) {

            showWarning("Please enter room / lab.");
            return;
        }

        String[] time =
                getTimeValues();

        boolean success =
                timetableDAO.updateTimetable(
                        selectedTimetableId,
                        facultyId,
                        subjectId,
                        day,
                        time[0],
                        time[1],
                        room
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Timetable updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();
            loadTimetable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update timetable.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE TIMETABLE
    // =========================================================

    private void deleteTimetable() {

        if (selectedTimetableId == -1) {

            showWarning(
                    "Please select a timetable record first."
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this timetable?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success =
                timetableDAO.deleteTimetable(
                        selectedTimetableId
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Timetable deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();
            loadTimetable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete timetable.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET TIME VALUES
    // =========================================================

    private String[] getTimeValues() {

        String selected =
                timeCombo.getSelectedItem().toString();

        switch (selected) {

            case "08:00 AM - 09:00 AM":
                return new String[]{
                        "08:00:00",
                        "09:00:00"
                };

            case "09:00 AM - 10:00 AM":
                return new String[]{
                        "09:00:00",
                        "10:00:00"
                };

            case "10:00 AM - 11:00 AM":
                return new String[]{
                        "10:00:00",
                        "11:00:00"
                };

            case "11:00 AM - 12:00 PM":
                return new String[]{
                        "11:00:00",
                        "12:00:00"
                };

            case "01:00 PM - 02:00 PM":
                return new String[]{
                        "13:00:00",
                        "14:00:00"
                };

            case "02:00 PM - 03:00 PM":
                return new String[]{
                        "14:00:00",
                        "15:00:00"
                };

            case "03:00 PM - 04:00 PM":
                return new String[]{
                        "15:00:00",
                        "16:00:00"
                };

            default:
                return new String[]{
                        "08:00:00",
                        "09:00:00"
                };
        }
    }

    // =========================================================
    // LOAD TIMETABLE TABLE
    // =========================================================

    private void loadTimetable() {

        tableModel.setRowCount(0);

        for (
                TimetableDAO.TimetableRecord record :
                timetableDAO.getAllTimetable()
        ) {

            tableModel.addRow(
                    new Object[]{
                            record.getTimetableId(),
                            record.getFacultyName(),
                            record.getSubjectName(),
                            record.getDay(),
                            formatTime(record.getStartTime()),
                            formatTime(record.getEndTime()),
                            record.getRoomNo()
                    }
            );
        }
    }

    // =========================================================
    // FORMAT TIME
    // =========================================================

    private String formatTime(String time) {

        try {

            String[] parts =
                    time.split(":");

            int hour =
                    Integer.parseInt(parts[0]);

            int minute =
                    Integer.parseInt(parts[1]);

            String amPm =
                    hour >= 12 ? "PM" : "AM";

            int displayHour =
                    hour % 12;

            if (displayHour == 0) {
                displayHour = 12;
            }

            return String.format(
                    "%02d:%02d %s",
                    displayHour,
                    minute,
                    amPm
            );

        } catch (Exception e) {

            return time;
        }
    }

    // =========================================================
    // SELECT TABLE ROW
    // =========================================================

    private void selectTableRow(int row) {

        selectedTimetableId =
                Integer.parseInt(
                        timetableTable
                                .getValueAt(row, 0)
                                .toString()
                );

        String faculty =
                timetableTable
                        .getValueAt(row, 1)
                        .toString();

        String subject =
                timetableTable
                        .getValueAt(row, 2)
                        .toString();

        String day =
                timetableTable
                        .getValueAt(row, 3)
                        .toString();

        String start =
                timetableTable
                        .getValueAt(row, 4)
                        .toString();

        String end =
                timetableTable
                        .getValueAt(row, 5)
                        .toString();

        String room =
                timetableTable
                        .getValueAt(row, 6)
                        .toString();

        selectComboByName(
                facultyCombo,
                faculty
        );

        selectComboByName(
                subjectCombo,
                subject
        );

        dayCombo.setSelectedItem(day);

        String timeSlot =
                start + " - " + end;

        timeCombo.setSelectedItem(timeSlot);

        roomField.setText(room);
    }

    // =========================================================
    // SELECT COMBO BY NAME
    // =========================================================

    private void selectComboByName(
            JComboBox<String> combo,
            String name) {

        for (int i = 0;
             i < combo.getItemCount();
             i++) {

            String item =
                    combo.getItemAt(i);

            if (item.endsWith(" - " + name)) {

                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        selectedTimetableId = -1;

        if (facultyCombo.getItemCount() > 0) {
            facultyCombo.setSelectedIndex(0);
        }

        if (subjectCombo.getItemCount() > 0) {
            subjectCombo.setSelectedIndex(0);
        }

        dayCombo.setSelectedIndex(0);
        timeCombo.setSelectedIndex(0);

        roomField.setText("");

        timetableTable.clearSelection();
    }

    // =========================================================
    // WARNING
    // =========================================================

    private void showWarning(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation",
                JOptionPane.WARNING_MESSAGE
        );
    }
}