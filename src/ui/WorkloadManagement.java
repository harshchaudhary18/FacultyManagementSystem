package com.faculty.management.ui;

import com.faculty.management.dao.FacultyDAO;
import com.faculty.management.dao.SubjectDAO;
import com.faculty.management.dao.WorkloadDAO;
import com.faculty.management.model.Faculty;
import com.faculty.management.model.Subject;
import com.faculty.management.model.Workload;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class WorkloadManagement extends JFrame {

    private JTextField workloadIdField;
    private JTextField hoursField;
    private JTextField semesterField;
    private JTextField academicYearField;
    private JTextField searchField;

    private JComboBox<Faculty> facultyComboBox;
    private JComboBox<Subject> subjectComboBox;

    private JTable workloadTable;
    private DefaultTableModel tableModel;

    private WorkloadDAO workloadDAO;
    private FacultyDAO facultyDAO;
    private SubjectDAO subjectDAO;

    public WorkloadManagement() {

        workloadDAO = new WorkloadDAO();
        facultyDAO = new FacultyDAO();
        subjectDAO = new SubjectDAO();

        setTitle("Faculty Workload Management");
        setSize(1100, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        loadFaculty();
        loadSubjects();
        loadWorkloads();
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // ================= TITLE =================

        JLabel title =
                new JLabel(
                        "Faculty Workload Management",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        // ================= FORM =================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(3, 4, 10, 10)
                );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 30, 10, 30
                )
        );

        // Workload ID

        formPanel.add(
                new JLabel("Workload ID:")
        );

        workloadIdField =
                new JTextField();

        workloadIdField.setEditable(false);

        formPanel.add(workloadIdField);

        // Faculty

        formPanel.add(
                new JLabel("Faculty:")
        );

        facultyComboBox =
                new JComboBox<>();

        formPanel.add(facultyComboBox);

        // Subject

        formPanel.add(
                new JLabel("Subject:")
        );

        subjectComboBox =
                new JComboBox<>();

        formPanel.add(subjectComboBox);

        // Hours

        formPanel.add(
                new JLabel("Hours / Week:")
        );

        hoursField =
                new JTextField();

        formPanel.add(hoursField);

        // Semester

        formPanel.add(
                new JLabel("Semester:")
        );

        semesterField =
                new JTextField();

        formPanel.add(semesterField);

        // Academic Year

        formPanel.add(
                new JLabel("Academic Year:")
        );

        academicYearField =
                new JTextField();

        formPanel.add(academicYearField);

        // ================= TOP PANEL =================

        JPanel topPanel =
                new JPanel(new BorderLayout());

        topPanel.add(
                title,
                BorderLayout.NORTH
        );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // ================= TABLE =================

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Faculty",
                                "Subject",
                                "Hours / Week",
                                "Semester",
                                "Academic Year",
                                "Status"
                        },
                        0
                );

        workloadTable =
                new JTable(tableModel);

        workloadTable.setRowHeight(28);

        JScrollPane scrollPane =
                new JScrollPane(workloadTable);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ================= BUTTON PANEL =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                8
                        )
                );

        JButton addButton =
                new JButton("Add");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");
        JButton calculateButton =
                new JButton("Calculate Workload");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(calculateButton);

        buttonPanel.add(
                new JLabel("Faculty ID:")
        );

        searchField =
                new JTextField(8);

        buttonPanel.add(searchField);

        JButton searchButton =
                new JButton("Search");

        JButton viewAllButton =
                new JButton("View All");

        buttonPanel.add(searchButton);
        buttonPanel.add(viewAllButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // ================= BUTTON ACTIONS =================

        addButton.addActionListener(
                e -> addWorkload()
        );

        updateButton.addActionListener(
                e -> updateWorkload()
        );

        deleteButton.addActionListener(
                e -> deleteWorkload()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        calculateButton.addActionListener(
                e -> calculateWorkload()
        );

        searchButton.addActionListener(
                e -> searchWorkload()
        );

        viewAllButton.addActionListener(
                e -> loadWorkloads()
        );

        // ================= SUBJECT SELECTION =================

        subjectComboBox.addActionListener(e -> {

            Subject subject =
                    (Subject) subjectComboBox
                            .getSelectedItem();

            if (subject != null) {

                hoursField.setText(
                        String.valueOf(
                                subject.getHoursPerWeek()
                        )
                );

                semesterField.setText(
                        String.valueOf(
                                subject.getSemester()
                        )
                );
            }
        });

        // ================= TABLE CLICK =================

        workloadTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                workloadTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            workloadIdField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row, 0
                                            )
                                            .toString()
                            );

                            String facultyName =
                                    tableModel
                                            .getValueAt(
                                                    row, 1
                                            )
                                            .toString();

                            String subjectName =
                                    tableModel
                                            .getValueAt(
                                                    row, 2
                                            )
                                            .toString();

                            selectFaculty(
                                    facultyName
                            );

                            selectSubject(
                                    subjectName
                            );

                            hoursField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row, 3
                                            )
                                            .toString()
                            );

                            semesterField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row, 4
                                            )
                                            .toString()
                            );

                            academicYearField.setText(
                                    tableModel
                                            .getValueAt(
                                                    row, 5
                                            )
                                            .toString()
                            );
                        }
                    }
                });

        add(mainPanel);
    }

    // ================= LOAD FACULTY =================

    private void loadFaculty() {

        facultyComboBox.removeAllItems();

        List<Faculty> facultyList =
                facultyDAO.getAllFaculty();

        for (Faculty faculty : facultyList) {

            facultyComboBox.addItem(faculty);
        }
    }

    // ================= LOAD SUBJECTS =================

    private void loadSubjects() {

        subjectComboBox.removeAllItems();

        List<Subject> subjectList =
                subjectDAO.getAllSubjects();

        for (Subject subject : subjectList) {

            subjectComboBox.addItem(subject);
        }
    }

    // ================= ADD =================
    private void addWorkload() {

        try {

            Faculty faculty =
                    (Faculty) facultyComboBox
                            .getSelectedItem();

            Subject subject =
                    (Subject) subjectComboBox
                            .getSelectedItem();


            if (faculty == null || subject == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select faculty and subject."
                );

                return;
            }


            int hours =
                    Integer.parseInt(
                            hoursField.getText().trim()
                    );

            int semester =
                    Integer.parseInt(
                            semesterField.getText().trim()
                    );

            String academicYear =
                    academicYearField.getText().trim();

            if (hours <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hours must be greater than 0.",
                        "Invalid Hours",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            if (semester <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semester must be greater than 0.",
                        "Invalid Semester",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (academicYear.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the academic year.",
                        "Missing Academic Year",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (isDuplicateWorkload(
                    faculty.getFacultyId(),
                    subject.getSubjectId(),
                    semester,
                    academicYear,
                    -1
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "This workload assignment already exists for this faculty, "
                                + "subject, semester and academic year.",
                        "Duplicate Workload",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // ================================================
            // CURRENT WORKLOAD
            // ================================================

            int currentHours =
                    workloadDAO.getTotalHoursByFaculty(
                            faculty.getFacultyId()
                    );


            int newTotal =
                    currentHours + hours;


            // ================================================
            // BLOCK OVERLOADED FACULTY
            // ================================================

            if (newTotal >= 25) {

                JOptionPane.showMessageDialog(
                        this,
                        "Workload cannot be added.\n\n"
                                + "Faculty: "
                                + faculty.getFacultyName()
                                + "\nCurrent Workload: "
                                + currentHours
                                + " hrs/week"
                                + "\nRequested: "
                                + hours
                                + " hrs/week"
                                + "\nNew Total: "
                                + newTotal
                                + " hrs/week"
                                + "\n\nStatus: OVERLOADED",
                        "Workload Warning",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // ================================================
            // CREATE WORKLOAD
            // ================================================

            Workload workload =
                    new Workload();

            workload.setFacultyId(
                    faculty.getFacultyId()
            );

            workload.setSubjectId(
                    subject.getSubjectId()
            );

            workload.setHoursPerWeek(
                    hours
            );

            workload.setSemester(
                    semester
            );

            workload.setAcademicYear(
                    academicYear
            );


            // ================================================
            // ADD
            // ================================================

            if (workloadDAO.addWorkload(workload)) {

                String status;

                if (newTotal <= 18) {

                    status = "NORMAL";

                } else {

                    status = "HIGH";
                }


                JOptionPane.showMessageDialog(
                        this,
                        "Workload added successfully!\n\n"
                                + "Faculty: "
                                + faculty.getFacultyName()
                                + "\nNew Total: "
                                + newTotal
                                + " hrs/week"
                                + "\nStatus: "
                                + status,
                        "Workload Status",
                        JOptionPane.INFORMATION_MESSAGE
                );


                loadWorkloads();

                clearFields();


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add workload.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Hours and semester must be valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }





    // ================= UPDATE =================

    private void updateWorkload() {

        try {

            if (workloadIdField
                    .getText()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a workload record first."
                );

                return;
            }

            Faculty faculty =
                    (Faculty) facultyComboBox
                            .getSelectedItem();

            Subject subject =
                    (Subject) subjectComboBox
                            .getSelectedItem();

            if (faculty == null ||
                    subject == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select faculty and subject."
                );

                return;
            }

            int hours =
                    Integer.parseInt(
                            hoursField.getText().trim()
                    );

            int semester =
                    Integer.parseInt(
                            semesterField.getText().trim()
                    );

            String academicYear =
                    academicYearField.getText().trim();

            if (hours <= 0 || semester <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hours and semester must be greater than 0.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (academicYear.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the academic year.",
                        "Missing Academic Year",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int workloadId =
                    Integer.parseInt(
                            workloadIdField.getText().trim()
                    );

            if (isDuplicateWorkload(
                    faculty.getFacultyId(),
                    subject.getSubjectId(),
                    semester,
                    academicYear,
                    workloadId
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "This workload assignment already exists.",
                        "Duplicate Workload",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int currentTotal =
                    workloadDAO.getTotalHoursByFaculty(
                            faculty.getFacultyId()
                    );

            // Remove the currently selected record if it belongs
            // to the same faculty.
            Workload selectedWorkload =
                    findWorkloadById(workloadId);

            if (selectedWorkload != null
                    && selectedWorkload.getFacultyId()
                    == faculty.getFacultyId()) {

                currentTotal -= selectedWorkload.getHoursPerWeek();
            }

            int newTotal = currentTotal + hours;

            if (newTotal > 24) {

                JOptionPane.showMessageDialog(
                        this,
                        "Faculty workload cannot exceed 24 hours/week.\n\n"
                                + "Current workload after removing old record: "
                                + currentTotal
                                + " hrs/week\n"
                                + "New total: "
                                + newTotal
                                + " hrs/week",
                        "Workload Limit",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            Workload workload =
                    new Workload();

            workload.setWorkloadId(
                    Integer.parseInt(
                            workloadIdField.getText()
                    )
            );

            workload.setFacultyId(
                    faculty.getFacultyId()
            );

            workload.setSubjectId(
                    subject.getSubjectId()
            );

            workload.setHoursPerWeek(
                    hours
            );

            workload.setSemester(
                    semester
            );

            workload.setAcademicYear(
                    academicYear
            );

            if (workloadDAO
                    .updateWorkload(workload)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Workload updated successfully!"
                );

                loadWorkloads();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update failed."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values."
            );
        }
    }

    // ================= DELETE =================

    private void deleteWorkload() {

        try {

            if (workloadIdField
                    .getText()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a workload record first."
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            workloadIdField.getText()
                    );

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this workload?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                if (workloadDAO.deleteWorkload(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Workload deleted successfully!"
                    );

                    loadWorkloads();
                    clearFields();
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid workload ID."
            );
        }
    }

    // ================= LOAD TABLE =================

    private void loadWorkloads() {

        tableModel.setRowCount(0);

        List<Workload> workloads =
                workloadDAO.getAllWorkloads();

        for (Workload workload :
                workloads) {

            String facultyName =
                    getFacultyName(
                            workload.getFacultyId()
                    );

            String subjectName =
                    getSubjectName(
                            workload.getSubjectId()
                    );

            tableModel.addRow(
                    new Object[]{
                            workload.getWorkloadId(),
                            facultyName,
                            subjectName,
                            workload.getHoursPerWeek(),
                            workload.getSemester(),
                            workload.getAcademicYear(),
                            getWorkloadStatus(
                                    workloadDAO.getTotalHoursByFaculty(
                                            workload.getFacultyId()
                                    )
                            )
                    }
            );
        }
    }
    // ================= CALCULATE TOTAL WORKLOAD =================

    private void calculateWorkload() {

        Faculty faculty =
                (Faculty) facultyComboBox
                        .getSelectedItem();

        if (faculty == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a faculty member."
            );

            return;
        }

        int facultyId =
                faculty.getFacultyId();

        int totalHours =
                workloadDAO
                        .getTotalHoursByFaculty(
                                facultyId
                        );

        JOptionPane.showMessageDialog(
                this,
                "Faculty: "
                        + faculty.getFacultyName()
                        + "\n\nTotal Weekly Workload: "
                        + totalHours
                        + " hours"
                        + "\nStatus: "
                        + getWorkloadStatus(totalHours),
                "Workload Summary",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= SEARCH =================

    private void searchWorkload() {

        try {

            String text =
                    searchField
                            .getText()
                            .trim();

            if (text.isEmpty()) {

                loadWorkloads();
                return;
            }

            int facultyId =
                    Integer.parseInt(text);

            tableModel.setRowCount(0);

            List<Workload> workloads =
                    workloadDAO
                            .searchByFaculty(facultyId);

            for (Workload workload :
                    workloads) {

                String facultyName =
                        getFacultyName(
                                workload.getFacultyId()
                        );

                String subjectName =
                        getSubjectName(
                                workload.getSubjectId()
                        );

                tableModel.addRow(
                        new Object[]{
                                workload.getWorkloadId(),
                                facultyName,
                                subjectName,
                                workload.getHoursPerWeek(),
                                workload.getSemester(),
                                workload.getAcademicYear()
                        }
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Faculty ID."
            );
        }
    }

    // ================= WORKLOAD STATUS =================

    private String getWorkloadStatus(int totalHours) {

        if (totalHours <= 18) {
            return "NORMAL";
        }

        if (totalHours <= 24) {
            return "HIGH";
        }

        return "OVERLOADED";
    }

    // ================= DUPLICATE CHECK =================

    private boolean isDuplicateWorkload(
            int facultyId,
            int subjectId,
            int semester,
            String academicYear,
            int excludeWorkloadId
    ) {

        List<Workload> workloads =
                workloadDAO.getAllWorkloads();

        for (Workload workload : workloads) {

            if (workload.getWorkloadId()
                    == excludeWorkloadId) {
                continue;
            }

            if (workload.getFacultyId() == facultyId
                    && workload.getSubjectId() == subjectId
                    && workload.getSemester() == semester
                    && academicYear.equalsIgnoreCase(
                    workload.getAcademicYear()
            )) {

                return true;
            }
        }

        return false;
    }

    // ================= FIND WORKLOAD BY ID =================

    private Workload findWorkloadById(int workloadId) {

        List<Workload> workloads =
                workloadDAO.getAllWorkloads();

        for (Workload workload : workloads) {

            if (workload.getWorkloadId()
                    == workloadId) {

                return workload;
            }
        }

        return null;
    }

    // ================= FIND FACULTY =================

    private String getFacultyName(int facultyId) {

        for (int i = 0;
             i < facultyComboBox.getItemCount();
             i++) {

            Faculty faculty =
                    facultyComboBox.getItemAt(i);

            if (faculty.getFacultyId() ==
                    facultyId) {

                return faculty.getFacultyName();
            }
        }

        return "Unknown Faculty";
    }

    // ================= FIND SUBJECT =================

    private String getSubjectName(int subjectId) {

        for (int i = 0;
             i < subjectComboBox.getItemCount();
             i++) {

            Subject subject =
                    subjectComboBox.getItemAt(i);

            if (subject.getSubjectId() ==
                    subjectId) {

                return subject.toString();
            }
        }

        return "Unknown Subject";
    }

    // ================= SELECT FACULTY =================

    private void selectFaculty(String facultyName) {

        for (int i = 0;
             i < facultyComboBox.getItemCount();
             i++) {

            Faculty faculty =
                    facultyComboBox.getItemAt(i);

            if (faculty.getFacultyName()
                    .equals(facultyName)) {

                facultyComboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    // ================= SELECT SUBJECT =================

    private void selectSubject(String subjectName) {

        for (int i = 0;
             i < subjectComboBox.getItemCount();
             i++) {

            Subject subject =
                    subjectComboBox.getItemAt(i);

            if (subject.toString()
                    .equals(subjectName)) {

                subjectComboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    // ================= CLEAR =================

    private void clearFields() {

        workloadIdField.setText("");

        if (facultyComboBox.getItemCount() > 0) {
            facultyComboBox.setSelectedIndex(0);
        }

        if (subjectComboBox.getItemCount() > 0) {
            subjectComboBox.setSelectedIndex(0);
        }

        searchField.setText("");
        hoursField.setText("");
        semesterField.setText("");
        academicYearField.setText("");
    }
}
