package com.faculty.management.dao;

import com.faculty.management.db.DatabaseConnection;
import com.faculty.management.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    // =====================================================
    // ADD USER
    // =====================================================

    public boolean addUser(User user) {

        String sql =
                "INSERT INTO users " +
                        "(username, password, role) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getPassword()
            );

            statement.setString(
                    3,
                    user.getRole()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // UPDATE USER
    // =====================================================

    public boolean updateUser(User user) {

        String sql =
                "UPDATE users SET " +
                        "username = ?, " +
                        "password = ?, " +
                        "role = ? " +
                        "WHERE user_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getPassword()
            );

            statement.setString(
                    3,
                    user.getRole()
            );

            statement.setInt(
                    4,
                    user.getUserId()
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // DELETE USER
    // =====================================================

    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM users " +
                        "WHERE user_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // =====================================================
    // GET ALL USERS
    // =====================================================

    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql =
                "SELECT user_id, username, " +
                        "password, role " +
                        "FROM users " +
                        "ORDER BY user_id";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                User user =
                        new User();

                user.setUserId(
                        result.getInt(
                                "user_id"
                        )
                );

                user.setUsername(
                        result.getString(
                                "username"
                        )
                );

                user.setPassword(
                        result.getString(
                                "password"
                        )
                );

                user.setRole(
                        result.getString(
                                "role"
                        )
                );

                users.add(user);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }
}
