package com.faculty.management.ui;

import com.faculty.management.dao.FacultyDashboardDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacultyDashboard extends JFrame {

    // ================= FACULTY ID =================

    private int facultyId;

    // ================= DAO =================

    private FacultyDashboardDAO dashboardDAO;

    // ================= COLORS =================

    private final Color SIDEBAR_COLOR = new Color(29, 48, 80);
    private final Color ACTIVE_COLOR = new Color(61, 91, 132);
    private final Color HOVER_COLOR = new Color(48, 70, 105);

    private final Color TEXT_COLOR = new Color(29, 48, 80);
    private final Color BACKGROUND_COLOR = new Color(245, 248, 252);

    // ================= DASHBOARD LABELS =================

    private JLabel facultyNameLabel;

    private JLabel subjectCountLabel;
    private JLabel dutyCountLabel;
    private JLabel assignmentCountLabel;
    private JLabel workloadLabel;
    private JLabel pendingTaskLabel;

    // ================= CONSTRUCTORS =================

    public FacultyDashboard() {
        this(1);
    }

    public FacultyDashboard(int facultyId) {

        this.facultyId = facultyId;

        dashboardDAO = new FacultyDashboardDAO();

        setTitle("Faculty Duty & Workload Management System - Faculty");

        setSize(1200, 750);

        setMinimumSize(new Dimension(1000, 650));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadDashboardData();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setPreferredSize(new Dimension(270, 750));

        sidebar.setBackground(SIDEBAR_COLOR);

        // =====================================================
        // BRAND
        // =====================================================

        JPanel brandPanel = new JPanel();

        brandPanel.setBackground(SIDEBAR_COLOR);

        brandPanel.setLayout(
                new BoxLayout(brandPanel, BoxLayout.Y_AXIS)
        );

        brandPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        35, 25, 20, 20
                )
        );

        JLabel logo = new JLabel("FM");

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        JLabel facultyManagement = new JLabel(
                "<html>FACULTY<br>MANAGEMENT</html>"
        );

        facultyManagement.setForeground(Color.WHITE);

        facultyManagement.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        JLabel subtitle = new JLabel("Faculty Portal");

        subtitle.setForeground(
                new Color(210, 220, 235)
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        JLabel menuTitle = new JLabel("FACULTY MENU");

        menuTitle.setForeground(
                new Color(170, 190, 215)
        );

        menuTitle.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        brandPanel.add(logo);

        brandPanel.add(
                Box.createVerticalStrut(12)
        );

        brandPanel.add(facultyManagement);

        brandPanel.add(
                Box.createVerticalStrut(8)
        );

        brandPanel.add(subtitle);

        brandPanel.add(
                Box.createVerticalStrut(25)
        );

        brandPanel.add(menuTitle);

        sidebar.add(
                brandPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MENU
        // =====================================================

        JPanel menuPanel = new JPanel();

        menuPanel.setBackground(SIDEBAR_COLOR);

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 15, 0, 15
                )
        );

        // Dashboard
        addMenuButton(
                menuPanel,
                "D",
                "Dashboard",
                true,
                () -> {
                    // Already on dashboard
                    loadDashboardData();
                }
        );

        // My Profile
        addMenuButton(
                menuPanel,
                "P",
                "My Profile",
                false,
                this::openProfile
        );

        // My Subjects
        addMenuButton(
                menuPanel,
                "S",
                "My Subjects",
                false,
                this::showSubjectsInfo
        );

        // My Timetable
        addMenuButton(
                menuPanel,
                "T",
                "My Timetable",
                false,
                this::openTimetable
        );

        // My Workload
        addMenuButton(
                menuPanel,
                "W",
                "My Workload",
                false,
                this::openWorkload
        );

        // My Duties
        addMenuButton(
                menuPanel,
                "D",
                "My Duties",
                false,
                this::openDuties
        );

        // Duty Assignment
        addMenuButton(
                menuPanel,
                "A",
                "Duty Assignment",
                false,
                this::showAssignmentInfo
        );

        // Reports
        addMenuButton(
                menuPanel,
                "R",
                "Reports",
                false,
                this::showReportsInfo
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // USER PANEL
        // =====================================================

        JPanel userPanel =
                new JPanel(new BorderLayout());

        userPanel.setBackground(SIDEBAR_COLOR);

        userPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 25, 20
                )
        );

        JLabel userIcon =
                new JLabel("F");

        userIcon.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        userIcon.setForeground(Color.WHITE);

        userIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        userIcon.setPreferredSize(
                new Dimension(50, 50)
        );

        userIcon.setOpaque(true);

        userIcon.setBackground(
                new Color(61, 105, 165)
        );

        JPanel userText =
                new JPanel();

        userText.setBackground(
                SIDEBAR_COLOR
        );

        userText.setLayout(
                new BoxLayout(
                        userText,
                        BoxLayout.Y_AXIS
                )
        );

        userText.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 12, 0, 0
                )
        );

        facultyNameLabel =
                new JLabel("Faculty");

        facultyNameLabel.setForeground(
                Color.WHITE
        );

        facultyNameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        JLabel role =
                new JLabel("FACULTY");

        role.setForeground(
                new Color(170, 190, 215)
        );

        role.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        userText.add(facultyNameLabel);

        userText.add(
                Box.createVerticalStrut(5)
        );

        userText.add(role);

        userPanel.add(
                userIcon,
                BorderLayout.WEST
        );

        userPanel.add(
                userText,
                BorderLayout.CENTER
        );

        sidebar.add(
                userPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        JPanel contentPanel =
                new JPanel(new BorderLayout());

        contentPanel.setBackground(
                BACKGROUND_COLOR
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 25, 35
                )
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setBackground(Color.WHITE);

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel dashboardTitle =
                new JLabel("Faculty Dashboard");

        dashboardTitle.setForeground(
                TEXT_COLOR
        );

        dashboardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel dashboardSubtitle =
                new JLabel(
                        "Overview of your subjects, workload and duties"
                );

        dashboardSubtitle.setForeground(
                new Color(95, 110, 130)
        );

        dashboardSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        headingPanel.add(
                dashboardTitle
        );

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(
                dashboardSubtitle
        );

        // =====================================================
        // HEADER RIGHT
        // =====================================================

        JPanel headerRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                0
                        )
                );

        headerRight.setBackground(Color.WHITE);

        JLabel facultyLabel =
                new JLabel("Faculty");

        facultyLabel.setForeground(
                TEXT_COLOR
        );

        facultyLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        logoutButton.setForeground(Color.WHITE);

        logoutButton.setBackground(
                new Color(190, 65, 65)
        );

        logoutButton.setFocusPainted(false);

        logoutButton.setBorderPainted(false);

        logoutButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        logoutButton.setPreferredSize(
                new Dimension(125, 48)
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        headerRight.add(
                facultyLabel
        );

        headerRight.add(
                logoutButton
        );

        header.add(
                headingPanel,
                BorderLayout.WEST
        );

        header.add(
                headerRight,
                BorderLayout.EAST
        );

        contentPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // DASHBOARD CONTENT
        // =====================================================

        JPanel dashboard =
                new JPanel();

        dashboard.setBackground(
                BACKGROUND_COLOR
        );

        dashboard.setLayout(
                new BoxLayout(
                        dashboard,
                        BoxLayout.Y_AXIS
                )
        );

        dashboard.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 30, 30
                )
        );

        // =====================================================
        // WELCOME PANEL
        // =====================================================

        JPanel welcome =
                new JPanel(new BorderLayout());

        welcome.setBackground(
                new Color(235, 242, 251)
        );

        welcome.setBorder(
                BorderFactory.createEmptyBorder(
                        22, 25, 22, 25
                )
        );

        welcome.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        105
                )
        );

        JPanel welcomeText =
                new JPanel();

        welcomeText.setBackground(
                new Color(235, 242, 251)
        );

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel welcomeTitle =
                new JLabel(
                        "Welcome, Faculty!"
                );

        welcomeTitle.setForeground(
                TEXT_COLOR
        );

        welcomeTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel welcomeSubtitle =
                new JLabel(
                        "Manage your academic workload and assigned duties."
                );

        welcomeSubtitle.setForeground(
                new Color(90, 110, 135)
        );

        welcomeSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        welcomeText.add(
                welcomeTitle
        );

        welcomeText.add(
                Box.createVerticalStrut(6)
        );

        welcomeText.add(
                welcomeSubtitle
        );

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        refreshButton.setForeground(
                TEXT_COLOR
        );

        refreshButton.setBackground(
                Color.WHITE
        );

        refreshButton.setFocusPainted(false);

        refreshButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        refreshButton.setPreferredSize(
                new Dimension(135, 52)
        );

        refreshButton.addActionListener(
                e -> {
                    loadDashboardData();

                    JOptionPane.showMessageDialog(
                            this,
                            "Dashboard data refreshed successfully.",
                            "Refresh",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        welcome.add(
                welcomeText,
                BorderLayout.WEST
        );

        welcome.add(
                refreshButton,
                BorderLayout.EAST
        );

        dashboard.add(welcome);

        dashboard.add(
                Box.createVerticalStrut(22)
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                12,
                                0
                        )
                );

        statsPanel.setBackground(
                BACKGROUND_COLOR
        );

        statsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120
                )
        );

        JPanel subjectsCard =
                createStatCard(
                        "S",
                        "0",
                        "Subjects"
                );

        JPanel dutiesCard =
                createStatCard(
                        "D",
                        "0",
                        "Duties"
                );

        JPanel assignmentsCard =
                createStatCard(
                        "A",
                        "0",
                        "Assignments"
                );

        JPanel workloadCard =
                createStatCard(
                        "W",
                        "0 hrs",
                        "Workload"
                );

        JPanel pendingCard =
                createStatCard(
                        "P",
                        "0",
                        "Pending Tasks"
                );

        statsPanel.add(subjectsCard);

        statsPanel.add(dutiesCard);

        statsPanel.add(assignmentsCard);

        statsPanel.add(workloadCard);

        statsPanel.add(pendingCard);

        // Save labels for dynamic data
        subjectCountLabel =
                getValueLabel(subjectsCard);

        dutyCountLabel =
                getValueLabel(dutiesCard);

        assignmentCountLabel =
                getValueLabel(assignmentsCard);

        workloadLabel =
                getValueLabel(workloadCard);

        pendingTaskLabel =
                getValueLabel(pendingCard);

        dashboard.add(statsPanel);

        dashboard.add(
                Box.createVerticalStrut(22)
        );

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        JPanel quickPanel =
                new JPanel(new BorderLayout());

        quickPanel.setBackground(
                Color.WHITE
        );

        quickPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        226,
                                        234
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                22,
                                25,
                                25,
                                25
                        )
                )
        );

        JPanel quickHeading =
                new JPanel();

        quickHeading.setBackground(
                Color.WHITE
        );

        quickHeading.setLayout(
                new BoxLayout(
                        quickHeading,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel quickTitle =
                new JLabel(
                        "Quick Actions"
                );

        quickTitle.setForeground(
                TEXT_COLOR
        );

        quickTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel quickSubtitle =
                new JLabel(
                        "Frequently used faculty modules"
                );

        quickSubtitle.setForeground(
                new Color(
                        95,
                        110,
                        130
                )
        );

        quickSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        quickHeading.add(
                quickTitle
        );

        quickHeading.add(
                Box.createVerticalStrut(5)
        );

        quickHeading.add(
                quickSubtitle
        );

        JPanel actionGrid =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                15,
                                15
                        )
                );

        actionGrid.setBackground(
                Color.WHITE
        );

        actionGrid.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        // ================= QUICK ACTION 1 =================

        JButton profileButton =
                createActionButton(
                        "My Profile",
                        new Color(
                                232,
                                241,
                                252
                        )
                );

        profileButton.addActionListener(
                e -> openProfile()
        );

        // ================= QUICK ACTION 2 =================

        JButton subjectsButton =
                createActionButton(
                        "My Subjects",
                        new Color(
                                237,
                                245,
                                239
                        )
                );

        subjectsButton.addActionListener(
                e -> showSubjectsInfo()
        );

        // ================= QUICK ACTION 3 =================

        JButton workloadButton =
                createActionButton(
                        "My Workload",
                        new Color(
                                249,
                                243,
                                230
                        )
                );

        workloadButton.addActionListener(
                e -> openWorkload()
        );

        // ================= QUICK ACTION 4 =================

        JButton dutiesButton =
                createActionButton(
                        "My Duties",
                        new Color(
                                244,
                                235,
                                248
                        )
                );

        dutiesButton.addActionListener(
                e -> openDuties()
        );

        // ================= QUICK ACTION 5 =================

        JButton timetableButton =
                createActionButton(
                        "My Timetable",
                        new Color(
                                235,
                                240,
                                250
                        )
                );

        timetableButton.addActionListener(
                e -> openTimetable()
        );

        // ================= QUICK ACTION 5 =================

        JButton assignmentButton =
                createActionButton(
                        "Assignments",
                        new Color(
                                231,
                                244,
                                247
                        )
                );

        assignmentButton.addActionListener(
                e -> showAssignmentInfo()
        );

        // ================= QUICK ACTION 6 =================

        JButton reportsButton =
                createActionButton(
                        "Reports",
                        new Color(
                                239,
                                237,
                                232
                        )
                );

        reportsButton.addActionListener(
                e -> showReportsInfo()
        );

        actionGrid.add(profileButton);

        actionGrid.add(subjectsButton);

        actionGrid.add(workloadButton);

        actionGrid.add(dutiesButton);

        actionGrid.add(timetableButton);

        actionGrid.add(assignmentButton);

        actionGrid.add(reportsButton);

        quickPanel.add(
                quickHeading,
                BorderLayout.NORTH
        );

        quickPanel.add(
                actionGrid,
                BorderLayout.CENTER
        );

        dashboard.add(
                quickPanel
        );

        // =====================================================
        // CONTENT SCROLL
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(dashboard);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // ADD SIDEBAR + CONTENT
        // =====================================================

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =====================================================
    // OPEN PROFILE
    // =====================================================

    private void openProfile() {

        FacultyProfile profile =
                new FacultyProfile(facultyId);

        profile.setVisible(true);
    }

    // =====================================================
    // OPEN WORKLOAD
    // =====================================================

    private void openWorkload() {

        FacultyWorkload workload =
                new FacultyWorkload(facultyId);

        workload.setVisible(true);
    }

    // =====================================================
    // OPEN DUTIES
    // =====================================================

    private void openDuties() {

        FacultyDuties duties =
                new FacultyDuties(facultyId);

        duties.setVisible(true);
    }

    // =====================================================
    // OPEN TIMETABLE
    // =====================================================

    private void openTimetable() {

        FacultyTimetable timetable =
                new FacultyTimetable(facultyId);

        timetable.setVisible(true);
    }

    // =====================================================
    // SUBJECT INFORMATION
    // =====================================================

    private void showSubjectsInfo() {

        JOptionPane.showMessageDialog(
                this,
                "Your assigned subjects are included in your workload.\n\n"
                        + "Use 'My Workload' to view your subject-wise workload.",
                "My Subjects",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // ASSIGNMENT INFORMATION
    // =====================================================

    private void showAssignmentInfo() {

        JOptionPane.showMessageDialog(
                this,
                "Your assigned duties can be viewed from 'My Duties'.",
                "Duty Assignment",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // REPORT INFORMATION
    // =====================================================

    private void showReportsInfo() {

        JOptionPane.showMessageDialog(
                this,
                "Faculty reports are currently available through the management portal.\n\n"
                        + "Your workload and duty details can be viewed from:\n"
                        + "• My Workload\n"
                        + "• My Duties",
                "Reports",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            new Login().setVisible(true);
        }
    }

    // =====================================================
    // LOAD DASHBOARD DATA
    // =====================================================

    private void loadDashboardData() {

        try {

            String facultyName =
                    dashboardDAO.getFacultyName(facultyId);

            if (facultyName != null &&
                    !facultyName.trim().isEmpty()) {

                facultyNameLabel.setText(
                        facultyName
                );
            }

            int subjects =
                    dashboardDAO.getTotalSubjects(
                            facultyId
                    );

            int duties =
                    dashboardDAO.getTotalDuties(
                            facultyId
                    );

            int workload =
                    dashboardDAO.getTotalWorkloadHours(
                            facultyId
                    );

            subjectCountLabel.setText(
                    String.valueOf(subjects)
            );

            dutyCountLabel.setText(
                    String.valueOf(duties)
            );

            /*
             * Assignment count is based on assigned duties.
             * At present the FacultyDashboardDAO provides
             * faculty duty count and workload data.
             */
            assignmentCountLabel.setText(
                    String.valueOf(duties)
            );

            workloadLabel.setText(
                    workload + " hrs"
            );

            /*
             * Pending task calculation is not currently
             * available from the existing DAO.
             *
             * Therefore we keep it at 0 instead of
             * inventing a database query.
             */
            pendingTaskLabel.setText("0");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load dashboard data.\n\n"
                            + e.getMessage(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String letter,
            String value,
            String title
    ) {

        JPanel card =
                new JPanel();

        card.setBackground(
                Color.WHITE
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                226,
                                234
                        )
                )
        );

        JLabel icon =
                new JLabel(letter);

        icon.setForeground(
                new Color(
                        45,
                        92,
                        145
                )
        );

        icon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(
                TEXT_COLOR
        );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                new Color(
                        100,
                        115,
                        135
                )
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(
                Box.createVerticalGlue()
        );

        card.add(icon);

        card.add(
                Box.createVerticalStrut(3)
        );

        card.add(valueLabel);

        card.add(
                Box.createVerticalStrut(4)
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalGlue()
        );

        return card;
    }

    // =====================================================
    // GET VALUE LABEL FROM STAT CARD
    // =====================================================

    private JLabel getValueLabel(JPanel card) {

        /*
         * Components:
         * 0 = vertical glue
         * 1 = icon
         * 2 = strut
         * 3 = value label
         */

        Component component =
                card.getComponent(3);

        return (JLabel) component;
    }

    // =====================================================
    // QUICK ACTION BUTTON
    // =====================================================

    private JButton createActionButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        button.setForeground(
                TEXT_COLOR
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                226,
                                234
                        )
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // ================= HOVER EFFECT =================

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBorder(
                                BorderFactory.createLineBorder(
                                        new Color(
                                                45,
                                                92,
                                                145
                                        ),
                                        2
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBorder(
                                BorderFactory.createLineBorder(
                                        new Color(
                                                220,
                                                226,
                                                234
                                        )
                                )
                        );
                    }
                }
        );

        return button;
    }

    // =====================================================
    // SIDEBAR MENU BUTTON
    // =====================================================

    private void addMenuButton(
            JPanel panel,
            String icon,
            String text,
            boolean active,
            Runnable action
    ) {

        JPanel item =
                new JPanel(
                        new BorderLayout()
                );

        item.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        item.setBackground(
                active
                        ? ACTIVE_COLOR
                        : SIDEBAR_COLOR
        );

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setForeground(
                Color.WHITE
        );

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        55,
                        55
                )
        );

        JLabel textLabel =
                new JLabel(text);

        textLabel.setForeground(
                Color.WHITE
        );

        textLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        item.add(
                iconLabel,
                BorderLayout.WEST
        );

        item.add(
                textLabel,
                BorderLayout.CENTER
        );

        // ================= ACTION =================

        item.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        item.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (!active) {

                            item.setBackground(
                                    HOVER_COLOR
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (!active) {

                            item.setBackground(
                                    SIDEBAR_COLOR
                            );
                        }
                    }

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        action.run();
                    }
                }
        );

        panel.add(item);

        panel.add(
                Box.createVerticalStrut(3)
        );
    }
}
