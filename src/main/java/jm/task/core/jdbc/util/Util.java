package jm.task.core.jdbc.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Util {

    private Util() {}

    public static DataSource connectionMySql() {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/new_test",
                "root","root");
        return dataSource;
    }
    // реализуйте настройку соеденения с БД
}
