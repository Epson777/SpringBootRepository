package ru.papikian.springcourse.spring_boot_course.repository;

import ru.papikian.springcourse.spring_boot_course.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByUserId(Integer id);
    User getUserByUserUsername(String username);
    void updateUser(User user);
    void deleteUser(Integer id);
}
