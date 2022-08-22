package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connection = null;
        String sql = "CREATE TABLE `test1`.`zh22` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";

        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при создании таблицы");
                }
            }
            log.severe("Произошла ошибка SQLException при создании таблицы");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при создании таблицы");
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        String sql = "DROP TABLE `test1`.`zh22`;";
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при удалении таблицы");
                }
            }
            log.severe("Произошла ошибка SQLException при удалении таблицы");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при удалении таблицы");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        String sql = "INSERT INTO zh22 (name, lastName, age) values (?, ?, ?);";
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при добавлении строки");
                }
            }
            log.severe("Произошла ошибка SQLException при добавлении строки");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при добавлении строки");
            }
        }

    }

    public void removeUserById(long id) {
        Connection connection = null;
        String sql = "DELETE FROM zh22 where id=?;";
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (int) id);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при удалении строки");
                }
            }
            log.severe("Произошла ошибка SQLException при удалении строки");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при удалении строки");
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = null;
        List<User> userList = new ArrayList<>();
        String sql = "select name, lastName, age from zh22";
        Statement statement = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при запросе всех пользователей");
                }
            }
            log.severe("Произошла ошибка SQLException при запросе всех пользователей");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при запросе всех пользователей");
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        String sql = "DELETE FROM `test1`.`zh22`;";
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.severe("Произошла ошибка SQLException при очистке таблицы");
                }
            }
            log.severe("Произошла ошибка SQLException при очистке таблицы");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.severe("Произошла ошибка SQLException при очистке таблицы");
            }
        }
    }
}
