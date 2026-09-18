package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacultyWorkloadDAO {

    public List<Object[]> getFacultyWorkload(int facultyId) {

        List<Object[]> workloadList =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "s.subject_code, " +
                        "s.subject_name, " +
                        "w.hours_per_week, " +
                        "w.semester, " +
                        "w.academic_year " +
                        "FROM workload w " +
                        "JOIN subjects s " +
                        "ON w.subject_id = s.subject_id " +
                        "WHERE w.faculty_id = ? " +
                        "ORDER BY w.semester, s.subject_name";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    facultyId
            );

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Object[] row = {

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

                workloadList.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return workloadList;
    }
}
