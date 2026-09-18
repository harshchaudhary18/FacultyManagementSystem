package com.faculty.management.ui;

import com.faculty.management.dao.UserDAO;
import com.faculty.management.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class UserManagement extends JFrame {

    // =========================================================
    // FIELDS
    // =========================================================

    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JComboBox<String> roleComboBox;

    private JTextField searchField;

    private JTable userTable;
    private DefaultTableModel tableModel;

    private UserDAO userDAO;

    private JLabel recordCountLabel;
    private JLabel statusLabel;

    private TableRowSorter<DefaultTableModel> sorter;


    // =========================================================
    // COLORS
    // =========================================================

    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color PRIMARY =
            new Color(35, 55, 90);

    private final Color PRIMARY_LIGHT =
            new Color(55, 78, 115);

    private final Color BORDER =
            new Color(210, 216, 225);

    private final Color TEXT =
            new Color(45, 55, 70);

    private final Color WHITE =
            Color.WHITE;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UserManagement() {

        userDAO = new UserDAO();

        setTitle("User Management");
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

        loadUsers();
    }


    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(18, 18));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(
                        22,
                        25,
                        22,
                        25
                )
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);


        JPanel headingPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        headingPanel.setOpaque(false);


        JLabel title =
                new JLabel(
                        "USER MANAGEMENT"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(PRIMARY);


        JLabel subtitle =
                new JLabel(
                        "Create and manage system user accounts"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                new Color(105, 115, 130)
        );


        headingPanel.add(title);
        headingPanel.add(subtitle);


        // =====================================================
        // HEADER RIGHT
        // =====================================================

        JPanel headerRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        headerRight.setOpaque(false);


        JLabel adminLabel =
                new JLabel(
                        "ADMINISTRATOR"
                );

        adminLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        adminLabel.setForeground(PRIMARY);

        adminLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 200, 215)
                        ),
                        BorderFactory.createEmptyBorder(
                                6,
                                12,
                                6,
                                12
                        )
                )
        );


        headerRight.add(adminLabel);


        headerPanel.add(
                headingPanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                headerRight,
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
                        new GridBagLayout()
                );

        centerPanel.setOpaque(false);


        GridBagConstraints mainGbc =
                new GridBagConstraints();

        mainGbc.fill =
                GridBagConstraints.BOTH;

        mainGbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        10
                );

        mainGbc.weighty = 1;


        // =====================================================
        // LEFT FORM CARD
        // =====================================================

        JPanel formCard =
                createCardPanel();


        formCard.setLayout(
                new BorderLayout(
                        10,
                        15
                )
        );


        JPanel formHeader =
                new JPanel(
                        new BorderLayout()
                );

        formHeader.setOpaque(false);


        JLabel formTitle =
                new JLabel(
                        "User Details"
                );

        formTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        formTitle.setForeground(PRIMARY);


        JLabel formSubtitle =
                new JLabel(
                        "Enter account information"
                );

        formSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        formSubtitle.setForeground(
                new Color(110, 120, 135)
        );


        JPanel formHeading =
                new JPanel(
                        new GridLayout(2, 1)
                );

        formHeading.setOpaque(false);

        formHeading.add(formTitle);
        formHeading.add(formSubtitle);


        formHeader.add(
                formHeading,
                BorderLayout.WEST
        );


        formCard.add(
                formHeader,
                BorderLayout.NORTH
        );


        // =====================================================
        // FORM FIELDS
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setOpaque(false);


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        7,
                        4,
                        7,
                        4
                );

        gbc.weightx = 1;


        // ---------------- USER ID ----------------

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                createLabel("User ID"),
                gbc
        );


        gbc.gridy = 1;

        userIdField =
                createTextField();

        userIdField.setEditable(false);

        userIdField.setBackground(
                new Color(238, 241, 245)
        );

        formPanel.add(
                userIdField,
                gbc
        );


        // ---------------- USERNAME ----------------

        gbc.gridy = 2;

        formPanel.add(
                createLabel("Username"),
                gbc
        );


        gbc.gridy = 3;

        usernameField =
                createTextField();

        formPanel.add(
                usernameField,
                gbc
        );


        // ---------------- PASSWORD ----------------

        gbc.gridy = 4;

        formPanel.add(
                createLabel("Password"),
                gbc
        );


        gbc.gridy = 5;

        passwordField =
                new JPasswordField();

        styleTextField(passwordField);

        formPanel.add(
                passwordField,
                gbc
        );


        // ---------------- ROLE ----------------

        gbc.gridy = 6;

        formPanel.add(
                createLabel("Role"),
                gbc
        );


        gbc.gridy = 7;

        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "ADMIN",
                                "HOD",
                                "FACULTY"
                        }
                );

        roleComboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        roleComboBox.setPreferredSize(
                new Dimension(
                        200,
                        38
                )
        );

        roleComboBox.setBackground(Color.WHITE);

        formPanel.add(
                roleComboBox,
                gbc
        );


        formCard.add(
                formPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FORM NOTE
        // =====================================================

        JLabel noteLabel =
                new JLabel(
                        "<html>Password is required when updating an account.</html>"
                );

        noteLabel.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        11
                )
        );

        noteLabel.setForeground(
                new Color(110, 120, 135)
        );


        formCard.add(
                noteLabel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // ADD LEFT PANEL
        // =====================================================

        mainGbc.gridx = 0;
        mainGbc.gridy = 0;

        mainGbc.weightx = 0.32;

        centerPanel.add(
                formCard,
                mainGbc
        );


        // =====================================================
        // RIGHT TABLE CARD
        // =====================================================

        JPanel tableCard =
                createCardPanel();


        tableCard.setLayout(
                new BorderLayout(
                        10,
                        12
                )
        );


        // =====================================================
        // TABLE HEADER
        // =====================================================

        JPanel tableHeader =
                new JPanel(
                        new BorderLayout(
                                10,
                                5
                        )
                );

        tableHeader.setOpaque(false);


        JPanel tableTitlePanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        tableTitlePanel.setOpaque(false);


        JLabel tableTitle =
                new JLabel(
                        "User Accounts"
                );

        tableTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        tableTitle.setForeground(PRIMARY);


        recordCountLabel =
                new JLabel(
                        "0 accounts"
                );

        recordCountLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        recordCountLabel.setForeground(
                new Color(110, 120, 135)
        );


        tableTitlePanel.add(tableTitle);
        tableTitlePanel.add(recordCountLabel);


        // =====================================================
        // SEARCH
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        searchPanel.setOpaque(false);


        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        230,
                        38
                )
        );

        styleTextField(searchField);


        JButton clearSearchButton =
                new JButton("Clear");

        styleSecondaryButton(
                clearSearchButton
        );


        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                clearSearchButton,
                BorderLayout.EAST
        );


        tableHeader.add(
                tableTitlePanel,
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
                                "User ID",
                                "Username",
                                "Role"
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


        userTable =
                new JTable(tableModel);

        userTable.setRowHeight(34);

        userTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        userTable.setShowVerticalLines(false);

        userTable.setShowHorizontalLines(
                true
        );

        userTable.setGridColor(
                new Color(230, 234, 240)
        );

        userTable.setSelectionBackground(
                new Color(225, 233, 245)
        );

        userTable.setSelectionForeground(
                TEXT
        );


        // =====================================================
        // TABLE HEADER STYLE
        // =====================================================

        userTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        userTable.getTableHeader()
                .setBackground(PRIMARY);

        userTable.getTableHeader()
                .setForeground(Color.WHITE);

        userTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                38
                        )
                );


        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        userTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(80);

        userTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(220);

        userTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(130);


        // =====================================================
        // ROLE RENDERER
        // =====================================================

        userTable.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        new RoleCellRenderer()
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        userTable
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


        // =====================================================
        // ADD RIGHT PANEL
        // =====================================================

        mainGbc.gridx = 1;

        mainGbc.weightx = 0.68;

        mainGbc.insets =
                new Insets(
                        0,
                        10,
                        0,
                        0
                );

        centerPanel.add(
                tableCard,
                mainGbc
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // BOTTOM ACTION BAR
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);


        // =====================================================
        // STATUS
        // =====================================================

        statusLabel =
                new JLabel(
                        "Ready"
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        statusLabel.setForeground(
                new Color(100, 110, 125)
        );


        bottomPanel.add(
                statusLabel,
                BorderLayout.WEST
        );


        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);


        JButton addButton =
                createPrimaryButton("Add User");

        JButton updateButton =
                createSecondaryButton("Update");

        JButton deleteButton =
                createDangerButton("Delete");

        JButton clearButton =
                createSecondaryButton("Clear");

        JButton refreshButton =
                createSecondaryButton("Refresh");


        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);


        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );


        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        addButton.addActionListener(
                e -> addUser()
        );

        updateButton.addActionListener(
                e -> updateUser()
        );

        deleteButton.addActionListener(
                e -> deleteUser()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        refreshButton.addActionListener(
                e -> {
                    searchField.setText("");
                    loadUsers();

                    statusLabel.setText(
                            "User list refreshed"
                    );
                }
        );


        // =====================================================
        // SEARCH
        // =====================================================

        sorter =
                new TableRowSorter<>(
                        tableModel
                );

        userTable.setRowSorter(sorter);


        searchField
                .getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            private void filter() {

                                String text =
                                        searchField
                                                .getText()
                                                .trim();

                                if (text.isEmpty()) {

                                    sorter.setRowFilter(
                                            null
                                    );

                                    statusLabel.setText(
                                            "Showing all users"
                                    );

                                } else {

                                    sorter.setRowFilter(
                                            RowFilter
                                                    .regexFilter(
                                                            "(?i)"
                                                                    + java.util.regex.Pattern
                                                                    .quote(text)
                                                    )
                                    );

                                    statusLabel.setText(
                                            "Searching for: "
                                                    + text
                                    );
                                }
                            }


                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }


                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }


                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {
                                filter();
                            }
                        }
                );


        clearSearchButton.addActionListener(
                e -> {
                    searchField.setText("");
                    sorter.setRowFilter(null);

                    statusLabel.setText(
                            "Showing all users"
                    );
                }
        );


        // =====================================================
        // TABLE ROW SELECTION
        // =====================================================

        userTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    int selectedRow =
                            userTable.getSelectedRow();

                    if (selectedRow < 0) {
                        return;
                    }


                    int modelRow =
                            userTable.convertRowIndexToModel(
                                    selectedRow
                            );


                    userIdField.setText(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            0
                                    )
                                    .toString()
                    );


                    usernameField.setText(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            1
                                    )
                                    .toString()
                    );


                    passwordField.setText("");


                    roleComboBox.setSelectedItem(
                            tableModel
                                    .getValueAt(
                                            modelRow,
                                            2
                                    )
                    );


                    statusLabel.setText(
                            "Selected user: "
                                    + usernameField
                                    .getText()
                    );
                });


        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        userTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        if (e.getClickCount() == 2) {

                            int row =
                                    userTable.getSelectedRow();

                            if (row >= 0) {

                                statusLabel.setText(
                                        "Editing user: "
                                                + usernameField
                                                .getText()
                                );
                            }
                        }
                    }
                }
        );


        add(mainPanel);
    }


    // =========================================================
    // LOAD USERS
    // =========================================================

    private void loadUsers() {

        tableModel.setRowCount(0);


        List<User> users =
                userDAO.getAllUsers();


        for (User user : users) {

            tableModel.addRow(
                    new Object[]{
                            user.getUserId(),
                            user.getUsername(),
                            user.getRole()
                    }
            );
        }


        recordCountLabel.setText(
                users.size()
                        + (users.size() == 1
                        ? " account"
                        : " accounts")
        );


        statusLabel.setText(
                "Loaded "
                        + users.size()
                        + " user accounts"
        );
    }


    // =========================================================
    // ADD USER
    // =========================================================

    private void addUser() {

        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();


        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username and password are required.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        User user =
                new User();


        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);


        if (userDAO.addUser(user)) {

            JOptionPane.showMessageDialog(
                    this,
                    "User added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadUsers();
            clearFields();


            statusLabel.setText(
                    "User added successfully"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add user.\n"
                            + "Username may already exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // UPDATE USER
    // =========================================================

    private void updateUser() {

        if (userIdField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user first.",
                    "Update User",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
                                .getPassword()
                );


        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();


        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter the password to update the user.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            int userId =
                    Integer.parseInt(
                            userIdField
                                    .getText()
                                    .trim()
                    );


            User user =
                    new User();


            user.setUserId(userId);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);


            if (userDAO.updateUser(user)) {

                JOptionPane.showMessageDialog(
                        this,
                        "User updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                loadUsers();
                clearFields();


                statusLabel.setText(
                        "User updated successfully"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to update user.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid User ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =========================================================
    // DELETE USER
    // =========================================================

    private void deleteUser() {

        if (userIdField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user first.",
                    "Delete User",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int userId =
                Integer.parseInt(
                        userIdField
                                .getText()
                                .trim()
                );


        String username =
                usernameField
                        .getText()
                        .trim();


        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete\n"
                                + "user \"" + username + "\"?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (choice != JOptionPane.YES_OPTION) {
            return;
        }


        if (userDAO.deleteUser(userId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "User deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadUsers();
            clearFields();


            statusLabel.setText(
                    "User deleted successfully"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete user.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        userIdField.setText("");

        usernameField.setText("");

        passwordField.setText("");

        roleComboBox.setSelectedIndex(0);

        userTable.clearSelection();

        statusLabel.setText(
                "Ready for new entry"
        );
    }


    // =========================================================
    // CREATE LABEL
    // =========================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT);

        return label;
    }


    // =========================================================
    // CREATE TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        styleTextField(field);

        return field;
    }


    // =========================================================
    // STYLE TEXT FIELD
    // =========================================================

    private void styleTextField(
            JTextField field
    ) {

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
                        38
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );
    }


    // =========================================================
    // CREATE CARD
    // =========================================================

    private JPanel createCardPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        return panel;
    }


    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text
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

        button.setBackground(PRIMARY);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        addHoverEffect(
                button,
                PRIMARY,
                PRIMARY_LIGHT
        );

        return button;
    }


    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private JButton createSecondaryButton(
            String text
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

        button.setForeground(PRIMARY);

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(185, 195, 210)
                        ),
                        BorderFactory.createEmptyBorder(
                                9,
                                16,
                                9,
                                16
                        )
                )
        );

        addHoverEffect(
                button,
                Color.WHITE,
                new Color(235, 239, 245)
        );

        return button;
    }


    // =========================================================
    // DANGER BUTTON
    // =========================================================

    private JButton createDangerButton(
            String text
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
                new Color(180, 45, 45)
        );

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 170, 170)
                        ),
                        BorderFactory.createEmptyBorder(
                                9,
                                16,
                                9,
                                16
                        )
                )
        );

        addHoverEffect(
                button,
                Color.WHITE,
                new Color(252, 238, 238)
        );

        return button;
    }


    // =========================================================
    // SECONDARY FORM BUTTON
    // =========================================================

    private void styleSecondaryButton(
            JButton button
    ) {

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(PRIMARY);

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );
    }


    // =========================================================
    // HOVER EFFECT
    // =========================================================

    private void addHoverEffect(
            JButton button,
            Color normal,
            Color hover
    ) {

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                hover
                        );
                    }


                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e
                    ) {

                        button.setBackground(
                                normal
                        );
                    }
                }
        );
    }


    // =========================================================
    // ROLE CELL RENDERER
    // =========================================================

    private static class RoleCellRenderer
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

            JLabel label =
                    (JLabel) super
                            .getTableCellRendererComponent(
                                    table,
                                    value,
                                    isSelected,
                                    hasFocus,
                                    row,
                                    column
                            );


            label.setHorizontalAlignment(
                    SwingConstants.CENTER
            );


            label.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            12
                    )
            );


            if (!isSelected) {

                if ("ADMIN".equals(value)) {

                    label.setForeground(
                            new Color(
                                    35,
                                    70,
                                    130
                            )
                    );

                } else if ("HOD".equals(value)) {

                    label.setForeground(
                            new Color(
                                    120,
                                    75,
                                    150
                            )
                    );

                } else if ("FACULTY".equals(value)) {

                    label.setForeground(
                            new Color(
                                    35,
                                    120,
                                    80
                            )
                    );

                } else {

                    label.setForeground(
                            new Color(
                                    70,
                                    80,
                                    90
                            )
                    );
                }

            } else {

                label.setForeground(new Color(55, 65, 81));
            }


            return label;
        }
    }
}