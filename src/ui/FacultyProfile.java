package com.faculty.management.ui;

import com.faculty.management.dao.FacultyProfileDAO;

import javax.swing.*;
import java.awt.*;

public class FacultyProfile extends JFrame {

    private int facultyId;

    private FacultyProfileDAO profileDAO;

    private JLabel nameValue;
    private JLabel emailValue;
    private JLabel phoneValue;
    private JLabel designationValue;
    private JLabel departmentValue;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public FacultyProfile(int facultyId) {

        this.facultyId = facultyId;

        profileDAO = new FacultyProfileDAO();


        // =====================================================
        // WINDOW
        // =====================================================

        setTitle("My Profile");

        setSize(650, 520);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "MY PROFILE",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(
                        35,
                        55,
                        90
                )
        );


        mainPanel.add(
                title,
                BorderLayout.NORTH
        );


        // =====================================================
        // PROFILE PANEL
        // =====================================================

        JPanel profilePanel =
                new JPanel(
                        new GridLayout(
                                5,
                                2,
                                15,
                                15
                        )
                );

        profilePanel.setBackground(
                Color.WHITE
        );

        profilePanel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        180,
                                        190,
                                        200
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );


        // =====================================================
        // NAME
        // =====================================================

        profilePanel.add(
                createLabel("Name:")
        );

        nameValue =
                createValueLabel();

        profilePanel.add(
                nameValue
        );


        // =====================================================
        // EMAIL
        // =====================================================

        profilePanel.add(
                createLabel("Email:")
        );

        emailValue =
                createValueLabel();

        profilePanel.add(
                emailValue
        );


        // =====================================================
        // PHONE
        // =====================================================

        profilePanel.add(
                createLabel("Phone:")
        );

        phoneValue =
                createValueLabel();

        profilePanel.add(
                phoneValue
        );


        // =====================================================
        // DESIGNATION
        // =====================================================

        profilePanel.add(
                createLabel("Designation:")
        );

        designationValue =
                createValueLabel();

        profilePanel.add(
                designationValue
        );


        // =====================================================
        // DEPARTMENT
        // =====================================================

        profilePanel.add(
                createLabel("Department:")
        );

        departmentValue =
                createValueLabel();

        profilePanel.add(
                departmentValue
        );


        mainPanel.add(
                profilePanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.setOpaque(false);


        // =====================================================
        // BACK BUTTON
        // =====================================================

        JButton backButton =
                new JButton(
                        "Back to Dashboard"
                );

        backButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        backButton.setPreferredSize(
                new Dimension(
                        170,
                        38
                )
        );

        backButton.setFocusPainted(
                false
        );


        buttonPanel.add(
                backButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.addActionListener(e -> {

            dispose();

        });


        // =====================================================
        // LOAD PROFILE
        // =====================================================

        loadProfile();


        // =====================================================
        // ADD MAIN PANEL
        // =====================================================

        add(mainPanel);
    }


    // =========================================================
    // CREATE LABEL
    // =========================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(
                new Color(
                        35,
                        55,
                        90
                )
        );

        return label;
    }


    // =========================================================
    // CREATE VALUE LABEL
    // =========================================================

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel(
                        "-"
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        label.setForeground(
                Color.DARK_GRAY
        );

        return label;
    }


    // =========================================================
    // LOAD PROFILE
    // =========================================================

    private void loadProfile() {

        Object[] profile =
                profileDAO.getFacultyProfile(
                        facultyId
                );


        // -----------------------------------------------------
        // PROFILE NOT FOUND
        // -----------------------------------------------------

        if (profile == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty profile not found.",
                    "Profile Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // -----------------------------------------------------
        // NAME
        // -----------------------------------------------------

        nameValue.setText(
                profile[0] != null
                        ? profile[0].toString()
                        : "-"
        );


        // -----------------------------------------------------
        // EMAIL
        // -----------------------------------------------------

        emailValue.setText(
                profile[1] != null
                        ? profile[1].toString()
                        : "-"
        );


        // -----------------------------------------------------
        // PHONE
        // -----------------------------------------------------

        phoneValue.setText(
                profile[2] != null
                        ? profile[2].toString()
                        : "-"
        );


        // -----------------------------------------------------
        // DESIGNATION
        // -----------------------------------------------------

        designationValue.setText(
                profile[3] != null
                        ? profile[3].toString()
                        : "-"
        );


        // -----------------------------------------------------
        // DEPARTMENT
        // -----------------------------------------------------

        departmentValue.setText(
                profile[4] != null
                        ? profile[4].toString()
                        : "-"
        );
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            /*
             * Testing ke liye existing faculty ID use karo.
             * Tumhare current database mein Rahul Sharma ka
             * faculty_id = 1 hai.
             */

            new FacultyProfile(1)
                    .setVisible(true);

        });
    }
}