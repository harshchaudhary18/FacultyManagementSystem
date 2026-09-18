package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;
import com.faculty.management.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    // ================= ADD =================

    public boolean addSubject(Subject subject) {

        String sql =
                "INSERT INTO subjects " +
                        "(subject_code, subject_name, department_id, " +
                        "semester, hours_per_week) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    subject.getSubjectCode()
            );

            statement.setString(
                    2,
                    subject.getSubjectName()
            );

            statement.setInt(
                    3,
                    subject.getDepartmentId()
            );

            statement.setInt(
                    4,
                    subject.getSemester()
            );

            statement.setInt(
                    5,
                    subject.getHoursPerWeek()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // ================= VIEW ALL =================

    public List<Subject> getAllSubjects() {

        List<Subject> subjectList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM subjects";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                Subject subject =
                        new Subject();

                subject.setSubjectId(
                        result.getInt("subject_id")
                );

                subject.setSubjectCode(
                        result.getString("subject_code")
                );

                subject.setSubjectName(
                        result.getString("subject_name")
                );

                subject.setDepartmentId(
                        result.getInt("department_id")
                );

                subject.setSemester(
                        result.getInt("semester")
                );

                subject.setHoursPerWeek(
                        result.getInt("hours_per_week")
                );

                subjectList.add(subject);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return subjectList;
    }

    // ================= UPDATE =================

    public boolean updateSubject(Subject subject) {

        String sql =
                "UPDATE subjects SET " +
                        "subject_code = ?, " +
                        "subject_name = ?, " +
                        "department_id = ?, " +
                        "semester = ?, " +
                        "hours_per_week = ? " +
                        "WHERE subject_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    subject.getSubjectCode()
            );

            statement.setString(
                    2,
                    subject.getSubjectName()
            );

            statement.setInt(
                    3,
                    subject.getDepartmentId()
            );

            statement.setInt(
                    4,
                    subject.getSemester()
            );

            statement.setInt(
                    5,
                    subject.getHoursPerWeek()
            );

            statement.setInt(
                    6,
                    subject.getSubjectId()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================

    public boolean deleteSubject(int subjectId) {

        String sql =
                "DELETE FROM subjects " +
                        "WHERE subject_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, subjectId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // ================= SEARCH =================

    public List<Subject> searchSubjects(String name) {

        List<Subject> subjectList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM subjects " +
                        "WHERE subject_name LIKE ? " +
                        "OR subject_code LIKE ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            String search =
                    "%" + name + "%";

            statement.setString(1, search);
            statement.setString(2, search);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Subject subject =
                        new Subject();

                subject.setSubjectId(
                        result.getInt("subject_id")
                );

                subject.setSubjectCode(
                        result.getString("subject_code")
                );

                subject.setSubjectName(
                        result.getString("subject_name")
                );

                subject.setDepartmentId(
                        result.getInt("department_id")
                );

                subject.setSemester(
                        result.getInt("semester")
                );

                subject.setHoursPerWeek(
                        result.getInt("hours_per_week")
                );

                subjectList.add(subject);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return subjectList;
    }
}