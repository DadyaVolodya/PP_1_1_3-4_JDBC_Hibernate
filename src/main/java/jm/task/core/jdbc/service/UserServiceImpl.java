package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoJDBCImpl();
    UserDao UserDaoHibernate = new UserDaoHibernateImpl();

    public UserServiceImpl() {
    }
    @Override
    public void createUsersTable() throws SQLException {
         UserDaoHibernate.createUsersTable();
    }
    @Override
    public void dropUsersTable() throws SQLException {
            UserDaoHibernate.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoHibernate.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        UserDaoHibernate.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }
    @Override
    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();

    }
}
