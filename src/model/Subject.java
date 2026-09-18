package com.faculty.management.model;

public class Subject {

    private int subjectId;
    private String subjectCode;
    private String subjectName;
    private int departmentId;
    private int semester;
    private int hoursPerWeek;

    // Empty constructor
    public Subject() {
    }

    // Constructor
    public Subject(
            int subjectId,
            String subjectCode,
            String subjectName,
            int departmentId,
            int semester,
            int hoursPerWeek) {

        this.subjectId = subjectId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.departmentId = departmentId;
        this.semester = semester;
        this.hoursPerWeek = hoursPerWeek;
    }

    // Subject ID
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    // Subject Code
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    // Subject Name
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // Department ID
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    // Semester
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    // Hours per week
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    @Override
    public String toString() {
        return subjectCode + " - " + subjectName;
    }
}
