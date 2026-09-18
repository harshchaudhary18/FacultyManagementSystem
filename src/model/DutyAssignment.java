package com.faculty.management.model;

public class DutyAssignment {

    private int assignmentId;
    private int facultyId;
    private int dutyId;
    private String status;

    public DutyAssignment() {
    }

    public DutyAssignment(
            int assignmentId,
            int facultyId,
            int dutyId,
            String status) {

        this.assignmentId = assignmentId;
        this.facultyId = facultyId;
        this.dutyId = dutyId;
        this.status = status;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
