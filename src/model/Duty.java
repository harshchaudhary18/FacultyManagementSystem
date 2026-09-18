package com.faculty.management.model;

import java.sql.Date;
import java.sql.Time;

public class Duty {

    private int dutyId;
    private String dutyName;
    private String dutyType;
    private Date dutyDate;
    private Time startTime;
    private Time endTime;
    private String location;

    // Empty constructor
    public Duty() {
    }

    // Constructor
    public Duty(
            int dutyId,
            String dutyName,
            String dutyType,
            Date dutyDate,
            Time startTime,
            Time endTime,
            String location) {

        this.dutyId = dutyId;
        this.dutyName = dutyName;
        this.dutyType = dutyType;
        this.dutyDate = dutyDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    // Duty ID
    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    // Duty Name
    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    // Duty Type
    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    // Duty Date
    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    // Start Time
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    // End Time
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    // Location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return dutyName + " - " + dutyDate;
    }
}
