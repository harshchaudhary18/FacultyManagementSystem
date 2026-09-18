package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FacultyProfileDAO {

    public Object[] getFacultyProfile(int facultyId) {

        Object[] profile = null;

        String sql =
                "SELECT " +
                        "f.faculty_name, " +
                        "f.email, " +
                        "f.phone, " +
                        "f.designation, " +
                        "d.department_name " +
                        "FROM faculty f " +
                        "LEFT JOIN departments d " +
                        "ON f.department_id = d.department_id " +
                        "WHERE f.faculty_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, facultyId);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                profile = new Object[]{

                        result.getString(
                                "faculty_name"
                        ),

                        result.getString(
                                "email"
                        ),

                        result.getString(
                                "phone"
                        ),

                        result.getString(
                                "designation"
                        ),

                        result.getString(
                                "department_name"
                        )
                };
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return profile;
    }
}
