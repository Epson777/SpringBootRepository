package ru.papikian.springcourse.spring_boot_course.service;



import ru.papikian.springcourse.spring_boot_course.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
    User getUserById(int id);
}
