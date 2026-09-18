package com.faculty.management.ui;

import com.faculty.management.dao.TimetableDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyTimetable extends JFrame {

    private int facultyId;

    private TimetableDAO timetableDAO;

    private JTable todayTable;
    private JTable weeklyTable;

    private DefaultTableModel todayModel;
    private DefaultTableModel weeklyModel;

    private JLabel titleLabel;
    private JLabel dateLabel;

    private JButton todayButton;
    private JButton weeklyButton;
    private JButton refreshButton;
    private JButton closeButton;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    // Fixed time slots
    private final String[] timeSlots = {
            "08:00 AM - 09:00 AM",
            "09:00 AM - 10:00 AM",
            "10:00 AM - 11:00 AM",
            "11:00 AM - 12:00 PM",
            "01:00 PM - 02:00 PM",
            "02:00 PM - 03:00 PM",
            "03:00 PM - 04:00 PM"
    };

    private final String[] days = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
    };

    public FacultyTimetable() {
        this(1);
    }

    public FacultyTimetable(int facultyId) {

        this.facultyId = facultyId;
        timetableDAO = new TimetableDAO();

        setTitle("My Timetable");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        createUI();
        loadTodayTimetable();
        loadWeeklyTimetable();
    }

    private void createUI() {

        setLayout(new BorderLayout());

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(35, 55, 80));
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 25, 15, 25)
        );

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titleLabel = new JLabel("My Timetable");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        dateLabel = new JLabel();
        dateLabel.setForeground(new Color(220, 225, 230));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        updateDate();

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(dateLabel);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        // =========================
        // TOP BUTTONS
        // =========================

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.setOpaque(false);

        todayButton = new JButton("Today's Timetable");
        weeklyButton = new JButton("Weekly Timetable");
        refreshButton = new JButton("Refresh");
        closeButton = new JButton("Close");

        styleButton(todayButton);
        styleButton(weeklyButton);
        styleButton(refreshButton);
        styleButton(closeButton);

        todayButton.addActionListener(e -> showToday());
        weeklyButton.addActionListener(e -> showWeekly());

        refreshButton.addActionListener(e -> {
            loadTodayTimetable();
            loadWeeklyTimetable();

            JOptionPane.showMessageDialog(
                    this,
                    "Timetable refreshed successfully.",
                    "Refresh",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(todayButton);
        buttonPanel.add(weeklyButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        headerPanel.add(buttonPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // =========================
        // CONTENT PANEL
        // =========================

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        createTodayPanel();
        createWeeklyPanel();

        add(contentPanel, BorderLayout.CENTER);

        // Default screen
        showToday();
    }

    // =========================
    // TODAY PANEL
    // =========================

    private void createTodayPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel heading = new JLabel("Today's Classes");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(35, 55, 80));

        panel.add(heading, BorderLayout.NORTH);

        String[] columns = {
                "Time",
                "Subject",
                "Room / Lab"
        };

        todayModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        todayTable = new JTable(todayModel);

        styleTable(todayTable);

        todayTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(220);

        todayTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(400);

        todayTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(todayTable);
        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(15, 0, 0, 0)
        );

        panel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(panel, "TODAY");
    }

    // =========================
    // WEEKLY PANEL
    // =========================

    private void createWeeklyPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel heading = new JLabel("Weekly Timetable");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(35, 55, 80));

        panel.add(heading, BorderLayout.NORTH);

        String[] columns = {
                "Time",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        weeklyModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        weeklyTable = new JTable(weeklyModel);

        styleTable(weeklyTable);

        weeklyTable.setRowHeight(65);

        weeklyTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(170);

        for (int i = 1; i <= 6; i++) {
            weeklyTable.getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(145);
        }

        // Wrap text inside cells
        weeklyTable.setDefaultRenderer(
                Object.class,
                new TimetableCellRenderer()
        );

        JScrollPane scrollPane = new JScrollPane(weeklyTable);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(15, 0, 0, 0)
        );

        panel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(panel, "WEEKLY");
    }

    // =========================
    // LOAD TODAY TIMETABLE
    // =========================

    private void loadTodayTimetable() {

        todayModel.setRowCount(0);

        List<TimetableDAO.TimetableRecord> records =
                timetableDAO.getTodayTimetable(facultyId);

        if (records == null || records.isEmpty()) {

            todayModel.addRow(new Object[]{
                    "No class",
                    "No classes scheduled for today.",
                    "-"
            });

            return;
        }

        for (TimetableDAO.TimetableRecord record : records) {

            String time =
                    formatTime(record.getStartTime())
                            + " - "
                            + formatTime(record.getEndTime());

            String room = record.getRoomNo();

            if (room == null || room.trim().isEmpty()) {
                room = "-";
            }

            todayModel.addRow(new Object[]{
                    time,
                    record.getSubjectName(),
                    room
            });
        }
    }

    // =========================
    // LOAD WEEKLY TIMETABLE
    // =========================

    private void loadWeeklyTimetable() {

        weeklyModel.setRowCount(0);

        List<TimetableDAO.TimetableRecord> records =
                timetableDAO.getFacultyWeeklyTimetable(facultyId);

        /*
         * Map:
         * Day + Start Time -> Timetable Record
         */
        Map<String, TimetableDAO.TimetableRecord> timetableMap =
                new HashMap<>();

        if (records != null) {

            for (TimetableDAO.TimetableRecord record : records) {

                String key =
                        record.getDay().trim().toLowerCase()
                                + "_"
                                + normalizeTime(record.getStartTime());

                timetableMap.put(key, record);
            }
        }

        // Create rows for all fixed time slots
        for (int i = 0; i < timeSlots.length; i++) {

            Object[] row = new Object[7];

            row[0] = timeSlots[i];

            String startTime = getStartTimeFromSlot(i);

            for (int j = 0; j < days.length; j++) {

                String key =
                        days[j].toLowerCase()
                                + "_"
                                + startTime;

                TimetableDAO.TimetableRecord record =
                        timetableMap.get(key);

                if (record != null) {

                    String subject =
                            record.getSubjectName();

                    String room =
                            record.getRoomNo();

                    if (room == null || room.trim().isEmpty()) {
                        room = "-";
                    }

                    row[j + 1] =
                            "<html><b>"
                                    + escapeHtml(subject)
                                    + "</b><br>"
                                    + "Room: "
                                    + escapeHtml(room)
                                    + "</html>";

                } else {

                    row[j + 1] = "-";
                }
            }

            weeklyModel.addRow(row);
        }
    }

    // =========================
    // SHOW TODAY
    // =========================

    private void showToday() {

        cardLayout.show(contentPanel, "TODAY");

        todayButton.setBackground(new Color(50, 90, 140));
        weeklyButton.setBackground(new Color(80, 80, 80));
    }

    // =========================
    // SHOW WEEKLY
    // =========================

    private void showWeekly() {

        cardLayout.show(contentPanel, "WEEKLY");

        weeklyButton.setBackground(new Color(50, 90, 140));
        todayButton.setBackground(new Color(80, 80, 80));
    }

    // =========================
    // UPDATE DATE
    // =========================

    private void updateDate() {

        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");

        dateLabel.setText(today.format(formatter));
    }

    // =========================
    // FORMAT TIME
    // =========================

    private String formatTime(String time) {

        if (time == null || time.trim().isEmpty()) {
            return "-";
        }

        try {

            String[] parts = time.split(":");

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            String amPm = hour >= 12 ? "PM" : "AM";

            int displayHour = hour % 12;

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

    // =========================
    // NORMALIZE TIME
    // =========================

    private String normalizeTime(String time) {

        if (time == null || time.trim().isEmpty()) {
            return "";
        }

        String[] parts = time.split(":");

        if (parts.length >= 2) {

            return String.format(
                    "%02d:%02d",
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])
            );
        }

        return time;
    }

    // =========================
    // GET START TIME
    // =========================

    private String getStartTimeFromSlot(int index) {

        switch (index) {

            case 0:
                return "08:00";

            case 1:
                return "09:00";

            case 2:
                return "10:00";

            case 3:
                return "11:00";

            case 4:
                return "13:00";

            case 5:
                return "14:00";

            case 6:
                return "15:00";

            default:
                return "";
        }
    }

    // =========================
    // BUTTON STYLE
    // =========================

    private void styleButton(JButton button) {

        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(80, 80, 80));
        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        9, 15, 9, 15
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    // =========================
    // TABLE STYLE
    // =========================

    private void styleTable(JTable table) {

        table.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        table.setRowHeight(45);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setGridColor(
                new Color(210, 215, 220)
        );

        table.setShowGrid(true);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        table.getTableHeader().setBackground(
                new Color(35, 55, 80)
        );

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 42)
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < table.getColumnCount(); i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }
    }

    // =========================
    // HTML ESCAPE
    // =========================

    private String escapeHtml(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // =========================
    // CUSTOM CELL RENDERER
    // =========================

    private static class TimetableCellRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

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

            setVerticalAlignment(
                    SwingConstants.CENTER
            );

            setFont(
                    new Font("Segoe UI", Font.PLAIN, 13)
            );

            return this;
        }
    }

    // =========================
    // MAIN METHOD FOR DIRECT TEST
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            FacultyTimetable timetable =
                    new FacultyTimetable(1);

            timetable.setVisible(true);
        });
    }
}