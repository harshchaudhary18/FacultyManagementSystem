package com.faculty.management.model;

public class Department {

    private int departmentId;
    private String departmentName;

    // Empty constructor
    public Department() {
    }

    // Constructor
    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getter for departmentId
    public int getDepartmentId() {
        return departmentId;
    }

    // Setter for departmentId
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    // Getter for departmentName
    public String getDepartmentName() {
        return departmentName;
    }

    // Setter for departmentName
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
