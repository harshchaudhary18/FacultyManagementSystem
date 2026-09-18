package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {

    // =========================================================
    // ADD TIMETABLE
    // =========================================================
    public boolean addTimetable(
            int facultyId,
            int subjectId,
            String day,
            String startTime,
            String endTime,
            String roomNo) {

        String sql = "INSERT INTO timetable " +
                "(faculty_id, subject_id, day, start_time, end_time, room_no) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, facultyId);
            statement.setInt(2, subjectId);
            statement.setString(3, day);
            statement.setTime(4, Time.valueOf(startTime));
            statement.setTime(5, Time.valueOf(endTime));
            statement.setString(6, roomNo);

            return statement.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // UPDATE TIMETABLE
    // =========================================================
    public boolean updateTimetable(
            int timetableId,
            int facultyId,
            int subjectId,
            String day,
            String startTime,
            String endTime,
            String roomNo) {

        String sql = "UPDATE timetable SET " +
                "faculty_id = ?, " +
                "subject_id = ?, " +
                "day = ?, " +
                "start_time = ?, " +
                "end_time = ?, " +
                "room_no = ? " +
                "WHERE timetable_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, facultyId);
            statement.setInt(2, subjectId);
            statement.setString(3, day);
            statement.setTime(4, Time.valueOf(startTime));
            statement.setTime(5, Time.valueOf(endTime));
            statement.setString(6, roomNo);
            statement.setInt(7, timetableId);

            return statement.executeUpdate() > 0;

        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // DELETE TIMETABLE
    // =========================================================
    public boolean deleteTimetable(int timetableId) {

        String sql =
                "DELETE FROM timetable WHERE timetable_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, timetableId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // GET ALL TIMETABLE
    // =========================================================
    public List<TimetableRecord> getAllTimetable() {

        List<TimetableRecord> list = new ArrayList<>();

        String sql =
                "SELECT t.timetable_id, " +
                        "t.faculty_id, " +
                        "f.faculty_name AS faculty_name, " +
                        "t.subject_id, " +
                        "s.subject_name, " +
                        "t.day, " +
                        "t.start_time, " +
                        "t.end_time, " +
                        "t.room_no " +
                        "FROM timetable t " +
                        "JOIN faculty f ON t.faculty_id = f.faculty_id " +
                        "JOIN subjects s ON t.subject_id = s.subject_id " +
                        "ORDER BY t.faculty_id, " +
                        "FIELD(t.day, " +
                        "'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'), " +
                        "t.start_time";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                list.add(new TimetableRecord(
                        result.getInt("timetable_id"),
                        result.getInt("faculty_id"),
                        result.getString("faculty_name"),
                        result.getInt("subject_id"),
                        result.getString("subject_name"),
                        result.getString("day"),
                        result.getTime("start_time").toString(),
                        result.getTime("end_time").toString(),
                        result.getString("room_no")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // GET FACULTY WEEKLY TIMETABLE
    // =========================================================
    public List<TimetableRecord> getFacultyWeeklyTimetable(int facultyId) {

        List<TimetableRecord> list = new ArrayList<>();

        String sql =
                "SELECT t.timetable_id, " +
                        "t.faculty_id, " +
                        "f.faculty_name AS faculty_name, " +
                        "t.subject_id, " +
                        "s.subject_name, " +
                        "t.day, " +
                        "t.start_time, " +
                        "t.end_time, " +
                        "t.room_no " +
                        "FROM timetable t " +
                        "JOIN faculty f ON t.faculty_id = f.faculty_id " +
                        "JOIN subjects s ON t.subject_id = s.subject_id " +
                        "WHERE t.faculty_id = ? " +
                        "ORDER BY FIELD(t.day, " +
                        "'Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'), " +
                        "t.start_time";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, facultyId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    list.add(new TimetableRecord(
                            result.getInt("timetable_id"),
                            result.getInt("faculty_id"),
                            result.getString("faculty_name"),
                            result.getInt("subject_id"),
                            result.getString("subject_name"),
                            result.getString("day"),
                            result.getTime("start_time").toString(),
                            result.getTime("end_time").toString(),
                            result.getString("room_no")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // GET TODAY'S TIMETABLE
    // =========================================================
    public List<TimetableRecord> getTodayTimetable(int facultyId) {

        List<TimetableRecord> list = new ArrayList<>();

        String sql =
                "SELECT t.timetable_id, " +
                        "t.faculty_id, " +
                        "f.faculty_name AS faculty_name, " +
                        "t.subject_id, " +
                        "s.subject_name, " +
                        "t.day, " +
                        "t.start_time, " +
                        "t.end_time, " +
                        "t.room_no " +
                        "FROM timetable t " +
                        "JOIN faculty f ON t.faculty_id = f.faculty_id " +
                        "JOIN subjects s ON t.subject_id = s.subject_id " +
                        "WHERE t.faculty_id = ? " +
                        "AND t.day = DAYNAME(CURDATE()) " +
                        "ORDER BY t.start_time";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, facultyId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    list.add(new TimetableRecord(
                            result.getInt("timetable_id"),
                            result.getInt("faculty_id"),
                            result.getString("faculty_name"),
                            result.getInt("subject_id"),
                            result.getString("subject_name"),
                            result.getString("day"),
                            result.getTime("start_time").toString(),
                            result.getTime("end_time").toString(),
                            result.getString("room_no")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // TIMETABLE RECORD
    // =========================================================
    public static class TimetableRecord {

        private int timetableId;
        private int facultyId;
        private String facultyName;
        private int subjectId;
        private String subjectName;
        private String day;
        private String startTime;
        private String endTime;
        private String roomNo;


        public TimetableRecord(
                int timetableId,
                int facultyId,
                String facultyName,
                int subjectId,
                String subjectName,
                String day,
                String startTime,
                String endTime,
                String roomNo) {

            this.timetableId = timetableId;
            this.facultyId = facultyId;
            this.facultyName = facultyName;
            this.subjectId = subjectId;
            this.subjectName = subjectName;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.roomNo = roomNo;
        }


        public int getTimetableId() {
            return timetableId;
        }

        public int getFacultyId() {
            return facultyId;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public String getDay() {
            return day;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getRoomNo() {
            return roomNo;
        }
    }
}