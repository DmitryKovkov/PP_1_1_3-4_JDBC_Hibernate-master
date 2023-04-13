package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

    public static SessionFactory ConnectionHibernate() {
        Configuration configuration =  new Configuration().addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }


    // реализуйте настройку соеденения с БД
}
