package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age INT NOT NULL)";
            statement.execute(query);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "DROP TABLE IF EXISTS users";
            statement.execute(query);
            System.out.println("Table dropped successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User removed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, lastName, (byte) age);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = "TRUNCATE TABLE users";
            statement.execute(query);
            System.out.println("Table cleaned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
