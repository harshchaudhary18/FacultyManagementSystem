package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;
import com.faculty.management.model.Workload;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkloadDAO {

    public boolean addWorkload(Workload workload) {
        String sql = "INSERT INTO workload (faculty_id, subject_id, hours_per_week, semester, academic_year) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workload.getFacultyId());
            statement.setInt(2, workload.getSubjectId());
            statement.setInt(3, workload.getHoursPerWeek());
            statement.setInt(4, workload.getSemester());
            statement.setString(5, workload.getAcademicYear());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Workload> getAllWorkloads() {
        List<Workload> workloadList = new ArrayList<>();
        String sql = "SELECT * FROM workload";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) workloadList.add(readWorkload(result));
        } catch (SQLException e) { e.printStackTrace(); }
        return workloadList;
    }

    public boolean updateWorkload(Workload workload) {
        String sql = "UPDATE workload SET faculty_id = ?, subject_id = ?, hours_per_week = ?, semester = ?, academic_year = ? WHERE workload_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workload.getFacultyId());
            statement.setInt(2, workload.getSubjectId());
            statement.setInt(3, workload.getHoursPerWeek());
            statement.setInt(4, workload.getSemester());
            statement.setString(5, workload.getAcademicYear());
            statement.setInt(6, workload.getWorkloadId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean deleteWorkload(int workloadId) {
        String sql = "DELETE FROM workload WHERE workload_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workloadId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Workload> searchByFaculty(int facultyId) {
        List<Workload> workloadList = new ArrayList<>();
        String sql = "SELECT * FROM workload WHERE faculty_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) workloadList.add(readWorkload(result));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return workloadList;
    }

    public int getTotalHoursByFaculty(int facultyId) {
        String sql = "SELECT COALESCE(SUM(hours_per_week), 0) FROM workload WHERE faculty_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, facultyId);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) return result.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // =========================================================
    // AUTO DISTRIBUTE WORKLOAD
    // =========================================================
    // Reassigns every existing workload record across all faculty.
    // The records themselves are preserved; only faculty_id changes.
    // Records are assigned from largest to smallest workload to the
    // faculty with the lowest newly calculated total.
    public boolean autoDistributeWorkload() {

        String facultySql =
                "SELECT faculty_id FROM faculty ORDER BY faculty_id";

        String workloadSql =
                "SELECT workload_id, hours_per_week " +
                        "FROM workload " +
                        "ORDER BY hours_per_week DESC, workload_id";

        String updateSql =
                "UPDATE workload SET faculty_id = ? WHERE workload_id = ?";

        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            List<Integer> facultyIds = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(facultySql);
                 ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    facultyIds.add(result.getInt("faculty_id"));
                }
            }

            if (facultyIds.isEmpty()) {
                connection.rollback();
                return false;
            }

            List<DistributionRecord> records = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(workloadSql);
                 ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    records.add(new DistributionRecord(
                            result.getInt("workload_id"),
                            result.getInt("hours_per_week")
                    ));
                }
            }

            if (records.isEmpty()) {
                connection.rollback();
                return false;
            }

            // Start from zero because this is a fresh redistribution.
            Map<Integer, Integer> distributedHours = new HashMap<>();
            for (Integer facultyId : facultyIds) {
                distributedHours.put(facultyId, 0);
            }

            try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
                for (DistributionRecord record : records) {
                    int selectedFaculty = facultyIds.get(0);
                    int lowestHours = distributedHours.get(selectedFaculty);

                    for (Integer facultyId : facultyIds) {
                        int currentHours = distributedHours.get(facultyId);
                        if (currentHours < lowestHours) {
                            lowestHours = currentHours;
                            selectedFaculty = facultyId;
                        }
                    }

                    statement.setInt(1, selectedFaculty);
                    statement.setInt(2, record.workloadId);
                    statement.addBatch();

                    distributedHours.put(
                            selectedFaculty,
                            distributedHours.get(selectedFaculty) + record.hoursPerWeek
                    );
                }
                statement.executeBatch();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try { connection.rollback(); }
                catch (SQLException rollbackException) { rollbackException.printStackTrace(); }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    private Workload readWorkload(ResultSet result) throws SQLException {
        Workload workload = new Workload();
        workload.setWorkloadId(result.getInt("workload_id"));
        workload.setFacultyId(result.getInt("faculty_id"));
        workload.setSubjectId(result.getInt("subject_id"));
        workload.setHoursPerWeek(result.getInt("hours_per_week"));
        workload.setSemester(result.getInt("semester"));
        workload.setAcademicYear(result.getString("academic_year"));
        return workload;
    }

    private static class DistributionRecord {
        private final int workloadId;
        private final int hoursPerWeek;

        DistributionRecord(int workloadId, int hoursPerWeek) {
            this.workloadId = workloadId;
            this.hoursPerWeek = hoursPerWeek;
        }
    }
}
