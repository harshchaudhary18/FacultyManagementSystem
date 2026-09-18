package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FacultyDashboardDAO {

    // =====================================================
    // GET FACULTY NAME
    // =====================================================

    public String getFacultyName(int facultyId) {

        String sql =
                "SELECT faculty_name " +
                        "FROM faculty " +
                        "WHERE faculty_id = ?";

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

                return result.getString(
                        "faculty_name"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "Faculty";
    }


    // =====================================================
    // TOTAL SUBJECTS
    // =====================================================

    public int getTotalSubjects(int facultyId) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM workload " +
                        "WHERE faculty_id = ?";

        return getCount(
                sql,
                facultyId
        );
    }


    // =====================================================
    // TOTAL WORKLOAD HOURS
    // =====================================================

    public int getTotalWorkloadHours(int facultyId) {

        String sql =
                "SELECT COALESCE(SUM(hours_per_week), 0) " +
                        "FROM workload " +
                        "WHERE faculty_id = ?";

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

                return result.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }


    // =====================================================
    // TOTAL ASSIGNED DUTIES
    // =====================================================

    public int getTotalDuties(int facultyId) {

        String sql =
                "SELECT COUNT(*) " +
                        "FROM duty_assignments " +
                        "WHERE faculty_id = ?";

        return getCount(
                sql,
                facultyId
        );
    }


    // =====================================================
    // COMMON COUNT METHOD
    // =====================================================

    private int getCount(
            String sql,
            int facultyId) {

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

            if (result.next()) {

                return result.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}