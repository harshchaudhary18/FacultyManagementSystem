package com.faculty.management.ui;

import com.faculty.management.dao.DashboardDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboard extends JFrame {

    private DashboardDAO dashboardDAO;

    private JLabel facultyCountLabel;
    private JLabel subjectCountLabel;
    private JLabel dutyCountLabel;
    private JLabel assignmentCountLabel;
    private JLabel workloadCountLabel;

    // ================= COLORS =================

    private static final Color SIDEBAR_COLOR =
            new Color(28, 42, 68);

    private static final Color SIDEBAR_ACTIVE =
            new Color(55, 78, 112);

    private static final Color SIDEBAR_HOVER =
            new Color(45, 63, 95);

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color CARD_COLOR =
            Color.WHITE;

    private static final Color TEXT_COLOR =
            new Color(35, 55, 90);

    private static final Color MUTED_COLOR =
            new Color(105, 115, 130);

    private static final Color BORDER_COLOR =
            new Color(225, 229, 236);

    private static final Color ACCENT_COLOR =
            new Color(55, 95, 150);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminDashboard() {

        dashboardDAO = new DashboardDAO();

        setTitle(
                "Faculty Duty & Workload Management System"
        );

        /*
         * Allow resizing and maximizing
         */
        setSize(1200, 750);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(true);


        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                BACKGROUND
        );


        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                new JPanel(new BorderLayout());

        sidebar.setPreferredSize(
                new Dimension(270, 0)
        );

        sidebar.setBackground(
                SIDEBAR_COLOR
        );


        // =====================================================
        // SIDEBAR HEADER
        // =====================================================

        JPanel sidebarHeader =
                new JPanel();

        sidebarHeader.setLayout(
                new BoxLayout(
                        sidebarHeader,
                        BoxLayout.Y_AXIS
                )
        );

        sidebarHeader.setOpaque(false);

        sidebarHeader.setBorder(
                new EmptyBorder(
                        28,
                        25,
                        20,
                        20
                )
        );


        JLabel logo =
                new JLabel("FM");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        logo.setForeground(
                Color.WHITE
        );

        logo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel systemTitle =
                new JLabel(
                        "<html>" +
                                "FACULTY<br>" +
                                "MANAGEMENT" +
                                "</html>"
                );

        systemTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        systemTitle.setForeground(
                Color.WHITE
        );

        systemTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel systemSubtitle =
                new JLabel(
                        "Duty & Workload System"
                );

        systemSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        systemSubtitle.setForeground(
                new Color(
                        185,
                        195,
                        210
                )
        );

        systemSubtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        sidebarHeader.add(logo);

        sidebarHeader.add(
                Box.createVerticalStrut(10)
        );

        sidebarHeader.add(
                systemTitle
        );

        sidebarHeader.add(
                Box.createVerticalStrut(6)
        );

        sidebarHeader.add(
                systemSubtitle
        );


        sidebar.add(
                sidebarHeader,
                BorderLayout.NORTH
        );


        // =====================================================
        // NAVIGATION
        // =====================================================

        JPanel navigationPanel =
                new JPanel();

        navigationPanel.setLayout(
                new BoxLayout(
                        navigationPanel,
                        BoxLayout.Y_AXIS
                )
        );

        navigationPanel.setOpaque(false);

        navigationPanel.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );


        JLabel menuLabel =
                new JLabel("MAIN MENU");

        menuLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        menuLabel.setForeground(
                new Color(
                        150,
                        165,
                        185
                )
        );

        menuLabel.setBorder(
                new EmptyBorder(
                        0,
                        10,
                        12,
                        0
                )
        );

        menuLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        navigationPanel.add(
                menuLabel
        );


        JButton dashboardButton =
                createSidebarButton(
                        "D",
                        "Dashboard",
                        true
                );

        JButton userButton =
                createSidebarButton(
                        "U",
                        "User Management",
                        false
                );

        JButton facultyButton =
                createSidebarButton(
                        "F",
                        "Faculty Management",
                        false
                );

        JButton departmentButton =
                createSidebarButton(
                        "D",
                        "Department Management",
                        false
                );

        JButton subjectButton =
                createSidebarButton(
                        "S",
                        "Subject Management",
                        false
                );

        JButton workloadButton =
                createSidebarButton(
                        "W",
                        "Workload Management",
                        false
                );

        JButton dutyButton =
                createSidebarButton(
                        "D",
                        "Duty Management",
                        false
                );

        JButton assignmentButton =
                createSidebarButton(
                        "A",
                        "Duty Assignment",
                        false
                );

        JButton reportButton =
                createSidebarButton(
                        "R",
                        "Reports",
                        false
                );


        addNavigationButton(
                navigationPanel,
                dashboardButton
        );

        addNavigationButton(
                navigationPanel,
                userButton
        );

        addNavigationButton(
                navigationPanel,
                facultyButton
        );

        addNavigationButton(
                navigationPanel,
                departmentButton
        );

        addNavigationButton(
                navigationPanel,
                subjectButton
        );

        addNavigationButton(
                navigationPanel,
                workloadButton
        );

        addNavigationButton(
                navigationPanel,
                dutyButton
        );

        addNavigationButton(
                navigationPanel,
                assignmentButton
        );

        addNavigationButton(
                navigationPanel,
                reportButton
        );


        sidebar.add(
                navigationPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // SIDEBAR FOOTER
        // =====================================================

        JPanel sidebarFooter =
                new JPanel(
                        new BorderLayout()
                );

        sidebarFooter.setOpaque(false);

        sidebarFooter.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        22,
                        20
                )
        );


        JPanel adminInfo =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        adminInfo.setOpaque(false);


        JLabel adminCircle =
                new JLabel(
                        "A",
                        SwingConstants.CENTER
                );

        adminCircle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        adminCircle.setForeground(
                Color.WHITE
        );

        adminCircle.setBackground(
                ACCENT_COLOR
        );

        adminCircle.setOpaque(true);

        adminCircle.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );


        JPanel adminText =
                new JPanel();

        adminText.setLayout(
                new BoxLayout(
                        adminText,
                        BoxLayout.Y_AXIS
                )
        );

        adminText.setOpaque(false);


        JLabel adminName =
                new JLabel(
                        "Administrator"
                );

        adminName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        adminName.setForeground(
                Color.WHITE
        );


        JLabel adminRole =
                new JLabel(
                        "ADMIN"
                );

        adminRole.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        adminRole.setForeground(
                new Color(
                        175,
                        190,
                        210
                ));


        adminText.add(
                adminName
        );

        adminText.add(
                Box.createVerticalStrut(3)
        );

        adminText.add(
                adminRole
        );


        adminInfo.add(
                adminCircle,
                BorderLayout.WEST
        );

        adminInfo.add(
                adminText,
                BorderLayout.CENTER
        );


        sidebarFooter.add(
                adminInfo,
                BorderLayout.CENTER
        );


        sidebar.add(
                sidebarFooter,
                BorderLayout.SOUTH
        );


        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                Color.WHITE
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        22,
                        30,
                        22,
                        30
                )
        );


        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);


        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                TEXT_COLOR
        );


        JLabel subtitle =
                new JLabel(
                        "Overview of faculty, workload and duty management"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                MUTED_COLOR
        );


        headingPanel.add(
                title
        );

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(
                subtitle
        );


        // =====================================================
        // HEADER RIGHT
        // =====================================================

        JPanel headerRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                5
                        )
                );

        headerRight.setOpaque(false);


        JLabel roleLabel =
                new JLabel(
                        "Administrator"
                );

        roleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        roleLabel.setForeground(
                TEXT_COLOR
        );


        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        logoutButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        logoutButton.setForeground(
                Color.WHITE
        );

        logoutButton.setBackground(
                new Color(
                        185,
                        65,
                        65
                )
        );

        logoutButton.setFocusPainted(false);

        logoutButton.setBorderPainted(false);

        logoutButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        logoutButton.setPreferredSize(
                new Dimension(
                        95,
                        36
                )
        );


        addButtonHover(
                logoutButton,
                new Color(
                        185,
                        65,
                        65
                ),
                new Color(
                        215,
                        80,
                        80
                )
        );


        headerRight.add(
                roleLabel
        );

        headerRight.add(
                logoutButton
        );


        headerPanel.add(
                headingPanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                headerRight,
                BorderLayout.EAST
        );


        contentPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        JPanel content =
                new JPanel(
                        new BorderLayout()
                );

        content.setOpaque(false);

        content.setBorder(
                new EmptyBorder(
                        22,
                        28,
                        25,
                        28
                )
        );


        // =====================================================
        // WELCOME PANEL
        // =====================================================

        JPanel welcomePanel =
                new JPanel(
                        new BorderLayout()
                );

        welcomePanel.setBackground(
                new Color(
                        235,
                        241,
                        249
                )
        );

        welcomePanel.setBorder(
                new EmptyBorder(
                        18,
                        22,
                        18,
                        22
                )
        );


        JPanel welcomeText =
                new JPanel();

        welcomeText.setLayout(
                new BoxLayout(
                        welcomeText,
                        BoxLayout.Y_AXIS
                )
        );

        welcomeText.setOpaque(false);


        JLabel welcomeTitle =
                new JLabel(
                        "Good Morning, Administrator!"
                );

        welcomeTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        welcomeTitle.setForeground(
                TEXT_COLOR
        );


        JLabel welcomeSubtitle =
                new JLabel(
                        "Manage your college faculty system from one place."
                );

        welcomeSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        welcomeSubtitle.setForeground(
                MUTED_COLOR
        );


        welcomeText.add(
                welcomeTitle
        );

        welcomeText.add(
                Box.createVerticalStrut(5)
        );

        welcomeText.add(
                welcomeSubtitle
        );


        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
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
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        105,
                        36
                )
        );

        refreshButton.setBorder(
                BorderFactory.createLineBorder(
                        BORDER_COLOR
                )
        );


        refreshButton.addActionListener(
                e -> loadDashboardStatistics()
        );


        welcomePanel.add(
                welcomeText,
                BorderLayout.WEST
        );

        welcomePanel.add(
                refreshButton,
                BorderLayout.EAST
        );


        content.add(
                welcomePanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER AREA
        // =====================================================

        JPanel centerArea =
                new JPanel(
                        new BorderLayout()
                );

        centerArea.setOpaque(false);


        // =====================================================
        // STATISTICS
        // =====================================================

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                12,
                                0
                        )
                );

        statisticsPanel.setOpaque(false);

        statisticsPanel.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        20,
                        0
                )
        );


        facultyCountLabel =
                createValueLabel();

        subjectCountLabel =
                createValueLabel();

        dutyCountLabel =
                createValueLabel();

        assignmentCountLabel =
                createValueLabel();

        workloadCountLabel =
                createValueLabel();


        statisticsPanel.add(
                createStatCard(
                        "Faculty",
                        "F",
                        facultyCountLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Subjects",
                        "S",
                        subjectCountLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Duties",
                        "D",
                        dutyCountLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Assignments",
                        "A",
                        assignmentCountLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "Workload",
                        "W",
                        workloadCountLabel
                )
        );


        centerArea.add(
                statisticsPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        JPanel quickPanel =
                new JPanel(
                        new BorderLayout()
                );

        quickPanel.setBackground(
                CARD_COLOR
        );

        quickPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                20,
                                20
                        )
                )
        );


        JPanel quickHeading =
                new JPanel();

        quickHeading.setLayout(
                new BoxLayout(
                        quickHeading,
                        BoxLayout.Y_AXIS
                )
        );

        quickHeading.setOpaque(false);


        JLabel quickTitle =
                new JLabel(
                        "Quick Actions"
                );

        quickTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        quickTitle.setForeground(
                TEXT_COLOR
        );


        JLabel quickSubtitle =
                new JLabel(
                        "Frequently used management modules"
                );

        quickSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        quickSubtitle.setForeground(
                MUTED_COLOR
        );


        quickHeading.add(
                quickTitle
        );

        quickHeading.add(
                Box.createVerticalStrut(4)
        );

        quickHeading.add(
                quickSubtitle
        );


        quickPanel.add(
                quickHeading,
                BorderLayout.NORTH
        );


        JPanel quickButtons =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                12,
                                12
                        )
                );

        quickButtons.setOpaque(false);

        quickButtons.setBorder(
                new EmptyBorder(
                        18,
                        0,
                        0,
                        0
                )
        );


        JButton quickFaculty =
                createQuickButton(
                        "Faculty",
                        new Color(
                                232,
                                240,
                                250
                        )
                );

        JButton quickSubject =
                createQuickButton(
                        "Subjects",
                        new Color(
                                237,
                                244,
                                238
                        )
                );

        JButton quickWorkload =
                createQuickButton(
                        "Workload",
                        new Color(
                                248,
                                242,
                                230
                        )
                );

        JButton quickDuty =
                createQuickButton(
                        "Duties",
                        new Color(
                                242,
                                235,
                                246
                        )
                );

        JButton quickAssignment =
                createQuickButton(
                        "Assignment",
                        new Color(
                                232,
                                242,
                                244
                        )
                );

        JButton quickReports =
                createQuickButton(
                        "Reports",
                        new Color(
                                240,
                                236,
                                228
                        )
                );

        JButton quickUsers =
                createQuickButton(
                        "Users",
                        new Color(
                                235,
                                238,
                                246
                        )
                );

        JButton quickDepartment =
                createQuickButton(
                        "Department",
                        new Color(
                                239,
                                241,
                                233
                        )
                );


        quickButtons.add(
                quickFaculty
        );

        quickButtons.add(
                quickSubject
        );

        quickButtons.add(
                quickWorkload
        );

        quickButtons.add(
                quickDuty
        );

        quickButtons.add(
                quickAssignment
        );

        quickButtons.add(
                quickReports
        );

        quickButtons.add(
                quickUsers
        );

        quickButtons.add(
                quickDepartment
        );


        quickPanel.add(
                quickButtons,
                BorderLayout.CENTER
        );


        centerArea.add(
                quickPanel,
                BorderLayout.CENTER
        );


        content.add(
                centerArea,
                BorderLayout.CENTER
        );


        contentPanel.add(
                content,
                BorderLayout.CENTER
        );


        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        add(mainPanel);


        // =====================================================
        // SIDEBAR ACTIONS
        // =====================================================

        userButton.addActionListener(
                e -> new UserManagement()
                        .setVisible(true)
        );

        facultyButton.addActionListener(
                e -> new FacultyManagement()
                        .setVisible(true)
        );

        departmentButton.addActionListener(
                e -> new DepartmentManagement()
                        .setVisible(true)
        );

        subjectButton.addActionListener(
                e -> new SubjectManagement()
                        .setVisible(true)
        );

        workloadButton.addActionListener(
                e -> new WorkloadManagement()
                        .setVisible(true)
        );

        dutyButton.addActionListener(
                e -> new DutyManagement()
                        .setVisible(true)
        );

        assignmentButton.addActionListener(
                e -> new DutyAssignmentManagement()
                        .setVisible(true)
        );

        reportButton.addActionListener(
                e -> new ReportManagement()
                        .setVisible(true)
        );


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        quickFaculty.addActionListener(
                e -> new FacultyManagement()
                        .setVisible(true)
        );

        quickSubject.addActionListener(
                e -> new SubjectManagement()
                        .setVisible(true)
        );

        quickWorkload.addActionListener(
                e -> new WorkloadManagement()
                        .setVisible(true)
        );

        quickDuty.addActionListener(
                e -> new DutyManagement()
                        .setVisible(true)
        );

        quickAssignment.addActionListener(
                e -> new DutyAssignmentManagement()
                        .setVisible(true)
        );

        quickReports.addActionListener(
                e -> new ReportManagement()
                        .setVisible(true)
        );

        quickUsers.addActionListener(
                e -> new UserManagement()
                        .setVisible(true)
        );

        quickDepartment.addActionListener(
                e -> new DepartmentManagement()
                        .setVisible(true)
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        logoutButton.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Confirm Logout",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        dispose();

                        new Login()
                                .setVisible(true);
                    }
                }
        );


        // =====================================================
        // LOAD DATA
        // =====================================================

        loadDashboardStatistics();
    }


    // =========================================================
    // ADD NAVIGATION BUTTON
    // =========================================================

    private void addNavigationButton(
            JPanel panel,
            JButton button
    ) {

        panel.add(button);

        panel.add(
                Box.createVerticalStrut(5)
        );
    }


    // =========================================================
    // CREATE SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String icon,
            String text,
            boolean active
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout()
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
                )
        );

        button.setPreferredSize(
                new Dimension(
                        240,
                        46
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        JLabel iconLabel =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );


        JLabel textLabel =
                new JLabel(
                        text
                );

        textLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        button.add(
                iconLabel,
                BorderLayout.WEST
        );

        button.add(
                textLabel,
                BorderLayout.CENTER
        );


        if (active) {

            button.setBackground(
                    SIDEBAR_ACTIVE
            );

            iconLabel.setForeground(
                    Color.WHITE
            );

            textLabel.setForeground(
                    Color.WHITE
            );

        } else {

            button.setBackground(
                    SIDEBAR_COLOR
            );

            iconLabel.setForeground(
                    new Color(
                            190,
                            200,
                            215
                    )
            );

            textLabel.setForeground(
                    new Color(
                            215,
                            222,
                            232
                    )
            );
        }


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (!active) {

                            button.setBackground(
                                    SIDEBAR_HOVER
                            );
                        }
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (!active) {

                            button.setBackground(
                                    SIDEBAR_COLOR
                            );
                        }
                    }
                }
        );


        return button;
    }


    // =========================================================
    // CREATE VALUE LABEL
    // =========================================================

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }


    // =========================================================
    // CREATE STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String icon,
            JLabel valueLabel
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                CARD_COLOR
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                12,
                                10,
                                12,
                                10
                        )
                )
        );


        JLabel iconLabel =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        iconLabel.setForeground(
                ACCENT_COLOR
        );


        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                MUTED_COLOR
        );


        JPanel center =
                new JPanel(
                        new BorderLayout()
                );

        center.setOpaque(false);


        center.add(
                iconLabel,
                BorderLayout.NORTH
        );

        center.add(
                valueLabel,
                BorderLayout.CENTER
        );


        card.add(
                center,
                BorderLayout.CENTER
        );

        card.add(
                titleLabel,
                BorderLayout.SOUTH
        );


        return card;
    }


    // =========================================================
    // CREATE QUICK BUTTON
    // =========================================================

    private JButton createQuickButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                TEXT_COLOR
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );


        Color originalColor =
                background;


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        220,
                                        228,
                                        240
                                )
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                originalColor
                        );
                    }
                }
        );


        return button;
    }


    // =========================================================
    // LOGOUT / BUTTON HOVER
    // =========================================================

    private void addButtonHover(
            JButton button,
            Color normal,
            Color hover
    ) {

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                hover
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                normal
                        );
                    }
                }
        );
    }


    // =========================================================
    // LOAD DASHBOARD STATISTICS
    // =========================================================

    private void loadDashboardStatistics() {

        try {

            int totalFaculty =
                    dashboardDAO.getTotalFaculty();

            int totalSubjects =
                    dashboardDAO.getTotalSubjects();

            int totalDuties =
                    dashboardDAO.getTotalDuties();

            int assignedDuties =
                    dashboardDAO.getAssignedDuties();

            int totalWorkload =
                    dashboardDAO.getTotalWorkloadHours();


            facultyCountLabel.setText(
                    String.valueOf(
                            totalFaculty
                    )
            );

            subjectCountLabel.setText(
                    String.valueOf(
                            totalSubjects
                    )
            );

            dutyCountLabel.setText(
                    String.valueOf(
                            totalDuties
                    )
            );

            assignmentCountLabel.setText(
                    String.valueOf(
                            assignedDuties
                    )
            );

            workloadCountLabel.setText(
                    totalWorkload + " hrs"
            );


        } catch (Exception e) {

            e.printStackTrace();

            facultyCountLabel.setText("0");

            subjectCountLabel.setText("0");

            dutyCountLabel.setText("0");

            assignmentCountLabel.setText("0");

            workloadCountLabel.setText("0 hrs");
        }
    }
}