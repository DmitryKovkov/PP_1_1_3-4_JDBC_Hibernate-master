package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
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

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("id");
        String firstname = rs.getString("first_name");
        String lastname = rs.getString("last_name");
        byte age = rs.getByte("age");
        return new User(id, firstname, lastname, age);
    };

    private JdbcTemplate jdbcTemplate;

    public UserDaoJDBCImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createUsersTable() {
        jdbcTemplate.update(SQL_CREATE);
    }

    public void dropUsersTable() {
        jdbcTemplate.update(SQL_DROP);
    }

    public void saveUser(String name, String lastName, byte age) {
        jdbcTemplate.update(SQL_INSERT, name, lastName, age);
    }

    public void removeUserById(long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    public void cleanUsersTable() {
        jdbcTemplate.update(SQL_DELETE);
    }
}
