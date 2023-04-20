package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    //language=SQL
    private static final String SQL_INSERT = "insert into user(first_name, last_name, age) values (?,?,?)";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from user";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from user where id = ?";

    //language=SQL
    private static final String SQL_DELETE = "delete from user";

    //language=SQL
    private static final String SQL_DROP = "drop table user";

    //language=SQL
    private static final String SQL_CREATE = "CREATE TABLE user(" +
            "id serial primary key," +
            "first_name varchar(20)," +
            "last_name varchar(20)," +
            "age tinyint unsigned )";


    //   private Jdbc;
    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        //this.jdbcTemplate = new JdbcTemplate(dataSource);
       this.connection = connection;
    }

    public void createUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //  jdbcTemplate.update(SQL_CREATE);
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DROP)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //  jdbcTemplate.update(SQL_DROP);
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // jdbcTemplate.update(SQL_INSERT, name, lastName, age);
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID) ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet= statement.executeQuery(SQL_SELECT_ALL)) {
            while (resultSet.next()) {
                userList.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_DELETE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
