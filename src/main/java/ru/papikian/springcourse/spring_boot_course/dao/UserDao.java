package ru.papikian.springcourse.spring_boot_course.dao;

import ru.papikian.springcourse.spring_boot_course.models.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUserById(Integer id);
    User getUserById(Integer id);
}
