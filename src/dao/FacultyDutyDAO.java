package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacultyDutyDAO {

    public List<Object[]> getFacultyDuties(int facultyId) {

        List<Object[]> dutyList =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "d.duty_name, " +
                        "d.duty_type, " +
                        "d.duty_date, " +
                        "d.start_time, " +
                        "d.end_time, " +
                        "d.location, " +
                        "da.status " +
                        "FROM duty_assignments da " +
                        "JOIN duties d " +
                        "ON da.duty_id = d.duty_id " +
                        "WHERE da.faculty_id = ? " +
                        "ORDER BY d.duty_date, d.start_time";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, facultyId);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Object[] row = {

                        result.getString("duty_name"),

                        result.getString("duty_type"),

                        result.getDate("duty_date"),

                        result.getTime("start_time"),

                        result.getTime("end_time"),

                        result.getString("location"),

                        result.getString("status")
                };

                dutyList.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dutyList;
    }
}
