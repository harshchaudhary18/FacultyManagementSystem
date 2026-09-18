package com.faculty.management.ui;

import com.faculty.management.dao.FacultyWorkloadDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FacultyWorkload extends JFrame {

    private int facultyId;

    private FacultyWorkloadDAO workloadDAO;

    private JTable workloadTable;

    private DefaultTableModel tableModel;

    private JLabel totalHoursLabel;


    public FacultyWorkload(int facultyId) {

        this.facultyId = facultyId;

        workloadDAO =
                new FacultyWorkloadDAO();

        setTitle("My Workload");

        setSize(900, 550);

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
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "MY WORKLOAD",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Subject Code",
                                "Subject Name",
                                "Hours/Week",
                                "Semester",
                                "Academic Year"
                        },
                        0
                );


        workloadTable =
                new JTable(tableModel);


        workloadTable.setRowHeight(28);

        workloadTable.setAutoCreateRowSorter(true);


        JScrollPane scrollPane =
                new JScrollPane(
                        workloadTable
                );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BOTTOM PANEL
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );


        totalHoursLabel =
                new JLabel(
                        "Total Workload: 0 hrs"
                );


        totalHoursLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        JButton refreshButton =
                new JButton(
                        "Refresh"
                );


        JButton closeButton =
                new JButton(
                        "Close"
                );


        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                closeButton
        );


        bottomPanel.add(
                totalHoursLabel,
                BorderLayout.WEST
        );


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

        refreshButton.addActionListener(e -> {

            loadWorkload();

        });


        closeButton.addActionListener(e -> {

            dispose();

        });


        // =====================================================
        // LOAD DATA
        // =====================================================

        loadWorkload();


        add(mainPanel);
    }


    // =========================================================
    // LOAD WORKLOAD
    // =========================================================

    private void loadWorkload() {

        tableModel.setRowCount(0);

        List<Object[]> workload =
                workloadDAO.getFacultyWorkload(
                        facultyId
                );


        int totalHours = 0;


        for (Object[] row : workload) {

            tableModel.addRow(row);

            totalHours +=
                    Integer.parseInt(
                            row[2].toString()
                    );
        }


        totalHoursLabel.setText(
                "Total Workload: "
                        + totalHours
                        + " hrs/week"
        );
    }
}
