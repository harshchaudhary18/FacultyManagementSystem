package com.faculty.management.ui;

import com.faculty.management.dao.FacultyDAO;
import com.faculty.management.db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("Faculty Duty & Workload Management System");
        setSize(850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ================= MAIN PANEL =================

        JPanel mainPanel =
                new JPanel(new GridLayout(1, 2));

        // ================= LEFT PANEL =================

        JPanel leftPanel =
                new JPanel(new GridBagLayout());

        leftPanel.setBackground(
                new Color(35, 55, 90)
        );

        GridBagConstraints leftGbc =
                new GridBagConstraints();

        leftGbc.gridx = 0;
        leftGbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel(
                        "<html><center>" +
                                "FACULTY DUTY<br>" +
                                "& WORKLOAD<br>" +
                                "MANAGEMENT SYSTEM" +
                                "</center></html>"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(Color.WHITE);

        leftGbc.gridy = 0;

        leftPanel.add(
                title,
                leftGbc
        );

        JLabel subtitle =
                new JLabel(
                        "College Management System"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(
                new Color(220, 225, 235)
        );

        leftGbc.gridy = 1;

        leftPanel.add(
                subtitle,
                leftGbc
        );

        // ================= RIGHT PANEL =================

        JPanel rightPanel =
                new JPanel(
                        new GridBagLayout()
                );

        rightPanel.setBackground(
                Color.WHITE
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 10, 8, 10);

        // ================= LOGIN TITLE =================

        JLabel loginTitle =
                new JLabel(
                        "Welcome Back"
                );

        loginTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        loginTitle.setForeground(
                new Color(35, 55, 90)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor =
                GridBagConstraints.CENTER;

        rightPanel.add(
                loginTitle,
                gbc
        );

        // ================= LOGIN SUBTITLE =================

        JLabel loginSubtitle =
                new JLabel(
                        "Login to continue"
                );

        loginSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        loginSubtitle.setForeground(
                Color.GRAY
        );

        gbc.gridy = 1;

        rightPanel.add(
                loginSubtitle,
                gbc
        );

        // ================= USERNAME =================

        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor =
                GridBagConstraints.EAST;

        rightPanel.add(
                usernameLabel,
                gbc
        );

        usernameField =
                new JTextField(18);

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        usernameField.setPreferredSize(
                new Dimension(220, 38)
        );

        gbc.gridx = 1;
        gbc.anchor =
                GridBagConstraints.WEST;

        rightPanel.add(
                usernameField,
                gbc
        );

        // ================= PASSWORD =================

        JLabel passwordLabel =
                new JLabel(
                        "Password:"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor =
                GridBagConstraints.EAST;

        rightPanel.add(
                passwordLabel,
                gbc
        );

        passwordField =
                new JPasswordField(18);

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setPreferredSize(
                new Dimension(220, 38)
        );

        gbc.gridx = 1;
        gbc.anchor =
                GridBagConstraints.WEST;

        rightPanel.add(
                passwordField,
                gbc
        );

        // ================= LOGIN BUTTON =================

        JButton loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                new Color(35, 55, 90)
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setPreferredSize(
                new Dimension(140, 40)
        );

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor =
                GridBagConstraints.CENTER;

        rightPanel.add(
                loginButton,
                gbc
        );

        // ================= ADD PANELS =================

        mainPanel.add(
                leftPanel
        );

        mainPanel.add(
                rightPanel
        );

        add(mainPanel);

        // ================= LOGIN ACTION =================

        loginButton.addActionListener(
                e -> login()
        );
    }

    // =====================================================
    // LOGIN METHOD
    // =====================================================

    private void login() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        // ================= VALIDATION =================

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ================= SQL =================

        String sql =
                "SELECT user_id, role " +
                        "FROM users " +
                        "WHERE username = ? " +
                        "AND password = ?";

        try (
                Connection connection =
                        DatabaseConnection
                                .getConnection();

                PreparedStatement statement =
                        connection
                                .prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );

            ResultSet result =
                    statement.executeQuery();

            // ================= USER FOUND =================

            if (result.next()) {

                int userId =
                        result.getInt(
                                "user_id"
                        );

                String role =
                        result.getString(
                                "role"
                        );

                // =================================================
                // ADMIN
                // =================================================

                if (role.equals("ADMIN")) {

                    dispose();

                    new AdminDashboard()
                            .setVisible(true);
                }

                // =================================================
                // HOD
                // =================================================

                else if (role.equals("HOD")) {

                    dispose();

                    new HodDashboard()
                            .setVisible(true);
                }

                // =================================================
                // FACULTY
                // =================================================

                else if (role.equals("FACULTY")) {

                    FacultyDAO facultyDAO =
                            new FacultyDAO();

                    int facultyId =
                            facultyDAO
                                    .getFacultyIdByUserId(
                                            userId
                                    );

                    // Faculty record not found

                    if (facultyId == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Faculty record not found.",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        return;
                    }

                    // Open faculty dashboard

                    dispose();

                    new FacultyDashboard(
                            facultyId
                    ).setVisible(true);
                }

                // =================================================
                // UNKNOWN ROLE
                // =================================================

                else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unknown user role.",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } else {

                // ================= INVALID LOGIN =================

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}