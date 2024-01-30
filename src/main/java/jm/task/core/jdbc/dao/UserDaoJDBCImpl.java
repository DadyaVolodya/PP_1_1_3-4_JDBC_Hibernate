package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.createStatement(connection)) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age INT NOT NULL)";
            statement.execute(query);
            System.out.println("Table created JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    @Override
    public void dropUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.createStatement(connection)) {
            String query = "DROP TABLE IF EXISTS users";
            statement.execute(query);
            System.out.println("Table dropped JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User saved JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User removed JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = Util.createStatement(connection)) {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, lastName, (byte) age);
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             Statement statement = Util.createStatement(connection)) {
            String query = "TRUNCATE TABLE users";
            statement.execute(query);
            System.out.println("Table cleaned JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
