package com.faculty.management.model;

public class Faculty {

    private int facultyId;
    private int userId;
    private String facultyName;
    private String email;
    private String phone;
    private String designation;
    private int departmentId;

    public Faculty() {
    }

    public Faculty(
            int facultyId,
            int userId,
            String facultyName,
            String email,
            String phone,
            String designation,
            int departmentId) {

        this.facultyId = facultyId;
        this.userId = userId;
        this.facultyName = facultyName;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.departmentId = departmentId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    @Override
    public String toString() {
        return facultyName;
    }
}
