package com.faculty.management.ui;

import com.faculty.management.dao.FacultyDutyDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FacultyDuties extends JFrame {

    private int facultyId;

    private FacultyDutyDAO dutyDAO;

    private JTable dutyTable;

    private DefaultTableModel tableModel;


    public FacultyDuties(int facultyId) {

        this.facultyId = facultyId;

        dutyDAO =
                new FacultyDutyDAO();

        setTitle("My Duties");

        setSize(1000, 550);

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
                        "MY DUTIES",
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
                                "Duty Name",
                                "Duty Type",
                                "Date",
                                "Start Time",
                                "End Time",
                                "Location",
                                "Status"
                        },
                        0
                );


        dutyTable =
                new JTable(tableModel);


        dutyTable.setRowHeight(28);

        dutyTable.setAutoCreateRowSorter(true);


        JScrollPane scrollPane =
                new JScrollPane(
                        dutyTable
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
                        new FlowLayout(
                                FlowLayout.RIGHT
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


        bottomPanel.add(
                refreshButton
        );

        bottomPanel.add(
                closeButton
        );


        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // REFRESH
        // =====================================================

        refreshButton.addActionListener(e -> {

            loadDuties();

        });


        // =====================================================
        // CLOSE
        // =====================================================

        closeButton.addActionListener(e -> {

            dispose();

        });


        // =====================================================
        // LOAD DATA
        // =====================================================

        loadDuties();


        add(mainPanel);
    }


    // =========================================================
    // LOAD DUTIES
    // =========================================================

    private void loadDuties() {

        tableModel.setRowCount(0);


        List<Object[]> duties =
                dutyDAO.getFacultyDuties(
                        facultyId
                );


        for (Object[] row : duties) {

            tableModel.addRow(row);
        }
    }
}
