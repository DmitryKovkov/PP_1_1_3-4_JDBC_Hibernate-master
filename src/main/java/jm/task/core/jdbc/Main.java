package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Петя", "Васечкин", (byte) 15);
        User user2 = new User("Вася", "Петров", (byte) 14);
        User user3 = new User("Лелик", "Иванов", (byte) 11);
        User user4 = new User("Болик", "Иванов", (byte) 14);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        System.out.println(userService.getAllUsers());
        userService.removeUserById(5);
        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
