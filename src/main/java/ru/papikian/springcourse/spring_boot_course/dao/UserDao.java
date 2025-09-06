package ru.papikian.springcourse.spring_boot_course.dao;



import ru.papikian.springcourse.spring_boot_course.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void add(User user);
    void update(User user);
    void delete(Integer id);
    User getUserById(int id);
}
