package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.ConnectionHibernate();

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session
                .createSQLQuery("CREATE TABLE user(" +
                "id serial primary key," +
                "first_name varchar(20)," +
                "last_name varchar(20)," +
                "age tinyint unsigned )").addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("drop table USER");
        query.executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String firstname, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        try{
             session.beginTransaction();
             User user = new User(firstname, lastName, age);
             session.save(user);
             session.getTransaction().commit();
         } finally {
            session.close();
         }

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from User where id = :id")
                .setString("id", String.valueOf(id));
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            List<User> userList = session.createQuery("SELECT a from User a", User.class).getResultList();
            session.getTransaction().commit();
            return userList;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Sysqtem.out.println("Сессия очистки таблицы");
        Session session = sessionFactory.getCurrentSession();
       try{
           session.beginTransaction();
           Query query = session.createQuery("delete from User ");
           query.executeUpdate();
           session.getTransaction().commit();
       } finally {
           session.close();
       }
    }
}
