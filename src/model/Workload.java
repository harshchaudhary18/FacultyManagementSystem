package com.faculty.management.model;

public class Workload {

    private int workloadId;
    private int facultyId;
    private int subjectId;
    private int hoursPerWeek;
    private int semester;
    private String academicYear;

    // Empty constructor
    public Workload() {
    }

    // Constructor
    public Workload(
            int workloadId,
            int facultyId,
            int subjectId,
            int hoursPerWeek,
            int semester,
            String academicYear) {

        this.workloadId = workloadId;
        this.facultyId = facultyId;
        this.subjectId = subjectId;
        this.hoursPerWeek = hoursPerWeek;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    // Workload ID
    public int getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(int workloadId) {
        this.workloadId = workloadId;
    }

    // Faculty ID
    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    // Subject ID
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    // Hours per week
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    // Semester
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    // Academic Year
    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}