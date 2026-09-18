package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public List<Object[]> getFacultyWorkloadReport() {

        List<Object[]> reportList =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "f.faculty_name, " +
                        "s.subject_code, " +
                        "s.subject_name, " +
                        "w.hours_per_week, " +
                        "w.semester, " +
                        "w.academic_year " +
                        "FROM workload w " +
                        "JOIN faculty f " +
                        "ON w.faculty_id = f.faculty_id " +
                        "JOIN subjects s " +
                        "ON w.subject_id = s.subject_id " +
                        "ORDER BY f.faculty_name, s.subject_name";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                Object[] row = {

                        result.getString(
                                "faculty_name"
                        ),

                        result.getString(
                                "subject_code"
                        ),

                        result.getString(
                                "subject_name"
                        ),

                        result.getInt(
                                "hours_per_week"
                        ),

                        result.getInt(
                                "semester"
                        ),

                        result.getString(
                                "academic_year"
                        )
                };

                reportList.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reportList;
    }
    public List<Object[]> getDutyAssignmentReport() {

        List<Object[]> reportList =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "f.faculty_name, " +
                        "d.duty_name, " +
                        "d.duty_type, " +
                        "d.duty_date, " +
                        "d.start_time, " +
                        "d.end_time, " +
                        "d.location, " +
                        "da.status " +
                        "FROM duty_assignments da " +
                        "JOIN faculty f " +
                        "ON da.faculty_id = f.faculty_id " +
                        "JOIN duties d " +
                        "ON da.duty_id = d.duty_id " +
                        "ORDER BY d.duty_date, d.start_time";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                Object[] row = {

                        result.getString("faculty_name"),
                        result.getString("duty_name"),
                        result.getString("duty_type"),
                        result.getDate("duty_date"),
                        result.getTime("start_time"),
                        result.getTime("end_time"),
                        result.getString("location"),
                        result.getString("status")
                };

                reportList.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reportList;
    }
    public List<Object[]> getFacultyWorkloadSummary() {

        List<Object[]> reportList =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "f.faculty_name, " +
                        "COALESCE(SUM(w.hours_per_week), 0) AS total_hours " +
                        "FROM faculty f " +
                        "LEFT JOIN workload w " +
                        "ON f.faculty_id = w.faculty_id " +
                        "GROUP BY f.faculty_id, f.faculty_name " +
                        "ORDER BY f.faculty_name";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                String facultyName =
                        result.getString(
                                "faculty_name"
                        );

                int totalHours =
                        result.getInt(
                                "total_hours"
                        );

                String status;

                if (totalHours <= 18) {

                    status = "NORMAL";

                } else if (totalHours <= 24) {

                    status = "HIGH";

                } else {

                    status = "OVERLOADED";
                }

                Object[] row = {

                        facultyName,
                        totalHours,
                        status
                };

                reportList.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reportList;
    }
}
